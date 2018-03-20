package com.example.login_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import adapter.Volunteer_ScreenAdapter;
import adapter.YXJ_ScreenAdapter;
import base.BaseActivity;
import base.BaseBean;
import bean.BiaoshiBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;
import untils.SPUtils;

public class Volunteer_ScreenActivity extends BaseActivity {
    @BindView(R.id.screen_et_fen)
    EditText screen_et_fen;
    @BindView(R.id.gv1)
    GridView gv1;
    @BindView(R.id.gv2)
    GridView gv2;
    @BindView(R.id.gv3)
    GridView gv3;
    @BindView(R.id.gv4)
    GridView gv4;
    @BindView(R.id.gv5)
    GridView gv5;
    @BindView(R.id.gv6)
    GridView gv6;
    @BindView(R.id.screen_iv_back)
    ImageView screenIvBack;
    @BindView(R.id.screen_tv_confirm)
    TextView screenTvConfirm;

    private String fen;


    private ArrayList<String> pc_list;
    private String s1;
    private String s2;
    private ArrayList<String> cc_list;
    private ArrayList<String> lx_list;
    private String s6;
    private boolean flag=true;
    private String tbsubtype;
    private String token;
    @Override
    public int getId() {
        return R.layout.activity_volunteer_screen;
    }

    @Override
    public void InIt() {
       String  tbmaxfen = (String) SPUtils.get(MyApp.context, "tbmaxfen", "500");
        tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "文科");
        token = (String) SPUtils.get(MyApp.context, "token", "");

        fen=tbmaxfen;
        screen_et_fen.setText(fen);
        screen_et_fen.setSelection(tbmaxfen.length());//将光标移至文字末尾
        initData();
    }

    private void initData() {
        final ArrayList<String> list1 = new ArrayList<>();
        list1.add("院校优先");
        list1.add("专业优先");
        list1.add("皆可");
        final YXJ_ScreenAdapter screenAdapter1 = new YXJ_ScreenAdapter(list1, Volunteer_ScreenActivity.this);
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        screenAdapter1.setSeclection(i);
                        screenAdapter1.notifyDataSetChanged();
                        flag=false;
                        if(i==0)
                        {
                            s1="0";
                        }
                        else if(i==1)
                        {
                            s1="1";
                        }
                        else if(i==2)
                        {
                            s1="2";
                        }

            }
        });
        gv1.setAdapter(screenAdapter1);


        final ArrayList<BiaoshiBean> list2 = new ArrayList<>();
        list2.add(new BiaoshiBean("本科一批",true));
        list2.add(new BiaoshiBean("本科二批",true));
        list2.add(new BiaoshiBean("本科三批",true));
        list2.add(new BiaoshiBean("专科",true));
         pc_list = new ArrayList<>();
        final Volunteer_ScreenAdapter screenAdapter2 = new Volunteer_ScreenAdapter(list2, Volunteer_ScreenActivity.this);
        screenAdapter2.setTvItem(new Volunteer_ScreenAdapter.TvItem() {
            @Override
            public void Tv(View view, int postion) {
                TextView tv_type=view.findViewById(R.id.tv_type);
                for (int i1 = 0; i1 < list2.size(); i1++) {
                    if(i1==postion)
                    {
                        if(list2.get(i1).isFlag())
                        {
                            tv_type.setBackgroundResource(R.drawable.bg_subject5);
                            list2.get(i1).setFlag(false);
                             pc_list.add(list2.get(i1).getStr());
                        }
                        else
                        {
                            tv_type.setBackgroundResource(R.drawable.bg_subject4);
                            list2.get(i1).setFlag(true);
                             pc_list.remove(list2.get(i1).getStr());
                        }
                    }
                }
            }
        });
        gv2.setAdapter(screenAdapter2);

        final ArrayList<BiaoshiBean> list3 = new ArrayList<>();
        list3.add(new BiaoshiBean("211",true));
        list3.add(new BiaoshiBean("985",true));
        list3.add(new BiaoshiBean("研究生院",true) );
        list3.add(new BiaoshiBean("自主招生",true) );
        list3.add(new BiaoshiBean("国防生",true) );
        list3.add(new BiaoshiBean("卓越计划",true) );

        cc_list = new ArrayList<>();
        Volunteer_ScreenAdapter screenAdapter3 = new Volunteer_ScreenAdapter(list3, Volunteer_ScreenActivity.this);
        screenAdapter3.setTvItem(new Volunteer_ScreenAdapter.TvItem() {
            @Override
            public void Tv(View view, int postion) {
                TextView tv_type=view.findViewById(R.id.tv_type);
                for (int i1 = 0; i1 < list3.size(); i1++) {
                    if(i1==postion)
                    {
                        if(list3.get(i1).isFlag())
                        {
                            tv_type.setBackgroundResource(R.drawable.bg_subject5);
                            list3.get(i1).setFlag(false);
                            cc_list.add(list3.get(i1).getStr());
                        }
                        else
                        {

                            tv_type.setBackgroundResource(R.drawable.bg_subject4);
                            list3.get(i1).setFlag(true);
                            cc_list.remove(list3.get(i1).getStr());

                        }
                    }
                }
            }
        });
        gv3.setAdapter(screenAdapter3);

        final ArrayList<BiaoshiBean> list4 = new ArrayList<>();
        list4.add(new BiaoshiBean("综合类",true));
        list4.add(new BiaoshiBean("理工类",true));
        list4.add(new BiaoshiBean("艺术类",true));
        list4.add(new BiaoshiBean("体育类",true));
        list4.add(new BiaoshiBean("军事类",true));
        list4.add(new BiaoshiBean("农林类",true));
        list4.add(new BiaoshiBean("医药类",true));
        list4.add(new BiaoshiBean("师范类",true));
        list4.add(new BiaoshiBean("政法类",true));
        list4.add(new BiaoshiBean("林业类",true));
        list4.add(new BiaoshiBean("民族类",true));
        list4.add(new BiaoshiBean("语言类",true));
        list4.add(new BiaoshiBean("财经类",true));
        lx_list = new ArrayList<>();

        Volunteer_ScreenAdapter screenAdapter4 = new Volunteer_ScreenAdapter(list4, Volunteer_ScreenActivity.this);
        screenAdapter4.setTvItem(new Volunteer_ScreenAdapter.TvItem() {
            @Override
            public void Tv(View view, int postion) {
                TextView tv_type=view.findViewById(R.id.tv_type);
                for (int i1 = 0; i1 < list4.size(); i1++) {
                    if(i1==postion)
                    {
                        if(list4.get(i1).isFlag())
                        {
                            tv_type.setBackgroundResource(R.drawable.bg_subject5);
                            list4.get(i1).setFlag(false);
                            lx_list.add(list4.get(i1).getStr());

                        }
                        else
                        {

                            tv_type.setBackgroundResource(R.drawable.bg_subject4);
                            list4.get(i1).setFlag(true);
                            lx_list.remove(list4.get(i1).getStr());

                        }
                    }
                }
            }
        });
        gv4.setAdapter(screenAdapter4);

        final ArrayList<String> list5 = new ArrayList<>();
        list5.add("北京");
        list5.add("天津");
        list5.add("江苏");
        list5.add("山西");
        list5.add("内蒙古");
        list5.add("辽宁");
        list5.add("吉林");
        list5.add("黑龙江");
        list5.add("上海");
        list5.add("浙江");
        list5.add("安徽");
        list5.add("福建");
        list5.add("江西");
        list5.add("山东");
        list5.add("河南");
        list5.add("湖北");
        list5.add("湖南");
        list5.add("广东");
        list5.add("广西");
        list5.add("海南");
        list5.add("重庆");
        list5.add("四川");
        list5.add("贵州");
        list5.add("云南");
        list5.add("西藏");
        list5.add("陕西");
        list5.add("甘肃");
        list5.add("青海");
        list5.add("宁夏");
        list5.add("新疆");
        list5.add("河北");
        final YXJ_ScreenAdapter screenAdapter5 = new YXJ_ScreenAdapter(list5, Volunteer_ScreenActivity.this);
        gv5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                screenAdapter5.setSeclection(i);
                screenAdapter5.notifyDataSetChanged();
                  s2 = list5.get(i).toString();
                  flag=false;
            }
        });
        gv5.setAdapter(screenAdapter5);

        final ArrayList<String> list6 = new ArrayList<>();
        list6.add("就业");
        list6.add("考研");
        list6.add("留学");
        final YXJ_ScreenAdapter screenAdapter6 = new YXJ_ScreenAdapter(list6, Volunteer_ScreenActivity.this);
        gv6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                screenAdapter6.setSeclection(i);
                screenAdapter6.notifyDataSetChanged();
                s6 = list6.get(i).toString();
                flag=false;
            }
        });
        gv6.setAdapter(screenAdapter6);
    }


    @OnClick({R.id.screen_iv_back, R.id.screen_tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.screen_iv_back:
                finish();
                break;
            case R.id.screen_tv_confirm:
                //TODO 确认按钮进行提交数据
                fen=screen_et_fen.getText().toString();
                String s3 = listToString(pc_list);
                String s4 = listToString(cc_list);
                String s5 = listToString(lx_list);
                //s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
                Intent intent=new Intent(Volunteer_ScreenActivity.this,AccurateActivity.class);
                intent.putExtra("fen",fen+"");
                intent.putExtra("s1",s1+"");
                intent.putExtra("s2",s2+"");
                intent.putExtra("s3",s3+"");
                intent.putExtra("s4",s4+"");
                intent.putExtra("s5",s5+"");
                intent.putExtra("s6",s6+"");
                SPUtils.put(MyApp.context,"tbmaxfen",fen+"");
                if(s1!=null&&s2!=null)
                {
                    startActivity(intent);

                    //保存精准筛选信息
                      MyQusetUtils.getInstance().getQuestInterface()
                            .update(tbsubtype,s2,fen,"","","",s1,"","",
                                    s5,s3,s4,s6,"",token)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSubscriber<BaseBean>() {
                                @Override
                                public void onNext(BaseBean baseBean) {

                                    System.out.println("成功++"+baseBean.msg);
                                }

                                @Override
                                public void onError(Throwable t) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
                else
                {
                    Toast("请选择信息");
                    return;
                }
                finish();
                 break;
        }
    }

    public static String listToString(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

}
