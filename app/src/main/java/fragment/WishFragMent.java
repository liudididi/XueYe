package fragment;



import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_demo.Buy2Activity;
import com.example.login_demo.EFCJieSuoActivity;
import com.example.login_demo.ExamMessageActivity;
import com.example.login_demo.MyApp;
import com.example.login_demo.R;
import com.example.login_demo.ReportedActivity;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import adapter.Wash_School_adapter;
import base.BaseBean;
import base.Basefragment;
import bean.CanSchoolBean;
import bean.CanSchoolBean2;
import bean.SlideshowBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import presenter.CountdownPresent;
import presenter.WishPresent;
import untils.MyQusetUtils;
import untils.NetUtil;
import untils.SPUtils;
import view.CountdownView;
import view.WishView;

/**
 * Created by 地地 on 2018/1/19.
 * 邮箱：461211527@qq.com.
 */

public class WishFragMent extends Basefragment implements WishView, CountdownView{
    private RecyclerView school_recycle;
    private XBanner ws_xbanner;
    private ArrayList<Integer> list;
    private LinearLayout ll_back;
    private TextView wish_monthly;
    private TextView wish_midterm;
    private TextView wish_end;
    private String tbmaxfen=null;
    private String tbarea=null;
    private String tbsubtype=null;
    private WishPresent wishPresent;
    private  String area;
    private  String token;
    private TextView wish_day1;
     private TextView wish_day3;
    private CountdownPresent countdownPresent;
    private ImageView wish_school_none;
    public static String djs=null;
    private TextView tv_gk1;
    private TextView tv_gm1;
    private TextView tv_ym3;
    private TextView tv_gx3;
    private ImageView iv;


    @Override
    public int getLayoutid() {
        return R.layout.wish_fragment;
    }

    @Override
    public void initView() {
    initId();
    }

    @Override
    public void onResume() {
        super.onResume();
        tbmaxfen = (String) SPUtils.get(MyApp.context, "tbmaxfen", "500");
        tbarea = (String) SPUtils.get(MyApp.context, "tbarea", "北京市");
        tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "文科");
        token = (String) SPUtils.get(MyApp.context, "token", "");
        wish_school_none.setVisibility(View.GONE);
        if(NetUtil.isNetworkAvailable(getActivity())){
          //  wishPresent.CanSchoolPresente(tbarea,tbsubtype,"0",tbmaxfen,"1","5");
            wishPresent.CompleCanSchoolPresente( "0",(Integer.parseInt(tbmaxfen)-21) + "","","", "",tbarea,tbsubtype);
        }else {
            Toast.makeText(getActivity(), "当前无网络", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onStop() {
        super.onStop();
        ws_xbanner.stopAutoPlay();
    }
    private void initId() {
        school_recycle = view.findViewById(R.id.wish_school_recycle);
        ws_xbanner = view.findViewById(R.id.ws_xbanner);
        ll_back = view.findViewById(R.id.ll_back);
        wish_monthly = view.findViewById(R.id.wish_monthly);
        wish_midterm = view.findViewById(R.id.wish_midterm);
        wish_end = view.findViewById(R.id.wish_end);

        wish_school_none = view.findViewById(R.id.wish_school_none);


        wish_day1 = view.findViewById(R.id.wish_day1);
        wish_day3 = view.findViewById(R.id.wish_day3);

        tv_gk1 = view.findViewById(R.id.tv_gk1);
        tv_gm1 = view.findViewById(R.id.tv_gm1);
        tv_ym3 = view.findViewById(R.id.tv_ym3);
        tv_gx3 = view.findViewById(R.id.tv_gx3);
        iv = view.findViewById(R.id.iv);

        list = new ArrayList<>();
        list.add(R.drawable.gkdjs);
        list.add(R.drawable.ymdjs);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (token.length() < 4) {
                    Toast.makeText(getActivity(), "请前往“我的”进行登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(NetUtil.isNetworkAvailable(getActivity())==false){
                    Toast.makeText(getActivity(), "当期无网络", Toast.LENGTH_SHORT).show();
                    return;
                }
                MyQusetUtils.getInstance().getQuestInterface().jzjudge(token)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .retry(2)
                        .subscribeWith(new DisposableSubscriber<BaseBean<String>>() {
                            @Override
                            public void onNext(BaseBean<String> stringBaseBean) {
                                iv.setEnabled(true);
                                if (stringBaseBean.code == 0) {
                                    final String data = stringBaseBean.data;
                                    if(data.equals("0")){
                                        SPUtils.put(MyApp.context,"kemuefc","本科");
                                    }else {
                                        SPUtils.put(MyApp.context,"kemuefc","专科");
                                    }
                                    SPUtils.put(MyApp.context,"VIP",true);
                                    getContext().startActivity(new Intent(getContext(), EFCJieSuoActivity.class));
                                }else {
                                    SPUtils.put(MyApp.context,"VIP",false);
                                    getContext().startActivity(new Intent(getContext(), Buy2Activity.class));
                                }
                            }

                            @Override
                            public void onError(Throwable t) {
                                iv.setEnabled(true);
                                Toast.makeText(getActivity(), "网络较差,请重试~", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
        ws_xbanner.setData(list,null);
        ws_xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                ws_xbanner.removeView(view);
                ImageView imageView = (ImageView) view;
                imageView.setImageResource(list.get(position));

            }
        });
        ws_xbanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0)
                {
                    wish_day1.setVisibility(View.VISIBLE);
                    tv_gk1.setVisibility(View.VISIBLE);
                    tv_gm1.setVisibility(View.VISIBLE);
                     wish_day3.setVisibility(View.GONE);
                    tv_ym3.setVisibility(View.GONE);
                    tv_gx3.setVisibility(View.GONE);
                }
                else if (position==1)
                {
                    tv_gk1.setVisibility(View.GONE);
                    tv_gm1.setVisibility(View.GONE);
                    wish_day1.setVisibility(View.GONE);
                     wish_day3.setVisibility(View.VISIBLE);
                    tv_ym3.setVisibility(View.VISIBLE);
                    tv_gx3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //动态设置高度
        View view_bottom=view.findViewById(R.id.view_bottom);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int heightPixels = dm.heightPixels;
        ViewGroup.LayoutParams layoutParams = view_bottom.getLayoutParams();
        layoutParams.width=dm.widthPixels;
        layoutParams.height=heightPixels/12;
        view_bottom.setLayoutParams(layoutParams);

        //轮播图
        wishPresent = new WishPresent(this);

        //倒计时
        countdownPresent = new CountdownPresent(this);
        countdownPresent.CountdownPresent();
        //能上的学校
        tbmaxfen = (String) SPUtils.get(MyApp.context, "tbmaxfen", "");
        tbarea = (String) SPUtils.get(MyApp.context, "tbarea", "");
        tbsubtype = (String) SPUtils.get(MyApp.context, "tbsubtype", "");
        if(tbarea!=null&&tbarea!=""&&tbmaxfen!=""&&tbmaxfen!=null&&tbsubtype!=null&&tbsubtype!=""){
           // wishPresent.CanSchoolPresente(tbarea,tbsubtype,"0",tbmaxfen,"1","5");
            wishPresent.CompleCanSchoolPresente( "0",(Integer.parseInt(tbmaxfen)-21) + "","","", "",tbarea,tbsubtype);

        }/*else {
            wishPresent.CanSchoolPresente("北京","文科","0","500","1","5");
        }*/
        onClick();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wishPresent.onDestory();
        countdownPresent.onDestory();
    }

    private void onClick() {
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), ReportedActivity.class));
            }
        });

        wish_monthly.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ExamMessageActivity.class);
                intent.putExtra("form","月考");
                intent.putExtra("form_int",0);
                getContext().startActivity(intent);


            }
        });
        wish_midterm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ExamMessageActivity.class);
                intent.putExtra("form","期中");
                intent.putExtra("form_int",1);
                getContext().startActivity(intent);

             }
        });
        wish_end.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ExamMessageActivity.class);
                intent.putExtra("form","期末");
                intent.putExtra("form_int",2);
                getContext().startActivity(intent);
            }
        });


    }

    //志愿表轮播图
    @Override
    public void Wishsuccess(BaseBean<List<SlideshowBean>> listBaseBean) {


    }

    @Override
    public void Wishfail(Throwable t) {

    }

    //能上的学校
    @Override
    public void CanSchoolsuccess(BaseBean<CanSchoolBean> canSchoolBeanBaseBean) {
        //能上的学校
        List<CanSchoolBean2> list=new ArrayList<>();
        List<CanSchoolBean.ListBean> list1 = canSchoolBeanBaseBean.data.getList();
        if(list1!=null&&list1.size()>0){
            wish_school_none.setVisibility(View.GONE);
            school_recycle.setVisibility(View.VISIBLE);
            for (int i = 0; i < 5; i++) {
                list.add(new CanSchoolBean2(list1.get(i).getUrl(),list1.get(i).getName(),list1.get(i).getAddress(),list1.get(i).getCom_rank()));
            }
            Wash_School_adapter wash_school_adapter=new Wash_School_adapter(list,getActivity());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            school_recycle.setLayoutManager(linearLayoutManager);
            school_recycle.setAdapter(wash_school_adapter);
        }else {
            wish_school_none.setVisibility(View.VISIBLE);
            school_recycle.setVisibility(View.GONE);
        }

    }

    @Override
    public void CanSchoolfail(Throwable t) {

    }

    //倒计时
    @Override
    public void Countdownsuccess(BaseBean baseBean) {
        djs = baseBean.data.toString();
        wish_day1.setText(djs+" 天");
        //wish_day2.setText((Integer.parseInt(djs)+2)+"");
        //wish_day3.setText((Integer.parseInt(djs)+85)+" 天");
        wish_day3.setText(baseBean.data2+" 天");
    }
    @Override
    public void Countdownfail(Throwable t) {

    }
}
