package cn.sxh.snowfox.UI.interactor;

import cn.sxh.snowfox.callback.RequestCallBack;
import rx.Subscription;

/**
 * @author by snow on 2017/8/14
 * @time 00:30
 * @mail snowtigersong@gmail.com
 */

public interface CategoryFragmentInteractor<T> {
    Subscription loadBannerInfo(RequestCallBack<T> callBack);
}
