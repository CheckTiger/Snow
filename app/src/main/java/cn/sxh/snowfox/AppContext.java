package cn.sxh.snowfox;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.sxh.snowfox.di.component.ApplicationComponent;
import cn.sxh.snowfox.di.component.DaggerApplicationComponent;
import cn.sxh.snowfox.di.module.ApplicationModule;

/**
 * Created by snow on 2017/8/8.
 */

public class AppContext extends Application {

    private static AppContext instance;
    protected AppConfig config;
    private ApplicationComponent mApplicationComponent;
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        AppContext appContext = (AppContext) context.getApplicationContext();
        return appContext.refWatcher;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = (AppContext) getApplicationContext();
        initLeakCanary();
        initStrictMode();
        MultiTypeInstaller.start();
        config = AppConfig.getConfig(getInstance());
        initApplicationComponent();
    }

    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            refWatcher = LeakCanary.install(this);
        } else {
            refWatcher = installLeakCanary();
        }
    }
    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
//                            .penaltyDialog() // 弹出违规提示对话框
                            .penaltyLog() // 在logcat中打印违规异常信息
                            .build());
            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog()
                            .build());
        }
    }
    private RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }

    public static AppContext getInstance(){return instance;}

    /**
     * 初始化ApplicationComponent
     */
    private void initApplicationComponent(){
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
    public ApplicationComponent getmApplicationComponent(){
        return mApplicationComponent;
    }
}
