package cn.sxh.snowfox.view.swiperefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreFooterLayout;

import cn.sxh.snowfox.R;

/**
 * Created by snow on 2017/8/7.
 * 描述：加载更多底部View
 * 创建人：snow
 * 创建时间：2017/8/7
 */

public class ClassicLoadMoreFooterView extends SwipeLoadMoreFooterLayout {
    private TextView tvLoadMore;
    private ImageView isSuccess;
    private ProgressBar progressBar;
    private int mFooterHeight;
    public ClassicLoadMoreFooterView(Context context) {
        this(context,null);
    }

    public ClassicLoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClassicLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFooterHeight = getResources().getDimensionPixelOffset(R.dimen.load_more_footer_height_classic);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvLoadMore = findViewById(R.id.tvLoadMore);
        isSuccess = findViewById(R.id.ivSuccess);
        progressBar = findViewById(R.id.progressbar);
    }

    @Override
    public void onPrepare() {
        isSuccess.setVisibility(GONE);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            isSuccess.setVisibility(GONE);
            progressBar.setVisibility(GONE);
            if (-y >= mFooterHeight) {
                tvLoadMore.setText("松开加载更多数据");
            } else {
                tvLoadMore.setText("上拉加载更多");
            }
        }
    }

    @Override
    public void onLoadMore() {
        tvLoadMore.setText("加载更多");
        progressBar.setVisibility(GONE);
    }

    @Override
    public void onComplete() {
        progressBar.setVisibility(GONE);
        isSuccess.setVisibility(VISIBLE);
    }

    @Override
    public void onReset() {
        isSuccess.setVisibility(GONE);
    }
}
