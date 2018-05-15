package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2018/3/10.
 */

public class YXJ_ScreenAdapter extends BaseAdapter {
    private ArrayList<String> list;
    private Context context;
    //优先级设置
    private int clickTemp = -1;

    //标识选择的Item
    public void setSeclection(int position ) {
        clickTemp = position;

    }


    public YXJ_ScreenAdapter(ArrayList<String> list, Context context) {
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.screen_item, null);
        TextView tv_type=inflate.findViewById(R.id.tv_type);
        tv_type.setText(list.get(i).toString());
        if(clickTemp==i)
        {
            tv_type.setBackgroundResource(R.drawable.bg_subject5);

        }
        else
        {
            tv_type.setBackgroundResource(R.drawable.bg_subject4);

        }
        return inflate;
    }
}
