package com.example.login_demo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.perfectMessagePresent;
import untils.SPUtils;
import view.perfectMessageView;

public class perfectMessageActivity extends BaseActivity implements perfectMessageView {

    @BindView(R.id.perfect_iv_back)
    ImageView perfectIvBack;
    @BindView(R.id.pf_edname)
    EditText pfEdname;

    @BindView(R.id.pf_tvyear)
    TextView pfTvyear;
    @BindView(R.id.pf_spart)
    Spinner pfSpart;

    @BindView(R.id.pf_tvsubmit)
    TextView pfTvsubmit;
    @BindView(R.id.pf_spgrade)
    Spinner pfSpgrade;
    @BindView(R.id.pf_tvhightschool)
    TextView pfTvhightschool;
    @BindView(R.id.pro_wen)
    TextView proWen;
    @BindView(R.id.pro_li)
    TextView proLi;
    @BindView(R.id.img_boy)
    ImageView imgBoy;
    @BindView(R.id.img_gril)
    ImageView imgGril;
    private ArrayAdapter<String> art_adapter;
    private ArrayAdapter<String> grade_adapter;
    //是否为特长生
    private Boolean isSpecial=false;
    //名字
    private String name;
    private String six;
    private String years;
    private String highschool;
    private String grade;
    private String subject;
    private String token;
    private List<String> artlist;
    private List<String> gradelist;
    private presenter.perfectMessagePresent perfectMessagePresent;

    @Override
    public int getId() {
        return R.layout.activity_perfect_message;
    }

    @Override
    protected void onResume() {
        super.onResume();
        highschool = (String) SPUtils.get(MyApp.context, "highschool", "");
        if (highschool != null && highschool.length() > 2) {
            pfTvhightschool.setText(highschool);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void InIt() {
        initnum();
        artlist = new ArrayList<>();
        gradelist = new ArrayList<>();
        artlist.add("否");
        artlist.add("是");
        gradelist.add("高三");
        gradelist.add("高二");
        gradelist.add("高一");

        //下拉框
        art_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, artlist);
        art_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pfSpart.setAdapter(art_adapter);

        pfSpart.setDropDownVerticalOffset(80);
        grade_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gradelist);
        grade_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pfSpgrade.setAdapter(grade_adapter);
        pfSpgrade.setDropDownVerticalOffset(80);
        pfSpgrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String str = (String) pfSpgrade.getSelectedItem();
                grade = str;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        //改变值
        pfSpart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //拿到被选择项的值
                String str = (String) pfSpart.getSelectedItem();
                if (str.equals("是")) {
                    isSpecial = true;
                } else {
                    isSpecial = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });




        pfTvhightschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(perfectMessageActivity.this, SelectProviceActivity.class);
            }
        });


        perfectMessagePresent = new perfectMessagePresent(this);


    }

    private void initnum() {
        years = pfTvyear.getText().toString();
        six = "男";
        subject = "文科";
        grade = "高三";
        token = (String) SPUtils.get(MyApp.context, "token", "");
    }

    @OnClick({R.id.perfect_iv_back, R.id.pro_wen, R.id.img_boy, R.id.img_gril, R.id.pro_li, R.id.pf_tvsubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.perfect_iv_back:
                finish();
                break;
            case R.id.img_boy:
                imgBoy.setImageResource(R.drawable.boymr);
                imgGril.setImageResource(R.drawable.gril);
                   six="男";
                break;
            case R.id.img_gril:
                imgBoy.setImageResource(R.drawable.boy);
                imgGril.setImageResource(R.drawable.grilmr);
                six="女";
                break;
            case R.id.pro_wen:
                proWen.setBackgroundResource(R.drawable.bg_subject3);
                proLi.setBackgroundResource(R.drawable.bg_subject2);
                subject = "文科";


                break;
            case R.id.pro_li:
                proWen.setBackgroundResource(R.drawable.bg_subjectbai);
                proLi.setBackgroundResource(R.drawable.bg_subjectselect);

                subject = "理科";
                break;
            case R.id.pf_tvsubmit:
                name = pfEdname.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast("请填写名字");
                    return;
                }
                if (TextUtils.isEmpty(highschool)) {
                    Toast("请选择学校");
                    return;
                }
                String province = (String) SPUtils.get(MyApp.context, "province", "");
                String city = (String) SPUtils.get(MyApp.context, "city", "");
                String area = (String) SPUtils.get(MyApp.context, "area", "");
                perfectMessagePresent.modifyUserinfoMoble(province, city, area, highschool, grade, name, six, years, subject, isSpecial, token);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SPUtils.remove(MyApp.context, "province");
        SPUtils.remove(MyApp.context, "city");
        SPUtils.remove(MyApp.context, "area");
        SPUtils.remove(MyApp.context, "highschool");
    }

    @Override
    public void UserinfoSuccess(String msg) {
        if (msg.equals("success")) {
            Toast(msg);
            SPUtils.put(MyApp.context, "name", name);
            SPUtils.put(MyApp.context, "school", highschool);
            finish();
        } else {
            Toast(msg);
        }
    }

    @Override
    public void UserinfoFail(String msg) {
        Toast(msg);
    }



}
