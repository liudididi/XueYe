package com.example.login_demo;

import android.*;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import adapter.Gv_addresssyAdapter;
import base.BaseActivity;
import base.BaseBean;
import bean.MyUserBean;
import bean.UserBean;
import bean.VisionBean;
import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import fragment.Home_Fragment;
import fragment.My_Fragment;
import fragment.WishFragMent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import presenter.VerSionPresent;
import untils.MyQusetUtils;
import untils.PermissionUtils;
import untils.SPUtils;
import view.VerSionView;

import static com.example.login_demo.MyApp.context;

public class HomeActivity extends BaseActivity implements VerSionView {

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
    private VerSionPresent verSionPresent;
    private VisionBean visionBean;
    private ProgressDialog pd6;
    @Override
    public int getId() {
        return R.layout.activity_home;
    }

    @Override
    public void InIt() {
        //初始化Fragment
        inItFragment();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        heightPixels = dm.heightPixels;
        SPUtils.put(MyApp.context,"FBL",heightPixels);
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
           /* android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
            p.height = (int) (heightPixels * 0.5);   //高度设置为屏幕的0.3//宽度设置为屏幕的0.5
            dialog.getWindow().setAttributes(p);*/
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

        }else {
            verSionPresent = new VerSionPresent(this);
            verSionPresent.versioninfo("Android");
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
        checkLogin();
        my_fragment = new My_Fragment();
        wish_FragMent = new WishFragMent();
        home_fragment = new Home_Fragment();
    }
    public   void checkLogin(){
        String token = (String) SPUtils.get(MyApp.context, "token", "");
        if(token.length()>4){
            DisposableSubscriber<BaseBean<UserBean>> disposableSubscriber =
                    MyQusetUtils.getInstance()
                    .getQuestInterface().getUserinfo(token)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeWith(new DisposableSubscriber<BaseBean<UserBean>>() {
                        @Override
                        public void onNext(BaseBean<UserBean> baseBean) {
                            if (baseBean.code == 0) {
                                if(baseBean.data!=null){
                                MyUserBean.setUserBean(baseBean.data);
                                UserBean data = baseBean.data;
                                if (data.getName() != null) {
                                    SPUtils.put(MyApp.context, "name", data.getName());
                                    String province = (String) data.getProvince();
                                    String city = (String) data.getCity();
                                    SPUtils.put(MyApp.context, "school", province + city);
                                   if(data.getExamprovince()!=null){
                                       SPUtils.put(MyApp.context, "tbarea", data.getExamprovince());
                                   }

                                   if(data.getStutype()!=null){
                                       SPUtils.put(MyApp.context, "tbsubtype", data.getStutype());
                                   }
                                    Integer examscore =   data.getExamscore();
                                    if(examscore!=null){
                                        SPUtils.put(MyApp.context, "tbmaxfen", examscore+"");
                                    }
                                    if(data.getSex()!=null){
                                        SPUtils.put(MyApp.context,"sex",data.getSex());
                                    }
                                }else {
                                    Intent intent=new Intent(HomeActivity.this,perfectMessageActivity.class);
                                    startActivity(intent);
                                }
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
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
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
                                checkLogin();
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
                                SPUtils.remove(MyApp.context, "tbsubtypeefc");
                                SPUtils.remove(MyApp.context, "tbmaxfenefc");
                                SPUtils.remove(MyApp.context, "kemuefc");
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
        if(verSionPresent!=null){
            verSionPresent.onDestory();
        }

    }

    @Override
    public void onBackPressed() {
         moveTaskToBack(true);
    }



    @Override
    public void VersionFail(String s) {

    }

    private void install() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "摆渡人.apk");
        if (!file.exists()) {
            return;
        }
        Uri downloadUri = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            downloadUri = Uri.fromFile(file);
        } else {
            Intent installApkIntent = new Intent();
            installApkIntent.setAction(Intent.ACTION_VIEW);
            installApkIntent.addCategory(Intent.CATEGORY_DEFAULT);
            installApkIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            installApkIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            downloadUri = FileProvider.getUriForFile(MyApp.context,"com.example.login_demo.myFileProvider", file);
        }
        intent.setDataAndType(downloadUri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }


    @Override
    public void VersionSuccess(List<VisionBean> list) {
        if (list != null && list.size() > 0) {
            visionBean = list.get(0);
            PackageInfo Common = null;
            try {
                Common = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
                int verCode = Common.versionCode;

                if (verCode < visionBean.getVersionCode()) {
                    if (visionBean == null) {
                        return;
                    }
                    PermissionUtils permissionUtils = new PermissionUtils(this);
                    boolean b = permissionUtils.hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (b) {
                        String str = "发现新版本：" + visionBean.getVersionName() +
                                " Code:" + visionBean.getVersionCode() + " ,是否更新？";
                        Dialog dialog = new android.app.AlertDialog.Builder(this).setTitle("软件更新").setMessage(str)
                                // 设置内容
                                .setPositiveButton("更新",// 设置确定按钮
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                Toast.makeText(context, "开始下载", Toast.LENGTH_SHORT).show();
                                                //final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "摆渡人.apk");
                                                    if (file.exists()) {
                                                        file.delete();
                                                    }
                                                    final Thread thread = new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            downfile();
                                                        }
                                                    });
                                                    pd6 = new ProgressDialog(HomeActivity.this);
                                                    pd6.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条
                                                    pd6.setCancelable(true);// 设置是否可以通过点击Back键取消
                                                    pd6.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条// 设置提示的title的图标，默认是没有的
                                                    pd6.setTitle("版本更新");
                                                    pd6.setMax(100);
                                                    pd6.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    thread.interrupt();
                                                                }
                                                            });
                                                    pd6.show();
                                                    thread.start();
                                                } else {
                                                    Intent intent = new Intent(HomeActivity.this, DownApkServer.class);
                                                    intent.putExtra("downUrl", visionBean.getDownloadPath());
                                                    startService(intent);
                                                }
                                            }
                                        })
                                .setNegativeButton("暂不更新",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int whichButton) {
                                                // 点击"取消"按钮之后退出程序

                                            }
                                        }).create();// 创建
                        // 显示对话框
                        dialog.show();
                    } else {
                        permissionUtils.requestPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, 10);
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void downfile() {
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "摆渡人.apk");
            HttpURLConnection connection = (HttpURLConnection) new URL(visionBean.getDownloadPath()).openConnection();
            connection.setRequestMethod("GET");
            long sum = 0;
            int code = connection.getResponseCode();
            if (code == 200 || code == 206) {
                int contentLength = connection.getContentLength();
                contentLength += sum;
                InputStream is = connection.getInputStream();
                /*
                *
                * 创建一个向具有指定 name 的文件中写入数据的输出文件流。
                * true表示当文件在下载过程中出现中断，
                * 当再次链接网络时，将会从断点处追加。
                *
                * */
                FileOutputStream fos = new FileOutputStream(file, true);

                byte[] buffer = new byte[102400];
                int length;
                long startTime = System.currentTimeMillis();
                while ((length = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, length);
                    sum += length;
                    float percent = sum * 100.0f / contentLength;
                    int p = (int) percent;
                    pd6.setProgress(p);
                    /*
                    * 实现进度条
                    * */
               /*     for (int i = 0; i < 50; i++) {
                        if (i < p) {
                            System.out.print('=');
                        } else if (i == p){
                            System.out.print('>');
                        } else {
                            System.out.print(' ');
                        }
                    }*/
                /*    System.out.print(']');
                    System.out.printf("\t%.2f%%", percent);
                    long speed = sum * 1000 / (System.currentTimeMillis() - startTime);
                    if (speed > (1 << 20)) {
                        System.out.printf("\t%d MB/s", speed >> 20);
                    } else if (speed > (1 << 10)) {
                        System.out.printf("\t%d KB/s", speed >> 10);
                    } else {
                        System.out.printf("\t%d B/s", speed);
                    }*/
                }
                install();
                pd6.dismiss();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
