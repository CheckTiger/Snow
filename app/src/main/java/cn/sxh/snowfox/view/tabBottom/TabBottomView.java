package cn.sxh.snowfox.view.tabBottom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.adapter.BaseAdapter;
import cn.sxh.snowfox.adapter.TabViewPagerAdapter;
import cn.sxh.snowfox.utils.DisplayUtil;

/**
 * Created by snow on 2017/8/2.
 */

public class TabBottomView extends RelativeLayout {

    private TabHost tabHost;
    private ViewPager contentViewPager;

    private int textSize;
    private int textColor;
    private int selectedTextColor;
    private int drawablePadding;

    /**
     * 图标属性
     */
    private int iconHeight;
    private int iconWidth;

    private int divideLineColor;
    private int divideLineHeight;

    private Tab.OnTabSelectedListener onTabSelectedListener;


    public TabBottomView(Context context) {
        super(context);
    }

    public TabBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TabBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initStyle(context, attrs);
        initTabHost(context);
        initDivideLine(context);
        initViewPager(context);
        tabHost.setContentViewPager(contentViewPager);
    }

    private void initStyle(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabContainerViewStyle);
        textColor = typedArray.getColor(R.styleable.TabContainerViewStyle_tabTextColor, Color.BLACK);
        selectedTextColor = typedArray.getColor(R.styleable.TabContainerViewStyle_selectedTextColor, Color.RED);
        textSize = typedArray.getDimensionPixelSize(R.styleable.TabContainerViewStyle_tabTextSize, DisplayUtil.dip2px(context,14));
        drawablePadding =typedArray.getDimensionPixelSize(R.styleable.TabContainerViewStyle_drawablePadding,DisplayUtil.dip2px(context,4));
        iconHeight = typedArray.getDimensionPixelSize(R.styleable.TabContainerViewStyle_iconHeight,0);
        iconWidth = typedArray.getDimensionPixelSize(R.styleable.TabContainerViewStyle_iconWidth,0);
        divideLineColor = typedArray.getColor(R.styleable.TabContainerViewStyle_divideLineColor, Color.BLACK);
        divideLineHeight = typedArray.getDimensionPixelSize(R.styleable.TabContainerViewStyle_divideLineHeight,DisplayUtil.dip2px(context,1));

        typedArray.recycle();
    }

    private void initTabHost(Context context) {
        tabHost = new TabHost(context);
        addView(tabHost.getRootView());
    }

    private void initDivideLine(Context context) {
        View divideLine = new View(context);
        divideLine.setId(R.id.divide_tab);
        divideLine.setBackgroundColor(divideLineColor);
        LayoutParams lineLp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, divideLineHeight);
        lineLp.addRule(RelativeLayout.ABOVE, R.id.linearlayout_tab);
        divideLine.setLayoutParams(lineLp);
        addView(divideLine);
    }

    private void initViewPager(Context context) {
        contentViewPager = new ViewPager(context);
        LayoutParams contentVpLp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentVpLp.addRule(RelativeLayout.ABOVE, R.id.divide_tab);
        contentViewPager.setLayoutParams(contentVpLp);
        contentViewPager.setId(R.id.viewpager_tab);

        contentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabHost.onChangeTabHostStatus(position);
                Tab selectedTab = tabHost.getTabForIndex(position);
                if (onTabSelectedListener != null && selectedTab != null) onTabSelectedListener.onTabSelected(selectedTab);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        addView(contentViewPager);
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        setAdapter(baseAdapter, 0);
    }

    public void setAdapter(BaseAdapter baseAdapter, int index) {
        if (baseAdapter == null) return;
        tabHost.addTabs(baseAdapter, textSize, textColor, selectedTextColor,drawablePadding,iconWidth,iconHeight);

        contentViewPager.setAdapter(new TabViewPagerAdapter(baseAdapter.getFragmentManager(), baseAdapter.getFragmentArray()));

        setCurrentItem(index);
    }

    public void setCurrentItem(int index) {
        tabHost.onChangeTabHostStatus(index);
    }

    public void setOnTabSelectedListener(Tab.OnTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }
}
