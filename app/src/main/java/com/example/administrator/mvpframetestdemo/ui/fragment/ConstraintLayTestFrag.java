package com.example.administrator.mvpframetestdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mvpframetestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XQyi on 2018/2/22.
 * Use:constraintlayout 练习
 * ps:参考 https://juejin.im/post/5a041845f265da430a500824
 */

public class ConstraintLayTestFrag extends Fragment {

    @BindView(R.id.group)
    Group group;
    @BindView(R.id.button)
    Button button;

    private View mView;
    boolean showT = true;

    public static ConstraintLayTestFrag newInstance() {
        Bundle args = new Bundle();
        ConstraintLayTestFrag fragment = new ConstraintLayTestFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.constraint_lays, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                group.setVisibility(showT ? View.GONE : View.VISIBLE);
                showT = !showT;
            }
        });
    }
}
