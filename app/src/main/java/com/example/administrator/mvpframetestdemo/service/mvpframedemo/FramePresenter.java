package com.example.administrator.mvpframetestdemo.service.mvpframedemo;

import com.example.administrator.mvpframetestdemo.service.RequestListener;

/**
 * Created by Poison_Y on 2017/8/8.
 * mail : yixiaoqiang1120@163.com
 */

public class FramePresenter extends FrameContract.FirstFramePresenter{

    @Override
    void loadDataPresenter(String url) {
        iModel.loadDataModel(url, new RequestListener() {
            @Override
            public void onSuccess(Object result) {
//                String datas = (String) result;
                iView.loadSuccessShow(result);
            }

            @Override
            public void onError() {

            }
        });

    }
}
