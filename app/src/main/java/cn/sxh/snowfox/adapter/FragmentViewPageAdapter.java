package cn.sxh.snowfox.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import cn.sxh.snowfox.R;

/**
 * Created by snow on 2017/8/3.
 */

public class FragmentViewPageAdapter extends BaseAdapter {

    private Fragment[] fragmentArray;
    private FragmentManager fragmentManager;
    private int hasMsgIndex = 0;
    private static final int DEFAULT_COUNT = 5;

    public void setHasMsgIndex(int hasMsgIndex) {
        this.hasMsgIndex = hasMsgIndex;
    }

    public FragmentViewPageAdapter(Fragment[] fragmentArray, FragmentManager fragmentManager) {
        this.fragmentArray = fragmentArray;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getCount() {
        return DEFAULT_COUNT;
    }

    @Override
    public int hasMsgIndex() {
        return hasMsgIndex;
    }

    @Override
    public String[] getTextArray() {
        return new String[]{"娱乐天地", "新闻小说", "惊喜", "购物车", "技术总结"};
    }

    @Override
    public int[] getIconImageArray() {
        return new int[] {R.mipmap.new_life_icon_grey, R.mipmap.new_find_icon_grey,
                R.mipmap.new_fininal_icon_grey, R.mipmap.new_shoppingcar_icon_grey,R.mipmap.new_myhome_icon_grey};
    }

    @Override
    public int[] getSelectedIconImageArray() {
        return  new int[] {R.mipmap.new_life_icon, R.mipmap.new_find_icon, R.mipmap.new_finial_icon,
                R.mipmap.new_shoppingcar_icon,R.mipmap.new_myhome_icon};
    }

    @Override
    public Fragment[] getFragmentArray() {
        return fragmentArray;
    }

    @Override
    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }
}
