package com.example.administrator.mvpframetestdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.administrator.mvpframetestdemo.R;
import com.example.administrator.mvpframetestdemo.ui.fragment.ConstraintLayTestFrag;

/**
 * Created by XQyi on 2018/2/22.
 * Use:小测试统一入口activity
 */

public class TestActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lay);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction =manager.beginTransaction();
        ConstraintLayTestFrag constraintLayTestFrag = ConstraintLayTestFrag.newInstance();
        transaction.add(R.id.test_fragment , constraintLayTestFrag);
        transaction.commit();
    }
}
