package com.example.login_demo;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.Spinner_Adapter;
import base.BaseActivity;
import base.BaseBean;
import bean.NumBean;
import bean.ZYNumBean;
import butterknife.BindView;
import butterknife.OnClick;
import presenter.NumPresenter;
import untils.FlowLayout;
import untils.SPUtils;
import untils.ZYZhiMaScoreView;
import view.NumView;

public class NumActivity extends BaseActivity implements NumView {

    @BindView(R.id.num_iv_back)
    ImageView numIvBack;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_lishu)
    TextView tvLishu;
    @BindView(R.id.schoold_lq)
    TextView schoold_lq;
    @BindView(R.id.schoold_jj)
    TextView schoold_jj;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.ll_ks)
    LinearLayout ll_ks;
    @BindView(R.id.tv_ks)
    TextView tv_ks;
    @BindView(R.id.iv_ks)
    ImageView iv_ks;
    @BindView(R.id.iv_ks_xia)
    ImageView iv_ks_xia;

    @BindView(R.id.tv_zy_name)
    TextView tv_zy_name;
    @BindView(R.id.zhexian_ll)
    LinearLayout zhexian_ll;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.view_back)
    View view_back;

    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.rla)
    RelativeLayout rla;
    @BindView(R.id.pb)
    ProgressBar pb;
    private NumPresenter numPresenter;
    private boolean flag = true;
    private int xiabiao;
    private String zyname;
    private String km;
    private String address;
    private String schoolname;
    private ZYZhiMaScoreView zhiMaScoreView;
    private String tbarea;
    private String tbsubtype;
    private FlowLayout fl;


    @Override
    public int getId() {
        return R.layout.activity_num;
    }

    @Override
    public void InIt() {

        tbarea = (String) SPUtils.get(MyApp.context, "tbarea", "北京市");
        tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "文科");
        if(tbsubtype.equals("文科"))
        {
            schoold_lq.setTextColor(Color.WHITE);
            schoold_lq.setBackgroundResource(R.drawable.back_schooldlan);

        }
        else
        {

            schoold_jj.setTextColor(Color.WHITE);
            schoold_jj.setBackgroundResource(R.drawable.back_schooldlan);
        }
        //学校名
        schoolname = getIntent().getStringExtra("schoolname");
        //专业名称
        String major = getIntent().getStringExtra("major");
        zyname = major;
        km = tbsubtype;
        address = tbarea;
        xiabiao = getIntent().getIntExtra("xiabiao", 0);
        numPresenter = new NumPresenter(NumActivity.this);
        numPresenter.NumPresenter(schoolname, tbsubtype, tbarea);
        numPresenter.ZYNumPresenter(schoolname, km, address, zyname);
        tv_zy_name.setText(zyname);
        if (flag == false) {
            sv.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
        } else {
            sv.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });
        }
    }

    private void initList() {
        final ArrayList<String> arealist = new ArrayList<>();
        arealist.add("北京市");
        arealist.add("天津市");
        arealist.add("河北省");
        arealist.add("山西省");
        arealist.add("内蒙古");
        arealist.add("辽宁省");
        arealist.add("吉林省");
        arealist.add("黑龙江");
        arealist.add("上海市");
        arealist.add("江苏省");
        arealist.add("浙江省");
        arealist.add("安徽省");
        arealist.add("福建省");
        arealist.add("江西省");
        arealist.add("山东省");
        arealist.add("河南省");
        arealist.add("湖北省");
        arealist.add("湖南省");
        arealist.add("广东省");
        arealist.add("广西省");
        arealist.add("海南省");
        arealist.add("重庆市");
        arealist.add("四川省");
        arealist.add("贵州省");
        arealist.add("云南省");
        arealist.add("西藏");
        arealist.add("陕西省");
        arealist.add("甘肃省");
        arealist.add("青海省");
        arealist.add("宁夏");
        arealist.add("新疆");
        arealist.add("台湾");
        arealist.add("澳门");

        Spinner_Adapter spinner_adapter = new Spinner_Adapter(arealist, NumActivity.this);
        lv.setAdapter(spinner_adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view_back.setVisibility(View.GONE);

                String s = arealist.get(i).toString();
                if (s != null) {
                    tv_ks.setText(s);
                    lv.setVisibility(View.GONE);
                    iv_ks.setVisibility(View.VISIBLE);
                    iv_ks_xia.setVisibility(View.GONE);
                    flag = true;
                    address = s;
                    numPresenter.ZYNumPresenter(schoolname, km, address, zyname);
                }
            }
        });
    }

    @OnClick({R.id.num_iv_back, R.id.tv_lishu, R.id.schoold_lq, R.id.schoold_jj, R.id.ll_ks})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.num_iv_back:
                finish();
                break;
            case R.id.schoold_lq:
                 schoold_lq.setTextColor(Color.WHITE);
                schoold_lq.setBackgroundResource(R.drawable.back_schooldlan);
                schoold_jj.setTextColor(Color.BLACK);
                schoold_jj.setBackgroundResource(R.drawable.back_schoold);
                km = "文科";
                numPresenter.ZYNumPresenter(schoolname, km, address, zyname);
                tbsubtype = km;

                numPresenter.NumPresenter(schoolname, tbsubtype, tbarea);
                 break;
            case R.id.schoold_jj:

                schoold_lq.setTextColor(Color.BLACK);
                schoold_lq.setBackgroundResource(R.drawable.back_schoold);
                schoold_jj.setTextColor(Color.WHITE);
                schoold_jj.setBackgroundResource(R.drawable.back_schooldlan);
                km = "理科";

                numPresenter.ZYNumPresenter(schoolname, km, address, zyname);
                tbsubtype = km;
                numPresenter.NumPresenter(schoolname, tbsubtype, tbarea);

                break;
            case R.id.ll_ks:
                if (flag) {
                    initList();
                    lv.setVisibility(View.VISIBLE);
                    iv_ks.setVisibility(View.GONE);
                    iv_ks_xia.setVisibility(View.VISIBLE);
                    view_back.setVisibility(View.VISIBLE);
                    flag = false;
                } else {
                    lv.setVisibility(View.GONE);
                    iv_ks.setVisibility(View.VISIBLE);
                    iv_ks_xia.setVisibility(View.GONE);
                    view_back.setVisibility(View.GONE);
                    flag = true;
                }
                break;
        }
    }

    @Override
    public void Numsuccess(BaseBean<List<NumBean>> listBaseBean) {
        pb.setVisibility(View.GONE);

        if(listBaseBean.data.size()>0&&listBaseBean.data!=null)
        {
            rla.setVisibility(View.VISIBLE);
            List<NumBean.MajorsBean> major = listBaseBean.data.get(0).getMajors();
            if (major.size() > 0 && major != null) {
                rla.removeAllViews();
                fl = new FlowLayout(this);
                List<String> list = new ArrayList<>();
                for (int i = 0; i < major.size(); i++) {
                    //专业名称
                    String major1 = major.get(i).getMajor();
                    if (major1.length() > 30) {
                        String substring = major1.substring(0, 30);
                        major1 = substring;
                    }
                    list.add(major1);
                }

                fl.setListZY(list);
                fl.setbianli(xiabiao);
                fl.setOnClick(new FlowLayout.OnClick() {
                    @Override
                    public void TagClick(String text, int postion) {
                        fl.setbianli(postion);
                        zyname = text;
                        numPresenter.ZYNumPresenter(schoolname, km, address, zyname);
                        tv_zy_name.setText(zyname);
                    }
                });
                rla.addView(fl);

                //隶属部
                String father = listBaseBean.data.get(0).getFather();
                String name = listBaseBean.data.get(0).getName();
                String time = listBaseBean.data.get(0).getTime();
                String stuProvince = listBaseBean.data.get(0).getAddress();
                tvAddress.setText(stuProvince);
                tvLishu.setText(father);
                tvName.setText(name);
                tvYear.setText(time);

                for (int i = 0; i < list.size(); i++) {

                    if(xiabiao==i)
                    {
                        String s = list.get(xiabiao).toString();
                        zyname=s;
                        tv_zy_name.setText(zyname);
                        numPresenter.ZYNumPresenter(schoolname, km, address, zyname);
                        return;
                    }
                    else
                    {
                        String s = list.get(0).toString();
                        zyname=s;
                        tv_zy_name.setText(zyname);
                    }

                }
            }
        }

        else
        {
            rla.setVisibility(View.GONE);
            tv_zy_name.setText("暂无数据");
        }
    }

    @Override
    public void Numfail(Throwable t) {

    }

    @Override
    public void ZYNumsuccess(BaseBean<List<ZYNumBean>> listBaseBean) {
        pb.setVisibility(View.GONE);
        List<ZYNumBean> data = listBaseBean.data;
        zhexian_ll.removeAllViews();
        int max = 0;
        int min = 0;
        zhiMaScoreView = new ZYZhiMaScoreView(NumActivity.this);
        List<Integer> listfen = new ArrayList<>();
        listfen.add(0);
        listfen.add(0);
        listfen.add(0);
        listfen.add(0);
        listfen.add(0);
        if (data.size() > 0 && data != null) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getYear().equals("2013")) {
                    listfen.set(0, Integer.parseInt(data.get(i).getScore()));
                }
                if (data.get(i).getYear().equals("2014")) {
                    listfen.set(1, Integer.parseInt(data.get(i).getScore()));
                }
                if (data.get(i).getYear().equals("2015")) {
                    listfen.set(2, Integer.parseInt(data.get(i).getScore()));
                }
                if (data.get(i).getYear().equals("2016")) {
                    listfen.set(3, Integer.parseInt(data.get(i).getScore()));
                }
                if (data.get(i).getYear().equals("2017")) {
                    listfen.set(4, Integer.parseInt(data.get(i).getScore()));
                }
            }
        } else {
            listfen.set(0, 0);
            listfen.set(1, 0);
            listfen.set(2, 0);
            listfen.set(3, 0);
            listfen.set(4, 0);
        }
        if (listfen.get(0) >= listfen.get(1)) {
            max = listfen.get(0) + 100;
            min = listfen.get(1) - 50;
        } else {
            max = listfen.get(1) + 100;
            min = listfen.get(0) - 50;
        }
        if (min < 0) {
            min = 0;
        }
        if (max > 700) {
            max = 700;
        }
        zhiMaScoreView.setlistfen(listfen);
        zhiMaScoreView.setMaxScore(max);
        zhiMaScoreView.setMinScore(min);
        zhexian_ll.addView(zhiMaScoreView);
    }

    @Override
    public void ZYNumfail(Throwable t) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        numPresenter.onDestory();
    }



}
