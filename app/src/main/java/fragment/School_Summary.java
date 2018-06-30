package fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.login_demo.JJParticularsActivity;
import com.example.login_demo.MyApp;
import com.example.login_demo.ParticularsActivity;
import com.example.login_demo.R;
import com.example.login_demo.SchoolDetailActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import base.BaseBean;
import base.Basefragment;
import bean.CampusBean;
import bean.FingerpostBean;
import bean.LXBean;
import bean.SchoolIntroduceBean;
import bean.StudentFromBean;
import bean.ZDXKBean;
import presenter.SchoolIntroducePresent;
import presenter.School_SummaryPresent;
import view.PieChatView;
import view.SchoolIntroduceView;
import view.School_SummaryView;

/**
 * Created by 地地 on 2018/2/5.
 * 邮箱：461211527@qq.com.
 */

public class School_Summary  extends Basefragment implements School_SummaryView {

    private TextView ss_tvshd;
    private TextView ss_bsd;
    private RelativeLayout ss_rlbin;
    private LinkedHashMap kindsMap = new LinkedHashMap<String, Integer>();
    private ArrayList<Integer> colors = new ArrayList<>();
    private School_SummaryPresent school_summaryPresent;
    private TextView tvwomen_bfb;
    private TextView tvman_bfb;
    private LinearLayout ll_xiangqing;
    private TextView ss_tvjs;
    private SchoolIntroducePresent schoolIntroducePresent;
    private TextView fmgk_tv;
    private String history;
    private RelativeLayout ss_sfbz;
    private RelativeLayout ss_jjzz;
    private RelativeLayout rl_zdsys;
    private String feescale;
    private String award;
    private RelativeLayout ss_stys;
    private RelativeLayout ss_szll;
    private RelativeLayout ss_zxsxx;
    private RelativeLayout ss_jyqk;
    private RelativeLayout rl_zdxk;
    private ImageView ss_back;
    private String teacherpower;
    private String inschoolstu;
    private String eatandsleep;
    private String employment;
    private String laboratory;
    private String major;
    private TextView tv_wangzhi;
    private TextView tv_youxiang;
    private TextView tv_phone;
    final public static int REQUEST_CODE_ASK_CALL_PHONE=123;
    private RelativeLayout rl_wenben;
    private TextView tv_wenben;

    @Override
    public int getLayoutid() {
        return R.layout.school_summary;
    }

    @Override
    public void initView() {
        initid();
        onClick();
        if(SchoolDetailActivity.bhsd!=null){
            ss_bsd.setText(SchoolDetailActivity.bhsd);
        }
        else
        {
            ss_bsd.setText("暂无信息");
        }
        if(SchoolDetailActivity.shsd!=null){
            ss_tvshd.setText(SchoolDetailActivity.shsd);
        }
        else
        {
            ss_tvshd.setText("暂无信息");
        }

        school_summaryPresent = new School_SummaryPresent(this);
        school_summaryPresent.StudentFrom(SchoolDetailActivity.schoolname);

        schoolIntroducePresent = new SchoolIntroducePresent(new SchoolIntroduceView() {
            @Override
            public void Introducesuccess(BaseBean<List<SchoolIntroduceBean>> listBaseBean) {
                List<SchoolIntroduceBean> data = listBaseBean.data;
                if(data.size()>0&&data!=null)
                {
                    if(data.get(0).getHistory().equals(""))
                    {
                        ss_tvjs.setVisibility(View.VISIBLE);
                        ss_tvjs.setText("暂无数据");

                    }
                    else
                    {
                        history = data.get(0).getHistory();
                        ss_tvjs.setVisibility(View.VISIBLE);
                        ss_tvjs.setText(history);
                    }
                }
                else
                {
                    ss_tvjs.setText("暂无数据");
                }

            }

            @Override
            public void Introducefail(Throwable t) {

            }

            @Override
            public void Fingerpostsuccess(BaseBean<List<FingerpostBean>> listBaseBean) {
                List<FingerpostBean> data = listBaseBean.data;
                if(data.size()>0&&data!=null)
                {
                    feescale = data.get(0).getFeescale();
                    award = data.get(0).getAward();
                }

            }

            @Override
            public void Fingerpostfail(Throwable t) {

            }

            @Override
            public void Campussuccess(BaseBean<List<CampusBean>> listBaseBean) {
                List<CampusBean> data = listBaseBean.data;
                if(data.size()>0&&data!=null)
                {
                    //师资力量
                    teacherpower = data.get(0).getTeacherpower();
                    //在校生信息
                    inschoolstu = data.get(0).getInschoolstu();
                    //食宿情况
                    eatandsleep = data.get(0).getEatandsleep();
                    //就业情况
                    employment = data.get(0).getEmployment();
                    //重点实验室
                    laboratory = data.get(0).getLaboratory();
                }
                else
                {
                    //师资力量
                    teacherpower = "数据整理中";
                    //在校生信息
                    inschoolstu = "数据整理中";
                    //食宿情况
                    eatandsleep = "数据整理中";
                    //就业情况
                    employment = "数据整理中";
                    //重点实验室
                    laboratory = "数据整理中";
                }

            }

            @Override
            public void Campusfail(Throwable t) {

            }

            @Override
            public void UnivImportantssuccess(BaseBean<ZDXKBean> zdxkBeanBaseBean) {
                //重点学科
                if(zdxkBeanBaseBean.data!=null)
                {
                    major = zdxkBeanBaseBean.data.getMajor();
                }
                else
                {
                    major ="数据整理中";
                }



            }

            @Override
            public void UnivImportantfail(Throwable t) {

            }

            @Override
            public void LianXisuccess(BaseBean<List<LXBean>> lxBeanBaseBean) {
                List<LXBean> data = lxBeanBaseBean.data;
                if(data.size()>0&&data!=null)
                {
                    //网址
                    final String admission_plan =data.get(0).getWebsite();
                    //邮箱


                    String email =data.get(0).getEmail();
                    //电话
                    final String phone = data.get(0).getPhone();
                    if(admission_plan!=null){
                    if(!admission_plan.equals(""))
                    {
                        tv_wangzhi.setText(admission_plan);
                        tv_wangzhi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), ParticularsActivity.class);
                                intent.putExtra("particulars_title","官方网址");
                                intent.putExtra("url",admission_plan);
                                startActivity(intent);
                            }
                        });
                    }
                  else
                    {
                        tv_wangzhi.setText("                               暂无数据");

                    }
                    }
                    else
                    {
                        tv_wangzhi.setText("                               暂无数据");

                    }
                    if(!email.equals("")&&email!=null)
                    {
                        tv_youxiang.setText(email);
                    }
                    else
                    {
                        tv_youxiang.setText("暂无数据");

                    }
                    if(!phone.equals(""))
                    {
                        tv_phone.setText(phone);
                        tv_phone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder ab=new AlertDialog.Builder(getContext()).setMessage("是否拨打"+phone+"?")
                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                callPhone(phone);
                                            }
                                        });
                                ab.create().show();
                            }
                        });

                    }
                    else
                    {
                        tv_phone.setText("暂无数据");

                    }
                }

                else
                {
                    tv_wangzhi.setText("                               暂无数据");
                    tv_youxiang.setText("暂无数据");
                    tv_phone.setText("暂无数据");
                }

            }

            @Override
            public void LianXifail(Throwable t) {

            }
        });
        schoolIntroducePresent.SchoolIntroducePresent(SchoolDetailActivity.schoolname);
        schoolIntroducePresent.FingerpostPresent(SchoolDetailActivity.schoolname);
        schoolIntroducePresent.CampusPresent(SchoolDetailActivity.schoolname);
        schoolIntroducePresent.ZDXKPresent("major",SchoolDetailActivity.schoolname);
        schoolIntroducePresent.LianXIPresent(SchoolDetailActivity.schoolname);
    }
    public void callPhone(String phoneNum) {

        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.CALL_PHONE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[] {
                        Manifest.permission.CALL_PHONE
                }, REQUEST_CODE_ASK_CALL_PHONE);
                return;
            } else {
                 Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
        }
    }

    //动态权限申请后处理
    @Override public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults){
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted callDirectly(mobile);
                }else {
                    // Permission Denied Toast.makeText(MainActivity.this,"CALL_PHONE Denied", Toast.LENGTH_SHORT) .show();
                }break;
              default:super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } }


    private void onClick() {
        ll_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ff(history);

            }
        });
        ss_sfbz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ff(feescale);


            }
        });


        ss_jjzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 ff(award);
            }
        });
        ss_stys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!eatandsleep.equals(""))
                {
                    ff(eatandsleep);
                }
                else
                {
                   ff("数据整理中");

                }
            }
        });
        ss_szll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teacherpower.equals(""))
                {
                    ff(teacherpower);
                 }
                else
                {
                    ff("数据整理中");
                 }

            }
        });
        ss_zxsxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!inschoolstu.equals(""))
                {
                    ff(inschoolstu);
                }
                else
                {
                   ff("数据整理中");

                }

            }
        });
        ss_jyqk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!employment.equals("")&&employment!=null)
                {
                    ff(employment);

                }
                else
                {
                   ff("数据整理中");

                }

            }
        });
        rl_zdsys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!laboratory.equals(""))
                {
                  ff(laboratory);
                }
                else
                {
                  ff("数据整理中");

                }

            }
        });
        rl_zdxk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!major.equals(""))
                {
                   ff(major);
                }
                else
                {
                   ff("数据整理中");
                }
            }
        });

    }

    private void ff(String str) {
        rl_wenben.setVisibility(View.VISIBLE);
        if (str != null) {
            tv_wenben.setText(str);
        } else {
            tv_wenben.setText("暂无数据");
        }
    }

    private void initid() {
        ss_tvshd = view.findViewById(R.id.ss_tvshd);
        ss_bsd = view.findViewById(R.id.ss_bsd);
        ss_rlbin = view.findViewById(R.id.ss_rlbin);
        tvwomen_bfb = view.findViewById(R.id.tvwomen_bfb);
        tvman_bfb = view.findViewById(R.id.tvman_bfb);
        ll_xiangqing = view.findViewById(R.id.ll_xiangqing);
        ss_tvjs = view.findViewById(R.id.ss_tvjs);
        fmgk_tv = view.findViewById(R.id.fmgk_tv);
        ss_sfbz = view.findViewById(R.id.ss_sfbz);
        ss_jjzz = view.findViewById(R.id.ss_jjzz);
        ss_stys = view.findViewById(R.id.ss_stys);
        ss_szll = view.findViewById(R.id.ss_szll);
        ss_back = view.findViewById(R.id.ss_back);
        ss_zxsxx = view.findViewById(R.id.ss_zxsxx);
        ss_jyqk = view.findViewById(R.id.ss_jyqk);
        rl_zdsys = view.findViewById(R.id.rl_zdsys);
        rl_zdxk = view.findViewById(R.id.rl_zdxk);
        tv_wangzhi = view.findViewById(R.id.tv_wangzhi);
        tv_youxiang = view.findViewById(R.id.tv_youxiang);
        tv_phone = view.findViewById(R.id.tv_phone);
          if(getActivity()!=null){
              rl_wenben = getActivity().findViewById(R.id.rl_wenben);
              tv_wenben = getActivity().findViewById(R.id.tv_wenben);
          }

    }
    @Override
    public void Studentfromsuccess(List<StudentFromBean> listBaseBean) {
     if(listBaseBean.size()>0&&listBaseBean!=null){
         StudentFromBean studentFromBean = listBaseBean.get(0);
         PieChatView pieChatView=new PieChatView(MyApp.context);
         pieChatView.setPadding(120,60,100,60);
         kindsMap.put("华北", studentFromBean.getHn());
         kindsMap.put("东北", studentFromBean.getEn());
         kindsMap.put("华东", studentFromBean.getHe());
         kindsMap.put("华南", studentFromBean.getHs());
         kindsMap.put("西北", studentFromBean.getWn());
         kindsMap.put("西南",studentFromBean.getWs());
         colors.add(Color.BLUE);//蓝色
         colors.add(Color.YELLOW);//黄色
         colors.add(-12067354);//浅蓝
         colors.add(-5808646);//紫色
         colors.add(Color.GREEN);//绿色
         colors.add(Color.RED);//红色
         pieChatView.setCenterTitle(" ");
         pieChatView.setDataMap(kindsMap);
         pieChatView.setColors(colors);
         pieChatView.setMinAngle(50);
         pieChatView.startDraw();
         ss_rlbin.addView(pieChatView);
         if(studentFromBean.getWoman()!=0)
         {
             tvwomen_bfb.setText(studentFromBean.getWoman()+"%");
         }
         else
         {
             tvwomen_bfb.setText(0+"%");
         }
        if(studentFromBean.getMan()!=0)
        {
            tvman_bfb.setText(studentFromBean.getMan()+"%");
        }
        else
        {
            tvman_bfb.setText(0+"%");
        }

         ss_back.setVisibility(View.GONE);
          if(studentFromBean.getWn()==0&&studentFromBean.getWs()==0&&studentFromBean.getHs()==0&&studentFromBean.getHe()==0&&studentFromBean.getHn()==0&&studentFromBean.getEn()==0)
         {
             ss_back.setVisibility(View.VISIBLE);
         }
     }
     else
     {
         ss_back.setVisibility(View.VISIBLE);
     }

    }

    @Override
    public void StudentfromFail(String msg) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        schoolIntroducePresent.onDestory();
    }
}
