package com.example.login_demo;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import base.BaseBean;
import bean.CXEFCBean;
import bean.StartFl;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import presenter.CXEFCPresenter;
import presenter.StartFlPresent;
import untils.MyQusetUtils;
import untils.SPUtils;
import view.CXEFCView;
import view.StartFView;


public class startfenleiActivity extends BaseActivity implements StartFView, CXEFCView {
    @BindView(R.id.pro_iv_back)
    ImageView proIvBack;
    @BindView(R.id.start_imgfl)
    ImageView startImgfl;
    @BindView(R.id.pro_startv)
    TextView proStartv;
    @BindView(R.id.pro_startv1)
    TextView proStartv1;
    @BindView(R.id.starttv1)
    TextView starttv1;
    @BindView(R.id.starttv2)
    TextView starttv2;
    @BindView(R.id.starttv3)
    TextView starttv3;
    @BindView(R.id.starttv4)
    TextView starttv4;
    @BindView(R.id.starttv5)
    TextView starttv5;
    @BindView(R.id.starttv6)
    TextView starttv6;
    @BindView(R.id.starttv7)
    TextView starttv7;
    @BindView(R.id.starttv8)
    TextView starttv8;
    @BindView(R.id.starttv9)
    TextView starttv9;
    @BindView(R.id.starttv10)
    TextView starttv10;
    @BindView(R.id.starttv25)
    TextView starttv25;
    @BindView(R.id.starttv11)
    TextView starttv11;
    @BindView(R.id.starttv12)
    TextView starttv12;
    @BindView(R.id.starttv13)
    TextView starttv13;
    @BindView(R.id.starttv15)
    TextView starttv15;
    @BindView(R.id.starttv16)
    TextView starttv16;
    @BindView(R.id.starttv14)
    TextView starttv14;
    @BindView(R.id.starttv17)
    TextView starttv17;
    @BindView(R.id.starttv18)
    TextView starttv18;
    @BindView(R.id.starttv19)
    TextView starttv19;
    @BindView(R.id.starttv20)
    TextView starttv20;
    @BindView(R.id.starttv21)
    TextView starttv21;
    @BindView(R.id.starttv24)
    TextView starttv24;
    @BindView(R.id.starttv23)
    TextView starttv23;
    @BindView(R.id.starttv22)
    TextView starttv22;
    @BindView(R.id.pro_yes)
    Button proYes;
    @BindView(R.id.pro_rmzy)
    TextView proRmzy;
    @BindView(R.id.pro_shl)
    TextView proShl;
    @BindView(R.id.pro_lxgc)
    TextView proLxgc;
    @BindView(R.id.pro_ysrw)
    TextView proYsrw;
    @BindView(R.id.pro_zrl)
    TextView proZrl;
    @BindView(R.id.horizontalscroll)
    HorizontalScrollView horizontalscroll;
    @BindView(R.id.img_zjt)
    ImageView imgZjt;
    private int min;
    private int max;
    private List<TextView> tvlist;
    public static List<String> fenlieanswerlist = new ArrayList<>();
    private List<String> newlist;
    private String type;
    private String classify;
    private String fenlei = "hot";
    private StartFlPresent startFlPresent;
    private String token;
    private String result = "";
    private CXEFCPresenter xcpresent;
    private int i1;
    private String data;
    private String hld;
    private String mbti;
    private AlphaAnimation anim01;
    private String gender;

    @Override
    public int getId() {
        return R.layout.activity_startfenlei;
    }

    @Override
    public void InIt() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int widthtPixels = dm.widthPixels;
        min = widthtPixels / 27;
        max = (int) (min * 1.5);
        data = getIntent().getStringExtra("data");

        i1 = Integer.parseInt(data);
        inittvlist();
        //动画
        anim01 = new AlphaAnimation(2.f, 0.1f);

        //动画持续的时间

        anim01.setDuration(1500);

        //设置动画持续的次数

        anim01.setRepeatCount(AlphaAnimation.INFINITE);

//应该做什么当它到达尽头。应用此设置只在重复计数大于0或无限。默认为重启。

        anim01.setRepeatMode(AlphaAnimation.REVERSE);

        //开启动画

        imgZjt.startAnimation(anim01);
        type = getIntent().getStringExtra("type");
        classify = getIntent().getStringExtra("classify");
        gender = getIntent().getStringExtra("gender");
        startFlPresent = new StartFlPresent(this);

        startFlPresent.getStartfl(classify, type, fenlei);
        xcpresent = new CXEFCPresenter(this);
        newlist = new ArrayList<>();
        fenlieanswerlist = new ArrayList<>();
        if (i1 >= 2) {
            for (int i = 0; i < tvlist.size(); i++) {
                tvlist.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, min);
                tvlist.get(i).setTextColor(Color.WHITE);
                tvlist.get(i).setText("");
            }
        } else {
            for (int i = 0; i < tvlist.size(); i++) {
                tvlist.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, min);
                tvlist.get(i).setTextColor(Color.WHITE);
                tvlist.get(i).setText("");
                final int finalI = i;
                tvlist.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int textSize = (int) tvlist.get(finalI).getTextSize();
                        if (textSize == min) {
                            tvlist.get(finalI).setTextSize(TypedValue.COMPLEX_UNIT_PX, max);
                            tvlist.get(finalI).setTextColor(Color.parseColor("#fffaa3"));
                            fenlieanswerlist.add(tvlist.get(finalI).getText().toString());
                        } else {
                            tvlist.get(finalI).setTextSize(TypedValue.COMPLEX_UNIT_PX, min);
                            tvlist.get(finalI).setTextColor(Color.WHITE);
                            fenlieanswerlist.remove(tvlist.get(finalI).getText().toString());
                        }
                    }
                });
            }
        }

    }


    private void inittvlist() {
        tvlist = new ArrayList<>();
        tvlist.add(starttv1);
        tvlist.add(starttv2);
        tvlist.add(starttv3);
        tvlist.add(starttv4);
        tvlist.add(starttv5);
        tvlist.add(starttv6);
        tvlist.add(starttv7);
        tvlist.add(starttv8);
        tvlist.add(starttv9);
        tvlist.add(starttv10);
        tvlist.add(starttv11);
        tvlist.add(starttv12);
        tvlist.add(starttv13);
        tvlist.add(starttv14);
        tvlist.add(starttv15);
        tvlist.add(starttv16);
        tvlist.add(starttv17);
        tvlist.add(starttv18);
        tvlist.add(starttv19);
        tvlist.add(starttv20);
        tvlist.add(starttv21);
        tvlist.add(starttv22);
        tvlist.add(starttv23);
        tvlist.add(starttv24);
        tvlist.add(starttv25);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startFlPresent.onDestory();
        if(anim01!=null){
            anim01.cancel();
        }
    }

    @OnClick({R.id.pro_iv_back, R.id.start_imgfl, R.id.pro_yes, R.id.pro_rmzy, R.id.pro_shl, R.id.pro_lxgc, R.id.pro_ysrw, R.id.pro_zrl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pro_iv_back:
                finish();
                break;
            case R.id.start_imgfl:
                Intent intent1 = new Intent(this, ProfessionStarActivity.class);
                intent1.putExtra("data", data);
                startActivity(intent1);
                finish();
                break;
            case R.id.pro_yes:
                if (i1 >= 2) {
                    Intent intent = new Intent(startfenleiActivity.this, MajorStarActivity.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                    finish();
                } else {
                    if (fenlieanswerlist.size() > 0) {
                        View viewe = LayoutInflater.from(this).inflate(R.layout.tankuang_zyts, null);
                        final AlertDialog dialog = new AlertDialog.Builder(this)
                                .setView(viewe).show();
                        TextView dilog_queren=  viewe.findViewById(R.id.dilog_queren);
                        TextView dilog_quxiao=  viewe.findViewById(R.id.dilog_quxiao);
                        dilog_queren.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tijiao();
                            }
                        });
                        dilog_quxiao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    } else {
                        Toast("试试左右滑动,选择你喜欢的职业吧！");
                    }
                }

                break;
            case R.id.pro_rmzy:
                fenlei = "hot";
                startFlPresent.getStartfl(classify, type, fenlei);
                horizontalscroll.scrollTo(0, 0);
                proRmzy.setTextColor(getResources().getColor(R.color.dialog_attention_explain_title));
                proShl.setTextColor(Color.WHITE);
                proLxgc.setTextColor(Color.WHITE);
                proYsrw.setTextColor(Color.WHITE);
                proZrl.setTextColor(Color.WHITE);
                break;
            case R.id.pro_shl:
                fenlei = "society";
                startFlPresent.getStartfl(classify, type, fenlei);
                horizontalscroll.scrollTo(0, 0);
                proShl.setTextColor(getResources().getColor(R.color.dialog_attention_explain_title));
                proRmzy.setTextColor(Color.WHITE);
                proLxgc.setTextColor(Color.WHITE);
                proYsrw.setTextColor(Color.WHITE);
                proZrl.setTextColor(Color.WHITE);
                break;
            case R.id.pro_lxgc:
                fenlei = "engineer";
                startFlPresent.getStartfl(classify, type, fenlei);
                horizontalscroll.scrollTo(0, 0);
                proLxgc.setTextColor(getResources().getColor(R.color.dialog_attention_explain_title));
                proShl.setTextColor(Color.WHITE);
                proRmzy.setTextColor(Color.WHITE);
                proYsrw.setTextColor(Color.WHITE);
                proZrl.setTextColor(Color.WHITE);
                break;
            case R.id.pro_ysrw:
                fenlei = "art";
                startFlPresent.getStartfl(classify, type, fenlei);
                horizontalscroll.scrollTo(0, 0);
                proYsrw.setTextColor(getResources().getColor(R.color.dialog_attention_explain_title));
                proLxgc.setTextColor(Color.WHITE);
                proShl.setTextColor(Color.WHITE);
                proRmzy.setTextColor(Color.WHITE);
                proZrl.setTextColor(Color.WHITE);
                break;
            case R.id.pro_zrl:
                fenlei = "nature";
                startFlPresent.getStartfl(classify, type, fenlei);
                horizontalscroll.scrollTo(0, 0);
                proZrl.setTextColor(getResources().getColor(R.color.dialog_attention_explain_title));
                proYsrw.setTextColor(Color.WHITE);
                proLxgc.setTextColor(Color.WHITE);
                proShl.setTextColor(Color.WHITE);
                proRmzy.setTextColor(Color.WHITE);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        token = (String) SPUtils.get(MyApp.context, "token", "");
    }


    private void tijiao() {

        for (int i = 0; i < fenlieanswerlist.size(); i++) {
            if (i == fenlieanswerlist.size() - 1) {
                result += fenlieanswerlist.get(i);
            } else {
                result += fenlieanswerlist.get(i) + ",";
            }
        }
        DisposableSubscriber<BaseBean> disposableSubscriber =
                MyQusetUtils.getInstance().getQuestInterface().tjzy(result, token)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSubscriber<BaseBean>() {
                            @Override
                            public void onNext(BaseBean baseBean) {
                                if (baseBean.code == 0) {
                                    Intent intent = new Intent(startfenleiActivity.this, GuoDuActivity.class);
                                    intent.putExtra("data", "2");
                                    intent.putExtra("result", result);
                                    intent.putExtra("Hld", hld);
                                    intent.putExtra("mbti", mbti);
                                    intent.putExtra("gender", gender);
                                    intent.putExtra("type", type);
                                    intent.putExtra("classify", classify);
                                    startActivity(intent);
                                    finish();
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


    @Override
    public void Stratjobsuccess(List<StartFl> list) {
        if (list != null && list.size() > 0) {
            newlist.clear();
            if (list.size() > 25) {
                for (int i = 0; i < 25; i++) {
                    newlist.add(list.get(i).getJob());
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    newlist.add(list.get(i).getJob());
                }
            }
            if (i1 >= 2) {
                xcpresent.CXEFCPresenter(token);
            } else if (i1 == 1) {
                xcpresent.CXEFCPresenter(token);
            } else {
                for (int i = 0; i < newlist.size(); i++) {
                    if (newlist.get(i) != null) {
                        tvlist.get(i).setText(newlist.get(i));
                    }
                    if (fenlieanswerlist.contains(newlist.get(i))) {
                        tvlist.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, max);
                        tvlist.get(i).setTextColor(Color.parseColor("#fffaa3"));
                    } else {
                        tvlist.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, min);
                        tvlist.get(i).setTextColor(Color.WHITE);
                    }
                }
            }

        }

    }

    @Override
    public void Stratjobfail(String msg) {

        startFlPresent.getStartfl(classify, type, fenlei);
    }

    @Override
    public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
        if (cxefcBeanBaseBean.data != null) {
            String testCode = cxefcBeanBaseBean.data.getTestCode();
            String[] split = testCode.split(",");
            String s = split[0];
            String[] split1 = s.split(":");
            mbti = split1[0];
            String s1 = split[1];
            String[] split2 = s1.split(":");
            hld = split2[0];

            if (i1 >= 2) {
                gender = cxefcBeanBaseBean.data.getGender();
                String stutype = cxefcBeanBaseBean.data.getStutype();
                if(stutype.equals("文科")){
                    classify="wen";
                }else {

                    classify="li";
                }
                String collegetype = cxefcBeanBaseBean.data.getCollegetype();
                if(collegetype.equals("本科")){
                    type="0";
                }else {
                    type="1";
                }
                result = cxefcBeanBaseBean.data.getJob();
                String[] split3 = result.split(",");
                for (String n : split3) {
                    fenlieanswerlist.add(n);
                }
                for (int i = 0; i < newlist.size(); i++) {
                    if (newlist.get(i) != null) {
                        tvlist.get(i).setText(newlist.get(i));
                    }
                    if (fenlieanswerlist.contains(newlist.get(i))) {
                        tvlist.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, max);
                        tvlist.get(i).setTextColor(Color.parseColor("#fffaa3"));
                    } else {
                        tvlist.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, min);
                        tvlist.get(i).setTextColor(Color.WHITE);
                    }
                }

            }else {
                for (int i = 0; i < newlist.size(); i++) {
                    if (newlist.get(i) != null) {
                        tvlist.get(i).setText(newlist.get(i));
                    }
                    if (fenlieanswerlist.contains(newlist.get(i))) {
                        tvlist.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, max);
                        tvlist.get(i).setTextColor(Color.parseColor("#fffaa3"));
                    } else {
                        tvlist.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, min);
                        tvlist.get(i).setTextColor(Color.WHITE);
                    }

                }

            }

        }

    }

    @Override
    public void GetEFCResultfail(Throwable t) {
        xcpresent.CXEFCPresenter(token);
    }


}
