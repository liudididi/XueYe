package adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_demo.MajorDetailActivity;
import com.example.login_demo.R;

import java.util.List;

import bean.Advanced_YX_Bean;

/**
 * Created by 祝文 on 2018/3/15.
 */

public class YX_ZhuanYe_Adapter extends RecyclerView.Adapter<YX_ZhuanYe_Adapter.MyViewHolder> {
    private List<Advanced_YX_Bean.MajorBean> list;
    private Context context;
    private String biaoshi;
    public YX_ZhuanYe_Adapter(List list, Context context,String biaoshi) {
        this.list = list;
        this.context = context;
        this.biaoshi=biaoshi;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.yx_zhuanye_item, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        String zyGai = (String) list.get(position+1).getZyGai();

        if(zyGai!=null)
        {
            String substring1=null;
            if(zyGai.length()>=4)
            {
                substring1 = zyGai.substring(2, 4);
            }
            if(zyGai.length()<4)
            {
                substring1 = zyGai.substring(2, 3);
            }
            //专业概率
            holder.tv_yx_zhuanye.setText(substring1+"%");

            if(biaoshi.equals("冲刺"))
            {
                holder.tv_yx_zhuanye.setText(substring1+"%");            }
            else if(biaoshi.equals("稳妥"))
            {
                holder.tv_yx_zhuanye.setText(substring1+"%");
                holder.tv_yx_zhuanye.setTextColor(context.getResources().getColor(R.color.zhu1));
            }
            else
            {
                holder.tv_yx_zhuanye.setText(substring1+"%");
                holder.tv_yx_zhuanye.setTextColor(context.getResources().getColor(R.color.lue));
            }
        }


        //专业名称
        String major = (String) list.get(position + 1).getMajor();

        holder.tv_yx_zy_name.setText(major);
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MajorDetailActivity.class);
                String majorId = (String) list.get(position + 1).getMajorId();
               if(majorId!=null)
                {
                    intent.putExtra("majorid",majorId);
                    String major1 = (String) list.get(position + 1).getMajor();
                    intent.putExtra("major",major1);
                    context.startActivity(intent);
                }
                else 
                {
                    Toast.makeText(context, "数据整理中", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size()-1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_yx_zhuanye;
        private final TextView tv_yx_zy_name;
        private final RelativeLayout rl;
        public MyViewHolder(View itemView) {
            super(itemView);

            tv_yx_zhuanye = itemView.findViewById(R.id.tv_yx_zhuanye);
            tv_yx_zy_name = itemView.findViewById(R.id.tv_yx_zy_name);
            rl = itemView.findViewById(R.id.rl);

        }
    }
}
