package com.example.login_demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import base.BaseActivity;
import base.BaseBean;
import bean.XGcsBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.XGcsPresenter;
import view.XGcsView;

public class XGcsActivity extends BaseActivity implements XGcsView{

    @BindView(R.id.pb1)
    ProgressBar pb1;
    @BindView(R.id.pb2)
    ProgressBar pb2;
    @BindView(R.id.pb3)
    ProgressBar pb3;
    @BindView(R.id.pb4)
    ProgressBar pb4;
    @BindView(R.id.pb5)
    ProgressBar pb5;
    @BindView(R.id.pb6)
    ProgressBar pb6;
    @BindView(R.id.pb7)
    ProgressBar pb7;
    @BindView(R.id.pb8)
    ProgressBar pb8;
    @BindView(R.id.xgguihua_iv_back)
    ImageView xgguihuaIvBack;

    @BindView(R.id.tv_gexing)
    TextView tvGexing;
    @BindView(R.id.tv_mangdian)
    TextView tvMangdian;
    @BindView(R.id.tv_youshi)
    TextView tvYoushi;
    @BindView(R.id.tv_lieshi)
    TextView tvLieshi;
    @BindView(R.id.tv_tezhi)
    TextView tvTezhi;
    @BindView(R.id.tv_gongxian)
    TextView tvGongxian;

    @BindView(R.id.tv_quexian)
    TextView tvQuexian;
    @BindView(R.id.tv_huanjing)
    TextView tvHuanjing;
    @BindView(R.id.tv_jianyi)
    TextView tvJianyi;
    @BindView(R.id.tv_daima)
    TextView tv_daima;
    private XGcsPresenter xGcsPresenter;

    @Override
    public int getId() {
        return R.layout.activity_xgcs;
    }

    @Override
    public void InIt() {

        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        if(code!=null)
        {
            String[] split = code.split(",");
            String s = split[0];
            String[] split1 = s.split(":");
            String result = split1[0];
            //E
            String s1 = split1[1];
            int i1 = Integer.parseInt(s1.substring(1));
            //S
            String s2 = split1[2];
            int i2 = Integer.parseInt(s2.substring(1));
            //T
            String s3 = split1[3];
            int i3 = Integer.parseInt(s3.substring(1));
            //j
            String s4 = split1[4];
            int i4 = Integer.parseInt(s4.substring(1));
            //I
            String s5= split1[5];
            int i5 = Integer.parseInt(s5.substring(1));
            //N
            String s6 = split1[6];
            int i6 = Integer.parseInt(s6.substring(1));
            //F
            String s7 = split1[7];
            int i7 = Integer.parseInt(s7.substring(1));
            //P
            String s8 = split1[8];
            int i8 = Integer.parseInt(s8.substring(1));
            tv_daima.setText(result);
            String index1 = result.substring(0, 1);
            if (index1.equals("I")) {
                pb2.setProgress(i5);
            } else {
                pb1.setProgress(30 - i1);
            }
            String index2 = result.substring(1, 2);
            if (index2.equals("N")) {
                pb4.setProgress(i6);
            } else {
                pb3.setProgress(30 - i2);
            }
            String index3 = result.substring(2, 3);
            if (index3.equals("F")) {
                pb6.setProgress(i7);
            } else {
                pb5.setProgress(30 - i3);
            }
            String index4 = result.substring(3, 4);
            if (index4.equals("P")) {
                pb8.setProgress(i8);
            } else {
                pb7.setProgress(30 - i4);
            }
            xGcsPresenter = new XGcsPresenter(this);
            xGcsPresenter.XGcsPresenter(result);
        }



    }

    @OnClick(R.id.xgguihua_iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void XGcssuccess(BaseBean<List<XGcsBean>> listBaseBean) {
        List<XGcsBean> data = listBaseBean.data;
        for (int i = 0; i < data.size(); i++) {
            //个人发展建议
            String proposal = data.get(i).getProposal();
            //可能存在的盲点
            String blind = data.get(i).getBlind();
            //工作中的优势
            String advantage = data.get(i).getAdvantage();
            //适合的工作环境
            String job_scene = data.get(i).getJob_scene();
            //对组织缺陷
            String defect = data.get(i).getDefect();
            //对组织贡献
            String contribution = data.get(i).getContribution();
            //个性描述
            String personality = data.get(i).getPersonality();
            //工作中的劣势
            String inferiority = data.get(i).getInferiority();
            //适合岗位的特质
            String job = data.get(i).getJob();

            tvGexing.setText(personality);
            tvMangdian.setText(blind);
            tvYoushi.setText(advantage);
            tvLieshi.setText(inferiority);
             tvTezhi.setText(job);
            tvGongxian.setText(contribution);

            tvQuexian.setText(defect);
            tvHuanjing.setText(job_scene);
            tvJianyi.setText(proposal);

        }
    }

    @Override
    public void XGcsfail(Throwable t) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        xGcsPresenter.onDestory();
    }
}
