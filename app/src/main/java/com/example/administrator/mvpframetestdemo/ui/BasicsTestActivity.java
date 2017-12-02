package com.example.administrator.mvpframetestdemo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.example.administrator.mvpframetestdemo.R;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/12/1.
 */

public class BasicsTestActivity extends FragmentActivity implements View.OnClickListener {

    private String strs = "str";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basics_activity);

        findViewById(R.id.basics_string_logA).setOnClickListener(this);
        findViewById(R.id.basics_string_logB).setOnClickListener(this);
        findViewById(R.id.basics_handler).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.basics_string_logA:
                logStringTest();
                break;
            case R.id.basics_string_logB:
                logStringTestYS();
                break;
            case R.id.basics_handler:
                handlerStaticTest();
                break;
        }
    }

    private static class MyHandler extends Handler{
        private final WeakReference<BasicsTestActivity> mActivity;

        public MyHandler(BasicsTestActivity activity) {
            mActivity = new WeakReference<BasicsTestActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            BasicsTestActivity basicsTestActivity = mActivity.get();
            if (basicsTestActivity != null) {
                switch (msg.what){
                    case 0:
                        Log.d("qwer==>> " , "static_handler>> 进来了 " + basicsTestActivity.strs);

                        break;
                }
            }
        }
    }
    private final MyHandler myHandler = new MyHandler(this);
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            Log.d("qwer==>> " , "static_handler");
            myHandler.sendEmptyMessage(0);
        }
    };

    private void handlerStaticTest(){
        myHandler.postDelayed(mRunnable , 1000*5);
        finish();
    }

   /* private final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    private void handlerTest(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("qwer==>> " , "handle_run");
            }
        },1000*60);
        Log.d("qwer==>>" , "handler_over");
        finish();
    }*/

    private static String str = "str";
    private void logStringTest(){

        String x = new String("firstString");
        Log.d("qwer==x1>> " , str);
        change(str);
        Log.d("qwer==x2>> " , str);
        System.out.println(str);
    }

    public void change(String x) {
        x = "even";
        Log.d("qwer==x*y>> " , x);
    }
    private void logStringTestYS(){
        StringBuilder sb = new StringBuilder("secondString");
//        String y = "secondString";
        Log.d("qwer==y1>> " , sb.toString());
        change(sb.toString());
        Log.d("qwer==y2>> " , sb.toString());
        System.out.println(sb);
    }
}
