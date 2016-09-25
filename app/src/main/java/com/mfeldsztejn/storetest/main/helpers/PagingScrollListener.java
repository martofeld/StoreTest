package com.mfeldsztejn.storetest.main.helpers;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * A listener for a paging scroll
 */
public class PagingScrollListener extends RecyclerView.OnScrollListener {

    /**
     * The default page size
     */
    private static final int PAGE_SIZE = 100;

    /**
     * The callback to inform the implementer the recycler view scrolled enough
     */
    private final PagingScrollCallback callback;

    /**
     * A constructor for the listener
     *
     * @param callback the callback for when scrolled enough
     */
    public PagingScrollListener(PagingScrollCallback callback) {
        super();
        this.callback = callback;
    }

    /**
     * The on scrolled implementation when scroll is enough the callback will be informed.
     *
     * @param recyclerView the recycler view being scrolled
     * @param dx           the amount of horizontal scroll.
     * @param dy           the amount of vertical scroll.
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE) {
            callback.onScrolled();
        }
    }


}
