package cn.sxh.snowfox.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by snow on 2017/8/7.
 */

public class ApiRetrofit {

    private static SnowFoxApi snowFoxApi;
    private static final String HOST = "http://www.51qunawan.cn/QunawanService/mobile/";

    public static SnowFoxApi getInstance() {
        if (snowFoxApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(HOST)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            snowFoxApi = retrofit.create(SnowFoxApi.class);
        }
        return snowFoxApi;
    }

    public static OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20*1000, TimeUnit.MILLISECONDS)
                .readTimeout(20*1000,TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true);
        return okHttpClient.build();
    }
}
