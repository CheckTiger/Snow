package cn.sxh.snowfox.UI.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.socks.library.KLog;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.sxh.greendao.TechnologyTable;
import cn.sxh.greendao.TechnologyTableDao;
import cn.sxh.snowfox.AppContext;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.UI.activity.MainActivity;
import cn.sxh.snowfox.UI.activity.NewsActivity;
import cn.sxh.snowfox.adapter.AllFragmentAdapter;
import cn.sxh.snowfox.base.BaseFragment;

/**
 * @author by snow on 2017/9/9
 * @time 23:53
 * @mail snowtigersong@gmail.com
 * 全部模块列表采用listView控件来实现
 */

public class AllFragment extends BaseFragment {
    private static final String TAG = "AllFragment";
    private ListView mListView;
    private List<String> list = new ArrayList<>();
    private AllFragmentAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.all_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        mListView = view.findViewById(R.id.all_fragment_listView);
        adapter = new AllFragmentAdapter(getContext(),list);
    }

    @Override
    protected void initData() {
        List<String> technologyName = Arrays.asList(AppContext.getInstance().getResources().getStringArray(R.array.all_fragment_item));
        for (int i = 0; i < technologyName.size(); i++) {
            list.add(technologyName.get(i));
        }
        mListView.setAdapter(adapter);
        adapter.setOnLinearLayoutListener((holder, position) -> gotoActivity(position));
    }

    private void gotoActivity(int position) {
        switch (position) {
            case 0:
            Intent intent = new Intent(getActivity(), NewsActivity.class);
            startActivity(intent);
        }

    }
}
