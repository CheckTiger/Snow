package cn.sxh.snowfox.view.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wxy on 2016/10/18 0018.
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder>
    implements FlatTypeAdapter, TypePool {

    protected final List<?> items;
    protected LayoutInflater inflater;
    protected TypePool delegate;


    public MultiTypeAdapter(@NonNull List<?> items) {
        this.delegate = new MultiTypePool();
        this.items = items;
    }


    public MultiTypeAdapter(@NonNull List<?> items, TypePool pool) {
        this.delegate = pool;
        this.items = items;
    }


    @NonNull
    @Override
    public Class onFlattenClass(@NonNull final Object item) {
        return item.getClass();
    }


    @NonNull
    @Override
    public Object onFlattenItem(@NonNull final Object item) {
        return item;
    }


    @SuppressWarnings("unchecked") @Override
    public int getItemViewType(int position) {
        Object item = items.get(position);
        return indexOf(onFlattenClass(item));
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return getProviderByIndex(indexViewType).onCreateViewHolder(inflater, parent);
    }


    @SuppressWarnings("unchecked") @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object item = items.get(position);
        getProviderByClass(onFlattenClass(item)).onBindViewHolder(holder, onFlattenItem(item));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public void applyGlobalMultiTypePool() {
        for (int i = 0; i < GlobalMultiTypePool.getContents().size(); i++) {
            final Class<?> clazz = GlobalMultiTypePool.getContents().get(i);
            final ItemViewProvider provider = GlobalMultiTypePool.getProviders().get(i);
            if (!this.getContents().contains(clazz)) {
                this.register(clazz, provider);
            }
        }
    }


    public void registerAll(@NonNull final MultiTypePool pool) {
        for (int i = 0; i < pool.getContents().size(); i++) {
            delegate.register(pool.getContents().get(i), pool.getProviders().get(i));
        }
    }


    @Override
    public void register(@NonNull Class<?> clazz, @NonNull ItemViewProvider provider) {
        delegate.register(clazz, provider);
    }


    @Override
    public int indexOf(@NonNull Class<?> clazz)
        throws ProviderNotFoundException {
        int index = delegate.indexOf(clazz);
        if (index >= 0) {
            return index;
        }
        throw new ProviderNotFoundException(clazz);
    }


    @NonNull
    @Override
    public ArrayList<Class<?>> getContents() {
        return delegate.getContents();
    }


    @NonNull
    @Override
    public ArrayList<ItemViewProvider> getProviders() {
        return delegate.getProviders();
    }


    @NonNull
    @Override
    public ItemViewProvider getProviderByIndex(int index) {
        return delegate.getProviderByIndex(index);
    }


    @NonNull
    @Override
    public <T extends ItemViewProvider> T getProviderByClass(@NonNull Class<?> clazz) {
        return delegate.getProviderByClass(clazz);
    }
}