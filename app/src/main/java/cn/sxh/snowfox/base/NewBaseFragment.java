package cn.sxh.snowfox.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import cn.sxh.snowfox.AppContext;
import cn.sxh.snowfox.UI.presenter.base.BasePresenter;
import cn.sxh.snowfox.di.component.DaggerFragmentComponent;
import cn.sxh.snowfox.di.component.FragmentComponent;
import cn.sxh.snowfox.di.module.FragmentModule;
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
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((AppContext) getActivity().getApplication()).getmApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
        initInjector();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mFragmentView);
            initViews(mFragmentView);
        }
        return mFragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        RefWatcher refWatcher = AppContext.getRefWatcher(getActivity());
        refWatcher.watch(this);
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        mSubscription.unsubscribe();
    }
}
