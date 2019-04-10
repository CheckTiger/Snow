package cn.sxh.snowfox.API;

import java.util.List;

import cn.sxh.snowfox.bean.BannerEntity;
import cn.sxh.snowfox.bean.JuHeBannerToutiaoEntity;
import cn.sxh.snowfox.bean.ThsNewsBean;
import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by snow on 2017/8/7.
 */

public interface SnowFoxApi {
    /**
     * 1.获取轮播图资源-get方式请求
     * @return
     */
    @GET("toutiao/index?&key=3988f8911b515e58d49ac192823d9960")
    Observable<JuHeBannerToutiaoEntity> getBannerByGet();
    /**
     * 1.获取轮播图资源-post方式请求
     * @return
     */
    @POST("common/find_advertising")
    Observable<BannerEntity> getBannerByQuNaWan();



    /**
     * 1.获取轮播图资源-post方式请求
     * @return
     */
    @POST("common/find_advertising")
    Observable<BannerEntity> getBannerBNaWan();
    /**
     * 1.获取轮播图资源--post请求方式
     * @return
     */
    @POST("toutiao/index")
    @FormUrlEncoded
    Observable<JuHeBannerToutiaoEntity> getBannerByPost(@Field("key")String key);

    @GET("{index}")
    Observable<ThsNewsBean> getNewsList(@Path("index") String index);
}
