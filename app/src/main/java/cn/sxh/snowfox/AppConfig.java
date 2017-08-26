package cn.sxh.snowfox;

import android.content.Context;
import android.content.SharedPreferences;

import rx.Subscription;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class AppConfig {

    public static final String APP_NIGHT_OR_LIGHT_MODE = "app_night_or_light_mode";//日夜间切换模式
    public static AppConfig config;

    public static Context mContext;

    public static AppConfig getConfig(Context context) {
        if (config == null) {
            config = new AppConfig();
            config.mContext = context;
        }
        return config;
    }


    /**
     * 打个比方：某个人的信息是这样的：清华大学--计算机学院的学生 学号：123456 姓名：赵日天
     * 在我们获取的时候，我们首先找到文件spName（清华大学--计算机学院），再
     * 通过key值（学号: 123456）把那个天杀的value（赵日天）拿出来，凌迟处死
     * 保存String字符串信息
     * @param spName 保存的文件名字（我们在这里将(清华大学--计算机学院)当作保存文件的名字）
     * @param key 保存的信息秘钥值  （学号: 123456 当作我们的key值）
     * @param value 保存的信息内容  （姓名：赵日天 当作我们的保存信息内容）
     */
    public static void saveStringSpValue(Context context,String spName, String key, String value) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(spName,Context.MODE_PRIVATE).edit();
        editor.putString(key,value);
        editor.commit();

    }

    /**
     * 获取保存的String字符串信息
     * @param context
     * @param spName
     * @param key
     * @return
     */
    public static String getStringSpValue(Context context,String spName,String key) {
        String value = "";
        if (context == null) {
            return "Are you sure that you have saved things?";
        }
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if (sp != null && key != null) {
            value = sp.getString(key, "Are you sure that you have saved things ?");
        }
        return value;
    }

    /**
     * 更新保存的String字符串信息
     * @param spName 保存的文件名字（我们在这里将(清华大学--计算机学院)当作保存文件的名字）
     * @param key 保存的信息秘钥值  （学号: 123456 当作我们的key值）
     * @param value 保存的信息内容  （姓名：赵日天 当作我们的保存信息内容）
     */
    public static void upDataStringSpValue(Context context,String spName, String key, String value) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(spName,Context.MODE_PRIVATE).edit();
        editor.putString(key,value);
        editor.commit();
    }


    /**
     * 打个比方：某个人的信息是这样的：清华大学--计算机学院的学生 学号：123456 姓名：赵日天
     * 在我们获取的时候，我们首先找到文件spName（清华大学--计算机学院），再
     * 通过key值（学号: 123456）把那个天杀的value（赵日天）拿出来，凌迟处死
     *
     * 保存String字符串信息
     * @param spName 保存的文件名字（我们在这里将(清华大学--计算机学院)当作保存文件的名字）
     * @param key 保存的信息秘钥值  （学号: 123456 当作我们的key值）
     * @param value 保存的信息内容  （姓名：赵日天 当作我们的保存信息内容）
     */
    public static void saveIntSpValue(Context context,String spName, String key, int value) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(spName,Context.MODE_PRIVATE).edit();
        editor.putInt(key,value);
        editor.commit();
    }

    /**
     * 获取保存的String字符串信息
     * @param context
     * @param spName
     * @param key
     * @return
     */
    public static int getIntSpValue(Context context,String spName,String key) {
        int value = 0;
        if (context == null) {
            return 0;
        }
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if (sp != null && key != null) {
            value = sp.getInt(key, -1);
        }
        return value;
    }

    /**
     * 更新保存的String字符串信息
     * @param spName 保存的文件名字（我们在这里将(清华大学--计算机学院)当作保存文件的名字）
     * @param key 保存的信息秘钥值  （学号: 123456 当作我们的key值）
     * @param value 保存的信息内容  （姓名：赵日天 当作我们的保存信息内容）
     */
    public static void upDataIntSpValue(Context context,String spName, String key, int value) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(spName,Context.MODE_PRIVATE).edit();
        editor.putInt(key,value);
        editor.commit();
    }

    /**
     * 打个比方：某个人的信息是这样的：清华大学--计算机学院的学生 学号：123456 姓名：赵日天
     * 在我们获取的时候，我们首先找到文件spName（清华大学--计算机学院），再
     * 通过key值（学号: 123456）把那个天杀的value（赵日天）拿出来，凌迟处死
     *
     * 保存Long字符串信息
     * @param spName 保存的文件名字（我们在这里将(清华大学--计算机学院)当作保存文件的名字）
     * @param key 保存的信息秘钥值  （学号: 123456 当作我们的key值）
     * @param value 保存的信息内容  （姓名：赵日天 当作我们的保存信息内容）
     */
    public static void saveLongSpValue(Context context,String spName, String key, long value) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(spName,Context.MODE_PRIVATE).edit();
        editor.putLong(key,value);
        editor.commit();
    }

    /**
     * 获取保存的Long字符串信息
     * @param context
     * @param spName
     * @param key
     * @return
     */
    public static long getLongSpValue(Context context,String spName,String key) {
        long value = 0;
        if (context == null) {
            return value;
        }
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if (sp != null && key != null) {
            value = sp.getLong(key, 0);
        }
        return value;
    }

    /**
     * 更新保存的String字符串信息
     * @param spName 保存的文件名字（我们在这里将(清华大学--计算机学院)当作保存文件的名字）
     * @param key 保存的信息秘钥值  （学号: 123456 当作我们的key值）
     * @param value 保存的信息内容  （姓名：赵日天 当作我们的保存信息内容）
     */
    public static void upDataLongSpValue(Context context,String spName, String key, long value) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(spName,Context.MODE_PRIVATE).edit();
        editor.putLong(key,value);
        editor.commit();
    }

    /**
     * 日夜间模式
     * @param context
     * @param spName
     * @param key
     * @param value
     */
    public static void isNightMode(Context context,String spName, String key, boolean value){
        if (context == null) {
            return ;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(spName,Context.MODE_PRIVATE).edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    /**
     * 获取存储的日夜间模式
     * @param context
     * @param spName
     * @param key
     * @return
     */
    public static boolean getNightMode(Context context,String spName, String key){
        boolean value = false;
        if (context == null) {
            return value;
        }
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if (sp != null && key != null) {
            value = sp.getBoolean(key, false);
        }
        return value;
    }

    public static void cancelSubscription(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
