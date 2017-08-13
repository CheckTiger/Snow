package cn.sxh.snowfox.callback;

/**
 * @author by snow on 2017/8/14
 * @time 00:11
 * @mail snowtigersong@gmail.com
 */

public interface RequestCallBack<T> {

    void beforeRequest();

    void success(T data);

    void onError(String errorMsg);
}
