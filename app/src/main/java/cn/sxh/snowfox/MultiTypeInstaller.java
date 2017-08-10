package cn.sxh.snowfox;

import cn.sxh.snowfox.UI.fragment.home.Banner;
import cn.sxh.snowfox.UI.fragment.home.BannerViewBinder;
import cn.sxh.snowfox.view.multitype.GlobalMultiTypePool;

/**
 * @author by snow on 2017/8/10
 * @time 22:25
 * @mail snowtigersong@gmail.com
 */

public class MultiTypeInstaller {
    public static void start(){
        GlobalMultiTypePool.register(Banner.class,new BannerViewBinder());//轮播图item加载到item类型池中
    }
}
