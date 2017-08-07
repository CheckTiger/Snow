package cn.sxh.snowfox.API;

import java.util.concurrent.TimeUnit;

import cn.sxh.snowfox.bean.BannerEntity;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by snow on 2017/8/7.
 */

public class ApiRetrofit {

    public static ApiRetrofit instance;
    private SnowFoxApi snowFoxApi;
    private static final String HOST = "http://www.51qunawan.cn/QunawanService/mobile";

    public ApiRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        snowFoxApi = retrofit.create(SnowFoxApi.class);
    }

    public static ApiRetrofit getInstance() {
        if (instance == null) {
            synchronized (ApiRetrofit.class) {
                if (instance == null) {
                    instance = new ApiRetrofit();
                }
            }
        }
        return instance;
    }

    public static OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20*1000, TimeUnit.MILLISECONDS)
                .readTimeout(20*1000,TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true);
        return okHttpClient.build();
    }
    /**
     * 1.获取轮播图资源
     * @return
     */
    public Observable<BannerEntity> getBanner(){
        return snowFoxApi.getBanner();
    }
}
