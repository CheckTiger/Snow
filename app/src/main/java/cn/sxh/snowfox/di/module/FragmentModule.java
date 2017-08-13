package cn.sxh.snowfox.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import cn.sxh.snowfox.di.scope.ContextLife;
import cn.sxh.snowfox.di.scope.PerFragment;
import dagger.Module;
import dagger.Provides;

/**
 * @author by snow on 2017/8/13
 * @time 23:50
 * @mail snowtigersong@gmail.com
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment){mFragment = fragment;}

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideActivityContext(){
        return mFragment.getActivity();
    }
    @Provides
    @PerFragment
    public Activity provideActivity(){
        return mFragment.getActivity();
    }

    public Fragment provideFragment(){
        return mFragment;
    }
}
