package com.example.administrator.mvpframetestdemo.service;

import java.lang.ref.WeakReference;

/**
 * Created by：XQyi on 2017/8/7
 * Use: MVP 抽调的Presenter基类（在这里持有 M 和 V 层）
 */
public abstract class BasePresenter<M , V> {

    /**Presenter层仍然要持有M，V的强引用*/
    public M iModel;
    public V iView;
    public WeakReference<V> iViewRef;

    /**对两个对象进行赋值*/
    public void attachModelView(M model , V view){

        iViewRef = new WeakReference<V>(view);
        this.iModel = model;
        this.iView = getView();

    }

    public V getView(){
        if (isAttach()) {
            return iViewRef.get();
        }else{
            return null;
        }
    }

    public void onDettach(){
        if (iViewRef != null) {
            iViewRef.clear();
            iViewRef = null;
        }
    }

    public boolean isAttach(){
        return null != iViewRef && null != iViewRef.get();
    }


}
