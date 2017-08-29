package cn.sxh.snowfox.UI.fragment.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.adapter.newsAdapter;
import cn.sxh.snowfox.bean.JuHeBannerToutiaoEntity;
import cn.sxh.snowfox.view.NewsListView;
import cn.sxh.snowfox.view.multitype.ItemViewProvider;

/**
 * @author by snow on 2017/8/29
 * @time 23:34
 * @mail snowtigersong@gmail.com
 */

public class ContentItemProvider extends ItemViewProvider<ContentItem, ContentItemProvider.ViewHolder> {

    private JuHeBannerToutiaoEntity bannerEntity;
    private Context context;

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_list_view, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ContentItem contentItem) {
        bannerEntity = contentItem.mEntity;
        this.context = contentItem.mActivity;
        if (bannerEntity.getResult() == null || bannerEntity.getResult().getData().isEmpty()) {
            return;
        }
        holder.mListView.setAdapter(new newsAdapter(bannerEntity,context));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private NewsListView mListView;
        ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            mListView = itemView.findViewById(R.id.news_list_view);
        }
    }
}
