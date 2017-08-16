package cn.sxh.snowfox.UI.presenter.impl;

import javax.inject.Inject;

import cn.sxh.snowfox.UI.interactor.CategoryFragmentInteractor;
import cn.sxh.snowfox.UI.interactor.CategoryFragmentInteractorImpl;
import cn.sxh.snowfox.UI.presenter.CategoryPresenter;
import cn.sxh.snowfox.UI.presenter.base.BasePresenterImpl;
import cn.sxh.snowfox.bean.BannerEntity;
import cn.sxh.snowfox.view.CategoryView;

/**
 * @author by snow on 2017/8/14
 * @time 00:27
 * @mail snowtigersong@gmail.com
 */

public class CategoryPresenterImpl extends BasePresenterImpl<CategoryView,BannerEntity>
        implements CategoryPresenter{

    private CategoryFragmentInteractor<BannerEntity> mCategoryInteractor;

    @Inject
    public CategoryPresenterImpl (CategoryFragmentInteractorImpl fragmentInteractor){mCategoryInteractor = fragmentInteractor;}

    @Override
    public void onCreate() {
        super.onCreate();
        loadBanner();
    }

    private void loadBanner() {
        mSubscription = mCategoryInteractor.loadBannerInfo(this);
    }

    @Override
    public void success(BannerEntity data) {
        super.success(data);
        mView.initBanner(data);
    }

    @Override
    public void onBannerChanged() {

    }
}
