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
import bean.EFCBean;
import bean.jobStarBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
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

    private String token;
    private String testCode;

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

        //专业
        initzhuanye();

    }

    private void initzhuanye() {

        MyQusetUtils.getInstance().getQuestInterface().getEFCData(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<EFCBean>>() {
                    @Override
                    public void onNext(BaseBean<EFCBean> efcBeanBaseBean) {
                        testCode = efcBeanBaseBean.data.getTest_code();
                        if(efcBeanBaseBean.data.getMajorGai()!=null&&efcBeanBaseBean.data.getMajorGai().size()>0){
                            List<String> majorGai = efcBeanBaseBean.data.getMajorGai();
                            zw.setVisibility(View.GONE);
                            pb.setVisibility(View.GONE);
                            xyghList.setVisibility(View.VISIBLE);
                            XueYeGuiHua_adapter xueYeGuiHua_adapter = new XueYeGuiHua_adapter(majorGai,XueYeGuiHuaActivity.this );
                            xyghList.setAdapter(xueYeGuiHua_adapter);
                            sv.smoothScrollTo(0, 0);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });







            //TODO 待完善
//            cxefcPresenter = new CXEFCPresenter(new CXEFCView() {
//                @Override
//                public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
//
//                    testCode = cxefcBeanBaseBean.data.getTestCode();
//                    if(cxefcBeanBaseBean.data.getMajorGai()!=null){
//                        String[] split = cxefcBeanBaseBean.data.getMajorGai().split(",");
//                        List<String> Stringlist=new ArrayList<>();
//                        for (int i = 0; i < split.length; i++) {
//                            Stringlist.add(split[i]);
//                        }
//                        zw.setVisibility(View.GONE);
//                        pb.setVisibility(View.GONE);
//                        xyghList.setVisibility(View.VISIBLE);
//                        XueYeGuiHua_adapter xueYeGuiHua_adapter = new XueYeGuiHua_adapter(Stringlist,XueYeGuiHuaActivity.this );
//                        xyghList.setAdapter(xueYeGuiHua_adapter);
//                        sv.smoothScrollTo(0, 0);
//                    }
//
//                }
//                @Override
//                public void GetEFCResultfail(Throwable t) {
//                }
//            });
//            cxefcPresenter.CXEFCPresenter(token);

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

    }
}
