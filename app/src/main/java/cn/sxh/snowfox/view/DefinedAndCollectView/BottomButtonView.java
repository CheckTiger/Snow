package cn.sxh.snowfox.view.DefinedAndCollectView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.sxh.snowfox.R;

/**
 * Created by snow on 2017/7/30.
 */

public class BottomButtonView extends View {
    private float mBorderWidth; //默认背景色
    private int mBorderColor; //默认高度
    private static int DEFAULT_CANVAS_COLOR = 0xff000000;//画布颜色

    private Paint mPaint;//画笔
    private RectF mBounds;//
    private float width;//宽度
    private float height;//高度
    private float radius;//角度
    private float smallLength;//最小长度
    private float largeLength;//最大长度
    public BottomButtonView(Context context) {
        super(context);
        init(context, null);
    }

    public BottomButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }


    public BottomButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    /**
     * 初始化配置属性
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BottomButtonView,0,0);
        mBorderColor = a.getColor(R.styleable.BottomButtonView_border_color,0xf600000);
        mBorderWidth = a.getDimension(R.styleable.BottomButtonView_default_width,2);
        a.recycle();
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//用于绘制时抗锯齿
//        mPaint.setAntiAlias(true);//用于绘制时抗锯齿
        mPaint.setStyle(Paint.Style.STROKE);//描边
        mPaint.setStrokeWidth(mBorderWidth);//设置边缘宽度
        mPaint.setColor(mBorderColor);//设置画笔颜色
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBounds = new RectF(getLeft(),getTop(),getRight(),getBottom());
        width = mBounds.right - mBounds.left;
        height = mBounds.bottom - mBounds.top;
        if (width < height) {
            radius = width / 4;
        } else {
            radius = height/4;
        }
        smallLength = 10;
        largeLength = 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(DEFAULT_CANVAS_COLOR);
        mPaint.setColor(0x66555555);
        RectF rectF = new RectF(mBounds.centerX() - (float) 0.9 * width / 2,
                mBounds.centerY() - (float) 0.9 * height / 2, mBounds.centerX() + (float) 0.9 * width / 2, mBounds.centerY() + (float) 0.9 * height / 2);
        canvas.drawRoundRect(rectF,30,30,mPaint);
        mPaint.setColor(mBorderColor);
        canvas.drawCircle(mBounds.centerX(), mBounds.centerY(), radius, mPaint);
        float start_x , start_y;
        float end_x,end_y;
        for (int i = 0; i < 60; i++) {
            start_x = (radius * (float) Math.cos(Math.PI / 180 * i * 6));
            start_y = (radius * (float) Math.sin(Math.PI / 180 * i * 6));
            if (i % 5 == 0) {
                end_x = start_x + largeLength * (float) Math.cos(Math.PI / 180 * i * 6);
                end_y = start_y + largeLength * (float) Math.sin(Math.PI / 180 * i * 6);
            } else {
                end_x = start_x + smallLength * (float) Math.cos(Math.PI / 180 * i * 6);
                end_y = start_y + smallLength * (float) Math.sin(Math.PI / 180 * i * 6);
            }
            start_x += mBounds.centerX();
            end_x += mBounds.centerX();
            start_y += mBounds.centerY();
            end_y += mBounds.centerY();
            canvas.drawLine(start_x, start_y, end_x, end_y, mPaint);
        }
        canvas.drawCircle(mBounds.centerX(),mBounds.centerY(),20,mPaint);
        canvas.rotate(60, mBounds.centerX(), mBounds.centerY());
        canvas.drawLine(mBounds.centerX(),mBounds.centerY(),mBounds.centerX(),mBounds.centerY()-radius,mPaint);
    }
}
