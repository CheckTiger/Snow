package cn.sxh.snowfox.UI.presenter.base;

import android.support.annotation.NonNull;

import cn.sxh.snowfox.view.base.BaseView;

/**
 * @author by snow on 2017/8/14
 * @time 00:07
 * @mail snowtigersong@gmail.com
 */

public interface BasePresenter {

    void onCreate();

    void attachView(@NonNull BaseView view);

    void onDestroy();
}
