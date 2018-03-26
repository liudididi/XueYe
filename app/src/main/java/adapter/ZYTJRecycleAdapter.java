package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.List;

import bean.ZYTJBean;

/**
 * Created by 祝文 on 2018/3/24.
 */

public class ZYTJRecycleAdapter extends RecyclerView.Adapter<ZYTJRecycleAdapter.MyViewHolder> {
    private List<ZYTJBean.MajorinfoBean> list;
    private Context context;

    public ZYTJRecycleAdapter(List<ZYTJBean.MajorinfoBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.zytj_item, null);

        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String major = list.get(position).getMajor();

        holder.tv_zytj.setText(major);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_zytj;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_zytj = itemView.findViewById(R.id.tv_zytj);
        }
    }
}
