package com.example.administrator.mvpframetestdemo.service.retrofittest;

import com.example.administrator.mvpframetestdemo.retrofitrealize.GetRequestInterface;
import com.example.administrator.mvpframetestdemo.retrofitrealize.bean.TranslationPost;
import com.example.administrator.mvpframetestdemo.service.RequestListener;
import com.example.administrator.mvpframetestdemo.util.OkHttpClientUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Poison_Y on 2017/8/28.
 * mail : yixiaoqiang1120@163.com
 * description:
 */

public class GetRequestModel extends GetRequestContract.GetRequestContractModel {

    @Override
    public void retrofitLoad(String url, RequestListener requestListener) {
        //to retrofit load
//        request(requestListener);
//        retrofitRx(requestListener);
        retrofitRx2(requestListener);

    }

    private void request(final RequestListener requestListener){
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://fy.iciba.com/")
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClientUtil.getOkHttpClient())
                .build();

        //创建 网络请求接口 的实例
        GetRequestInterface getRequestInterface = retrofit.create(GetRequestInterface.class);
//        //对 发送请求 进行封装
//        //Call<Translation> call = getRequestInterface.getCall();
//        Call<Translation> call = getRequestInterface.httpGetCall();
//        //发送网络请求(异步)
//        call.enqueue(new Callback<Translation>() {
//            //请求成功时回调
//            @Override
//            public void onResponse(Call<Translation> call, Response<Translation> response) {
//                System.out.println("请求成功");
//                requestListener.onSuccess(response.body());
//            }
//            //请求失败时回调
//            @Override
//            public void onFailure(Call<Translation> call, Throwable t) {
//                System.out.println("连接失败");
//                requestListener.onError();
//            }
//        });

        Call<TranslationPost> callPost = getRequestInterface.postCall("you are my hero!");

        callPost.enqueue(new Callback<TranslationPost>() {
            @Override
            public void onResponse(Call<TranslationPost> call, Response<TranslationPost> response) {
                System.out.println("请求成功 ==>> " + response.body().getTranslateResult().get(0).get(0).getTgt());
                requestListener.onSuccess(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onFailure(Call<TranslationPost> call, Throwable t) {
                System.out.println("连接失败");
                requestListener.onError();
            }
        });
    }

    private void retrofitRx(final RequestListener requestListener){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(0 , TimeUnit.SECONDS);
        builder.connectTimeout(0 , TimeUnit.SECONDS);
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        OkHttpClient okHttpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://fanyi.youdao.com/")
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
        //创建 网络请求接口 的实例
        GetRequestInterface getRequestInterface = retrofit.create(GetRequestInterface.class);
        getRequestInterface.postRxCall("you are my hero!")
                           .subscribeOn(Schedulers.io())
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe(new Observer<TranslationPost>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {
                                   //为请求提供一个取消的手段
                               }
                               @Override
                               public void onNext(@NonNull TranslationPost translationPost) {
                                   //请求成功
                                   System.out.println("请求成功 ==>> " + translationPost.getTranslateResult().get(0).get(0).getTgt());
                                   requestListener.onSuccess(translationPost.getTranslateResult().get(0).get(0).getTgt());
                               }
                               @Override
                               public void onError(@NonNull Throwable e) {
                                   //请求出错
                                   System.out.println("请求失败");
                                   requestListener.onError();
                               }
                               @Override
                               public void onComplete() {
                                   //请求完成
                               }
                           });
    }


    private void retrofitRx2(final RequestListener requestListener){

        GetRequestInterface getRequestInterface = mRetrofit.create(GetRequestInterface.class);
        Observable<TranslationPost> observable = getRequestInterface.getBookDatas("hei you are my hero!");
        toSubscribe(observable, new Observer<TranslationPost>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                //为请求提供一个取消的手段
            }

            @Override
            public void onNext(@NonNull TranslationPost translationPost) {
                //请求成功
                System.out.println("请求成功 ==>> " + translationPost.getTranslateResult().get(0).get(0).getTgt());
                requestListener.onSuccess(translationPost.getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                //请求出错
                System.out.println("请求失败");
                requestListener.onError();
            }

            @Override
            public void onComplete() {
                //请求完成
            }
        });
    }
}
