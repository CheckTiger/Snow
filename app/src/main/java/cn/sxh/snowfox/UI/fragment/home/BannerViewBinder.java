package cn.sxh.snowfox.UI.fragment.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.bean.JuHeBannerToutiaoEntity;
import cn.sxh.snowfox.view.multitype.ItemViewProvider;

/**
 * Created by snow on 2017/8/10.
 */
public class BannerViewBinder extends ItemViewProvider<Banner, BannerViewBinder.ViewHolder> implements OnBannerListener{

    private GlideImageLoader mImageLoader;
    private JuHeBannerToutiaoEntity bannerEntity;
    private Context context;
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_banner, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Banner banner) {
        holder.banner.setImageLoader(getImageLoader());
        bannerEntity = banner.mEntity;
        this.context = banner.mActivity;
        if (bannerEntity.getResult().getData() == null || bannerEntity.getResult().getData().isEmpty()) {
            return;
        }
        List<String> IMAGE_URL =new ArrayList<>();
        for (JuHeBannerToutiaoEntity.ResultBean.DataBean result : bannerEntity.getResult().getData()) {
            IMAGE_URL.add(result.getThumbnail_pic_s02());
        }
        if (IMAGE_URL.size() > 1) {
            holder.banner.isAutoPlay(true);//其实这个库默认是设置为轮播的
        }
        holder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        holder.banner.setBannerAnimation(Transformer.DepthPage);
        holder.banner.setImages(IMAGE_URL);
        holder.banner.setDelayTime(1500);
        holder.banner.setIndicatorGravity(BannerConfig.CENTER);
        holder.banner.start();
        holder.banner.setOnBannerListener(this);//加载点击监听事件
    }

    @Override
    public void OnBannerClick(int position) {
        ArrayList<String> webUrl = new ArrayList<>();
        if (bannerEntity.getResult() != null) {
            for (JuHeBannerToutiaoEntity.ResultBean.DataBean result : bannerEntity.getResult().getData()) {
                webUrl.add(result.getUrl());
            }
            Toast.makeText(context, webUrl.get(position), Toast.LENGTH_SHORT).show();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull private final com.youth.banner.Banner banner;
        ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    private  class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
    public synchronized GlideImageLoader getImageLoader(){
        if (mImageLoader == null) {
            mImageLoader = new GlideImageLoader();
        }
        return mImageLoader;
    }
}
