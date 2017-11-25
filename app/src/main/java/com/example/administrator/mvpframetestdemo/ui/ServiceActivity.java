package com.example.administrator.mvpframetestdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.administrator.mvpframetestdemo.R;
import com.example.administrator.mvpframetestdemo.service.servicetest.MyService;

/**
 * Created by：XQyi on 2017/10/12 15:55
 * Use:
 */
public class ServiceActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_lay);

        findViewById(R.id.stop_service).setOnClickListener(this);
        findViewById(R.id.start_service).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_service:
                Intent startIntent = new Intent(this , MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this , MyService.class);
                stopService(stopIntent);
                break;
        }
    }
}
