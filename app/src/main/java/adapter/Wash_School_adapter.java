package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.login_demo.More_SchoolActivity;
import com.example.login_demo.R;
import com.example.login_demo.SchoolDetailActivity;

import java.util.List;

import base.BaseApi;
import bean.CanSchoolBean2;
import untils.NetUtil;

/**
 * Created by 地地 on 2018/1/21.
 * 邮箱：461211527@qq.com.
 */

public class Wash_School_adapter  extends RecyclerView.Adapter{
     private List<CanSchoolBean2> list;
     private Context context;
     private int a=0;
     private int b=1;
    public Wash_School_adapter(List<CanSchoolBean2> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder=null;
        switch (viewType)
        {
            case 0:
                view = View.inflate(context, R.layout.more_item, null);
                holder=new School_viewHoder1(view);
                break;
            case 1:
                view = View.inflate(context, R.layout.my_wish_school_item, null);
                        holder=new School_viewHoder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position))
        {
            case 0:
                School_viewHoder1 school_viewHoder1= (School_viewHoder1) holder;
                school_viewHoder1.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(NetUtil.isNetworkAvailable(context)==false){
                            Toast.makeText(context, "当前无网络", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        context.startActivity(new Intent(context, More_SchoolActivity.class));
                     }
                });
                break;
            case 1:
                final School_viewHoder school_viewHoder= (School_viewHoder) holder;
                school_viewHoder.item_school_name.setText(list.get(position).getName());
                school_viewHoder.item_school_address.setText(list.get(position).getAddress()+"  "+list.get(position).getTypeRank());

              //  Glide.with(context).load(BaseApi.ImgApi+list.get(position).getImgurl()).into(school_viewHoder.item_school_icon);
                Glide.with(context).load(BaseApi.ImgApi+list.get(position).getImgurl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(school_viewHoder.item_school_icon) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        school_viewHoder.item_school_icon  .setImageDrawable(circularBitmapDrawable);
                    }
                });

                school_viewHoder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(NetUtil.isNetworkAvailable(context)==false){
                            Toast.makeText(context, "当前无网络", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent=new Intent(context, SchoolDetailActivity.class);
                        intent.putExtra("schoolname",list.get(position).getName());
                        context.startActivity(intent);
                    }
                });
                break;
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==4)
        {
            return a;
        }
        else
        {
            return b;
        }
    }



    class  School_viewHoder extends  RecyclerView.ViewHolder {

       ImageView item_school_icon;
         TextView item_school_name;
          TextView item_school_address;
          View view;

       public School_viewHoder(View itemView) {
           super(itemView);
           item_school_icon = itemView.findViewById(R.id.item_school_icon);
           item_school_name = itemView.findViewById(R.id.item_school_name);
           item_school_address = itemView.findViewById(R.id.item_school_address);
           view=itemView;
       }
   }
    class  School_viewHoder1 extends  RecyclerView.ViewHolder {

        public School_viewHoder1(View itemView) {
            super(itemView);
        }
    }
}
