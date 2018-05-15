package fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.login_demo.AddServeActivity;
import com.example.login_demo.CharacterActivity;
import com.example.login_demo.GradePolyLineActivity;
import com.example.login_demo.HelpActivity;
import com.example.login_demo.MaJorActivity;
import com.example.login_demo.MainActivity;
import com.example.login_demo.MyApp;
import com.example.login_demo.MySchoolActivity;
import com.example.login_demo.PresonMessageActivity;
import com.example.login_demo.R;
import com.example.login_demo.ReportedActivity;
import com.example.login_demo.SetTingActivity;
import com.example.login_demo.SuggestActivity;

import base.BaseApi;
import base.BaseBean;
import base.Basefragment;
import bean.MyUserBean;
import bean.UserBean;
import presenter.CountdownPresent;
import untils.NetUtil;
import untils.SPUtils;
import view.CountdownView;

/**
 * Created by 祝文 on 2018/1/19.
 */

public class My_Fragment extends Basefragment implements View.OnClickListener, CountdownView{

    private TextView my_login;
    private  ImageView my_icon;
    private RelativeLayout my_school,my_major,my_washtable,my_character,my_gradetable
                            ,my_help,my_addserve,my_suggest;

    private ImageView my_setting;
    private String token;
    private Intent intent;
    private Boolean checLogin;
    private View my_view;
    private TextView myfragment_name;
    private TextView myfragment_school;
    private String school;
    private String name;
    private TextView tv_tv;
    private ImageView iv_qm;
    private CountdownPresent countdownPresent;
    private TextView tv_day1;
    private TextView tv_day2;
    private TextView tv_day3;

    @Override
    public int getLayoutid() {
        return R.layout.my_fragment;
    }

    @Override
    public void initView() {
        init();
        initOnClick();
        countdownPresent = new CountdownPresent(this);
        countdownPresent.CountdownPresent();
    }

 /*   @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {   // 不在最前端显示 相当于调用了onPause();
            Toast.makeText(getActivity(), "展示了", Toast.LENGTH_SHORT).show();
        }
    }*/

    private void initOnClick() {
        my_login.setOnClickListener(this);
        my_icon.setOnClickListener(this);
        my_school.setOnClickListener(this);
        my_major.setOnClickListener(this);
        my_washtable.setOnClickListener(this);
        my_character.setOnClickListener(this);
        my_gradetable.setOnClickListener(this);

        my_help.setOnClickListener(this);
        my_addserve.setOnClickListener(this);
        my_suggest.setOnClickListener(this);
        my_setting.setOnClickListener(this);

    }

    private void init() {
        my_login = view.findViewById(R.id.my_login);
        tv_tv = view.findViewById(R.id.tv_tv);
        iv_qm = view.findViewById(R.id.iv_qm);
        tv_day1 = view.findViewById(R.id.tv_day1);
        tv_day2 = view.findViewById(R.id.tv_day2);
        tv_day3 = view.findViewById(R.id.tv_day3);
        my_icon = view.findViewById(R.id.my_icon);
        my_school = view.findViewById(R.id.my_school);
        //我的专业
        my_major = view.findViewById(R.id.my_major);
        //性格测试
        my_character = view.findViewById(R.id.my_character);
        //我的成绩表
        my_gradetable = view.findViewById(R.id.my_gradetable);
        //我的志愿表
        my_washtable = view.findViewById(R.id.my_washtable);
        myfragment_name = view.findViewById(R.id.myfragment_name);
        myfragment_school = view.findViewById(R.id.myfragment_school);
        //我的帮助
        my_help = view.findViewById(R.id.my_help);
        //购买增值
        my_addserve = view.findViewById(R.id.my_addserve);
        //建议
        my_suggest = view.findViewById(R.id.my_suggest);
        my_setting = view.findViewById(R.id.my_setting);
        my_view = view.findViewById(R.id.my_view);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int heightPixels = dm.heightPixels;
        ViewGroup.LayoutParams layoutParams =  my_view.getLayoutParams();
        layoutParams.width=dm.widthPixels;
        layoutParams.height=heightPixels/12;
        my_view.setLayoutParams(layoutParams);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_login:
                    intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                break;
            case R.id.my_school:
                 checLogin= checLogin();
                if(checLogin ==true){
                   if(NetUtil.isNetworkAvailable(getActivity())==false){
                       Toast.makeText(getActivity(), "检查您的网络", Toast.LENGTH_SHORT).show();
                       return;
                   }

                        intent = new Intent(getActivity(), MySchoolActivity.class);
                        intent.putExtra("token", token);
                        startActivity(intent);
                }
                break;
            case R.id.my_icon:
                checLogin = checLogin();
                if(checLogin ==true){

                    if(NetUtil.isNetworkAvailable(getActivity())==false){
                        Toast.makeText(getActivity(), "检查您的网络", Toast.LENGTH_SHORT).show();
                        return;
                    }
                        intent = new Intent(getActivity(), PresonMessageActivity.class);
                        startActivity(intent);
                }
                break;
            case R.id.my_major:
                checLogin = checLogin();
                if(checLogin ==true){
                    boolean networkAvailable = NetUtil.isNetworkAvailable(getActivity());
                    if(networkAvailable){
                        intent=new Intent(getActivity(), MaJorActivity.class);
                        intent.putExtra("token",token);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getActivity(), "检查您的网络", Toast.LENGTH_SHORT).show();
                    }


                }

                break;
            case R.id.my_washtable:
                checLogin = checLogin();
                if(checLogin ==true){
                    intent=new Intent(getActivity(), ReportedActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.my_character:
                checLogin = checLogin();
                if(checLogin ==true){
                    intent=new Intent(getActivity(), CharacterActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.my_gradetable:
                checLogin = checLogin();
                if(checLogin ==true){
                    Intent intent=new Intent(getContext(),GradePolyLineActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.my_help:
                if(NetUtil.isNetworkAvailable(getActivity())==false){
                    Toast.makeText(getActivity(), "检查您的网络", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent in=new Intent(getContext(), HelpActivity.class);
                in.putExtra("pid","2");
                getContext().startActivity(in);
                break;
            case R.id.my_addserve:
                if(NetUtil.isNetworkAvailable(getActivity())==false){
                    Toast.makeText(getActivity(), "检查您的网络", Toast.LENGTH_SHORT).show();
                    return;
                }
                getContext().startActivity(new Intent(getContext(), AddServeActivity.class));
                break;
            case R.id.my_suggest:
                getContext().startActivity(new Intent(getContext(), SuggestActivity.class));
                break;
            case R.id.my_setting:
                 intent=new Intent(getActivity(), SetTingActivity.class);
                 startActivity(intent);
                break;

        }
    }



    private Boolean checLogin() {
       if(token.length()<4){
           new AlertDialog.Builder(getActivity())
                   .setTitle("提示")
                   .setMessage("该功能需要登录后才能使用")
                   .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           intent=new Intent(getActivity(),MainActivity.class);
                           startActivity(intent);
                       }
                   })
                   .setNegativeButton("取消", null)
                   .show();
           return  false;
         }else {
           return true;
       }
    }

    @Override
    public void onResume() {
        super.onResume();
        token = (String) SPUtils.get(MyApp.context, "token", "");
        if(token.length()>4){

            MyUserBean.checkLogin();
             my_login.setVisibility(View.INVISIBLE);
             tv_tv.setVisibility(View.GONE);
            UserBean userBeanInstans = MyUserBean.getUserBeanInstans();
            if(userBeanInstans!=null){
                 school = (String) SPUtils.get(MyApp.context, "school", "地区");
                 name = (String) SPUtils.get(MyApp.context, "name", "姓名");
                myfragment_name.setVisibility(View.VISIBLE);
                myfragment_school.setVisibility(View.VISIBLE);
                iv_qm.setVisibility(View.VISIBLE);
                myfragment_name.setText(name);
                myfragment_school.setText(school);
                if(userBeanInstans.getSex()!=null){
                    if(userBeanInstans.getSex().equals("女")){
                       // Glide.with(getActivity()).load(R.drawable.gril).into(my_icon);
                       // my_icon.setImageResource(R.drawable.gril);
                        Glide.with(MyApp.context).load(R.drawable.gril).asBitmap().centerCrop().into(new BitmapImageViewTarget(my_icon) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(MyApp.context.getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                my_icon .setImageDrawable(circularBitmapDrawable);
                            }
                        });



                    }else {
                      //  Glide.with(getActivity()).load(R.drawable.boy).into(my_icon);
                        Glide.with(MyApp.context).load(R.drawable.boy).asBitmap().centerCrop().into(new BitmapImageViewTarget(my_icon) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(MyApp.context.getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                my_icon .setImageDrawable(circularBitmapDrawable);
                            }
                        });
                    }
                }
                else {
                    /*Glide.with(getActivity()).load(R.drawable.boy).into(my_icon);*/
                   // my_icon.setImageResource(R.drawable.boy);

                    Glide.with(MyApp.context).load(R.drawable.boy).asBitmap().centerCrop().into(new BitmapImageViewTarget(my_icon) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(MyApp.context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            my_icon .setImageDrawable(circularBitmapDrawable);
                        }
                    });
                }
            }
            my_login.setEnabled(false);
        }else {
           // my_icon.setImageResource(R.drawable.moren);
            Glide.with(MyApp.context).load(R.drawable.moren).asBitmap().centerCrop().into(new BitmapImageViewTarget(my_icon) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(MyApp.context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    my_icon .setImageDrawable(circularBitmapDrawable);
                }
            });
            my_login.setText("登录");
            tv_tv.setVisibility(View.VISIBLE);
            iv_qm.setVisibility(View.GONE);

            my_login.setVisibility(View.VISIBLE);
            my_login.setEnabled(true);
            myfragment_name.setVisibility(View.INVISIBLE);
            myfragment_school.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void Countdownsuccess(BaseBean baseBean) {

        String s = baseBean.data.toString();

        if(s!=null&&s.length()==3)
        {
            String substring = s.substring(0,1);
            String substring1 = s.substring(1);
            String substring2 = s.substring(2);
            tv_day1.setText(substring);
            tv_day2.setText(substring1);
            tv_day3.setText(substring2);
        }
        if(s!=null&&s.length()==2)
        {
            String substring = s.substring(0,1);
            String substring1 = s.substring(1);
            tv_day1.setText("0");
            tv_day2.setText(substring);
            tv_day3.setText(substring1);
        }
        else
        {
            tv_day1.setText("0");
            tv_day2.setText("0");
            tv_day3.setText(s);

        }
    }

    @Override
    public void Countdownfail(Throwable t) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countdownPresent.onDestory();
    }
}
