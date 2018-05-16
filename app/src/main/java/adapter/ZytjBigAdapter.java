package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.List;

import bean.EFCBean;
import untils.ListViewForScrollView;

/**
 * Created by 地地 on 2018/5/16.
 * 邮箱：461211527@qq.com.
 */

public class ZytjBigAdapter extends BaseAdapter {

   private List<EFCBean.MajortwoBean> list;
   private Context context;

    public ZytjBigAdapter(List<EFCBean.MajortwoBean> list, Context context) {
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
        if(convertView!=null){
            convertView=View.inflate(context, R.layout.zytjbigitem,null);
        }
        final ListViewForScrollView list_two= convertView.findViewById(R.id.list_two);
        final List<EFCBean.MajortwoBean.ThreeListBean> threeList = list.get(position).getThreeList();
        if(threeList.size()>0&&threeList!=null){
            ZytjSmall_adapter zytjSmall_adapter=new ZytjSmall_adapter(threeList,context);
            list_two.setAdapter(zytjSmall_adapter);
        }



        TextView tv_major= convertView.findViewById(R.id.tv_major);
        RatingBar rb_start= convertView.findViewById(R.id.rb_start);
        final ImageView img_next= convertView.findViewById(R.id.img_next);
        img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(list_two.getVisibility()==View.GONE){
                    list_two.setVisibility(View.VISIBLE);
                    img_next.setImageResource(R.drawable.right_arrow);
                }else {
                    list_two.setVisibility(View.GONE);
                    img_next.setImageResource(R.drawable.next);
                }
            }
        });
        rb_start.setNumStars(list.get(position).getStarnum());
        tv_major.setText(list.get(position).getMajorName());
        return convertView;
    }
}
