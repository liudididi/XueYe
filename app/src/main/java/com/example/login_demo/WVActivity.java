package com.example.login_demo;

import android.content.Intent;
import android.webkit.WebView;

import base.BaseActivity;
import butterknife.BindView;

public class WVActivity extends BaseActivity {


    @BindView(R.id.wv)
    WebView wv;

    @Override
    public int getId() {
        return R.layout.activity_wv;
    }

    @Override
    public void InIt() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        wv.loadUrl(url);
    }


}
