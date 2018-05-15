package com.example.login_demo;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class BuyEFCActivity extends BaseActivity {

    @BindView(R.id.buyefc_iv_back)
    ImageView buyefcIvBack;



    @Override
    public int getId() {
        return R.layout.activity_buy_efc;
    }

    @Override
    public void InIt() {

    }

    @OnClick({R.id.buyefc_iv_back, R.id.tv_kaiqi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buyefc_iv_back:
                finish();
                break;
            case R.id.tv_kaiqi:
                Intent intent = new Intent(this, EFCJieSuoActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



}
