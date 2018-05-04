package adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_demo.MajorDetailActivity;
import com.example.login_demo.R;
import com.example.login_demo.SchoolDetailActivity;
import com.example.login_demo.TuiJianSchoolActivity;

import java.util.List;

import bean.Advanced_YX_Bean;
import untils.CircleProgressView;

/**
 * Created by 祝文 on 2018/3/9.
 */

public class Accurate_Zy_Yx_Adapter extends RecyclerView.Adapter<Accurate_Zy_Yx_Adapter.MyViewHolder> {

    private Context context;
    private List<Advanced_YX_Bean> list;

    public Accurate_Zy_Yx_Adapter(Context context, List<Advanced_YX_Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.accurate_zy_yx_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

          final List<Advanced_YX_Bean.MajorBean> major = list.get(position).getMajor();

                //院校概率
                String yxGai = list.get(position).getYxGai();
                final String zyGai = major.get(0).getZyGai();
                String major1 = major.get(0).getMajor();
                String  substring1 = null;
                String  substring =null;
                if(yxGai.length()>=4)
                {
                    substring = yxGai.substring(2, 4);
                }
               if(yxGai.length()<4)
               {
                   substring = yxGai.substring(2, 3);
                   if(substring.length()==1)
                   {
                       substring+="0";
                   }
               }
               /*
               if(zyGai!=null)
               {
                   if(zyGai.length()>=4)
                   {
                       substring1 = zyGai.substring(2, 4);
                   }
                   if(zyGai.length()<4)
                   {
                       substring1 = zyGai.substring(2, 3);
                       if(substring1.length()==1)
                       {
                           substring1+="0";
                       }
                   }
               }
               else
               {
                   substring1="0";
               }

               //专业概率
                holder.tv_zhuanye.setText(substring1+"%");*/
                //专业名称
                if (major1!=null)
                {
                    holder.tv_zy_name.setText(major1);

                }
                else
                {
                    holder.tv_zy_name.setText("暂无数据");

                }
                //进度条从0到100
                final ValueAnimator animator = ValueAnimator.ofFloat(0, Integer.parseInt(substring));
                animator.setDuration(100);
                animator.setInterpolator(new LinearInterpolator());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float current = (float) animation.getAnimatedValue();
                        holder.cpv.setmCurrent((int) current);
                    }
                });
                animator.start();
                holder.cpv.setOnLoadingCompleteListener(new CircleProgressView.OnLoadingCompleteListener() {
                    @Override
                    public void complete() {
                        Toast.makeText(context, "加载完成", Toast.LENGTH_SHORT).show();
                    }

                });
            //专业个数
            String num = list.get(position).getNum();
            //大学名称
            String name = list.get(position).getName();
        Advanced_YX_Bean.InfoBean info = list.get(position).getInfo();

                //全国排名
                String  ranking = info.getRanking();
                holder.tv_paiming.setText(ranking);
                if(info.getNine()!=null&&info.getNine().length()>1)
                {
                    holder.tv_nine.setText(info.getNine());
                    holder.tv_nine.setVisibility(View.VISIBLE);
                }
                 else
                {
                    holder.tv_nine.setVisibility(View.GONE);
                }
                if(info.getTwo()!=null&&info.getTwo().length()>1)
                {
                    holder.tv_two.setText(info.getTwo());
                    holder.tv_two.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.tv_two.setVisibility(View.GONE);
                }
                if(info.getGraduate()!=null&&info.getGraduate().length()>1)
                {
                    holder.tv_graduate.setText(info.getGraduate());
                    holder.tv_graduate.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.tv_graduate.setVisibility(View.GONE);
                }
               if(info.getRecruit()!=null&&info.getRecruit().length()>1)
               {
                   holder.tv_recruit.setText(info.getRecruit());
                   holder.tv_recruit.setVisibility(View.VISIBLE);
               }
               else
               {
                   holder.tv_recruit.setVisibility(View.GONE);
               }



            holder.tv_name.setText(name);

            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, TuiJianSchoolActivity.class);
                    CircleProgressView.mPaintColor= Color.RED;
                    CircleProgressView.mTextColor= Color.RED;
                    intent.putExtra("schoolname",list.get(position).getName());
                    context.startActivity(intent);
                }
            });
            holder.rl_zy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, MajorDetailActivity.class);
                    intent.putExtra("majorid",major.get(0).getMajorId());
                    intent.putExtra("major",major.get(0).getMajor());
                    context.startActivity(intent);
                }
            });
        }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CircleProgressView cpv;
        private final TextView tv_name;
        private final TextView tv_num;
        private final TextView tv_paiming;
        //private final TextView tv_zhuanye;
        private final TextView tv_zy_name;
        private final TextView tv_nine;
        private final TextView tv_two;

        private final TextView tv_graduate;
        private final TextView tv_recruit;
        private final RelativeLayout rl;
        private final RelativeLayout rl_zy;


        public MyViewHolder(View itemView) {
            super(itemView);

            cpv = itemView.findViewById(R.id.cpv);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_paiming = itemView.findViewById(R.id.tv_paiming);
            //tv_zhuanye = itemView.findViewById(R.id.tv_zhuanye);
            tv_zy_name = itemView.findViewById(R.id.tv_zy_name);

            tv_nine = itemView.findViewById(R.id.tv_nine);
            tv_two = itemView.findViewById(R.id.tv_two);

            tv_graduate = itemView.findViewById(R.id.tv_graduate);
            tv_recruit = itemView.findViewById(R.id.tv_recruit);

            rl = itemView.findViewById(R.id.rl);
            rl_zy = itemView.findViewById(R.id.rl_zy);

        }
    }
}
