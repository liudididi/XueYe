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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.login_demo.R;
import com.example.login_demo.SchoolDetailActivity;

import java.util.ArrayList;
import java.util.List;

import base.BaseApi;
import bean.MajorSchoolBean;
import fragment.Majorschool_Fragment;
import untils.FlowLayout;

/**
 * Created by 地地 on 2018/1/26.
 * 邮箱：461211527@qq.com.
 */

public class MajorSchoolRecycle extends RecyclerView.Adapter {

    private Context context;
    private List<MajorSchoolBean> list;

    public MajorSchoolRecycle(Context context, List<MajorSchoolBean>  list) {
        this.context = context;
        this.list = list;
    }

    public  void  LodingMore(List<MajorSchoolBean> newlist){
        list.addAll(newlist);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.majorschool_listitem, null);
        MySchoolViewHolder mySchoolViewHolder=new MySchoolViewHolder(view);
        return mySchoolViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Majorschool_Fragment.marjorschool_tvnum.setText(list.size()+"所");
        holder.setIsRecyclable(false);
        final MySchoolViewHolder mySchoolViewHolder= (MySchoolViewHolder) holder;
        mySchoolViewHolder.schoolitem_name.setText(list.get(position).getMajor_school());
        String url = list.get(position).getUrl();
        String two = list.get(position).getTwo();
        String school_type = list.get(position).getSchool_type();
        String nine = list.get(position).getNine();
        String graduate = list.get(position).getGraduate();
        String recruit = list.get(position).getRecruit();
        String defense_student = list.get(position).getDefense_student();
        List<String> fujialist=new ArrayList<>();

       if(school_type!=null){
           mySchoolViewHolder.schoolitem_typerank.setText(school_type);
       }
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
      if(url!=null){
            //Glide.with(context).load(BaseApi.ImgApi+url).into(mySchoolViewHolder.schoolitem_url);
          Glide.with(context).load(BaseApi.ImgApi + url).asBitmap().centerCrop().into(new BitmapImageViewTarget(mySchoolViewHolder.schoolitem_url) {
              @Override
              protected void setResource(Bitmap resource) {
                  RoundedBitmapDrawable circularBitmapDrawable =
                          RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                  circularBitmapDrawable.setCircular(true);
                  mySchoolViewHolder.schoolitem_url.setImageDrawable(circularBitmapDrawable);
              }
          });


                  }

       if(fujialist.size()>0&&fujialist!=null){
             mySchoolViewHolder.majoritem_flow.setZyListData(fujialist);
        }else {
           fujialist.add("暂无信息");
           mySchoolViewHolder.majoritem_flow.setZyListData(fujialist);
       }


      mySchoolViewHolder.view.setOnClickListener(new View.OnClickListener() {
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
        if(list.size()>200){
            return 200;
        }else {
            return list.size();
        }
    }

    class  MySchoolViewHolder extends  RecyclerView.ViewHolder {
        private TextView    schoolitem_name;

        private TextView    schoolitem_typerank;

        private ImageView schoolitem_url;
        private FlowLayout majoritem_flow;
        private  View view;



        public MySchoolViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            schoolitem_name=itemView.findViewById(R.id.schoolitem_name);
            schoolitem_typerank=itemView.findViewById(R.id.schoolitem_typerank);
            schoolitem_url=itemView.findViewById(R.id.schoolitem_url);
            majoritem_flow=itemView.findViewById(R.id.majoritem_flow);
        }
    }
}
