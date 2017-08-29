package cn.sxh.snowfox.UI.fragment.home;

import android.app.Activity;
import android.support.annotation.NonNull;

import cn.sxh.snowfox.bean.JuHeBannerToutiaoEntity;
import cn.sxh.snowfox.view.multitype.Item;

/**
 * @author by snow on 2017/8/29
 * @time 23:33
 * @mail snowtigersong@gmail.com
 * 内容列表
 */

public class ContentItem implements Item {
    public final JuHeBannerToutiaoEntity mEntity;
    public final Activity mActivity;

    public ContentItem(Activity activity, @NonNull JuHeBannerToutiaoEntity bannerEntity) {
        this.mEntity = bannerEntity;
        this.mActivity = activity;
    }
}
