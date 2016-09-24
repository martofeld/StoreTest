package com.mfeldsztejn.storetest;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * The main application implementation for one time operations
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Use the application context to avoid leaking memory
        Fresco.initialize(this);
    }
}
