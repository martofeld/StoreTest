package com.mfeldsztejn.storetest.dtos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mfeldsztejn on 9/22/16.
 */

public class Article {
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
}
