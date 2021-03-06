package com.example.login_demo;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.Complete_Adapter;
import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import untils.SPUtils;

public class CompleteWishActivity extends BaseActivity {
    @BindView(R.id.complete_iv_back)
    ImageView completeIvBack;
    @BindView(R.id.ivyi_right)
    ImageView ivyiRight;
    @BindView(R.id.ivyi_next)
    ImageView ivyiNext;
    @BindView(R.id.complete_rl1)
    RelativeLayout completeRl1;
    @BindView(R.id.completelv1)
    ListView completelv1;
    @BindView(R.id.rllistyi)
    RelativeLayout rllistyi;
    @BindView(R.id.complete_tvyi)
    TextView completeTvyi;


    @BindView(R.id.complete_tvsan)
    TextView completeTvsan;
    @BindView(R.id.ivsan_right)
    ImageView ivsanRight;
    @BindView(R.id.ivsan_next)
    ImageView ivsanNext;
    @BindView(R.id.complete_rl3)
    RelativeLayout completeRl3;
    @BindView(R.id.completelv3)
    ListView completelv3;
    @BindView(R.id.rllistsan)
    RelativeLayout rllistsan;

    @BindView(R.id.complete_tvyes)
    TextView completeTvyes;
    @BindView(R.id.complete_school)
    TextView completeSchool;
    @BindView(R.id.complete_major)
    TextView completeMajor;
    @BindView(R.id.complete_zhuan)
    TextView completeZhuan;
    @BindView(R.id.complete_ben)
    TextView completeBen;
    @BindView(R.id.rl_city)
    RelativeLayout rlCity;
    @BindView(R.id.img_cjwh)
    ImageView imgCjwh;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.complete_rl4)
    LinearLayout completeRl4;
    private Boolean lv1msg = false;
    private Boolean lv2msg = false;
    private Boolean lv3msg = false;
    private Boolean lv4msg = false;
    private ArrayList listyi;

    private ArrayList listsan;


    private int cityType = 4;
    private String schoolType = "";
    private String benzhuan = "需要";

    @Override
    public int getId() {
        return R.layout.activity_complete_wish;
    }

    @Override
    public void InIt() {
        initlist();
    }



    private void initlist() {
        schoolType = (String) SPUtils.get(MyApp.context, "schoolType", "");

        if(schoolType !=null&& schoolType !="默认"){
            String cityTypea = (String) SPUtils.get(MyApp.context, "cityType", "");
            String isAccept = (String) SPUtils.get(MyApp.context, "isAccept", "");

            if(schoolType.equals("")){
                completeTvsan.setText("不限");

            }else {
                completeTvsan.setText(schoolType);

            }

            if(cityTypea.equals("4")){
                completeTvyi.setText("不限");
                cityType=4;
            }else  if(cityTypea.equals("1")){
                completeTvyi.setText("一线城市");
                cityType=1;
            }
            else  if(cityTypea.equals("2")){
                completeTvyi.setText("二线城市");
                cityType=2;
            }
            else  if(cityTypea.equals("3")){
                completeTvyi.setText("省会城市");
                cityType=3;
            }

            if(isAccept.equals("需要")){
                completeBen.setBackgroundResource(R.drawable.bg_subject2);
                completeZhuan.setBackgroundResource(R.drawable.bg_subject3);
                benzhuan = "需要";
            }else {
                completeZhuan.setBackgroundResource(R.drawable.bg_subjectbai);
                completeBen.setBackgroundResource(R.drawable.bg_subjectselect);
                benzhuan = "不需要";
            }
         }

        listyi = new ArrayList<>();
        listyi.add("一线城市");
        listyi.add("二线城市");
        listyi.add("省会城市");
        listyi.add("不限");
        Complete_Adapter completelv1_adapter = new Complete_Adapter(listyi, this);
        completelv1.setAdapter(completelv1_adapter);
        completelv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = listyi.get(i).toString();
                rllistyi.setVisibility(View.GONE);
                lv1msg = false;
                ivyiRight.setVisibility(View.VISIBLE);
                ivyiNext.setVisibility(View.GONE);
                completeTvyi.setText(str);
                cityType = i + 1;

            }
        });
        listsan = new ArrayList<>();
        listsan.add("理工类院校");
        listsan.add("综合类院校");
        listsan.add("艺体类院校");
        listsan.add("不限");
        Complete_Adapter completelv3_adapter = new Complete_Adapter(listsan, this);
        completelv3.setAdapter(completelv3_adapter);
        completelv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = listsan.get(i).toString();
                rllistsan.setVisibility(View.GONE);
                lv3msg = false;
                ivsanRight.setVisibility(View.VISIBLE);
                ivsanNext.setVisibility(View.GONE);
                completeTvsan.setText(str);
                if (i == 0) {
                    schoolType = "理工类";
                } else if (i == 1) {
                    schoolType = "综合类";
                } else if (i == 2) {
                    schoolType = "艺体类";
                } else {
                    schoolType = "";
                }
            }
        });

    }

    @OnClick({R.id.complete_iv_back, R.id.rl_city,R.id.img_cjwh, R.id.complete_zhuan, R.id.complete_ben, R.id.complete_school, R.id.complete_major, R.id.complete_rl1, R.id.complete_rl2, R.id.complete_rl3, R.id.complete_rl4, R.id.complete_tvyes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.complete_iv_back:
                finish();
                break;
            case R.id.img_cjwh:
                rlCity.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_city:
                rlCity.setVisibility(View.GONE);
                break;
            case R.id.complete_school:

                completeSchool.setBackgroundResource(R.drawable.bg_subject3);
                completeMajor.setBackgroundResource(R.drawable.bg_subject2);
                break;
            case R.id.complete_major:
                completeSchool.setBackgroundResource(R.drawable.bg_subjectbai);
                completeMajor.setBackgroundResource(R.drawable.bg_subjectselect);

                break;
            case R.id.complete_zhuan:
                completeBen.setBackgroundResource(R.drawable.bg_subject2);
                completeZhuan.setBackgroundResource(R.drawable.bg_subject3);
                benzhuan = "需要";
                break;

            case R.id.complete_ben:
                benzhuan = "不需要";
                completeZhuan.setBackgroundResource(R.drawable.bg_subjectbai);
                completeBen.setBackgroundResource(R.drawable.bg_subjectselect);
                break;

            case R.id.complete_rl1:
                if (lv1msg) {
                    rllistyi.setVisibility(View.GONE);
                    ivyiRight.setVisibility(View.VISIBLE);
                    ivyiNext.setVisibility(View.GONE);
                    lv1msg = false;
                } else {
                    rllistyi.setVisibility(View.VISIBLE);
                    ivyiRight.setVisibility(View.GONE);
                    ivyiNext.setVisibility(View.VISIBLE);
                    lv1msg = true;
                    lv2msg = false;
                    lv3msg = false;
                    lv4msg = false;
                    rllistsan.setVisibility(View.GONE);
                    ivsanRight.setVisibility(View.VISIBLE);
                    ivsanNext.setVisibility(View.GONE);
                }
                break;

            case R.id.complete_rl2:
                if (lv2msg) {

                    lv2msg = false;
                } else {

                    lv2msg = true;
                    lv1msg = false;
                    lv3msg = false;
                    lv4msg = false;

                    rllistyi.setVisibility(View.GONE);
                    ivyiRight.setVisibility(View.VISIBLE);
                    ivyiNext.setVisibility(View.GONE);

                    rllistsan.setVisibility(View.GONE);
                    ivsanRight.setVisibility(View.VISIBLE);
                    ivsanNext.setVisibility(View.GONE);


                }
                break;


            case R.id.complete_rl3:
                if (lv3msg) {
                    rllistsan.setVisibility(View.GONE);
                    ivsanRight.setVisibility(View.VISIBLE);
                    ivsanNext.setVisibility(View.GONE);
                    lv3msg = false;
                } else {
                    rllistsan.setVisibility(View.VISIBLE);
                    ivsanRight.setVisibility(View.GONE);
                    ivsanNext.setVisibility(View.VISIBLE);
                    lv3msg = true;
                    lv1msg = false;
                    lv2msg = false;
                    lv4msg = false;
                    rllistyi.setVisibility(View.GONE);
                    ivyiRight.setVisibility(View.VISIBLE);
                    ivyiNext.setVisibility(View.GONE);


                }
                break;

            case R.id.complete_rl4:
                if (lv4msg) {
                    lv4msg = false;
                } else {
                    lv4msg = true;
                    lv2msg = false;
                    lv3msg = false;
                    lv1msg = false;
                    rllistyi.setVisibility(View.GONE);
                    ivyiRight.setVisibility(View.VISIBLE);
                    ivyiNext.setVisibility(View.GONE);
                    rllistsan.setVisibility(View.GONE);
                    ivsanRight.setVisibility(View.VISIBLE);
                    ivsanNext.setVisibility(View.GONE);
                }
                break;

            case R.id.complete_tvyes:
                if ( benzhuan != null && schoolType != null) {
                    Intent i = new Intent();
                    if(cityType==4){
                        i.putExtra("cityType","");
                    }else {
                        i.putExtra("cityType", cityType + "");
                    }

                    i.putExtra("isAccept", benzhuan);
                    i.putExtra("schoolType", schoolType);
                    setResult(4, i);
                    finish();
                } else {
                    Toast("亲，这些都是必填项");
                }
                break;
        }
    }



}
