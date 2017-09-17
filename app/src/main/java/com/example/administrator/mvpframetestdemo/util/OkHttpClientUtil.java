package com.example.administrator.mvpframetestdemo.util;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        //拦截http请求进行监控  ps:一个典型应用场景是所有http请求需要加上api key,在Retrofit2之前，可以通过RequestInterceptor实现：
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("" , "").build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });

        return httpClient.build();
    }

    public static Retrofit getRetrofit(String currUrl){
        Retrofit retrofit = new Retrofit.Builder()
//                            .baseUrl("http://fanyi.youdao.com/")
                            .baseUrl(currUrl)
                            .client(OkHttpClientUtil.getOkHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();

        return retrofit;
    }

//    private static Retrofit create() {
//        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//        builder.readTimeout(10, TimeUnit.SECONDS);
//        builder.connectTimeout(9, TimeUnit.SECONDS);
//
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addInterceptor(interceptor);
//        }
//
//        return new Retrofit.Builder().baseUrl(ENDPOINT)
//                .client(builder.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//    }
    
}
