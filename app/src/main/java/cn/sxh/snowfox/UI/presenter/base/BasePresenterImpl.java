package cn.sxh.snowfox.UI.presenter.base;

import android.support.annotation.NonNull;

import cn.sxh.snowfox.callback.RequestCallBack;
import cn.sxh.snowfox.view.base.BaseView;
import rx.Subscription;

/**
 * @author by snow on 2017/8/14
 * @time 00:09
 * @mail snowtigersong@gmail.com
 */

public class BasePresenterImpl<T extends BaseView, E> implements BasePresenter, RequestCallBack<E> {
    protected T mView;
    protected Subscription mSubscription;
    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(@NonNull BaseView view) {
        mView = (T) view;
    }

    @Override
    public void onDestroy() {
        mSubscription.unsubscribe();
    }

    @Override
    public void beforeRequest() {
        mView.showProgress();
    }

    @Override
    public void success(E data) {
        mView.hideProgress();
    }

    @Override
    public void onError(String errorMsg) {
        mView.hideProgress();
        mView.showMsg(errorMsg);
    }
}
