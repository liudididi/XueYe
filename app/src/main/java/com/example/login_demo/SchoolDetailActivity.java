package com.example.login_demo;

import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.weavey.loading.lib.LoadingLayout;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import base.BaseApi;
import base.BaseBean;
import bean.CollerSchoolBean;
import butterknife.BindView;
import butterknife.OnClick;
import fragment.School_Brochures;
import fragment.School_Enroll;
import fragment.School_Summary;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.FlowLayout;
import untils.MyQusetUtils;
import untils.SPUtils;

public class SchoolDetailActivity extends BaseActivity {

    @BindView(R.id.schoold_name)
    TextView schooldName;

    @BindView(R.id.schoold_iv_back)
    ImageView schooldIvBack;
    @BindView(R.id.schoold_address)
    TextView schooldAddress;


    @BindView(R.id.schoold_lq)
    TextView schooldLq;
    @BindView(R.id.schoold_jj)
    TextView schooldJj;
    @BindView(R.id.schoold_zsjz)
    TextView schooldZsjz;
    @BindView(R.id.school_fl)
    FrameLayout schoolFl;
    @BindView(R.id.schoold_collect)
    ImageView schooldCollect;
    @BindView(R.id.schooldIvdoor)
    ImageView shoolidIvdoor;

    @BindView(R.id.schoold_icon)
    ImageView schoolicon;
    @BindView(R.id.lodiing)
    LoadingLayout lodiing;
    @BindView(R.id.schoold_rl)
    RelativeLayout schooldRl;
    @BindView(R.id.schoold_v)
    View schooldV;
    @BindView(R.id.schoold_rl2)
    RelativeLayout schooldRl2;
    @BindView(R.id.school_flow)
    FlowLayout schoolFlow;

    public  static  boolean isefc;
    private School_Summary school_summary;
    private Fragment currentFragment;
    private String token;
    public static String schoolname;
    private DisposableSubscriber<BaseBean<List<CollerSchoolBean>>> disposableSubscriber;
    public static String shsd = null;
    public static String bhsd = null;
    private School_Enroll school_enroll;
    private School_Brochures school_brochures;
    private ConnectionChangeReceiver myReceiver;
    private static RelativeLayout rl_wenben;
    private static TextView tv_wenben;
    public static View view_gl;
    public static ProgressBar pb;

    @Override
    public int getId() {
        return R.layout.activity_school_detail;
    }
    @Override
    public void InIt() {
        isefc=getIntent().getBooleanExtra("EFC",false);
        rl_wenben = findViewById(R.id.rl_wenben);
        tv_wenben = findViewById(R.id.tv_wenben);
        view_gl = findViewById(R.id.view_gl);
        pb = findViewById(R.id.pb);

        rl_wenben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_wenben.setVisibility(View.GONE);
            }
        });
        loadingLayout = lodiing;
        registerReceiver();
        initfragment();
        token = (String) SPUtils.get(MyApp.context, "token", "");
        schoolname = getIntent().getStringExtra("schoolname");
        schooldName.setText(schoolname);
        info();
        switchFragment(school_enroll).commitAllowingStateLoss();
    }
    private void info() {
    }
    public static void ff(String s) {
        rl_wenben.setVisibility(View.VISIBLE);
        if (s != null) {
            tv_wenben.setText(s);
        } else {
            tv_wenben.setText("暂无数据");
        }
    }
    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.school_fl, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }
    private void initfragment() {
        school_summary = new School_Summary();
        school_enroll = new School_Enroll();
        school_brochures = new School_Brochures();
    }
    @OnClick({R.id.schoold_iv_back, R.id.schoold_lq, R.id.schoold_jj, R.id.schoold_zsjz, R.id.schoold_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.schoold_iv_back:
                finish();
                break;
            case R.id.schoold_lq:
                schooldLq.setTextColor(Color.WHITE);
                schooldLq.setBackgroundResource(R.drawable.back_schooldlan);


                schooldJj.setTextColor(Color.BLACK);
                schooldJj.setBackgroundResource(R.drawable.back_schoold);

                schooldZsjz.setTextColor(Color.BLACK);
                schooldZsjz.setBackgroundResource(R.drawable.back_schoold);
                switchFragment(school_enroll).commitAllowingStateLoss();

                break;
            case R.id.schoold_jj:

                schooldLq.setTextColor(Color.BLACK);
                schooldLq.setBackgroundResource(R.drawable.back_schoold);


                schooldJj.setTextColor(Color.WHITE);
                schooldJj.setBackgroundResource(R.drawable.back_schooldlan);

                schooldZsjz.setTextColor(Color.BLACK);
                schooldZsjz.setBackgroundResource(R.drawable.back_schoold);


                switchFragment(school_summary).commitAllowingStateLoss();
                break;
            case R.id.schoold_zsjz:


                schooldLq.setTextColor(Color.BLACK);
                schooldLq.setBackgroundResource(R.drawable.back_schoold);


                schooldJj.setTextColor(Color.BLACK);
                schooldJj.setBackgroundResource(R.drawable.back_schoold);

                schooldZsjz.setTextColor(Color.WHITE);
                schooldZsjz.setBackgroundResource(R.drawable.back_schooldlan);

                switchFragment(school_brochures).commitAllowingStateLoss();

                break;


            case R.id.schoold_collect:
                collect();
                break;
        }
    }


    public void collect() {
        if (token == null || token.length() < 4) {
            Toast("用户未登录");
            return;
        }
        MyQusetUtils.getInstance()
                .getQuestInterface().collect("0", schoolname, token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSubscriber<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        Toast.makeText(MyApp.context, baseBean.msg, Toast.LENGTH_SHORT).show();
                        if (baseBean.msg.equals("收藏成功")) {
                            Glide.with(SchoolDetailActivity.this).load(R.drawable.collect_yes).into(schooldCollect);
                        } else {
                            Glide.with(SchoolDetailActivity.this).load(R.drawable.collect_none).into(schooldCollect);
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

    public void iscollect() {
        disposableSubscriber = MyQusetUtils.getInstance()
                .getQuestInterface().getchoolisscollet(schoolname, token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSubscriber<BaseBean<List<CollerSchoolBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<CollerSchoolBean>> listBaseBean) {
                        if (listBaseBean.code == 0) {
                            List<CollerSchoolBean> data = listBaseBean.data;
                            if (data != null && data.size() > 0) {
                                 List<String> strlist=new ArrayList<>();
                                String collectionTime = data.get(0).getCollectionTime();
                                if (collectionTime != null && collectionTime.length() > 2) {
                                    Glide.with(SchoolDetailActivity.this).load(R.drawable.collect_yes).into(schooldCollect);
                                } else {
                                    Glide.with(SchoolDetailActivity.this).load(R.drawable.collect_none).into(schooldCollect);
                                }
                                String two = data.get(0).getTwo();
                                if (two != null && two.length() > 1) {

                                    strlist.add(two);
                                }
                                String nine = data.get(0).getNine();
                                if (nine != null && nine.length() > 1) {

                                    strlist.add(nine);
                                }

                                String door = data.get(0).getDoor();

                                if (door != null) {
                                    Glide.with(SchoolDetailActivity.this).load(BaseApi.ImgApi + door).into(shoolidIvdoor);
                                }

                                String url = data.get(0).getUrl();
                                if (url != null) {
                                   // Glide.with(SchoolDetailActivity.this).load(BaseApi.ImgApi + url).into(schoolicon);
                                    Glide.with(SchoolDetailActivity.this).load(BaseApi.ImgApi + url).asBitmap().centerCrop().into(new BitmapImageViewTarget(schoolicon) {
                                        @Override
                                        protected void setResource(Bitmap resource) {
                                            RoundedBitmapDrawable circularBitmapDrawable =
                                                    RoundedBitmapDrawableFactory.create(SchoolDetailActivity.this.getResources(), resource);
                                            circularBitmapDrawable.setCircular(true);
                                            schoolicon.setImageDrawable(circularBitmapDrawable);
                                        }
                                    });

                                }
                                String preeminentPlan = data.get(0).getPreeminentPlan();
                                if (preeminentPlan != null && preeminentPlan.length() > 1) {
                                    strlist.add(preeminentPlan);
                                }
                                String defenseStudent = data.get(0).getDefenseStudent();
                                if (defenseStudent != null && defenseStudent.length() > 1) {
                                    strlist.add(defenseStudent);
                                }

                                String address = data.get(0).getAddress();
                                if (address != null && address.length() > 1) {
                                    schooldAddress.setText(address);
                                } else {
                                    schooldAddress.setText("地址暂无数据");
                                }

                                String graduate = data.get(0).getRecruit();
                                if (graduate != null && graduate.length() > 1) {
                                    strlist.add(graduate);
                                }

                                if(strlist.size()==0){
                                    strlist.add("暂无数据");
                                }
                                schoolFlow.setschoolListData(strlist);
                                String shuoshi = data.get(0).getShuoshi();
                                if (shuoshi != null) {
                                    shsd = shuoshi;
                                }
                                String boshi = data.get(0).getBoshi();
                                if (boshi != null) {
                                    bhsd = boshi;
                                }
                            }

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
    protected void onDestroy() {
        super.onDestroy();
        if (disposableSubscriber != null) {
            disposableSubscriber.dispose();
        }
        unregisterReceiver();
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
                    loadingLayout.setStatus(LoadingLayout.No_Network);
                    schoolFl.setVisibility(View.GONE);
                } else {
                    schoolFl.setVisibility(View.VISIBLE);
                    schooldLq.setTextColor(Color.WHITE);
                    schooldLq.setBackgroundResource(R.drawable.back_schooldlan);

                    schooldJj.setTextColor(Color.BLACK);
                    schooldJj.setBackgroundResource(R.drawable.back_schoold);

                    schooldZsjz.setTextColor(Color.BLACK);
                    schooldZsjz.setBackgroundResource(R.drawable.back_schoold);
                    initfragment();
                    token = (String) SPUtils.get(MyApp.context, "token", "");
                    schoolname = getIntent().getStringExtra("schoolname");
                    schooldName.setText(schoolname);
                    iscollect();
                    info();
                    switchFragment(school_enroll).commitAllowingStateLoss();
                    loadingLayout.setStatus(LoadingLayout.Success);
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



}
