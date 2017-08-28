package com.example.administrator.mvpframetestdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.example.administrator.mvpframetestdemo.R;
import com.example.administrator.mvpframetestdemo.service.retrofittest.GetRequestFragment;

/**
 * Created by Poison_Y on 2017/8/28.
 * mail : yixiaoqiang1120@163.com
 * description:
 */

public class RetrofitActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//启用拓展窗口（无标题）
        setContentView(R.layout.frame_activity);

        // url = "https://api.douban.com/v2/book/search?q=金瓶梅&tag=&start=0&count=1";
//        TextView frameView = (TextView) findViewById(R.id.frame_actview);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        GetRequestFragment fragment = new GetRequestFragment();
        fragmentTransaction.add(R.id.data_fragment, fragment);
        fragmentTransaction.commit();
    }
}
