package com.example.login_demo;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.weavey.loading.lib.LoadingLayout;

import java.util.ArrayList;
import java.util.List;

import adapter.MoreSchoolRecycle;
import adapter.Spinner_Adapter;
import base.BaseActivity;
import bean.CheckSchoolBean;
import butterknife.BindView;
import butterknife.OnClick;
import presenter.MoreSchoolPresent;
import untils.SPUtils;
import view.MoreSchoolView;

public class MoreSchoolActivity extends BaseActivity implements MoreSchoolView {
    @BindView(R.id.mschool_iv_back)
    ImageView mschoolIvBack;
    @BindView(R.id.mschool_search)
    ImageView mschoolSearch;

    @BindView(R.id.mschool_none)
    ImageView img_none;


    @BindView(R.id.diqu_list)
    ListView diqu_list;
    @BindView(R.id.yuanxiao_list)
    ListView yuanxiao_list;

    @BindView(R.id.rl_diqu)
    RelativeLayout rl_diqu;
    @BindView(R.id.rl_yuanxiao)
    RelativeLayout rl_yuanxiao;

    @BindView(R.id.iv_right1)
    ImageView iv_right1;
    @BindView(R.id.iv_next1)
    ImageView iv_next1;
    @BindView(R.id.iv_right2)
    ImageView iv_right2;
    @BindView(R.id.iv_next2)
    ImageView iv_next2;

    @BindView(R.id.tv_diqu)
    TextView tv_diqu;
    @BindView(R.id.tv_yuanxiao)
    TextView tv_yuanxiao;


    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.rl_list)
    RelativeLayout rlList;

    private ArrayList<String> arealist;
    private ArrayList<String> sortlist;


    private String area = "";
    private String sort = "";
    private MoreSchoolPresent moreSchoolPresent;
    private String token;
    private ConnectionChangeReceiver myReceiver;
    private boolean flag = true;
    private boolean flag2 = true;
    private String page = "1";
    private MoreSchoolRecycle adpter;

    private  XRecyclerView mschoolXlist;


    @Override
    public int getId() {
        return R.layout.activity_more_school;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void InIt() {

        initList();
        this.loadingLayout = findViewById(R.id.lodiing);
        registerReceiver();

        token = (String) SPUtils.get(MyApp.context, "token", "");
        moreSchoolPresent = new MoreSchoolPresent(this);

        //地区适配器

        Spinner_Adapter arealist_adapter = new Spinner_Adapter(arealist, MoreSchoolActivity.this);
        diqu_list.setAdapter(arealist_adapter);
        //改变值

        diqu_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                rlList.removeAllViews();
                mschoolXlist=new XRecyclerView(MoreSchoolActivity.this);
                mschoolXlist.setPullRefreshEnabled(false);
                mschoolXlist.setLayoutManager(new LinearLayoutManager(MoreSchoolActivity.this));
                rlList.addView(mschoolXlist);
                mschoolXlist.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {

                    }

                    @Override
                    public void onLoadMore() {

                        int i = Integer.parseInt(page);
                        i++;
                        page = i + "";
                        moreSchoolPresent.checkschool(area, sort, page, "20");

                    }
                });
                adpter = null;
                mschoolXlist.setLoadingMoreEnabled(true);
                pb.setVisibility(View.VISIBLE);
                diqu_list.setVisibility(View.GONE);
                iv_right1.setVisibility(View.VISIBLE);
                iv_next1.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                flag = true;
                String s = arealist.get(i).toString();
                tv_diqu.setText(s);

                if (arealist.get(i).toString().equals("全国")) {
                    area = "";
                    page = "1";

                    moreSchoolPresent.checkschool(area, sort, page, "20");
                } else {
                    area = s;
                    page = "1";
                    moreSchoolPresent.checkschool(area, sort, page, "20");
                }

            }
        });
        //院校适配器
        Spinner_Adapter yuanxiao_Adapter = new Spinner_Adapter(sortlist, MoreSchoolActivity.this);
        yuanxiao_list.setAdapter(yuanxiao_Adapter);

        yuanxiao_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                rlList.removeAllViews();
                mschoolXlist=new XRecyclerView(MoreSchoolActivity.this);
                mschoolXlist.setPullRefreshEnabled(false);

                mschoolXlist.setLayoutManager(new LinearLayoutManager(MoreSchoolActivity.this));
                rlList.addView(mschoolXlist);
                mschoolXlist.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {

                    }

                    @Override
                    public void onLoadMore() {

                        int i = Integer.parseInt(page);
                        i++;
                        page = i + "";
                        moreSchoolPresent.checkschool(area, sort, page, "20");

                    }
                });

                pb.setVisibility(View.VISIBLE);
                view3.setVisibility(View.GONE);
                mschoolXlist.setLoadingMoreEnabled(true);
                adpter = null;
                yuanxiao_list.setVisibility(View.GONE);
                iv_right2.setVisibility(View.VISIBLE);
                iv_next2.setVisibility(View.GONE);
                flag2 = true;
                String s = sortlist.get(i).toString();
                tv_yuanxiao.setText(s);
                if (sortlist.get(i).toString().equals("不限")) {
                    sort = "";
                    page = "1";
                    moreSchoolPresent.checkschool(area, sort, page, "20");
                } else {
                    sort = s;
                    page = "1";
                    moreSchoolPresent.checkschool(area, sort, page, "20");
                }


            }
        });

        rl_diqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view3.setVisibility(View.GONE);
                flag2 = true;
                yuanxiao_list.setVisibility(View.GONE);
                iv_right2.setVisibility(View.VISIBLE);
                iv_next2.setVisibility(View.GONE);
                if (flag) {
                    iv_right1.setVisibility(View.GONE);
                    iv_next1.setVisibility(View.VISIBLE);
                    diqu_list.setVisibility(View.VISIBLE);
                    view4.setVisibility(View.VISIBLE);
                    flag = false;
                } else {
                    iv_right1.setVisibility(View.VISIBLE);
                    iv_next1.setVisibility(View.GONE);
                    diqu_list.setVisibility(View.GONE);
                    view4.setVisibility(View.GONE);
                    flag = true;
                }

            }
        });
        rl_yuanxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                view4.setVisibility(View.GONE);
                diqu_list.setVisibility(View.GONE);
                iv_right1.setVisibility(View.VISIBLE);
                iv_next1.setVisibility(View.GONE);
                if (flag2) {
                    iv_right2.setVisibility(View.GONE);
                    iv_next2.setVisibility(View.VISIBLE);
                    yuanxiao_list.setVisibility(View.VISIBLE);
                    view3.setVisibility(View.VISIBLE);
                    flag2 = false;
                } else {
                    iv_right2.setVisibility(View.VISIBLE);
                    iv_next2.setVisibility(View.GONE);
                    yuanxiao_list.setVisibility(View.GONE);
                    view3.setVisibility(View.GONE);
                    flag2 = true;
                }
            }
        });
    }

    private void initList() {
        arealist = new ArrayList<>();
        arealist.add("全国");
        arealist.add("北京市");
        arealist.add("天津市");
        arealist.add("河北省");
        arealist.add("山西省");
        arealist.add("内蒙古");
        arealist.add("辽宁省");
        arealist.add("吉林省");
        arealist.add("黑龙江");
        arealist.add("上海市");
        arealist.add("江苏省");
        arealist.add("浙江省");
        arealist.add("安徽省");
        arealist.add("福建省");
        arealist.add("江西省");
        arealist.add("山东省");
        arealist.add("河南省");
        arealist.add("湖北省");
        arealist.add("湖南省");
        arealist.add("广东省");
        arealist.add("广西省");
        arealist.add("海南省");
        arealist.add("重庆市");
        arealist.add("四川省");
        arealist.add("贵州省");
        arealist.add("云南省");
        arealist.add("西藏");
        arealist.add("陕西省");
        arealist.add("甘肃省");
        arealist.add("青海省");
        arealist.add("宁夏");
        arealist.add("新疆");
        arealist.add("台湾");
        arealist.add("澳门");
        sortlist = new ArrayList<>();
        sortlist.add("不限");
        sortlist.add("综合类");
        sortlist.add("理工类");
        sortlist.add("师范类");
        sortlist.add("农林类");
        sortlist.add("医药类");
        sortlist.add("民族类");
        sortlist.add("财经类");
        sortlist.add("政法类");
        sortlist.add("语言类");
        sortlist.add("军事类");
        sortlist.add("艺术类");
        sortlist.add("林业类");
        sortlist.add("体育类");

    }

    @OnClick({R.id.mschool_iv_back, R.id.mschool_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mschool_iv_back:
                finish();
                break;
            case R.id.mschool_search:

                intent(this, SearchParticularsActivity.class);

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        arealist = null;
        sortlist = null;
        moreSchoolPresent.onDestory();
        unregisterReceiver();
    }

    @Override
    public void CheckSuccess(final List<CheckSchoolBean> list) {

        pb.setVisibility(View.GONE);
        if (list != null && list.size() > 0) {
            img_none.setVisibility(View.GONE);
            mschoolXlist.setVisibility(View.VISIBLE);
            loadingLayout.setStatus(LoadingLayout.Success);
            if (adpter == null) {
                adpter = new MoreSchoolRecycle(this, list);
                mschoolXlist.setAdapter(adpter);
            } else {
                adpter.loading(list);
                mschoolXlist.loadMoreComplete();
            }

        }else {
            mschoolXlist.loadMoreComplete();
            if(adpter==null){
                img_none.setVisibility(View.VISIBLE);
                mschoolXlist.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void CheckFail(String msg) {
        moreSchoolPresent.checkschool(area, sort, page, "20");

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void registerReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //设置网络状态提示布局的状态
//无网的时候，无网提示的展示View展示出来
//重新联接上网络时，自动加载数据
//这个是onresume中实现了数据的刷新，即是，网络连接后，重新拉取数据
        myReceiver = new ConnectionChangeReceiver() {
            @Override
            public void changeNetStatus(boolean flag) {
                //设置网络状态提示布局的状态
                if (flag) {
                    loadingLayout.setStatus(LoadingLayout.No_Network);
                    mschoolXlist.setVisibility(View.GONE);
                } else {
                    //有网
                    mschoolXlist=new XRecyclerView(MoreSchoolActivity.this);
                    rlList.removeAllViews();
                    mschoolXlist.setPullRefreshEnabled(false);
                    mschoolXlist.setLayoutManager(new LinearLayoutManager(MoreSchoolActivity.this));
                    rlList.addView(mschoolXlist);
                    mschoolXlist.setLoadingListener(new XRecyclerView.LoadingListener() {
                        @Override
                        public void onRefresh() {

                        }

                        @Override
                        public void onLoadMore() {
                            int i = Integer.parseInt(page);
                            i++;
                            page = i + "";
                            moreSchoolPresent.checkschool(area, sort, page, "20");

                        }
                    });
                    moreSchoolPresent.checkschool(area, sort, page, "20");


                }
            }
        };
        this.registerReceiver(myReceiver, filter);
    }

    public void unregisterReceiver() {
        if (myReceiver != null) {
            this.unregisterReceiver(myReceiver);
        }
    }


}
