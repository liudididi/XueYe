package com.example.login_demo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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


    @BindView(R.id.pf_spart)
    Spinner pfSpart;

    @BindView(R.id.pf_tvsubmit)
    TextView pfTvsubmit;

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
    @BindView(R.id.perfect_six)
    RelativeLayout perfectSix;
    @BindView(R.id.perfect_heightschool)
    RelativeLayout perfectHeightschool;
    @BindView(R.id.tv_gknf)
    TextView tvGknf;
    @BindView(R.id.tv_tihsi)
    TextView tvTihsi;
    private ArrayAdapter<String> art_adapter;
    private ArrayAdapter<String> grade_adapter;
    //是否为特长生
    private Boolean isSpecial = false;
    //名字
    private String name;
    private String six;
    private String years;
    private String grade;
    private String subject;
    private String token;
    private List<String> artlist;

    private presenter.perfectMessagePresent perfectMessagePresent;
    private String province;
    private String city;
    private String area;

    @Override
    public int getId() {
        return R.layout.activity_perfect_message;
    }

    @Override
    protected void onResume() {
        super.onResume();

        province = (String) SPUtils.get(MyApp.context, "province", "");
        city = (String) SPUtils.get(MyApp.context, "city", "");
        area = (String) SPUtils.get(MyApp.context, "area", "");
        if (area != null && area.length() > 2) {
            pfTvhightschool.setText(province + city + area);
        }



        SpannableStringBuilder builder = new SpannableStringBuilder(tvTihsi.getText().toString());
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor("#f29600"));
        builder.setSpan(redSpan, 12, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTihsi.setText(builder);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void InIt() {
        initnum();
        artlist = new ArrayList<>();

        artlist.add("否");
        artlist.add("是");


        //下拉框
        art_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, artlist);
        art_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pfSpart.setAdapter(art_adapter);
        pfSpart.setDropDownVerticalOffset(80);







       /* grade_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gradelist);
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
        });*/

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
        years = "请选择";
        six = "男";
        subject = "null";
        grade = "高三";
        token = (String) SPUtils.get(MyApp.context, "token", "");
    }

    @OnClick({R.id.perfect_iv_back, R.id.tv_gknf, R.id.pro_wen, R.id.img_boy, R.id.img_gril, R.id.pro_li, R.id.pf_tvsubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.perfect_iv_back:
                finish();
                break;
            case R.id.tv_gknf:
                View viewe = LayoutInflater.from(perfectMessageActivity.this).inflate(R.layout.dialog_message, null);
                final AlertDialog dialog = new AlertDialog.Builder(perfectMessageActivity.this)
                        .setView(viewe).show();
                RelativeLayout rl_gaoyi = viewe.findViewById(R.id.rl_gaoyi);
                RelativeLayout rl_gaosan = viewe.findViewById(R.id.rl_gaosan);
                RelativeLayout rl_gaoer = viewe.findViewById(R.id.rl_gaoer);
                rl_gaoyi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvGknf.setText("2020年");
                        years = "2020";
                        grade = "高一";
                        dialog.dismiss();
                    }
                });
                rl_gaosan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvGknf.setText("2018年");
                        years = "2018";
                        grade = "高三";
                        dialog.dismiss();
                    }
                });
                rl_gaoer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvGknf.setText("2019年");
                        years = "2019";
                        grade = "高二";
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.img_boy:
                imgBoy.setImageResource(R.drawable.boymr);
                imgGril.setImageResource(R.drawable.gril);
                six = "男";
                break;
            case R.id.img_gril:
                imgBoy.setImageResource(R.drawable.boy);
                imgGril.setImageResource(R.drawable.grilmr);
                six = "女";
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
                if (TextUtils.isEmpty(area)) {
                    Toast("请选择地区");
                    return;
                }
                if (years.equals("请选择")) {
                    Toast("请选择高考年份");
                    return;
                }
                perfectMessagePresent.modifyUserinfoMoble(province, city, area, null, grade, name, six, years, null, isSpecial, token);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SPUtils.remove(MyApp.context, "province");
        SPUtils.remove(MyApp.context, "city");
        SPUtils.remove(MyApp.context, "area");
    }

    @Override
    public void UserinfoSuccess(String msg) {
            Toast(msg);
            SPUtils.put(MyApp.context, "name", name);
            SPUtils.put(MyApp.context, "school", pfTvhightschool.getText().toString());
            SPUtils.put(MyApp.context, "tbarea", province);
            intent(this, HomeActivity.class);
            finish();
    }

    @Override
    public void UserinfoFail(String msg) {
        Toast(msg);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {

            return false;
        }

        return super.onKeyDown(keyCode, event);
    }
}
