package com.mfeldsztejn.storetest.main.helpers;

import com.mfeldsztejn.storetest.dtos.Article;

import java.util.Comparator;

/**
 * Created by mfeldsztejn on 9/24/16.
 */

public class AlphabeticalComparator implements Comparator<Article> {

    private boolean ascending;

    public AlphabeticalComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(Article a1, Article a2) {
        if (ascending)
            return a1.compareTo(a2);
        else
            return a2.compareTo(a1);
    }

    public void toggle() {
        ascending = !ascending;
    }
}
