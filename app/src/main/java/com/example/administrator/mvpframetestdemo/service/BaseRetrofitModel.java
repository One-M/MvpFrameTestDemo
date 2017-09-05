package com.example.administrator.mvpframetestdemo.service;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by：XQyi on 2017/9/1 16:13
 * Use:
 */
public abstract class BaseRetrofitModel{

    private static final int DEFAULT_TIME = 10;    //默认超时时间
    private final long RETRY_TIMES = 1;   //重订阅次数
    protected Retrofit mRetrofit;

    public BaseRetrofitModel(){

        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(DEFAULT_TIME , TimeUnit.SECONDS);
            builder.connectTimeout(DEFAULT_TIME , TimeUnit.SECONDS);
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                        .baseUrl("http://fanyi.youdao.com/")
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
        }

    }
    protected <T> void toSubscribe(Observable<T> observable, Observer<T> observer){

        observable.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .timeout(DEFAULT_TIME , TimeUnit.SECONDS)
                  .retry(RETRY_TIMES)
                  .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                      @Override
                      public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                          return null;
                      }
                  })
                  .subscribe(observer);


    }
}
