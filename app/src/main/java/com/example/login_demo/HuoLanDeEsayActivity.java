package com.example.login_demo;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import base.BaseBean;
import bean.CXEFCBean;
import bean.EsaySdsBean;
import butterknife.BindView;
import butterknife.OnClick;
import me.panpf.swsv.CircularLayout;
import me.panpf.swsv.SpiderWebScoreView;
import presenter.EsayHuanLanDePresenter;
import presenter.EsayMbtiLSPresenter;
import untils.SPUtils;
import view.EsayHuanLanDeView;
import view.EsayMbtiLSView;

public class HuoLanDeEsayActivity extends BaseActivity implements EsayMbtiLSView {

    @BindView(R.id.tv_dm1)
    TextView tvDm1;
    @BindView(R.id.tv_dm2)
    TextView tvDm2;
    @BindView(R.id.tv_dm3)
    TextView tvDm3;

    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_2)
    TextView tv_2;
    @BindView(R.id.tv_3)
    TextView tv_3;

    @BindView(R.id.tv_xing1)
    TextView tv_xing1;
    @BindView(R.id.tv_xing2)
    TextView tv_xing2;
    @BindView(R.id.tv_xing3)
    TextView tv_xing3;

    @BindView(R.id.tv_xing1_nr)
    TextView tv_xing1_nr;
    @BindView(R.id.tv_xing2_nr)
    TextView tv_xing2_nr;
    @BindView(R.id.tv_xing3_nr)
    TextView tv_xing3_nr;

    @BindView(R.id.tv_xing1_fx)
    TextView tv_xing1_fx;
    @BindView(R.id.tv_xing2_fx)
    TextView tv_xing2_fx;
    @BindView(R.id.tv_xing3_fx)
    TextView tv_xing3_fx;
    @BindView(R.id.sds_iv_back)
    ImageView sdsIvBack;
    @BindView(R.id.ll5)
    LinearLayout ll5;
    @BindView(R.id.iv_cc)
    ImageView ivCc;


    private EsayMbtiLSPresenter esayMbtiLSPresenter;
    private String token;
    private String result;
    private EsayHuanLanDePresenter esayHuanLanDePresenter;

    @Override
    public int getId() {
        return R.layout.activity_huo_lan_de_esay;
    }

    @Override
    public void InIt() {

        token = (String) SPUtils.get(MyApp.context, "token", "");
        esayMbtiLSPresenter = new EsayMbtiLSPresenter(this);
        String scode = getIntent().getStringExtra("Scode");
        if(scode!=null)
        {
            fx(scode);
        }
        else
        {
            esayMbtiLSPresenter.EsayMbtiLSPresenter("SDS_E", token);
        }


    }

    @Override
    public void EsayMbtiLSsuccess(BaseBean<List<CXEFCBean>> listBaseBean) {
        String testCode = listBaseBean.data.get(0).getTestCode();
        fx(testCode);
    }

    private void fx(String textcode) {
        String[] split1 = textcode.split(":");
        result = split1[0];
        String index1 = result.substring(0, 1);
        String index2 = result.substring(1, 2);
        String index3 = result.substring(2, 3);
        tvDm1.setText(index1);
        tvDm2.setText(index2);
        tvDm3.setText(index3);

        String s1 = split1[1];
        int i1 = Integer.parseInt(s1.substring(1));
        String s2 = split1[2];
        int i2 = Integer.parseInt(s2.substring(1));
        String s3 = split1[3];
        int i3 = Integer.parseInt(s3.substring(1));
        String s4 = split1[4];
        int i4 = Integer.parseInt(s4.substring(1));
        String s5 = split1[5];
        int i5 = Integer.parseInt(s5.substring(1));
        String s6 = split1[6];
        int i6 = Integer.parseInt(s6.substring(1));

        SpiderWebScoreView spiderWebScoreView1 = (SpiderWebScoreView) findViewById(R.id.spiderWeb_mainActivity_1);
        CircularLayout circularLayout1 = (CircularLayout) findViewById(R.id.layout_mainActivity_circular1);
        Score[] scores = new Score[]{
                new Score(i1),
                new Score(i2),
                new Score(i3),
                new Score(i4),
                new Score(i5),
                new Score(i6),
        };
        setup(spiderWebScoreView1, circularLayout1, scores);
        esayHuanLanDePresenter = new EsayHuanLanDePresenter(new EsayHuanLanDeView() {
            @Override
            public void EsayHuanLanDesuccess(BaseBean<List<EsaySdsBean>> listBaseBean) {

                EsaySdsBean esaySdsBean = listBaseBean.data.get(0);
                String test_code = esaySdsBean.getTest_code();
                String standby1 = esaySdsBean.getStandby1();
                String test_analysis = esaySdsBean.getTest_analysis();
                String test_job = esaySdsBean.getTest_job();
                tv_1.setText(test_code);
                tv_xing1.setText(standby1);
                tv_xing1_nr.setText(test_analysis);
                tv_xing1_fx.setText(test_job);

                EsaySdsBean esaySdsBean1 = listBaseBean.data.get(1);
                String test_code1 = esaySdsBean1.getTest_code();
                String standby11 = esaySdsBean1.getStandby1();
                String test_analysis1 = esaySdsBean1.getTest_analysis();
                String test_job1 = esaySdsBean1.getTest_job();
                tv_2.setText(test_code1);
                tv_xing2.setText(standby11);
                tv_xing2_nr.setText(test_analysis1);
                tv_xing2_fx.setText(test_job1);
                EsaySdsBean esaySdsBean2 = listBaseBean.data.get(2);
                String test_code2 = esaySdsBean2.getTest_code();
                String standby12 = esaySdsBean2.getStandby1();
                String test_analysis2 = esaySdsBean2.getTest_analysis();
                String test_jo2b = esaySdsBean2.getTest_job();
                tv_3.setText(test_code2);
                tv_xing3.setText(standby12);
                tv_xing3_nr.setText(test_analysis2);
                tv_xing3_fx.setText(test_jo2b);

            }

            @Override
            public void EsayHuanLanDefail(Throwable t) {

            }
        });
        esayHuanLanDePresenter.EsayHuanLanDePresenter(result);
    }

    @Override
    public void EsayMbtiLSfail(Throwable t) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        esayMbtiLSPresenter.onDestory();
        esayHuanLanDePresenter.onDestory();
    }



    @OnClick({R.id.sds_iv_back, R.id.iv_cc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sds_iv_back:
                finish();
                break;
            case R.id.iv_cc:
                ivCc.setEnabled(false);
                Intent intent3=new Intent(HuoLanDeEsayActivity.this,CeShiShuoMingActivity.class);
                startActivity(intent3);
                finish();
                break;
        }
    }

    private void setup(SpiderWebScoreView spiderWebScoreView, CircularLayout circularLayout, Score... scores) {
        float[] scoreArray = new float[scores.length];
        for (int w = 0; w < scores.length; w++) {
            scoreArray[w] = scores[w].score;
        }
        spiderWebScoreView.setScores(30f, scoreArray);

        circularLayout.removeAllViews();
        List<String> list = new ArrayList<>();


        list.add("A艺术类");
        list.add("C常规");
        list.add("E企业");
        list.add("I研究");
        list.add("S社会");
        list.add("R实际");
        for (int i = 0; i < list.size(); i++) {
            TextView scoreTextView = new TextView(this);
            scoreTextView.setTextColor(Color.BLACK);
            scoreTextView.setText(list.get(i));
            circularLayout.addView(scoreTextView);
        }

    }
    private static class Score {
        private int score;


        private Score(int score) {
            this.score = score;
        }
    }
}
