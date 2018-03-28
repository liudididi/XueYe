package com.example.login_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import base.BaseActivity;
import base.BaseBean;
import bean.CXEFCBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import presenter.CXEFCPresenter;
import untils.MyQusetUtils;
import untils.SPUtils;
import view.CXEFCView;

public class ProfessionStarActivity extends BaseActivity  implements CXEFCView{

    @BindView(R.id.img_nan)
    ImageView imgNan;
    @BindView(R.id.ll_nan)
    LinearLayout llNan;
    @BindView(R.id.img_nv)
    ImageView imgNv;
    @BindView(R.id.ll_nv)
    LinearLayout llNv;
    public String classify;
    public static String type="1";
    public static String gender="1";
    public static String wenli="1";
    private String km;
    private String leixing;
    private String xingbie;
    @BindView(R.id.pro_iv_back)
    ImageView proIvBack;
    @BindView(R.id.img_wen)
    ImageView imgWen;
    @BindView(R.id.ll_wen)
    LinearLayout llWen;
    @BindView(R.id.img_li)
    ImageView imgLi;
    @BindView(R.id.ll_li)
    LinearLayout llLi;
    @BindView(R.id.img_ben)
    ImageView imgBen;
    @BindView(R.id.ll_ben)
    LinearLayout llBen;
    @BindView(R.id.img_zhuan)
    ImageView imgZhuan;
    @BindView(R.id.ll_zhuan)
    LinearLayout llZhuan;
    @BindView(R.id.pro_tvyes)
    TextView proTvyes;
    private String token;
    private String data;
    private CXEFCPresenter cxefcPresenter;

    @Override
    public int getId() {
        return R.layout.activity_profession_star;
    }

    @Override
    public void InIt() {
        leixing="本科";
        km="文科";
        xingbie="男";
        data = getIntent().getStringExtra("data");
        cxefcPresenter = new CXEFCPresenter(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        token = (String) SPUtils.get(MyApp.context, "token", "");
        int i2 = Integer.parseInt(data);
        if(i2>=2){
            llZhuan.setEnabled(false);
            llBen.setEnabled(false);
            llNan.setEnabled(false);
            llNv.setEnabled(false);
            llWen.setEnabled(false);
            llLi.setEnabled(false);
            cxefcPresenter.CXEFCPresenter(token);

        }else {
            llZhuan.setEnabled(true);
            llBen.setEnabled(true);
            llNan.setEnabled(true);
            llNv.setEnabled(true);
            llWen.setEnabled(true);
            llLi.setEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cxefcPresenter.onDestory();
    }

    @OnClick({R.id.pro_iv_back, R.id.ll_wen, R.id.ll_li, R.id.ll_ben, R.id.ll_zhuan, R.id.pro_tvyes, R.id.ll_nan, R.id.ll_nv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pro_iv_back:
                finish();
                break;
            case R.id.ll_wen:
                imgWen.setImageResource(R.drawable.hong);
                imgLi.setImageResource(R.drawable.bai);

                km="文科";
                wenli="wen";
                break;
            case R.id.ll_li:
                imgLi.setImageResource(R.drawable.hong);
                imgWen.setImageResource(R.drawable.bai);

                km="理科";
                wenli="li";
                break;
            case R.id.ll_ben:
                imgBen.setImageResource(R.drawable.hong);
                imgZhuan.setImageResource(R.drawable.bai);
                type = "0";
                leixing="本科";
                break;
            case R.id.ll_zhuan:
                imgZhuan.setImageResource(R.drawable.hong);
                imgBen.setImageResource(R.drawable.bai);
                type = "1";
                leixing="专科";
                break;
            case R.id.ll_nan:
                imgNan.setImageResource(R.drawable.hong);
                imgNv.setImageResource(R.drawable.bai);
                gender = "1";
                xingbie="男";
                break;
            case R.id.ll_nv:
                imgNan.setImageResource(R.drawable.bai);
                imgNv.setImageResource(R.drawable.hong);
                gender = "0";
                xingbie="女";
                break;
            case R.id.pro_tvyes:
                int i = Integer.parseInt(data);
                if(i>=2){
                    Intent intent = new Intent(ProfessionStarActivity.this, startfenleiActivity.class);
                    intent.putExtra("data",data);
                    intent.putExtra("classify", wenli);
                    intent.putExtra("type", type);
                    intent.putExtra("gender",gender);
                    startActivity(intent);
                    finish();
                }else {

                    //保存职业筛选条件
                    MyQusetUtils.getInstance().getQuestInterface()
                            .updateStuInfo(km,leixing,xingbie,token)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSubscriber<BaseBean>() {
                                @Override
                                public void onNext(BaseBean baseBean) {
                                    Intent intent = new Intent(ProfessionStarActivity.this, startfenleiActivity.class);
                                    intent.putExtra("data",data);
                                    intent.putExtra("classify", wenli);
                                    intent.putExtra("type", type);
                                    intent.putExtra("gender",gender);
                                    startActivity(intent);
                                    finish();
                                }
                                @Override
                                public void onError(Throwable t) {
                                }
                                @Override
                                public void onComplete() {

                                }
                            });
                }

                break;
        }
    }


    @Override
    public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {

if(cxefcBeanBaseBean.data!=null){
    String agender = cxefcBeanBaseBean.data.getGender();
    if(agender.equals("男"))
    {
        xingbie="男";
        imgNan.setImageResource(R.drawable.hong);
        imgNv.setImageResource(R.drawable.bai);
        gender="1";
    }
    if(agender.equals("女"))
    {
        imgNan.setImageResource(R.drawable.bai);
        imgNv.setImageResource(R.drawable.hong);
        xingbie="女";
        gender="0";
    }


    String stutype = cxefcBeanBaseBean.data.getStutype();
    if(stutype.equals("文科"))
    {
        imgWen.setImageResource(R.drawable.hong);
        imgLi.setImageResource(R.drawable.bai);
        wenli = "wen";
    }
    if(stutype.equals("理科"))
    {
        imgLi.setImageResource(R.drawable.hong);
        imgWen.setImageResource(R.drawable.bai);
        wenli = "li";
    }

    //专科本科
    String collegetype = cxefcBeanBaseBean.data.getCollegetype();

    if(collegetype.equals("本科"))
    {
        imgBen.setImageResource(R.drawable.hong);
        imgZhuan.setImageResource(R.drawable.bai);
        type = "0";
    }
    if(collegetype.equals("专科"))
    {
        imgZhuan.setImageResource(R.drawable.hong);
        imgBen.setImageResource(R.drawable.bai);
        type = "1";
    }

}

    }

    @Override
    public void GetEFCResultfail(Throwable t) {
        cxefcPresenter.CXEFCPresenter(token);
    }
}
