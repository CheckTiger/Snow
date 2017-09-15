package cn.sxh.snowfox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import cn.sxh.snowfox.R;

/**
 * @author by snow on 2017/9/15
 * @time 21:18
 * @mail snowtigersong@gmail.com
 */

public class AllFragmentAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;
    public AllFragmentAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.all_fragment_list_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        initInfo(holder,i);
        return view;
    }

    private void initInfo(ViewHolder holder, int i) {
        holder.title_name.setText(list.get(i));
    }

    static class ViewHolder{
        private TextView title_name;

        public ViewHolder(View view) {
            title_name = view.findViewById(R.id.all_fragment_item_text);
            AutoUtils.auto(view);
        }
    }
}
