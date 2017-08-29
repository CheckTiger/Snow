package cn.sxh.snowfox.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author by snow on 2017/8/30
 * @time 00:41
 * @mail snowtigersong@gmail.com
 */

public class NewsRecyclerView extends RecyclerView {
    public NewsRecyclerView(Context context) {
        super(context);
    }

    public NewsRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
