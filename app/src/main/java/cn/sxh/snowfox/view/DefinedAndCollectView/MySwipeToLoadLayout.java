package cn.sxh.snowfox.view.DefinedAndCollectView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

/**
 * @author by snow on 2017/9/21
 * @time 22:20
 * @mail snowtigersong@gmail.com
 */

public class MySwipeToLoadLayout extends SwipeToLoadLayout {
    public MySwipeToLoadLayout(Context context) {
        super(context);
    }

    public MySwipeToLoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySwipeToLoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }
}
