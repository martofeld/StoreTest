package com.mfeldsztejn.storetest.main.helpers;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by mfeldsztejn on 9/24/16.
 */

public class PagingScrollListener extends RecyclerView.OnScrollListener {

    private static final int PAGE_SIZE = 100;

    private final PagingScrollCallback callback;

    public PagingScrollListener(PagingScrollCallback callback) {
        this.callback = callback;
    }

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
