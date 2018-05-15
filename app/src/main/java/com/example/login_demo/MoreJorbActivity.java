package com.example.login_demo;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import adapter.JobXiaoAdapter;
import adapter.JobdaAdapter;
import base.BaseActivity;
import bean.MoreJobBean;
import butterknife.BindView;
import butterknife.OnClick;
import presenter.MoreJobPresent;
import view.MorJobView;

public class MoreJorbActivity extends BaseActivity implements MorJobView, JobdaAdapter.SetbendaBack {


    @BindView(R.id.mschool_iv_back)
    ImageView mschoolIvBack;

    @BindView(R.id.job_search)
    ImageView job_search;

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

        job_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent(MoreJorbActivity.this,SearchParticularsActivity.class);
            }
        });

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
       Animation animation = (Animation) AnimationUtils.loadAnimation(
               MoreJorbActivity.this, R.anim.rotate);
       LayoutAnimationController lac = new LayoutAnimationController(animation);
       lac.setDelay(0.2f);  //设置动画间隔时间
       lac.setOrder(LayoutAnimationController.ORDER_NORMAL); //设置列表的显示顺序
       jobdaList.setLayoutAnimation(lac);
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
