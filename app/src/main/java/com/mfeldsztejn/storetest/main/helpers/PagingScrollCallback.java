package com.mfeldsztejn.storetest.main.helpers;

/**
 * A callback to inform the implementer of a paging scroll
 */
public interface PagingScrollCallback {
    /**
     * The recycler view scrolled enough to request a paging
     */
    void onScrolled();
}
