package cn.sxh.snowfox.API;

import cn.sxh.snowfox.bean.BannerEntity;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by snow on 2017/8/7.
 */

public interface SnowFoxApi {
    /**
     * 1.获取轮播图资源
     * @return
     */
    @POST("/common/find_advertising")
    Observable<BannerEntity> getBanner();
}
