package cn.sxh.snowfox.UI.interactor;

import android.util.Log;

import javax.inject.Inject;

import cn.sxh.snowfox.API.ApiRetrofit;
import cn.sxh.snowfox.UI.fragment.home.Banner;
import cn.sxh.snowfox.bean.BannerEntity;
import cn.sxh.snowfox.callback.RequestCallBack;
import cn.sxh.snowfox.view.multitype.MultiTypeAdapter;
import cn.sxh.snowfox.view.multitype.MultiTypeAsserts;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author by snow on 2017/8/14
 * @time 00:33
 * @mail snowtigersong@gmail.com
 */

public class CategoryFragmentInteractorImpl implements CategoryFragmentInteractor<BannerEntity> {
    private static final String TAG = CategoryFragmentInteractorImpl.class.getSimpleName();

    @Inject
    public CategoryFragmentInteractorImpl(){}
    @Override
    public Subscription loadBannerInfo(RequestCallBack<BannerEntity> callBack) {

        return ApiRetrofit.getInstance().getBannerByQuNaWan()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BannerEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"请求数据失败------->>>>>>"+e.getMessage());
                        Log.e(TAG,"请求数据成功------->>>>>>"+e.getLocalizedMessage());
                        callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(BannerEntity bannerEntity) {
                        Log.e(TAG,"请求数据成功------->>>>>>"+bannerEntity.getTime());
                        Log.e(TAG,"请求数据成功------->>>>>>"+bannerEntity.getCode());
                        callBack.success(bannerEntity);
                    }
                });
    }
}
