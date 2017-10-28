package cn.sxh.snowfox.UI.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sxh.snowfox.AppContext;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.UI.activity.NewsActivity;
import cn.sxh.snowfox.UI.activity.PaintActivity;
import cn.sxh.snowfox.UI.activity.ViewStudyActivity;
import cn.sxh.snowfox.adapter.AllFragmentAdapter;
import cn.sxh.snowfox.adapter.KnowledgeExpandableListViewAdapter;
import cn.sxh.snowfox.base.BaseFragment;

import static android.R.id.icon;
import static android.R.id.list;

/**
 * @author by snow on 2017/9/9
 * @time 23:53
 * @mail snowtigersong@gmail.com
 * 该模块采用 可折叠列表ExpandableListView来实现
 */

public class KnowledgeFragment extends BaseFragment {
    private ListView listview;
    private AllFragmentAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.knowledge_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        listview = view.findViewById(R.id.expanded_listView);
    }

    @Override
    protected void initData() {
        List<String> technologyName = Arrays.asList(AppContext.getInstance().getResources().getStringArray(R.array.knowledge_fragment_item));
        adapter = new AllFragmentAdapter( getContext(),technologyName);
        listview.setAdapter(adapter);
        adapter.setOnLinearLayoutListener((holder, position) -> gotoActivity(position));
    }

    private void gotoActivity(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(getActivity(), ViewStudyActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent paintIntent = new Intent(getActivity(), PaintActivity.class);
                startActivity(paintIntent);
                break;
        }
    }
}
