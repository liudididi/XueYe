package com.example.login_demo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XGcsActivity extends BaseActivity {

    @BindView(R.id.pb1)
    ProgressBar pb1;
    @BindView(R.id.pb2)
    ProgressBar pb2;
    @BindView(R.id.pb3)
    ProgressBar pb3;
    @BindView(R.id.pb4)
    ProgressBar pb4;
    @BindView(R.id.pb5)
    ProgressBar pb5;
    @BindView(R.id.pb6)
    ProgressBar pb6;
    @BindView(R.id.pb7)
    ProgressBar pb7;
    @BindView(R.id.pb8)
    ProgressBar pb8;
    @BindView(R.id.xgguihua_iv_back)
    ImageView xgguihuaIvBack;

    @Override
    public int getId() {
        return R.layout.activity_xgcs;
    }

    @Override
    public void InIt() {
        String result = "INTP";
        String index1 = result.substring(0, 1);
        if (index1.equals("I")) {
            pb2.setProgress(35);
        } else {
            pb1.setProgress(100 - 35);
        }
        String index2 = result.substring(1, 2);
        if (index2.equals("N")) {
            pb4.setProgress(35);
        } else {
            pb3.setProgress(100 - 35);
        }

        String index3 = result.substring(2, 3);
        if (index3.equals("F")) {
            pb6.setProgress(35);
        } else {
            pb5.setProgress(100 - 35);
        }
        String index4 = result.substring(3, 4);
        if (index4.equals("P")) {
            pb8.setProgress(35);
        } else {
            pb7.setProgress(100 - 35);
        }


    }

    @OnClick(R.id.xgguihua_iv_back)
    public void onViewClicked() {
        finish();
    }
}
