package com.example.administrator.mvpframetestdemo.service.mvpframedemo;

import com.example.administrator.mvpframetestdemo.service.BaseModel;
import com.example.administrator.mvpframetestdemo.service.BasePresenter;
import com.example.administrator.mvpframetestdemo.service.BaseView;
import com.example.administrator.mvpframetestdemo.service.RequestListener;

/**
 * Created by：XQyi on 2017/8/7 18:40
 * Use:
 */
public interface FrameContract {

    interface FirstFrameView<T> extends BaseView{
        void loadSuccessShow(T t);
        void loadFailShow();
        void loadTokenLose();
    }

    abstract class FirstFrameModel extends BaseModel{

        abstract void loadDataModel(String url , RequestListener requestListener);

    }
    abstract class FirstFramePresenter extends BasePresenter<FirstFrameModel , FirstFrameView>{

        abstract void loadDataPresenter(String url);
    }
}
