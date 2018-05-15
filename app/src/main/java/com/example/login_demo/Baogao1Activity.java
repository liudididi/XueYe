package com.example.login_demo;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import base.BaseActivity;
import base.BaseBean;
import bean.EsayBaoGaoBean;
import butterknife.BindView;
import presenter.EsayBaoGaoPresenter;
import view.EsayBaoGaoView;

public class Baogao1Activity extends BaseActivity {
    @BindView(R.id.tv_leixing)
    TextView tvLeixing;
    @BindView(R.id.tv_daima)
    TextView tvDaima;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.iv)
    ImageView iv;
    private EsayBaoGaoPresenter esayBaoGaoPresenter;


    @Override
    public int getId() {
        return R.layout.activity_baogao1;
    }

    @Override
    public void InIt() {

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        String daima = intent.getStringExtra("daima");
        final String s = intent.getStringExtra("s");

        tvLeixing.setText(s);
        tvDaima.setText(daima);

        esayBaoGaoPresenter = new EsayBaoGaoPresenter(new EsayBaoGaoView() {
            @Override
            public void EsayBaoGaosuccess(BaseBean<List<EsayBaoGaoBean>> listBaseBean) {
                if(listBaseBean!=null){
                    String basic = listBaseBean.data.get(0).getBasic();
                    String job_development = listBaseBean.data.get(0).getJob_development();
                    String work_environment = listBaseBean.data.get(0).getWork_environment();

                    if (basic != null&&s.equals("基本分析报告")) {
                        tv1.setText(basic);
                    }
                    if (job_development != null&&s.equals("职业发展分析报告")) {
                        tv1.setText(job_development);
                    }
                    if (work_environment != null&&s.equals("工作环境分析报告")) {
                        tv1.setText(work_environment);
                    }

                }

            }

            @Override
            public void EsayBaoGaofail(Throwable t) {

            }
        });
        esayBaoGaoPresenter.EsayBaoGaoPresenter(daima);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        esayBaoGaoPresenter.onDestory();
    }


}
