package cn.sxh.snowfox.utils;

import android.content.Context;

/**
 * Created by snow on 2017/8/3.
 */

public class DisplayUtil {
    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
