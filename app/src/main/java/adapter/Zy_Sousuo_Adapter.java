package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.List;

import bean.SearchBean;
import bean.SerchZYBean;

/**
 * Created by 祝文 on 2018/3/16.
 */

public class Zy_Sousuo_Adapter extends BaseAdapter {
    private List<SerchZYBean> list;
    private Context context;

    public Zy_Sousuo_Adapter(List list, Context context) {
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.zy_sousuo_item, null);
        TextView tv_zy=inflate.findViewById(R.id.tv_zy);

            tv_zy.setText(list.get(i).getName());


        return inflate;
    }
}
