package com.ludowica.goodlooks.services;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApp.context = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getAppContext() {
        return MyApp.context;
    }

}
