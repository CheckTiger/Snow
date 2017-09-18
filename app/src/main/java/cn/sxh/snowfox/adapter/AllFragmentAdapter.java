package cn.sxh.snowfox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
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
        initUIListener(holder,i);
        return view;
    }

    private void initUIListener(ViewHolder holder, int i) {
        holder.mLinearLayout.setOnClickListener(view -> listener.onLinearLayoutClick(holder,i));
    }

    private void initInfo(ViewHolder holder, int i) {
        holder.title_name.setText(list.get(i));
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

    /**
     * 练习接口回调的写作方式
     */
    private OnLinearLayoutListener listener;
    public interface OnLinearLayoutListener{
        void onLinearLayoutClick(ViewHolder holder,int position);
    }
    public void setOnLinearLayoutListener(OnLinearLayoutListener layoutListener) {
        this.listener = layoutListener;
    }
}
