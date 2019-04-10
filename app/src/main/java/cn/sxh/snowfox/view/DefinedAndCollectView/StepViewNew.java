package cn.sxh.snowfox.view.DefinedAndCollectView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.sxh.snowfox.R;

/**
 * Created by Administrator on 2017/12/26 0026.
 */

public class StepViewNew extends View {
    private static int DefaultTextSize = 35;//默认字体大小
    private static int HorizontalStepView = 0;//水平方向
    private static int VerticalStepViewForward = 1;//垂直方向从前往后
    private static int VerticalStepViewReverse = 2;//垂直方向从后往前
    private static int DefaultTypeView = 0;//默认为水平方向
    private static int DEFAULT_DISTANCE = 20;//默认为水平方向间距

    private Paint mBitmapPaint;//图片的画笔
    private Paint mTextPaint;//文字的画笔
    private int DEFAUT_COLOR;
    private String [] title;
    public StepViewNew(Context context) {
        this(context,null);
    }

    public StepViewNew(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    /*在构造函数方法中初始化画笔*/
    public StepViewNew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StepViewNew);
        DefaultTypeView = a.getInteger(R.styleable.StepViewNew_viewType, HorizontalStepView);
        DefaultTextSize = a.getDimensionPixelSize(R.styleable.StepViewNew_default_textSize, DefaultTextSize);
        VerticalStepViewForward = a.getDimensionPixelOffset(R.styleable.StepViewNew_VerticalStepViewForward, VerticalStepViewForward);
        VerticalStepViewForward = a.getDimensionPixelOffset(R.styleable.StepViewNew_VerticalStepViewReverse, VerticalStepViewReverse);
        DEFAULT_DISTANCE = a.getInteger(R.styleable.StepViewNew_default_distance, DEFAULT_DISTANCE);
        DEFAUT_COLOR = a.getColor(R.styleable.StepViewNew_paintColor, DEFAULT_DISTANCE);
        a.recycle();
        this.setBackgroundColor(getResources().getColor(R.color.color_type_step_view));
        mBitmapPaint = new Paint(Paint.DEV_KERN_TEXT_FLAG);
        mBitmapPaint.setColor(DEFAUT_COLOR);
        mTextPaint = new Paint(Paint.DEV_KERN_TEXT_FLAG);
        mTextPaint.setTextSize(DefaultTextSize);
        mTextPaint.setColor(Color.parseColor("#E91E63"));
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int width = 50;
        for (int i = 0; i < title.length; i++) {
            width = width + 150 ;
            canvas.drawText(title[i],width,300,mTextPaint);
        }
//        canvas.drawText("宋学虎",200,300,mTextPaint);
//        canvas.drawText("宋学虎",350,300,mTextPaint);
//        canvas.drawText("宋学虎",500,300,mTextPaint);
//        canvas.drawText("宋学虎",650,300,mTextPaint);
    }

    public void setDefaultTypeView(int defaultTypeView) {
        DefaultTypeView = defaultTypeView;
    }
    public void  setTitle(String[] titles){
        this.title = titles;}
}
