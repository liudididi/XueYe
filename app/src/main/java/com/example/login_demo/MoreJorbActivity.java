package com.example.login_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import adapter.JobXiaoAdapter;
import adapter.JobdaAdapter;
import adapter.MoreJobExpandableListViewAdapter;
import base.BaseActivity;
import bean.MoreJobBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.MoreJobPresent;
import view.MorJobView;

public class MoreJorbActivity extends BaseActivity implements MorJobView, JobdaAdapter.SetbendaBack {


    @BindView(R.id.mschool_iv_back)
    ImageView mschoolIvBack;


    @BindView(R.id.morjob_pb)
    ProgressBar mojob_pb;
    @BindView(R.id.jobda_list)
    ListView jobdaList;
    @BindView(R.id.jobxiao_list)
    ListView jobxiaoList;
    @BindView(R.id.job_rl)
    RelativeLayout jobRl;
    private MoreJobPresent moreJobPresent;


    @Override
    public int getId() {
        return R.layout.activity_more_jorb;
    }

    @Override
    public void InIt() {
        moreJobPresent = new MoreJobPresent(this);
        moreJobPresent.selectAllJob();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moreJobPresent.onDestory();

    }

    @OnClick(R.id.mschool_iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void MorJobSuccess(List<MoreJobBean> list) {
        mojob_pb.setVisibility(View.GONE);
        jobRl.setVisibility(View.VISIBLE);
   if(list.size()>0&&list!=null){
       JobdaAdapter jobdaAdapter=new JobdaAdapter(list,this);
       jobdaAdapter.setSetjobdaBack(this);
       jobdaList.setAdapter(jobdaAdapter);
       JobXiaoAdapter jobXiaoAdapter=new JobXiaoAdapter(list.get(0).getJobListTwo(),this);
       jobxiaoList.setAdapter(jobXiaoAdapter);

   }



      /*  if (adapter == null) {
            adapter = new MoreJobExpandableListViewAdapter(list, this);
            treeViewSimple.setAdapter(adapter);
        } else {
            adapter.Refresh(list);
        }*/
    }

    @Override
    public void MorJobFail(String msg) {
        moreJobPresent.selectAllJob();
    }



    @Override
    public void setjobxiao(TextView benda_tv, List<MoreJobBean.JobListTwoBean> list) {
        JobXiaoAdapter jobXiaoAdapter=new JobXiaoAdapter(list,this);
        jobxiaoList.setAdapter(jobXiaoAdapter);
    }
}
