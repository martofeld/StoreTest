package com.mfeldsztejn.storetest.dtos;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Representation of the article
 */
public class Article implements Comparable<Article> {
    /**
     * The name of the article
     */
    @SerializedName("name")
    private String name;
    /**
     * The relative url for the image
     */
    @SerializedName("url")
    private String imageUrl;

    /**
     * Get the name of the article
     *
     * @return the name of the article
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the article
     *
     * @param name the new name of the article
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the image url of the article
     *
     * @return the image url of the article
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Set the image url of the article
     *
     * @param imageUrl the new image url of the article
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Is the image url an absolute path
     *
     * @return if the image url is absolute
     */
    public boolean isImageUrlAbsolute() {
        return imageUrl.startsWith("http");
    }

    /**
     * The compare to implementation
     *
     * @param article an article to compare to
     * @return the compare value of the names
     */
    @SuppressFBWarnings("EQ_COMPARETO_USE_OBJECT_EQUALS")
    @Override
    public int compareTo(@NonNull Article article) {
        return name.compareTo(article.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals;
        if (obj instanceof Article) {
            Article article = (Article) obj;
            equals = article == this || article.getName().equals(this.name) && article.getImageUrl().equals(this.imageUrl);
        } else {
            equals = false;
        }

        return equals;

    }
}
