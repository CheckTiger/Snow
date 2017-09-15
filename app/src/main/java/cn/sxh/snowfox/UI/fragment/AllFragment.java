package cn.sxh.snowfox.UI.fragment;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.adapter.AllFragmentAdapter;
import cn.sxh.snowfox.base.BaseFragment;

/**
 * @author by snow on 2017/9/9
 * @time 23:53
 * @mail snowtigersong@gmail.com
 * 全部模块列表采用listView控件来实现
 */

public class AllFragment extends BaseFragment {
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
        for (int i = 0; i < 20; i++) {
            list.add("宋学虎" + i);
        }
        mListView.setAdapter(adapter);
    }
}
