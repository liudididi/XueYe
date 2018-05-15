package com.example.login_demo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import butterknife.OnClick;
import fragment.WishFragMent;
import presenter.CountdownPresent;
import presenter.PayPresent;
import untils.SPUtils;
import view.CountdownView;
import view.PayView;
import zfbpay.AliPayResult;

public class BuyActivity extends BaseActivity implements CountdownView, PayView {


    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_goumai)
    TextView tvGoumai;
    @BindView(R.id.buy_tvdjs)
    TextView buyTvdjs;
    @BindView(R.id.buy_iv_back)
    ImageView buyIvBack;
    @BindView(R.id.iv_xlce1)
    ImageView ivXlce1;
    @BindView(R.id.iv_xlce2)
    ImageView ivXlce2;
    @BindView(R.id.but_title)
    TextView butTitle;
    @BindView(R.id.buy_pb)
    ProgressBar buyPb;
    private CountdownPresent countdownPresent;
    private String bh = "1";
    private String title;
    private int pay = 2;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case SDK_PAY_FLAG:

                    AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);

                    System.out.println("支付宝支付返回：" + payResult.toString());

                    switch (payResult.getResultStatus()) {
                        case "9000":
                            Toast.makeText(BuyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BuyActivity.this, CeShiShuoMingActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        case "8000":
                            Toast.makeText(BuyActivity.this, "正在处理中", Toast.LENGTH_SHORT).show();
                            break;
                        case "4000":
                            Toast.makeText(BuyActivity.this, "订单支付失败", Toast.LENGTH_SHORT).show();
                            break;
                        case "5000":
                            Toast.makeText(BuyActivity.this, "重复请求", Toast.LENGTH_SHORT).show();
                            break;
                        case "6001":
                            Toast.makeText(BuyActivity.this, "已取消支付", Toast.LENGTH_SHORT).show();
                            break;
                        case "6002":
                            Toast.makeText(BuyActivity.this, "网络连接出错", Toast.LENGTH_SHORT).show();
                            break;
                        case "6004":
                            Toast.makeText(BuyActivity.this, "正在处理中", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(BuyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
    private PayPresent payPresent;
    private String price;
    private String token;

    @Override
    public int getId() {
        return R.layout.activity_buy;
    }

    @Override
    public void InIt() {
        price = getIntent().getStringExtra("price");
        if (price != null) {
            tvMoney.setText(price);
        }
        countdownPresent = new CountdownPresent(this);
        buyIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (MentalityActivity.xlcp.equals("MBTI")) {
            bh = "1";
            ivXlce1.setImageResource(R.drawable.mbti1);
            ivXlce2.setImageResource(R.drawable.mbti2);
            title = "MBTI特质测试";

        } else {
            bh = "2";
            ivXlce1.setImageResource(R.drawable.huolande1);
            ivXlce2.setImageResource(R.drawable.huolande2);
            title = "霍兰德兴趣特质测试";
        }
        butTitle.setText(title);
        payPresent = new PayPresent(this);

        buyPb.setVisibility(View.GONE);
        tvGoumai.setEnabled(true);
    }


    @OnClick({R.id.tv_goumai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_goumai:
                if (token.length() > 4) {
                    buyPb.setVisibility(View.VISIBLE);
                    tvGoumai.setEnabled(false);
                    payPresent.XiaDan(token, bh, pay + "");
              /*     Toast.makeText(BuyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BuyActivity.this, CeShiShuoMingActivity.class);
                    startActivity(intent);
                    finish();*/
                    break;
                } else {
                    Toast("用户未登录");
                }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        buyPb.setVisibility(View.GONE);
        tvGoumai.setEnabled(true);
        if (WishFragMent.djs != null) {
            buyTvdjs.setText(WishFragMent.djs);
        } else {
            countdownPresent.CountdownPresent();
        }
        token = (String) SPUtils.get(MyApp.context, "token", "");
        int PAYCODE = (int) SPUtils.get(MyApp.context, "PAYCODE", -1);
        if (PAYCODE == 0) {
            SPUtils.put(MyApp.context, "PAYCODE", -1);
            Toast.makeText(BuyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BuyActivity.this, CeShiShuoMingActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        countdownPresent.onDestory();
    }

    @Override
    public void Countdownsuccess(BaseBean baseBean) {
        WishFragMent.djs = baseBean.data.toString();
        buyTvdjs.setText(WishFragMent.djs);
    }

    @Override
    public void Countdownfail(Throwable t) {
        countdownPresent.CountdownPresent();
    }


    @Override
    public void XDsuccess(XDingdanBean xDingdanBean) {
        buyPb.setVisibility(View.GONE);
        tvGoumai.setEnabled(true);
        if (xDingdanBean != null) {
            String outTradeNo = xDingdanBean.getOutTradeNo();
            tanchuang(BuyActivity.this, outTradeNo);
        }
    }

    @Override
    public void ZFBPaysuccess(final String orderinfo) {
        if (orderinfo != null) {
            Runnable payRunnable = new Runnable() {
                @Override
                public void run() {
                    PayTask alipay = new PayTask(BuyActivity.this);
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
        buyPb.setVisibility(View.GONE);
        tvGoumai.setEnabled(true);
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
        tv_mingcheng.setText(title);
        tv_bianhao.setText(outTradeNo);
        if (price != null) {
            diangdan_money.setText(price);
        }
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



}
