package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.login_demo.R;
import com.example.login_demo.TuiJianSchoolActivity;

import java.util.ArrayList;
import java.util.List;

import base.BaseApi;
import bean.CanSchoolBean3;
import untils.CircleProgressView;
import untils.FlowLayout;
import untils.NetUtil;

/**
 * Created by 祝文 on 2018/1/28.
 */

public class MoreSchool_Adapter extends RecyclerView.Adapter<MoreSchool_Adapter.MyViewHolder> {
   private  ArrayList<CanSchoolBean3> list;
   private Context context;

    public MoreSchool_Adapter(ArrayList<CanSchoolBean3> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.moreschool_item, null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
       // Glide.with(context).load(BaseApi.ImgApi+list.get(position).getImgurl()).into(holder.moreschool_iv);

        Glide.with(context).load(BaseApi.ImgApi + list.get(position).getImgurl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.moreschool_iv) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.moreschool_iv.setImageDrawable(circularBitmapDrawable);
            }
        });

        holder.more_name.setText(list.get(position).getName());
        holder.more_address.setText(list.get(position).getAddress());

         String two = list.get(position).getTwo();
        String nine = list.get(position).getNine();
        String defense_student = list.get(position).getDefense_student();
        String graduate = list.get(position).getGraduate();
        String recruit = list.get(position).getRecruit();
        String preeminent_plan = list.get(position).getPreeminent_plan();
        String com_rank = list.get(position).getCom_rank();
        if(com_rank!=null)
        {
            holder.more_zh.setText(com_rank);
        }
        else
        {
            holder.more_zh.setText("暂无数据");
        }

        List<String> fujialist=new ArrayList<>();
        if(two!=null&&two.length()>2){
            fujialist.add(two);
        }
        if(nine!=null&&nine.length()>2){
            fujialist.add(nine);
        }
        if(defense_student!=null&&defense_student.length()>2){
            fujialist.add(defense_student);
        }

        if(recruit!=null&&recruit.length()>2){
            fujialist.add(recruit);
        }
        if(graduate!=null&&graduate.length()>2){
            fujialist.add(graduate);
        }
        if(preeminent_plan!=null&&preeminent_plan.length()>2){
            fujialist.add(preeminent_plan);
        }

        if(fujialist.size()>0&&fujialist!=null){
            holder.fl.setZyListData(fujialist);
        }else {
            fujialist.add("暂无信息");
            holder.fl.setZyListData(fujialist);
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent=new Intent(context, SchoolDetailActivity.class);
                intent.putExtra("schoolname",list.get(position).getName());
                context.startActivity(intent);*/
                   if(NetUtil.isNetworkAvailable(context)==false){
                       Toast.makeText(context, "当前无网络", Toast.LENGTH_SHORT).show();
                       return;
                   }
                Intent intent=new Intent(context, TuiJianSchoolActivity.class);
                CircleProgressView.mPaintColor= Color.RED;
                CircleProgressView.mTextColor=Color.RED;
                intent.putExtra("schoolname",list.get(position).getName());
                intent.putExtra("pici",list.get(position).getTime());
                intent.putExtra("schoolurl",BaseApi.ImgApi+list.get(position).getImgurl());
                 context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView moreschool_iv;
        private final TextView more_name;
        private final TextView more_address;
        private final TextView more_zh;
        private  View view;
        private FlowLayout fl;

        public MyViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            moreschool_iv = itemView.findViewById(R.id.moreschool_iv);
            more_name = itemView.findViewById(R.id.more_name);
            more_address = itemView.findViewById(R.id.more_address);
            more_zh = itemView.findViewById(R.id.more_zh);
            fl = itemView.findViewById(R.id.fl);

        }
    }
}
