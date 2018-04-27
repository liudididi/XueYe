package com.example.login_demo;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import adapter.Gv_addressAdapter;
import adapter.Gv_addresssyAdapter;
import base.BaseActivity;
import base.BaseBean;
import bean.MyUserBean;
import bean.UserBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import fragment.Home_Fragment;
import fragment.My_Fragment;
import fragment.WishFragMent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;
import untils.SPUtils;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.frame_main)
    FrameLayout frameMain;

    private My_Fragment my_fragment;
    private WishFragMent wish_FragMent;
    private Home_Fragment home_fragment;
    private Fragment currentFragment;
    private List<String> arealist;
    private CoordinatorLayout.LayoutParams layoutParams;
    private int heightPixels;
   private  String subject="文科";

    @Override
    public int getId() {
        return R.layout.activity_home;
    }

    @Override
    public void InIt() {
        //初始化Fragment
        inItFragment();
        checkLogin();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        heightPixels = dm.heightPixels;
        layoutParams = (CoordinatorLayout.LayoutParams) bottomBar.getLayoutParams();
        layoutParams.width = dm.widthPixels;
        layoutParams.height = heightPixels / 12;
        bottomBar.setLayoutParams(layoutParams);

        Boolean frist = (Boolean) SPUtils.get(MyApp.context, "frist", false);
        if(frist==false){

            View viewe = LayoutInflater.from(HomeActivity.this).inflate(R.layout.dialog_shouye, null);
            final AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this)
                    .setView(viewe).show();

  //为获取屏幕宽、高
            android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
            p.height = (int) (heightPixels * 0.5);   //高度设置为屏幕的0.3//宽度设置为屏幕的0.5
            dialog.getWindow().setAttributes(p);
            dialog.setCancelable(false);

            final TextView sy_tvaddress= viewe.findViewById(R.id.sy_tvaddress);
            final EditText sy_edfenshu= viewe.findViewById(R.id.sy_edfenshu);
            final TextView sy_yes= viewe.findViewById(R.id.sy_yes);
            final LinearLayout sy_wen= viewe.findViewById(R.id.sy_wen);
            final LinearLayout sy_li= viewe.findViewById(R.id.sy_li);
            final ImageView img_li= viewe.findViewById(R.id.imgsy_li);
            final ImageView img_wen= viewe.findViewById(R.id.imgsy_wen);
            sy_wen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    img_wen.setImageResource(R.drawable.hong);
                    img_li.setImageResource(R.drawable.bai1);
                    subject = "文科";
                }
            });
            sy_li.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    img_wen.setImageResource(R.drawable.bai1);
                    img_li.setImageResource(R.drawable.hong);
                    subject = "理科";
                }
            });
            sy_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = sy_edfenshu.getText().toString();
                    if(TextUtils.isEmpty(s)){
                        Toast("请填写分数");
                        return;
                    }
                    if(Integer.parseInt(s)<0||Integer.parseInt(s)>750){
                        Toast("分数过高或过低，请填写正确的分数");
                        return;
                    }
                    String s1 = sy_tvaddress.getText().toString();
                    if(s1.equals("点击选择城市")){
                        Toast("请选择城市");
                        return;
                    }
                    SPUtils.put(MyApp.context, "tbmaxfen", s );
                    SPUtils.put(MyApp.context, "tbarea", s1);
                    SPUtils.put(MyApp.context, "tbsubtype", subject);
                    Home_Fragment.homegrade.setText(s);
                    Home_Fragment.homearea.setText(s1);
                    Home_Fragment.homesubtype.setText(subject);
                    SPUtils.put(MyApp.context, "frist", true);
                    dialog.dismiss();
                }
            });
            final GridView gd_address= viewe.findViewById(R.id.gd_address);
            final LinearLayout ll_da= viewe.findViewById(R.id.ll_da);
            sy_tvaddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gd_address.setVisibility(View.VISIBLE);
                    ll_da.setVisibility(View.INVISIBLE);
                }
            });
            initdata();
            Gv_addresssyAdapter gv_addressAdapter = new Gv_addresssyAdapter(arealist, this);
            gv_addressAdapter.setAddressBack(new Gv_addresssyAdapter.AddressBack() {
                @Override
                public void setaddress(String s) {
                    gd_address.setVisibility(View.GONE);
                    ll_da.setVisibility(View.VISIBLE);
                    sy_tvaddress.setText(s);
                }
            });
            gd_address.setAdapter(gv_addressAdapter);


        }


        /**
         * 底部点击事件
         */
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.tab_home:
                        switchFragment(home_fragment).commitAllowingStateLoss();
                        break;
                    case R.id.tab_wish:
                        switchFragment(wish_FragMent).commitAllowingStateLoss();
                        break;
                    case R.id.tab_my:
                        switchFragment(my_fragment).commitAllowingStateLoss();
                        break;
                }
            }
        });


    }

    private void initdata() {
       arealist=new ArrayList<>();
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
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //super.onSaveInstanceState(outState, outPersistentState);
    }

    /**
     * 加载Fragment
     */
    private void inItFragment() {
        my_fragment = new My_Fragment();
        wish_FragMent = new WishFragMent();
        home_fragment = new Home_Fragment();
    }
    public     void checkLogin(){
        String token = (String) SPUtils.get(MyApp.context, "token", "");
        if(token.length()>4){
            DisposableSubscriber<BaseBean<UserBean>> disposableSubscriber = MyQusetUtils.getInstance()
                    .getQuestInterface().getUserinfo(token)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeWith(new DisposableSubscriber<BaseBean<UserBean>>() {
                        @Override
                        public void onNext(BaseBean<UserBean> baseBean) {
                            if (baseBean.code == 0) {
                                MyUserBean.setUserBean(baseBean.data);
                                UserBean data = baseBean.data;
                                if (data.getName() != null) {
                                    SPUtils.put(MyApp.context, "name", data.getName());
                                    String province = (String) data.getProvince();
                                    String city = (String) data.getCity();
                                    String area = (String) data.getArea();
                                    SPUtils.put(MyApp.context, "school", province + city + area);
                                }else {
                                    Intent intent=new Intent(HomeActivity.this,perfectMessageActivity.class);
                                    startActivity(intent);
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

    }





    /**
     * 切换fragment
     *
     * @param targetFragment
     * @return
     */
    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.frame_main, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

    @Override
    protected void onResume() {
        super.onResume();
        final String token = (String) SPUtils.get(MyApp.context, "token", "");
        if(token.length()>4){
                     MyQusetUtils.getInstance()
                    .getQuestInterface().checkToken(token)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .retry(2)
                    .subscribeWith(new DisposableSubscriber<BaseBean>() {
                        @Override
                        public void onNext(BaseBean baseBean) {
                            if(baseBean.code==0){
                                SPUtils.put(MyApp.context,"token",baseBean.token);
                            }
                            else {
                                SPUtils.remove(MyApp.context, "token");
                                SPUtils.remove(MyApp.context, "tbmaxfen");
                                SPUtils.remove(MyApp.context, "tbarea");
                                SPUtils.remove(MyApp.context, "tbsubtype");
                                SPUtils.remove(MyApp.context, "majorindex");
                                SPUtils.remove(MyApp.context, "name");
                                SPUtils.remove(MyApp.context, "school");
                                SPUtils.remove(MyApp.context, "province");
                                SPUtils.remove(MyApp.context, "city");
                                SPUtils.remove(MyApp.context, "area");
                                MyUserBean.setUserBean(null);
                                Toast("用户信息失效，请重新登录");
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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyUserBean.onDestory();

    }


}
