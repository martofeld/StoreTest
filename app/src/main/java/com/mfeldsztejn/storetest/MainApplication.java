package com.mfeldsztejn.storetest;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by mfeldsztejn on 9/23/16.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Use the application context to avoid leaking memory
        Fresco.initialize(this);
    }
}
