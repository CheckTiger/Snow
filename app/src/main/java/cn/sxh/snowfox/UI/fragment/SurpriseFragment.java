package cn.sxh.snowfox.UI.fragment;

import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.adapter.ImageAdapter;
import cn.sxh.snowfox.base.BaseFragment;

/**
 * Created by snow on 2017/8/5.
 */

public class SurpriseFragment extends BaseFragment implements AbsListView.OnScrollListener{
    private List<Integer> list = new ArrayList<>();
    private GridView mGridView;
    private ImageAdapter mAdapter;
    private boolean mIsGridViewIdle = false;
    @Override
    protected int getContentView() {
        return R.layout.nav_header_news;
    }

    @Override
    protected void initUI(View view) {
        mGridView = view.findViewById(R.id.gridView_imageLoader);
        mGridView.setOnScrollListener(this);
        mAdapter = new ImageAdapter(getContext(), list);
        mGridView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        list.add(R.drawable.pic002);
        list.add(R.drawable.pic003);
        list.add(R.drawable.pic004);
        list.add(R.drawable.pic006);
        list.add(R.drawable.pic007);
        list.add(R.drawable.pic008);
        list.add(R.drawable.pic009);
        list.add(R.drawable.pic010);
        list.add(R.drawable.pic011);
        list.add(R.drawable.pic012);
        list.add(R.drawable.pic014);
        list.add(R.drawable.pic015);
        list.add(R.drawable.pic016);
        list.add(R.drawable.pic017);
        list.add(R.drawable.pic018);
        list.add(R.drawable.pic019);
        list.add(R.drawable.pic020);
        list.add(R.drawable.pic021);
        list.add(R.drawable.pic023);
        list.add(R.drawable.pic024);
        list.add(R.drawable.pic025);
        list.add(R.drawable.pic026);
        list.add(R.drawable.pic027);
        list.add(R.drawable.pic028);
        list.add(R.drawable.pic029);
        list.add(R.drawable.pic030);
        list.add(R.drawable.pic031);
        list.add(R.drawable.pic032);
        list.add(R.drawable.pic033);
        list.add(R.drawable.pic034);
        list.add(R.drawable.pic035);
        list.add(R.drawable.pic036);
        list.add(R.drawable.pic037);
        list.add(R.drawable.pic038);
        list.add(R.drawable.pic040);
        list.add(R.drawable.pic041);
        list.add(R.drawable.pic042);
        list.add(R.drawable.pic043);
        list.add(R.drawable.pic044);
        list.add(R.drawable.pic045);
        list.add(R.drawable.pic046);
        list.add(R.drawable.pic047);
        list.add(R.drawable.pic048);
        list.add(R.drawable.pic049);
        list.add(R.drawable.pic050);
        list.add(R.drawable.pic051);
        list.add(R.drawable.pic052);
        list.add(R.drawable.pic053);
        list.add(R.drawable.pic054);
        list.add(R.drawable.pic055);
        list.add(R.drawable.pic056);
        list.add(R.drawable.pic057);
        list.add(R.drawable.pic058);
        list.add(R.drawable.pic059);
        list.add(R.drawable.pic060);
        list.add(R.drawable.pic061);
        list.add(R.drawable.pic062);
        list.add(R.drawable.pic063);
        list.add(R.drawable.pic064);
        list.add(R.drawable.pic065);
        list.add(R.drawable.pic066);
        list.add(R.drawable.pic067);
        list.add(R.drawable.pic068);
        list.add(R.drawable.pic069);
        list.add(R.drawable.pic070);
        list.add(R.drawable.pic071);
        list.add(R.drawable.pic072);
        list.add(R.drawable.pic073);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            mIsGridViewIdle = true;
            mAdapter.setGridViewIdle(mIsGridViewIdle);
        } else {
            mIsGridViewIdle = false;
            mAdapter.setGridViewIdle(mIsGridViewIdle);
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
