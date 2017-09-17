package com.example.administrator.mvpframetestdemo.service.retrofittest;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mvpframetestdemo.R;
import com.example.administrator.mvpframetestdemo.service.BaseFragment;
import com.example.administrator.mvpframetestdemo.util.ToastUtil;
import com.example.administrator.mvpframetestdemo.util.ToastUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Poison_Y on 2017/8/28.
 * mail : yixiaoqiang1120@163.com
 * description:
 */

public class GetRequestFragment extends BaseFragment<GetRequestPresenter , GetRequestModel> implements GetRequestContract.GetRequestContractView, View.OnClickListener {


    private View mView;
    private TextView mDetailsTxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.first_fragment_lay , container , false);
        initView();
        return mView;
    }

    private void initView(){
        TextView mFirstDatas = mView.findViewById(R.id.first_datas);
        mFirstDatas.setOnClickListener(this);
        mView.findViewById(R.id.rxjava_data).setOnClickListener(this);

        mView.findViewById(R.id.toast_utils).setOnClickListener(this);

        mDetailsTxt = mView.findViewById(R.id.retrofit_data);
    }

    private void initData(){
        iPresenter.retrofitLoad("");
    }

    @Override
    public void overrideOnCreate() {

    }

    @Override
    public void successLoad(Object obj) {
        String data = (String)obj;
        mDetailsTxt.setText(data);
    }

    @Override
    public void failLoad() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.first_datas:
                initData();
                break;
            case R.id.rxjava_data:
//                rxJavaTest8();
                rxJavaFlowable1();
                break;
            case R.id.toast_utils:
                IToast();
                break;
        }
    }

    /**原生*/
    private void ptotogenesisToast(){
        Log.d("toast == " , "makeText");
        Toast.makeText(getActivity(), "makeText", Toast.LENGTH_SHORT).show();
    }

    private void UtilsToast(){
//        ToastUtils.showToast(getContext() , "UtilsToast" , Toast.LENGTH_SHORT);

        ToastUtils.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(getContext() , "UtilsToastHandler" , Toast.LENGTH_SHORT);
            }
        });
    }
    private void IToast(){
//        IToast.show("我是自定义的toast");
        ToastUtil toastUtil = new ToastUtil();
        toastUtil.Short(getContext() , "自定义message字体、背景色").setToastColor(Color.WHITE , getResources().getColor(R.color.yellow_verify_code)).show();

        /**自定义布局*/
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.i_toast_view,null);
//        new ToastUtil(getContext() , view  , Toast.LENGTH_SHORT).show();
    }

    private String TAG = "RxJavaLog==>> ";
    private void rxTest1(){
        //创建 上游Observable
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建 下游Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG , "subscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG , "onNext" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG , "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG , "onComplete");
            }
        };
        //订阅 subscribe
        observable.subscribe(observer);

    }
    private void rxJavaTest2(){
        //链式编程
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG , "subscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG , "onNext" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG , "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG , "onComplete");
            }
        });
    }
    private void rxJavaTest3(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG , "Consumer_onNext" + integer);
            }
        });
    }
    /**线程切换*/
    private void rxJavaTest4(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Log.d(TAG, "After subscribeOn(io), current thread is: " + Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Consumer<Integer>() {
              @Override
              public void accept(Integer integer) throws Exception {
                  Log.d(TAG , "Consumer_onNext" + integer);
                  Log.d(TAG, "After observeOn(main), current thread is : " + Thread.currentThread().getName());
              }
          });
    }
    /**flatMap(无序) + concatMap(有序)*/
    private void rxJavaTest5(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onNext(6);
            }
        })
        .concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10 , TimeUnit.MILLISECONDS);
            }
        })
//         .flatMap(new Function<Integer, ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
//                final List<String> list = new ArrayList<>();
//                for (int i = 0; i < 3; i++) {
//                    list.add("I am value " + integer);
//                }
//                return Observable.fromIterable(list).delay(10 , TimeUnit.MILLISECONDS);
//            }
//        })
          .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }
    /**zip*/
    private void rxJavaTest6(){

        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Thread.sleep(1000);

                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Thread.sleep(1000);

                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Thread.sleep(1000);

                Log.d(TAG, "emit 4");
                emitter.onNext(4);
//                Thread.sleep(1000);

                Log.d(TAG, "emit complete1");
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.io());/**这里的Observable如果不指定线程（observable1 和 observable1在同一线程里）
                                            zip时 同一个线程里执行代码有先后顺序 会先执行observable1再执行observable2
                                            这样无法做到同事执行，so：指定线程执行observable 实现同步*/

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "emit A");
                emitter.onNext("A");
                Thread.sleep(1000);

                Log.d(TAG, "emit B");
                emitter.onNext("B");
                Thread.sleep(1000);

                Log.d(TAG, "emit C");
                emitter.onNext("C");
                Thread.sleep(1000);

                Log.d(TAG, "emit complete2");
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }
            @Override
            public void onNext(String value) {
                Log.d(TAG, "onNext: " + value);
            }
            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }
        });
    }
    /**OOM手动处理*/
    private void rxJavaTest7(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0;  ; i++) { //无限循环（制造OOM）
                    e.onNext(i);
                    /**处理方法一 每次发送完事件延时2秒*/
                    Thread.sleep(2000);
                }
            }
        }).subscribeOn(Schedulers.io())
                /**处理方法二 filter 过滤器（只有被100整除的数才通过）*/
//            .filter(new Predicate<Integer>() {
//                @Override
//                public boolean test(@NonNull Integer integer) throws Exception {
//                    return integer % 100 == 0;
//                }
//            })
                /**处理方法三 sample 每隔指定的时间就从上游中取出一个事件发送给下游*/
//                .sample(2 , TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                });

    }
    /**解决OOM 的zip*/
    private void rxJavaTest8(){
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0;  ; i++) { //无限循环（制造OOM）
                    e.onNext(i);
                    Thread.sleep(2000);  //发送事件之后延时2秒
                }
            }
        }).subscribeOn(Schedulers.io())
//                .sample(2 , TimeUnit.SECONDS)
                ;
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("A");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
                return integer + s;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.w(TAG, throwable);
                    }
                });
    }

    /**Flowable
     * 上游变成了Flowable, 下游变成了Subscriber, 但是水管之间的连接还是通过subscribe()*/
    private void rxJavaFlowable1() {
        //基本的用法
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> integer) throws Exception {
                Log.d(TAG, "emit 1");
                integer.onNext(1);
                Log.d(TAG, "emit 2");
                integer.onNext(2);
                Log.d(TAG, "emit 3");
                integer.onNext(3);
                Log.d(TAG, "emit complete");
                integer.onComplete();
            }
        }, BackpressureStrategy.ERROR);//这个参数用来选择背压

        Subscriber<Integer> downstream  = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe");
                s.request(Long.MAX_VALUE);  //注意这句代码 Flowable在设计的时候采用了一种新的思路也就是响应式拉取(这里必须告诉上游需要的数据条数)
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.w(TAG, "onError: ", t);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
        flowable.subscribe(downstream);
    }
}
