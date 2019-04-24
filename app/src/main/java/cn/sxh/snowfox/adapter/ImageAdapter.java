package cn.sxh.snowfox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import cn.sxh.snowfox.ImageLoader.ImageResizer;
import cn.sxh.snowfox.R;

/**
 * @package-name: cn.sxh.snowfox.adapter
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/4/23 0023 : 17 :41
 * @project-name: Snow
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> mList;
    private LayoutInflater mInflater;
    private boolean IsGridViewIdle = true;

    public ImageAdapter(Context context, List<Integer> mList) {
        this.context = context;
        this.mList = mList;
        mInflater = LayoutInflater.from(context);
    }

    @Override

    public int getCount() {
        return mList.size() > 0 ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.surprise_fragment_gv_list_image_loader_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (IsGridViewIdle) {
            holder.mIv.setImageBitmap(ImageResizer.getIntance().decodeSampledBitmapFromResource(context.getResources(), mList.get(position), 100, 100));
        }
        return convertView;
    }

    private class ViewHolder {
        private ImageView mIv;
        public ViewHolder(View convertView) {
            mIv = convertView.findViewById(R.id.gridView_imageLoader_image_item);
        }
    }

    public void setGridViewIdle(boolean isToLoadResources) {
        this.IsGridViewIdle = isToLoadResources;
        notifyDataSetChanged();
    }
}
