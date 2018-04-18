package fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_demo.MyApp;
import com.example.login_demo.NumActivity;
import com.example.login_demo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapter.NumAdapter;
import adapter.SchoolEnrollAdapter;
import adapter.Spinner_Adapter2;
import adapter.ZYTJRecycleAdapter;
import base.BaseBean;
import base.Basefragment;
import bean.ForecastBean;
import bean.GailvBean;
import bean.LuquXianBean;
import bean.NumBean;
import bean.SchoolEnrollBean;
import bean.ZYNumBean;
import bean.ZYTJBean;
import bean.univCompareBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import presenter.ForecastPresent;
import presenter.NumPresenter;
import presenter.SchoolEnrollPresent;
import untils.Histogram;
import untils.MyQusetUtils;
import untils.SPUtils;
import untils.ZhiMaScoreView;
import view.ForecastView;
import view.NumView;
import view.SchoolEnrollView;

/**
 * Created by 地地 on 2018/2/5.
 * 邮箱：461211527@qq.com.
 */

public class School_Enroll  extends Basefragment implements SchoolEnrollView, ForecastView, NumView{

    private RecyclerView se_rv;
    private SchoolEnrollPresent schoolEnrollPresent;
    private String tbmaxfen;
    private String tbarea;
    private String tbsubtype;
    private ImageView iv_right;
    private TextView tv_pici;
    private ImageView iv_next;
    private boolean msg=true;
    private boolean msg2=true;
    private ListView school_lv1;
    private ListView school_lv2;
    private TextView tv_skx;
    private ImageView iv_right2;
    private ImageView iv_next2;
    private ZhiMaScoreView zhiMaScoreView;
    private TextView tv_tvarea;
    private TextView se_tvtype;
    private TextView se_tvmaxfen;
    private LinearLayout zhexian_ll;
    private TextView school_enroll_tv;
    private TextView school_enroll_tvtime;
    private String schoolname;
    private ForecastPresent forecastPresent;
    private RelativeLayout rl_tszy;
    private ImageView iv_lq_right;
    private ImageView iv_lq_xia;
    private boolean flag=true;
    private boolean flag1=true;

    private RecyclerView lv_tszy;
    private RelativeLayout rl_zdzy;
    private ImageView iv_zdzy_right;
    private ImageView iv_zdzy_xia;
    private RecyclerView rv_zdzy;
    private RecyclerView fs_rv;
    private NumPresenter numPresenter;
    private TextView tv_shuju;
    private LinearLayout ll_xiangqing;
    private ImageView iv_xia;
    private TextView tv_xq;
    private List<NumBean> data;
    private ArrayList<String>   list=new ArrayList<>();;

    @Override
    public int getLayoutid() {
        return R.layout.school_enroll;
    }

    @Override
    public void initView() {

        init();
        schoolname = getActivity().getIntent().getStringExtra("schoolname");
        tbarea = (String) SPUtils.get(MyApp.context, "tbarea", "北京市");
        tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "文科");
        tbmaxfen = (String) SPUtils.get(MyApp.context, "tbmaxfen", "500");
        schoolEnrollPresent = new SchoolEnrollPresent(this);
        schoolEnrollPresent.SchoolEnrollPresent(schoolname,tbarea,tbsubtype);
        schoolEnrollPresent.getscoreCompareMobil(tbarea,tbsubtype, schoolname);
        MyQusetUtils.getInstance().getQuestInterface()
                .univCompare(schoolname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<List<univCompareBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<univCompareBean>> univCompareBeanBaseBean) {
                            if(univCompareBeanBaseBean.code==0){
                                List<univCompareBean> data = univCompareBeanBaseBean.data;
                                if(data!=null&&data.size()>=0){
                                    for (int i = 0; i < data.size(); i++) {
                                        list.add(data.get(i).getTime());
                                    }
                                }
                                tv_pici.setText(list.get(0));
                                schoolEnrollPresent.getluquxian(tbarea, schoolname,tbsubtype,tv_pici.getText().toString(),tv_skx.getText().toString());

                                final Spinner_Adapter2 spinner_adapter=new Spinner_Adapter2(list,getContext());

                                tv_pici.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        if(msg==true)
                                        {
                                            school_lv1.setVisibility(View.VISIBLE);
                                            school_lv1.setAdapter(spinner_adapter);
                                            msg=false;
                                            iv_right.setVisibility(View.GONE);
                                            iv_next.setVisibility(View.VISIBLE);
                                        }
                                        else
                                        {
                                            school_lv1.setVisibility(View.GONE);
                                            msg=true;
                                            iv_right.setVisibility(View.VISIBLE);
                                            iv_next.setVisibility(View.GONE);
                                        }

                                    }
                                });
                            }



                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        Histogram column_two =  view.findViewById(R.id.column_two);
        column_two.setData(Integer.parseInt(tbmaxfen), 750);
        column_two.mPaint.setColor(getResources().getColor(R.color.zhu2)); //改变柱状图的颜色
        forecastPresent = new ForecastPresent(this);
        forecastPresent.ForecastPresent(tbarea,tbsubtype,schoolname);
        tv_tvarea.setText(tbarea);
        se_tvtype.setText(tbsubtype);
        se_tvmaxfen.setText(tbmaxfen);
        numPresenter = new NumPresenter(this);
        numPresenter.NumPresenter(schoolname,tbsubtype,tbarea);
        final ArrayList<String> list2=new ArrayList<>();
        list2.add("省控线");
        list2.add("平均分");
        list2.add("最高分");
        list2.add("最低分");
        final Spinner_Adapter2 spinner_adapter2=new Spinner_Adapter2(list2,getContext());
        tv_skx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(msg2==true)
                {
                    school_lv2.setVisibility(View.VISIBLE);
                    school_lv2.setAdapter(spinner_adapter2);
                    msg2=false;
                    iv_right2.setVisibility(View.GONE);
                    iv_next2.setVisibility(View.VISIBLE);
                }
                else
                {
                    school_lv2.setVisibility(View.GONE);
                    msg2=true;
                    iv_right2.setVisibility(View.VISIBLE);
                    iv_next2.setVisibility(View.GONE);
                }

            }
        });

        school_lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = list.get(i).toString();
                tv_pici.setText(s);
                school_lv1.setVisibility(View.GONE);
                msg=true;
                iv_right.setVisibility(View.VISIBLE);
                iv_next.setVisibility(View.GONE);
                schoolEnrollPresent.getluquxian(tbarea, schoolname,tbsubtype,tv_pici.getText().toString(),tv_skx.getText().toString());

            }
        });
        school_lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = list2.get(i).toString();
                tv_skx.setText(s);
                school_lv2.setVisibility(View.GONE);
                msg2=true;
                iv_right2.setVisibility(View.VISIBLE);
                iv_next2.setVisibility(View.GONE);
                schoolEnrollPresent.getluquxian(tbarea, schoolname,tbsubtype,tv_pici.getText().toString(),tv_skx.getText().toString());
            }
        });
        rl_tszy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 schoolEnrollPresent.TZmajor(schoolname,1);

                if(flag)
                {
                    iv_lq_right.setVisibility(View.GONE);
                    iv_lq_xia.setVisibility(View.VISIBLE);
                    lv_tszy.setVisibility(View.VISIBLE);
                    flag=false;
                }
               else
                {
                    iv_lq_right.setVisibility(View.VISIBLE);
                    iv_lq_xia.setVisibility(View.GONE);
                    lv_tszy.setVisibility(View.GONE);
                    flag=true;
                }

            }
        });

        rl_zdzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schoolEnrollPresent.TZmajor(schoolname,0);

                if(flag1)
                {
                    iv_zdzy_right.setVisibility(View.GONE);
                    iv_zdzy_xia.setVisibility(View.VISIBLE);
                    rv_zdzy.setVisibility(View.VISIBLE);
                    flag1=false;
                }
                else
                {
                    iv_zdzy_right.setVisibility(View.VISIBLE);
                    iv_zdzy_xia.setVisibility(View.GONE);
                    rv_zdzy.setVisibility(View.GONE);
                    flag1=true;
                }
            }
        });

        ll_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = tv_xq.getText().toString();
                if(s.equals("全部专业详情"))
                {
                    Intent intent=new Intent(getContext(),NumActivity.class);
                    //学校名称
                    String name = data.get(0).getName();
                    List<NumBean.MajorsBean> major = data.get(0).getMajors();
                    String major1 = major.get(0).getMajor();
                    intent.putExtra("schoolname",name);
                    intent.putExtra("major",major1);
                    intent.putExtra("xiabiao",0);
                    getActivity().startActivity(intent);
                }
            }
        });
    }

    private void init() {
            initid();
        school_enroll_tv = view.findViewById(R.id.school_enroll_tv);
    }

    private void initid() {
        se_rv = view.findViewById(R.id.se_rv);
        school_lv1 = view.findViewById(R.id.school_lv1);
        school_lv2= view.findViewById(R.id.school_lv2);
        tv_pici = view.findViewById(R.id.tv_pici);
        iv_right = view.findViewById(R.id.iv_right);
        iv_next = view.findViewById(R.id.iv_next);
        tv_skx = view.findViewById(R.id.tv_skx);
        iv_right2 = view.findViewById(R.id.iv_right2);
        iv_next2 = view.findViewById(R.id.iv_next2);
        tv_tvarea = view.findViewById(R.id.se_tvarea);
        se_tvtype = view.findViewById(R.id.se_tvtype);
        school_enroll_tvtime = view.findViewById(R.id.school_enroll_tvtime);
        se_tvmaxfen = view.findViewById(R.id.se_tvmaxfen);
        zhexian_ll = view.findViewById(R.id.zhexian_ll);
        rl_tszy = view.findViewById(R.id.rl_tszy);
        iv_lq_right = view.findViewById(R.id.iv_lq_right);
        iv_lq_xia = view.findViewById(R.id.iv_lq_xia);
        lv_tszy = view.findViewById(R.id.lv_tszy);
        rl_zdzy = view.findViewById(R.id.rl_zdzy);
        iv_zdzy_right = view.findViewById(R.id.iv_zdzy_right);
        iv_zdzy_xia = view.findViewById(R.id.iv_zdzy_xia);
        rv_zdzy = view.findViewById(R.id.rv_zdzy);

        fs_rv = view.findViewById(R.id.fs_rv);
        iv_xia = view.findViewById(R.id.iv_xia);
        tv_xq = view.findViewById(R.id.tv_xq);
        ll_xiangqing=view.findViewById(R.id.ll_xiangqing);
    }

    //大学录取的专业招生计划
    @Override
    public void SchoolEnrollsuccess(BaseBean<List<SchoolEnrollBean>> listBaseBean) {
        List<SchoolEnrollBean> data = listBaseBean.data;
        SchoolEnrollAdapter schoolEnrollAdapter=new SchoolEnrollAdapter(data,getContext());
        se_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        se_rv.setAdapter(schoolEnrollAdapter);
    }

    @Override
    public void SchoolEnrollfail(Throwable t) {

    }

    @Override
    public void GetlvBeansuccess(List<GailvBean> listBaseBean) {
        if(listBaseBean!=null&&listBaseBean.size()>0){
            String time = listBaseBean.get(0).getTime();
            school_enroll_tvtime.setText(time+"录取率");
            String scoreAvg = listBaseBean.get(0).getScoreAvg();

            int selffen = Integer.parseInt(tbmaxfen);
            if(scoreAvg!=null)
            {
                int fenshuxian = Integer.parseInt(scoreAvg);
                if(selffen>fenshuxian){
                    if((selffen-fenshuxian)*0.5+80<98){
                        school_enroll_tv.setText((selffen-fenshuxian)*0.5+80+"%");
                    }else {
                        school_enroll_tv.setText(98+"%");
                    }
                }else {
                    if((80-(fenshuxian-selffen)*2)>0){
                        school_enroll_tv.setText((80-(fenshuxian-selffen)*2)+"%");
                    }else {
                        school_enroll_tv.setText(0.1+"%");
                    }
                }
            }



        }

    }

    @Override
    public void GetlvBeanfail(String msg) {

    }

    @Override
    public void TZmajorsuccess(BaseBean<List<ZYTJBean>> listBaseBean) {
        List<ZYTJBean> data = listBaseBean.data;
        if(data.size()>0&&data!=null)
        {
            for (int i = 0; i < data.size(); i++) {
                List<ZYTJBean.MajorinfoBean> majorinfo = data.get(i).getMajorinfo();
                if(majorinfo!=null&&majorinfo.size()>0)
                {
                    ZYTJRecycleAdapter zytjRecycleAdapter=new ZYTJRecycleAdapter(majorinfo,getContext());
                    lv_tszy.setLayoutManager(new LinearLayoutManager(getContext()));
                    lv_tszy.setAdapter(zytjRecycleAdapter);

                    rv_zdzy.setLayoutManager(new LinearLayoutManager(getContext()));
                    rv_zdzy.setAdapter(zytjRecycleAdapter);
                }
                else
                {
                    Toast.makeText(getContext(), "暂无数据", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }

    @Override
    public void TZmajorfail(Throwable t) {

    }

    @Override
    public void LuquXianBeansuccess(List<LuquXianBean> listBaseBean) {
        zhexian_ll.removeAllViews();
         int max=0;
         int min=0;
        zhiMaScoreView = new ZhiMaScoreView(getActivity());
        List<Integer> listfen=new ArrayList<>();
        listfen.add(0);
        listfen.add(0);
        listfen.add(0);
        listfen.add(0);
        listfen.add(0);
        if(listBaseBean!=null&&listBaseBean.size()>0){
            for (int i = 0; i <5 ; i++) {
                if(listBaseBean.get(i).getScore()!=null){
                    String score = listBaseBean.get(i).getScore();
                    if(score.equals("")){
                        score="0";
                    }
                    listfen.set(4-i,Integer.parseInt(score));
                }
            }
        }
       max= Collections.max(listfen);
        min=Collections.min(listfen);
        if(min<0){
            min=0;
        }
        if(max>700){
            max=700;
        }
        zhiMaScoreView.setlistfen(listfen);
        zhiMaScoreView.setMaxScore(max);
        zhiMaScoreView.setMinScore(min);
        zhexian_ll.addView(zhiMaScoreView);
    }

    @Override
    public void LuquXianBeanfail(String msg) {
        zhexian_ll.removeAllViews();
        int max=0;
        int min=0;
        zhiMaScoreView = new ZhiMaScoreView(getActivity());
        List<Integer> listfen=new ArrayList<>();
        listfen.add(0);
        listfen.add(0);
        listfen.add(0);
        listfen.add(0);
        listfen.add(0);
        if(min<0){
            min=0;
        }
        if(max>700){
            max=700;
        }
        zhiMaScoreView.setlistfen(listfen);
        zhiMaScoreView.setMaxScore(max);
        zhiMaScoreView.setMinScore(min);
        zhexian_ll.addView(zhiMaScoreView);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        schoolEnrollPresent.onDestory();
        forecastPresent.onDestory();
        numPresenter.onDestory();
    }

    @Override
    public void Forecastsuccess(BaseBean<List<ForecastBean>> listBaseBean) {
        List<ForecastBean> data = listBaseBean.data;

        if(data!=null&&data.size()>0)
        {
            String scoreAvg = data.get(0).getScoreAvg();
            Histogram column_one =  view.findViewById(R.id.column_one);
            if(scoreAvg!=null)
            {
                column_one.setData( Integer.parseInt(scoreAvg), 750);
                column_one.mPaint.setColor(getResources().getColor(R.color.zhu1)); //改变柱状图的颜色
            }

        }

    }

    @Override
    public void Forecastfail(Throwable t) {

    }

    @Override
    public void Numsuccess(BaseBean<List<NumBean>> listBaseBean) {

        data = listBaseBean.data;
        if(data.size()>0&& data !=null)
        {
            List<NumBean.MajorsBean> majors = data.get(0).getMajors();
            NumAdapter numAdapter=new NumAdapter(majors,getContext(),schoolname);
            fs_rv.setLayoutManager(new LinearLayoutManager(getContext()));
            fs_rv.setAdapter(numAdapter);
        }
        else
        {
            iv_xia.setVisibility(View.GONE);
            tv_xq.setText("暂无数据");
        }
    }
    @Override
    public void Numfail(Throwable t) {

    }

    @Override
    public void ZYNumsuccess(BaseBean<List<ZYNumBean>> listBaseBean) {

    }

    @Override
    public void ZYNumfail(Throwable t) {

    }
}
