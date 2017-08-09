package cn.sxh.snowfox.view.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * @author Wxy on 2016/10/18 0018.
 */

public class MultiTypeAsserts {

    private MultiTypeAsserts() {
        throw new AssertionError();
    }


    /**
     * Make the exception to occur in your class for debug and index.
     *
     * @throws ProviderNotFoundException If check failed.
     * @throws IllegalArgumentException If your Items/List is empty.
     */
    @SuppressWarnings("unchecked")
    public static void assertAllRegistered(
        @NonNull MultiTypeAdapter adapter,
        @NonNull List<?> items)
        throws ProviderNotFoundException, IllegalArgumentException, IllegalAccessError {

        if (items.size() == 0) {
            throw new IllegalArgumentException("Your Items/List is empty.");
        }
        for (Object item : items) {
            adapter.indexOf(adapter.onFlattenClass(item));
        }
        /* All passed. */
    }


    /**
     * @throws IllegalAccessError The assertHasTheSameAdapter() method must be placed after
     * recyclerView.setAdapter()
     * @throws IllegalArgumentException If your recyclerView's adapter
     * is not the sample with the argument adapter.
     */
    public static void assertHasTheSameAdapter(
            @NonNull RecyclerView recyclerView, @NonNull MultiTypeAdapter adapter)
        throws IllegalArgumentException, IllegalAccessError {
        if (recyclerView.getAdapter() == null) {
            throw new IllegalAccessError(
                "The assertHasTheSameAdapter() method must be placed after recyclerView.setAdapter()");
        }
        if (recyclerView.getAdapter() != adapter) {
            throw new IllegalArgumentException(
                "Your recyclerView's adapter is not the sample with the argument adapter.");
        }
    }

}
