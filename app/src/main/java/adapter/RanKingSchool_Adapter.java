package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.login_demo.R;
import com.example.login_demo.SchoolDetailActivity;

import java.util.ArrayList;
import java.util.List;

import base.BaseApi;
import bean.RanKingSchoolBean;
import untils.FlowLayout;

/**
 * Created by 祝文 on 2018/1/30.
 */

public class RanKingSchool_Adapter extends RecyclerView.Adapter<RanKingSchool_Adapter.MyViewHolder> {
   private List<RanKingSchoolBean.ListBean> list;
   private Context context;

    public RanKingSchool_Adapter( List<RanKingSchoolBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void LoadMore(List<RanKingSchoolBean.ListBean> newlist) {
        if(list!=null){
            list.addAll(newlist);
            this.notifyDataSetChanged();
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rankingschool_item, null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.setIsRecyclable(false);
        //211
        String two = list.get(position).getTwo();
        //985
        String nine = list.get(position).getNine();
        //国防生
        String defense_student = list.get(position).getDefense_student();

        //研究生院
        String graduate = list.get(position).getGraduate();
        //自主招生
        String recruit = list.get(position).getRecruit();
        //卓越计划
        String preeminent_plan = list.get(position).getPreeminent_plan();

        String url = list.get(position).getUrl();
        String name = list.get(position).getName();
        String address = list.get(position).getAddress();
        String father = list.get(position).getFather();
        String ranking = list.get(position).getRanking();
        System.out.println("数据++++"+url);

        List<String> fujialist=new ArrayList<>();
        if(two!=null&&two.length()>2){
            fujialist.add(two);
        }
        if(nine!=null&&nine.length()>2){
            fujialist.add(nine);
        }
        if(graduate!=null&&graduate.length()>2){
            fujialist.add(graduate);
        }
        if(recruit!=null&&recruit.length()>2){
            fujialist.add(recruit);
        }
        if(defense_student!=null&&defense_student.length()>2){
            fujialist.add(defense_student);
        }
        if(preeminent_plan!=null&&preeminent_plan.length()>2){
            fujialist.add(preeminent_plan);
        }


        if(fujialist.size()>0&&fujialist!=null){
            holder.majoritem_flow.setZyListData(fujialist);
        }else {
            fujialist.add("暂无信息");
            holder.majoritem_flow.setZyListData(fujialist);
        }

        if(url!=null){
        //    Glide.with(context).load(BaseApi.ImgApi+url).into(holder.schoolitem_url);

            Glide.with(context).load(BaseApi.ImgApi + url).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.schoolitem_url) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.schoolitem_url.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
        holder.schoolitem_name.setText(name);
        holder.schoolitem_address.setText(address);
        holder.tv_father.setText(father);
        holder.schoolitem_typerank.setText(ranking);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SchoolDetailActivity.class);
                intent.putExtra("schoolname",list.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView schoolitem_url;
        private final TextView schoolitem_name;
        private final TextView schoolitem_address;
        private final TextView schoolitem_typerank;
        private final TextView tv_father;
        private final FlowLayout majoritem_flow;

        public MyViewHolder(View itemView) {
            super(itemView);
            schoolitem_url = itemView.findViewById(R.id.schoolitem_url);
            schoolitem_name = itemView.findViewById(R.id.schoolitem_name);
            schoolitem_address = itemView.findViewById(R.id.schoolitem_address);
            schoolitem_typerank = itemView.findViewById(R.id.schoolitem_typerank);
            majoritem_flow = itemView.findViewById(R.id.majoritem_flow);
            tv_father = itemView.findViewById(R.id.tv_father);

        }
    }
}
