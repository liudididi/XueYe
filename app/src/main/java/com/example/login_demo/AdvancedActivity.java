package com.example.login_demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.MoreSchool_Adapter;
import base.BaseActivity;
import base.BaseBean;
import bean.CanSchoolBean;
import bean.CanSchoolBean3;
import bean.SlideshowBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.WishPresent;
import untils.SPUtils;
import view.WishView;

public class AdvancedActivity extends BaseActivity implements WishView {

    @BindView(R.id.advanced_iv_back)
    ImageView advancedIvBack;
    @BindView(R.id.advanced_minute)
    TextView advancedMinute;
    @BindView(R.id.advanced_sprint)
    TextView advancedSprint;
    @BindView(R.id.advanced_rl_sprint)
    RelativeLayout advancedRlSprint;
    @BindView(R.id.advanced_reliable)
    TextView advancedReliable;
    @BindView(R.id.advanced_rl_reliable)
    RelativeLayout advancedRlReliable;
    @BindView(R.id.advanced_minimum)
    TextView advancedMinimum;
    @BindView(R.id.advanced_rl_minimum)
    RelativeLayout advancedRlMinimum;

    @BindView(R.id.view_sprint)
    View view_sprint;
    @BindView(R.id.view_reliable)
    View view_reliable;
    @BindView(R.id.view_minimum)
    View view_minimum;

    @BindView(R.id.advanced_rl)
    RecyclerView advancedRl;
    @BindView(R.id.ad_tishi)
   ImageView adTishi;

    @BindView(R.id.cc_tvnum)
    TextView ccTvnum;
    @BindView(R.id.wt_tvnum)
    TextView wtTvnum;
    @BindView(R.id.bd_tvnum)
    TextView bdTvnum;


    @BindView(R.id.iv_cc)
    ImageView iv_cc;
    @BindView(R.id.iv_wt)
    ImageView iv_wt;
    @BindView(R.id.iv_bd)
    ImageView iv_bd;

    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.advanced_fen)
    TextView advanced_fen;

    @BindView(R.id.rl_chengji)
    RelativeLayout rl_chengji;
    private String tbmaxfen;
    private String tbarea;
    private String tbsubtype;

    private WishPresent wishPresent;

    private MoreSchool_Adapter moreSchool_adapter;
    private String biaoshi;
    private String schoolType;

    @Override
    public int getId() {
        return R.layout.activity_advanced;
    }

    @Override
    public void InIt() {
        wishPresent = new WishPresent(this);
        advancedRl.setLayoutManager(new LinearLayoutManager(AdvancedActivity.this));
        advancedRl.setNestedScrollingEnabled(false);

        tbmaxfen = (String) SPUtils.get(MyApp.context, "tbmaxfen", "500");
        tbarea = (String) SPUtils.get(MyApp.context, "tbarea", "北京市");
        tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "文科");
        schoolType = (String) SPUtils.get(MyApp.context, "schoolType", "默认");

        advanced_fen.setText(tbarea + tbsubtype + tbmaxfen + "分");
        biaoshi="冲刺";
        if(schoolType !=null&& schoolType !="默认"){
            String cityType = (String) SPUtils.get(MyApp.context, "cityType", "");
            String isAccept = (String) SPUtils.get(MyApp.context, "isAccept", "");
            wishPresent.CompleCanSchoolPresente((Integer.parseInt(tbmaxfen) -5)+ "",(Integer.parseInt(tbmaxfen) + 10) + "",cityType,isAccept, schoolType,tbarea,tbsubtype);
        }
        else {
            wishPresent.CompleCanSchoolPresente((Integer.parseInt(tbmaxfen) -5)+ "",(Integer.parseInt(tbmaxfen) + 10) + "","","","",tbarea,tbsubtype);
            //wishPresent.CanSchoolPresente(tbarea, tbsubtype, (Integer.parseInt(tbmaxfen) -5)+ "", (Integer.parseInt(tbmaxfen) + 10) + "", "1", "30");
        }

        advancedSprint.setTextColor(Color.BLACK);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wishPresent.onDestory();


    }

    @Override
    protected void onResume() {
        super.onResume();
        tbmaxfen = (String) SPUtils.get(MyApp.context, "tbmaxfen", "500");
        tbarea = (String) SPUtils.get(MyApp.context, "tbarea", "北京市");
        tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "文科");
        schoolType = (String) SPUtils.get(MyApp.context, "schoolType", "");

    }

    @OnClick({R.id.rl_chengji,R.id.advanced_iv_back,R.id.rl_estimate, R.id.advanced_rl_sprint, R.id.advanced_rl_reliable, R.id.advanced_rl_minimum})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.advanced_iv_back:
                finish();
                break;
            case R.id.rl_estimate:
                Intent intent=new Intent(this,CompleteWishActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.rl_chengji:
                intent(AdvancedActivity.this, EstimateGradeActivity.class);
                finish();
                break;

            case R.id.advanced_rl_sprint:
                biaoshi = "冲刺";
                pb.setVisibility(View.VISIBLE);
                advancedSprint.setTextColor(Color.BLACK);
                advancedReliable.setTextColor(Color.GRAY);
                advancedMinimum.setTextColor(Color.GRAY);
                view_sprint.setVisibility(View.VISIBLE);
                view_reliable.setVisibility(View.GONE);
                view_minimum.setVisibility(View.GONE);

                if(schoolType !=null&& schoolType !="默认"){
                    String cityType = (String) SPUtils.get(MyApp.context, "cityType", "");
                    String isAccept = (String) SPUtils.get(MyApp.context, "isAccept", "");
                     wishPresent.CompleCanSchoolPresente((Integer.parseInt(tbmaxfen) -5)+ "",(Integer.parseInt(tbmaxfen) + 10) + "",cityType,isAccept,schoolType,tbarea,tbsubtype);
                }
                else {
                    wishPresent.CompleCanSchoolPresente((Integer.parseInt(tbmaxfen) -5)+ "",(Integer.parseInt(tbmaxfen) + 10) + "","","","",tbarea,tbsubtype);

                    // wishPresent.CanSchoolPresente(tbarea, tbsubtype, (Integer.parseInt(tbmaxfen) -5)+ "", (Integer.parseInt(tbmaxfen) + 10) + "", "1", "30");
                }
                break;

            case R.id.advanced_rl_reliable:
                biaoshi = "稳妥";
                pb.setVisibility(View.VISIBLE);

                advancedSprint.setTextColor(Color.GRAY);
                advancedReliable.setTextColor(Color.BLACK);
                advancedMinimum.setTextColor(Color.GRAY);
                view_sprint.setVisibility(View.GONE);
                view_reliable.setVisibility(View.VISIBLE);
                view_minimum.setVisibility(View.GONE);
                if(schoolType !=null&& schoolType !=""){
                    String cityType = (String) SPUtils.get(MyApp.context, "cityType", "");
                    String isAccept = (String) SPUtils.get(MyApp.context, "isAccept", "");
                     wishPresent.CompleCanSchoolPresente((Integer.parseInt(tbmaxfen) -20)+ "",(Integer.parseInt(tbmaxfen) -6) + "",cityType,isAccept,schoolType,tbarea,tbsubtype);
                } else {
                    wishPresent.CompleCanSchoolPresente((Integer.parseInt(tbmaxfen) -20)+ "",(Integer.parseInt(tbmaxfen) -6) + "","","","",tbarea,tbsubtype);

                    //wishPresent.CanSchoolPresente(tbarea, tbsubtype, (Integer.parseInt(tbmaxfen) -20)+ "", (Integer.parseInt(tbmaxfen) -6) + "", "1", "30");
                }

                break;

            case R.id.advanced_rl_minimum:
                biaoshi = "保底";
                pb.setVisibility(View.VISIBLE);
                advancedSprint.setTextColor(Color.GRAY);
                advancedReliable.setTextColor(Color.GRAY);
                advancedMinimum.setTextColor(Color.BLACK);
                view_sprint.setVisibility(View.GONE);
                view_reliable.setVisibility(View.GONE);
                view_minimum.setVisibility(View.VISIBLE);
                if(schoolType !=null&& schoolType !="默认"){
                    String cityType = (String) SPUtils.get(MyApp.context, "cityType", "");
                    String isAccept = (String) SPUtils.get(MyApp.context, "isAccept", "");
                     wishPresent.CompleCanSchoolPresente(0+ "",(Integer.parseInt(tbmaxfen)-21) + "",cityType,isAccept,schoolType,tbarea,tbsubtype);
                }
                else {
                    wishPresent.CompleCanSchoolPresente(0+ "",(Integer.parseInt(tbmaxfen)-21) + "","","","",tbarea,tbsubtype);

                   // wishPresent.CanSchoolPresente(tbarea, tbsubtype, 0+ "", (Integer.parseInt(tbmaxfen)-21) + "", "1", "30");
                }

                break;

        }
    }

    @Override
    public void Wishsuccess(BaseBean<List<SlideshowBean>> listBaseBean) {

    }

    @Override
    public void Wishfail(Throwable t) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 4) {
            pb.setVisibility(View.VISIBLE);
             biaoshi="冲刺";
            String cityType=data.getStringExtra("cityType");
            String isAccept=data.getStringExtra("isAccept");
            String schoolType=data.getStringExtra("schoolType");
            SPUtils.put(MyApp.context,"cityType",cityType);
            SPUtils.put(MyApp.context,"isAccept",isAccept);
            SPUtils.put(MyApp.context,"schoolType",schoolType);
            wishPresent.CompleCanSchoolPresente((Integer.parseInt(tbmaxfen) -5)+ "",(Integer.parseInt(tbmaxfen) + 10) + "",cityType,isAccept,schoolType,tbarea,tbsubtype);
            advancedSprint.setTextColor(Color.BLACK);
            advancedReliable.setTextColor(Color.GRAY);
            advancedMinimum.setTextColor(Color.GRAY);
            view_sprint.setVisibility(View.VISIBLE);
            view_reliable.setVisibility(View.GONE);
            view_minimum.setVisibility(View.GONE);
        }


    }

    @Override
    public void CanSchoolsuccess(BaseBean<CanSchoolBean> canSchoolBeanBaseBean) {

        List<CanSchoolBean.ListBean> list1 = canSchoolBeanBaseBean.data.getList();
        ArrayList<CanSchoolBean3> list = new ArrayList<>();

        if(list1.size()>0&&list1!=null){
            pb.setVisibility(View.GONE);
            adTishi.setVisibility(View.GONE);
            advancedRl.setVisibility(View.VISIBLE);
            for (int i = 0; i < list1.size(); i++) {
                String url = list1.get(i).getUrl();
                String name = list1.get(i).getName();
                String address = list1.get(i).getAddress();
                String father = list1.get(i).getFather();
                String two = list1.get(i).getTwo();
                String nine = list1.get(i).getNine();
                String defense_student = list1.get(i).getDefense_student();
                String graduate = list1.get(i).getGraduate();
                String recruit = list1.get(i).getRecruit();
                String preeminent_plan = list1.get(i).getPreeminent_plan();
                String com_rank = list1.get(i).getCom_rank();

                list.add(new CanSchoolBean3(url, name, address, father,two,nine,defense_student,graduate,recruit,preeminent_plan,com_rank));
            }
            if (biaoshi.equals("冲刺")) {

                ccTvnum.setText(list.size()+"所");
                ccTvnum.setVisibility(View.VISIBLE);
                bdTvnum.setVisibility(View.GONE);
                wtTvnum.setVisibility(View.GONE);

                iv_cc.setVisibility(View.VISIBLE);
                iv_wt.setVisibility(View.GONE);
                iv_bd.setVisibility(View.GONE);
            } else if (biaoshi.equals("稳妥")) {

                wtTvnum.setText(list.size()+"所");
                ccTvnum.setVisibility(View.GONE);
                bdTvnum.setVisibility(View.GONE);
                wtTvnum.setVisibility(View.VISIBLE);

                iv_cc.setVisibility(View.GONE);
                iv_wt.setVisibility(View.VISIBLE);
                iv_bd.setVisibility(View.GONE);
            } else {

                bdTvnum.setText(list.size()+"所");
                ccTvnum.setVisibility(View.GONE);
                bdTvnum.setVisibility(View.VISIBLE);
                wtTvnum.setVisibility(View.GONE);

                iv_cc.setVisibility(View.GONE);
                iv_wt.setVisibility(View.GONE);
                iv_bd.setVisibility(View.VISIBLE);
            }

            moreSchool_adapter = new MoreSchool_Adapter(list, AdvancedActivity.this);
            advancedRl.setLayoutManager(new LinearLayoutManager(AdvancedActivity.this));
            advancedRl.setAdapter(moreSchool_adapter);

        }else {
            pb.setVisibility(View.GONE);

            adTishi.setVisibility(View.VISIBLE);
            advancedRl.setVisibility(View.GONE);

            if (biaoshi.equals("冲刺")) {
                if(list.size()==0)
                {
                    ccTvnum.setText(0+"所");
                }
                ccTvnum.setText(list.size()+"所");
                ccTvnum.setVisibility(View.VISIBLE);
                bdTvnum.setVisibility(View.GONE);
                wtTvnum.setVisibility(View.GONE);

                iv_cc.setVisibility(View.VISIBLE);
                iv_wt.setVisibility(View.GONE);
                iv_bd.setVisibility(View.GONE);
            } else if (biaoshi.equals("稳妥")) {
                if(list.size()==0)
                {
                    wtTvnum.setText(0+"所");
                }
                wtTvnum.setText(list.size()+"所");
                ccTvnum.setVisibility(View.GONE);
                bdTvnum.setVisibility(View.GONE);
                wtTvnum.setVisibility(View.VISIBLE);

                iv_cc.setVisibility(View.GONE);
                iv_wt.setVisibility(View.VISIBLE);
                iv_bd.setVisibility(View.GONE);
            } else  {
                if(list.size()==0 )
                {
                    bdTvnum.setText(0+"所");
                }
                bdTvnum.setText(list.size()+"所");
                ccTvnum.setVisibility(View.GONE);
                bdTvnum.setVisibility(View.VISIBLE);
                wtTvnum.setVisibility(View.GONE);

                iv_cc.setVisibility(View.GONE);
                iv_wt.setVisibility(View.GONE);
                iv_bd.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public void CanSchoolfail(Throwable t) {


    }
}
