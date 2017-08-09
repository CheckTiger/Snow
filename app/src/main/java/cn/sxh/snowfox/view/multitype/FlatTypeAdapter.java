package cn.sxh.snowfox.view.multitype;

import android.support.annotation.NonNull;

/**
 * @author Wxy on 2016/10/18 0018.
 */

public interface FlatTypeAdapter {

    @NonNull
    Class onFlattenClass(@NonNull Object item);

    @NonNull
    Object onFlattenItem(@NonNull final Object item);
}