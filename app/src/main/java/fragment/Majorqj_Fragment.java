package fragment;

import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.login_demo.MajorDetailActivity;
import com.example.login_demo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapter.XinziZhuangKAdapter;
import base.Basefragment;
import bean.MajorgkBean;
import presenter.MajorgkPresent;
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
        majorgkPresent = new MajorgkPresent(this);
        majorgkPresent.getMajorgk(MajorDetailActivity.majorid);

    }

    @Override
    public void MajorgkSusccess(MajorgkBean majorgkBean) {

        if(majorgkBean!=null){
            if(majorgkBean.getEmployment_prospects()!=null){
                //就业前景
                qj_tvqj.setText(majorgkBean.getEmployment_prospects());
            }
            if(majorgkBean.getJobSituation()!=null){
                qj_pro_address.setText(majorgkBean.getJobSituation().getPro_address());
                qj_rank.setText(majorgkBean.getJobSituation().getRank());
            }

if(majorgkBean.getSalaryScale()!=null&&majorgkBean.getSalaryScale().size()>0){
    XinziZhuangKAdapter xinziZhuangKAdapter=new XinziZhuangKAdapter(majorgkBean.getSalaryScale(),getContext());
    xzzk_list.setAdapter(xinziZhuangKAdapter);
    qj_sv.smoothScrollTo(0,0);
            }
            if(majorgkBean.getAverageSalary()!=null&&majorgkBean.getAverageSalary().size()>0){
                ZhiMaScoreViewXinzi zhiMaScoreView=new ZhiMaScoreViewXinzi(getActivity());
                List<Integer> listfen=new ArrayList<>();
                for (int i = 0; i <5 ; i++) {
                    listfen.add(majorgkBean.getAverageSalary().get(i).getAveragesalary());
                }
                Integer max = Collections.max(listfen);
                if(max==0){
                    listfen.clear();
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
}
