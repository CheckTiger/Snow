package cn.sxh.snowfox.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import cn.sxh.snowfox.UI.presenter.base.BasePresenter;
import cn.sxh.snowfox.di.component.FragmentComponent;
import rx.Subscription;

/**
 * @author by snow on 2017/8/14
 * @time 00:54
 * @mail snowtigersong@gmail.com
 */

public abstract class NewBaseFragment<T extends BasePresenter> extends Fragment {

    protected FragmentComponent mFragmentComponent;
    public FragmentComponent getFragmentComponent(){return mFragmentComponent;}

    protected T mPresenter;

    private View mFragmentView;

    public abstract void initInjector();

    public abstract void initViews(View view);

    public abstract int getLayoutId();

    protected Subscription mSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
