package com.example.login_demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.login_demo.wxapi.WXPayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import adapter.TuiJianAdapter;
import base.BaseActivity;
import base.BaseApi;
import base.BaseBean;
import bean.CXEFCBean;
import bean.TuiJianBean;
import bean.WeiXinBean;
import bean.XDingdanBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.CXEFCPresenter;
import presenter.PayPresent;
import presenter.TuiJianPresent;
import untils.Dianji2;
import untils.FlowLayout;
import untils.ListViewForScrollView;
import untils.SPUtils;
import view.CXEFCView;
import view.PayView;
import view.TuiJianView;
import zfbpay.AliPayResult;

public class TuiJianSchoolActivity extends BaseActivity implements TuiJianView {


    @BindView(R.id.tuijian_iv)
    ImageView tuijianIv;
    @BindView(R.id.tuijian_name)
    TextView tuijianName;
    @BindView(R.id.tuijian_zh)
    TextView tuijianZh;
    @BindView(R.id.tuijian_iv_back)
    ImageView tuijianIvBack;
    @BindView(R.id.rl_tjschool)
    RelativeLayout rlTjschool;
    @BindView(R.id.fl_tjly)
    FlowLayout flTjly;
    @BindView(R.id.ll_ling)
    LinearLayout llLing;
    @BindView(R.id.tv_ktefc)
    TextView tvKtefc;
    @BindView(R.id.tv_jixu)
    TextView tvJixu;
    @BindView(R.id.ll_two)
    LinearLayout llTwo;
    @BindView(R.id.tj_hour)
    TextView tjHour;
    @BindView(R.id.tj_minute)
    TextView tjMinute;
    @BindView(R.id.tj_miao)
    TextView tjMiao;
    @BindView(R.id.ll_san)
    LinearLayout llSan;
    @BindView(R.id.list_zy)
    ListViewForScrollView listZy;
    @BindView(R.id.ll_one)
    LinearLayout llOne;
    @BindView(R.id.tv_zyjh)
    TextView tvZyjh;
    private String schoolname;
    private TuiJianPresent tuiJianPresent;
    private Handler handler;
    private Runnable runnable;
    private long time = 3;
    private String tbarea;
    private String tbmaxfen;
    private String tbsubtype;
    private String token;
    private Boolean tjly = true;
    private String schoolurl;
    private String pici;
    private boolean efc;
    private PayPresent payPresent;

    private int pay = 2;

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case SDK_PAY_FLAG:
                    AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
                    switch (payResult.getResultStatus()) {
                        case "9000":
                            SPUtils.put(MyApp.context, "VIP", true);
                            Toast.makeText(TuiJianSchoolActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                            isretry();
                            break;
                        case "8000":
                            Toast.makeText(TuiJianSchoolActivity.this, "正在处理中", Toast.LENGTH_SHORT).show();
                            break;
                        case "4000":
                            Toast.makeText(TuiJianSchoolActivity.this, "订单支付失败", Toast.LENGTH_SHORT).show();
                            break;
                        case "5000":
                            Toast.makeText(TuiJianSchoolActivity.this, "重复请求", Toast.LENGTH_SHORT).show();
                            break;
                        case "6001":
                            Toast.makeText(TuiJianSchoolActivity.this, "已取消支付", Toast.LENGTH_SHORT).show();
                            break;
                        case "6002":
                            Toast.makeText(TuiJianSchoolActivity.this, "网络连接出错", Toast.LENGTH_SHORT).show();
                            break;
                        case "6004":
                            Toast.makeText(TuiJianSchoolActivity.this, "正在处理中", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(TuiJianSchoolActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    break;

            }
        }
    };

    @Override
    public int getId() {
        return R.layout.activity_tui_jian_school;
    }

    @Override
    public void InIt() {
        schoolname = getIntent().getStringExtra("schoolname");
        pici = getIntent().getStringExtra("pici");
        tuijianName.setText(schoolname);
        schoolurl = getIntent().getStringExtra("schoolurl");
        if (schoolurl != null) {
            Glide.with(this).load(schoolurl).asBitmap().centerCrop().into(new BitmapImageViewTarget(tuijianIv) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(TuiJianSchoolActivity.this.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    tuijianIv.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
        tuiJianPresent = new TuiJianPresent(this);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                time--;
                String formatLongToTimeStr = formatLongToTimeStr(time);
                String[] split = formatLongToTimeStr.split("：");
                for (int i = 0; i < split.length; i++) {
                    if (i == 0) {
                        tjHour.setText(split[0]);
                    }
                    if (i == 1) {
                        tjMinute.setText(split[1]);
                    }
                    if (i == 2) {
                        tjMiao.setText(split[2]);
                    }
                }
                if (time > 0) {
                    handler.postDelayed(this, 1000);
                } else {
                    handler.removeCallbacks(this);
                    tuiJianPresent.GetTuijian(schoolname, pici, tbarea, tbsubtype, tbmaxfen, token);
                }
            }
        };
    }

    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue();
        if (second > 60) {
            minute = second / 60;         //取整
            second = second % 60;         //取余
        }
//199415
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = hour + "：" + minute + "：" + second;
        return strtime;
    }


    @Override
    protected void onResume() {
        efc = getIntent().getBooleanExtra("EFC", false);
        token = (String) SPUtils.get(MyApp.context, "token", "");
        if (efc) {
            CXEFCPresenter cxefcPresenter = new CXEFCPresenter(new CXEFCView() {
                @Override
                public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
                    if (cxefcBeanBaseBean.code == 0) {
                        tbmaxfen = cxefcBeanBaseBean.data.getCeeScore();
                        tbsubtype = cxefcBeanBaseBean.data.getStutype();
                        tbarea = cxefcBeanBaseBean.data.getSourceArea();
                        tuiJianPresent.GetTuijian(schoolname, pici, tbarea, tbsubtype, tbmaxfen, token);
                    } else {
                        Toast("网络较差，请稍后重试");
                    }
                }

                @Override
                public void GetEFCResultfail(Throwable t) {
                    Toast("网络较差，请稍后重试");
                }
            });
            cxefcPresenter.CXEFCPresenter(token);
        } else {
            tbmaxfen = (String) SPUtils.get(MyApp.context, "tbmaxfen", "500");
            tbarea = (String) SPUtils.get(MyApp.context, "tbarea", "北京市");
            tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "文科");
            tuiJianPresent.GetTuijian(schoolname, pici, tbarea, tbsubtype, tbmaxfen, token);

        }
        int PAYCODE = (int) SPUtils.get(MyApp.context, "PAYCODE", -1);
        if (PAYCODE == 0) {
            SPUtils.put(MyApp.context, "PAYCODE", -1);
            SPUtils.put(MyApp.context, "VIP", true);
            Toast.makeText(TuiJianSchoolActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
            isretry();
        }
        super.onResume();
    }

    private void isretry() {
        String str = "是否重新测试？";
        Dialog dialog = new AlertDialog.Builder(this).setTitle("支付成功").setMessage(str)
                // 设置内容
                .setPositiveButton("重新测试",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                Intent intent = new Intent(TuiJianSchoolActivity.this, AnswerActivity.class);
                                startActivity(intent);
                                finish();
                                dialog.dismiss();

                            }
                        }).setNegativeButton("不需要",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                                Intent intent = new Intent(TuiJianSchoolActivity.this, ComlitEFCActivity.class);
                                startActivity(intent);
                                finish();
                                dialog.dismiss();
                            }
                        }).create();// 创建
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tuiJianPresent.onDestory();
    }

    @OnClick({R.id.tuijian_iv_back, R.id.rl_tjschool, R.id.tv_ktefc, R.id.tv_jixu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tuijian_iv_back:
                finish();
                break;
            case R.id.rl_tjschool:
                Intent intent = new Intent(this, SchoolDetailActivity.class);
                intent.putExtra("schoolname", schoolname);
                intent.putExtra("EFC", efc);
                startActivity(intent);
                break;
            case R.id.tv_ktefc:
                if (efc) {
                    payPresent = new PayPresent(new PayView() {
                        @Override
                        public void XDsuccess(XDingdanBean xDingdanBean) {
                            if (xDingdanBean != null) {
                                String outTradeNo = xDingdanBean.getOutTradeNo();
                                tanchuang(TuiJianSchoolActivity.this, outTradeNo);
                            }
                        }

                        @Override
                        public void ZFBPaysuccess(final String orderinfo) {
                            if (orderinfo != null) {
                                Runnable payRunnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        PayTask alipay = new PayTask(TuiJianSchoolActivity.this);
                                        Map<String, String> result = alipay.payV2(orderinfo, true);
                                        Message msg = new Message();
                                        msg.what = SDK_PAY_FLAG;
                                        msg.obj = result;
                                        mHandler.sendMessage(msg);
                                    }
                                };
                                // 必须异步调用
                                Thread payThread = new Thread(payRunnable);
                                payThread.start();
                            }
                        }

                        @Override
                        public void WXPaysuccess(WeiXinBean weiXinBean) {
                            if (weiXinBean != null) {
                                WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
                                builder.setAppId(weiXinBean.getAppid())
                                        .setPartnerId(weiXinBean.getPartnerid())
                                        .setPrepayId(weiXinBean.getPrepayid())
                                        .setPackageValue(weiXinBean.getPackageX())
                                        .setNonceStr(weiXinBean.getNoncestr())
                                        .setTimeStamp(weiXinBean.getTimestamp())
                                        .setSign(weiXinBean.getSign())
                                        .build().toWXPayNotSign(TuiJianSchoolActivity.this, weiXinBean.getAppid());
                            }
                        }

                        @Override
                        public void XDFail(String s) {
                            Toast(s);
                        }
                    });
                    String type = (String) SPUtils.get(MyApp.context, "kemuefc", "本科");
                    if (token.length() > 4) {
                        if (Dianji2.isNotFastClick()) {

                            if (type.equals("本科")) {
                                payPresent.XiaDan(token, "3", pay + "");
                            } else {
                                payPresent.XiaDan(token, "4", pay + "");
                            }
                        }
                    } else {
                        Toast("token失效，请重新登录");
                    }
                } else {
                    Intent intent1 = new Intent(TuiJianSchoolActivity.this, Buy2Activity.class);
                    intent1.putExtra("price", "598");
                    startActivity(intent1);
                }

                break;
            case R.id.tv_jixu:
                Intent intent2 = new Intent(TuiJianSchoolActivity.this, EFCJieSuoActivity.class);
                startActivity(intent2);
                break;
        }
    }

    public void tanchuang(Context context, final String outTradeNo) {
        pay = 2;
        final Dialog dialog = new Dialog(context, R.style.Theme_Light_Dialog);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dingdan_layout, null);
        //获得dialog的window窗口
        Window window = dialog.getWindow();
        //设置dialog在屏幕底部
        window.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        window.setWindowAnimations(R.style.dialogStyle);
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        //将自定义布局加载到dialog上
        dialog.setContentView(dialogView);
        dialog.show();
        ImageView iv_chahao = dialogView.findViewById(R.id.iv_chahao);
        RelativeLayout rl5 = dialogView.findViewById(R.id.rl5);
        RelativeLayout rl6 = dialogView.findViewById(R.id.rl6);
        final ImageView iv_weixin_dui = dialogView.findViewById(R.id.iv_weixin_dui);
        final ImageView iv_zhifubao_dui = dialogView.findViewById(R.id.iv_zhifubao_dui);
        TextView tv_queren = dialogView.findViewById(R.id.tv_queren);
        TextView diangdan_money = dialogView.findViewById(R.id.diangdan_money);
        TextView tv_bianhao = dialogView.findViewById(R.id.tv_bianhao);
        TextView tv_mingcheng = dialogView.findViewById(R.id.tv_mingcheng);
     /*   if(zk)
        {
            tv_mingcheng.setText("升学设计系统--专科版");
        }
        else
        {
            tv_mingcheng.setText("升学设计系统--本科版");
        }*/
        tv_mingcheng.setText("升学设计系统");
        tv_bianhao.setText(outTradeNo);
    /*    if(zk){
            diangdan_money.setText("698");
        }else {
            diangdan_money.setText("698");
        }*/
        diangdan_money.setText("598");

        iv_chahao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        rl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_weixin_dui.setVisibility(View.VISIBLE);
                iv_zhifubao_dui.setVisibility(View.GONE);
                pay = 2;
            }
        });
        rl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_weixin_dui.setVisibility(View.GONE);
                iv_zhifubao_dui.setVisibility(View.VISIBLE);
                pay = 1;
            }
        });
        tv_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pay == 1) {
                    payPresent.ZFBpay(outTradeNo);
                } else {
                    payPresent.WXpay(outTradeNo);
                }

 /*     {
                "msg": "success",
                    "code": 0,
                    "data": {
                "appId": "wx6a6810597dadc392",
                        "nonceStr": "1345008214",
                        "package": "Sign=WXPay",
                        "partnerId": "1496598622",
                        "prepayId": "wx20180303134458805252",
                        "sign": "28F70F7923B17D091C4EEBA72155B648",
                        "timeStamp": "1520055901"
            }
            }*/
                dialog.cancel();
            }
        });
    }

    @Override
    public void TuijianSuccess(TuiJianBean tuiJianBean) {
        if (tuiJianBean != null) {
            if (tuiJianBean.getYear() != null) {
                tuijianZh.setText(tuiJianBean.getYear() + "年" + tuiJianBean.getTime() + "最低分" + tuiJianBean.getMinScore());
            } else {
                tuijianZh.setText("暂无数据");
            }
            if (schoolurl == null) {
                Glide.with(MyApp.context).load(BaseApi.ImgApi + tuiJianBean.getCollegeBadge()).into(tuijianIv);
            }
            List<String> list = new ArrayList<>();
            if (tjly) {
                List<String> recommend = tuiJianBean.getRecommend();
                if (recommend != null && recommend.size() > 0) {
                    list.addAll(recommend);
                }
                List<String> vipRecommend = tuiJianBean.getVipRecommend();
                if (vipRecommend != null && vipRecommend.size() > 0) {
                    list.addAll(vipRecommend);
                }

                if (list.size() == 0) {
                    list.add("暂无数据");
                }
                flTjly.settuijianListData(list);

                tjly = false;
            }
            String efcState = tuiJianBean.getEfcState();
            if (efcState.equals("0")) {
                tvZyjh.setText("专业计划");
                llLing.setVisibility(View.VISIBLE);
                llSan.setVisibility(View.GONE);
                llTwo.setVisibility(View.GONE);
                llOne.setVisibility(View.GONE);
            } else if (efcState.equals("1")) {
                List<TuiJianBean.EfcRecommendMajorEntityBean> efcRecommendMajorEntity = tuiJianBean.getEfcRecommendMajorEntity();
                if (efcRecommendMajorEntity.size() > 0 && efcRecommendMajorEntity != null) {
                    TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(efcRecommendMajorEntity, this);
                    listZy.setAdapter(tuiJianAdapter);
                }
                llOne.setVisibility(View.VISIBLE);
                llLing.setVisibility(View.GONE);
                llSan.setVisibility(View.GONE);
                llTwo.setVisibility(View.GONE);

            } else if (efcState.equals("2")) {
                llTwo.setVisibility(View.VISIBLE);
                llLing.setVisibility(View.GONE);
                llSan.setVisibility(View.GONE);
                llOne.setVisibility(View.GONE);
            } else if (efcState.equals("3")) {
                String countDown = tuiJianBean.getCountDown();
                time = Integer.parseInt(countDown);
                handler.postDelayed(runnable, 1000);
                llSan.setVisibility(View.VISIBLE);
                llTwo.setVisibility(View.GONE);
                llLing.setVisibility(View.GONE);
                llOne.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void TuijianFail(String msg) {

    }



}
