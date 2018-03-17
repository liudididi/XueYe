package com.example.login_demo;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComlitEFCActivity extends BaseActivity {
    @BindView(R.id.comlitefc_rl_iv)
    ImageView comlitefcRlIv;
    @BindView(R.id.but_chakan)
    Button butChakan;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_minute)
    TextView tvMinute;
    @BindView(R.id.tv_miao)
    TextView tvMiao;
    @BindView(R.id.rl_wancheng)
    RelativeLayout rlWancheng;
    @BindView(R.id.ll_weiwancheng)
    LinearLayout llWeiwancheng;
    private long time = 3;
    private Handler handler;
    private Runnable runnable;

    @Override
    public int getId() {
        return R.layout.activity_comlit_efc;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void InIt() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                time--;
                String formatLongToTimeStr = formatLongToTimeStr(time);
                String[] split = formatLongToTimeStr.split("：");
                for (int i = 0; i < split.length; i++) {
                    if (i == 0) {
                        tvHour.setText(split[0]);
                    }
                    if (i == 1) {
                        tvMinute.setText(split[1]);
                    }
                    if (i == 2) {
                        tvMiao.setText(split[2]);
                    }
                }
                if (time > 0) {
                    handler.postDelayed(this, 1000);
                }else {
                    butChakan.setEnabled(true);
                    rlWancheng.setVisibility(View.VISIBLE);
                    llWeiwancheng.setVisibility(View.GONE);
                    handler.removeCallbacks(this);
                    butChakan.setBackground(getResources().getDrawable(R.drawable.back_capacityyi));

                }
            }
        };
        handler.postDelayed(runnable, 1000);
        butChakan.setEnabled(false);
        butChakan.setBackground(getResources().getDrawable(R.drawable.back_capacityhui));
    }
    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue();
        if (second > 60) {
            minute = second / 60;         //取整
            second = second % 60;         //取余
        }

        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = hour + "：" + minute + "：" + second;
        return strtime;

    }

    @OnClick({R.id.comlitefc_rl_iv, R.id.but_chakan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comlitefc_rl_iv:
                break;
            case R.id.but_chakan:
                intent(this, XueYeGuiHuaActivity.class);
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(runnable!=null){
            handler.postDelayed(runnable, 1000);
        }
    }
}
