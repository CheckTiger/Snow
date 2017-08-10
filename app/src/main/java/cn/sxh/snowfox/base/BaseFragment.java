package cn.sxh.snowfox.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by snow on 2017/8/6.
 */

public abstract class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();
    protected View mRootView;//根布局
    protected Unbinder unbinder;


    private boolean isVisible;//控制dialog的显示与关闭

    /**
     * 获取布局
     * @return
     */
    protected abstract  int getContentView();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"-------------------onCreate----------->>>>>>");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isVisible = true;
        mRootView = inflater.inflate(getContentView(),container,false);
        unbinder = ButterKnife.bind(this, mRootView);
        Log.e(TAG,"-------------------onCreateView----------->>>>>>");
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        initData();
        Log.e(TAG,"-------------------onViewCreated----------->>>>>>");
    }

    protected abstract void initUI(View view);
    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
