package com.example.administrator.mvpframetestdemo.service.mvpframedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mvpframetestdemo.R;
import com.example.administrator.mvpframetestdemo.service.BaseFragment;

/**
 * Created by Poison_Y on 2017/8/23.
 * mail : yixiaoqiang1120@163.com
 */

public class FirstFragment extends BaseFragment<FramePresenter , FrameModel> implements FrameContract.FirstFrameView, View.OnClickListener {


    private View mView;
    private TextView mFirstDatas;
    private TextView mDetailsTxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.first_fragment_lay , container , false);
        initView();
        return mView;
    }

    private void initView(){
        mFirstDatas = mView.findViewById(R.id.first_datas);
        mFirstDatas.setOnClickListener(this);

        mDetailsTxt = mView.findViewById(R.id.first_data_details);
    }
    private  void initData(){
        String url = "https://api.douban.com/v2/book/search?q=金瓶梅&tag=&start=0&count=1";
        iPresenter.loadDataPresenter(url);
    }

    @Override
    public void overrideOnCreate() {

    }

    @Override
    public void loadSuccessShow(Object o) {
        String datas = (String) o;
        mDetailsTxt.setText(datas);
    }

    @Override
    public void loadFailShow() {

    }

    @Override
    public void loadTokenLose() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.first_datas:
                initData();
                break;
        }
    }
}
