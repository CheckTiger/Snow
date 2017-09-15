package cn.sxh.snowfox.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by snow on 2017/8/3.
 */

public abstract class BaseTabAdapter {
    public abstract int getCount();
    public abstract int hasMsgIndex();

    public abstract String[] getTextArray();
    public abstract int[] getIconImageArray();

    public abstract int[] getSelectedIconImageArray();

    public abstract Fragment[] getFragmentArray();
    public abstract FragmentManager getFragmentManager();
}
