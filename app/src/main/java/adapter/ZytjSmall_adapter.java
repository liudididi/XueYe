package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_demo.MajorDetailActivity;
import com.example.login_demo.R;

import java.util.List;

import bean.EFCBean;

/**
 * Created by 地地 on 2018/3/15.
 * 邮箱：461211527@qq.com.
 */

public class ZytjSmall_adapter extends BaseAdapter {
    private List<EFCBean.MajortwoBean.ThreeListBean> list;
    private Context context;
    public ZytjSmall_adapter(List<EFCBean.MajortwoBean.ThreeListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return  list.size();
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
            convertView=View.inflate(context, R.layout.xygh_item_small,null);
        }
        TextView ghitem_title= convertView.findViewById(R.id.ghitem_title);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getMajorId()!=null){
                    Intent intent=new Intent(context, MajorDetailActivity.class);
                    intent.putExtra("majorid",list.get(position).getMajorId());
                    intent.putExtra("major",list.get(position).getMajor());
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context, "数据整理中", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ghitem_title.setText(list.get(position).getMajor());

        TextView ghitem_gailv= convertView.findViewById(R.id.ghitem_gailv);
        String zyGai=list.get(position).getGai()+"";
         if(zyGai.length()>=4)
        {
            zyGai = zyGai.substring(2, 4);
        }
        else if(zyGai.equals("00"))
        {
            zyGai="100";
        }
        else
        {
            zyGai = zyGai.substring(2)+"0";
        }
        ghitem_gailv.setText(zyGai+"%");
         return convertView;
    }
}
