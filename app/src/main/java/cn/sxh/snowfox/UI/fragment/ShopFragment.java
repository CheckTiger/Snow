package cn.sxh.snowfox.UI.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.adapter.TabPagerAdapter;
import cn.sxh.snowfox.base.NewBaseFragment;

/**
 * Created by snow on 2017/8/5.
 */

public class ShopFragment extends NewBaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private String[] titles = {"首页", "资讯", "视频精选"};
    private List<Fragment> list;
    private TabPagerAdapter pagerAdapter;
    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        list = new ArrayList<>();
        list.add(new ArcGisFragment());
        list.add(new ArcGisFragment());
        list.add(new ArcGisFragment());
        pagerAdapter = new TabPagerAdapter(getActivity().getSupportFragmentManager(), list, titles);
        viewPager.setAdapter(pagerAdapter);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news;
    }
}
