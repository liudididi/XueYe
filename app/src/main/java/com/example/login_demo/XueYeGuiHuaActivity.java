package com.example.login_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import adapter.XueYeGuiHua_adapter;
import base.BaseActivity;
import base.BaseApi;
import base.BaseBean;
import bean.CXEFCBean;
import bean.jobStarBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import presenter.CXEFCPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import untils.ListViewForScrollView;
import untils.MyQusetUtils;
import untils.NetInterceptor;
import untils.QuestInterface;
import untils.SPUtils;
import view.CXEFCView;

public class XueYeGuiHuaActivity extends BaseActivity {

    @BindView(R.id.guihua_iv_back)
    ImageView guihuaIvBack;
    @BindView(R.id.rl_xqcebg)
    RelativeLayout rlXqcebg;
    @BindView(R.id.rl_xgcebg)
    RelativeLayout rlXgcebg;
    @BindView(R.id.xygh_list)
    ListViewForScrollView xyghList;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.guihua_ivyiwen)
    ImageView guihuaIvyiwen;
    @BindView(R.id.rl_wenti)
    RelativeLayout rlWenti;
    @BindView(R.id.re_guihuadi)
    RelativeLayout reGuihuadi;
    @BindView(R.id.guihua_title)
    RelativeLayout guihuaTitle;
    @BindView(R.id.xlcs_bt)
    Button xlcsBt;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.zw)
    View zw;
    private boolean yiwen = false;
    private CXEFCPresenter cxefcPresenter;
    private String token;
    private String testCode;
    private String xb;
    private String km;
    @Override
    public int getId() {
        return R.layout.activity_xue_ye_gui_hua;
    }

    @Override
    public void InIt() {
        xyghList.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);
        zw.setVisibility(View.VISIBLE);
        token = (String) SPUtils.get(MyApp.context, "token", "");
        xb="1";
        km="0";
        //专业
        initzhuanye();

    }

    private void initzhuanye() {
            //TODO 待完善
            cxefcPresenter = new CXEFCPresenter(new CXEFCView() {
                @Override
                public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
                    String job = cxefcBeanBaseBean.data.getJob();
                    testCode = cxefcBeanBaseBean.data.getTestCode();
                    String[] split = testCode.split(",");
                    String s = split[0];
                    String s1 = split[1];
                    String[] split1 = s.split(":");
                    //MBTI
                    String s2 = split1[0];
                     String[] split2 = s1.split(":");
                    // 霍兰德
                    String s3 = split2[0];
                     //男女
                    String gender = cxefcBeanBaseBean.data.getGender();
                    if(gender.equals("男"))
                    {
                        xb="1";
                    }
                    if(gender.equals("女"))
                    {
                        xb="0";
                    }
                     //专科本科
                    String collegetype = cxefcBeanBaseBean.data.getCollegetype();
                    if(collegetype.equals("本科"))
                    {
                        km="0";
                    }
                    if(collegetype.equals("专科"))
                    {
                        km="1";
                    }

                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(NetInterceptor.REWRITE_RESPONSE_INTERCEPTOR_LOG)
                            .addInterceptor(NetInterceptor.REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
                            .addNetworkInterceptor(NetInterceptor.REWRITE_RESPONSE_INTERCEPTOR)
                            .addInterceptor(NetInterceptor.REWRITE_RESPONSE_MYINTERCEPTOR)
                            .connectTimeout(MyQusetUtils.TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(MyQusetUtils.TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(MyQusetUtils.TIMEOUT, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(false)
                            .build();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BaseApi.Api)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    QuestInterface questInterface = retrofit.create(QuestInterface.class);
                     Call<BaseBean<List<jobStarBean>>> baseBeanCall = questInterface.jobsStarMajorMobil(s3, s2, xb,km ,job);
                    baseBeanCall.enqueue(new Callback<BaseBean<List<jobStarBean>>>() {
                        @Override
                        public void onResponse(Call<BaseBean<List<jobStarBean>>> call, Response<BaseBean<List<jobStarBean>>> response) {
                            xyghList.setVisibility(View.VISIBLE);
                            pb.setVisibility(View.GONE);
                            zw.setVisibility(View.GONE);
                            final BaseBean<List<jobStarBean>> body = response.body();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(body.code == 0) {
                                        XueYeGuiHua_adapter xueYeGuiHua_adapter = new XueYeGuiHua_adapter(body.data,XueYeGuiHuaActivity.this );
                                        xyghList.setAdapter(xueYeGuiHua_adapter);
                                        sv.smoothScrollTo(0, 0);

                                    }else {
                                        Toast(body.msg);

                                    }
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<BaseBean<List<jobStarBean>>> call, Throwable t) {

                        }
                    });
                }
                @Override
                public void GetEFCResultfail(Throwable t) {
                }
            });
            cxefcPresenter.CXEFCPresenter(token);

    }
    @OnClick({R.id.guihua_iv_back, R.id.rl_xqcebg, R.id.rl_xgcebg, R.id.guihua_ivyiwen,R.id.xlcs_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guihua_iv_back:
                intent(this,EFCJieSuoActivity.class);
                finish();
                break;
            case R.id.xlcs_bt:
                intent(this,Volunteer_ScreenActivity.class);
                break;
            case R.id.rl_xqcebg:
                Intent intent1=new Intent(XueYeGuiHuaActivity.this, XQcsActivity.class);
                intent1.putExtra("code",testCode);
                startActivity(intent1);

                break;
            case R.id.rl_xgcebg:
                Intent intent=new Intent(XueYeGuiHuaActivity.this, XGcsActivity.class);
                intent.putExtra("code",testCode);
                startActivity(intent);
                break;
            case R.id.guihua_ivyiwen:

                if (yiwen) {
                    rlWenti.setVisibility(View.GONE);
                    sv.setVisibility(View.VISIBLE);
                    guihuaIvyiwen.setImageResource(R.drawable.cjwt);
                    yiwen = false;
                    reGuihuadi.setVisibility(View.VISIBLE);
                } else {
                    rlWenti.setVisibility(View.VISIBLE);
                    sv.setVisibility(View.GONE);
                    guihuaIvyiwen.setImageResource(R.drawable.cha);
                    yiwen = true;
                    reGuihuadi.setVisibility(View.GONE);
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        cxefcPresenter.onDestory();
    }
}
