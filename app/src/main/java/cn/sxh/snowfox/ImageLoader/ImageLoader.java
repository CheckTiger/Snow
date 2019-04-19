package cn.sxh.snowfox.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.util.LruCache;
import android.widget.ImageView;

import com.socks.library.KLog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import cn.sxh.snowfox.R;


/**
 * @package-name: cn.sxh.snowfox.ImageLoader
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/4/18 0018 : 20 :38
 * @project-name: Snow
 */

public class ImageLoader {

    private static final String TAG = "ImageLoader";
    private static final int MESSAGE_POST_RESULT = 1;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();


    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXINUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final long KEEP_ALIVE = 10L;
    private static final int TAG_KEY_URI = 155;
    private static final long DISK_CACHE_SIZE = 1024 * 1024 * 50;
    private static final int IO_BUFFER_SIZE = 8 * 1024;
    private static final int DISK_CACHE_INDEX = 0;
    private boolean mIsDiskLruCacheCreated = false;

    /**
     * 线程工厂
     */
    private static final ThreadFactory sTHREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);
        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, TAG + "#" + mCount.getAndIncrement());
        }
    };

    /**
     * 创建线程池
     */
    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXINUM_POOL_SIZE,
            KEEP_ALIVE, TimeUnit.SECONDS,new LinkedBlockingDeque<>(),sTHREAD_FACTORY);

    private Handler mMainHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            LoaderResult result = (LoaderResult) msg.obj;
            ImageView imageView = result.imageView;
            imageView.setImageBitmap(result.bitmap);
            String uri = (String) imageView.getTag(TAG_KEY_URI);
            if (uri.equals(result.uri)) {
                imageView.setImageBitmap(result.bitmap);
            } else {
                KLog.w(TAG,"set the bitmap,but the uri has changed,ignored!");
            }
        }
    };

    private LruCache<String, Bitmap> mMemoryCache;
    private DiskLruCache mDiskLruCache;
    private Context mContext;
    private ImageResizer mImageResizer = new ImageResizer();


    /**
     * ImageLoader的初始化操作
     * @param context
     */
    private ImageLoader(Context context) {
        this.mContext = context;
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };

        File diskCacheDir = getDiskCacheDir(mContext, "bitmap");
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdirs();
        }

        if (getUsableSpace(diskCacheDir) > DISK_CACHE_SIZE) {
            try {
                mDiskLruCache = DiskLruCache.open(diskCacheDir, 1, 1, DISK_CACHE_SIZE);
                mIsDiskLruCacheCreated = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建ImageLoader的一个实例对象
     * @param context
     * @return
     */
    public static ImageLoader build(Context context) {
        return new ImageLoader(context);
    }

    /**
     * 将bitmap存储到内存缓存中
     * @param key
     * @param bitmap
     */
    private void addBitMapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * 判断内存缓存中是否有此Bitmap的缓存，有则返回，无则将此Bitmap缓存到内存中
     * @param key
     * @return
     */
    private Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    public void bindBitmap(final String uri, final ImageView imageView) {
        bindBitmap(uri, imageView, 0, 0);
    }

    private void bindBitmap(String uri, ImageView imageView, int reqWidth, int reqHeight) {
        imageView.setTag(uri);
        Bitmap bitmap = loadBitmapFromMemCache(uri);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }

        Runnable loadBitmapTask = () -> {
            Bitmap bitmap1 = null;
            try {
                bitmap1 = loadBitmap(uri, reqWidth, reqHeight);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bitmap1 != null) {
                LoaderResult result = new LoaderResult(imageView, uri, bitmap1);
                mMainHandler.obtainMessage(MESSAGE_POST_RESULT, result).sendToTarget();
            }
        };
        THREAD_POOL_EXECUTOR.execute(loadBitmapTask);
    }

    private Bitmap loadBitmap(String url, int reqWidth, int reqHeight) throws IOException {
        Bitmap bitmap = loadBitmapFromMemCache(url);
        if (bitmap != null) {
            KLog.d(TAG, "loadBitmapFromMemCache,uri: " + url);
            return bitmap;
        }
        try {
            bitmap = loadBitmapFromDiskCache(url, reqWidth, reqHeight);
            if (bitmap != null) {
                KLog.d(TAG, "loadBitmapFromDiskCache,uri: " + url);
                return bitmap;
            }
            bitmap = loadBitmapFromHttp(url, reqWidth, reqHeight);
            KLog.d(TAG, "loadBitmapFromHttp,uri: " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bitmap != null && !mIsDiskLruCacheCreated) {
            KLog.w(TAG, "DiskLruCache is not created" + url);
            bitmap = downLoaditmapFromUrl(url);
        }
        return bitmap;
    }

    private Bitmap loadBitmapFromMemCache(String url) {
        final String key = hashKeyFromUrl(url);
        Bitmap bitmap = getBitmapFromMemCache(key);
        return bitmap;
    }

    private Bitmap loadBitmapFromHttp(String url, int reqWidth, int reqHeight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("can not visit network from UIThread");
        }
        if (mDiskLruCache == null) {
            return null;
        }
        String key = hashKeyFromUrl(url);
        DiskLruCache.Editor editor = mDiskLruCache.edit(key);
        if (editor != null) {
            OutputStream outputStream = editor.newOutputStream(DISK_CACHE_INDEX);
            if (downloadUrlToStream(url, outputStream)) {
                editor.commit();
            } else {
                editor.abort();
            }
            mDiskLruCache.flush();
        }
        return loadBitmapFromDiskCache(url, reqWidth, reqHeight);
    }

    private Bitmap loadBitmapFromDiskCache(String url, int reqWidth, int reqHeight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            KLog.w(TAG, "load bitmap from UIThread,it is not recommended");
        }
        if (mDiskLruCache == null) {
            return null;
        }
        Bitmap bitmap = null;
        String key = hashKeyFromUrl(url);
        DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
        if (snapshot != null) {
            FileInputStream fileInputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
            FileDescriptor fileDescriptor = fileInputStream.getFD();
            bitmap = mImageResizer.decodeSampledBitmapFromFileDescriptor(fileDescriptor, reqWidth, reqHeight);
            if (bitmap != null) {
                addBitMapToMemoryCache(key,bitmap);
            }
        }
        return bitmap;
    }

    public boolean downloadUrlToStream(String urlString, OutputStream outputStream) throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            in.close();
            out.close();
        }
        return false;
    }

    private Bitmap downLoaditmapFromUrl(String urlString) throws IOException{
        Bitmap bitmap = null;
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            in.close();
        }
        return bitmap;
    }

    private String hashKeyFromUrl(String url) {
        String cacheKey;
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            cacheKey = bytesToHexString(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xFF & digest[i]);
            if (hex.length() == 1) {
                sb.append("0");
            }
            sb.append(hex);
        }
        return sb.toString();
    }



    private File getDiskCacheDir(Context context, String bitmap) {
        boolean externalStorageAvaiable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if (externalStorageAvaiable) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        String diskCacheDir = cachePath + File.separator + bitmap + File.separator;
        File file = new File(diskCacheDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private long getUsableSpace(File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return path.getUsableSpace();
        }
        final StatFs statFs = new StatFs(path.getPath());
        return (long) statFs.getBlockSize() * (long) statFs.getAvailableBlocks();
    }

    private static class LoaderResult{
        public ImageView imageView;
        public String uri;
        public Bitmap bitmap;

        public LoaderResult(ImageView imageView, String uri, Bitmap bitmap) {
            this.imageView = imageView;
            this.uri = uri;
            this.bitmap = bitmap;
        }
    }
}
