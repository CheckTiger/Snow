package cn.sxh.snowfox.UI.fragment;

import android.view.View;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.base.NewBaseFragment;

/**
 * @package-name: cn.sxh.snowfox.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2018/2/6 0006 : 15 :30
 * @project-name: Snow
 */

public class ArcGisFragment extends NewBaseFragment {
    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.arcgis_fragment_layout;
    }
}
