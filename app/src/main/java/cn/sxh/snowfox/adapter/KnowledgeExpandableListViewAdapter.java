package cn.sxh.snowfox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;
import java.util.Map;

import cn.sxh.snowfox.R;

/**
 * @author by snow on 2017/9/18
 * @time 23:45
 * @mail snowtigersong@gmail.com
 */

public class KnowledgeExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Map<String, List<String>> dataset;
    private List<String> technologyName;
    private Context context;
    private LayoutInflater inflater;

    public KnowledgeExpandableListViewAdapter(Map<String, List<String>> dataset, Context context,List<String> list) {
        this.dataset = dataset;
        this.context = context;
        this.technologyName = list;
        this.inflater = LayoutInflater.from(context);
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
        return dataset.get(technologyName.get(i));
    }

    @Override
    public Object getChild(int i, int i1) {
        return dataset.get(technologyName.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.all_fragment_list_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title_name.setText(technologyName.get(i));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.all_fragment_list_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title_name.setText(dataset.get(technologyName.get(i)).get(i1));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public class ViewHolder{
        private TextView title_name;
        private LinearLayout mLinearLayout;

        public ViewHolder(View view) {
            title_name = view.findViewById(R.id.all_fragment_item_text);
            mLinearLayout = view.findViewById(R.id.all_fragment_linearLayout);
            AutoUtils.auto(view);
        }
    }

}
