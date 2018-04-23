package com.example.login_demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

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

public class PrimaryActivity extends BaseActivity implements WishView {


    @BindView(R.id.primary_iv_back)
    ImageView primaryIvBack;
    @BindView(R.id.primary_minute)
    TextView primaryMinute;
    @BindView(R.id.primary_sprint)
    TextView primary_sprint;
    @BindView(R.id.primary_reliable)
    TextView primary_reliable;
    @BindView(R.id.primary_minimum)
    TextView primary_minimum;
    @BindView(R.id.view_sprint)
    View view_sprint;
    @BindView(R.id.view_reliable)
    View view_reliable;
    @BindView(R.id.view_minimum)
    View view_minimum;

    @BindView(R.id.rl_sprint)
    RelativeLayout rl_sprint;
    @BindView(R.id.rl_reliable)
    RelativeLayout rl_reliable;
    @BindView(R.id.rl_minimum)
    RelativeLayout rl_minimum;
    @BindView(R.id.primary_rl)
    XRecyclerView primary_rl;
    @BindView(R.id.prima_tishi)
    ImageView primaTishi;
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
    private WishPresent wishPresent;
     private String tbmaxfen;
    private String tbarea;
    private String tbsubtype;
    private String biaoshi;
    private String page="1";

    @Override
    public int getId() {
        return R.layout.activity_primary;
    }

    @Override
    public void InIt() {
        primary_sprint.setTextColor(Color.BLACK);
        primary_rl.setPullRefreshEnabled(false);
        primary_rl.setLoadingMoreEnabled(true);
        wishPresent = new WishPresent(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wishPresent.onDestory();

    }

    @Override
    protected void onResume() {
        super.onResume();
        primary_sprint.setTextColor(Color.BLACK);
        primary_reliable.setTextColor(Color.GRAY);
        primary_minimum.setTextColor(Color.GRAY);
        view_sprint.setVisibility(View.VISIBLE);
        view_reliable.setVisibility(View.GONE);
        view_minimum.setVisibility(View.GONE);
        tbmaxfen = (String) SPUtils.get(MyApp.context, "tbmaxfen", "500");
        tbarea = (String) SPUtils.get(MyApp.context, "tbarea", "北京市");
        tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "文科");
        primaryMinute.setText(tbarea + tbsubtype + tbmaxfen + "分");
        biaoshi = "冲刺";
        if (tbarea != null && tbarea != "" && tbmaxfen != "" && tbmaxfen != null && tbsubtype != null && tbsubtype != "") {
            wishPresent.CanSchoolPresente(tbarea, tbsubtype, (Integer.parseInt(tbmaxfen) -5) + "", (Integer.parseInt(tbmaxfen) + 10) + "", page, "30");
        }
     }

    @OnClick({R.id.primary_iv_back, R.id.primary_minute, R.id.rl_sprint, R.id.rl_reliable, R.id.rl_minimum})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.primary_iv_back:
                finish();
                break;
            case R.id.primary_minute:
                intent(PrimaryActivity.this, EstimateGradeActivity.class);
                finish();
                break;
            //冲刺推荐
            case R.id.rl_sprint:
                biaoshi = "冲刺";
                pb.setVisibility(View.VISIBLE);
                primary_sprint.setTextColor(Color.BLACK);
                primary_reliable.setTextColor(Color.GRAY);
                primary_minimum.setTextColor(Color.GRAY);
                view_sprint.setVisibility(View.VISIBLE);
                view_reliable.setVisibility(View.GONE);
                view_minimum.setVisibility(View.GONE);
                //list.clear();
                if (tbarea != null && tbarea != "" && tbmaxfen != "" && tbmaxfen != null && tbsubtype != null && tbsubtype != "") {
                    wishPresent.CanSchoolPresente(tbarea, tbsubtype, (Integer.parseInt(tbmaxfen) -5) + "", (Integer.parseInt(tbmaxfen) + 10) + "",  page, "30");

                }
                break;
            //稳妥推荐
            case R.id.rl_reliable:
                biaoshi = "稳妥";
                pb.setVisibility(View.VISIBLE);

                primary_sprint.setTextColor(Color.GRAY);
                primary_reliable.setTextColor(Color.BLACK);
                primary_minimum.setTextColor(Color.GRAY);
                view_sprint.setVisibility(View.GONE);
                view_reliable.setVisibility(View.VISIBLE);
                view_minimum.setVisibility(View.GONE);
                //list.clear();
                if (tbarea != null && tbarea != "" && tbmaxfen != "" && tbmaxfen != null && tbsubtype != null && tbsubtype != "") {
                    wishPresent.CanSchoolPresente(tbarea, tbsubtype, (Integer.parseInt(tbmaxfen) -20) + "", (Integer.parseInt(tbmaxfen)-6) + "",  page, "30");

                }

                break;
            //保底推荐
            case R.id.rl_minimum:
                biaoshi = "保底";
                pb.setVisibility(View.VISIBLE);

                primary_sprint.setTextColor(Color.GRAY);
                primary_reliable.setTextColor(Color.GRAY);
                primary_minimum.setTextColor(Color.BLACK);
                view_sprint.setVisibility(View.GONE);
                view_reliable.setVisibility(View.GONE);
                view_minimum.setVisibility(View.VISIBLE);
               // list.clear();
                if (tbarea != null && tbarea != "" && tbmaxfen != "" && tbmaxfen != null && tbsubtype != null && tbsubtype != "") {
                    wishPresent.CanSchoolPresente(tbarea, tbsubtype, 0 + "", (Integer.parseInt(tbmaxfen)-21) + "",  page, "30");

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
    public void CanSchoolsuccess(BaseBean<CanSchoolBean> canSchoolBeanBaseBean) {


        List<CanSchoolBean.ListBean> list1 = canSchoolBeanBaseBean.data.getList();
        ArrayList<CanSchoolBean3> list = new ArrayList<>();

        if (list1 != null && list1.size() > 0) {
            pb.setVisibility(View.GONE);

            primaTishi.setVisibility(View.GONE);
            primary_rl.setVisibility(View.VISIBLE);
            for (int i = 0; i < list1.size(); i++) {
                String url = list1.get(i).getUrl();
                String name = list1.get(i).getName();
                String address = list1.get(i).getAddress();
                String father = list1.get(i).getFather();
                String typeRank = list1.get(i).getTypeRank();
                //list.add(new CanSchoolBean3(url, name, address, father, typeRank));
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

            MoreSchool_Adapter  moreSchool_adapter = new MoreSchool_Adapter(list, PrimaryActivity.this);
            LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(PrimaryActivity.this);
            primary_rl.setLayoutManager(linearLayoutManager);
            primary_rl.setAdapter(moreSchool_adapter);


        } else {
            pb.setVisibility(View.GONE);
            primaTishi.setVisibility(View.VISIBLE);
            primary_rl.setVisibility(View.GONE);

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
            } else {
                if(list.size()==0)
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

        if (biaoshi.equals("冲刺")) {
            wishPresent.CanSchoolPresente(tbarea, tbsubtype, (Integer.parseInt(tbmaxfen) -5) + "", (Integer.parseInt(tbmaxfen) + 10) + "",  page, "30");
        } else if (biaoshi.equals("稳妥")) {
            wishPresent.CanSchoolPresente(tbarea, tbsubtype, (Integer.parseInt(tbmaxfen) -20) + "", (Integer.parseInt(tbmaxfen)-6) + "",  page, "30");
        } else {
            wishPresent.CanSchoolPresente(tbarea, tbsubtype, 0 + "", (Integer.parseInt(tbmaxfen)-21) + "",  page, "5");
        }

    }






}
