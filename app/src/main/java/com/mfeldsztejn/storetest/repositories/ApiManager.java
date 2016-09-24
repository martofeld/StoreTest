package com.mfeldsztejn.storetest.repositories;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A manager for the api to ensure it is never null
 */
public class ApiManager {

    /**
     * The base url for the server
     */
    public static final String BASE_URL = "http://10.0.3.2:3000";
    /**
     * A retrofit instance, static since we only have one instance
     */
    private static Retrofit retrofit;

    /**
     * A private constructor to avoid an instance of this class
     */
    private ApiManager() {
    }

    /**
     * Obtain a retrofit instance, if null build it
     *
     * @return the retrofit instance
     */
    @NonNull
    public static Retrofit getInstance() {
        if (retrofit == null) {
            buildRetrofit();
        }
        return retrofit;
    }

    /**
     * Build the retrofit instance with the default configurations
     */
    private static void buildRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
