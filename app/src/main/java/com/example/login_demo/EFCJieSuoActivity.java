package com.example.login_demo;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import base.BaseActivity;
import base.BaseBean;
import bean.CXEFCBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import presenter.CXEFCPresenter;
import untils.MyQusetUtils;
import untils.SPUtils;
import view.CXEFCView;

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
    @BindView(R.id.jiesuo_pb)
    ProgressBar jiesuoPb;
    @BindView(R.id.img_xlcsbq)
    ImageView imgXlcsbq;
    @BindView(R.id.img_zhiyxkbq)
    ImageView imgZhiyxkbq;
    @BindView(R.id.img_zhuanyxkbq)
    ImageView imgZhuanyxkbq;
    @BindView(R.id.img_zntbbq)
    ImageView imgZntbbq;
    private String token;
    private String data;

    private String majorresult;
    private String gender;
    private String classify;
    private String type;
    private ConnectionChangeReceiver myReceiver;
    private boolean net = true;
    private String sourceArea;
    private String stutype;
    private String ceeScore;
    private boolean flag=true;
    @Override
    public int getId() {
        return R.layout.activity_efcjie_suo;
    }

    @Override
    public void InIt() {

        rlZhiyxk.setEnabled(false);
        rlZhuanyxk.setEnabled(false);
        rlZntb.setEnabled(false);
        registerReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver();
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
                        jiesuoPb.setVisibility(View.GONE);
                        if (data.equals("0")) {
                            imgZhiyxkwjs.setVisibility(View.VISIBLE);
                            imgZhuanyxkwjs.setVisibility(View.VISIBLE);
                            imgZntbwjs.setVisibility(View.VISIBLE);
                            rlZhiyxk.setEnabled(false);
                            rlZhuanyxk.setEnabled(false);
                            rlZntb.setEnabled(false);
                        }
                        if (data.equals("1")) {
                            imgZhiyxkwjs.setVisibility(View.GONE);
                            imgZhuanyxkwjs.setVisibility(View.VISIBLE);
                            imgZntbwjs.setVisibility(View.VISIBLE);
                            imgXlcsbq.setImageResource(R.drawable.biaoywc);
                            rlZhiyxk.setEnabled(true);
                            rlZhuanyxk.setEnabled(false);
                            rlZntb.setEnabled(false);
                        }
                        if (data.equals("2")) {
                            imgZhiyxkwjs.setVisibility(View.GONE);
                            imgZhuanyxkwjs.setVisibility(View.GONE);
                            imgZntbwjs.setVisibility(View.VISIBLE);
                            imgXlcsbq.setImageResource(R.drawable.biaoywc);
                            imgZhiyxkbq.setImageResource(R.drawable.biaoywc);
                            rlZhiyxk.setEnabled(true);
                            rlZhuanyxk.setEnabled(true);
                            rlZntb.setEnabled(false);
                        }
                        if (data.equals("3")) {
                            imgZhiyxkwjs.setVisibility(View.GONE);
                            imgZhuanyxkwjs.setVisibility(View.GONE);
                            imgXlcsbq.setImageResource(R.drawable.biaoywc);
                            imgZhiyxkbq.setImageResource(R.drawable.biaoywc);
                            imgZhuanyxkbq.setImageResource(R.drawable.biaoywc);
                            rlZhiyxk.setEnabled(true);
                            rlZhuanyxk.setEnabled(true);

                            MyQusetUtils.getInstance().getQuestInterface().gettime(token)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeWith(new DisposableSubscriber<BaseBean<String>>() {
                                        @Override
                                        public void onNext(BaseBean<String> stringBaseBean) {
                                            if (stringBaseBean.code == 0) {
                                                String data = stringBaseBean.data;
                                                int i = Integer.parseInt(data);

                                                if (i < 0) {
                                                    imgZntbwjs.setVisibility(View.GONE);
                                                    rlZntb.setEnabled(true);
                                                    imgZntbbq.setImageResource(R.drawable.biaoywc);
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
                        if (data.equals("4")) {
                            imgZhiyxkwjs.setVisibility(View.GONE);
                            imgZhuanyxkwjs.setVisibility(View.GONE);
                            imgZntbwjs.setVisibility(View.GONE);
                            imgXlcsbq.setImageResource(R.drawable.biaoywc);
                            imgZhiyxkbq.setImageResource(R.drawable.biaoywc);
                            imgZhuanyxkbq.setImageResource(R.drawable.biaoywc);
                            imgZntbbq.setImageResource(R.drawable.biaoywc);
                            rlZhiyxk.setEnabled(true);
                            rlZhuanyxk.setEnabled(true);
                            rlZntb.setEnabled(true);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                        MyQusetUtils.getInstance().getQuestInterface().hqjd(token)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableSubscriber<BaseBean<String>>() {
                                    @Override
                                    public void onNext(BaseBean<String> integerBaseBean) {
                                        data = integerBaseBean.data;
                                        jiesuoPb.setVisibility(View.GONE);
                                        if (data.equals("0")) {
                                            imgZhiyxkwjs.setVisibility(View.VISIBLE);
                                            imgZhuanyxkwjs.setVisibility(View.VISIBLE);
                                            imgZntbwjs.setVisibility(View.VISIBLE);
                                            rlZhiyxk.setEnabled(false);
                                            rlZhuanyxk.setEnabled(false);
                                            rlZntb.setEnabled(false);
                                        }
                                        if (data.equals("1")) {
                                            imgZhiyxkwjs.setVisibility(View.GONE);
                                            imgZhuanyxkwjs.setVisibility(View.VISIBLE);
                                            imgZntbwjs.setVisibility(View.VISIBLE);
                                            rlXlcs.setEnabled(true);
                                            rlZhiyxk.setEnabled(true);
                                            rlZhuanyxk.setEnabled(false);
                                            rlZntb.setEnabled(false);
                                        }
                                        if (data.equals("2")) {
                                            imgZhiyxkwjs.setVisibility(View.GONE);
                                            imgZhuanyxkwjs.setVisibility(View.GONE);
                                            imgZntbwjs.setVisibility(View.VISIBLE);
                                            rlXlcs.setEnabled(true);
                                            rlZhiyxk.setEnabled(true);
                                            rlZhuanyxk.setEnabled(true);
                                            rlZntb.setEnabled(false);
                                        }
                                        if (data.equals("3")) {
                                            imgZhiyxkwjs.setVisibility(View.GONE);
                                            imgZhuanyxkwjs.setVisibility(View.GONE);
                                            rlZhiyxk.setEnabled(true);
                                            rlZhuanyxk.setEnabled(true);
                                            rlXlcs.setEnabled(true);

                                            MyQusetUtils.getInstance().getQuestInterface().gettime(token)
                                                    .subscribeOn(Schedulers.io())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribeWith(new DisposableSubscriber<BaseBean<String>>() {
                                                        @Override
                                                        public void onNext(BaseBean<String> stringBaseBean) {
                                                            if (stringBaseBean.code == 0) {
                                                                String data = stringBaseBean.data;
                                                                int i = Integer.parseInt(data);
                                                                if (i < 0) {
                                                                    imgZntbwjs.setVisibility(View.GONE);
                                                                    rlZntb.setEnabled(true);
                                                                    imgZntbbq.setImageResource(R.drawable.biaoywc);
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
                                        if (data.equals("4")) {
                                            imgZhiyxkwjs.setVisibility(View.GONE);
                                            imgZhuanyxkwjs.setVisibility(View.GONE);
                                            imgZntbwjs.setVisibility(View.GONE);
                                            rlZhiyxk.setEnabled(true);
                                            rlZhuanyxk.setEnabled(true);
                                            rlZntb.setEnabled(true);
                                            rlXlcs.setEnabled(true);
                                            imgZntbbq.setImageResource(R.drawable.biaoywc);
                                        }

                                    }

                                    @Override
                                    public void onError(Throwable t) {
                                        Toast("请检查你的网络~~");
                                        imgZhiyxkwjs.setVisibility(View.VISIBLE);
                                        imgZhuanyxkwjs.setVisibility(View.VISIBLE);
                                        imgZntbwjs.setVisibility(View.VISIBLE);
                                        rlZhiyxk.setEnabled(false);
                                        rlZhuanyxk.setEnabled(false);
                                        rlZntb.setEnabled(false);
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
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
                if (data != null && net == true) {
                    Intent intent = new Intent(this, XlcsActivity.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                } else {
                    Toast("请检查您的网络");
                }
                break;
            case R.id.rl_zhiyxk:
                if (data != null && net == true) {
                    Intent intent1 = new Intent(this, ProfessionStarActivity.class);
                    intent1.putExtra("data", data);
                    startActivity(intent1);
                } else {
                    Toast("请检查您的网络");
                }

                break;
            case R.id.rl_zhuanyxk:
                if (data != null && net == true) {
                    Intent intent2 = new Intent(this, MajorStarActivity.class);
                    intent2.putExtra("data", data);
                    startActivity(intent2);
                } else {
                    Toast("请检查您的网络");
                }

                break;
            case R.id.rl_zntb:
                if (data != null && net == true) {
                    CXEFCPresenter cxefcPresenter=new CXEFCPresenter(new CXEFCView() {
                        @Override
                        public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
                            //考生省份
                            sourceArea = cxefcBeanBaseBean.data.getSourceArea();
                            //文理科
                            stutype = cxefcBeanBaseBean.data.getStutype();
                            //考生分数
                            ceeScore = cxefcBeanBaseBean.data.getCeeScore();
                            if(sourceArea!=null&&stutype!=null&&ceeScore!=null)
                            {
                                Intent intent=new Intent(EFCJieSuoActivity.this,AccurateActivity.class);
                                intent.putExtra("s2",sourceArea+"");
                                intent.putExtra("tbsubtype",stutype+"");
                                intent.putExtra("fen",ceeScore);
                                intent.putExtra("s1","0");
                                intent.putExtra("s4","");
                                intent.putExtra("s5","");
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void GetEFCResultfail(Throwable t) {
                        }
                    });
                    cxefcPresenter.CXEFCPresenter(token);
                }
                else
                {
                    Toast("请检查您的网络");
                }
                break;
        }
    }

   /* @Override
    public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
        rlZhuanyxk.setEnabled(true);
        if (cxefcBeanBaseBean.code == 0) {
            majorresult = cxefcBeanBaseBean.data.getJob();
            String testCode = cxefcBeanBaseBean.data.getTestCode();
            String[] split = testCode.split(",");
            String s = split[0];
            String[] split1 = s.split(":");
            String mbti = split1[0];
            String s1 = split[1];
            String[] split2 = s1.split(":");
            String hld = split2[0];
            gender = cxefcBeanBaseBean.data.getGender();
            String stutype = cxefcBeanBaseBean.data.getStutype();
            if (stutype.equals("文科")) {
                classify = "wen";
            } else {
                classify = "li";
            }
            String collegetype = cxefcBeanBaseBean.data.getCollegetype();
            if (collegetype.equals("本科")) {
                type = "0";
            } else {
                type = "1";
            }
            Intent intent2 = new Intent(this, MajorStarActivity.class);
            intent2.putExtra("data", data);
            intent2.putExtra("result", majorresult);
            intent2.putExtra("Hld", hld);
            intent2.putExtra("mbti", mbti);
            intent2.putExtra("gender", gender);
            intent2.putExtra("type", type);
            intent2.putExtra("classify", classify);
            startActivity(intent2);
        }

    }

    @Override
    public void GetEFCResultfail(Throwable t) {

    }*/

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
                    Toast("当前无网络");
                    net = false;

                } else {
                    net = true;
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


}
