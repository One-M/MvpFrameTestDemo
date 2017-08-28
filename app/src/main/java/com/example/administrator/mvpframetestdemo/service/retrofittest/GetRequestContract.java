package com.example.administrator.mvpframetestdemo.service.retrofittest;

import com.example.administrator.mvpframetestdemo.service.BaseModel;
import com.example.administrator.mvpframetestdemo.service.BasePresenter;
import com.example.administrator.mvpframetestdemo.service.BaseView;
import com.example.administrator.mvpframetestdemo.service.RequestListener;

/**
 * Created by Poison_Y on 2017/8/28.
 * mail : yixiaoqiang1120@163.com
 * description:
 */

public interface GetRequestContract {

    interface GetRequestContractView extends BaseView{
        void successLoad(Object o);
        void failLoad();
    }

    interface GetRequestContractModel extends BaseModel{
        void retrofitLoad(String url , RequestListener requestListener);
    }

    abstract class GetRequestContractPresenter extends BasePresenter<GetRequestContractModel , GetRequestContractView>{
        abstract void retrofitLoad(String url);
    }
}
