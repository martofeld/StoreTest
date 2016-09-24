package com.mfeldsztejn.storetest.repositories;

import com.mfeldsztejn.storetest.dtos.Article;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mfeldsztejn on 9/22/16.
 */

public interface ArticleApiInterface {
    @GET("articles/{page}/")
    Observable<List<Article>> getAllArticles(@Path("page") int page);
}
