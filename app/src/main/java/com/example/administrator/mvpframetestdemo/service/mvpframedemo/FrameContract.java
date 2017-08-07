package com.example.administrator.mvpframetestdemo.service.mvpframedemo;

import com.example.administrator.mvpframetestdemo.service.BaseModel;
import com.example.administrator.mvpframetestdemo.service.BasePresenter;
import com.example.administrator.mvpframetestdemo.service.BaseView;

/**
 * Created by：XQyi on 2017/8/7 18:40
 * Use:
 */
public class FrameContract {

    interface FrameView<T> extends BaseView{

    }

    interface FrameModel extends BaseModel{

    }
    abstract class FramePresenter extends BasePresenter<FrameModel , FrameView>{

    }
}
