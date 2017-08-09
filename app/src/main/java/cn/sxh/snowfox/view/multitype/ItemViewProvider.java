package cn.sxh.snowfox.view.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author Wxy on 2016/10/18 0018.
 */

public abstract class ItemViewProvider<C, V extends RecyclerView.ViewHolder> {

    /* @formatter:off */

    @NonNull
    protected abstract V onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    protected abstract void onBindViewHolder(@NonNull V holder, @NonNull C c);
}