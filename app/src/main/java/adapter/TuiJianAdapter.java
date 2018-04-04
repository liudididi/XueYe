package adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.List;

import bean.TuiJianBean;
import untils.CircleProgressView;

/**
 * Created by 祝文 on 2018/4/3.
 */

public class TuiJianAdapter extends BaseAdapter{
    private  List<TuiJianBean.EfcRecommendMajorEntityBean> list;
    private Context context;

    public TuiJianAdapter(List<TuiJianBean.EfcRecommendMajorEntityBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View inflate, ViewGroup viewGroup) {
        if(inflate==null){
            inflate = LayoutInflater.from(context).inflate(R.layout.tuijian_item, null);
        }
        final CircleProgressView cpv=inflate.findViewById(R.id.cpv);
        TextView tv_zy= inflate.findViewById(R.id.tv_zy);
        TextView tv_tj_zhuanye= inflate.findViewById(R.id.tv_tj_zhuanye);
        TextView tv_zd_zhuanye= inflate.findViewById(R.id.tv_zd_zhuanye);
        TextView tv_zy_liebei= inflate.findViewById(R.id.tv_zy_liebei);
        TextView tv_year_fen= inflate.findViewById(R.id.tv_year_fen);
        //重点专业
        String importantFlag = list.get(i).getImportantFlag();
        if(importantFlag!=null)
        {
            tv_zd_zhuanye.setText(importantFlag);
            tv_zd_zhuanye.setVisibility(View.VISIBLE);
        }
        //推荐专业
        String featureFlag = list.get(i).getFeatureFlag();
        if(featureFlag!=null)
        {
            tv_tj_zhuanye.setText(featureFlag);
            tv_tj_zhuanye.setVisibility(View.VISIBLE);
        }
        tv_zy_liebei.setText(list.get(i).getMajorType());

        tv_year_fen.setText(list.get(i).getYear()+"年录取最低分"+list.get(i).getScore());
        tv_zy.setText(list.get(i).getMajor());
    /*    double gai =  (list.get(i).getGai());
        String s = String.valueOf(gai);
        String substring=null;
        if(s.length()<=3)
        {
            substring= s.substring(1,2);
            substring+="0";
        }else {
            substring= s.substring(2, 3);
        }*/


        int gai= (int) ((list.get(i).getGai())*100);
        String substring=null;
        if(gai<=1){
            substring="1";
        }else {
            String s = String.valueOf(gai);

            if(s.length()<2)
            {
                substring= s.substring(0,1);

            }else {
                substring= s.substring(0,2);
            }
        }


        //进度条从0到100
        final ValueAnimator animator = ValueAnimator.ofFloat(0, Integer.parseInt(substring));
        animator.setDuration(100);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float current = (float) animation.getAnimatedValue();
                 cpv.setmCurrent((int) current);
            }
        });
        animator.start();
        return inflate;
    }
}
