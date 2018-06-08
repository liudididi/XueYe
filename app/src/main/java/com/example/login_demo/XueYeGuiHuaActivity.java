package com.example.login_demo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.login_demo.wxapi.WXPayUtils;

import java.util.List;
import java.util.Map;

import adapter.XueYeGuiHua_adapter;
import adapter.ZytjBigAdapter;
import base.BaseActivity;
import base.BaseBean;
import bean.CXEFCBean;
import bean.EFCBean;
import bean.WeiXinBean;
import bean.XDingdanBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import presenter.CXEFCPresenter;
import presenter.CountdownPresent;
import presenter.PayPresent;
import untils.Dianji2;
import untils.ListViewForScrollView;
import untils.ListViewScrollView;
import untils.MyListView;
import untils.MyQusetUtils;
import untils.SPUtils;
import view.CXEFCView;
import view.CountdownView;
import view.PayView;
import zfbpay.AliPayResult;

public class XueYeGuiHuaActivity extends BaseActivity implements CXEFCView, CountdownView {

    @BindView(R.id.guihua_iv_back)
    ImageView guihuaIvBack;
    @BindView(R.id.rl_xqcebg)
    RelativeLayout rlXqcebg;
    @BindView(R.id.rl_xgcebg)
    RelativeLayout rlXgcebg;
    @BindView(R.id.xygh_list)
    ListViewForScrollView xyghList;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.guihua_ivyiwen)
    ImageView guihuaIvyiwen;
    @BindView(R.id.rl_wenti)
    RelativeLayout rlWenti;
    @BindView(R.id.re_guihuadi)
    RelativeLayout reGuihuadi;
    @BindView(R.id.guihua_title)
    RelativeLayout guihuaTitle;

    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.zw)
    RelativeLayout zw;
    @BindView(R.id.zhanwei)
    View zhanwei;
    @BindView(R.id.xlcs_bt)
    Button xlcs_bt;
    @BindView(R.id.xlcp_jingzhun)
    TextView xlcpJingzhun;
    @BindView(R.id.xlcp_putong)
    TextView xlcpPutong;
    @BindView(R.id.ll_zytj)
    LinearLayout llZytj;
    @BindView(R.id.rl_zytjbg)
    RelativeLayout rlZytjbg;
    @BindView(R.id.zytj_list)
    ListViewForScrollView zytjList;
    @BindView(R.id.tv_day1)
    TextView tv_day1;
    @BindView(R.id.tv_day2)
    TextView tv_day2;
    @BindView(R.id.tv_day3)
    TextView tv_day3;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv)
    TextView tv;
    private boolean yiwen = false;

    private String token;
    private String testCode;
    private CXEFCPresenter cxefcPresenter;
    private String sourceArea;
    private String stutype;
    private String ceeScore;
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
                            Toast.makeText(XueYeGuiHuaActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                            isretry();
                            break;
                        case "8000":
                            Toast.makeText(XueYeGuiHuaActivity.this, "正在处理中", Toast.LENGTH_SHORT).show();
                            break;
                        case "4000":
                            Toast.makeText(XueYeGuiHuaActivity.this, "订单支付失败", Toast.LENGTH_SHORT).show();
                            break;
                        case "5000":
                            Toast.makeText(XueYeGuiHuaActivity.this, "重复请求", Toast.LENGTH_SHORT).show();
                            break;
                        case "6001":
                            Toast.makeText(XueYeGuiHuaActivity.this, "已取消支付", Toast.LENGTH_SHORT).show();
                            break;
                        case "6002":
                            Toast.makeText(XueYeGuiHuaActivity.this, "网络连接出错", Toast.LENGTH_SHORT).show();
                            break;
                        case "6004":
                            Toast.makeText(XueYeGuiHuaActivity.this, "正在处理中", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(XueYeGuiHuaActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    break;

            }
        }
    };
    private PayPresent payPresent;
    private boolean vip;
    private CountdownPresent countdownPresent;

    @Override
    public int getId() {
        return R.layout.activity_xue_ye_gui_hua;
    }
    private void isretry() {
        String str="是否重新测试？";
        Dialog dialog = new android.app.AlertDialog.Builder(this).setTitle("支付成功").setMessage(str)
                // 设置内容
                .setPositiveButton("重新测试",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                Intent intent = new Intent(XueYeGuiHuaActivity.this, AnswerActivity.class);
                                startActivity(intent);
                                finish();
                                dialog.dismiss();

                            }
                        }).setNegativeButton("不需要",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                                Intent intent = new Intent(XueYeGuiHuaActivity.this, ComlitEFCActivity.class);
                                startActivity(intent);
                                finish();
                                dialog.dismiss();
                            }
                        }).create();// 创建
        dialog.setCancelable(false);
        dialog.show();
    }
    @Override
    public void InIt() {
        countdownPresent = new CountdownPresent(this);
        countdownPresent.CountdownPresent();
        xyghList.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);
        zw.setVisibility(View.VISIBLE);
        token = (String) SPUtils.get(MyApp.context, "token", "");
        vip = getIntent().getBooleanExtra("VIP", false);
        initzhuanye();
        if (vip) {
            pb.setVisibility(View.GONE);
            zw.setVisibility(View.VISIBLE);
            xlcs_bt.setVisibility(View.GONE);
            llZytj.setVisibility(View.GONE);
            payPresent = new PayPresent(new PayView() {
                @Override
                public void XDsuccess(XDingdanBean xDingdanBean) {
                    if (xDingdanBean != null) {
                        String outTradeNo = xDingdanBean.getOutTradeNo();
                        tanchuang(XueYeGuiHuaActivity.this, outTradeNo);
                    }
                }

                @Override
                public void ZFBPaysuccess(final String orderinfo) {
                    if (orderinfo != null) {
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(XueYeGuiHuaActivity.this);
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
                                .build().toWXPayNotSign(XueYeGuiHuaActivity.this, weiXinBean.getAppid());
                    }
                }

                @Override
                public void XDFail(String s) {
                    Toast(s);
                }
            });
        } else {
            zw.setVisibility(View.GONE);
            zhanwei.setVisibility(View.VISIBLE);
            llZytj.setVisibility(View.VISIBLE);
            xlcs_bt.setVisibility(View.VISIBLE);
            xlcpJingzhun.setVisibility(View.GONE);
            xlcpJingzhun.setVisibility(View.GONE);
            rl.setVisibility(View.GONE);
        }
        //专业
        cxefcPresenter = new CXEFCPresenter(this);
        cxefcPresenter.CXEFCPresenter(token);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int PAYCODE = (int) SPUtils.get(MyApp.context, "PAYCODE", -1);
        if (PAYCODE == 0) {
            SPUtils.put(MyApp.context, "PAYCODE", -1);
            SPUtils.put(MyApp.context, "VIP", true);
            Toast.makeText(XueYeGuiHuaActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
            isretry();
        }
    }

    private void initzhuanye() {
        MyQusetUtils.getInstance().getQuestInterface().getEFCData(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<EFCBean>>() {
                    @Override
                    public void onNext(BaseBean<EFCBean> efcBeanBaseBean) {
                        testCode = efcBeanBaseBean.data.getTest_code();
                   if(vip==false){
                       if (efcBeanBaseBean.data.getMajorGai() != null && efcBeanBaseBean.data.getMajorGai().size() > 0) {
                           List<String> majorGai = efcBeanBaseBean.data.getMajorGai();
                           zhanwei.setVisibility(View.GONE);
                           pb.setVisibility(View.GONE);
                           xyghList.setVisibility(View.VISIBLE);
                           XueYeGuiHua_adapter xueYeGuiHua_adapter = new XueYeGuiHua_adapter(majorGai, XueYeGuiHuaActivity.this);
                           xyghList.setAdapter(xueYeGuiHua_adapter);
                           sv.smoothScrollTo(0, 0);
                       }
                       final List<EFCBean.MajortwoBean> majortwo = efcBeanBaseBean.data.getMajortwo();

                       if (majortwo != null && majortwo.size() > 0) {
                           ZytjBigAdapter zytjBigAdapter = new ZytjBigAdapter(majortwo, XueYeGuiHuaActivity.this);
                           zytjList.setAdapter(zytjBigAdapter);
                       }else {
                           llZytj.setVisibility(View.GONE);
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


        //TODO 待完善
//            cxefcPresenter = new CXEFCPresenter(new CXEFCView() {
//                @Override
//                public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
//
//                    testCode = cxefcBeanBaseBean.data.getTestCode();
//                    if(cxefcBeanBaseBean.data.getMajorGai()!=null){
//                        String[] split = cxefcBeanBaseBean.data.getMajorGai().split(",");
//                        List<String> Stringlist=new ArrayList<>();
//                        for (int i = 0; i < split.length; i++) {
//                            Stringlist.add(split[i]);
//                        }
//                        zw.setVisibility(View.GONE);
//                        pb.setVisibility(View.GONE);
//                        xyghList.setVisibility(View.VISIBLE);
//                        XueYeGuiHua_adapter xueYeGuiHua_adapter = new XueYeGuiHua_adapter(Stringlist,XueYeGuiHuaActivity.this );
//                        xyghList.setAdapter(xueYeGuiHua_adapter);
//                        sv.smoothScrollTo(0, 0);
//                    }
//
//                }
//                @Override
//                public void GetEFCResultfail(Throwable t) {
//                }
//            });
//            cxefcPresenter.CXEFCPresenter(token);

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

    @OnClick({R.id.guihua_iv_back, R.id.rl_xqcebg, R.id.rl_xgcebg, R.id.guihua_ivyiwen, R.id.xlcs_bt, R.id.xlcp_putong, R.id.xlcp_jingzhun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guihua_iv_back:
                intent(this, EFCJieSuoActivity.class);
                finish();
                break;
            case R.id.xlcp_putong:
                //s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
                if (sourceArea != null && stutype != null && ceeScore != null) {
                    Intent intent = new Intent(XueYeGuiHuaActivity.this, AccurateActivity.class);
                    intent.putExtra("s2", sourceArea + "");
                    intent.putExtra("tbsubtype", stutype + "");
                    intent.putExtra("fen", ceeScore);
                    intent.putExtra("s1", "0");
                    intent.putExtra("s4", "");
                    intent.putExtra("s5", "");
                    startActivity(intent);
                    finish();
                }
                // intent(this,AccurateActivity.class);
                break;
            case R.id.xlcs_bt:
                //s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
                if (sourceArea != null && stutype != null && ceeScore != null) {
                    Intent intent = new Intent(XueYeGuiHuaActivity.this, AccurateActivity.class);
                    intent.putExtra("s2", sourceArea + "");
                    intent.putExtra("tbsubtype", stutype + "");
                    intent.putExtra("fen", ceeScore);
                    intent.putExtra("s1", "0");
                    intent.putExtra("s4", "");
                    intent.putExtra("s5", "");
                    startActivity(intent);
                    finish();
                }
                // intent(this,AccurateActivity.class);
                break;
            case R.id.xlcp_jingzhun:
                //s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
                 String type = (String) SPUtils.get(MyApp.context, "kemuefc", "本科");
                if (token.length() > 4) {
                    if(Dianji2.isNotFastClick()){
                        if(type.equals("本科")){
                            payPresent.XiaDan(token, "3", pay + "");
                        }else {
                            payPresent.XiaDan(token, "4", pay + "");
                        }
                    }
                } else {
                    Toast("token失效，请重新登录");
                }
                // intent(this,AccurateActivity.class);
                break;
            case R.id.rl_xqcebg:
                Intent intent1 = new Intent(XueYeGuiHuaActivity.this, XQcsActivity.class);
                intent1.putExtra("code", testCode);
                startActivity(intent1);
                break;
            case R.id.rl_xgcebg:
                Intent intent = new Intent(XueYeGuiHuaActivity.this, XGcsActivity.class);
                intent.putExtra("code", testCode);
                startActivity(intent);
                break;
            case R.id.guihua_ivyiwen:

                if (yiwen) {
                    rlWenti.setVisibility(View.GONE);
                    sv.setVisibility(View.VISIBLE);
                    guihuaIvyiwen.setImageResource(R.drawable.cjwt);
                    yiwen = false;
                    reGuihuadi.setVisibility(View.VISIBLE);
                } else {
                    rlWenti.setVisibility(View.VISIBLE);
                    sv.setVisibility(View.GONE);
                    guihuaIvyiwen.setImageResource(R.drawable.cha);
                    yiwen = true;
                    reGuihuadi.setVisibility(View.GONE);
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        cxefcPresenter.onDestory();
        countdownPresent.onDestory();
    }

    @Override
    public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
        //考生省份
        sourceArea = cxefcBeanBaseBean.data.getSourceArea();
        //文理科
        stutype = cxefcBeanBaseBean.data.getStutype();
        SPUtils.put(MyApp.context,"tbsubtypeefc",stutype);
        SPUtils.put(MyApp.context,"kemuefc",cxefcBeanBaseBean.data.getCollegetype());

        //考生分数
        ceeScore = cxefcBeanBaseBean.data.getCeeScore();

        SPUtils.put(MyApp.context,"tbmaxfenefc",ceeScore);

    }

    @Override
    public void GetEFCResultfail(Throwable t) {

    }


    @Override
    public void Countdownsuccess(BaseBean baseBean) {

        String type = baseBean.type.toString();
        if(type.equals("1"))
        {
            String s = baseBean.data.toString();
            tv.setText("距高考还有");
            DaoJiShi(s);

        }
        else if(type.equals("3"))
        {
            String s = baseBean.data3.toString();
            tv.setText("距报考还有");
            DaoJiShi(s);
        }
        else if(type.equals("4"))
        {
            String s = baseBean.data4.toString();
            tv.setText("距报考结束还有");
            DaoJiShi(s);
        }


    }

    private void DaoJiShi(String s) {
        if(s!=null&&s.length()==3)
        {
            String substring = s.substring(0,1);
            String substring1 = s.substring(1,2);
            String substring2 = s.substring(2,3);
            System.out.println("倒计时+++"+substring+"++"+substring1+"+++"+substring2);
            tv_day1.setText(substring);
            tv_day2.setText(substring1);
            tv_day3.setText(substring2);
        }
        else if(s!=null&&s.length()==2)
        {
            String substring = s.substring(0,1);
            String substring1 = s.substring(1,2);
            tv_day1.setText("0");
            tv_day2.setText(substring);
            tv_day3.setText(substring1);
        }
        else
        {
            tv_day1.setText("0");
            tv_day2.setText("0");
            tv_day3.setText(s);
        }
    }
    @Override
    public void Countdownfail(Throwable t) {

    }
}
