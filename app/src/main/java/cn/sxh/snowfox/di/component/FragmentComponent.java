package cn.sxh.snowfox.di.component;

import android.app.Activity;
import android.content.Context;

import cn.sxh.snowfox.UI.fragment.CategoryFragment;
import cn.sxh.snowfox.UI.fragment.TechnologyFragment;
import cn.sxh.snowfox.di.module.FragmentModule;
import cn.sxh.snowfox.di.scope.ContextLife;
import cn.sxh.snowfox.di.scope.PerFragment;
import dagger.Component;

/**
 * @author by snow on 2017/8/13
 * @time 23:50
 * @mail snowtigersong@gmail.com
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(TechnologyFragment categoryFragment);
}
