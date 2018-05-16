package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context, R.layout.xygh_item,null);
        }
        TextView ghitem_title= convertView.findViewById(R.id.ghitem_title);

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
