package fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_demo.MajorDetailActivity;
import com.example.login_demo.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import adapter.MajorSchoolRecycle;
import base.Basefragment;
import bean.MajorSchoolBean;
import presenter.MajorSchoolPresent;
import view.MajorSchoolView;

/**
 * Created by 地地 on 2018/2/2.
 * 邮箱：461211527@qq.com.
 */

public class Majorschool_Fragment extends Basefragment implements MajorSchoolView {

    private XRecyclerView majorschool_xrecycle;
    public static TextView marjorschool_tvnum;
    private MajorSchoolPresent majorSchoolPresent;
    private ProgressBar majorshool_pb;
    private ImageView majorschool_none;
    private MajorSchoolRecycle adpter;
    private  int page=1;


    @Override
    public int getLayoutid() {
        return R.layout.majorschool;
    }

    @Override
    public void initView() {
     initid();
     majorSchoolPresent = new MajorSchoolPresent(this);

     majorSchoolPresent.getMajorschool(MajorDetailActivity.majorid,page);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initid() {
        majorschool_xrecycle = view.findViewById(R.id.majorschool_xrecycle);
        majorschool_xrecycle.setPullRefreshEnabled(false);
        majorschool_xrecycle.setLoadingMoreEnabled(true);
        majorschool_xrecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        marjorschool_tvnum = view.findViewById(R.id.marjorschool_tvnum);
        majorshool_pb = view.findViewById(R.id.majorshool_pb);
        majorschool_none = view.findViewById(R.id.majorschool_none);
        majorschool_xrecycle.setNestedScrollingEnabled(false);

    }




    @Override
    public void MarjorSchoolSuccess(final List<MajorSchoolBean> list) {
        if(list!=null&list.size()>0){
            majorschool_none.setVisibility(View.GONE);
            majorshool_pb.setVisibility(View.GONE);
            if(adpter==null){
                adpter = new MajorSchoolRecycle(getActivity(),list);
                majorschool_xrecycle.setAdapter(adpter);
            }else {
                adpter.LodingMore(list);
            }
            majorschool_xrecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {

                }

                @Override
                public void onLoadMore() {
                    page++;
                    majorSchoolPresent.getMajorschool(MajorDetailActivity.majorid,page);
                    majorschool_xrecycle.loadMoreComplete();
                }
            });
        }else {
            Toast.makeText(getActivity(), "暂无数据！！", Toast.LENGTH_LONG).show();
            majorshool_pb.setVisibility(View.GONE);
        }
    }

    @Override
    public void MarjorSchoolFail(String msg) {

    }
}
