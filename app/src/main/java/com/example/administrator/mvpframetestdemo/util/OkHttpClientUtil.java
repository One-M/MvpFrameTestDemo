package com.example.administrator.mvpframetestdemo.util;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Poison_Y on 2017/8/28.
 * mail : yixiaoqiang1120@163.com
 * description:
 */

public class OkHttpClientUtil {

    public static OkHttpClient getOkHttpClient(){
        //日志显示级别
        HttpLoggingInterceptor.Level bodyLevel = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("yxq","OkHttp====Message:"+message);
            }
        });
        //定制OkHttp
        httpLoggingInterceptor.setLevel(bodyLevel);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClient.addInterceptor(httpLoggingInterceptor);

        return httpClient.build();
    }
}
