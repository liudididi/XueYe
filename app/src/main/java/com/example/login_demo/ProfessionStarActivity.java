package com.example.login_demo;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.Gv_addressAdapter;
import base.BaseActivity;
import base.BaseBean;
import bean.CXEFCBean;
import bean.CityBean;
import bean.MinlineBean;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import presenter.CXEFCPresenter;
import untils.MyQusetUtils;
import untils.NetUtil;
import untils.SPUtils;
import view.CXEFCView;

public class ProfessionStarActivity extends BaseActivity implements CXEFCView {

    @BindView(R.id.img_nan)
    ImageView imgNan;
    @BindView(R.id.ll_nan)
    LinearLayout llNan;
    @BindView(R.id.img_nv)
    ImageView imgNv;
    @BindView(R.id.ll_nv)
    LinearLayout llNv;
    public String classify;
    public static String type = "0";
    public static String gender = "1";
    public static String wenli = "wen";
    @BindView(R.id.gv_address)
    GridView gvAddress;
    @BindView(R.id.gv_addresstwo)
    GridView gvAddressTwo;
    @BindView(R.id.pro_edfenshu)
    EditText proEdfenshu;
    @BindView(R.id.pro_edname)
    EditText proEdname;
    @BindView(R.id.pro_tvaddress)
    TextView proTvaddress;
    @BindView(R.id.rl_wai)
    RelativeLayout rlWai;
    @BindView(R.id.rl_li)
    RelativeLayout rlLi;
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
    private List<String> arealist;
    private  String area;
    private String city=null;

    @Override
    public int getId() {
        return R.layout.activity_profession_star;
    }

    @Override
    public void InIt() {
        leixing="本科";
        km = "文科";
        xingbie = "男";
        data = getIntent().getStringExtra("data");
         cxefcPresenter = new CXEFCPresenter(this);
        arealist = new ArrayList<>();
        initdata();
    }
    private void initdata() {
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
        Gv_addressAdapter gv_addressAdapter = new Gv_addressAdapter(arealist, this);
        gv_addressAdapter.setAddressBack(new Gv_addressAdapter.AddressBack() {
            @Override
            public void setaddress(String s) {
                area=s;
               if(s.equals("北京市")||s.equals("重庆市")||s.equals("天津市")||s.equals("香港")||s.equals("台湾")||s.equals("澳门")){
                    proTvaddress.setText(area);
                    city="";
                    rlLi.setVisibility(View.INVISIBLE);
                    rlWai.setVisibility(View.VISIBLE);
                    return;
                }
                if(NetUtil.isNetworkAvailable(ProfessionStarActivity.this)==false){
                    Toast.makeText(ProfessionStarActivity.this, "检查您的网络", Toast.LENGTH_SHORT).show();
                    return;
                }
                MyQusetUtils.getInstance().getQuestInterface().getcitybyname(s)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSubscriber<BaseBean<List<CityBean>>>() {
                            @Override
                            public void onNext(BaseBean<List<CityBean>> listBaseBean) {
                                if(listBaseBean.code==0){
                                    List<CityBean> data = listBaseBean.data;
                                    if(data.size()>0&&data!=null){
                                        gvAddress.setVisibility(View.INVISIBLE);
                                        gvAddressTwo.setVisibility(View.VISIBLE);
                                        List<String> addresstwo=new ArrayList<>();
                                        for (int i = 0; i < data.size(); i++) {
                                            addresstwo.add(data.get(i).getCity());
                                        }
                                        Gv_addressAdapter gv_addressAdapter = new Gv_addressAdapter(addresstwo, ProfessionStarActivity.this);
                                        gv_addressAdapter.setAddressBack(new Gv_addressAdapter.AddressBack() {
                                            @Override
                                            public void setaddress(String s) {
                                                city=s;
                                                proTvaddress.setText(area+city);
                                                rlLi.setVisibility(View.INVISIBLE);
                                                rlWai.setVisibility(View.VISIBLE);
                                            }
                                        });
                                        gvAddressTwo.setAdapter(gv_addressAdapter);
                                    }

                                }


                            }

                            @Override
                            public void onError(Throwable t) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });



            }
        });
        gvAddress.setAdapter(gv_addressAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        token = (String) SPUtils.get(MyApp.context, "token", "");
        int i2 = Integer.parseInt(data);
        if (i2 >= 2) {
            llZhuan.setEnabled(false);
            llBen.setEnabled(false);
            llNan.setEnabled(false);
            llNv.setEnabled(false);
            llWen.setEnabled(false);
            llLi.setEnabled(false);
            cxefcPresenter.CXEFCPresenter(token);
            proEdname.setEnabled(false);
            proTvaddress.setEnabled(false);
            proEdfenshu.setEnabled(false);
        } else {
            llZhuan.setEnabled(true);
            llBen.setEnabled(true);
            llNan.setEnabled(true);
            llNv.setEnabled(true);
            llWen.setEnabled(true);
            llLi.setEnabled(true);
          String s = (String) SPUtils.get(MyApp.context, "kemuefc", "默认");
          if(s.equals("本科")){
              imgBen.setImageResource(R.drawable.hong);
              imgZhuan.setImageResource(R.drawable.bai);
              type = "0";
              leixing = "本科";
              llZhuan.setEnabled(false);
              llBen.setEnabled(false);
          }else if(s.equals("专科")){
              imgZhuan.setImageResource(R.drawable.hong);
              imgBen.setImageResource(R.drawable.bai);
              type = "1";
              llZhuan.setEnabled(false);
              llBen.setEnabled(false);
              leixing = "专科";
          }
            proEdname.setEnabled(true);
            proTvaddress.setEnabled(true);
            proEdfenshu.setEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cxefcPresenter.onDestory();
    }

    @OnClick({R.id.pro_iv_back,R.id.pro_tvaddress, R.id.ll_wen, R.id.ll_li, R.id.ll_ben, R.id.ll_zhuan, R.id.pro_tvyes, R.id.ll_nan, R.id.ll_nv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pro_iv_back:
                finish();
                break;
            case R.id.pro_tvaddress:
                rlLi.setVisibility(View.VISIBLE);
                rlWai.setVisibility(View.INVISIBLE);
                gvAddress.setVisibility(View.VISIBLE);
                gvAddressTwo.setVisibility(View.INVISIBLE);
                break;
            case R.id.ll_wen:
                imgWen.setImageResource(R.drawable.hong);
                imgLi.setImageResource(R.drawable.bai);
                km = "文科";
                wenli = "wen";
                break;
            case R.id.ll_li:
                imgLi.setImageResource(R.drawable.hong);
                imgWen.setImageResource(R.drawable.bai);
                km = "理科";
                wenli = "li";
                break;
            case R.id.ll_ben:
                imgBen.setImageResource(R.drawable.hong);
                imgZhuan.setImageResource(R.drawable.bai);
                type = "0";
                leixing = "本科";
                break;
            case R.id.ll_zhuan:
                imgZhuan.setImageResource(R.drawable.hong);
                imgBen.setImageResource(R.drawable.bai);
                type = "1";
                leixing = "专科";
                break;
            case R.id.ll_nan:
                imgNan.setImageResource(R.drawable.hong);
                imgNv.setImageResource(R.drawable.bai);
                gender = "1";
                xingbie = "男";
                break;
            case R.id.ll_nv:
                imgNan.setImageResource(R.drawable.bai);
                imgNv.setImageResource(R.drawable.hong);
                gender = "0";
                xingbie = "女";
                break;
            case R.id.pro_tvyes:
                final int i = Integer.parseInt(data);
                if (i >= 2) {
                    Intent intent = new Intent(ProfessionStarActivity.this, startfenleiActivity.class);
                    intent.putExtra("data", data);
                    intent.putExtra("classify", wenli);
                    intent.putExtra("type", type);
                    intent.putExtra("gender", gender);
                    startActivity(intent);
                    finish();
                } else {
                    final String fenshu = proEdfenshu.getText().toString();
                    if(TextUtils.isEmpty(fenshu)){
                        Toast("分数为空");
                        return;
                    }
                    if(Integer.parseInt(fenshu)>750||Integer.parseInt(fenshu)<0){
                        Toast("分数不真实，请实际填写");
                        return;
                    }
                    final String name = proEdname.getText().toString();
                    if(TextUtils.isEmpty(name)){
                        Toast("姓名为空");
                        return;
                    }
                    final String address = proTvaddress.getText().toString();
                    if(address.equals("点击选择城市")){
                        Toast("请选择地址");
                        return;
                    }

                    MyQusetUtils.getInstance().getQuestInterface().getMinline(area,km)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .retry(2).subscribeWith(new DisposableSubscriber<BaseBean<List<MinlineBean>>>() {
                        @Override
                        public void onNext(BaseBean<List<MinlineBean>> listBaseBean) {

                            if(listBaseBean.code==0){
                                if(listBaseBean.data!=null&&listBaseBean.data.size()>0){
                                    MinlineBean minlineBean = listBaseBean.data.get(0);
                                   if(Integer.parseInt(minlineBean.getScore())<Integer.parseInt(fenshu)){
                                       tijiao(fenshu, name, area);
                                   }else {

                                       if(type.equals("0")){

                                           View viewe = LayoutInflater.from(ProfessionStarActivity.this).inflate(R.layout.dilog_zyxk, null);
                                           final AlertDialog dialog = new AlertDialog.Builder(ProfessionStarActivity.this)
                                                   .setView(viewe).show();
                                           TextView zyxk_zhuan=    viewe.findViewById(R.id.zyxk_zhuan);
                                           zyxk_zhuan.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   leixing = "专科";
                                                   tijiao(fenshu, name, address);
                                                   dialog.dismiss();
                                               }
                                           });
                                           TextView zyxk_ben=    viewe.findViewById(R.id.zyxk_ben);
                                           zyxk_ben.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   tijiao(fenshu, name, address);
                                                   dialog.dismiss();
                                               }
                                           });
                                           TextView zyxk_tishi=    viewe.findViewById(R.id.zyxk_tishi);
                                           zyxk_tishi.setText(minlineBean.getYear()+"年本科省控线是"+minlineBean.getScore()+"分，经过分析建议推荐你选择专科院校");

                                       }else {
                                           tijiao(fenshu, name, area);
                                       }
                                   }
                                }else {

                                    tijiao(fenshu, name, address);
                                }
                            }
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

    private void tijiao(final String fenshu, String name, String address) {

        //保存职业筛选条件
        MyQusetUtils.getInstance().getQuestInterface()
                .updateStuInfo(fenshu,name,km, leixing, xingbie,address, city,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2)
                .subscribeWith(new DisposableSubscriber<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {

                        if(baseBean.code==0){
                            SPUtils.put(MyApp.context,"tbarea",area);
                            SPUtils.put(MyApp.context,"tbsubtypeefc",km);
                            SPUtils.put(MyApp.context,"tbmaxfenefc",fenshu);
                            SPUtils.put(MyApp.context,"kemuefc",leixing);
                            SPUtils.put(MyApp.context,"school",area+city);
                            Intent intent = new Intent(ProfessionStarActivity.this, startfenleiActivity.class);
                            intent.putExtra("data", data);
                            intent.putExtra("classify", wenli);
                            intent.putExtra("type", type);
                            intent.putExtra("gender", gender);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
        if (cxefcBeanBaseBean.data != null) {
            proEdname.setText(cxefcBeanBaseBean.data.getName());
           if(cxefcBeanBaseBean.data.getCity()!=null){
               proTvaddress.setText(cxefcBeanBaseBean.data.getSourceArea()+cxefcBeanBaseBean.data.getCity());
           }else {
               proTvaddress.setText(cxefcBeanBaseBean.data.getSourceArea());
           }
            proEdfenshu.setText(cxefcBeanBaseBean.data.getCeeScore());
            SPUtils.put(MyApp.context,"kemuefc",cxefcBeanBaseBean.data.getCollegetype());
            SPUtils.put(MyApp.context,"tbmaxfenefc",cxefcBeanBaseBean.data.getCeeScore());
            String agender = cxefcBeanBaseBean.data.getGender();
            if (agender.equals("男")) {
                xingbie = "男";
                imgNan.setImageResource(R.drawable.hong);
                imgNv.setImageResource(R.drawable.bai);
                gender = "1";
            }
            if (agender.equals("女")) {
                imgNan.setImageResource(R.drawable.bai);
                imgNv.setImageResource(R.drawable.hong);
                xingbie = "女";
                gender = "0";
            }
            String stutype = cxefcBeanBaseBean.data.getStutype();
            SPUtils.put(MyApp.context,"tbsubtypeefc",stutype);
            if (stutype.equals("文科")) {
                imgWen.setImageResource(R.drawable.hong);
                imgLi.setImageResource(R.drawable.bai);
                wenli = "wen";
            }
            if (stutype.equals("理科")) {
                imgLi.setImageResource(R.drawable.hong);
                imgWen.setImageResource(R.drawable.bai);
                wenli = "li";
            }

            //专科本科
            String collegetype = cxefcBeanBaseBean.data.getCollegetype();

            if (collegetype.equals("本科")) {
                imgBen.setImageResource(R.drawable.hong);
                imgZhuan.setImageResource(R.drawable.bai);
                type = "0";
            }
            if (collegetype.equals("专科")) {
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
