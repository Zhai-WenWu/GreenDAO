package com.example.administrator.greendaotest;

import android.app.Application;

/**
 * Created by Administrator on 2018/9/3.
 */

public class App extends Application {
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App getInstance() {
        return app;
    }
}
