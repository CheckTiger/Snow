package cn.sxh.snowfox.view.DefinedAndCollectView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/12/26 0026.
 */

public class NewViewPager extends ViewPager {
    public NewViewPager(Context context) {
        super(context);
    }

    public NewViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean dispatchHoverEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
