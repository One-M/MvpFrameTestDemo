package com.example.administrator.mvpframetestdemo.service;

/**
 * Created by：XQyi on 2017/8/7
 * Use: 作为Model的回调接口，作用是把数据传递给P层。
 */
public interface RequestListener<E> {

    /**
     * 请求成功回调
     * @param result
     */
    void onSuccess(E result);

    /**
     * 请求失败回调
     */
    void onError();
}
