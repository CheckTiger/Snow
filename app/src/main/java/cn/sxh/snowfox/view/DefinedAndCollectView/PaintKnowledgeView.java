package cn.sxh.snowfox.view.DefinedAndCollectView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
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
    public int DEFAULT_TYPE = 2;//线性渐变
    public static final int LINEAR_GRADIENT = 1;//线性渐变
    public static final int RadialGradient  = 2;//线性渐变
    private Paint mPaint;//画笔
    private Shader mShader;//着色器
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

        if (DEFAULT_TYPE == LINEAR_GRADIENT) {
            mShader = new LinearGradient(100, 100, 500, 500,
                    Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        }

        if (DEFAULT_TYPE == RadialGradient) {
            mShader = new RadialGradient(300, 300,200,
                    Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        }

        mPaint.setShader(mShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (DEFAULT_TYPE == LINEAR_GRADIENT) {
            canvas.drawCircle(300,300,200,mPaint);
        }

        if (DEFAULT_TYPE == RadialGradient) {
            canvas.drawCircle(300,300,200,mPaint);
        }
    }

    public void setViewType(int type) {
        this.DEFAULT_TYPE = type;
    }
}
