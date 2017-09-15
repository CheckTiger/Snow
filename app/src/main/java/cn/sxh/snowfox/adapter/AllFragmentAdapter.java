package cn.sxh.snowfox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author by snow on 2017/9/15
 * @time 21:18
 * @mail snowtigersong@gmail.com
 */

public class AllFragmentAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public AllFragmentAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
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
        TextView textView = new TextView(context);
        textView.setText(list.get(i));
        return textView;
    }
}
