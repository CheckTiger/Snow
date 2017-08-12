package cn.sxh.snowfox.UI.fragment.home;

import android.app.Activity;
import android.support.annotation.NonNull;

import cn.sxh.snowfox.bean.BannerEntity;
import cn.sxh.snowfox.bean.JuHeBannerToutiaoEntity;
import cn.sxh.snowfox.view.multitype.Item;

/**
 * Created by snow on 2017/8/10.
 */
public class Banner implements Item {

//    public final JuHeBannerToutiaoEntity mEntity;
    public final BannerEntity mEntity;
    public final Activity mActivity;

    public Banner(Activity activity, @NonNull BannerEntity bannerEntity) {
        this.mEntity = bannerEntity;
        this.mActivity = activity;
    }
}