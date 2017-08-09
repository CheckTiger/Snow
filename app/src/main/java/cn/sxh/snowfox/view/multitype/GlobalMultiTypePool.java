package cn.sxh.snowfox.view.multitype;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * @author Wxy on 2016/10/18 0018.
 */

public class GlobalMultiTypePool {


    private static MultiTypePool pool = new MultiTypePool();


    public static void register(
            @NonNull Class<?> clazz, @NonNull ItemViewProvider provider) {
        pool.register(clazz, provider);
    }


    public static int indexOf(@NonNull Class<?> clazz) {
        return pool.indexOf(clazz);
    }


    @NonNull
    public static ArrayList<Class<?>> getContents() {
        return pool.getContents();
    }


    @NonNull
    public static ArrayList<ItemViewProvider> getProviders() {
        return pool.getProviders();
    }


    @NonNull
    public static ItemViewProvider getProviderByIndex(int index) {
        return pool.getProviderByIndex(index);
    }


    @NonNull
    public static <T extends ItemViewProvider> T getProviderByClass(
        @NonNull Class<?> clazz) {
        return pool.getProviderByClass(clazz);
    }


    @NonNull
    public static MultiTypePool getPool() {
        return pool;
    }
}
