package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.List;

import bean.MajorgkBean;

/**
 * Created by 地地 on 2018/3/28.
 * 邮箱：461211527@qq.com.
 */

public class XinziZhuangKAdapter extends BaseAdapter {
    private List<MajorgkBean.MajorJobMenNumListBean> list;
    private Context context;

    public XinziZhuangKAdapter(List<MajorgkBean.MajorJobMenNumListBean> list, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context, R.layout.xzzk_item,null);
        }
        TextView xzzk_xinzi= convertView.findViewById(R.id.xzzk_xinzi);
        TextView xzzk_bfb= convertView.findViewById(R.id.xzzk_bfb);
        TextView tv_num= convertView.findViewById(R.id.tv_num);
        int i = position + 1;
        if(i<=3){
            tv_num.setTextColor(Color.WHITE);
            tv_num.setBackgroundResource(R.drawable.back_circleorging);
        }else {
            tv_num.setBackgroundResource(R.drawable.back_circlebai);
        }
        tv_num.setText(i+"");
        ProgressBar xzzk_pb= convertView.findViewById(R.id.xzzk_pb);
        double v = list.get(position).getProportion() * 100;
        xzzk_bfb.setText((int) v+"%");
        xzzk_pb.setProgress((int) v);
        xzzk_xinzi.setText(list.get(position).getArea()+"");
        return convertView;
    }
}
