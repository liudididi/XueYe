package fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_demo.AccurateActivity;
import com.example.login_demo.MyApp;
import com.example.login_demo.ProvinceActivity;
import com.example.login_demo.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import adapter.Accurate_Yx_Adapter;
import adapter.Spinner3_Adapter;
import adapter.Spinner_Adapter;
import base.BaseApi;
import base.BaseBean;
import base.Basefragment;
import bean.Advanced_YX_Bean;
import bean.CXEFCBean;
import butterknife.BindView;
import moudle.CXEFCMoudle;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import presenter.CXEFCPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Query;
import untils.QuestInterface;
import untils.SPUtils;
import view.CXEFCView;

/**
 * Created by 祝文 on 2018/3/8.
 */

public class Academy_Fragment extends Basefragment{

    private boolean flag1=true;
    private boolean flag2=true;
    private boolean flag3=true;
    private int biaoji;

    private TextView tv_yx;
    private TextView tv_xk;
    private TextView tv_gj;
    private TextView tv_cc;
    private TextView tv_wt;
    private TextView tv_bd;
    private RelativeLayout rl_cc;
    private RelativeLayout rl_wt;
    private RelativeLayout rl_bd;
    private View view_cc;
    private View view_wt;
    private View view_bd;
    private RelativeLayout rl_yx;
    private RelativeLayout rl_xk;
    private RelativeLayout rl_gj;
    private ImageView iv_right1;
    private ImageView iv_right2;
    private ImageView iv_right3;
    private ImageView iv_next1;
    private ImageView iv_next2;
    private ImageView iv_next3;
    private ListView lv1;
    private ListView lv2;
    private RelativeLayout lv3;
    private ListView lv_left;
    private ListView lv_right;
    private Spinner_Adapter spinner_adapter;
    private Spinner_Adapter spinner_adapter1;
    private Spinner3_Adapter spinner_adapter3;
    private Spinner3_Adapter spinner_adapter4;
    private Spinner3_Adapter spinner_adapter5;
    private Spinner3_Adapter spinner_adapter6;
    private Spinner3_Adapter spinner_adapter7;
    private Spinner3_Adapter spinner_adapter8;
    private Spinner3_Adapter spinner_adapter9;
    private ArrayList<String> list;
    private ArrayList<String> list1;
    private ArrayList<String> list2;
    private ArrayList<String> list3;
    private ArrayList<String> list4;
    private ArrayList<String> list5;
    private ArrayList<String> list6;
    private ArrayList<String> list7;
    private TextView tv_chongzhi;
    private TextView tv_queding;
    private RelativeLayout rl;
    private LinearLayout ll5;
    private LinearLayout ll6;
    private EditText ed_fen;

    private RecyclerView rv_yx;
    private String tbsubtype;
    private ImageView iv;
    private ProgressBar pb;
    private String cwb="0";
   // s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
    private String s1=AccurateActivity.s1;
    private String s2=AccurateActivity.s2;
    private String s3=AccurateActivity.s3;
    private String s4=AccurateActivity.s4;
    private String s5=AccurateActivity.s5;
    private String s6=AccurateActivity.s6;
    private String fen=AccurateActivity.fen;
    //院校区域
    private String city="";
    private String token;
    private CXEFCPresenter cxefcPresenter;
    private View lv1_view;
    private View lv2_view;
    private View lv3_view;


    @Override
    public int getLayoutid() {
        return R.layout.academy;
    }

    @Override
    public void initView() {
        tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "文科");
        token = (String) SPUtils.get(MyApp.context, "token", "");



        init();
        initData();
        tv_cc.setTextColor(Color.BLACK);
        initOnClick();

        //走接口进行请求数据
        qingqiu(s1,s3,s4,s5,city,s6,"",s2,tbsubtype,fen,cwb);

    }

    private void initData() {
        list = new ArrayList<>();
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
        spinner_adapter = new Spinner_Adapter(list,getContext());

        list1 = new ArrayList<>();
        list1.add("综合类");
        list1.add("理工类");
        list1.add("艺术类");
        list1.add("体育类");
        list1.add("军事类");
        list1.add("农林类");
        list1.add("医药类");
        list1.add("师范类");
        list1.add("政法类");
        list1.add("林业类");
        list1.add("民族类");
        list1.add("语言类");
        list1.add("财经类");
        spinner_adapter1 = new Spinner_Adapter(list1,getContext());

        list2 = new ArrayList<>();
        list2.add("预估分数");
        list2.add("院校区域");
        list2.add("院校批次");
        list2.add("院校层次");
        list2.add("院校类型");
        list2.add("毕业后方向");
        spinner_adapter3 = new Spinner3_Adapter(list2,getContext());


        spinner_adapter4 = new Spinner3_Adapter(list,getContext());

        list3 = new ArrayList<>();
        list3.add("本科一批");
        list3.add("本科二批");
        list3.add("本科三批");
        list3.add("专科");
        spinner_adapter5 = new Spinner3_Adapter(list3,getContext());

        list4 = new ArrayList<>();
        list4.add("211");
        list4.add("985");
        list4.add("研究生院");
        list4.add("自主招生");
        list4.add("国防生");
        list4.add("卓越计划");
        spinner_adapter6= new Spinner3_Adapter(list4,getContext());

        list5 = new ArrayList<>();
        list5.add("综合类");
        list5.add("理工类");
        list5.add("艺术类");
        list5.add("体育类");
        list5.add("军事类");
        list5.add("农林类");
        list5.add("医药类");
        list5.add("师范类");
        list5.add("政法类");
        list5.add("林业类");
        list5.add("民族类");
        list5.add("语言类");
        list5.add("财经类");
        spinner_adapter7 = new Spinner3_Adapter(list5,getContext());

        list6 = new ArrayList<>();
        list6.add("就业");
        list6.add("考研");
        list6.add("留学");
        spinner_adapter8 = new Spinner3_Adapter(list6,getContext());


    }
    private void initOnClick() {
        //y院校区域
        rl_yx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv1_view.setVisibility(View.VISIBLE);
                lv2_view.setVisibility(View.GONE);
                lv3_view.setVisibility(View.GONE);


                lv2.setVisibility(View.GONE);
                iv_right2.setVisibility(View.VISIBLE);
                iv_next2.setVisibility(View.GONE);
                flag2=true;

                lv3.setVisibility(View.GONE);
                iv_right3.setVisibility(View.VISIBLE);
                iv_next3.setVisibility(View.GONE);
                flag3=true;
                if(flag1)
                {
                    iv_right1.setVisibility(View.GONE);
                    iv_next1.setVisibility(View.VISIBLE);
                    flag1=false;
                    lv1.setVisibility(View.VISIBLE);
                    lv1.setAdapter(spinner_adapter);

                    lv1_view.setVisibility(View.VISIBLE);

                }
              else
                {
                    iv_right1.setVisibility(View.VISIBLE);
                    iv_next1.setVisibility(View.GONE);
                    flag1=true;
                    lv1.setVisibility(View.GONE);

                    lv1_view.setVisibility(View.GONE);

                }
            }
        });
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String yx = list.get(i).toString();
                tv_yx.setText(yx);
                lv1.setVisibility(View.GONE);
                iv_right1.setVisibility(View.VISIBLE);
                iv_next1.setVisibility(View.GONE);
                flag1=true;

                lv1_view.setVisibility(View.GONE);

                pb.setVisibility(View.VISIBLE);
                city=yx;
                qingqiu(s1,s3,s4,s5,city,s6,"",s2,tbsubtype,fen,cwb);

            }
        });
        //学科范围
        rl_xk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv1_view.setVisibility(View.GONE);
                lv2_view.setVisibility(View.VISIBLE);
                lv3_view.setVisibility(View.GONE);



                lv1.setVisibility(View.GONE);
                iv_right1.setVisibility(View.VISIBLE);
                iv_next1.setVisibility(View.GONE);
                flag1=true;

                lv3.setVisibility(View.GONE);
                iv_right3.setVisibility(View.VISIBLE);
                iv_next3.setVisibility(View.GONE);
                flag3=true;
                 if(flag2)
                {
                    iv_right2.setVisibility(View.GONE);
                    iv_next2.setVisibility(View.VISIBLE);
                    flag2=false;
                    lv2.setVisibility(View.VISIBLE);
                    lv2.setAdapter(spinner_adapter1);

                    lv2_view.setVisibility(View.VISIBLE);

                }
                else
                {
                    iv_right2.setVisibility(View.VISIBLE);
                    iv_next2.setVisibility(View.GONE);
                    flag2=true;
                    lv2.setVisibility(View.GONE);

                    lv2_view.setVisibility(View.GONE);

                }
            }
        });
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String fw = list1.get(i).toString();
                lv2.setVisibility(View.GONE);
                tv_xk.setText(fw);
                iv_right2.setVisibility(View.VISIBLE);
                iv_next2.setVisibility(View.GONE);
                flag2=true;

                lv2_view.setVisibility(View.GONE);

                pb.setVisibility(View.VISIBLE);
                s5=fw;
                qingqiu(s1,s3,s4,s5,city,s6,"",s2,tbsubtype,fen,cwb);


            }
        });
        //高级筛选
        rl_gj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv1_view.setVisibility(View.GONE);
                lv2_view.setVisibility(View.GONE);
                lv3_view.setVisibility(View.VISIBLE);

                lv1.setVisibility(View.GONE);
                iv_right1.setVisibility(View.VISIBLE);
                iv_next1.setVisibility(View.GONE);
                flag1=true;

                lv2.setVisibility(View.GONE);
                iv_right2.setVisibility(View.VISIBLE);
                iv_next2.setVisibility(View.GONE);
                flag2=true;

               ed_fen.setText(fen);
                if(flag3)
                {
                    iv_right3.setVisibility(View.GONE);
                    iv_next3.setVisibility(View.VISIBLE);
                    flag3=false;
                    lv3.setVisibility(View.VISIBLE);
                    lv_left.setAdapter(spinner_adapter3);

                    lv3_view.setVisibility(View.VISIBLE);

                }
                else
                {
                    iv_right3.setVisibility(View.VISIBLE);
                    iv_next3.setVisibility(View.GONE);
                    flag3=true;
                    lv3.setVisibility(View.GONE);

                    lv3_view.setVisibility(View.GONE);

                }
            }
        });
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                if(list2.get(i).toString().equals("预估分数"))
                {
                    ll6.setVisibility(View.GONE);
                }
                //list
                if(list2.get(i).toString().equals("院校区域"))
                {
                    ll6.setVisibility(View.VISIBLE);
                    lv_right.setVisibility(View.VISIBLE);
                    lv_right.setAdapter(spinner_adapter4);
                    biaoji=4;
                }
                //list3
                if(list2.get(i).toString().equals("院校批次"))
                {

                    ll6.setVisibility(View.VISIBLE);
                    lv_right.setAdapter(spinner_adapter5);
                    biaoji=5;
                }
                //list4
                if(list2.get(i).toString().equals("院校层次"))
                {

                    ll6.setVisibility(View.VISIBLE);
                    lv_right.setAdapter(spinner_adapter6);
                    biaoji=6;
                }
                //list5
                if(list2.get(i).toString().equals("院校类型"))
                {

                    ll6.setVisibility(View.VISIBLE);
                    lv_right.setAdapter(spinner_adapter7);
                    biaoji=7;
                }
                //list6
                if(list2.get(i).toString().equals("毕业后方向"))
                {

                    ll6.setVisibility(View.VISIBLE);
                    lv_right.setAdapter(spinner_adapter8);
                    biaoji=8;
                }
            }
        });
        lv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(biaoji==4)
                {
                    city = list.get(i).toString();
                    System.out.println("选项"+ city);
                }
                if(biaoji==5)
                {
                    s3 = list3.get(i).toString();
                    System.out.println("选项"+ s3);
                }
                if(biaoji==6)
                {
                    s4 = list4.get(i).toString();
                    System.out.println("选项"+ s4);
                }
                if(biaoji==7)
                {
                    s5 = list5.get(i).toString();
                    System.out.println("选项"+ s5);
                }
                if(biaoji==8)
                {
                    s6 = list6.get(i).toString();
                    System.out.println("选项"+ s6);
                }
            }
        });
        tv_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv3_view.setVisibility(View.GONE);

                lv3.setVisibility(View.GONE);
                iv_right3.setVisibility(View.VISIBLE);
                iv_next3.setVisibility(View.GONE);
                flag3=true;
                fen=ed_fen.getText().toString();
                System.out.println("选项"+fen+city+s3+s4+s5+ s6);
                //s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
                pb.setVisibility(View.VISIBLE);
                SPUtils.put(MyApp.context,"tbmaxfen",fen+"");
                qingqiu(s1,s3,s4,s5,city,s6,"",s2,tbsubtype,fen,cwb);
            }
        });
        tv_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv3_view.setVisibility(View.GONE);

                lv3.setVisibility(View.GONE);
                iv_right3.setVisibility(View.VISIBLE);
                iv_next3.setVisibility(View.GONE);
                flag3=true;

                city="";
                //s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
                s3="";
                s4="";
                s5="";
                s6="";
                tv_yx.setText("院校区域");
                tv_xk.setText("学科范围");
                pb.setVisibility(View.VISIBLE);
                qingqiu(s1,s3,s4,s5,city,s6,"",s2,tbsubtype,fen,cwb);
                System.out.println("选项+++"+s1+s2+tbsubtype+fen+cwb);
             }
        });

        rl_cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_cc.setTextColor(Color.BLACK);
                tv_wt.setTextColor(Color.GRAY);
                tv_bd.setTextColor(Color.GRAY);
                view_cc.setVisibility(View.VISIBLE);
                view_wt.setVisibility(View.GONE);
                view_bd.setVisibility(View.GONE);

                //走接口进行请求数据  type冲
                pb.setVisibility(View.VISIBLE);
                cwb="0";
                qingqiu(s1,s3,s4,s5,city,s6,"",s2,tbsubtype,fen,cwb);
            }
        });
        rl_wt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_cc.setTextColor(Color.GRAY);
                tv_wt.setTextColor(Color.BLACK);
                tv_bd.setTextColor(Color.GRAY);
                view_cc.setVisibility(View.GONE);
                view_wt.setVisibility(View.VISIBLE);
                view_bd.setVisibility(View.GONE);

                //走接口进行请求数据  type稳
                pb.setVisibility(View.VISIBLE);

                cwb="1";
                qingqiu(s1,s3,s4,s5,city,s6,"",s2,tbsubtype,fen,cwb);

            }
        });
        rl_bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_cc.setTextColor(Color.GRAY);
                tv_wt.setTextColor(Color.GRAY);
                tv_bd.setTextColor(Color.BLACK);
                view_cc.setVisibility(View.GONE);
                view_wt.setVisibility(View.GONE);
                view_bd.setVisibility(View.VISIBLE);
                //走接口进行请求数据  type保
                pb.setVisibility(View.VISIBLE);

                cwb="2";
                qingqiu(s1,s3,s4,s5,city,s6,"",s2,tbsubtype,fen,cwb);

            }
        });
    }

    //cwb 冲稳保  city 院校区域  s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
    private void qingqiu(final String s1, final String s3, final String s4, final String s5, final String city, final String s6, String tuition, final String s2, final String tbsubtype, final String fen, final String cwb) {
         final HashMap<String,String> map=new HashMap<>();
        cxefcPresenter= new CXEFCPresenter(new CXEFCView() {
            @Override
            public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
                String majorGai = cxefcBeanBaseBean.data.getMajorGai();
                for (int i = 0; i < 20; i++) {
                    String[] split = majorGai.split(",");
                    String s = split[i];
                    String[] split1 = s.split(":");
                    //专业名
                    String name = split1[0];
                    //专业概率
                    String gailv = split1[2];
                    String substring = gailv.substring(0, 3);
                     map.put(name,substring+"");
                }
                Gson gson=new Gson();
                String route= gson.toJson(map);

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(BaseApi.Api)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                QuestInterface questInterface = retrofit.create(QuestInterface.class);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
                //s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
                Call<BaseBean<List<Advanced_YX_Bean>>> call = questInterface.shaixuan(s1,s3,s4,
                        s5, city, s6, "", s2, tbsubtype,fen,
                        cwb, body);

                call.enqueue(new Callback<BaseBean<List<Advanced_YX_Bean>>>() {
                    @Override
                    public void onResponse(Call<BaseBean<List<Advanced_YX_Bean>>> call, Response<BaseBean<List<Advanced_YX_Bean>>> response) {

                        pb.setVisibility(View.GONE);
                        rv_yx.setVisibility(View.VISIBLE);
                        List<Advanced_YX_Bean> data = response.body().data;
                        if(data.size()>0&&data!=null)
                        {
                            iv.setVisibility(View.GONE);
                            rv_yx.setVisibility(View.VISIBLE);
                            Accurate_Yx_Adapter accurate_yx_adapter=new Accurate_Yx_Adapter(getContext(),data);
                            rv_yx.setLayoutManager(new LinearLayoutManager(getContext()));
                            rv_yx.setAdapter(accurate_yx_adapter);
                        }
                        else
                        {
                            iv.setVisibility(View.VISIBLE);
                            rv_yx.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseBean<List<Advanced_YX_Bean>>> call, Throwable t) {

                    }
                });
            }
            @Override
            public void GetEFCResultfail(Throwable t) {

            }
        });
        cxefcPresenter.CXEFCPresenter(token);

    }

    private void init() {
        tv_yx = view.findViewById(R.id.tv_yx);
        tv_xk = view.findViewById(R.id.tv_xk);
        tv_gj = view.findViewById(R.id.tv_gj);
        tv_cc = view.findViewById(R.id.tv_cc);
        tv_wt = view.findViewById(R.id.tv_wt);
        tv_bd = view.findViewById(R.id.tv_bd);
        rl_cc = view.findViewById(R.id.rl_cc);
        rl_wt = view.findViewById(R.id.rl_wt);
        rl_bd = view.findViewById(R.id.rl_bd);
        view_cc = view.findViewById(R.id.view_cc);
        view_wt = view.findViewById(R.id.view_wt);
        view_bd = view.findViewById(R.id.view_bd);
        rl_yx = view.findViewById(R.id.rl_yx);
        rl_xk = view.findViewById(R.id.rl_xk);
        rl_gj = view.findViewById(R.id.rl_gj);

        iv_right1 = view.findViewById(R.id.iv_right1);
        iv_right2 = view.findViewById(R.id.iv_right2);
        iv_right3 = view.findViewById(R.id.iv_right3);
        iv_next1 = view.findViewById(R.id.iv_next1);
        iv_next2 = view.findViewById(R.id.iv_next2);
        iv_next3 = view.findViewById(R.id.iv_next3);

        lv1 = view.findViewById(R.id.lv1);
        lv2 = view.findViewById(R.id.lv2);
        lv3 = view.findViewById(R.id.lv3);
        lv_left = view.findViewById(R.id.lv_left);
        lv_right = view.findViewById(R.id.lv_right);
        tv_chongzhi = view.findViewById(R.id.tv_chongzhi);
        tv_queding = view.findViewById(R.id.tv_queding);

        rl = view.findViewById(R.id.rl);
        ll5 = view.findViewById(R.id.ll5);
        ll6 = view.findViewById(R.id.ll6);
        ed_fen = view.findViewById(R.id.ed_fen);

        rv_yx = view.findViewById(R.id.rv_yx);
        iv = view.findViewById(R.id.iv);
        pb = view.findViewById(R.id.pb);

        lv1_view = view.findViewById(R.id.lv1_view);
        lv2_view = view.findViewById(R.id.lv2_view);
        lv3_view = view.findViewById(R.id.lv3_view);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;

        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) rl.getLayoutParams();
//获取当前控件的布局对象
        params.width=(width/3)*2;
        rl.setLayoutParams(params);//将设置好的布局参数应用到控件中

        LinearLayout.LayoutParams params1= (LinearLayout.LayoutParams) ll6.getLayoutParams();
//获取当前控件的布局对象
        params1.width=(width/3)*2;
        ll6.setLayoutParams(params1);

        LinearLayout.LayoutParams params2= (LinearLayout.LayoutParams) ll5.getLayoutParams();
//获取当前控件的布局对象
        params2.width=width/3;
        ll5.setLayoutParams(params2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cxefcPresenter.onDestory();
    }


}
