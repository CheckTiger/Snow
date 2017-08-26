package cn.sxh.snowfox.UI.fragment;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.sxh.snowfox.API.ApiRetrofit;
import cn.sxh.snowfox.API.HttpResult;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.UI.fragment.home.Banner;
import cn.sxh.snowfox.base.BaseFragment;
import cn.sxh.snowfox.bean.BannerEntity;
import cn.sxh.snowfox.bean.JuHeBannerToutiaoEntity;
import cn.sxh.snowfox.view.multitype.Item;
import cn.sxh.snowfox.view.multitype.MultiTypeAdapter;
import cn.sxh.snowfox.view.multitype.MultiTypeAsserts;
import rx.Observable;
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

//    private JuHeBannerToutiaoEntity mBnnerEntity;
    private BannerEntity mBnnerEntity;
    private List<Item> items = new ArrayList<>();
    private MultiTypeAdapter adapter;
    private Activity activity;

    private String key = "3988f8911b515e58d49ac192823d9960";
    @Override
    protected int getContentView() {
        return R.layout.category_fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;

    }

    @Override
    protected void initUI(View view) {
        swipeTarget = view.findViewById(R.id.swipe_target);
    }

    @Override
    protected void initData() {
//        ApiRetrofit.getInstance().getBannerByPost(key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(mBannerSub);
        ApiRetrofit.getInstance().getBannerByQuNaWan().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mBannerSub);
    }

    private void addDataToMultiType() {
        items.add(new Banner(getActivity(), mBnnerEntity));
        adapter = new MultiTypeAdapter(items);
        adapter.applyGlobalMultiTypePool();
        MultiTypeAsserts.assertAllRegistered(adapter,items);
        swipeTarget.setAdapter(adapter);
    }

    /**
     * 获取banner数据
     */
    private final Subscriber<BannerEntity> mBannerSub = new Subscriber<BannerEntity>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG,"请求数据失败------->>>>>>"+e.getMessage());
            Log.e(TAG,"请求数据失败------->>>>>>"+e.getLocalizedMessage());
        }

        @Override
        public void onNext(BannerEntity bannerEntity) {
            mBnnerEntity = bannerEntity;
            Log.e(TAG,"请求数据成功------->>>>>>"+bannerEntity.getReason());
            Log.e(TAG,"请求数据成功------->>>>>>"+bannerEntity.getResult().size());
            addDataToMultiType();
        }
    };
}
