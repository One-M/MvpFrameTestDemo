package com.example.administrator.mvpframetestdemo;

import android.app.Application;

/**
 * Created by：XQyi on 2017/9/17 17:21
 * Use:
 */
public class MyApplication extends Application {

    private static MyApplication myAppContext;

    public static MyApplication getMyAppContext() {
        return myAppContext;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        myAppContext = this;
    }
}
