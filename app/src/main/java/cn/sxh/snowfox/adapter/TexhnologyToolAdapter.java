package cn.sxh.snowfox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author by snow on 2017/9/2
 * @time 21:47
 * @mail snowtigersong@gmail.com
 */

public class TexhnologyToolAdapter extends BaseAdapter {
    private List<String> list;
    private Context mContext;

    public TexhnologyToolAdapter(List<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
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
        TextView textView = new TextView(mContext);
        textView.setText(list.get(i));
        return textView;
    }
}
