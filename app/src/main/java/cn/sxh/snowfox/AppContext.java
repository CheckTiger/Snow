package cn.sxh.snowfox;

import android.app.Application;

/**
 * Created by snow on 2017/8/8.
 */

public class AppContext extends Application {

    private static AppContext instance;
    protected AppConfig config;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = (AppContext) getApplicationContext();
        MultiTypeInstaller.start();
        config = AppConfig.getConfig(getInstance());
    }

    public static AppContext getInstance(){return instance;}


}
