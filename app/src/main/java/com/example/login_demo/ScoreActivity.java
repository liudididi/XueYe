package com.example.login_demo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import adapter.ProvinceAdapter2;

import adapter.Spinner_Adapter;
import base.BaseActivity;
import base.BaseBean;
import bean.ScoreBean1;
import bean.ScoreBean2;
import butterknife.BindView;
import butterknife.OnClick;
import presenter.ScorePresent;
import untils.SPUtils;
import view.ScoreView;

public class ScoreActivity extends BaseActivity implements ScoreView{


    @BindView(R.id.score_iv_back)
    ImageView scoreIvBack;


    @BindView(R.id.score_rv_science1)
    RecyclerView score_rv_science1;
    @BindView(R.id.score_rv_science2)
    RecyclerView score_rv_science2;
    @BindView(R.id.score_rv_science3)
    RecyclerView score_rv_science3;
    @BindView(R.id.score_rv_science4)
    RecyclerView score_rv_science4;
    @BindView(R.id.score_rv_arts1)
    RecyclerView score_rv_arts1;
    @BindView(R.id.score_rv_arts2)
    RecyclerView score_rv_arts2;
    @BindView(R.id.score_rv_arts3)
    RecyclerView score_rv_arts3;
    @BindView(R.id.score_rv_arts4)
    RecyclerView score_rv_arts4;



    @BindView(R.id.rl_yuanxiao)
    RelativeLayout rl_yuanxiao;
    @BindView(R.id.rl_name)
    RelativeLayout rl_name;

    @BindView(R.id.iv_right2)
    ImageView iv_right2;
    @BindView(R.id.iv_next2)
    ImageView iv_next2;
    @BindView(R.id.iv_right3)
    ImageView iv_right3;
    @BindView(R.id.iv_next3)
    ImageView iv_next3;

    @BindView(R.id.kaosheng_list)
    ListView kaosheng_list;
    @BindView(R.id.yuanxiao_list)
    ListView yuanxiao_list;
    @BindView(R.id.name_list)
    ListView name_list;
    @BindView(R.id.view_kaosheng)
    View view_kaosheng;
    @BindView(R.id.view_yuanxiao)
    View view_yuanxiao;
    @BindView(R.id.view_name)
    View view_name;


    @BindView(R.id.tv_yuanxiao)
    TextView tv_yuanxiao;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_diqu)
    TextView tv_diqu;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.iv_none)
    ImageView iv_none;
    @BindView(R.id.pb)
    ProgressBar pb;
    private ArrayAdapter<String> area_adapter;
    private String tbsubtype;
    private ScorePresent scorePresent;
    private ArrayList<String> list3;
    private ArrayList<String> list1;
    private ArrayList<String> list2;
    private ArrayList<String> listthree;
    private ArrayList<String> list4;
    private ArrayList<String> list5;
    private ArrayList<String> list6;
    private ArrayList<String> list7;
    private ArrayList<String> list8;


    private String school;
    private List<ScoreBean2> data2;

    private boolean flag1=true;
    private boolean flag2=true;
    private boolean flag3=true;

    private boolean zt1=true;
    private boolean zt2=true;
    private boolean zt3=true;
    private Spinner_Adapter adapter;
    private String tbarea;
    private String s;

    @Override
    public int getId() {
        return R.layout.activity_score;
    }

    @Override
    public void InIt() {

        initView();
        Dianji();
        scorePresent = new ScorePresent(this);
        tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "文科");
        tbarea = (String) SPUtils.get(MyApp.context, "tbarea", "北京市");
        tv_diqu.setText(tbarea);
        yuanxiao();


    }

    private void yuanxiao() {
        rl_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tv_yuanxiao.getText().toString().length()<=3)
                {
                        zt1=true;
                        iv_right2.setVisibility(View.VISIBLE);
                        iv_next2.setVisibility(View.GONE);
                        zt2=true;
                        kaosheng_list.setVisibility(View.GONE);
                        yuanxiao_list.setVisibility(View.GONE);

                        view_kaosheng.setVisibility(View.GONE);
                        view_yuanxiao.setVisibility(View.GONE);
                        if(zt3)
                        {
                            iv_right3.setVisibility(View.GONE);
                            iv_next3.setVisibility(View.VISIBLE);
                            name_list.setVisibility(View.VISIBLE);
                            view_name.setVisibility(View.VISIBLE);
                            zt3=false;
                        }
                        else
                        {
                            iv_right3.setVisibility(View.VISIBLE);
                            iv_next3.setVisibility(View.GONE);
                            name_list.setVisibility(View.GONE);
                            view_name.setVisibility(View.GONE);
                            zt3=true;
                        }


                }
                else
                {
                    Toast.makeText(ScoreActivity.this, "请选择院校地址", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void Dianji() {





        rl_yuanxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                zt1=true;
                iv_right3.setVisibility(View.VISIBLE);
                iv_next3.setVisibility(View.GONE);
                zt3=true;
                kaosheng_list.setVisibility(View.GONE);
                name_list.setVisibility(View.GONE);

                view_kaosheng.setVisibility(View.GONE);
                view_name.setVisibility(View.GONE);
                if(zt2)
                {
                    iv_right2.setVisibility(View.GONE);
                    iv_next2.setVisibility(View.VISIBLE);
                    yuanxiao_list.setVisibility(View.VISIBLE);
                    view_yuanxiao.setVisibility(View.VISIBLE);
                    zt2=false;
                }
                else
                {
                    iv_right2.setVisibility(View.VISIBLE);
                    iv_next2.setVisibility(View.GONE);
                    yuanxiao_list.setVisibility(View.GONE);
                    view_yuanxiao.setVisibility(View.GONE);
                    zt2=true;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "文科");
        tbarea = (String) SPUtils.get(MyApp.context, "tbarea", "北京市");

    }

    private void initView() {

        final ArrayList<String> list = new ArrayList<>();
        list.add("北京市");
        list.add("天津市");
        list.add("河北省");
        list.add("山西省");
        list.add("内蒙古");
        list.add("辽宁省");
        list.add("吉林省");
        list.add("黑龙江");
        list.add("上海市");
        list.add("江苏省");
        list.add("浙江省");
        list.add("安徽省");
        list.add("福建省");
        list.add("江西省");
        list.add("山东省");
        list.add("河南省");
        list.add("湖北省");
        list.add("湖南省");
        list.add("广东省");
        list.add("广西省");
        list.add("海南省");
        list.add("重庆市");
        list.add("四川省");
        list.add("贵州省");
        list.add("云南省");
        list.add("西藏");
        list.add("陕西省");
        list.add("甘肃省");
        list.add("青海省");
        list.add("宁夏");
        list.add("新疆");
        list.add("台湾");
        list.add("澳门");

        //院校所在地
        adapter=new Spinner_Adapter(list,ScoreActivity.this);
        yuanxiao_list.setAdapter(adapter);
        yuanxiao_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                s = list.get(i).toString();
                tv_yuanxiao.setText(s);
                scorePresent.ScorePresent(s,tbsubtype);

                yuanxiao_list.setVisibility(View.GONE);
                view_yuanxiao.setVisibility(View.GONE);
                iv_right2.setVisibility(View.VISIBLE);
                iv_next2.setVisibility(View.GONE);
                zt2=true;

            }
        });

    }


    @OnClick(R.id.score_iv_back)
    public void onViewClicked() {
        finish();
    }
    //获取大学的接口
    @Override
    public void Score1success(BaseBean<List<ScoreBean1>> listBaseBean) {


        list3 = new ArrayList<>();
        List<ScoreBean1> data1 = listBaseBean.data;
        if(data1.size()>0&& data1 !=null)
        {
            for (int i = 0; i < data1.size(); i++) {
                String name = data1.get(i).getName();
                list3.add(name);
            }

             //院校名称
            adapter=new Spinner_Adapter(list3,ScoreActivity.this);
            name_list.setAdapter(adapter);
            name_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    pb.setVisibility(View.VISIBLE);

                    school = list3.get(i).toString();
                    tv_name.setText(school);
                    tv_name.setTextColor(getResources().getColor(R.color.zhu4));
                    scorePresent.Score2Present(tbarea,school);

                    name_list.setVisibility(View.GONE);
                    view_name.setVisibility(View.GONE);
                    iv_right3.setVisibility(View.VISIBLE);
                    iv_next3.setVisibility(View.GONE);
                    zt3=true;

                }
            });
            yuanxiao();
        }
        else
        {
            adapter.notifyDataSetChanged();
            rl_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iv_right3.setVisibility(View.VISIBLE);
                    iv_next3.setVisibility(View.GONE);
                    name_list.setVisibility(View.GONE);
                    view_name.setVisibility(View.GONE);
                    zt3=true;
                    Toast.makeText(ScoreActivity.this, "无大学信息", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    @Override
    public void Score1fail(Throwable t) {
        scorePresent.ScorePresent(s,tbsubtype);
    }

    //分数查询
    @Override
    public void Score2success(BaseBean<List<ScoreBean2>> listBaseBean) {

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        listthree = new ArrayList<>();
        list4 = new ArrayList<>();
        list5 = new ArrayList<>();
        list6 = new ArrayList<>();
        list7= new ArrayList<>();
        list8 = new ArrayList<>();
        data2 = listBaseBean.data;
     if(data2!=null&&data2.size()>0){
         pb.setVisibility(View.GONE);
         iv.setVisibility(View.GONE);
         iv_none.setVisibility(View.GONE);
         rl.setVisibility(View.VISIBLE);
         for (int i = 0; i < data2.size(); i++) {
             //文理科
             String classify = data2.get(i).getClassify();
             if(classify.equals("理科"))
             {
                 //几批
                 String time = data2.get(i).getTime();
                 list1.add(time);
                 flag1=true;
                 flag2=true;
                 flag3=true;
                 List<ScoreBean2.ScoresBean> scores = data2.get(i).getScores();
                 for (int i1 = 0; i1 < scores.size(); i1++) {
                     String year = scores.get(i1).getYear();
                     if(year.equals("2017"))
                     {
                         list2.add(scores.get(i1).getScoreAvg());
                         flag1=false;
                     }
                     if(year.equals("2016"))
                     {
                         listthree.add(scores.get(i1).getScoreAvg());
                         flag2=false;
                     }
                     if(year.equals("2015"))
                     {
                         list4.add(scores.get(i1).getScoreAvg());
                         flag3=false;
                     }
                 }
                 if(flag1==true)
                 {
                     list2.add("---");
                 }
                 if(flag2==true)
                 {
                     listthree.add("---");
                 }
                 if(flag3==true)
                 {
                     list4.add("---");
                 }
             }
             if(classify.equals("文科"))
             {
                 //几批
                 String time = data2.get(i).getTime();
                 list5.add(time);
                 flag1=true;
                 flag2=true;
                 flag3=true;
                 List<ScoreBean2.ScoresBean> scores = data2.get(i).getScores();
                 for (int i1 = 0; i1 < scores.size(); i1++) {
                     String year = scores.get(i1).getYear();
                     if(year.equals("2017"))
                     {
                         list6.add(scores.get(i1).getScoreAvg());
                         flag1=false;
                     }
                     if(year.equals("2016"))
                     {
                         list7.add(scores.get(i1).getScoreAvg());
                         flag2=false;
                     }
                     if(year.equals("2015"))
                     {
                         list8.add(scores.get(i1).getScoreAvg());
                         flag3=false;
                     }
                 }
                 if(flag1==true)
                 {
                     list6.add("---");
                 }
                 if(flag2==true)
                 {
                     list7.add("---");
                 }
                 if(flag3==true)
                 {
                     list8.add("---");
                 }
             }
         }
         //理科
         ProvinceAdapter2 provinceAdapter9 = new ProvinceAdapter2(list1,ScoreActivity.this);
         score_rv_science1.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));
         score_rv_science1.setAdapter(provinceAdapter9);

         ProvinceAdapter2 provinceAdapter10=new ProvinceAdapter2(list2,ScoreActivity.this);
         score_rv_science2.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));
         score_rv_science2.setAdapter(provinceAdapter10);

         ProvinceAdapter2 provinceAdapter11=new ProvinceAdapter2(listthree,ScoreActivity.this);
         score_rv_science3.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));
         score_rv_science3.setAdapter(provinceAdapter11);

         ProvinceAdapter2 provinceAdapter12=new ProvinceAdapter2(list4,ScoreActivity.this);
         score_rv_science4.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));
         score_rv_science4.setAdapter(provinceAdapter12);

         //文科
         ProvinceAdapter2 provinceAdapter13 = new ProvinceAdapter2(list5,ScoreActivity.this);
         score_rv_arts1.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));
         score_rv_arts1.setAdapter(provinceAdapter13);

         ProvinceAdapter2 provinceAdapter14=new ProvinceAdapter2(list6,ScoreActivity.this);
         score_rv_arts2.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));
         score_rv_arts2.setAdapter(provinceAdapter14);

         ProvinceAdapter2 provinceAdapter15=new ProvinceAdapter2(list7,ScoreActivity.this);
         score_rv_arts3.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));
         score_rv_arts3.setAdapter(provinceAdapter15);

         ProvinceAdapter2 provinceAdapter16=new ProvinceAdapter2(list8,ScoreActivity.this);
         score_rv_arts4.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));
         score_rv_arts4.setAdapter(provinceAdapter16);

         if(list1.size()==0)
         {
             Toast.makeText(this, "无数据", Toast.LENGTH_SHORT).show();
         }

         if(list5.size()==0)
         {
             Toast.makeText(this, "无数据", Toast.LENGTH_SHORT).show();
         }
     }

     else
     {
         pb.setVisibility(View.GONE);
         iv_none.setVisibility(View.VISIBLE);
         rl.setVisibility(View.GONE);
         iv.setVisibility(View.GONE);

     }

    }

    @Override
    public void Score2fail(Throwable t) {
        scorePresent.Score2Present(tbarea,school);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scorePresent.onDestory();
    }
}
