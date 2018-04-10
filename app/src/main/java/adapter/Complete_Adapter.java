package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.ArrayList;
import java.util.List;


public class Complete_Adapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public Complete_Adapter( List<String> list, Context context) {
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.complete_item, null);
        TextView spinner_tv=inflate.findViewById(R.id.spinner_tv);
        spinner_tv.setText(list.get(i).toString());
        return inflate;
    }
}
