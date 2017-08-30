package cn.sxh.snowfox.UI.fragment;

import android.app.Activity;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.sxh.snowfox.API.ApiRetrofit;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.UI.fragment.home.Banner;
import cn.sxh.snowfox.UI.fragment.home.ContentItem;
import cn.sxh.snowfox.base.BaseFragment;
import cn.sxh.snowfox.bean.JuHeBannerToutiaoEntity;
import cn.sxh.snowfox.view.NewsRecyclerView;
import cn.sxh.snowfox.view.multitype.Item;
import cn.sxh.snowfox.view.multitype.MultiTypeAdapter;
import cn.sxh.snowfox.view.multitype.MultiTypeAsserts;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by snow on 2017/8/5.
 */

public class CategoryFragment extends BaseFragment implements OnRefreshListener,OnLoadMoreListener{
    private static final String TAG = CategoryFragment.class.getSimpleName();
    @BindView(R.id.swipe_target)
    NewsRecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    private JuHeBannerToutiaoEntity mBnnerEntity;
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
        ApiRetrofit.getInstance().getBannerByPost(key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mBannerSub);
        setListeners();
    }

    private void setListeners() {
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
    }

    private void addDataToMultiType() {
        items.add(new Banner(getActivity(), mBnnerEntity));
        items.add(new ContentItem(getActivity(), mBnnerEntity));
        adapter = new MultiTypeAdapter(items);
        adapter.applyGlobalMultiTypePool();
        MultiTypeAsserts.assertAllRegistered(adapter,items);
        swipeTarget.setAdapter(adapter);
    }

    /**
     * 获取banner数据
     */
    private final Subscriber<JuHeBannerToutiaoEntity> mBannerSub = new Subscriber<JuHeBannerToutiaoEntity>() {
        @Override
        public void onCompleted() {
            KLog.e(TAG,"上拉加载下拉刷新------->>>>>>"+"结束了");
        }

        @Override
        public void onError(Throwable e) {
            KLog.e(TAG,"请求数据失败------->>>>>>"+e.getMessage());
            swipeToLoadLayout.setRefreshing(false);
            swipeToLoadLayout.setLoadingMore(false);
        }

        @Override
        public void onNext(JuHeBannerToutiaoEntity bannerEntity) {
            mBnnerEntity = bannerEntity;
            addDataToMultiType();
        }
    };

    @Override
    public void onLoadMore() {
        ApiRetrofit.getInstance().getBannerByPost(key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mBannerSub);
        swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onRefresh() {
        ApiRetrofit.getInstance().getBannerByPost(key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mBannerSub);
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
    }
}
