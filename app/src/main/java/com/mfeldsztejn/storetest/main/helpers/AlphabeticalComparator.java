package com.mfeldsztejn.storetest.main.helpers;

import com.mfeldsztejn.storetest.dtos.Article;

import java.util.Comparator;

/**
 * An alphabetical comparator for articles
 */
public class AlphabeticalComparator implements Comparator<Article> {

    /**
     * Should sort ascending or descending
     */
    private boolean ascending;

    /**
     * A constructor for the comparator
     *
     * @param ascending should sort ascending by default
     */
    public AlphabeticalComparator(boolean ascending) {
        this.ascending = ascending;
    }

    /**
     * The compare implementation
     *
     * @param a1 the first article
     * @param a2 the second article
     * @return the return value of the article comparisons
     */
    @Override
    public int compare(Article a1, Article a2) {
        int value;
        if (ascending) {
            value = a1.compareTo(a2);
        } else {
            value = a2.compareTo(a1);
        }
        return value;
    }

    /**
     * Toggle if should be ascending or descending
     */
    public void toggle() {
        ascending = !ascending;
    }
}
