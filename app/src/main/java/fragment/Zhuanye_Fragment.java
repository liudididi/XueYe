package fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.login_demo.AccurateActivity;
import com.example.login_demo.AnswerActivity;
import com.example.login_demo.ComlitEFCActivity;
import com.example.login_demo.MyApp;
import com.example.login_demo.R;
import com.example.login_demo.wxapi.WXPayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import adapter.Accurate_Zy_Yx_Adapter;
import adapter.Spinner3_Adapter;
import adapter.Spinner_Adapter;
import adapter.ZY_ZY_Adapter;
import adapter.Zy_Sousuo_Adapter;
import base.BaseApi;
import base.BaseBean;
import base.Basefragment;
import bean.Advanced_YX_Bean;
import bean.CXEFCBean;
import bean.SerchZYBean;
import bean.ShijianBean;
import bean.WeiXinBean;
import bean.XDingdanBean;
import presenter.CXEFCPresenter;
import presenter.CountdownPresent;
import presenter.PayPresent;
import presenter.SerchZYPresent;
import presenter.ShijianPresent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import untils.CircleProgressView;
import untils.Dianji2;
import untils.QuestInterface;
import untils.SPUtils;
import untils.ShijianUtils;
import view.CXEFCView;
import view.CountdownView;
import view.PayView;
import view.SerchZYView;
import view.ShijianView;
import zfbpay.AliPayResult;

/**
 * Created by 祝文 on 2018/3/8.
 */

public class Zhuanye_Fragment extends Basefragment implements CountdownView,PayView {
    private boolean flag1=true;
    private boolean flag2=true;
    private boolean flag3=true;
    private int biaoji;

    private TextView tv_yx;
    private TextView tv_xk;

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
    private String quyu;
    private String pici;
    private String cengci;
    private String leixing;
    private String fangxiang;
    private RelativeLayout rl_tj;
    private ListView lv_zy_zhuanye;
    private TextView tv_tj;
    private RecyclerView rv_zy_zhuanye;
    // s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
    private String s1="1";
    private String s2=AccurateActivity.s2;
    //private String s3=AccurateActivity.s3;
    private String s4=AccurateActivity.s4;
    private String s5=AccurateActivity.s5;
    //private String s6=AccurateActivity.s6;
    private String fen=AccurateActivity.fen;
    private String tbsubtype=AccurateActivity.tbsubtype;

    //院校区域
    private String city="";
    private String cwb="0";

    private ProgressBar pb;
    private ProgressBar pb2;
    private ProgressBar pb3;
    private ImageView zy_iv;
    private ListView lv_ss_zy;
    private EditText et_zy;
    private String major;
    private String token;
    private CXEFCPresenter cxefcPresenter;
    private View lv1_view;
    private View lv2_view;
    private View lv3_view;
    private String id;
    private boolean vip;
    private String shijian;
    private boolean dateOneBigger;

    private TextView tv_day1;
    private TextView tv_day2;
    private TextView tv_day3;
    private RelativeLayout rl_shengji;
    private CountdownPresent countdownPresent;
    private ImageView iv_zy_mohu;
    private TextView tv_tishi;
    private PayPresent payPresent;
    private int pay = 2;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case SDK_PAY_FLAG:

                    AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
                    switch (payResult.getResultStatus()) {
                        case "9000":
                            SPUtils.put(MyApp.context,"VIP",true);
                            isretry();
                            Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();

                            break;
                        case "8000":
                            Toast.makeText(getContext(), "正在处理中", Toast.LENGTH_SHORT).show();
                            break;
                        case "4000":
                            Toast.makeText(getContext(), "订单支付失败", Toast.LENGTH_SHORT).show();
                            break;
                        case "5000":
                            Toast.makeText(getContext(), "重复请求", Toast.LENGTH_SHORT).show();
                            break;
                        case "6001":
                            Toast.makeText(getContext(), "已取消支付", Toast.LENGTH_SHORT).show();
                            break;
                        case "6002":
                            Toast.makeText(getContext(), "网络连接出错", Toast.LENGTH_SHORT).show();
                            break;
                        case "6004":
                            Toast.makeText(getContext(), "正在处理中", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(getContext(), "支付失败", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    break;

            }
//            返回码	含义
//            9000	订单支付成功
//            8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
//            4000	订单支付失败
//            5000	重复请求
//            6001	用户中途取消
//            6002	网络连接出错
//            6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
//            其它	其它支付错误

        }
    };
    private TextView tv_gj;

    @Override
    public int getLayoutid() {
        return R.layout.zhuanye;
    }

    @Override
    public void initView() {

        countdownPresent = new CountdownPresent(this);
        countdownPresent.CountdownPresent();

        payPresent = new PayPresent(this);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        init();
        ShijianPresent shijianPresent=new ShijianPresent(new ShijianView() {
            @Override
            public void Shijiansuccess( ShijianBean shijianBean) {
                String data1 = shijianBean.getData();
                String[] split = data1.split(" ");
                shijian = split[0];
                dateOneBigger = ShijianUtils.isDateOneBigger(shijian, "2018-06-20");
            }

            @Override
            public void Shijianfail(Throwable t) {

            }
        });
        shijianPresent.ShijianPresent();
         token = (String) SPUtils.get(MyApp.context, "token", "");
        vip = (boolean) SPUtils.get(MyApp.context, "VIP", false);
        tv_tj.setTextColor(getContext().getResources().getColor(R.color.delete_dialog_text_color));
        initData();
        initOnClick();
        zhuanye();

        if(vip){
            rl_shengji.setVisibility(View.GONE);
            iv_zy_mohu.setVisibility(View.GONE);
            tv_tishi.setVisibility(View.GONE);
            et_zy.setEnabled(true);
        }
        else
        {
            rl_shengji.setVisibility(View.VISIBLE);
            iv_zy_mohu.setVisibility(View.VISIBLE);
            tv_tishi.setVisibility(View.VISIBLE);
            et_zy.setEnabled(false);
            rl_shengji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String token = (String) SPUtils.get(MyApp.context, "token", "");
                    String type = (String) SPUtils.get(MyApp.context, "kemuefc", "本科");
                    if (token.length() > 4) {
                        if(Dianji2.isNotFastClick()){
                            if(type.equals("本科")){
                                payPresent.XiaDan(token, "3", pay + "");
                            }else {
                                payPresent.XiaDan(token, "4", pay + "");
                            }
                        }
                    } else {
                        Toast.makeText(getContext(), "token失效，请重新登录", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void zhuanye() {

        final ArrayList<String> list=new ArrayList<>();
        //TODO 为后期做铺垫  专业id
        final ArrayList<String> listid=new ArrayList<>();

        cxefcPresenter = new CXEFCPresenter(new CXEFCView() {
            @Override
            public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
                String majorGai = cxefcBeanBaseBean.data.getMajorGai();
                for (int i = 0; i < 20; i++) {
                    String[] split = majorGai.split(",");
                    String s = split[i];
                    String[] split1 = s.split(":");
                    //专业名
                    String name = split1[0];
                    String id = split1[3];
                     list.add(name);
                    listid.add(id);
                }

                pb.setVisibility(View.GONE);
                lv_zy_zhuanye.setVisibility(View.VISIBLE);
                 if(list.size()>0&&list!=null)
                {
                    ZY_ZY_Adapter zy_zy_adapter=new ZY_ZY_Adapter(list,getActivity());
                    lv_zy_zhuanye.setAdapter(zy_zy_adapter);
                }

                lv_zy_zhuanye.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        pb2.setVisibility(View.VISIBLE);
                        //专业名
                        major = list.get(i).toString();
                        id = listid.get(i).toString();
                        CircleProgressView.mTextColor=Color.RED;
                        CircleProgressView.mPaintColor=Color.RED;
                        zhuanye_yuanxiao(major,s1,"",s4,s5,city,"","",s2,tbsubtype,fen,cwb);
                    }
                });

            }

            @Override
            public void GetEFCResultfail(Throwable t) {

            }
        });
        cxefcPresenter.CXEFCPresenter(token);

    }

    private void isretry() {
        String str="是否重新测试？";
        Dialog dialog = new android.app.AlertDialog.Builder( getActivity()).setTitle("支付成功").setMessage(str)
                // 设置内容
                .setPositiveButton("重新测试",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                Intent intent = new Intent( getActivity(), AnswerActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                                dialog.dismiss();

                            }
                        }).setNegativeButton("不需要",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                                Intent intent = new Intent( getActivity(), ComlitEFCActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                                dialog.dismiss();
                            }
                        }).create();// 创建
        dialog.setCancelable(false);
        dialog.show();
    }
    private void zhuanye_yuanxiao(final String zy_name, String s1, String s3, String s4, String s5, String city, String s6, String tuition, String s2, String tbsubtype, String fen, String cwb) {
        //cwb 冲稳保  city 院校区域  s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向

        if(zy_name==null)
        {
            Toast.makeText(getContext(), "请选择专业", Toast.LENGTH_SHORT).show();
        }

       /* HashMap<String,String> map=new HashMap<>();
        map.put(zy_name,"0.7");

            Gson gson=new Gson();
            String route= gson.toJson(map);
        System.out.println("类型++"+route);*/
        Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(BaseApi.Api)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            QuestInterface questInterface = retrofit.create(QuestInterface.class);
            //RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
            //s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
            Call<BaseBean<List<Advanced_YX_Bean>>> call = questInterface.shaixuan(s1,s3,s4,
                    s5, city, s6, "", s2, tbsubtype,fen,
                    cwb, zy_name+":0.7:null",token);
        call.enqueue(new Callback<BaseBean<List<Advanced_YX_Bean>>>() {
                @Override
                public void onResponse(Call<BaseBean<List<Advanced_YX_Bean>>> call, Response<BaseBean<List<Advanced_YX_Bean>>> response) {
                    pb2.setVisibility(View.GONE);
                    rv_zy_zhuanye.setVisibility(View.VISIBLE);


                   List<Advanced_YX_Bean> data = response.body().data;
                     if(data.size()>0&&data!=null)
                    {
                        pb3.setVisibility(View.GONE);
                        et_zy.setText(zy_name);
                        lv_ss_zy.setVisibility(View.GONE);
                        if(zy_name!=null)
                        {
                            et_zy.setSelection(zy_name.length());

                        }

                        lv_zy_zhuanye.setVisibility(View.GONE);
                        rv_zy_zhuanye.setVisibility(View.VISIBLE);
                        zy_iv.setVisibility(View.GONE);
                        Accurate_Zy_Yx_Adapter accurate_yx_adapter=new Accurate_Zy_Yx_Adapter(getContext(),data);
                        rv_zy_zhuanye.setLayoutManager(new LinearLayoutManager(getContext()));
                        rv_zy_zhuanye.setAdapter(accurate_yx_adapter);
                        tv_tj.setTextColor(getContext().getResources().getColor(R.color.activity_attention_players_text));

                    }
                    else
                    {
                        pb3.setVisibility(View.GONE);
                        et_zy.setText(zy_name);
                        lv_ss_zy.setVisibility(View.GONE);
                        if(zy_name!=null)
                        {
                            et_zy.setSelection(zy_name.length());

                        }

                        zy_iv.setVisibility(View.VISIBLE);
                        rv_zy_zhuanye.setVisibility(View.GONE);
                        lv_zy_zhuanye.setVisibility(View.GONE);
                        tv_tj.setTextColor(getContext().getResources().getColor(R.color.activity_attention_players_text));
                    }
                }
                @Override
                public void onFailure(Call<BaseBean<List<Advanced_YX_Bean>>> call, Throwable t) {

                }
            });
    }

    private void initData() {
        list = new ArrayList<>();
        list.add("全国");
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
        list1.add("不限");

        list1.add("综合类");
        list1.add("理工类");
      /*  list1.add("艺术类");
        list1.add("体育类");
        list1.add("军事类");
        list1.add("农林类");
        list1.add("医药类");
        list1.add("师范类");
        list1.add("政法类");
        list1.add("林业类");
        list1.add("民族类");
        list1.add("语言类");
        list1.add("财经类");*/
        spinner_adapter1 = new Spinner_Adapter(list1,getContext());

        list2 = new ArrayList<>();
      /*  list2.add("预估分数");
        list2.add("院校区域");
        //list2.add("院校批次");
        list2.add("院校层次");
        list2.add("院校类型");
        //list2.add("毕业后方向");*/
        spinner_adapter3 = new Spinner3_Adapter(list2,getContext());


        spinner_adapter4 = new Spinner3_Adapter(list,getContext());

       /* list3 = new ArrayList<>();
        list3.add("本一");
        list3.add("本二");
        list3.add("本三");
        list3.add("专科");*/
        spinner_adapter5 = new Spinner3_Adapter(list3,getContext());

        list4 = new ArrayList<>();
       /* list4.add("不限");
        list4.add("211");
        list4.add("985");
        list4.add("研究生院");
        list4.add("自主招生");
        list4.add("国防生");
        list4.add("卓越计划");*/
        spinner_adapter6= new Spinner3_Adapter(list4,getContext());

        list5 = new ArrayList<>();
        list5.add("不限");
        list5.add("综合类");
        list5.add("理工类");
      /*  list5.add("农林类");
        list5.add("医药类");
        list5.add("师范类");
        list5.add("政法类");
        list5.add("林业类");
        list5.add("民族类");
        list5.add("语言类");
        list5.add("财经类");*/
        spinner_adapter7 = new Spinner3_Adapter(list5,getContext());

      /*  list6 = new ArrayList<>();
        list6.add("就业");
        list6.add("考研");
        list6.add("留学");*/
        spinner_adapter8 = new Spinner3_Adapter(list6,getContext());


    }
    private void initOnClick() {
        et_zy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 String s = charSequence.toString();
                lv_ss_zy.setVisibility(View.VISIBLE);
                SerchZYPresent serchZYPresent=new SerchZYPresent(new SerchZYView() {
                    @Override
                    public void SerchZYsuccess(final BaseBean<List<SerchZYBean>> listBaseBean) {
                        List<SerchZYBean> data = listBaseBean.data;
                        if( data!=null&&data.size()>0){
                            Zy_Sousuo_Adapter zy_sousuo_adapter=new Zy_Sousuo_Adapter(data,getContext());
                            lv_ss_zy.setAdapter(zy_sousuo_adapter);
                            lv_ss_zy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    pb3.setVisibility(View.VISIBLE);
                                    zhuanye_yuanxiao(listBaseBean.data.get(i).getName(),s1,"",s4,s5,city,"","",s2,tbsubtype,fen,cwb);
                                }
                            });
                        }
                    }

                    @Override
                    public void SerchZYfail(Throwable t) {

                    }
                });
                serchZYPresent.SerchZYPresent(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //推荐专业
        rl_tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv1_view.setVisibility(View.GONE);
                lv2_view.setVisibility(View.GONE);
                lv3_view.setVisibility(View.GONE);
                iv_right1.setVisibility(View.VISIBLE);
                iv_right2.setVisibility(View.VISIBLE);
                iv_right3.setVisibility(View.VISIBLE);
                iv_next1.setVisibility(View.GONE);
                iv_next2.setVisibility(View.GONE);
                iv_next3.setVisibility(View.GONE);
                lv1.setVisibility(View.GONE);
                lv2.setVisibility(View.GONE);
                lv3.setVisibility(View.GONE);
                flag1=true;
                flag2=true;
                flag3=true;
                tv_tj.setTextColor(getContext().getResources().getColor(R.color.delete_dialog_text_color));
                lv_zy_zhuanye.setVisibility(View.VISIBLE);
                rv_zy_zhuanye.setVisibility(View.GONE);

                 et_zy.setText("");

                lv_ss_zy.setVisibility(View.GONE);
            }
        });

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
                String s = list.get(i).toString();
                tv_yx.setText(s);
                lv1.setVisibility(View.GONE);
                iv_right1.setVisibility(View.VISIBLE);
                iv_next1.setVisibility(View.GONE);
                flag1=true;
                lv1_view.setVisibility(View.GONE);

                pb3.setVisibility(View.VISIBLE);
                if(s.equals("全国"))
                {
                    s="";
                }
                city=s;
                zhuanye_yuanxiao(major,s1,"",s4,s5,city,"","",s2,tbsubtype,fen,cwb);
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
                String s = list1.get(i).toString();
                lv2.setVisibility(View.GONE);
                tv_xk.setText(s);
                iv_right2.setVisibility(View.VISIBLE);
                iv_next2.setVisibility(View.GONE);
                flag2=true;
                lv2_view.setVisibility(View.GONE);

                pb3.setVisibility(View.VISIBLE);
                if(s.equals("不限"))
                {
                    s="";
                }
                s5=s;
                zhuanye_yuanxiao(major,s1,"",s4,s5,city,"","",s2,tbsubtype,fen,cwb);
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

                    if(vip==false)
                    {
                        ed_fen.setEnabled(false);
                        Toast.makeText(getContext(), "开通VIP才能修改分数", Toast.LENGTH_SHORT).show();
                    }
                    if(vip==true&&dateOneBigger==true)
                    {
                        ed_fen.setEnabled(false);
                        Toast.makeText(getContext(), "分数不能修改", Toast.LENGTH_SHORT).show();
                    }
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
      /*  lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
              *//*  if(list2.get(i).toString().equals("院校批次"))
                {

                    ll6.setVisibility(View.VISIBLE);
                    lv_right.setAdapter(spinner_adapter5);
                    biaoji=5;
                }*//*
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
             *//*   if(list2.get(i).toString().equals("毕业后方向"))
                {

                    ll6.setVisibility(View.VISIBLE);
                    lv_right.setAdapter(spinner_adapter8);
                    biaoji=8;
                }*//*
            }
        });
        lv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if(biaoji==4)
                {
                    city = list.get(i).toString();
                    if(city.equals("全国"))
                    {
                        city="";
                    }
                    System.out.println("选项"+ city);
                }
              *//*  if(biaoji==5)
                {
                    s3 = list3.get(i).toString();
                    System.out.println("选项"+ s3);
                }*//*
                if(biaoji==6)
                {
                    s4 = list4.get(i).toString();
                    if(s4.equals("不限"))
                    {
                       s4="";
                    }
                    System.out.println("选项"+ s4);
                }
                if(biaoji==7)
                {
                    s5 = list5.get(i).toString();
                    if(s5.equals("不限")){
                        s5="";
                    }

                    System.out.println("选项"+ s5);
                }
            *//*    if(biaoji==8)
                {
                    s6 = list6.get(i).toString();
                    System.out.println("选项"+ s6);
                }*//*
            }
        });*/
        tv_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv3_view.setVisibility(View.GONE);

                lv3.setVisibility(View.GONE);
                iv_right3.setVisibility(View.VISIBLE);
                iv_next3.setVisibility(View.GONE);
                flag3=true;
                fen=ed_fen.getText().toString();
                tv_gj.setText(fen+"分");
                System.out.println("选项"+fen+city+"null"+s4+s5+ "null");
                //s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
                pb3.setVisibility(View.VISIBLE);
                SPUtils.put(MyApp.context,"tbmaxfen",fen+"");

                zhuanye_yuanxiao(major,s1,"",s4,s5,city,"","",s2,tbsubtype,fen,cwb);
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

               /* city="";
                //s1优先级   s2考生所在地 s3普通批次  s4院校层级 s5院校类型  s6毕业后的方向
                ///s3="";
                s4="";
                s5="";
                //s6="";
                tv_yx.setText("院校区域");
                tv_xk.setText("院校类型");
                pb3.setVisibility(View.VISIBLE);
                zhuanye_yuanxiao(major,s1,"",s4,s5,city,"","",s2,tbsubtype,fen,cwb);
                System.out.println("选项+++"+s1+s2+tbsubtype+fen+cwb);*/
            }
        });

        lv1_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv1.setVisibility(View.GONE);
                lv1_view.setVisibility(View.GONE);
                iv_right1.setVisibility(View.VISIBLE);
                iv_next1.setVisibility(View.GONE);
                flag1=true;
            }
        });
        lv2_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv2.setVisibility(View.GONE);
                lv2_view.setVisibility(View.GONE);
                iv_right2.setVisibility(View.VISIBLE);
                iv_next2.setVisibility(View.GONE);
                flag2=true;
            }
        });
        lv3_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv3.setVisibility(View.GONE);
                lv3_view.setVisibility(View.GONE);
                iv_right3.setVisibility(View.VISIBLE);
                iv_next3.setVisibility(View.GONE);
                flag3=true;
            }
        });
    }
    private void init() {
        tv_yx = view.findViewById(R.id.tv_yx);
        tv_xk = view.findViewById(R.id.tv_xk);

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


        rl_tj = view.findViewById(R.id.rl_tj);
        tv_tj = view.findViewById(R.id.tv_tj);
        lv_zy_zhuanye = view.findViewById(R.id.lv_zy_zhuanye);
        rv_zy_zhuanye = view.findViewById(R.id.rv_zy_zhuanye);

        pb = view.findViewById(R.id.pb);
        pb2 = view.findViewById(R.id.pb2);
        zy_iv = view.findViewById(R.id.zy_iv);


        lv_ss_zy = view.findViewById(R.id.lv_ss_zy);
        et_zy = view.findViewById(R.id.et_zy);
        pb3 = view.findViewById(R.id.pb3);

        lv1_view = view.findViewById(R.id.lv1_view);
        lv2_view = view.findViewById(R.id.lv2_view);
        lv3_view = view.findViewById(R.id.lv3_view);
        tv_day1 = view.findViewById(R.id.tv_day1);
        tv_day2 = view.findViewById(R.id.tv_day2);
        tv_day3 = view.findViewById(R.id.tv_day3);
        rl_shengji = view.findViewById(R.id.rl_shengji);
        iv_zy_mohu = view.findViewById(R.id.iv_zy_mohu);
        tv_tishi = view.findViewById(R.id.tv_tishi);
        tv_gj = view.findViewById(R.id.tv_gj);
       /* DisplayMetrics dm = getResources().getDisplayMetrics();
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
        ll5.setLayoutParams(params2);*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cxefcPresenter.onDestory();
        countdownPresent.onDestory();
    }

    @Override
    public void Countdownsuccess(BaseBean baseBean) {
        String s = baseBean.data.toString();

        if(s!=null&&s.length()==3)
        {
            String substring = s.substring(0,1);
            String substring1 = s.substring(1);
            String substring2 = s.substring(2);
            tv_day1.setText(substring);
            tv_day2.setText(substring1);
            tv_day3.setText(substring2);
        }
        if(s!=null&&s.length()==2)
        {
            String substring = s.substring(0,1);
            String substring1 = s.substring(1);
            tv_day1.setText("0");
            tv_day2.setText(substring);
            tv_day3.setText(substring1);
        }
        else
        {
            tv_day1.setText("0");
            tv_day2.setText("0");
            tv_day3.setText(s);

        }
    }

    @Override
    public void Countdownfail(Throwable t) {

    }

    @Override
    public void XDsuccess(XDingdanBean xDingdanBean) {
        if (xDingdanBean != null) {
            String outTradeNo = xDingdanBean.getOutTradeNo();
            tanchuang(getContext(), outTradeNo);
        }
    }

    @Override
    public void ZFBPaysuccess(final String orderinfo) {
        //  final  String orderInfo="alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2016091100482610&biz_content=%7B%22body%22%3A%22%E6%B5%8B%E8%AF%95%22%2C%22out_trade_no%22%3A%222018031016260614933445%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22pay%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F6ya7kc.natappfree.cc%2Falipay%2Fpay&sign=NqORNz%2BNXCciqd8oFc1G3DYfUznjZYkfOiMU8RKs09FWq5o%2Fp2Pmg64M3HoRAHNOLabdkVyRlEVakRBorzPXI%2B1KBF7bkzX5Z9Dfy1w7bz6%2BiFX0BRptzk6u2fpn0M965BHxsj3y8q4%2FJ%2BhdhL%2B0%2BQd0Yp8qfFtxv9M9jN7ixRgLJ%2BfeQ1HIkM9O7cwxfYFo6tgTfgMSzjVM8heLKfZ3KUQkHIEDaaeOt%2FhOwhSbWZFHKvIdrz2v8orsqEJAIaKAM%2BpSlxmEUnw7TPCihEU8TghG8MIzhecas17GlmzjfBJQgOWxypP46VonUKdehcezechrhEsF3J%2BVyY6LFUVmMw%3D%3D&sign_type=RSA2&timestamp=2018-03-10+16%3A26%3A16&version=1.0&sign=NqORNz%2BNXCciqd8oFc1G3DYfUznjZYkfOiMU8RKs09FWq5o%2Fp2Pmg64M3HoRAHNOLabdkVyRlEVakRBorzPXI%2B1KBF7bkzX5Z9Dfy1w7bz6%2BiFX0BRptzk6u2fpn0M965BHxsj3y8q4%2FJ%2BhdhL%2B0%2BQd0Yp8qfFtxv9M9jN7ixRgLJ%2BfeQ1HIkM9O7cwxfYFo6tgTfgMSzjVM8heLKfZ3KUQkHIEDaaeOt%2FhOwhSbWZFHKvIdrz2v8orsqEJAIaKAM%2BpSlxmEUnw7TPCihEU8TghG8MIzhecas17GlmzjfBJQgOWxypP46VonUKdehcezechrhEsF3J%2BVyY6LFUVmMw%3D%3D";
        if (orderinfo != null) {
            Runnable payRunnable = new Runnable() {
                @Override
                public void run() {
                    PayTask alipay = new PayTask(getActivity());
                    Map<String, String> result = alipay.payV2(orderinfo, true);
                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();
        }
    }

    @Override
    public void WXPaysuccess(WeiXinBean weiXinBean) {
        if (weiXinBean != null) {
            WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
            builder.setAppId(weiXinBean.getAppid())
                    .setPartnerId(weiXinBean.getPartnerid())
                    .setPrepayId(weiXinBean.getPrepayid())
                    .setPackageValue(weiXinBean.getPackageX())
                    .setNonceStr(weiXinBean.getNoncestr())
                    .setTimeStamp(weiXinBean.getTimestamp())
                    .setSign(weiXinBean.getSign())
                    .build().toWXPayNotSign(getActivity(), weiXinBean.getAppid());
        }
    }

    @Override
    public void XDFail(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();

    }

    public void tanchuang(Context context, final String outTradeNo) {
        pay = 2;
        final Dialog dialog = new Dialog(context, R.style.Theme_Light_Dialog);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dingdan_layout, null);
        //获得dialog的window窗口
        Window window = dialog.getWindow();
        //设置dialog在屏幕底部
        window.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        window.setWindowAnimations(R.style.dialogStyle);
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        //将自定义布局加载到dialog上
        dialog.setContentView(dialogView);
        dialog.show();
        ImageView iv_chahao = dialogView.findViewById(R.id.iv_chahao);
        RelativeLayout rl5 = dialogView.findViewById(R.id.rl5);
        RelativeLayout rl6 = dialogView.findViewById(R.id.rl6);
        final ImageView iv_weixin_dui = dialogView.findViewById(R.id.iv_weixin_dui);
        final ImageView iv_zhifubao_dui = dialogView.findViewById(R.id.iv_zhifubao_dui);
        TextView tv_queren = dialogView.findViewById(R.id.tv_queren);
        TextView diangdan_money = dialogView.findViewById(R.id.diangdan_money);
        TextView tv_bianhao = dialogView.findViewById(R.id.tv_bianhao);
        TextView tv_mingcheng = dialogView.findViewById(R.id.tv_mingcheng);
     /*   if(zk)
        {
            tv_mingcheng.setText("升学设计系统--专科版");
        }
        else
        {
            tv_mingcheng.setText("升学设计系统--本科版");
        }*/
        tv_mingcheng.setText("升学设计系统");
        tv_bianhao.setText(outTradeNo);
    /*    if(zk){
            diangdan_money.setText("698");
        }else {
            diangdan_money.setText("698");
        }*/
        diangdan_money.setText("598");

        iv_chahao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        rl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_weixin_dui.setVisibility(View.VISIBLE);
                iv_zhifubao_dui.setVisibility(View.GONE);
                pay = 2;
            }
        });
        rl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_weixin_dui.setVisibility(View.GONE);
                iv_zhifubao_dui.setVisibility(View.VISIBLE);
                pay = 1;
            }
        });
        tv_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pay == 1) {
                    payPresent.ZFBpay(outTradeNo);
                } else {
                    payPresent.WXpay(outTradeNo);
                }

 /*     {
                "msg": "success",
                    "code": 0,
                    "data": {
                "appId": "wx6a6810597dadc392",
                        "nonceStr": "1345008214",
                        "package": "Sign=WXPay",
                        "partnerId": "1496598622",
                        "prepayId": "wx20180303134458805252",
                        "sign": "28F70F7923B17D091C4EEBA72155B648",
                        "timeStamp": "1520055901"
            }
            }*/
                dialog.cancel();
            }
        });
    }
}
