package cn.sxh.snowfox.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.amap.api.maps.MapView;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.base.BaseFragment;

/**
 * Created by snow on 2017/8/5.
 */

public class SurpriseFragment extends BaseFragment {
    private MapView mMapView;
    @Override
    protected int getContentView() {
        return R.layout.nav_header_news;
    }

    @Override
    protected void initUI(View view) {
        mMapView = view.findViewById(R.id.alibaba_map);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }
}
