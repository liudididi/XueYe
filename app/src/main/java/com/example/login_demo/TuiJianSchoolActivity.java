package com.example.login_demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meg7.widget.CustomShapeImageView;

import java.util.ArrayList;
import java.util.List;

import adapter.TuiJianAdapter;
import base.BaseActivity;
import bean.TuiJianBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.TuiJianPresent;
import untils.FlowLayout;
import untils.ListViewForScrollView;
import untils.SPUtils;
import view.TuiJianView;

public class TuiJianSchoolActivity extends BaseActivity implements TuiJianView {


    @BindView(R.id.tuijian_iv)
    CustomShapeImageView tuijianIv;
    @BindView(R.id.tuijian_name)
    TextView tuijianName;

    @BindView(R.id.tuijian_zh)
    TextView tuijianZh;
    @BindView(R.id.tuijian_iv_back)
    ImageView tuijianIvBack;
    @BindView(R.id.rl_tjschool)
    RelativeLayout rlTjschool;
    @BindView(R.id.fl_tjly)
    FlowLayout flTjly;
    @BindView(R.id.ll_ling)
    LinearLayout llLing;
    @BindView(R.id.tv_ktefc)
    TextView tvKtefc;
    @BindView(R.id.tv_jixu)
    TextView tvJixu;
    @BindView(R.id.ll_two)
    LinearLayout llTwo;
    @BindView(R.id.tj_hour)
    TextView tjHour;
    @BindView(R.id.tj_minute)
    TextView tjMinute;
    @BindView(R.id.tj_miao)
    TextView tjMiao;
    @BindView(R.id.ll_san)
    LinearLayout llSan;
    @BindView(R.id.list_zy)
    ListViewForScrollView listZy;
    @BindView(R.id.ll_one)
    LinearLayout llOne;
    private String schoolname;
    private TuiJianPresent tuiJianPresent;
    private Handler handler;
    private Runnable runnable;
    private long time = 3;
    private String tbarea;
    private String tbmaxfen;
    private String tbsubtype;
    private String token;
    private Boolean tjly = true;

    @Override
    public int getId() {
        return R.layout.activity_tui_jian_school;
    }

    @Override
    public void InIt() {
        schoolname = getIntent().getStringExtra("schoolname");
        tuijianName.setText(schoolname);

        String schoolurl = getIntent().getStringExtra("schoolurl");
        Glide.with(this).load(schoolurl).into(tuijianIv);

        tuiJianPresent = new TuiJianPresent(this);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                time--;
                String formatLongToTimeStr = formatLongToTimeStr(time);
                String[] split = formatLongToTimeStr.split("：");
                for (int i = 0; i < split.length; i++) {
                    if (i == 0) {
                        tjHour.setText(split[0]);
                    }
                    if (i == 1) {
                        tjMinute.setText(split[1]);
                    }
                    if (i == 2) {
                        tjMiao.setText(split[2]);
                    }
                }
                if (time > 0) {

                    handler.postDelayed(this, 1000);
                } else {
                    handler.removeCallbacks(this);
                    tuiJianPresent.GetTuijian(schoolname, tbarea, tbsubtype, tbmaxfen, token);
                }
            }
        };
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
//199415
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = hour + "：" + minute + "：" + second;
        return strtime;

    }


    @Override
    protected void onResume() {
        super.onResume();
        tbmaxfen = (String) SPUtils.get(MyApp.context, "tbmaxfen", "500");
        tbarea = (String) SPUtils.get(MyApp.context, "tbarea", "北京市");
        tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "文科");
        token = (String) SPUtils.get(MyApp.context, "token", "");
        tuiJianPresent.GetTuijian(schoolname, tbarea, tbsubtype, tbmaxfen, token);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tuiJianPresent.onDestory();
    }

    @OnClick({R.id.tuijian_iv_back, R.id.rl_tjschool, R.id.tv_ktefc, R.id.tv_jixu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tuijian_iv_back:
                finish();
                break;
            case R.id.rl_tjschool:
                Intent intent = new Intent(this, SchoolDetailActivity.class);
                intent.putExtra("schoolname", schoolname);
                startActivity(intent);
                break;
            case R.id.tv_ktefc:
                Intent intent1 = new Intent(TuiJianSchoolActivity.this, BuyEFCActivity.class);
                intent1.putExtra("price", "698");
                startActivity(intent1);
                break;

            case R.id.tv_jixu:
                Intent intent2 = new Intent(TuiJianSchoolActivity.this, EFCJieSuoActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void TuijianSuccess(TuiJianBean tuiJianBean) {
        if (tuiJianBean != null) {
            if(tuiJianBean.getYear()!=null){
                tuijianZh.setText(tuiJianBean.getYear() + "年" + tuiJianBean.getTime() + "最低分" + tuiJianBean.getMinScore());
            }else {
                tuijianZh.setText("暂无数据");
            }
            List<String> list = new ArrayList<>();
            if (tjly) {
                List<String> recommend = tuiJianBean.getRecommend();
                if (recommend != null && recommend.size() > 0) {
                    list.addAll(recommend);
                }
                List<String> vipRecommend = tuiJianBean.getVipRecommend();
                if (vipRecommend != null && vipRecommend.size() > 0) {
                    list.addAll(vipRecommend);
                }
                System.out.println("listsize=="+list.size());
                if(list.size()==0) {
                    list.add("暂无数据");
                }
                flTjly.settuijianListData(list);

                tjly = false;
            }
            String efcState = tuiJianBean.getEfcState();
            if (efcState.equals("0")) {
                llLing.setVisibility(View.VISIBLE);
                llSan.setVisibility(View.GONE);
                llTwo.setVisibility(View.GONE);
                llOne.setVisibility(View.GONE);
            } else if (efcState.equals("1")) {
                List<TuiJianBean.EfcRecommendMajorEntityBean> efcRecommendMajorEntity = tuiJianBean.getEfcRecommendMajorEntity();
                if(efcRecommendMajorEntity.size()>0&&efcRecommendMajorEntity!=null){
                    TuiJianAdapter  tuiJianAdapter=new TuiJianAdapter(efcRecommendMajorEntity,this);
                    listZy.setAdapter(tuiJianAdapter);
                }
                llOne.setVisibility(View.VISIBLE);
                llLing.setVisibility(View.GONE);
                llSan.setVisibility(View.GONE);
                llTwo.setVisibility(View.GONE);

            } else if (efcState.equals("2")) {
                llTwo.setVisibility(View.VISIBLE);
                llLing.setVisibility(View.GONE);
                llSan.setVisibility(View.GONE);
                llOne.setVisibility(View.GONE);
            } else if (efcState.equals("3")) {
                String countDown = tuiJianBean.getCountDown();
                time = Integer.parseInt(countDown);
                handler.postDelayed(runnable, 1000);
                llSan.setVisibility(View.VISIBLE);
                llTwo.setVisibility(View.GONE);
                llLing.setVisibility(View.GONE);
                llOne.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void TuijianFail(String msg) {

    }





}
