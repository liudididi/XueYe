package com.example.login_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import base.BaseActivity;
import bean.JobInforBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.GetJobinfoPresent;
import view.GetJobinfoView;

public class JobDetailsActivity extends BaseActivity implements GetJobinfoView {

    @BindView(R.id.jobd_tvjob)
    TextView jobdTvjob;
    @BindView(R.id.jobd_iv_back)
    ImageView jobdIvBack;
    @BindView(R.id.jobd_tvgqdy)
    TextView jobdTvgqdy;
    @BindView(R.id.jobd_tvxzzk)
    TextView jobdTvxzzk;
    @BindView(R.id.jobd_tvzyjs)
    TextView jobdTvzyjs;
    @BindView(R.id.jobd_tvrztj)
    TextView jobdTvrztj;
    @BindView(R.id.jobd_tvfzkj)
    TextView jobdTvfzkj;
    @BindView(R.id.jobd_tvgzlx)
    TextView jobdTvgzlx;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.jobtv5)
    TextView jobtv5;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.jobtv6)
    TextView jobtv6;
    @BindView(R.id.tv_wenben)
    TextView tvWenben;
    @BindView(R.id.rl_wenben)
    RelativeLayout rlWenben;
    @BindView(R.id.rl_zyjsxq)
    TextView rlZyjsxq;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.jobtv7)
    TextView jobtv7;
    @BindView(R.id.rl_rztjxq)
    TextView rlRztjxq;
    @BindView(R.id.rl_fzkjxq)
    TextView rlFzkjxq;
    private GetJobinfoPresent getJobinfoPresent;

    @Override
    public int getId() {
        return R.layout.activity_job_details;
    }

    @Override
    public void InIt() {
        String jobname = getIntent().getStringExtra("jobname");
        jobdTvjob.setText(jobname);
        getJobinfoPresent = new GetJobinfoPresent(this);
        getJobinfoPresent.getJobInfo(jobname);
        rlWenben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlWenben.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getJobinfoPresent.onDestory();
    }

    @OnClick(R.id.jobd_iv_back)
    public void onViewClicked() {

        finish();
    }

    @Override
    public void GetJobinfoSuccess(List<JobInforBean> list) {
        if (list != null && list.size() > 0) {
            JobInforBean jobInforBean = list.get(0);
            if (jobInforBean.getJobEducation() != null&&jobInforBean.getJobEducation().length()>2) {
                jobdTvgqdy.setText(jobInforBean.getJobEducation());
            }else {
                jobdTvgqdy.setText("暂无数据");
            }
            if (jobInforBean.getZeroMonery() != null&&jobInforBean.getZeroMonery().length()>2) {
                jobdTvxzzk.setText(jobInforBean.getZeroMonery());
            }else {
                jobdTvgqdy.setText("暂无数据");
            }

            if (jobInforBean.getThreefiveMonery() != null&&jobInforBean.getThreefiveMonery().length()>2) {
                jobdTvzyjs.setText(jobInforBean.getThreefiveMonery());
            }else {
                jobdTvgqdy.setText("暂无数据");
            }

            if (jobInforBean.getJobRequirement() != null&&jobInforBean.getJobRequirement().length()>2) {
                jobdTvrztj.setText(jobInforBean.getJobRequirement());
            }else {
                jobdTvgqdy.setText("暂无数据");
            }

            if (jobInforBean.getTwoMonery() != null&&jobInforBean.getJobRequirement().length()>2) {
                jobdTvfzkj.setText(jobInforBean.getTwoMonery());
            }else {
                jobdTvgqdy.setText("暂无数据");
            }
            if (jobInforBean.getJobContent() != null&&jobInforBean.getJobRequirement().length()>2) {
                jobdTvgzlx.setText(jobInforBean.getJobContent());
            }else {
                jobdTvgqdy.setText("暂无数据");
            }

        }
    }

    @Override
    public void GetJobinfoFail(String msg) {

    }
    @OnClick({R.id.rl_zyjsxq, R.id.rl_rztjxq, R.id.rl_fzkjxq,R.id.rl_zygwdyxq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_zyjsxq:
                tvWenben.setText(jobdTvzyjs.getText().toString());
                rlWenben.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_rztjxq:
                tvWenben.setText(jobdTvrztj.getText().toString());
                rlWenben.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_fzkjxq:
                tvWenben.setText(jobdTvfzkj.getText().toString());
                rlWenben.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_zygwdyxq:
                tvWenben.setText(jobdTvgqdy.getText().toString());
                rlWenben.setVisibility(View.VISIBLE);
                break;
        }
    }
}
