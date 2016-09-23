package com.mfeldsztejn.storetest.repositories;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mfeldsztejn on 9/22/16.
 */

public class ApiManager {
    public static final String BASE_URL = "http://10.0.3.2:3000";
    private static Retrofit retrofit;

    private ApiManager() {
    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            buildRetrofit();
        }
        return retrofit;
    }

    private static void buildRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
