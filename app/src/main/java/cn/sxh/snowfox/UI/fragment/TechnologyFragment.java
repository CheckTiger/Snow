package cn.sxh.snowfox.UI.fragment;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.UI.presenter.impl.CategoryPresenterImpl;
import cn.sxh.snowfox.base.NewBaseFragment;
import cn.sxh.snowfox.bean.BannerEntity;
import cn.sxh.snowfox.event.BannerChangeEvent;
import cn.sxh.snowfox.utils.RxBus;
import cn.sxh.snowfox.view.fragmentView.CategoryView;
import rx.functions.Action1;

/**
 * Created by snow on 2017/8/5.
 */

public class TechnologyFragment extends NewBaseFragment implements CategoryView{
    private static final String TAG = TechnologyFragment.class.getSimpleName();

    @Inject
    CategoryPresenterImpl mCategoryPresenter;
    @Inject
    Activity mActivity;
    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();
    }

    private void initPresenter() {
        mPresenter = mCategoryPresenter;
        mPresenter.attachView(this);
        mPresenter.onCreate();
        mSubscription = RxBus.getInstance().toObservable(BannerChangeEvent.class)
                .subscribe(new Action1<BannerChangeEvent>() {
                    @Override
                    public void call(BannerChangeEvent bannerChangeEvent) {
                        Log.e(TAG, "initPresenter------" + "初始化开始");
                        mCategoryPresenter.onBannerChanged();
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg(String message) {
        Log.e(TAG, "dagger模式实验成功------" + message);
    }

    @Override
    public void initBanner(BannerEntity bannerEntity) {
        Log.e(TAG, "dagger模式实验成功------" + bannerEntity.getReason());
    }
}
