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
import com.example.login_demo.R;
import com.example.login_demo.SchoolDetailActivity;
import com.example.login_demo.TuiJianSchoolActivity;

import java.util.ArrayList;
import java.util.List;

import base.BaseApi;
import bean.CheckSchoolBean;
import untils.FlowLayout;
import untils.NetUtil;

/**
 * Created by 地地 on 2018/1/26.
 * 邮箱：461211527@qq.com.
 */

public class MoreSchoolRecycle extends RecyclerView.Adapter {

    private Context context;
    private List<CheckSchoolBean> list;

    public MoreSchoolRecycle(Context context, List<CheckSchoolBean> list) {
        this.context = context;
        this.list = list;
    }

    public  void  Refsh(List<CheckSchoolBean> newlist){
        list.clear();
        list=newlist;
        this.notifyDataSetChanged();
    }
    public  void loading(List<CheckSchoolBean> newlist){
        list.addAll(newlist);
        this.notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.moreschool_listitem, null);
        MySchoolViewHolder mySchoolViewHolder=new MySchoolViewHolder(view);
        return mySchoolViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        holder.setIsRecyclable(false);
        final MySchoolViewHolder mySchoolViewHolder= (MySchoolViewHolder) holder;
        mySchoolViewHolder.schoolitem_name.setText(list.get(position).getName());
        if(list.get(position).getRanking()!=null)
        {
            mySchoolViewHolder.schoolitem_typerank.setText(list.get(position).getRanking());
        }
        else
        {
            mySchoolViewHolder.schoolitem_typerank.setText("暂无数据");
        }
        mySchoolViewHolder.schoolitem_address.setText(list.get(position).getAddress());
        final String url = list.get(position).getUrl();
        String two = list.get(position).getTwo();
        String defenseStudent = list.get(position).getDefenseStudent();
        String nine = list.get(position).getNine();
        String recruit = list.get(position).getRecruit();
        String preeminentPlan = list.get(position).getPreeminentPlan();
        List<String> fujialist=new ArrayList<>();
        if(two!=null&&two.length()>2){
            fujialist.add(two);
        }
        if(nine!=null&&nine.length()>2){
            fujialist.add(nine);
        }
        if(defenseStudent!=null&&defenseStudent.length()>2){
            fujialist.add(defenseStudent);
        }

        if(recruit!=null&&recruit.length()>2){
            fujialist.add(recruit);
        }
        if(preeminentPlan!=null&&preeminentPlan.length()>2){
            fujialist.add(preeminentPlan);
        }

        if(fujialist.size()>0&&fujialist!=null){
            mySchoolViewHolder.majoritem_flow.setZyListData(fujialist);
        }else {
            fujialist.add("暂无信息");
            mySchoolViewHolder.majoritem_flow.setZyListData(fujialist);
        }
        if(url!=null){
           // Glide.with(context).load(BaseApi.ImgApi+url).into(mySchoolViewHolder.schoolitem_url);
            Glide.with(context).load(BaseApi.ImgApi+url).asBitmap().centerCrop().into(new BitmapImageViewTarget(mySchoolViewHolder.schoolitem_url) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    mySchoolViewHolder.schoolitem_url .setImageDrawable(circularBitmapDrawable);
                }
            });

        }
         mySchoolViewHolder.view.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  MySchoolViewHolder extends  RecyclerView.ViewHolder {
        private TextView    schoolitem_name;
        private TextView    schoolitem_address;
        private TextView    schoolitem_typerank;
        private ImageView schoolitem_url;
        private FlowLayout majoritem_flow;
        private  View view;
        public MySchoolViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            schoolitem_name=itemView.findViewById(R.id.schoolitem_name);
            schoolitem_address=itemView.findViewById(R.id.schoolitem_address);
            schoolitem_typerank=itemView.findViewById(R.id.schoolitem_typerank);
            schoolitem_url=itemView.findViewById(R.id.schoolitem_url);
            majoritem_flow=itemView.findViewById(R.id.majoritem_flow);
        }
    }



}
