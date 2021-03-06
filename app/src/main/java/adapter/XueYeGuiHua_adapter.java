package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.List;

/**
 * Created by 地地 on 2018/3/15.
 * 邮箱：461211527@qq.com.
 */

public class XueYeGuiHua_adapter extends BaseAdapter {
    private List<String>  list;
    private Context context;
    public XueYeGuiHua_adapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        if(list.size()<8){
            return list.size();
        }else {
            return  8;
        }
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
        String[] split = list.get(position).split(":");
        ghitem_title.setText(split[0]);

        TextView ghitem_gailv= convertView.findViewById(R.id.ghitem_gailv);
        String zyGai=split[2];
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
