package cn.sxh.snowfox.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import okhttp3.internal.cache.DiskLruCache;

/**
 * @package-name: cn.sxh.snowfox.ImageLoader
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/4/18 0018 : 20 :38
 * @project-name: Snow
 */

public class ImageLoader {
    private LruCache<String, Bitmap> mMemoryCache;
    private DiskLruCache mDiskLruCache;
    private Context mContext;

    public ImageLoader(Context context) {
        this.mContext = context;
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }
}
