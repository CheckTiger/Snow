package cn.sxh.snowfox.UI.fragment;

import android.view.View;

import cn.sxh.snowfox.API.ApiRetrofit;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.base.BaseFragment;
import cn.sxh.snowfox.bean.BannerEntity;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by snow on 2017/8/5.
 */

public class CategoryFragment extends BaseFragment {
    @Override
    protected int getContentView() {
        return R.layout.category_fragment;
    }

    @Override
    protected void initUI(View view) {
        Observable<BannerEntity> bannerData = ApiRetrofit.getInstance().getBanner();
        Subscription subscription = Observable.create(new Observable.OnSubscribe<BannerEntity>() {
            @Override
            public void call(Subscriber<? super BannerEntity> subscriber) {

            }
        }).subscribe();
        bannerData.subscribe();
    }

    @Override
    protected void initData() {

    }
}
