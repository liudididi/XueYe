package com.example.login_demo;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import adapter.HelpAdapter;
import base.BaseActivity;
import base.BaseBean;
import bean.HelpBean;
import butterknife.BindView;
import butterknife.OnClick;
import presenter.HelpPresenter;
import view.HelpView;

public class HelpActivity extends BaseActivity implements HelpView{


    @BindView(R.id.help_iv_back)
    ImageView helpIvBack;
    @BindView(R.id.help_rv)
    RecyclerView help_rv;
    private HelpPresenter helpPresenter;
    private String pid;

    @Override
    public int getId() {
        return R.layout.activity_help;
    }

    @Override
    public void InIt() {
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        helpPresenter = new HelpPresenter(this);
        helpPresenter.HelpPresenter("0", pid);
    }



    @OnClick({R.id.help_iv_back })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.help_iv_back:
                finish();
                break;

        }
    }

    @Override
    public void Helpsuccess(BaseBean<List<HelpBean>> listBaseBean) {
        List<HelpBean> data = listBaseBean.data;
        if(data!=null&&data.size()>0){
            help_rv.setLayoutManager(new LinearLayoutManager(HelpActivity.this));
            help_rv.setAdapter(new HelpAdapter(data,HelpActivity.this));
        }

    }

    @Override
    public void Helpfail(Throwable t) {
        helpPresenter = new HelpPresenter(this);
        helpPresenter.HelpPresenter("0", pid);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helpPresenter.onDestory();
    }
}
