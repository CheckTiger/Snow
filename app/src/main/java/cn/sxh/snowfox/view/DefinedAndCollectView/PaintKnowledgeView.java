package cn.sxh.snowfox.view.DefinedAndCollectView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.sxh.snowfox.R;

/**
 * Created by Administrator on 2017/10/31 0031.
 */

public class PaintKnowledgeView extends View {
    /**
     * Shader.TileMode 是枚举值，有三个值CLAMP 、REPEAT 和 MIRROR
     * 1.CLAMP表示。当所画图形的尺寸大于给定的区域，会用给定区域四边的颜色来填充剩余的空间
     * 2.REPEAT表示，当我们绘制的图形尺寸大于Bitmap尺寸时，会用Bitmap重复平铺整个绘制的区域。
     * 3.MIRROR表示，当绘制的图形尺寸大于Bitmap尺寸时，MIRROR也会用Bitmap重复平铺整个绘图区域，
     * 与REPEAT不同的是，两个相邻的Bitmap互为镜像。
     */
    public int DEFAULT_TYPE = 0;//默认无使用着色器
    public static final int LINEAR_GRADIENT = 1;//线性渐变
    public static final int RadialGradient  = 2;//辐射渐变
    public static final int SweepGradient   = 3;//扫描渐变
    public static final int BitmapShader    = 4;//BitmapShader
    public static final int ComposeShader   = 5;//混合着色器
    public static final int LightingColorFilte   = 6;//为绘制设置颜色过滤。颜色过滤的意思，就是为绘制的内容设置一个统一的过滤策略，然后 Canvas.drawXXX() 方法会对每个像素都进行过滤后再绘制出来
    public static final int Xfermode        = 7;//
    private Paint mPaint;//画笔
    private Shader mShader;//着色器
    private Bitmap bitmap,bitmap1,bitmap2;
    private ColorFilter colorFilter1,colorFilter2;
    private Xfermode xfermode1,xfermode2,xfermode3;


    public PaintKnowledgeView(Context context) {
        this(context,null);
    }

    public PaintKnowledgeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PaintKnowledgeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PaintKnowledgeView);
        DEFAULT_TYPE = a.getInteger(R.styleable.PaintKnowledgeView_type,LINEAR_GRADIENT);
        a.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (DEFAULT_TYPE == 0) {
            // TODO: 2017/11/1 0001 设置view类型为0，是为了区别显示，删除此处类型也没事
        }
        
        if (DEFAULT_TYPE == LINEAR_GRADIENT) {
            mShader = new LinearGradient(100, 100, 500, 500,
                    Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
            mPaint.setShader(mShader);
        }

        if (DEFAULT_TYPE == RadialGradient) {
            mShader = new RadialGradient(300, 200,200,
                    Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);
            mPaint.setShader(mShader);
        }

        if (DEFAULT_TYPE == SweepGradient) {
            mShader = new SweepGradient(300, 200,
                    Color.parseColor("#E91E63"), Color.parseColor("#2196F3"));
            mPaint.setShader(mShader);
        }

        if (DEFAULT_TYPE == BitmapShader) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
            mShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mPaint.setShader(mShader);
        }

        if (DEFAULT_TYPE == ComposeShader) {
            setLayerType(LAYER_TYPE_SOFTWARE,null);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
            Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

            Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
            Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

            mShader = new ComposeShader(shader, shader1, PorterDuff.Mode.SRC_OVER);
            mPaint.setShader(mShader);
        }

        if (DEFAULT_TYPE == LightingColorFilte) {
            colorFilter1 = new LightingColorFilter(0x00ffff, 0x000000);
            colorFilter2 = new LightingColorFilter(0xffffff, 0x003000);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        }

        if (DEFAULT_TYPE == Xfermode) {
            xfermode1 = new PorterDuffXfermode(PorterDuff.Mode.SRC);
            xfermode2 = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
            xfermode3 = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
            bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
            bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (DEFAULT_TYPE == 0) {
            canvas.drawCircle(200,100,100,mPaint);
        }

        if (DEFAULT_TYPE == LINEAR_GRADIENT) {
            canvas.drawCircle(300,200,200,mPaint);
        }

        if (DEFAULT_TYPE == RadialGradient) {
            canvas.drawCircle(300,200,200,mPaint);
        }

        if (DEFAULT_TYPE == SweepGradient) {
            canvas.drawCircle(300,200,200,mPaint);
        }

        if (DEFAULT_TYPE == BitmapShader) {
            canvas.drawCircle(200,200,200,mPaint);
        }

        if (DEFAULT_TYPE == ComposeShader) {
            canvas.drawCircle(200, 200, 200, mPaint);
        }

        if (DEFAULT_TYPE == LightingColorFilte) {
            mPaint.setColorFilter(colorFilter1);
            canvas.drawBitmap(bitmap, 0, 0, mPaint);

            mPaint.setColorFilter(colorFilter2);
            canvas.drawBitmap(bitmap, bitmap.getWidth() + 100, 0, mPaint);
        }

        if (DEFAULT_TYPE == Xfermode) {
            int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

            canvas.drawBitmap(bitmap1, 0, 0, mPaint);
            mPaint.setXfermode(xfermode1);
            canvas.drawBitmap(bitmap2, 0, 0, mPaint);
            mPaint.setXfermode(null);

            canvas.drawBitmap(bitmap1, bitmap1.getWidth(), 0, mPaint);
            mPaint.setXfermode(xfermode2);
            canvas.drawBitmap(bitmap2, bitmap1.getWidth(), 0, mPaint);
            mPaint.setXfermode(null);

            canvas.drawBitmap(bitmap1, 0, bitmap1.getHeight() + 20, mPaint);
            mPaint.setXfermode(xfermode3);
            canvas.drawBitmap(bitmap2, 0, bitmap1.getHeight() + 20, mPaint);
            mPaint.setXfermode(null);

            canvas.restoreToCount(saved);
        }
    }

    public void setViewType(int type) {
        this.DEFAULT_TYPE = type;
    }
}
