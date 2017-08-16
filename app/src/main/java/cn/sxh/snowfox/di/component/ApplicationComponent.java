package cn.sxh.snowfox.di.component;

import android.content.Context;

import cn.sxh.snowfox.di.module.ApplicationModule;
import cn.sxh.snowfox.di.scope.ContextLife;
import cn.sxh.snowfox.di.scope.PerApp;
import dagger.Component;

/**
 * @author by snow on 2017/8/13
 * @time 13:56
 * @mail snowtigersong@gmail.com
 */


//用@Component表示这个接口是一个连接器，能用@Component注解的只能是interface或者抽象类
@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplication();
}
