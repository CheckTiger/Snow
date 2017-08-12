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

    /**
     * 打个比方我们要拿到某个人的名字，他的信息是这样：清华大学-计算机学院 学号：123 姓名：赵日天
     * 我们要建立一个文件名字spName--清华大学-计算机学院---来存放这些数据（其实对应的就是--------spName.xml）===第二个参数
     * 有没文件名字我们还要知道他的 学号：123才能得到他的名字赵日天---也就是HashMap<Integer,String></>中的键值对key===第三个参数
     * 最后的就是我们保存的内容 value  ===第四个参数
     * 保存String类型数据
     * @param context
     * @param spName
     * @param key
     * @param value
     */
    public static void saveStringValue(Context context,String spName,String key,String value){
        if (context == null) {
            return;
        }
        //保存数据
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.apply();
    }
}
