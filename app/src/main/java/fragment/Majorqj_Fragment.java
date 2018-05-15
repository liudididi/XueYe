package fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.login_demo.MajorDetailActivity;
import com.example.login_demo.MyApp;
import com.example.login_demo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapter.XinziZhuangKAdapter;
import base.Basefragment;
import bean.MajorgkBean;
import presenter.MajorgkPresent;
import untils.ArcView;
import untils.ListViewForScrollView;
import untils.ZhiMaScoreViewXinzi;
import view.MajorgkView;

/**
 * Created by 地地 on 2018/2/2.
 * 邮箱：461211527@qq.com.
 */

public class Majorqj_Fragment  extends Basefragment implements MajorgkView {

    private MajorgkPresent majorgkPresent;
    private TextView qj_tvqj;
    private TextView qj_pro_address;
    private TextView qj_rank;
    private ListViewForScrollView xzzk_list;
    private RelativeLayout rl_pjxz;
    private ScrollView qj_sv;
    private RelativeLayout rl_bt;
    private  ArcView myPieChat;
    private RelativeLayout qj_r4;
    private List<TextView> textViewList;
    private LinearLayout ll_shang;
    private LinearLayout ll_xia;


    @Override
    public int getLayoutid() {
        return R.layout.majorqj;
    }

    @Override
    public void initView() {
        qj_tvqj = view.findViewById(R.id.qj_tvqj);
        qj_sv = view.findViewById(R.id.qj_sv);
        qj_pro_address = view.findViewById(R.id.qj_pro_address);
        qj_rank = view.findViewById(R.id.qj_rank);
        xzzk_list = view.findViewById(R.id.xzzk_list);
        rl_pjxz = view.findViewById(R.id.rl_pjxz);
        rl_bt = view.findViewById(R.id.rl_bt);
        qj_r4 = view.findViewById(R.id.qj_r4);
        myPieChat=view.findViewById(R.id.myPiechat);
        majorgkPresent = new MajorgkPresent(this);
        majorgkPresent.getMajorgk(MajorDetailActivity.majorid);

      TextView  tv_one= view.findViewById(R.id.tv_one);
      TextView  tv_two= view.findViewById(R.id.tv_two);
      TextView  tv_three= view.findViewById(R.id.tv_three);
      TextView  tv_five= view.findViewById(R.id.tv_five);
      TextView  tv_four= view.findViewById(R.id.tv_four);
      TextView  tv_six= view.findViewById(R.id.tv_six);
        textViewList = new ArrayList<>();
        textViewList.add(tv_one);
        textViewList.add(tv_two);
        textViewList.add(tv_three);
        textViewList.add(tv_five);
        textViewList.add(tv_four);
        textViewList.add(tv_six);
        ll_shang = view.findViewById(R.id.ll_shang);
        ll_xia = view.findViewById(R.id.ll_xia);
    }

    @Override
    public void MajorgkSusccess(MajorgkBean majorgkBean) {

        if(majorgkBean!=null){
            if(majorgkBean.getEmployment_prospects()!=null){
                //就业前景
                qj_tvqj.setText(majorgkBean.getEmployment_prospects());
            }
            else
            {
                qj_tvqj.setText("数据整理中");
            }
            if(majorgkBean.getJobSituation()!=null){
                qj_pro_address.setText(majorgkBean.getJobSituation().getPro_address());
                qj_rank.setText(majorgkBean.getJobSituation().getRank());
            }
            else
            {
                qj_pro_address.setText("数据整理中");

            }
            if(majorgkBean.getMajorJobproList()!=null&&majorgkBean.getMajorJobproList().size()>0){
                rl_bt.setVisibility(View.VISIBLE);
                qj_r4.setVisibility(View.VISIBLE);
                ll_shang.setVisibility(View.VISIBLE);
                ll_xia.setVisibility(View.VISIBLE);
                List<Times> times = new ArrayList<>();
                for (int i = 0; i < majorgkBean.getMajorJobproList().size(); i++) {
                        Times t = new Times();
                        t.hour = majorgkBean.getMajorJobproList().get(i).getJobpro();
                        t.text =majorgkBean.getMajorJobproList().get(i).getJobname(); ;
                        times.add(t);
                }
                int size = times.size();
                if(size>6){
                    size=6;
                }
                for (int i = 0; i < size; i++) {
                    textViewList.get(i).setText(times.get(i).text);
                }
                ArcView.ArcViewAdapter<Times> arcViewAdapter = myPieChat.new ArcViewAdapter<Times>() {

                    @Override
                    public double getValue(Times times) {
                        return times.hour;
                    }

                    @Override
                    public String getText(Times times) {
                        return "";
                    }
                };
                //设置adapter
                arcViewAdapter.setData(times);//设置数据集
                myPieChat.setMaxNum(6);//设置可以显示的最大数值 该数值之后的会合并为其他
            }else {
                rl_bt.setVisibility(View.GONE);
                qj_r4.setVisibility(View.GONE);
                ll_shang.setVisibility(View.GONE);
                ll_xia.setVisibility(View.GONE);
            }

if(majorgkBean.getMajorJobMenNumList()!=null&&majorgkBean.getMajorJobMenNumList().size()>0){
    List<MajorgkBean.MajorJobMenNumListBean> majorJobMenNumList = majorgkBean.getMajorJobMenNumList();
    Collections.reverse(majorJobMenNumList);
    XinziZhuangKAdapter xinziZhuangKAdapter=new XinziZhuangKAdapter(majorJobMenNumList,getContext());
    xzzk_list.setAdapter(xinziZhuangKAdapter);
    qj_sv.smoothScrollTo(0,0);
            }
            if(majorgkBean.getAverageSalary()!=null&&majorgkBean.getAverageSalary().size()>0){
                ZhiMaScoreViewXinzi zhiMaScoreView=new ZhiMaScoreViewXinzi(MyApp.context);
                List<Integer> listfen=new ArrayList<>();
                listfen.add(0);
                listfen.add(0);
                listfen.add(0);
                listfen.add(0);
                listfen.add(0);
                listfen.add(0);
                for (int i = 0; i <majorgkBean.getAverageSalary().size(); i++) {
                    listfen.set(i,majorgkBean.getAverageSalary().get(i).getAveragesalary());
                }
                Integer max = Collections.max(listfen);
                if(max==0){
                    listfen.clear();
                    listfen.add(0);
                    listfen.add(0);
                    listfen.add(0);
                    listfen.add(0);
                    listfen.add(0);
                    listfen.add(0);
                    zhiMaScoreView.setMaxScore(100);
                }else {
                    zhiMaScoreView.setMaxScore(Collections.max(listfen)+1000);
                }
                zhiMaScoreView.setlistfen(listfen);
                zhiMaScoreView.setMinScore(0);
                rl_pjxz.addView(zhiMaScoreView);

            }


        }

    }

    @Override
    public void MajorgkFail(String msg) {

    }

    class Times {
        double hour;
        String text;
    }
}
