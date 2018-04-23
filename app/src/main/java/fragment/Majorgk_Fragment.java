package fragment;

import android.view.View;
import android.widget.TextView;

import com.example.login_demo.MajorDetailActivity;
import com.example.login_demo.R;
import com.google.gson.annotations.Until;

import java.util.ArrayList;
import java.util.List;

import adapter.ZhuanYgk_zhishiAdapter;
import base.Basefragment;
import bean.MajorgkBean;
import presenter.MajorgkPresent;
import untils.FlowLayout;
import untils.FlowLayoutzy;
import untils.ListViewForScrollView;
import view.MajorgkView;

/**
 * Created by 地地 on 2018/2/2.
 * 邮箱：461211527@qq.com.
 */

public class Majorgk_Fragment  extends Basefragment implements MajorgkView{


    private MajorgkPresent majorgkPresent;
    private TextView fmgk_tvdaima;
    private TextView fmgk_tvcckemu;
    private TextView fmgk_tvssdl;
    private TextView fmgk_ccyear;
    private TextView fmgk_tvxw;
    private TextView fmgk_tvzykc;
    private TextView fmgk_tvpyxq;
    private TextView fmgk_zyjs;
    private TextView fmgk_tvpymb;
    private ListViewForScrollView fmgk_zsnl;
    private TextView fmgk_snxz;
    private FlowLayoutzy fmgk_flow;
    private TextView fmgk_tvzsnl;

    @Override
    public int getLayoutid() {
        return R.layout.majorgk;
    }

    @Override
    public void initView() {
         initid();
        majorgkPresent = new MajorgkPresent(this);
        majorgkPresent.getMajorgk(MajorDetailActivity.majorid);
        fmgk_tvdaima.setText(MajorDetailActivity.majorid);
        String substring = MajorDetailActivity.majorid.substring(0, 2);
        int i = Integer.parseInt(substring);
        if(i<50){
            fmgk_tvcckemu.setText("本科");
        }else {
            fmgk_tvcckemu.setText("专科");
        }

    }

    private void initid() {

        fmgk_tvdaima = view.findViewById(R.id.fmgk_tvdaima);
        fmgk_tvcckemu = view.findViewById(R.id.fmgk_tvcckemu);
        fmgk_tvssdl = view.findViewById(R.id.fmgk_tvssdl);
        fmgk_ccyear = view.findViewById(R.id.fmgk_ccyear);
        fmgk_tvxw = view.findViewById(R.id.fmgk_tvxw);
        fmgk_tvzykc = view.findViewById(R.id.fmgk_tvzykc);
        fmgk_tvpyxq = view.findViewById(R.id.fmgk_tvpyxq);
        fmgk_zyjs = view.findViewById(R.id.fmgk_zyjs);
        fmgk_tvpymb = view.findViewById(R.id.fmgk_tvpymb);
        fmgk_zsnl = view.findViewById(R.id.fmgk_zsnl);
        fmgk_snxz = view.findViewById(R.id.fmgk_snxz);
        fmgk_flow = view.findViewById(R.id.fmgk_flow);
        fmgk_tvzsnl = view.findViewById(R.id.fmgk_tvzsnl);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        majorgkPresent.onDestory();
    }

    @Override
    public void MajorgkSusccess(MajorgkBean majorgkBean) {
        if(majorgkBean!=null){

            if(majorgkBean.getTraining_target()!=null){
                fmgk_tvpymb.setText(majorgkBean.getTraining_target());
            }
            if(majorgkBean.getBigType()!=null){
                fmgk_tvssdl.setText(majorgkBean.getBigType());
            }
            if(majorgkBean.getYear_school()!=null){
                fmgk_ccyear.setText(majorgkBean.getYear_school());
            }
            if(majorgkBean.getAward_degree()!=null){

                fmgk_tvxw.setText(majorgkBean.getAward_degree());
            }
            if(majorgkBean.getCourse()!=null){

                fmgk_tvzykc.setText(majorgkBean.getCourse());
            }
            if(majorgkBean.getTraining_requirements()!=null){
                fmgk_tvpyxq.setText(majorgkBean.getTraining_requirements());

            }
            if(majorgkBean.getAveragesalary()!=0){
                fmgk_snxz.setText("￥"+majorgkBean.getAveragesalary());
            }



         if(majorgkBean.getPractice_teaching()!=null){
             fmgk_zyjs.setText(majorgkBean.getPractice_teaching());
         }
         //知识能力
         if(majorgkBean.getAbility()!=null&&majorgkBean.getAbility().size()>0){
             fmgk_tvzsnl.setVisibility(View.GONE);
             List<MajorgkBean.AbilityBean> ability = majorgkBean.getAbility();
             ZhuanYgk_zhishiAdapter zhuanYgk_zhishiAdapter=new ZhuanYgk_zhishiAdapter(ability,getActivity());
             fmgk_zsnl.setAdapter(zhuanYgk_zhishiAdapter);
         }
         if(majorgkBean.getJob()!=null&&majorgkBean.getJob().size()>0){
             List<String> list=new ArrayList<>();
             List<MajorgkBean.JobBean> job = majorgkBean.getJob();
             for (int i = 0; i < job.size(); i++) {
                 list.add(job.get(i).getJobname());
             }
             fmgk_flow.setListData(list);
         }


        }

    }

    @Override
    public void MajorgkFail(String msg) {

    }
}
