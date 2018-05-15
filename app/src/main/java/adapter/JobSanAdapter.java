package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_demo.JobDetailsActivity;
import com.example.login_demo.R;

import java.util.List;

import bean.MoreJobBean;

/**
 * Created by 地地 on 2018/3/23.
 * 邮箱：461211527@qq.com.
 */

public class JobSanAdapter extends BaseAdapter {

    private List<MoreJobBean.JobListTwoBean.JobListThreeBean> list;
    private Context context;



    public JobSanAdapter(List<MoreJobBean.JobListTwoBean.JobListThreeBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context, R.layout.sanlistitem,null);
        }
        final TextView san_tv=convertView.findViewById(R.id.san_tv);
        san_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, JobDetailsActivity.class);
                intent.putExtra("jobname",list.get(position).getJob());
                context.startActivity(intent);
            }
        });
        san_tv.setText(list.get(position).getJob());
        return convertView;
    }

}
