package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_demo.MajorDetailActivity;
import com.example.login_demo.R;

import java.util.List;

import bean.TuiJianBean;

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
    public View getView(final int i, View inflate, ViewGroup viewGroup) {
        if(inflate==null){
            inflate = LayoutInflater.from(context).inflate(R.layout.tuijian_item, null);
        }
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(i).getMajorId()!=null)
                {
                    Intent intent=new Intent(context, MajorDetailActivity.class);
                    intent.putExtra("majorid",list.get(i).getMajorId()+"");
                    intent.putExtra("major",list.get(i).getMajor());
                    context.startActivity(intent);
                }
                else

                {
                    Toast.makeText(context, "暂无专业详情", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        //final CircleProgressView cpv=inflate.findViewById(R.id.cpv);
        TextView tv_zy= inflate.findViewById(R.id.tv_zy);
        TextView tv_tj_zhuanye= inflate.findViewById(R.id.tv_tj_zhuanye);
        TextView tv_zd_zhuanye= inflate.findViewById(R.id.tv_zd_zhuanye);
        TextView tv_zy_liebei= inflate.findViewById(R.id.tv_zy_liebei);
        TextView tv_year_fen= inflate.findViewById(R.id.tv_year_fen);
        TextView tv_zy_jihua= inflate.findViewById(R.id.tv_zy_jihua);
        TextView tv_year_pici= inflate.findViewById(R.id.tv_year_pici);


        //重点专业
        String importantFlag =list.get(i).getImportantFlag();
        if(importantFlag!=null)
        {
            tv_zd_zhuanye.setText(importantFlag);
            tv_zd_zhuanye.setVisibility(View.VISIBLE);
        }

        //推荐专业
        String featureFlag =list.get(i).getFeatureFlag();
        if(featureFlag!=null)
        {
            tv_tj_zhuanye.setText(featureFlag);
            tv_tj_zhuanye.setVisibility(View.VISIBLE);
        }
        else
        {
            tv_tj_zhuanye.setVisibility(View.GONE);
        }

        if(importantFlag!=null&&featureFlag!=null)
        {
            tv_zd_zhuanye.setText(importantFlag);
            tv_zd_zhuanye.setVisibility(View.VISIBLE);
            tv_tj_zhuanye.setText(featureFlag);
            tv_tj_zhuanye.setVisibility(View.VISIBLE);
        }





        String majorType = list.get(i).getMajorType();
        if(majorType!=null)
        {
            tv_zy_liebei.setText(majorType);
        }
        else
        {
            tv_zy_liebei.setText("数据整理中");
        }

        String year = list.get(i).getYear();
        String score = list.get(i).getScore();
        if(year!=null&&score!=null)
        {
            tv_year_fen.setText(year+"年录取最低分"+score);
        }
        else
        {
            tv_year_fen.setText( "数据整理中" );
        }

        tv_zy.setText(list.get(i).getMajor());
        String time = list.get(i).getTime();
        if(time!=null)
        {
            tv_year_pici.setText(time);
        }else
        {
            tv_year_pici.setText("数据整理中");
        }

        String planYear = list.get(i).getPlanYear();
        String number = list.get(i).getNumber();
        if(planYear!=null&&number!=null)
        {
            tv_zy_jihua.setText(planYear+"计划招生:"+number+"人");
        }
        if(number==null)
        {
            tv_zy_jihua.setText(planYear+"计划招生:0人");
        }

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


        /*int gai= (int) ((list.get(i).getGai())*100);
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
        animator.start();*/
        return inflate;
    }
}
