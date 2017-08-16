package cn.sxh.snowfox.di.module;

import android.content.Context;

import cn.sxh.snowfox.AppContext;
import cn.sxh.snowfox.di.scope.ContextLife;
import cn.sxh.snowfox.di.scope.PerApp;
import dagger.Module;
import dagger.Provides;

/**
 * @author by snow on 2017/8/13
 * @time 13:56
 * @mail snowtigersong@gmail.com
 */
@Module  //表示提供module这个类数据对象
public class ApplicationModule {
    private AppContext mAppContext;
    public ApplicationModule(AppContext application){mAppContext = application;}
    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext(){
        return mAppContext.getApplicationContext();
    }
}
