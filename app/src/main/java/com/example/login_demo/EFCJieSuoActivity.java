package com.example.login_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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

public class EFCJieSuoActivity extends BaseActivity {


    @BindView(R.id.jiesuo_iv_back)
    ImageView jiesuoIvBack;
    @BindView(R.id.img_xlcs)
    ImageView imgXlcs;
    @BindView(R.id.rl_xlcs)
    RelativeLayout rlXlcs;
    @BindView(R.id.img_zhiyxkjs)
    ImageView imgZhiyxkjs;
    @BindView(R.id.img_zhiyxkwjs)
    ImageView imgZhiyxkwjs;
    @BindView(R.id.rl_zhiyxk)
    RelativeLayout rlZhiyxk;
    @BindView(R.id.img_zhuanyxkjs)
    ImageView imgZhuanyxkjs;
    @BindView(R.id.img_zhuanyxkwjs)
    ImageView imgZhuanyxkwjs;
    @BindView(R.id.rl_zhuanyxk)
    RelativeLayout rlZhuanyxk;
    @BindView(R.id.img_zntbjs)
    ImageView imgZntbjs;
    @BindView(R.id.img_zntbwjs)
    ImageView imgZntbwjs;
    @BindView(R.id.rl_zntb)
    RelativeLayout rlZntb;
    private String token;
    private String data;
    @Override
    public int getId() {
        return R.layout.activity_efcjie_suo;
    }

    @Override
    public void InIt() {



    }

    @Override
    protected void onResume() {
        super.onResume();
        token = (String) SPUtils.get(MyApp.context, "token", "");
        MyQusetUtils.getInstance().getQuestInterface().hqjd(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<String>>() {

                    @Override
                    public void onNext(BaseBean<String> integerBaseBean) {
                        data = integerBaseBean.data;
                        if(data.equals("0")){
                            imgZhiyxkwjs.setVisibility(View.VISIBLE);
                            imgZhuanyxkwjs.setVisibility(View.VISIBLE);
                            imgZntbwjs.setVisibility(View.VISIBLE);
                            rlZhiyxk.setEnabled(false);
                            rlZhuanyxk.setEnabled(false);
                            rlZntb.setEnabled(false);
                        }
                        if(data.equals("1")){
                            imgZhiyxkwjs.setVisibility(View.GONE);
                            imgZhuanyxkwjs.setVisibility(View.VISIBLE);
                            imgZntbwjs.setVisibility(View.VISIBLE);


                            rlZhiyxk.setEnabled(true);
                            rlZhuanyxk.setEnabled(false);
                            rlZntb.setEnabled(false);
                        }
                        if(data.equals("2")){
                            imgZhiyxkwjs.setVisibility(View.GONE);
                            imgZhuanyxkwjs.setVisibility(View.GONE);
                            imgZntbwjs.setVisibility(View.VISIBLE);

                            rlZhiyxk.setEnabled(true);
                            rlZhuanyxk.setEnabled(true);
                            rlZntb.setEnabled(false);
                        }
                        if (data.equals("3")){
                            imgZhiyxkwjs.setVisibility(View.GONE);
                            imgZhuanyxkwjs.setVisibility(View.GONE);
                            imgZntbwjs.setVisibility(View.GONE);

                            rlZhiyxk.setEnabled(true);
                            rlZhuanyxk.setEnabled(true);
                            rlZntb.setEnabled(true);
                        }
                        if(data.equals("4")){
                            imgZhiyxkwjs.setVisibility(View.GONE);
                            imgZhuanyxkwjs.setVisibility(View.GONE);
                            imgZntbwjs.setVisibility(View.GONE);
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

    @OnClick({R.id.jiesuo_iv_back, R.id.rl_xlcs, R.id.rl_zhiyxk, R.id.rl_zhuanyxk, R.id.rl_zntb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jiesuo_iv_back:
                finish();
                break;
            case R.id.rl_xlcs:

                Intent intent=new Intent(this,XlcsActivity.class);
                intent.putExtra("data",data);


                break;
            case R.id.rl_zhiyxk:
                intent(this,ProfessionStarActivity.class);
                break;
            case R.id.rl_zhuanyxk:
                intent(this,MajorStarActivity.class);
                break;
            case R.id.rl_zntb:
                intent(this,Volunteer_ScreenActivity.class);
                break;
        }
    }
}
