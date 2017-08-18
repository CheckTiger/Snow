package cn.sxh.snowfox.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.socks.library.KLog;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import cn.sxh.snowfox.AppContext;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.UI.activity.NewsActivity;
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

    private static final String TAG = NewBaseFragment.class.getSimpleName();
    protected FragmentComponent mFragmentComponent;
    public FragmentComponent getFragmentComponent(){return mFragmentComponent;}

    protected T mPresenter;

    private View mFragmentView;

    public abstract void initInjector();

    public abstract void initViews(View view);

    public abstract int getLayoutId();

    protected Subscription mSubscription;
    protected NavigationView mBaseNavView;
    private DrawerLayout mDrawerLayout;
    private Class mClass;
    protected boolean mIsHasNavigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.e(TAG,"onCreate: --------->>>>>"+"开始执行");
        KLog.e(TAG,getClass().getSimpleName());
        initFragmentComponent();
    }

    /**
     * 此处采用的是dagger2.5的注入式框架，对fragment进行注入
     */
    private void initFragmentComponent() {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((AppContext) getActivity().getApplication()).getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: -------------->>>>>>>" +"开始执行");
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mFragmentView);
            initInjector();
            initToolBar(mFragmentView);
            initViews(mFragmentView);
//            if (mIsHasNavigationView) {
//            }
            initDrawerLayout(mFragmentView);
        }
        return mFragmentView;
    }

    private void initToolBar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    private void initDrawerLayout(View view){
        mDrawerLayout = view.findViewById(R.id.drawer_layout);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        NavigationView navView = view.findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(),
                mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /**
         * lambadas的使用方法如下.
         * 第一步：你需要在app的build.gradle的
         * android{compileOptions {sourceCompatibility JavaVersion.VERSION_1_8targetCompatibility JavaVersion.VERSION_1_8}}添加此java1.8支持
         * 第二步：还是在此配置文件内添加如此插件依赖 在配置文件的最顶端
         * apply plugin: 'me.tatarka.retrolambda'
         * apply plugin: 'com.neenbedankt.android-apt'
         * 第三步：需要在你的Project工程配置文件下面添加如此路径
         * classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
         * classpath 'me.tatarka:gradle-retrolambda:3.2.4'
         * 这样你还是不明白的话 我建议你就不要用lambadas，
         * 直接用最普通的内部类监听事件就行，不影响工程的使用，这里只是为了简化代码的整洁行
         */
        if (navView != null) {
            navView.setNavigationItemSelectedListener(item -> go(item));//此处使用lambadas，此库仅支持java1.8
            mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    if (mClass != null) {
                        Intent intent = new Intent(getActivity(), mClass);
                        startActivity(intent);
                        getActivity().overridePendingTransition(0, 0);
                        mClass = null;
                    }
                }
            });
        }
    }

    private boolean go(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_news:
                Toast.makeText(getActivity(), "新闻", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_photo:
                Toast.makeText(getActivity(), "图片", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_video:
                Toast.makeText(getActivity(), "视频", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_night_mode:
                Toast.makeText(getActivity(), "夜间模式", Toast.LENGTH_SHORT).show();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
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
