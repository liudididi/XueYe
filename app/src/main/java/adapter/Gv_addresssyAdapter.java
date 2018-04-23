package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.List;

/**
 * Created by 地地 on 2018/4/18.
 * 邮箱：461211527@qq.com.
 */

public class Gv_addresssyAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private  AddressBack addressBack;

    public void setAddressBack(AddressBack addressBack) {
        this.addressBack = addressBack;
    }

    public Gv_addresssyAdapter(List<String> list, Context context) {
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
            convertView=View.inflate(context, R.layout.adress_itemblack,null);
        }
        TextView tv_addresss= convertView.findViewById(R.id.tv_addresss);
        tv_addresss.setText(list.get(position));
        tv_addresss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressBack.setaddress(list.get(position));
            }
        });
        return convertView;
    }

    public  interface  AddressBack{
        void  setaddress(String s);
    }
}
