package cn.sxh.snowfox.UI.interactor;

import android.util.Log;

import javax.inject.Inject;

import cn.sxh.snowfox.API.ApiRetrofit;
import cn.sxh.snowfox.bean.BannerEntity;
import cn.sxh.snowfox.callback.RequestCallBack;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

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
        return Observable.create(new Observable.OnSubscribe<BannerEntity>(){

            @Override
            public void call(Subscriber<? super BannerEntity> subscriber) {
                Log.e(TAG, "call: ----------->>>>>>"+"开始请求数据" );
                subscriber.onNext(ApiRetrofit.getInstance().getBannerBNaWan());
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<BannerEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "call: ----------->>>>>>"+e.getMessage() );
                callBack.onError("网络繁忙");
            }

            @Override
            public void onNext(BannerEntity bannerEntity) {
                Log.e(TAG, "call: ----------->>>>>>"+bannerEntity.getReason() );
                callBack.success(bannerEntity);
            }
        });
    }
}
