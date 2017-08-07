package com.example.administrator.mvpframetestdemo.service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by：XQyi on 2017/8/7
 * Use: mvp-V 的base类（V层的base ）fragment
 */
public abstract class BaseFragment<T extends BasePresenter, M extends BaseModel> extends Fragment {

    public T iPresenter;
    public M iModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //内部获取第一个类型参数的真实类型  ，反射new出对象
        iPresenter = CreateUtil.getT(this , 0);
        //内部获取第二个类型参数的真实类型  ，反射new出对象
        iModel = CreateUtil.getT(this , 1);
        //使得P层绑定M层和V层，持有M和V的引用
        iPresenter.attachModelView(iModel , this);

        overrideOnCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter.onDettach();
    }

    /**子类Fragment实现, 在子类里实现onCreate；不用则不做操作*/
    public abstract void overrideOnCreate();
}
