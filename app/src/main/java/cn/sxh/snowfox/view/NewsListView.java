package cn.sxh.snowfox.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * @author by snow on 2017/8/30
 * @time 00:37
 * @mail snowtigersong@gmail.com
 */

public class NewsListView extends ListView {
    public NewsListView(Context context) {
        super(context);
    }

    public NewsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
