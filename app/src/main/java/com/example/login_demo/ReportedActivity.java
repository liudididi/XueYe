package com.example.login_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import base.BaseActivity;
import base.BaseBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;
import untils.SPUtils;

//填报志愿表
public class ReportedActivity extends BaseActivity {

    @BindView(R.id.reported_iv_back)
    ImageView reportedIvBack;
    @BindView(R.id.reported_primary)
    TextView reportedPrimary;
    @BindView(R.id.reported_advanced)
    TextView reportedAdvanced;
    @BindView(R.id.reported_accurate)
    TextView reportedAccurate;
    @BindView(R.id.re_pb)
    ProgressBar rePb;
    private String token;

    @Override
    public int getId() {
        return R.layout.activity_reported;
    }

    @Override
    public void InIt() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        token = (String) SPUtils.get(MyApp.context, "token", "");
    }

    @OnClick({R.id.reported_iv_back, R.id.reported_primary, R.id.reported_advanced, R.id.reported_accurate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reported_iv_back:
                finish();
                break;
            //初级志愿表
            case R.id.reported_primary:
                intent(this, PrimaryActivity.class);
                break;
            //高级志愿表
            case R.id.reported_advanced:
                intent(this, AdvancedActivity.class);
                break;
            //精选志愿表
            case R.id.reported_accurate:
                if (token.length() < 4) {
                    Toast("用户未登录");
                    return;
                }
                rePb.setVisibility(View.VISIBLE);
                reportedAccurate.setEnabled(false);
                MyQusetUtils.getInstance().getQuestInterface().jzjudge(token)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSubscriber<BaseBean<String>>() {
                            @Override
                            public void onNext(BaseBean<String> stringBaseBean) {
                                if (stringBaseBean.code == 0) {
                                    reportedAccurate.setEnabled(true);
                                    rePb.setVisibility(View.GONE);
                                    if (stringBaseBean.data.equals("0")) {
                                        Intent intent = new Intent(ReportedActivity.this, BuyEFCActivity.class);
                                        intent.putExtra("price", "698");
                                        startActivity(intent);
                                    } else {
                                        MyQusetUtils.getInstance().getQuestInterface().gettime(token)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribeWith(new DisposableSubscriber<BaseBean<String>>() {
                                                    @Override
                                                    public void onNext(BaseBean<String> stringBaseBean) {
                                                        rePb.setVisibility(View.GONE);
                                                        if (stringBaseBean.code == 0) {
                                                            reportedAccurate.setEnabled(true);
                                                            Intent intent = new Intent(ReportedActivity.this, ComlitEFCActivity.class);
                                                            startActivity(intent);
                                                        } else {
                                                            reportedAccurate.setEnabled(true);
                                                            rePb.setVisibility(View.GONE);
                                                            Intent intent = new Intent(ReportedActivity.this, EFCJieSuoActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }

                                                    @Override
                                                    public void onError(Throwable t) {
                                                        reportedAccurate.setEnabled(true);
                                                        rePb.setVisibility(View.GONE);
                                                        Toast("网络较差,请重试~");
                                                    }

                                                    @Override
                                                    public void onComplete() {

                                                    }
                                                });
                                    }
                                }
                            }

                            @Override
                            public void onError(Throwable t) {
                                reportedAccurate.setEnabled(true);
                                rePb.setVisibility(View.GONE);
                                Toast("网络较差,请重试~");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
        }
    }


}
