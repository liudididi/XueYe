package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.login_demo.NumActivity;
import com.example.login_demo.R;

import java.util.List;

import bean.NumBean;

/**
 * Created by 祝文 on 2018/3/31.
 */

public class NumAdapter extends RecyclerView.Adapter<NumAdapter.ViewHolder> {
    private List<NumBean.MajorsBean> list;
    private Context context;
    private String schoolname;

    public NumAdapter(List<NumBean.MajorsBean> list, Context context, String schoolname) {
        this.list = list;
        this.context = context;
        this.schoolname = schoolname;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.num_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.school_enroll_tv1.setText(list.get(position).getMajor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,NumActivity.class);
                intent.putExtra("schoolname",schoolname);
                intent.putExtra("major",list.get(position).getMajor());
                intent.putExtra("xiabiao",position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list.size()>=5)
        {
            return 5;
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView school_enroll_tv1;

        public ViewHolder(View itemView) {
            super(itemView);
            school_enroll_tv1 = itemView.findViewById(R.id.school_enroll_tv1);
        }
    }
}
