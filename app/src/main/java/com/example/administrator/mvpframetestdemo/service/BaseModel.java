package com.example.administrator.mvpframetestdemo.service;

import com.example.administrator.mvpframetestdemo.util.OkHttpClientUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by：XQyi on 2017/8/7
 * Use: 所有model的基类，用于在P层中持有，在对应的Contract合约类中定义方法实现
 */
public abstract class BaseModel {

    private static final int DEFAULT_TIME = 10;    //默认超时时间
    private final long RETRY_TIMES = 1;   //重订阅次数
    protected Retrofit mRetrofit;

    public BaseModel(){

        if (mRetrofit == null) {
//            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//            builder.readTimeout(DEFAULT_TIME , TimeUnit.SECONDS);
//            builder.connectTimeout(DEFAULT_TIME , TimeUnit.SECONDS);
//            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
//            OkHttpClient okHttpClient = builder.build();

//            mRetrofit = new Retrofit.Builder()
//                    .baseUrl("http://fanyi.youdao.com/")
//                    .client(OkHttpClientUtil.getOkHttpClient())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build();
            String url = "http://m.1332255.com:81";
            mRetrofit = OkHttpClientUtil.getRetrofit(url);
        }

    }
    protected <T> void toSubscribe(Observable<T> observable, Observer<T> observer){

         observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .timeout(DEFAULT_TIME , TimeUnit.SECONDS)
//                .retry(RETRY_TIMES)
//                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
//                        return null;
//                    }
//                })
                .subscribe(observer);

    }
}
