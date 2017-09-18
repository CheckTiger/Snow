package cn.sxh.snowfox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;
import java.util.Map;

/**
 * @author by snow on 2017/9/18
 * @time 23:45
 * @mail snowtigersong@gmail.com
 */

public class KnowledgeExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Map<String, List<String>> dataset;
    private List<String> technologyName;
    private Context context;

    public KnowledgeExpandableListViewAdapter(Map<String, List<String>> dataset, Context context,List<String> list) {
        this.dataset = dataset;
        this.context = context;
        this.technologyName = list;
    }

    @Override
    public int getGroupCount() {
        return dataset.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return dataset.get(technologyName.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
