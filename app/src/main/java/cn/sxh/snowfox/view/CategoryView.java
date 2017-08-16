package cn.sxh.snowfox.view;

import cn.sxh.snowfox.bean.BannerEntity;
import cn.sxh.snowfox.view.base.BaseView;

/**
 * @author by snow on 2017/8/14
 * @time 00:23
 * @mail snowtigersong@gmail.com
 */

public interface CategoryView extends BaseView {
    void initBanner(BannerEntity bannerEntity);
}
