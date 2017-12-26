package cn.sxh.snowfox.UI.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.socks.library.KLog;

import java.util.List;

import butterknife.BindView;
import cn.sxh.snowfox.API.ApiRetrofit;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.base.NewBaseFragment;
import cn.sxh.snowfox.bean.ThsNewsBean;
import cn.sxh.snowfox.view.fragmentView.MusicFragemntView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by snow on 2017/8/5.
 */

public class MusicFragment extends NewBaseFragment implements MusicFragemntView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ThsNewsBean mThsNewsBean;
    private String index = "1";

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        ApiRetrofit.getThsInstance().getNewsList(index)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ThsNewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e("sxh","请求数据失败------->>>>>>"+e.getMessage());
                    }

                    @Override
                    public void onNext(ThsNewsBean thsNewsBean) {
                        mThsNewsBean = thsNewsBean;
                        for (int i = 0; i < mThsNewsBean.getData().size(); i++) {
                            KLog.e("sxh","请求数据------->>>>>>"+mThsNewsBean.getData().get(i).getTitle());
                        }
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

    }

    @Override
    public void initViewPager(List<String> listTab) {
        Toast.makeText(getActivity(), "添加一些音乐文件", Toast.LENGTH_SHORT).show();
    }

}
