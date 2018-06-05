

package adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_demo.MajorDetailActivity;
import com.example.login_demo.MyApp;
import com.example.login_demo.R;
import com.example.login_demo.TuiJianSchoolActivity;

import java.util.List;

import bean.Advanced_YX_Bean;
import untils.CircleProgressView;
import untils.SPUtils;

/**
 * Created by 祝文 on 2018/3/9.
 */

public class Accurate_Yx_Adapter extends RecyclerView.Adapter<Accurate_Yx_Adapter.MyViewHolder> {

    private Context context;
    private List<Advanced_YX_Bean> list;
    private String biaoshi;
    private final boolean vip;

    public Accurate_Yx_Adapter(Context context, List<Advanced_YX_Bean> list,String biaoshi ) {
        this.context = context;
        this.list = list;
        this.biaoshi=biaoshi;
        vip = (boolean) SPUtils.get(MyApp.context, "VIP", false);
     }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.accurate_yx_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        list.get(position).setSelecked(true);
             final List<Advanced_YX_Bean.MajorBean> major = list.get(position).getMajor();
                 //院校概率
                String yxGai = list.get(position).getYxGai();
                //final String zyGai = major.get(0).getZyGai();
        if(major!=null&&major.size()>0){
            String major1 = (String) major.get(0).getMajor();
            if(major1!=null)
            {
                holder.tv_zy_name.setText(major1);

            }
            else
            {
                holder.tv_zy_name.setText("暂无数据");
            }
        }
                //String  substring1 = null;
                String  substring =null;
                if(yxGai!=null)
                {
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
               //专业概率
                if(substring1!=null)
                {
                    if(biaoshi.equals("冲刺"))
                    {
                        holder.tv_zhuanye.setText(substring1+"%");
                     }
                  else if(biaoshi.equals("稳妥"))
                    {
                        holder.tv_zhuanye.setText(substring1+"%");
                        holder.tv_zhuanye.setTextColor(context.getResources().getColor(R.color.zhu1));
                    }
                    else
                    {
                        holder.tv_zhuanye.setText(substring1+"%");
                        holder.tv_zhuanye.setTextColor(context.getResources().getColor(R.color.lue));
                    }

                }
                else
                {
                    if(biaoshi.equals("冲刺"))
                    {
                        holder.tv_zhuanye.setText("0"+"%");
                    }
                    else if(biaoshi.equals("稳妥"))
                    {
                        holder.tv_zhuanye.setText("0"+"%");
                        holder.tv_zhuanye.setTextColor(context.getResources().getColor(R.color.zhu1));
                    }
                    else
                    {
                        holder.tv_zhuanye.setText("0"+"%");
                        holder.tv_zhuanye.setTextColor(context.getResources().getColor(R.color.lue));
                    }


                }*/
                //专业名称
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
                if(info!=null)
                {
                    String  ranking = info.getRanking();
                    //全国排名
                    if(ranking!=null&&ranking.length()>0)
                    {
                        holder.tv_paiming.setText(ranking);
                    }
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
                }


             holder.ll_zhankai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(list.get(position).isSelecked())
                    {
                        holder.iv_next.setVisibility(View.GONE);
                        holder.tv_qb.setVisibility(View.GONE);
                        holder.iv_shang.setVisibility(View.VISIBLE);
                        holder.tv_sq.setVisibility(View.VISIBLE);
                        holder.rv_zhuanye.setVisibility(View.VISIBLE);
                        if(major.size()>1&&major!=null)
                        {
                            holder.rv_zhuanye.setVisibility(View.VISIBLE);
                            YX_ZhuanYe_Adapter yx_zhuanYe_adapter=new YX_ZhuanYe_Adapter(major,context,biaoshi);
                            holder.rv_zhuanye.setLayoutManager(new LinearLayoutManager(context));
                            holder.rv_zhuanye.setAdapter(yx_zhuanYe_adapter);
                         }
                         list.get(position).setSelecked(false);
                     }
                    else
                    {
                        holder.iv_next.setVisibility(View.VISIBLE);
                        holder.tv_qb.setVisibility(View.VISIBLE);
                        holder.iv_shang.setVisibility(View.GONE);
                        holder.tv_sq.setVisibility(View.GONE);
                        holder.rv_zhuanye.setVisibility(View.GONE);
                        list.get(position).setSelecked(true);
                     }
                }
            });
            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, TuiJianSchoolActivity.class);
                    intent.putExtra("schoolname",list.get(position).getName());
                    intent.putExtra("pici",list.get(position).getTime());
                    intent.putExtra("EFC",true);
                    CircleProgressView.mPaintColor= Color.RED;
                    CircleProgressView.mTextColor= Color.RED;
                    context.startActivity(intent);
                }
            });
            holder.rl_zy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, MajorDetailActivity.class);
                    if(major.get(0).getMajorId()!=null)
                    {
                        String majorId = (String) major.get(0).getMajorId();
                        intent.putExtra("majorid",majorId);
                        String major2 = (String) major.get(0).getMajor();
                        intent.putExtra("major",major2);
                        context.startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(context, "数据整理中", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            if(vip)
            {
                holder.rl_zy.setVisibility(View.VISIBLE);
                holder.cpv.setVisibility(View.VISIBLE);
                holder.iv_vip.setVisibility(View.GONE);
                RelativeLayout.LayoutParams Params =  (RelativeLayout.LayoutParams)holder.tv_name.getLayoutParams();
                Params.width =620;
                holder.tv_name.setLayoutParams(Params);
                if(name.length()>=9)
                {
                    String substring2 = name.substring(0, 8);
                    holder.tv_name.setText(substring2+"..");
                }
                else
                {
                    holder.tv_name.setText(name);
                }
                if(num!=null)
                {
                    holder.tv_num.setText("("+num+"个推荐专业)");
                }
                else
                {
                    holder.tv_num.setText("(0个推荐专业)");
                }

            }
            else
            {
                holder.rl_zy.setVisibility(View.GONE);
                holder.cpv.setVisibility(View.GONE);
                holder.iv_vip.setVisibility(View.VISIBLE);
                holder.tv_num.setText("(VIP查看推荐专业)");
                RelativeLayout.LayoutParams Params =  (RelativeLayout.LayoutParams)holder.tv_name.getLayoutParams();
                Params.width =450;
                holder.tv_name.setLayoutParams(Params);
                if(name.length()>6)
                {
                    String substring2 = name.substring(0, 6);
                    holder.tv_name.setText(substring2+"..");
                }
                else
                {
                    holder.tv_name.setText(name);
                }
            }
        }
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        if(vip)
        {
            return list.size();
        }
        else {
            if(list.size()>=5)
            {
                return 5;
            }
            else
            {
                return list.size();
            }
        }
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
        private final LinearLayout ll_zhankai;
        private final ImageView iv_next;
        private final TextView tv_qb;
        private final ImageView iv_shang;
        private final TextView tv_sq;
        private final RecyclerView rv_zhuanye;
        private final RelativeLayout rl;
        private final RelativeLayout rl_zy;
        private final ImageView iv_vip;

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
            ll_zhankai = itemView.findViewById(R.id.ll_zhankai);
            iv_next = itemView.findViewById(R.id.iv_next);
            tv_qb = itemView.findViewById(R.id.tv_qb);
            iv_shang = itemView.findViewById(R.id.iv_shang);
            tv_sq = itemView.findViewById(R.id.tv_sq);
            rv_zhuanye = itemView.findViewById(R.id.lv_zhuanye);
            rl = itemView.findViewById(R.id.rl);
            rl_zy = itemView.findViewById(R.id.rl_zy);
            iv_vip = itemView.findViewById(R.id.iv_vip);
        }
    }
}
