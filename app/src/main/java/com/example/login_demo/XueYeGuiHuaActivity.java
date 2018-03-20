package com.example.login_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import bean.jobStarBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import untils.ListViewForScrollView;
import untils.MyQusetUtils;
import untils.NetInterceptor;
import untils.QuestInterface;

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
    private boolean yiwen = false;

    @Override
    public int getId() {
        return R.layout.activity_xue_ye_gui_hua;
    }

    @Override
    public void InIt() {
        //专业
        initzhuanye();

    }

    private void initzhuanye() {
        Map<String, String> map = new HashMap<>();
        if(startfenleiActivity.fenlieanswerlist!=null&&startfenleiActivity.fenlieanswerlist.size()>0){
            for (int i = 0; i < startfenleiActivity.fenlieanswerlist.size(); i++) {
                map.put(startfenleiActivity.fenlieanswerlist.get(i), "");
            }
        }else {
            map.put("机电工程师", "");
            map.put("建筑技术家", "");
        }
        Gson gson = new Gson();
        String route = gson.toJson(map);
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
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), route);
        Call<BaseBean<List<jobStarBean>>> baseBeanCall = questInterface.jobsStarMajorMobil("CAS", "INFP", "1","1" ,body);
        baseBeanCall.enqueue(new Callback<BaseBean<List<jobStarBean>>>() {
            @Override
            public void onResponse(Call<BaseBean<List<jobStarBean>>> call, Response<BaseBean<List<jobStarBean>>> response) {
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

    @OnClick({R.id.guihua_iv_back, R.id.rl_xqcebg, R.id.rl_xgcebg, R.id.guihua_ivyiwen,R.id.xlcs_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guihua_iv_back:
                finish();
                break;
            case R.id.xlcs_bt:
                intent(this,Volunteer_ScreenActivity.class);

                break;
            case R.id.rl_xqcebg:
                intent(this, XQcsActivity.class);
                break;
            case R.id.rl_xgcebg:
                intent(this, XGcsActivity.class);
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



}
