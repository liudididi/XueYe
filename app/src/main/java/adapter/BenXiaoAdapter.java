package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.login_demo.R;

import java.util.List;

import bean.SelectMajorBean;
import untils.ListViewForScrollView;

/**
 * Created by 地地 on 2018/3/23.
 * 邮箱：461211527@qq.com.
 */

public class BenXiaoAdapter extends BaseAdapter {

    private List<SelectMajorBean.ChildBeanX>  list;
    private Context context;

    public BenXiaoAdapter(List<SelectMajorBean.ChildBeanX> list, Context context) {
        this.list = list;
        this.context = context;
        for (int i = 0; i <list.size() ; i++) {
            if(i==0){
                list.get(i).zt=true;
            }else {
                list.get(i).zt=false;
            }

        }
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
            convertView=View.inflate(context, R.layout.xiaolistitem,null);
        }
        final TextView xiao_tv=convertView.findViewById(R.id.xiao_tv);
        final ListViewForScrollView list_san=convertView.findViewById(R.id.list_san);
        final ImageView xiaojt_img=convertView.findViewById(R.id.xiaojt_img);
        BenSanAdapter benSanAdapter=new BenSanAdapter(list.get(position).getChild(),context);
        list_san.setAdapter(benSanAdapter);
        if(list.get(position).zt){
            list_san.setVisibility(View.VISIBLE);
            xiaojt_img.setImageResource(R.drawable.xia);
        }else {
            list_san.setVisibility(View.GONE);
            xiaojt_img.setImageResource(R.drawable.you);
        }
        xiao_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(list.get(position).zt){
                 list.get(position).zt=false;
              }else {
                 list.get(position).zt=true;
             }
             notifyDataSetChanged();
            }
        });
        xiao_tv.setText(list.get(position).getMajorName());
        return convertView;
    }

}
