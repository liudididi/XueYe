package fragment;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_demo.R;
import com.example.login_demo.SchoolBrouesDetailsActivity;
import com.example.login_demo.SchoolDetailActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.School_Brochures_Adapter;
import base.BaseBean;
import base.Basefragment;
import bean.SchoolBrochuresBean;
import presenter.SchoolBrochuresPresent;
import view.SchoolBrochuresView;

/**
 * Created by 祝文 on 2018/2/7.
 */

public class School_Brochures extends Basefragment implements SchoolBrochuresView{


    private RelativeLayout rl_zsjz;
    private RelativeLayout rl_zzzs;
    private RelativeLayout rl_bsszs;
    private RelativeLayout rl_tcszs;


    @Override
    public int getLayoutid() {
        return R.layout.school_brochures;
    }

    @Override
    public void initView() {

        rl_zsjz = view.findViewById(R.id.rl_zsjz);
        rl_zzzs = view.findViewById(R.id.rl_zzzs);
        rl_bsszs = view.findViewById(R.id.rl_bsszs);
        rl_tcszs = view.findViewById(R.id.rl_tcszs);

        SchoolBrochuresPresent schoolBrochuresPresent=new SchoolBrochuresPresent(this);
        schoolBrochuresPresent.SchoolBrochuresPresent(SchoolDetailActivity.schoolname);


    }

    @Override
    public void Brochuressuccess(BaseBean<List<SchoolBrochuresBean>> listBaseBean) {
        List<SchoolBrochuresBean> data = listBaseBean.data;
        if(data.size()>0&&data!=null)
        {

                //招生简章
                final String  admissionRules = data.get(0).getAdmissionRules();

                //自主招生
                 final String independent = data.get(0).getIndependent();
                 //b报送招生
                final String baosongstr = data.get(0).getBaosongstr();
                //特长生招生
                final String specialtystr = data.get(0).getSpecialtystr();

            rl_zsjz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SchoolDetailActivity.ff(admissionRules);
                }
            });
            rl_zzzs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SchoolDetailActivity.ff(independent);
                }
            });
            rl_bsszs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SchoolDetailActivity.ff(baosongstr);
                }
            });
            rl_tcszs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SchoolDetailActivity.ff(specialtystr);
                }
            });


        }


    }

    @Override
    public void Brochuresfail(Throwable t) {

    }
}
