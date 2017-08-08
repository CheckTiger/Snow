package cn.sxh.snowfox.UI.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.List;

import butterknife.BindView;
import cn.sxh.snowfox.API.ApiRetrofit;
import cn.sxh.snowfox.API.HttpResult;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.base.BaseFragment;
import cn.sxh.snowfox.bean.BannerEntity;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by snow on 2017/8/5.
 */

public class CategoryFragment extends BaseFragment {
    private static final String TAG = CategoryFragment.class.getSimpleName();
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    @Override
    protected int getContentView() {
        return R.layout.category_fragment;
    }

    @Override
    protected void initUI(View view) {
        ApiRetrofit.getInstance().getBanner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BannerEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"请求数据失败------->>>>>>"+e.getMessage());
                        Log.e(TAG,"请求数据失败----1--->>>>>>"+e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(BannerEntity bannerEntity) {
                        String result = bannerEntity.getReason();
                        Log.e(TAG,"请求数据成功------->>>>>>"+result);
                        Log.e(TAG,"请求数据成功------->>>>>>"+bannerEntity.getTime());
                        Log.e(TAG,"请求数据成功------->>>>>>"+bannerEntity.getResult().size());
                    }
                });
    }

    @Override
    protected void initData() {

    }
}
