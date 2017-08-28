package com.example.administrator.mvpframetestdemo.service.retrofittest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mvpframetestdemo.R;
import com.example.administrator.mvpframetestdemo.retrofitrealize.bean.Translation;
import com.example.administrator.mvpframetestdemo.service.BaseFragment;

/**
 * Created by Poison_Y on 2017/8/28.
 * mail : yixiaoqiang1120@163.com
 * description:
 */

public class GetRequestFragment extends BaseFragment<GetRequestPresenter , GetRequestModel> implements GetRequestContract.GetRequestContractView, View.OnClickListener {


    private View mView;
    private TextView mDetailsTxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.first_fragment_lay , container , false);
        initView();
        return mView;
    }

    private void initView(){
        TextView mFirstDatas = mView.findViewById(R.id.first_datas);
        mFirstDatas.setOnClickListener(this);

        mDetailsTxt = mView.findViewById(R.id.retrofit_data);
    }

    private void initData(){
        iPresenter.retrofitLoad("");
    }

    @Override
    public void overrideOnCreate() {

    }

    @Override
    public void successLoad(Object obj) {
        String data = (String)obj;
        mDetailsTxt.setText(data);
    }

    @Override
    public void failLoad() {

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
