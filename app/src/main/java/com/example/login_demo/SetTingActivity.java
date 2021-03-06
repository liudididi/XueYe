package com.example.login_demo;

import android.Manifest;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import base.BaseActivity;
import base.BaseApi;
import bean.MyUserBean;
import bean.UserBean;
import bean.VisionBean;
import butterknife.BindView;
import butterknife.OnClick;
import presenter.VerSionPresent;
import untils.NetUtil;
import untils.PermissionUtils;
import untils.SPUtils;
import view.VerSionView;

import static com.example.login_demo.MyApp.context;

public class SetTingActivity extends BaseActivity implements VerSionView {

    @BindView(R.id.setting_iv_back)
    ImageView settingIvBack;
    @BindView(R.id.setting_account)
    RelativeLayout settingAccount;
    @BindView(R.id.setting_baiduren)
    RelativeLayout settingBaiduren;
    @BindView(R.id.setting_verson)
    RelativeLayout settingVerson;
    @BindView(R.id.setting_useragreen)
    RelativeLayout settingUseragreen;
    @BindView(R.id.setting_back)
    TextView settingBack;
    @BindView(R.id.setting_name)
    TextView settingname;
    @BindView(R.id.setting_school)
    TextView settingschool;
    @BindView(R.id.setting_icon)
     ImageView setting_icon;
    @BindView(R.id.img_hong)
    ImageView imgHong;
    @BindView(R.id.setting_verinfo)
    TextView settingVerinfo;
    @BindView(R.id.setting_ll)
    LinearLayout settingLl;
    @BindView(R.id.tv_quxiao)
    TextView tvQuxiao;
    @BindView(R.id.tv_exitlogin)
    TextView tvExitlogin;
    @BindView(R.id.rl_exitlogin)
    RelativeLayout rlExitlogin;
    private String token;
    private Intent intent;
    private VisionBean visionBean;
    private VerSionPresent verSionPresent;
    private ProgressDialog pd6;
    PackageInfo Common = null;
    @Override
    public int getId() {
        return R.layout.activity_set_ting;
    }

    @Override
    public void InIt() {
        token = (String) SPUtils.get(MyApp.context, "token", "");
       // verSionPresent = new VerSionPresent(this);
       // verSionPresent.versioninfo("Android");
        try {
            Common = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            settingVerinfo.setText("当前版本" + Common.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Boolean checLogin() {
        if (token.length() < 4) {
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("请前往“我的”进行登录后才能使用")
                    .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            intent = new Intent(SetTingActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserBean userBeanInstans = MyUserBean.getUserBeanInstans();
        if (userBeanInstans != null) {
            String sex = (String) userBeanInstans.getSex();
            if (sex != null) {
                if (sex.equals("女")) {

                    Glide.with(context).load(R.drawable.gril ).asBitmap().centerCrop().into(new BitmapImageViewTarget(setting_icon) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            setting_icon .setImageDrawable(circularBitmapDrawable);
                        }
                    });
                } else {
                    Glide.with(context).load(R.drawable.boy ).asBitmap().centerCrop().into(new BitmapImageViewTarget(setting_icon) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            setting_icon .setImageDrawable(circularBitmapDrawable);
                        }
                    });

                }
            } else {
                Glide.with(context).load(R.drawable.boy ).asBitmap().centerCrop().into(new BitmapImageViewTarget(setting_icon) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        setting_icon .setImageDrawable(circularBitmapDrawable);
                    }
                });
            }
            String name = (String) userBeanInstans.getName();
            if (name != null) {
                settingname.setText(name);
               String school   = (String) userBeanInstans.getProvince()+userBeanInstans.getCity();
                settingschool.setText(school);
            } else {
                settingname.setText("");
                settingschool.setText("");
            }
        }
    }

    @OnClick({R.id.setting_iv_back, R.id.setting_account,R.id.tv_quxiao, R.id.tv_exitlogin, R.id.rl_exitlogin, R.id.setting_baiduren, R.id.setting_verson, R.id.setting_useragreen, R.id.setting_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_iv_back:
                finish();
                break;
            //账户管理
            case R.id.setting_account:
                Boolean aBoolean = checLogin();
                if (aBoolean == true) {
                    if(NetUtil.isNetworkAvailable(this)==false){
                        Toast("当前无网络");
                        return;
                    }
                    intent = new Intent(this, AccountMagActivity.class);
                    startActivity(intent);
                }
                break;
            //关于摆渡人
            case R.id.setting_baiduren:
                if(NetUtil.isNetworkAvailable(this)==false){
                    Toast("当前无网络");
                    return;
                }
                intent = new Intent(this, AsForBaiDuRenActivity.class);
                startActivity(intent);

                break;
            //版本介绍
            case R.id.setting_verson:

                break;
            //用户协议
            case R.id.setting_useragreen:

                if(NetUtil.isNetworkAvailable(this)==false){
                    Toast("当前无网络");
                    return;
                }
                Intent intent = new Intent(this, ParticularsActivity.class);
                intent.putExtra("particulars_title","用户协议");
                intent.putExtra("url","https://m.bdrvip.com/#/agreement");
                startActivity(intent);
                break;
            //退出登录
            case R.id.setting_back:
                if(token.length()>4){
                    rlExitlogin.setVisibility(View.VISIBLE);
                }else {
                    Toast("请前往“我的”进行登录");
                }

                break;


            case R.id.tv_quxiao:
                rlExitlogin.setVisibility(View.GONE);
                break;
            case R.id.tv_exitlogin:
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
                finish();
                break;
            case R.id.rl_exitlogin:
                rlExitlogin.setVisibility(View.GONE);
                break;
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
            downloadUri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".myFileProvider", file);

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
                    boolean b = permissionUtils.hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
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
                                                settingVerson.setEnabled(false);
                                                imgHong.setVisibility(View.INVISIBLE);
                                                Toast.makeText(context, "开始下载", Toast.LENGTH_SHORT).show();
                                                //final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            /*    NotificationChannel channel = new NotificationChannel("123", "摆渡人", NotificationManager.IMPORTANCE_HIGH);
                                                *//**设置下载地址*//*
                                                manager.createNotificationChannel(channel);
                                                Intent intent = new Intent(SetTingActivity.this, SetTingActivity.class);
                                                PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
                                                Notification build = new NotificationCompat.Builder(SetTingActivity.this)
                                                        .setTicker("正在下载") //通知首次出现在通知栏，带上升动画效果的
                                                        .setOngoing(true)//设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐) 或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                                                        .setSmallIcon(android.R.drawable.stat_notify_chat)//设置通知小ICON
                                                        .setContentTitle("下载中")//设置通知栏标题
                                                        .setContentText("正在下载请稍等")//设置通知栏显示内容
                                                        .setChannelId("123")
                                                        .setContentIntent(contentIntent)
                                                        .setAutoCancel(true)
                                                        .build();
                                                manager.notify(123, build);*/
                                                final Thread thread = new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        downfile();
                                                    }
                                                });

                                                pd6 = new ProgressDialog(SetTingActivity.this);
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
                                            /*    new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        int i = 0;
                                                        while (i < 100) {
                                                            try {
                                                                Thread.sleep(200);
                                                                // 更新进度条的进度,可以在子线程中更新进度条进度
                                                                pd6.incrementProgressBy(1);
                                                                // dialog.incrementSecondaryProgressBy(10)//二级进度条更新方式
                                                                i++;
                                                            } catch (Exception e) {
                                                            }
                                                        }
                                                        // 在进度条走完时删除Dialog
                                                        pd6.dismiss();
                                                    }
                                                }).start();*/

                                           } else {
                                                Intent intent = new Intent(SetTingActivity.this, DownApkServer.class);
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
                        permissionUtils.requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, 10);
                    }
                } else {
                    settingVerinfo.setText("当前版本" + visionBean.getVersionName());
                    imgHong.setVisibility(View.INVISIBLE);
                    settingVerson.setEnabled(false);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void VersionFail(String s) {

        try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            settingVerinfo.setText("当前版本" + packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }





}
