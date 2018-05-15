package com.example.login_demo;

import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import me.panpf.swsv.CircularLayout;
import me.panpf.swsv.SpiderWebScoreView;
import untils.ZhiMaScoreViewXQ;

public class XQcsActivity extends BaseActivity {

    @BindView(R.id.xqguihua_iv_back)
    ImageView xqguihuaIvBack;
    @BindView(R.id.rl_xqzhexian)
    RelativeLayout rlXqzhexian;
    @BindView(R.id.tv_daima)
    TextView tv_daima;
    @Override
    public int getId() {
        return R.layout.activity_xqcs;
    }

    @Override
    public void InIt() {
        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        if(code!=null)
        {
            String[] split = code.split(",");
            String s = split[1];
            String[] split1 = s.split(":");
            String result = split1[0];
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
            tv_daima.setText(result);
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
            rlXqzhexian.removeAllViews();
            ZhiMaScoreViewXQ zhiMaScoreViewXQ = new ZhiMaScoreViewXQ(this);
            List<Integer> listfen=new ArrayList<>();
            listfen.add(i1);
            listfen.add(i2);
            listfen.add(i3);
            listfen.add(i4);
            listfen.add(i5);
            listfen.add(i6);
            zhiMaScoreViewXQ.setlistfen(listfen);
            zhiMaScoreViewXQ.setMaxScore(30);
            zhiMaScoreViewXQ.setMinScore(0);
            rlXqzhexian.addView(zhiMaScoreViewXQ);
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



    @OnClick(R.id.xqguihua_iv_back)
    public void onViewClicked() {
        finish();
    }

    private static class Score {
        private int score;


        private Score(int score) {
            this.score = score;
        }
    }
}
