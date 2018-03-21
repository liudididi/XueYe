package com.example.login_demo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import base.BaseActivity;
import base.BaseBean;
import bean.CXEFCBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;
import untils.SPUtils;

public class XlcsActivity extends BaseActivity {


    @BindView(R.id.xlcs_iv_back)
    ImageView xlcsIvBack;

    @BindView(R.id.lsbg_bt)
    Button lsbgBt;
    @BindView(R.id.re_buydi1)
    LinearLayout reBuydi1;
    @BindView(R.id.re_buydi2)
    LinearLayout reBuydi2;
    @BindView(R.id.xlcs_bt1)
    Button xlcsBt1;
    @BindView(R.id.xlcs_b2)
    Button xlcsB2;

    @Override
    public int getId() {
        return R.layout.activity_xlcs;
    }

    @Override
    public void InIt() {
        String data = getIntent().getStringExtra("data");
        int i = Integer.parseInt(data);
        if (i >= 3) {
            reBuydi2.setVisibility(View.VISIBLE);
            reBuydi1.setVisibility(View.GONE);
        }
        xlcsBt1.setEnabled(false);
        xlcsB2.setEnabled(false);
        String token = (String) SPUtils.get(MyApp.context, "token", "");
        MyQusetUtils.getInstance().getQuestInterface().jznumber(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.code == 0) {
                            xlcsBt1.setEnabled(true);
                            xlcsBt1.setBackground(getResources().getDrawable(R.drawable.back_capacity));

                            xlcsB2.setEnabled(true);
                            xlcsB2.setBackground(getResources().getDrawable(R.drawable.back_capacity));
                        } else {
                            xlcsBt1.setEnabled(false);
                            xlcsBt1.setBackground(getResources().getDrawable(R.drawable.back_capacityhui));

                            xlcsB2.setEnabled(false);
                            xlcsB2.setBackground(getResources().getDrawable(R.drawable.back_capacityhui));
                            Toast(baseBean.msg);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @OnClick({R.id.xlcs_iv_back, R.id.xlcs_bt1, R.id.xlcs_b2, R.id.lsbg_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xlcs_iv_back:
                finish();
                break;
            case R.id.lsbg_bt:
                intent(XlcsActivity.this, ComlitEFCActivity.class);
                finish();

                break;
            case R.id.xlcs_bt1:
                Intent intent = new Intent(XlcsActivity.this, AnswerActivity.class);
                intent.putExtra("ceshi", "MBTI");
                startActivity(intent);
                break;
            case R.id.xlcs_b2:
                Intent inten2 = new Intent(XlcsActivity.this, AnswerActivity.class);
                inten2.putExtra("ceshi", "MBTI");
                startActivity(inten2);
                break;
        }
    }



}
