package cn.sxh.snowfox.UI.fragment;

import android.view.View;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.socks.library.KLog;

import butterknife.BindView;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.base.BaseFragment;
import cn.sxh.snowfox.view.NavigationTabStrip;

/**
 * Created by snow on 2017/8/5.
 */

public class TechnologyFragment extends BaseFragment {
    private static final String TAG = TechnologyFragment.class.getSimpleName();
    @BindView(R.id.navigation_tab)
    NavigationTabStrip navigationTab;
    @BindView(R.id.swipe_target)
    ListView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    private int mIndex = 0;

    @Override
    protected int getContentView() {
        return R.layout.technology_fragment_view;
    }

    @Override
    protected void initUI(View view) {
        navigationTab.setTabIndex(0, true);
        navigationTab.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                mIndex = index;
            }

            @Override
            public void onEndTabSelected(String title, int index) {
                changeContent(mIndex);
            }
        });
    }

    private void changeContent(int mIndex) {
        switch (mIndex) {
            case 0:
                KLog.e(TAG, "NavigationTabStrip选择onStartTabSelected下标为-------->>>" + mIndex);
                break;
            case 1:
                KLog.e(TAG, "NavigationTabStrip选择onStartTabSelected下标为-------->>>" + mIndex);
                break;
            case 2:
                KLog.e(TAG, "NavigationTabStrip选择onStartTabSelected下标为-------->>>" + mIndex);
                break;
            case 3:
                KLog.e(TAG, "NavigationTabStrip选择onStartTabSelected下标为-------->>>" + mIndex);
                break;
            case 4:
                KLog.e(TAG, "NavigationTabStrip选择onStartTabSelected下标为-------->>>" + mIndex);
                break;
        }

    }

    @Override
    protected void initData() {

    }

}
