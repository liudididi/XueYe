package com.example.login_demo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.login_demo.wxapi.WXPayUtils;

import java.util.Map;

import base.BaseActivity;
import base.BaseBean;
import bean.WeiXinBean;
import bean.XDingdanBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.CountdownPresent;
import presenter.PayPresent;
import untils.SPUtils;
import view.CountdownView;
import view.PayView;
import zfbpay.AliPayResult;

import static com.example.login_demo.MyApp.context;

public class
DuiBiActivity extends BaseActivity implements PayView, CountdownView {


    @BindView(R.id.duibi_iv_back)
    ImageView duibiIvBack;
    @BindView(R.id.tv_jingzhun)
    TextView tvJingzhun;
    @BindView(R.id.tv_putong)
    TextView tvPutong;
    @BindView(R.id.tv_hang)
    TextView tvHang;
    @BindView(R.id.tv_day1)
    TextView tv_day1;
    @BindView(R.id.tv_day2)
    TextView tv_day2;
    @BindView(R.id.tv_day3)
    TextView tv_day3;


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
                            SPUtils.put(MyApp.context,"VIP",true);
                            isretry();
                            Toast.makeText(DuiBiActivity.this, "支付成功", Toast.LENGTH_SHORT).show();

                            break;
                        case "8000":
                            Toast.makeText(DuiBiActivity.this, "正在处理中", Toast.LENGTH_SHORT).show();
                            break;
                        case "4000":
                            Toast.makeText(DuiBiActivity.this, "订单支付失败", Toast.LENGTH_SHORT).show();
                            break;
                        case "5000":
                            Toast.makeText(DuiBiActivity.this, "重复请求", Toast.LENGTH_SHORT).show();
                            break;
                        case "6001":
                            Toast.makeText(DuiBiActivity.this, "已取消支付", Toast.LENGTH_SHORT).show();
                            break;
                        case "6002":
                            Toast.makeText(DuiBiActivity.this, "网络连接出错", Toast.LENGTH_SHORT).show();
                            break;
                        case "6004":
                            Toast.makeText(DuiBiActivity.this, "正在处理中", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(DuiBiActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    break;

            }
//            返回码	含义
//            9000	订单支付成功
//            8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
//            4000	订单支付失败
//            5000	重复请求
//            6001	用户中途取消
//            6002	网络连接出错
//            6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
//            其它	其它支付错误

        }
    };
    private CountdownPresent countdownPresent;

    private void isretry() {
        String str="是否重新测试？";
        Dialog dialog = new android.app.AlertDialog.Builder(this).setTitle("支付成功").setMessage(str)
                // 设置内容
                .setPositiveButton("重新测试",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                Intent intent = new Intent(DuiBiActivity.this, AnswerActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        }).setNegativeButton("不需要",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                                Intent intent = new Intent(DuiBiActivity.this, ComlitEFCActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).create();// 创建
        dialog.show();
    }

    @Override
    public int getId() {
        return R.layout.activity_dui_bi;
    }

    @Override
    public void InIt() {
        payPresent = new PayPresent(this);

        String str = tvHang.getText().toString();
        Spannable span = new SpannableString(str);
        span.setSpan(new ForegroundColorSpan(Color.BLACK), 4, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(Color.BLACK), 10, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvHang.setText(span);

        countdownPresent = new CountdownPresent(this);
        countdownPresent.CountdownPresent();
    }

    @OnClick({R.id.duibi_iv_back, R.id.tv_jingzhun, R.id.tv_putong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.duibi_iv_back:
                finish();
                break;
            case R.id.tv_jingzhun:
                String token = (String) SPUtils.get(MyApp.context, "token", "");
                if (token.length() > 4) {
                     payPresent.XiaDan(token, "4", pay + "");
                } else {
                    Toast("token失效，请前往“我的”进行登录");
                }
                break;
            case R.id.tv_putong:
                Intent intent = new Intent(DuiBiActivity.this, XueYeGuiHuaActivity.class);
                intent.putExtra("VIP",true);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int PAYCODE = (int) SPUtils.get(MyApp.context, "PAYCODE", -1);
        if(PAYCODE==0){
            SPUtils.put(MyApp.context,"PAYCODE",-1);
            SPUtils.put(MyApp.context,"VIP",true);
            Toast.makeText(DuiBiActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
            isretry();
        }
    }

    @Override
    public void XDsuccess(XDingdanBean xDingdanBean) {
        if (xDingdanBean != null) {
            String outTradeNo = xDingdanBean.getOutTradeNo();
            tanchuang(DuiBiActivity.this, outTradeNo);
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
    public void ZFBPaysuccess(final String orderinfo) {
        //  final  String orderInfo="alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2016091100482610&biz_content=%7B%22body%22%3A%22%E6%B5%8B%E8%AF%95%22%2C%22out_trade_no%22%3A%222018031016260614933445%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22pay%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F6ya7kc.natappfree.cc%2Falipay%2Fpay&sign=NqORNz%2BNXCciqd8oFc1G3DYfUznjZYkfOiMU8RKs09FWq5o%2Fp2Pmg64M3HoRAHNOLabdkVyRlEVakRBorzPXI%2B1KBF7bkzX5Z9Dfy1w7bz6%2BiFX0BRptzk6u2fpn0M965BHxsj3y8q4%2FJ%2BhdhL%2B0%2BQd0Yp8qfFtxv9M9jN7ixRgLJ%2BfeQ1HIkM9O7cwxfYFo6tgTfgMSzjVM8heLKfZ3KUQkHIEDaaeOt%2FhOwhSbWZFHKvIdrz2v8orsqEJAIaKAM%2BpSlxmEUnw7TPCihEU8TghG8MIzhecas17GlmzjfBJQgOWxypP46VonUKdehcezechrhEsF3J%2BVyY6LFUVmMw%3D%3D&sign_type=RSA2&timestamp=2018-03-10+16%3A26%3A16&version=1.0&sign=NqORNz%2BNXCciqd8oFc1G3DYfUznjZYkfOiMU8RKs09FWq5o%2Fp2Pmg64M3HoRAHNOLabdkVyRlEVakRBorzPXI%2B1KBF7bkzX5Z9Dfy1w7bz6%2BiFX0BRptzk6u2fpn0M965BHxsj3y8q4%2FJ%2BhdhL%2B0%2BQd0Yp8qfFtxv9M9jN7ixRgLJ%2BfeQ1HIkM9O7cwxfYFo6tgTfgMSzjVM8heLKfZ3KUQkHIEDaaeOt%2FhOwhSbWZFHKvIdrz2v8orsqEJAIaKAM%2BpSlxmEUnw7TPCihEU8TghG8MIzhecas17GlmzjfBJQgOWxypP46VonUKdehcezechrhEsF3J%2BVyY6LFUVmMw%3D%3D";
        if (orderinfo != null) {
            Runnable payRunnable = new Runnable() {
                @Override
                public void run() {
                    PayTask alipay = new PayTask(DuiBiActivity.this);
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
                    .build().toWXPayNotSign(this, weiXinBean.getAppid());
        }
    }

    @Override
    public void XDFail(String s) {
        Toast(s);
    }


    @Override
    public void Countdownsuccess(BaseBean baseBean) {

        String s = baseBean.data.toString();

        if(s!=null&&s.length()==3)
        {
            String substring = s.substring(0,1);
            String substring1 = s.substring(1);
            String substring2 = s.substring(2);
            tv_day1.setText(substring);
            tv_day2.setText(substring1);
            tv_day3.setText(substring2);
        }
        if(s!=null&&s.length()==2)
        {
            String substring = s.substring(0,1);
            String substring1 = s.substring(1);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        countdownPresent.onDestory();
    }
}
