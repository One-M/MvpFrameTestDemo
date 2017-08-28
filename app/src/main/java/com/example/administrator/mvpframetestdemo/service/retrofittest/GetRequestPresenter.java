package com.example.administrator.mvpframetestdemo.service.retrofittest;

import com.example.administrator.mvpframetestdemo.retrofitrealize.bean.Translation;
import com.example.administrator.mvpframetestdemo.service.RequestListener;

/**
 * Created by Poison_Y on 2017/8/28.
 * mail : yixiaoqiang1120@163.com
 * description:
 */

public class GetRequestPresenter extends GetRequestContract.GetRequestContractPresenter{
    @Override
    void retrofitLoad(String url) {
        iModel.retrofitLoad(url, new RequestListener() {
            @Override
            public void onSuccess(Object result) {
                iView.successLoad(result);
            }

            @Override
            public void onError() {
                iView.failLoad();
            }
        });
    }
}
