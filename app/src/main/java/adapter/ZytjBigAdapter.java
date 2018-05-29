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
import untils.MyListView;

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
        for (int i = 0; i < list.size(); i++) {
            list.get(i).zt=false;
        }
        if(list.size()==1){
            list.get(0).zt=true;
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
            convertView=View.inflate(context, R.layout.zytjbigitem,null);
        }
        final MyListView list_two= convertView.findViewById(R.id.list_two);
        final List<EFCBean.MajortwoBean.ThreeListBean> threeList = list.get(position).getThreeList();
        if(threeList.size()>0&&threeList!=null){
            ZytjSmall_adapter zytjSmall_adapter=new ZytjSmall_adapter(threeList,context);
            list_two.setAdapter(zytjSmall_adapter);
        }
        final ImageView img_next= convertView.findViewById(R.id.img_next);

        if(list.get(position).zt==true){
            list_two.setVisibility(View.VISIBLE);
            img_next.setImageResource(R.drawable.next);

        }else {
            list_two.setVisibility(View.GONE);
            img_next.setImageResource(R.drawable.right_arrow);

        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list_two.getVisibility()==View.GONE){
                    list_two.setVisibility(View.VISIBLE);
                      img_next.setImageResource(R.drawable.next);

                    list.get(position).zt=true;
                }else {
                    list_two.setVisibility(View.GONE);
                     img_next.setImageResource(R.drawable.right_arrow);

                    list.get(position).zt=false;
                }
            }
        });


        TextView tv_major= convertView.findViewById(R.id.tv_major);
        RatingBar rb_start= convertView.findViewById(R.id.rb_start);
        final ImageView img_one= convertView.findViewById(R.id.img_one);
        final ImageView img_two= convertView.findViewById(R.id.img_two);
        final ImageView img_three= convertView.findViewById(R.id.img_three);
        final ImageView img_four= convertView.findViewById(R.id.img_four);
        final ImageView img_five= convertView.findViewById(R.id.img_five);


        final int starnum = list.get(position).getStarnum();
        if(starnum==1){
            img_one.setVisibility(View.VISIBLE);
        }else  if(starnum==2){
            img_one.setVisibility(View.VISIBLE);
            img_two.setVisibility(View.VISIBLE);
        }
        else  if(starnum==3){
            img_one.setVisibility(View.VISIBLE);
            img_two.setVisibility(View.VISIBLE);
            img_three.setVisibility(View.VISIBLE);
        }
        else  if(starnum==4){
            img_one.setVisibility(View.VISIBLE);
            img_two.setVisibility(View.VISIBLE);
            img_three.setVisibility(View.VISIBLE);
            img_four.setVisibility(View.VISIBLE);
        }
        else  if(starnum==5){
            img_one.setVisibility(View.VISIBLE);
            img_two.setVisibility(View.VISIBLE);
            img_three.setVisibility(View.VISIBLE);
            img_four.setVisibility(View.VISIBLE);
            img_five.setVisibility(View.VISIBLE);
        }
        tv_major.setText(list.get(position).getMajorName());
        return convertView;
    }
}
