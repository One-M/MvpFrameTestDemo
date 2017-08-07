package com.example.administrator.mvpframetestdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.administrator.mvpframetestdemo.R;

/**
 * Created by：XQyi on 2017/8/7 18:32
 * Use:
 */
public class FrameAct extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_activity);

        // url = "https://api.douban.com/v2/book/search?q=金瓶梅&tag=&start=0&count=1";
        TextView frameView = (TextView) findViewById(R.id.frame_actview);
    }
}
