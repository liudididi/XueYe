package com.example.login_demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragment.Academy_Fragment;
import fragment.Zhuanye_Fragment;

public class AccurateActivity extends BaseActivity {
    @BindView(R.id.accurate_iv_back)
    ImageView accurateIvBack;
    @BindView(R.id.accurate_academy)
    TextView accurateAcademy;
    @BindView(R.id.accurate_rl_academy)
    RelativeLayout accurateRlAcademy;
    @BindView(R.id.accurate_major)
    TextView accurateMajor;
    @BindView(R.id.accurate_rl_major)
    RelativeLayout accurateRlMajor;
    @BindView(R.id.view_sprint)
    View view_sprint;
    @BindView(R.id.view_reliable)
    View view_reliable;



    private Academy_Fragment academy_fragment;
    private Zhuanye_Fragment zhuanye_fragment;
    public static String fen;
    public static String s1;
    public static String s2;
    public static String s3;
    public static String s4;
    public static String s5;
    public static String s6;
    @Override
    public int getId() {
        return R.layout.activity_accurate;
    }
    @Override
    public void InIt() {
        accurateAcademy.setTextColor(Color.BLACK);
        Intent intent = getIntent();
        fen = intent.getStringExtra("fen");
        s1 = intent.getStringExtra("s1");
        s2 = intent.getStringExtra("s2");
        s3 = intent.getStringExtra("s3");
        s4 = intent.getStringExtra("s4");
        s5 = intent.getStringExtra("s5");
        s6 = intent.getStringExtra("s6");
        System.out.println("参数++++"+fen+s1+s2+s3+s4+s5+s6);
        academy_fragment = new Academy_Fragment();
        zhuanye_fragment = new Zhuanye_Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, academy_fragment).show(academy_fragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, zhuanye_fragment).hide(zhuanye_fragment).commit();
    }

    @OnClick({R.id.accurate_iv_back, R.id.accurate_rl_academy, R.id.accurate_rl_major})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.accurate_iv_back:
                finish();
                break;
            case R.id.accurate_rl_academy:
                accurateAcademy.setTextColor(Color.BLACK);
                accurateMajor.setTextColor(Color.GRAY);
                view_sprint.setVisibility(View.VISIBLE);
                view_reliable.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().show(academy_fragment).commit();
                getSupportFragmentManager().beginTransaction().hide(zhuanye_fragment).commit();
                break;
            case R.id.accurate_rl_major:
                accurateMajor.setTextColor(Color.BLACK);
                accurateAcademy.setTextColor(Color.GRAY);
                view_sprint.setVisibility(View.GONE);
                view_reliable.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().hide(academy_fragment).commit();
                getSupportFragmentManager().beginTransaction().show(zhuanye_fragment).commit();
                break;
        }
    }
}
