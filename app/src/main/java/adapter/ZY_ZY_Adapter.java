package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.List;

import bean.jobStarBean;

/**
 * Created by 祝文 on 2018/3/15.
 */

public class ZY_ZY_Adapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public ZY_ZY_Adapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.zy_zy_item, null);

        TextView tv_shu=inflate.findViewById(R.id.tv_shu);
        TextView tv_zy=inflate.findViewById(R.id.tv_zy);
        tv_zy.setText(list.get(i).toString());
        if(i<=2)
        {
            tv_shu.setText(i+1+"");
            tv_shu.setTextColor(Color.WHITE);
            tv_shu.setBackgroundResource(R.drawable.bg_num);
        }
        else
        {
            tv_shu.setText(i+1+"");
            tv_shu.setTextColor(context.getResources().getColor(R.color.delete_dialog_text_color));
            tv_shu.setBackgroundResource(R.drawable.bg_num3);
        }
        return inflate;
    }
}
