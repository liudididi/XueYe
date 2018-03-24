package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_demo.R;

import java.util.List;

import bean.MajorgkBean;

/**
 * Created by 地地 on 2018/3/24.
 * 邮箱：461211527@qq.com.
 */

public class ZhuanYgk_zhishiAdapter extends BaseAdapter {
    private List<MajorgkBean.AbilityBean>  list;
    private Context context;

    public ZhuanYgk_zhishiAdapter(List<MajorgkBean.AbilityBean> list, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = View.inflate(context, R.layout.zygk_zsitem, null);
        }
        TextView zygk_item = convertView.findViewById(R.id.zygk_item);
        zygk_item.setText((position+1)+"、"+list.get(position).getGraduatesAbility());
        return convertView;
    }
}
