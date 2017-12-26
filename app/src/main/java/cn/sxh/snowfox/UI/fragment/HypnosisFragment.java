package cn.sxh.snowfox.UI.fragment;

import android.view.View;
import android.widget.ListView;

import com.socks.library.KLog;

import cn.sxh.snowfox.API.ApiRetrofit;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.adapter.NewsAdapterNew;
import cn.sxh.snowfox.base.BaseFragment;
import cn.sxh.snowfox.bean.ThsNewsBean;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/26 0026.
 */

public class HypnosisFragment extends BaseFragment {
    private ListView mListView;
    private ThsNewsBean mThsNewsBean;
    private String index = "1";
    private NewsAdapterNew mAdapterNew;
    @Override
    protected int getContentView() {
        return R.layout.hypnosis_fragment_item;
    }

    @Override
    protected void initUI(View view) {
        mListView = view.findViewById(R.id.listView);

    }

    @Override
    protected void initData() {
        ApiRetrofit.getThsInstance().getNewsList(index)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }


    private final Subscriber<ThsNewsBean> mSubscriber = new Subscriber<ThsNewsBean>() {
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
            mAdapterNew = new NewsAdapterNew(getContext(), mThsNewsBean);
            mListView.setAdapter(mAdapterNew);
        }
    };
}
