package com.example.login_demo;

import android.app.Dialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import base.BaseBean;
import bean.CXEFCBean;
import bean.EsayBaoGaoBean;
import butterknife.BindView;
import butterknife.OnClick;
import presenter.EsayBaoGaoPresenter;
import presenter.EsayMbtiLSPresenter;
import untils.SPUtils;
import view.EsayBaoGaoView;
import view.EsayMbtiLSView;

public class MBI_CSActivity extends BaseActivity implements EsayMbtiLSView {

    @BindView(R.id.tv_dm1)
    TextView tvDm1;
    @BindView(R.id.tv_dm2)
    TextView tvDm2;
    @BindView(R.id.tv_dm3)
    TextView tvDm3;
    @BindView(R.id.tv_dm4)
    TextView tvDm4;
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
    @BindView(R.id.tv_yi)
    TextView tvYi;
    @BindView(R.id.tv_er)
    TextView tvEr;
    @BindView(R.id.tv_san)
    TextView tvSan;
    @BindView(R.id.tv_dm)
    TextView tv_dm;

    @BindView(R.id.rl_jbfxbg)
    RelativeLayout rl_jbfxbg;
    @BindView(R.id.rl_zyfzfxbg)
    RelativeLayout rl_zyfzfxbg;
    @BindView(R.id.rl_gzhjbg)
    RelativeLayout rl_gzhjbg;

    @BindView(R.id.rl_mbititle)
    RelativeLayout rl_mbititle;
    @BindView(R.id.iv_cc)
    ImageView iv_cc;
    @BindView(R.id.mbi_iv_back)
    ImageView mbi_iv_back;

    private EsayMbtiLSPresenter esayMbtiLSPresenter;
    private String token;
    private String result;

    @Override
    public int getId() {
        return R.layout.activity_mbi__cs;
    }

    @Override
    public void InIt() {
        token = (String) SPUtils.get(MyApp.context, "token", "");

        esayMbtiLSPresenter = new EsayMbtiLSPresenter(this);
        esayMbtiLSPresenter.EsayMbtiLSPresenter("MBTI_E", token);
    }

    @Override
    public void EsayMbtiLSsuccess(BaseBean<List<CXEFCBean>> listBaseBean) {
        String testCode = listBaseBean.data.get(0).getTestCode();
        String[] split1 = testCode.split(":");

        result = split1[0];
        tv_dm.setText(result);
        String index1 = result.substring(0, 1);
        String index2 = result.substring(1, 2);
        String index3 = result.substring(2, 3);
        String index4 = result.substring(3, 4);
        tvDm1.setText(index1);
        tvDm2.setText(index2);
        tvDm3.setText(index3);
        tvDm4.setText(index4);

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
        String s5 = split1[5];
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


        if (index1.equals("I")) {
            pb2.setProgress(i5);
        } else {
            pb1.setProgress(30 - i1);
        }
         if (index2.equals("N")) {
            pb4.setProgress(i6);
        } else {
            pb3.setProgress(30 - i2);
        }
         if (index3.equals("F")) {
            pb6.setProgress(i7);
        } else {
            pb5.setProgress(30 - i3);
        }
         if (index4.equals("P")) {
            pb8.setProgress(i8);
        } else {
            pb7.setProgress(30 - i4);
        }
    }

    @Override
    public void EsayMbtiLSfail(Throwable t) {

    }




    @OnClick({R.id.rl_jbfxbg, R.id.rl_zyfzfxbg,R.id.rl_gzhjbg,R.id.iv_cc,R.id.mbi_iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_jbfxbg:

                String s1 = tvYi.getText().toString();
                Intent intent=new Intent(MBI_CSActivity.this,Baogao1Activity.class);
                intent.putExtra("daima",result);
                intent.putExtra("s",s1);
                startActivity(intent);

                break;
            case R.id.rl_zyfzfxbg:
                String s2 = tvEr.getText().toString();
                Intent intent1=new Intent(MBI_CSActivity.this,Baogao1Activity.class);
                intent1.putExtra("daima",result);
                intent1.putExtra("s",s2);
                startActivity(intent1);


                break;
            case R.id.rl_gzhjbg:
                String s3 = tvSan.getText().toString();
                Intent intent2=new Intent(MBI_CSActivity.this,Baogao1Activity.class);
                intent2.putExtra("daima",result);
                intent2.putExtra("s",s3);
                startActivity(intent2);


                break;
            case R.id.iv_cc:
                Intent intent3=new Intent(MBI_CSActivity.this,CeShiShuoMingActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.mbi_iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        esayMbtiLSPresenter.onDestory();

    }

}
