package com.example.login_demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.login_demo.wxapi.WXPayUtils;

import java.io.IOException;
import java.util.Map;

import base.BaseActivity;
import base.BaseApi;
import base.BaseBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.ResponseBody;
import presenter.YfenQPresent;
import retrofit2.Retrofit;

import untils.MyQusetUtils;
import untils.QuestInterface;
import untils.SPUtils;
import view.YfenQView;
import zfbpay.AliPayResult;
import zfbpay.PayResult;

import static java.security.AccessController.getContext;

//TODO 心理测评
public class MentalityActivity extends BaseActivity implements YfenQView {
    @BindView(R.id.mentality_iv_back)
    ImageView mentalityIvBack;
    @BindView(R.id.rl1)
    ImageView rl1;
    @BindView(R.id.rl2)
    ImageView rl2;

    public   static    String  xlcp=null;
    private YfenQPresent yfenQPresent;
    private String token;

    @Override
    public int getId() {
        return R.layout.activity_mentality;
    }

    @Override
    public void InIt() {
        yfenQPresent = new YfenQPresent(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        token = (String) SPUtils.get(MyApp.context, "token", "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        yfenQPresent.onDestory();
    }

    @OnClick({R.id.mentality_iv_back, R.id.rl1, R.id.rl2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mentality_iv_back:
                finish();
                break;
            case R.id.rl1:
                xlcp="MBTI";
                if(token.length()>4){
                    yfenQPresent.yfJudge("MBTI_E",token);
                }else {
                    Toast("请先登录");
                }
                break;
            case R.id.rl2:
                xlcp="霍兰德";
                if(token.length()>4){
                    yfenQPresent.yfJudge("SDS_E",token);
                }else {
                    Toast("请先登录");
                }
                break;
        }
    }

    @Override
    public void YfenQsuccess(BaseBean baseBean) {
        if(baseBean.code==0){
            Intent intent = new Intent(MentalityActivity.this, CeShiShuoMingActivity.class);
            startActivity(intent);
            finish();
        }else {
            if(xlcp.equals("MBTI")){
                Intent intent=new Intent(this,BuyActivity.class);
                intent.putExtra("price","0.01");
                startActivity(intent);
            }else {
                Intent intent2=new Intent(this,BuyActivity.class);
                intent2.putExtra("price","0.01");
                startActivity(intent2);
            }
        }
    }

    @Override
    public void YfenFail(String msg) {

    }
}
