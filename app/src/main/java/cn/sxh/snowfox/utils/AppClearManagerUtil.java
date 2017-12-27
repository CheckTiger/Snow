package cn.sxh.snowfox.utils;

import android.content.Context;

/**
 * @package-name: cn.sxh.snowfox.utils
 * @auther:snowFox
 * @time: 2017/12/27 0027 18:27
 * @project-name: Snow
 */

public class AppClearManagerUtil {
    /**
     * 清除本应用内部缓存数据(/data/data/com.xxx.xxx/cache)
     * @param context 上下文
     * @return void
     */
    public static void cleanInternalCache(Context context) {
//        AppFileMgr.deleteFilesByDirectory(context.getCacheDir());
//        AppLogMessageMgr.i("AppCleanMgr->>cleanInternalCache", "清除本应用内部缓存(/data/data/" + context.getPackageName() + "/cache)");
    }
}
