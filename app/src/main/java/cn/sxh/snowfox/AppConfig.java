package cn.sxh.snowfox;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class AppConfig {

    public static AppConfig config;
    private static Context context;

    public static AppConfig getConfig(Context context) {
        if (config == null) {
            config = new AppConfig();
            config.context = context;
        }
        return config;
    }

//    public static SharedPreferences getPreferences(){
////        SharedPreferences sp = context.getSharedPreferences();
//    }
}
