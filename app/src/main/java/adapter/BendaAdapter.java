package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.List;

import bean.SelectMajorBean;

/**
 * Created by 地地 on 2018/3/23.
 * 邮箱：461211527@qq.com.
 */

public class BendaAdapter extends BaseAdapter {

    private List<SelectMajorBean> list;
    private Context context;
    private  SetbendaBack setbendaBack;
    private  int  zt=0;

    public BendaAdapter(List<SelectMajorBean> list, Context context) {
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
            convertView=View.inflate(context, R.layout.dalistitem,null);
        }
        final TextView benda_tv=convertView.findViewById(R.id.benda_tv);
          if(position==zt){
              benda_tv.setBackgroundColor(Color.parseColor("#3B9EFF"));
          }else {
              benda_tv.setBackgroundColor(Color.parseColor("#F3F3F3"));
          }
        benda_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zt=position;
                setbendaBack.setbenxiao(benda_tv,list.get(position).getChild());
                notifyDataSetChanged();
            }
        });
        benda_tv.setText(list.get(position).getMajorName());
        return convertView;
    }

  public   interface  SetbendaBack{
      void  setbenxiao(TextView benda_tv, List<SelectMajorBean.ChildBeanX> list);
    }

    public void setSetbendaBack(SetbendaBack setbendaBack) {
        this.setbendaBack = setbendaBack;
    }
}
