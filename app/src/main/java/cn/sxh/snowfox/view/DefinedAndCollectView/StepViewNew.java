package cn.sxh.snowfox.view.DefinedAndCollectView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.sxh.snowfox.R;

/**
 * Created by Administrator on 2017/12/26 0026.
 */

public class StepViewNew extends View {
    private static int DefaultTextSize = 15;//默认字体大小
    private static int HorizontalStepView = 0;//水平方向
    private static int VerticalStepViewForward = 1;//垂直方向从前往后
    private static int VerticalStepViewReverse = 2;//垂直方向从后往前
    private static int DefaultTypeView = 0;//默认为水平方向
    public StepViewNew(Context context) {
        this(context,null);
    }

    public StepViewNew(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StepViewNew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StepViewNew);
        DefaultTypeView = a.getInteger(R.styleable.StepViewNew_viewType, HorizontalStepView);
        this.setBackgroundColor(getResources().getColor(R.color.color_type_step_view));
    }

    public void setDefaultTypeView(int defaultTypeView) {
        DefaultTypeView = defaultTypeView;
    }
}
