package cn.sxh.snowfox.UI.fragment;

import android.view.View;

import cn.sxh.snowfox.AppConfig;
import cn.sxh.snowfox.AppContext;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.base.BaseFragment;

/**
 * Created by snow on 2017/8/5.
 */

public class MusicFragment extends BaseFragment {
    @Override
    protected int getContentView() {
        return R.layout.activity_news;
    }

    @Override
    protected void initUI(View view) {
        AppConfig.saveStringSpValue(AppContext.getInstance(),"BANNER","SONG","你好啊");
    }

    @Override
    protected void initData() {

    }
}
