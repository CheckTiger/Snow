package cn.sxh.snowfox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.bean.ThsNewsBean;

/**
 * Created by Administrator on 2017/12/26 0026.
 */

public class NewsAdapterNew extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ThsNewsBean mNewsBean;

    public NewsAdapterNew(Context context, ThsNewsBean mNewsBean) {
        this.context = context;
        this.mNewsBean = mNewsBean;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return  mNewsBean.getData().size()-1;
    }

    @Override
    public Object getItem(int position) {
        return mNewsBean.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.hypnosis_fragment, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        for (int j = 0; j < mNewsBean.getData().size(); j++) {
            initViewData(holder,position);
        }
        return convertView;
    }

    /**
     * 填充数据
     * @param holder
     * @param i
     */
    private void initViewData(ViewHolder holder, int i) {
        Glide.with(context).load("https:"+mNewsBean.getData().get(i+1).getImgurl()).into(holder.newsIcon);
        holder.newsTitle.setText(mNewsBean.getData().get(i+1).getTitle());
        holder.newsTime.setText(mNewsBean.getData().get(i+1).getCtime());
    }

    static class ViewHolder  {
        private TextView newsTitle,newsTime;
        private ImageView newsIcon;
        ViewHolder(View itemView) {
            AutoUtils.auto(itemView);
            newsTitle = itemView.findViewById(R.id.title_name);
            newsTime = itemView.findViewById(R.id.title_content);
            newsIcon = itemView.findViewById(R.id.logo_icon);
        }
    }
}
