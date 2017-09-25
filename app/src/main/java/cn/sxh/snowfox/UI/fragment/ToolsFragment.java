package cn.sxh.snowfox.UI.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.sxh.snowfox.AppContext;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.UI.activity.BankActivity;
import cn.sxh.snowfox.UI.activity.NewsActivity;
import cn.sxh.snowfox.adapter.AllFragmentAdapter;
import cn.sxh.snowfox.base.BaseFragment;

/**
 * @author by snow on 2017/9/9
 * @time 23:53
 * @mail snowtigersong@gmail.com
 */

public class ToolsFragment extends BaseFragment {
    private static final String TAG = "ToolsFragment";
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
        List<String> technologyName = Arrays.asList(AppContext.getInstance().getResources().getStringArray(R.array.tools_fragment_item));
        for (int i = 0; i < technologyName.size(); i++) {
            list.add(technologyName.get(i));
        }
        mListView.setAdapter(adapter);
        adapter.setOnLinearLayoutListener((holder, position) -> gotoActivity(position));
    }

    private void gotoActivity(int position) {
        switch (position) {
            case 1:
                Intent intent = new Intent(getActivity(), BankActivity.class);
                startActivity(intent);
                break;
        }
    }

}
