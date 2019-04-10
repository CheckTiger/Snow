package cn.sxh.snowfox.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.socks.library.KLog;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.base.BaseFragment;

/**
 * Created by snow on 2017/8/5.
 */

public class SurpriseFragment extends BaseFragment {
    private MapView mMapView;
    private MyLocationStyle myLocationStyle;
    private AMap mAmap;
    private AMapLocationClient aMapLocationClient;
    private AMapLocationClientOption aMapLocationClientOption = null;
    private AMapLocationListener locationListener;
    @Override
    protected int getContentView() {
        return R.layout.nav_header_news;
    }

    @Override
    protected void initUI(View view) {
        mMapView = view.findViewById(R.id.alibaba_map);
        myLocationStyle = new MyLocationStyle();
        mAmap = mMapView.getMap();
        aMapLocationClient = new AMapLocationClient(getContext());
        aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setInterval(2000);
        locationListener = aMapLocation -> {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    aMapLocation.getLocationType();
                    aMapLocation.getLatitude();
                    aMapLocation.getLongitude();
                    aMapLocation.getAccuracy();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date data = new Date(aMapLocation.getTime());
                    String time  = df.format(data);
                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    aMapLocation.getCountry();//国家信息
                    aMapLocation.getProvince();//省信息
                    aMapLocation.getCity();//城市信息
                    aMapLocation.getDistrict();//城区信息
                    aMapLocation.getStreet();//街道信息
                    aMapLocation.getStreetNum();//街道门牌号信息
                    aMapLocation.getCityCode();//城市编码
                    aMapLocation.getAdCode();//地区编码
                    aMapLocation.getAoiName();//获取当前定位点的AOI信息
                    KLog.e("城市定位信息------>>>>>sxh", aMapLocation.getLocationType() + "---" + aMapLocation.getLatitude() +
                            "---" + aMapLocation.getLongitude() + "---" + aMapLocation.getAccuracy() + "---" + time+"--"+
                            aMapLocation.getAddress()+ "---" + aMapLocation.getCountry() + "---" + aMapLocation.getCity()+
                            "---" + aMapLocation.getDistrict() + "---" + aMapLocation.getStreet()+"--"+aMapLocation.getCityCode());
                }
            }
        };
        aMapLocationClient.setLocationListener(locationListener);
        aMapLocationClient.startLocation();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        initLocation();
    }

    private void initLocation() {
        myLocationStyle.interval(2000);
        mAmap.setMyLocationStyle(myLocationStyle);
        myLocationStyle.getMyLocationIcon();
        mAmap.setMyLocationEnabled(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}
