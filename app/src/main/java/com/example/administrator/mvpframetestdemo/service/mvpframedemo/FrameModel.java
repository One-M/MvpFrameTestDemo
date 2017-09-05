package com.example.administrator.mvpframetestdemo.service.mvpframedemo;

import com.example.administrator.mvpframetestdemo.service.BaseModel;
import com.example.administrator.mvpframetestdemo.service.RequestListener;

/**
 * Created by Poison_Y on 2017/8/8.
 * mail : yixiaoqiang1120@163.com
 */

public class FrameModel extends FrameContract.FirstFrameModel {
    @Override
    public void loadDataModel(String url, RequestListener requestListener) {
        requestListener.onSuccess(url);

    }
}
