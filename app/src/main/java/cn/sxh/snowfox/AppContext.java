package cn.sxh.snowfox;

import android.app.Application;

/**
 * Created by snow on 2017/8/8.
 */

public class AppContext extends Application {

    private static AppContext instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = (AppContext) getApplicationContext();
        MultiTypeInstaller.start();
    }

    public static AppContext getInstance(){return instance;}
}
