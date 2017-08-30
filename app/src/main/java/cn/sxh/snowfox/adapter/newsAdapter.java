package cn.sxh.snowfox.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.UI.activity.NewsActivity;
import cn.sxh.snowfox.bean.JuHeBannerToutiaoEntity;

/**
 * @author by snow on 2017/8/30
 * @time 00:16
 * @mail snowtigersong@gmail.com
 */

public class newsAdapter extends BaseAdapter {
    private JuHeBannerToutiaoEntity bannerEntity;
    private Context context;
    private LayoutInflater inflater;

    public newsAdapter(JuHeBannerToutiaoEntity bannerEntity, Context context) {
        this.bannerEntity = bannerEntity;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bannerEntity.getResult().getData() == null ? 0 : bannerEntity.getResult().getData().size();
    }

    @Override
    public Object getItem(int i) {
        return bannerEntity.getResult().getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.content_item_layout, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        for (int j = 0; j < bannerEntity.getResult().getData().size(); j++) {
            initViewData(holder,i);
        }
        holder.mLinearLayout.setOnClickListener(view1 -> gotoNewsDetail(i));
        return view;
    }

    /**
     * 填充数据
     * @param holder
     * @param i
     */
    private void initViewData(ViewHolder holder, int i) {
        Glide.with(context).load(bannerEntity.getResult().getData().get(i).getThumbnail_pic_s()).into(holder.newsIcon);
        holder.newsTitle.setText(bannerEntity.getResult().getData().get(i).getTitle());
        holder.newsActor.setText(bannerEntity.getResult().getData().get(i).getAuthor_name());
        holder.newsData.setText(bannerEntity.getResult().getData().get(i).getDate());
        holder.newsSource.setText(bannerEntity.getResult().getData().get(i).getCategory());
    }

    /**
     * 跳转到新闻详情页面
     * @param i
     */
    private void gotoNewsDetail(int i) {
        Intent intent = new Intent(context, NewsActivity.class);
        intent.putExtra("news_url",bannerEntity.getResult().getData().get(i).getUrl());
        context.startActivity(intent);
    }

    static class ViewHolder  {
        private TextView newsTitle,newsActor,newsData,newsSource;
        private ImageView newsIcon;
        private LinearLayout mLinearLayout;
        ViewHolder(View itemView) {
            AutoUtils.auto(itemView);
            newsTitle = itemView.findViewById(R.id.news_title);
            newsActor = itemView.findViewById(R.id.news_actor);
            newsData = itemView.findViewById(R.id.news_data);
            newsSource = itemView.findViewById(R.id.news_source);
            newsIcon = itemView.findViewById(R.id.news_icon);
            mLinearLayout = itemView.findViewById(R.id.news_container);
        }
    }
}
