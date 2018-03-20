package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.ArrayList;

import bean.BiaoshiBean;

/**
 * Created by 祝文 on 2018/3/10.
 */

public class Volunteer_ScreenAdapter extends BaseAdapter {
    private ArrayList<BiaoshiBean> list;
    private Context context;


    public Volunteer_ScreenAdapter(ArrayList<BiaoshiBean> list, Context context) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.screen_item, null);
        TextView tv_type = inflate.findViewById(R.id.tv_type);
        tv_type.setText(list.get(i).getStr().toString());
        tv_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvItem.Tv(view,i);
            }
        });

        return inflate;
    }

    private TvItem tvItem;

    public void setTvItem(TvItem tvItem) {
        this.tvItem = tvItem;
    }
    public interface TvItem
    {
        void Tv(View view, int postion);
    }
}
