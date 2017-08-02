package cn.sxh.snowfox.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.sxh.snowfox.R;

/**
 * Created by snow on 2017/8/2.
 */

public class TabHost {
    private Context context;
    private LinearLayout rootView;
    private List<Tab> tabList = new ArrayList<>();
    private ViewPager contentViewPager;

    public TabHost(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {
        rootView = new LinearLayout(context);
        rootView.setOrientation(LinearLayout.HORIZONTAL);
//        rootView.setId(R.id.linearlayout_tab);

        RelativeLayout.LayoutParams rootViewLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootViewLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rootView.setLayoutParams(rootViewLp);
    }

    private void addTab(Tab tab) {
        if (tab == null) {
            return;
        }
        tabList.add(tab);
    }
}
