package com.mfeldsztejn.storetest.repositories;

import com.mfeldsztejn.storetest.dtos.Article;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * The interface for the article api that will be used by retrofit
 */
public interface ArticleApi {

    /**
     * Get all the articles from the service
     *
     * @param page the page we are in
     * @return an observable that can be subscribed to
     */
    @GET("articles/{page}/")
    Observable<List<Article>> getAllArticles(@Path("page") int page);
}
