package com.example.login_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuoDuActivity extends BaseActivity {


    @BindView(R.id.guodu_iv_back)
    ImageView guoduIvBack;
    @BindView(R.id.tv_kaiqi)
    TextView tvKaiqi;
    private String majorresult;
    private String data;
    private String hld;
    private String mbti;
    private String gender;
    private String type;
    private String classify;

    @Override
    public int getId() {
        return R.layout.activity_guo_du;
    }

    @Override
    public void InIt() {
        majorresult = getIntent().getStringExtra("result");
        data = getIntent().getStringExtra("data");
        hld = getIntent().getStringExtra("Hld");
        mbti = getIntent().getStringExtra("mbti");
        gender = getIntent().getStringExtra("gender");
        type = getIntent().getStringExtra("type");
        classify = getIntent().getStringExtra("classify");


    }


    @OnClick({R.id.guodu_iv_back, R.id.tv_kaiqi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guodu_iv_back:
                finish();
                break;
            case R.id.tv_kaiqi:
                Intent intent = new Intent(GuoDuActivity.this, MajorStarActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("result", majorresult);
                intent.putExtra("Hld", hld);
                intent.putExtra("mbti", mbti);
                intent.putExtra("gender", gender);
                intent.putExtra("type", type);
                intent.putExtra("classify", classify);
                startActivity(intent);
                finish();
                break;
        }
    }
}
