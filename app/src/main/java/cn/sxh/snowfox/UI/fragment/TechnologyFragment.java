package cn.sxh.snowfox.UI.fragment;

import android.util.Log;
import android.view.View;

import cn.sxh.snowfox.API.ApiRetrofit;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.base.BaseFragment;
import cn.sxh.snowfox.bean.BannerEntity;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by snow on 2017/8/5.
 */

public class TechnologyFragment extends BaseFragment {
    private static final String TAG = TechnologyFragment.class.getSimpleName();

    @Override
    protected int getContentView() {
        return R.layout.activity_news;
    }

    @Override
    protected void initUI(View view) {
        ApiRetrofit.getInstance().getBannerByQuNaWan()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BannerEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BannerEntity bannerEntity) {
                        Log.e(TAG,"请求数据成功------->>>>>>"+bannerEntity.getReason());
                    }
                });
    }

    @Override
    protected void initData() {

    }
}
