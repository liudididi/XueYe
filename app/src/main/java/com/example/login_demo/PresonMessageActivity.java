package com.example.login_demo;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.meg7.widget.CustomShapeImageView;
import com.weavey.loading.lib.LoadingLayout;

import base.BaseActivity;
import base.BaseBean;
import bean.MyUserBean;
import bean.UserBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import presenter.perfectMessagePresent;
import untils.MyQusetUtils;
import untils.NetUtil;
import untils.SPUtils;
import view.perfectMessageView;

public class PresonMessageActivity extends BaseActivity implements perfectMessageView {


    @BindView(R.id.preson_iv_back)
    ImageView presonIvBack;
    @BindView(R.id.preson_complie)
    ImageView presonComplie;
    @BindView(R.id.preson_title)
    RelativeLayout presonTitle;
    @BindView(R.id.preson_picon)
    CustomShapeImageView presonIcon;
    @BindView(R.id.preson_name)
    EditText presonName;
    @BindView(R.id.preson_six)
    TextView presonSix;
    @BindView(R.id.preson_type)
    TextView presonType;
    @BindView(R.id.preson_near)
    TextView presonNear;
    @BindView(R.id.preson_highschool)
    TextView presonHighschool;
    private ConnectionChangeReceiver myReceiver;
    private  String sex;
    private String area;
    private String city;
    private String province;
    private String grade;
    private String years;

    private presenter.perfectMessagePresent perfectMessagePresent;
    private String token;

    @Override
    public int getId() {
        return R.layout.activity_preson_message;
    }

    @Override
    public void InIt() {
        registerReceiver();
        perfectMessagePresent=new perfectMessagePresent(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        token = (String) SPUtils.get(MyApp.context, "token", "");
              MyQusetUtils.getInstance()
                .getQuestInterface().getUserinfo(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSubscriber<BaseBean<UserBean>>() {
                    @Override
                    public void onNext(BaseBean<UserBean> baseBean) {
                        if(baseBean.code==0){
                            UserBean data = baseBean.data;
                            MyUserBean.setUserBean(data);
                            if(data.getName()!=null){
                                String name = (String) data.getName();
                                presonName.setText(name);
                            }
                            if(data.getSex()!=null){
                              sex = (String) data.getSex();
                                presonSix.setText(sex);
                            }
                            if(data.getSex()!=null){
                                if(data.getSex().equals("女")){
                                    Glide.with(PresonMessageActivity.this).load(R.drawable.gril).into(presonIcon);
                                }else {
                                    Glide.with(PresonMessageActivity.this).load(R.drawable.boy).into(presonIcon);
                                }
                            }else {
                                Glide.with(PresonMessageActivity.this).load(R.drawable.boy).into(presonIcon);
                            }
                           if(data.getStutype()!=null){
                               presonType.setText(data.getStutype()+"");
                           }
                           if(data.getExamyear()!=null){
                               years= (String) data.getExamyear();
                               presonNear.setText(years);
                           }
                           if(data.getArea()!=null){
                               province = (String) data.getProvince();
                               city = (String) data.getCity();
                               area = (String) data.getArea();
                               grade = (String) data.getGrade();
                               presonHighschool.setText(province + city + area);
                           }
                        }else {
                            Toast.makeText(MyApp.context,"token超时，请重新登录",Toast.LENGTH_SHORT);
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        Toast("获取信息失败，请稍后重试");
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick({R.id.preson_iv_back, R.id.preson_complie, R.id.preson_icon,R.id.preson_six})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.preson_iv_back:

                perfectMessagePresent.modifyUserinfoMoble(province, city, area, null, grade, presonName.getText().toString(), sex, years, null, false, token);
                break;
            case  R.id.preson_six:
                View viewe = LayoutInflater.from(PresonMessageActivity.this).inflate(R.layout.dialog_sex, null);
                final AlertDialog dialog = new AlertDialog.Builder(PresonMessageActivity.this)
                        .setView(viewe).show();
                final TextView pro_nan = viewe.findViewById(R.id.pro_nan);
                final TextView pro_nv = viewe.findViewById(R.id.pro_nv);
                final TextView pro_queding = viewe.findViewById(R.id.pro_queding);
                if(sex.equals("男")){
                    pro_nan.setBackgroundResource(R.drawable.bg_subject3);
                    pro_nv.setBackgroundResource(R.drawable.bg_subject2);
                }else {
                    pro_nan.setBackgroundResource(R.drawable.bg_subjectbai);
                    pro_nv.setBackgroundResource(R.drawable.bg_subjectselect);
                }
                pro_nv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pro_nan.setBackgroundResource(R.drawable.bg_subjectbai);
                        pro_nv.setBackgroundResource(R.drawable.bg_subjectselect);
                        sex="女";
                    }
                });
                pro_queding.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presonSix.setText(sex);
                        dialog.dismiss();
                    }
                });
                pro_nan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pro_nan.setBackgroundResource(R.drawable.bg_subject3);
                        pro_nv.setBackgroundResource(R.drawable.bg_subject2);
                        sex="男";

                    }
                });
                break;
            case R.id.preson_complie:
                if(NetUtil.isNetworkAvailable(this)==false){
                    Toast.makeText(this, "当前无网络", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent(this,perfectMessageActivity.class);
                break;
            case R.id.preson_icon:

                break;
        }
    }



    public void registerReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //设置网络状态提示布局的状态
//无网的时候，无网提示的展示View展示出来
//重新联接上网络时，自动加载数据
//这个是onresume中实现了数据的刷新，即是，网络连接后，重新拉取数据
        myReceiver = new ConnectionChangeReceiver() {
            @Override
            public void changeNetStatus(boolean flag) {
                //设置网络状态提示布局的状态
                if (flag) {

                } else {
                    //有网
                   onResume();

                }
            }
        };
        this.registerReceiver(myReceiver, filter);
    }

    public void unregisterReceiver() {
        if (myReceiver != null) {
            this.unregisterReceiver(myReceiver);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    @Override
    public void UserinfoSuccess(String msg) {
        SPUtils.put(MyApp.context, "name", presonName.getText().toString());
        finish();
    }

    @Override
    public void UserinfoFail(String msg) {
           Toast("当前无网络");
              finish();
    }
}
