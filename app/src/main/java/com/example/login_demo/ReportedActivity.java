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
import untils.NetUtil;
import untils.SPUtils;

//填报志愿表
public class ReportedActivity extends BaseActivity {

    @BindView(R.id.reported_iv_back)
    ImageView reportedIvBack;
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

    @OnClick({R.id.reported_iv_back, R.id.reported_advanced, R.id.reported_accurate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reported_iv_back:
                finish();
                break;
          /*  //初级志愿表
            case R.id.reported_primary:
                if(NetUtil.isNetworkAvailable(this)){
                    intent(this, PrimaryActivity.class);
                }else {
                    Toast("当前无网络");
                }
                break;*/
            //高级志愿表
            case R.id.reported_advanced:

                if(NetUtil.isNetworkAvailable(this)){
                    intent(this, AdvancedActivity.class);
                }else {
                    Toast("当前无网络");
                }

                break;
            //精选志愿表
            case R.id.reported_accurate:
                if (token.length() < 4) {
                    Toast("用户未登录");
                    return;
                }
                if(NetUtil.isNetworkAvailable(this)==false){
                    Toast("当期无网络");
                    return;
                }
                rePb.setVisibility(View.VISIBLE);
                reportedAccurate.setEnabled(false);
                MyQusetUtils.getInstance().getQuestInterface().jzjudge(token)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .retry(2)
                        .subscribeWith(new DisposableSubscriber<BaseBean<String>>() {
                            @Override
                            public void onNext(BaseBean<String> stringBaseBean) {
                                reportedAccurate.setEnabled(true);
                                rePb.setVisibility(View.GONE);
                                if (stringBaseBean.code == 0) {
                                    if(stringBaseBean.data.equals("0")){
                                        SPUtils.put(MyApp.context,"EFCFX","本科");
                                    }else {
                                        SPUtils.put(MyApp.context,"EFCFX","专科");
                                    }
                                    rePb.setVisibility(View.GONE);
                                    Intent intent = new Intent(ReportedActivity.this, EFCJieSuoActivity.class);
                                    startActivity(intent);
                                }else {
                                    Intent intent = new Intent(ReportedActivity.this, Buy2Activity.class);
                                    intent.putExtra("price", "698");
                                    startActivity(intent);
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
