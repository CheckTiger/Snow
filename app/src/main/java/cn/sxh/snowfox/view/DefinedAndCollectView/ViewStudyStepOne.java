package cn.sxh.snowfox.view.DefinedAndCollectView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.sxh.snowfox.R;

/**
 * @author by snow on 2017/9/30
 * @time 20:54
 * @mail snowtigersong@gmail.com
 */

public class ViewStudyStepOne extends View {
    private int DEFAULT_VIEW_TYPE = 1;//默认
    public static final int COLOR_VIEW = 1;//背景
    public static final int CIRCLE_VIEW = 2;//圆形
    public static final int RECT_VIEW = 3;//矩形
    public static final int POINT_VIEW = 4;//圆点
    public static final int OVAL_VIEW = 5;//椭圆
    public static final int LINE_VIEW = 6;//线
    public static final int ROUND_RECT_VIEW = 7;//圆角矩形
    public static final int ARC_VIEW = 8;//
    public static final int PATH_VIEW = 9;//路径view
    public static final int HISTOGRAM_VIEW = 10;//条形图
    public static final int PIE_CHART_VIEW = 11;//饼状图
    public static final int COLOR_ALPH_VIEW = 12;//透明设置
    private Paint mPaint;
    private Path mPath = new Path();

    private String[] mStrings = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
    private int[] mLeftString = new int[7];
    private int mLeft = 58;
    private int mRight = mLeft + 75;
    private int mBottom = 400;
    private int mBottomText = 420;
    private int mTop;
    private Path histogram = new Path();
    private Rect mRect = new Rect();
    private Paint mPaintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
    public ViewStudyStepOne(Context context) {
        this(context,null);
    }

    public ViewStudyStepOne(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewStudyStepOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewStudyStepOne);
        DEFAULT_VIEW_TYPE = a.getInteger(R.styleable.ViewStudyStepOne_view_type,1);
//        loadPath();
        setHistogramPath();
    }

    private void setHistogramPath(){
        histogram.moveTo(50, 400);
        histogram.lineTo(50, 50);
        histogram.moveTo(50, 400);
        histogram.lineTo(650, 400);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void loadPath() {
        mPath.moveTo(400, 300);
        mPath.lineTo(305, 170);
        mPath.arcTo(300, 100, 400, 200, -199, 199, false);
        mPath.arcTo(400, 100, 500, 200, -180, 199, false);
        mPath.lineTo(495, 170);
        mPath.close();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
        if (DEFAULT_VIEW_TYPE == COLOR_VIEW) {
            canvas.drawColor(Color.GRAY);//设置背景色
        }
        if (DEFAULT_VIEW_TYPE == CIRCLE_VIEW) {
            canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/3,mPaint);//画圆
        }
        if (DEFAULT_VIEW_TYPE == RECT_VIEW) {
            canvas.drawRect(100,200,200,300,mPaint);
        }
        if (DEFAULT_VIEW_TYPE == POINT_VIEW) {
            canvas.drawPoint(250,250,mPaint);
        }
        if (DEFAULT_VIEW_TYPE == OVAL_VIEW) {
            canvas.drawOval(200, 300, 500, 400, mPaint);
        }
        if (DEFAULT_VIEW_TYPE == LINE_VIEW) {
            canvas.drawLine(200, 200, 500, 400, mPaint);
        }
        if (DEFAULT_VIEW_TYPE == ROUND_RECT_VIEW) {
            canvas.drawRoundRect(200, 300, 500, 400, 50, 50, mPaint);
        }
        if (DEFAULT_VIEW_TYPE == ARC_VIEW) {
            RectF rectF = new RectF(100, 50, 700, 450);
            canvas.drawArc(rectF, -100, 100, true, mPaint);//绘制扇形
            canvas.drawArc(rectF, 20, 140, false, mPaint); // 绘制弧形
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawArc(rectF, 180, 60, false, mPaint);//绘制弧线
        }
        if (DEFAULT_VIEW_TYPE == PATH_VIEW) {
            canvas.drawPath(mPath, mPaint);
        }
        if (DEFAULT_VIEW_TYPE == HISTOGRAM_VIEW) {
            canvas.drawPath(histogram, mPaint);

            int textCenter;
            for (int i = 0; i < 7; i++) {
                textCenter = (mLeft + mRight) / 2 - mLeftString[i];
                if (i == 0){
                    mTop = mBottom - 3;
                }
                if (i == 1) {
                    mTop = mBottom - 10;
                }
                if (i == 2) {
                    mTop = mBottom - 8;
                }
                if (i == 3) {
                    mTop = mBottom - 150;
                }
                if (i == 4) {
                    mTop = mBottom - 300;
                }
                if (i == 5) {
                    mTop = mBottom - 350;
                }
                if (i == 6) {
                    mTop = mBottom - 130;
                }
                canvas.drawText(mStrings[i], textCenter, mBottomText, mPaintRect);
                canvas.drawRect(mLeft, mTop, mRight, mBottom, mPaintRect);

                mLeft += 85;
                mRight += 85;
            }

            mLeft = 58;
            mRight = mLeft + 75;
        }
        if (DEFAULT_VIEW_TYPE == PIE_CHART_VIEW) {

        }
        if (DEFAULT_VIEW_TYPE == COLOR_ALPH_VIEW) {

        }
    }

    private void initPaint(){
        mPaint = new Paint();
        if (DEFAULT_VIEW_TYPE == COLOR_VIEW) {
            mPaint.setColor(Color.YELLOW);
        }
        if (DEFAULT_VIEW_TYPE == CIRCLE_VIEW) {
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//实心圆
            mPaint.setColor(Color.YELLOW);
        }
        if (DEFAULT_VIEW_TYPE == RECT_VIEW) {
            mPaint.setStyle(Paint.Style.FILL);//填充模式
            mPaint.setColor(Color.YELLOW);
        }
        if (DEFAULT_VIEW_TYPE == POINT_VIEW) {
            mPaint.setStyle(Paint.Style.STROKE);//画线模式
            mPaint.setStrokeWidth(250);//画线模式的线条宽度
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setColor(Color.YELLOW);
        }
        if (DEFAULT_VIEW_TYPE == OVAL_VIEW) {
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setColor(Color.YELLOW);
        }
        if (DEFAULT_VIEW_TYPE == LINE_VIEW) {
            mPaint.setStrokeWidth(20);
            mPaint.setColor(Color.YELLOW);
        }
        if (DEFAULT_VIEW_TYPE == ROUND_RECT_VIEW) {
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setColor(Color.YELLOW);
        }
        if (DEFAULT_VIEW_TYPE == ARC_VIEW) {
            mPaint.setStyle(Paint.Style.FILL); // 填充模式
            mPaint.setColor(Color.YELLOW);
        }
        if (DEFAULT_VIEW_TYPE == PATH_VIEW) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(3);
            mPaint.setColor(Color.YELLOW);
        }
        if (DEFAULT_VIEW_TYPE == HISTOGRAM_VIEW) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(2);
            mPaint.setColor(Color.argb(200, 7, 237, 237));

            mPaintRect.setColor(Color.argb(200, 84, 237, 118));
            mPaintRect.setTextSize(15);
            for (int i = 0; i < mStrings.length; i++) {
                mPaintRect.getTextBounds(mStrings[i], 0, mStrings[i].length(), mRect);
                mLeftString[i] = mRect.width() / 2;
            }
        }
        if (DEFAULT_VIEW_TYPE == PIE_CHART_VIEW) {

        }
        if (DEFAULT_VIEW_TYPE == COLOR_ALPH_VIEW) {

        }
    }
    /**
     * 对外提供入口设置view的类型，根据类型来判断绘制不同的形状
     * @param type
     */
    public void setViewType(int type) {
        this.DEFAULT_VIEW_TYPE = type;
    }
}
