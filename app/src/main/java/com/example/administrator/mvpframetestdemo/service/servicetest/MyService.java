package com.example.administrator.mvpframetestdemo.service.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by：XQyi on 2017/10/12 16:18
 * Use:
 */
public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService " , "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService " , "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService " , "onDestroy");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
