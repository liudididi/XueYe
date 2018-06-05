package com.example.login_demo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import base.BaseActivity;
import base.BaseBean;
import bean.CXEFCBean;
import butterknife.BindView;
import butterknife.OnClick;
import presenter.EsayMbtiLSPresenter;
import untils.SPUtils;
import view.EsayMbtiLSView;

public class CeShiShuoMingActivity extends BaseActivity implements EsayMbtiLSView{
    @BindView(R.id.xlcs_iv_back)
    ImageView xlcsIvBack;
    @BindView(R.id.ceshi_title)
    TextView ceshiTitle;
    @BindView(R.id.iv_ceshi)
    ImageView ivCeshi;
    @BindView(R.id.bt_lsbg)
    Button btLsbg;
    @BindView(R.id.bt_kscs)
    Button btKscs;
  private  String title;
    private  String token;
    private EsayMbtiLSPresenter esayMbtiLSPresenter;

    @Override
    public int getId() {
        return R.layout.activity_ce_shi_shuo_ming;
    }

    @Override
    public void InIt() {
        token = (String) SPUtils.get(MyApp.context, "token", "");
        esayMbtiLSPresenter = new EsayMbtiLSPresenter(this);

        if (MentalityActivity.xlcp.equals("MBTI")) {
            ivCeshi.setImageResource(R.drawable.mbtics);
            title="MBTI测试说明";
        } else {
            ivCeshi.setImageResource(R.drawable.hldcs);
            title="霍兰德测试说明";
        }
        ceshiTitle.setText(title);
    }



    @OnClick({R.id.xlcs_iv_back, R.id.bt_lsbg, R.id.bt_kscs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xlcs_iv_back:
         finish();
                break;
            case R.id.bt_lsbg:
                btLsbg.setEnabled(false);
                if (MentalityActivity.xlcp.equals("MBTI")) {
                    esayMbtiLSPresenter.EsayMbtiLSPresenter("MBTI_E",token);

                } else {

                     esayMbtiLSPresenter.EsayMbtiLSPresenter("SDS_E",token);

                }
                break;
            case R.id.bt_kscs:
                if (MentalityActivity.xlcp.equals("MBTI")) {
                    Intent intent=new Intent(CeShiShuoMingActivity.this,AnswerActivity.class);
                    intent.putExtra("ceshi","MBTI_E");
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent=new Intent(CeShiShuoMingActivity.this,AnswerActivity.class);
                    intent.putExtra("ceshi","SDS_E");
                    startActivity(intent);
                    finish();
                }

                break;
        }
    }

    @Override
    public void EsayMbtiLSsuccess(BaseBean<List<CXEFCBean>> listBaseBean) {
        btLsbg.setEnabled(true);
        if(listBaseBean.code==0)
        {
            if(MentalityActivity.xlcp.equals("MBTI"))
            {
                if(listBaseBean.data.size()>0&&listBaseBean.data!=null)
                {
                    Intent intent=new Intent(CeShiShuoMingActivity.this,MBI_CSActivity.class);
                    intent.putExtra("Mcode",listBaseBean.data.get(0).getTestCode());
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(this, "请先测试", Toast.LENGTH_SHORT).show();
                }

            }
            else
            {
                if(listBaseBean.data.size()>0&&listBaseBean.data!=null)
                {
                    Intent intent=new Intent(CeShiShuoMingActivity.this,HuoLanDeEsayActivity.class);
                    intent.putExtra("Scode",listBaseBean.data.get(0).getTestCode());
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(this, "请先测试", Toast.LENGTH_SHORT).show();
                }
            }
        }
      else
        {
            Toast.makeText(this, listBaseBean.msg, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void EsayMbtiLSfail(Throwable t) {
        btLsbg.setEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        esayMbtiLSPresenter.onDestory();
    }
}
