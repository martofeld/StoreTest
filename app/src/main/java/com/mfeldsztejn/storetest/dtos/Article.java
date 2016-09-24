package com.mfeldsztejn.storetest.dtos;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mfeldsztejn on 9/22/16.
 */

public class Article implements Comparable<Article> {
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String imageUrl;

    public Article() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int compareTo(@NonNull Article article) {
        return name.compareTo(article.getName());
    }
}
