package cn.sxh.snowfox.UI.fragment;

import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sxh.snowfox.AppContext;
import cn.sxh.snowfox.R;
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
    private ExpandableListView listview;
    private Map<String, List<String>> dataset = new HashMap<>();
    private KnowledgeExpandableListViewAdapter adapter;
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
        List<String> technologyName = Arrays.asList(AppContext.getInstance().getResources().getStringArray(R.array.all_fragment_item));
        String[] technologyName1 = new String[technologyName.size()];
        List<String> listRelative = new ArrayList<>();
        List<String> listUI = new ArrayList<>();
        for (int i = 0; i < technologyName.size(); i++) {
            technologyName1[i] = technologyName.get(i);
            if (technologyName.get(i).equals("常用布局")) {
                listRelative.add("相对布局");
                listRelative.add("线性布局");
                listRelative.add("帧布局");
                listRelative.add("表格布局");
                listRelative.add("绝对布局");
                dataset.put(technologyName.get(i),listRelative);
            }
            if (technologyName.get(i).equals("常见控件")) {
                listUI.add("textView");
                listUI.add("button");
                listUI.add("ImageView");
                listUI.add("ImageButton");
                listUI.add("Dialog");
                dataset.put(technologyName.get(i),listUI);
            }
        }
        adapter = new KnowledgeExpandableListViewAdapter( dataset, getActivity(),technologyName);
        listview.setAdapter(adapter);
    }

}
