package com.example.login_demo;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import base.BaseActivity;
import base.BaseBean;
import butterknife.BindView;
import butterknife.OnClick;
import presenter.YfenQPresent;

import untils.SPUtils;
import view.YfenQView;

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
                    Toast("请前往“我的”进行登录");
                }
                break;
            case R.id.rl2:
                xlcp="霍兰德";
                if(token.length()>4){
                    yfenQPresent.yfJudge("SDS_E",token);
                }else {
                    Toast("请前往“我的”进行登录");
                }
                break;
        }
    }

    @Override
    public void YfenQsuccess(BaseBean baseBean) {
        if(baseBean.code==0){
            Intent intent = new Intent(MentalityActivity.this, CeShiShuoMingActivity.class);
            startActivity(intent);

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
