package com.example.login_demo;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import base.BaseActivity;
import bean.JobInforBean;
import butterknife.BindView;
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
    @BindView(R.id.rl_zygwdyxq)
    TextView rl_zygwdyxq;

    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;

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
                jobdTvgqdy.post(new Runnable() {
                    @Override
                    public void run() {
                        int lineCount = jobdTvgqdy.getLineCount();
                        if(lineCount<5)
                        {
                            rl_zygwdyxq.setVisibility(View.GONE);
                            iv1.setVisibility(View.GONE);
                        }
                     }
                });
                jobdTvgqdy.setText(jobInforBean.getJobEducation());
            }else {
                jobdTvgqdy.setText("暂无数据");
                rl_zygwdyxq.setVisibility(View.GONE);
                iv1.setVisibility(View.GONE);
            }
            if (jobInforBean.getZeroMonery() != null&&jobInforBean.getZeroMonery().length()>2) {

                jobdTvxzzk.setText(jobInforBean.getZeroMonery());

            }else {
                jobdTvxzzk.setText("暂无数据");
            }

            if (jobInforBean.getThreefiveMonery() != null&&jobInforBean.getThreefiveMonery().length()>2) {
                jobdTvzyjs.post(new Runnable() {
                    @Override
                    public void run() {
                        int lineCount = jobdTvzyjs.getLineCount();
                        if(lineCount<4)
                        {
                            rlZyjsxq.setVisibility(View.GONE);
                            iv2.setVisibility(View.GONE);
                        }
                    }
                });
                jobdTvzyjs.setText(jobInforBean.getThreefiveMonery());
            }else {
                jobdTvzyjs.setText("暂无数据");
                rlZyjsxq.setVisibility(View.GONE);
                iv2.setVisibility(View.GONE);
            }

            if (jobInforBean.getJobRequirement() != null&&jobInforBean.getJobRequirement().length()>2) {
                jobdTvrztj.post(new Runnable() {
                    @Override
                    public void run() {
                        int lineCount = jobdTvrztj.getLineCount();
                        if(lineCount<4)
                        {
                            rlRztjxq.setVisibility(View.GONE);
                            iv3.setVisibility(View.GONE);
                        }
                    }
                });
                jobdTvrztj.setText(jobInforBean.getJobRequirement());
            }else {
                jobdTvrztj.setText("暂无数据");
                rlRztjxq.setVisibility(View.GONE);
                iv3.setVisibility(View.GONE);
            }

            if (jobInforBean.getTwoMonery() != null&&jobInforBean.getJobRequirement().length()>2) {

                jobdTvfzkj.post(new Runnable() {
                    @Override
                    public void run() {
                        int lineCount = jobdTvfzkj.getLineCount();
                        if(lineCount<4)
                        {
                            rlFzkjxq.setVisibility(View.GONE);
                            iv4.setVisibility(View.GONE);
                        }
                    }
                });
                jobdTvfzkj.setText(jobInforBean.getTwoMonery());
            }else {
                jobdTvfzkj.setText("暂无数据");
                rlFzkjxq.setVisibility(View.GONE);
                iv4.setVisibility(View.GONE);
            }
            if (jobInforBean.getJobContent() != null&&jobInforBean.getJobRequirement().length()>2) {

                jobdTvgzlx.setText(jobInforBean.getJobContent());
            }else {
                jobdTvgzlx.setText("暂无数据");
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
