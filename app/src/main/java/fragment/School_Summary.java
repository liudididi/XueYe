package fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.login_demo.JJParticularsActivity;
import com.example.login_demo.R;
import com.example.login_demo.SchoolDetailActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import base.BaseBean;
import base.Basefragment;
import bean.CampusBean;
import bean.FingerpostBean;
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
        if(SchoolDetailActivity.shsd!=null){
            ss_tvshd.setText(SchoolDetailActivity.shsd);
        }

        school_summaryPresent = new School_SummaryPresent(this);
        school_summaryPresent.StudentFrom(SchoolDetailActivity.schoolname);

        schoolIntroducePresent = new SchoolIntroducePresent(new SchoolIntroduceView() {
            @Override
            public void Introducesuccess(BaseBean<List<SchoolIntroduceBean>> listBaseBean) {
                List<SchoolIntroduceBean> data = listBaseBean.data;
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
            }

            @Override
            public void Campusfail(Throwable t) {

            }

            @Override
            public void UnivImportantssuccess(BaseBean<ZDXKBean> zdxkBeanBaseBean) {
                //重点学科
                major = zdxkBeanBaseBean.data.getMajor();

            }

            @Override
            public void UnivImportantfail(Throwable t) {

            }
        });
        schoolIntroducePresent.SchoolIntroducePresent(SchoolDetailActivity.schoolname);
        schoolIntroducePresent.FingerpostPresent(SchoolDetailActivity.schoolname);
        schoolIntroducePresent.CampusPresent(SchoolDetailActivity.schoolname);
        schoolIntroducePresent.ZDXKPresent("major",SchoolDetailActivity.schoolname);
    }

    private void onClick() {
        ll_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SchoolDetailActivity.ff(history);
            }
        });
        ss_sfbz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SchoolDetailActivity.ff(feescale);
            }
        });
        ss_jjzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SchoolDetailActivity.ff(award);
            }
        });
        ss_stys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SchoolDetailActivity.ff(eatandsleep);
            }
        });

        ss_szll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SchoolDetailActivity.ff(teacherpower);
            }
        });
        ss_zxsxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SchoolDetailActivity.ff(inschoolstu);
            }
        });
        ss_jyqk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SchoolDetailActivity.ff(employment);
            }
        });
        rl_zdsys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SchoolDetailActivity.ff(laboratory);
            }
        });
        rl_zdxk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SchoolDetailActivity.ff(major);
            }
        });

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
    }

    @Override
    public void Studentfromsuccess(List<StudentFromBean> listBaseBean) {
     if(listBaseBean.size()>0&&listBaseBean!=null){
         StudentFromBean studentFromBean = listBaseBean.get(0);
         PieChatView pieChatView=new PieChatView(getContext());
         pieChatView.setPadding(80,50,80,50);
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
         tvwomen_bfb.setText(studentFromBean.getWoman()+"%");
         tvman_bfb.setText(studentFromBean.getMan()+"%");
         ss_back.setVisibility(View.GONE);
         System.out.println("1111111111"+studentFromBean.getHn());
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
