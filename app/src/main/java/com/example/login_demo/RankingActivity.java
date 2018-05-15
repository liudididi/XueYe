package com.example.login_demo;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import adapter.RanKingSchool_Adapter;
import base.BaseActivity;
import base.BaseBean;
import bean.RanKingSchoolBean;
import butterknife.BindView;
import butterknife.OnClick;
import presenter.RanKingPresent;
import view.RankingView;

public class RankingActivity extends BaseActivity implements RankingView{

    @BindView(R.id.ranking_iv_back)
    ImageView rankingIvBack;
    @BindView(R.id.ranking_rv)
    XRecyclerView rankingRv;
    @BindView(R.id.pb)
    ProgressBar pb;
    private RanKingPresent ranKingPresent;
    private int page=1;
    private RanKingSchool_Adapter ranKingSchool_adapter;

    @Override
    public int getId() {
        return R.layout.activity_ranking;
    }

    @Override
    public void InIt() {
        ranKingPresent = new RanKingPresent(this);
        ranKingPresent.RanKingPresent(page,10);
        rankingRv.setPullRefreshEnabled(false);
        rankingRv.setLoadingMoreEnabled(true);

    }



    @OnClick({R.id.ranking_iv_back })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ranking_iv_back:
                finish();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ranKingPresent.onDestory();

    }

    //大学排序
    @Override
    public void RanKingsuccess(BaseBean<RanKingSchoolBean> ranKingSchoolBeanBaseBean) {

        pb.setVisibility(View.GONE);
        List<RanKingSchoolBean.ListBean> list = ranKingSchoolBeanBaseBean.data.getList();

        if(ranKingSchool_adapter==null)
        {
            ranKingSchool_adapter = new RanKingSchool_Adapter(list,RankingActivity.this);
            rankingRv.setLayoutManager(new LinearLayoutManager(RankingActivity.this));
            rankingRv.setAdapter(ranKingSchool_adapter);
        }
       else
        {
            ranKingSchool_adapter.LoadMore(list);
        }
        rankingRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //允许刷新，加载更多
            }
            @Override
            public void onLoadMore() {
                rankingRv.loadMoreComplete();
                page++;
                ranKingPresent.RanKingPresent(page,10);
             }
        });


    }

    @Override
    public void RanKingfail(Throwable t) {

    }
}
