package presenter;

import java.util.List;

import base.BaseBean;
import base.Basepresent;
import bean.ForecastBean;
import bean.GailvBean;
import bean.LuquXianBean;
import bean.SchoolEnrollBean;
import bean.ZYTJBean;
import moudle.SchoolEnrollMoudle;
import view.SchoolEnrollView;

/**
 * Created by 祝文 on 2018/2/5.
 */

public class SchoolEnrollPresent extends Basepresent {
    private SchoolEnrollMoudle schoolEnrollMoudle;
    private SchoolEnrollView schoolEnrollView;
    public SchoolEnrollPresent(Object view) {
        super(view);
        if(schoolEnrollMoudle==null)
        {
            schoolEnrollMoudle=new SchoolEnrollMoudle();
        }
        schoolEnrollView= (SchoolEnrollView) view;
    }
    public void SchoolEnrollPresent(String name, String province, String type)
    {
        schoolEnrollMoudle.SchoolEnroll(name, province, type, new SchoolEnrollMoudle.SchoolEnrollBack() {
            @Override
            public void SchoolEnrollsuccess(BaseBean<List<SchoolEnrollBean>> listBaseBean) {
                schoolEnrollView.SchoolEnrollsuccess(listBaseBean);
            }

            @Override
            public void SchoolEnrollfail(Throwable t) {
                schoolEnrollView.SchoolEnrollfail(t);
            }
        });
    }

    public  void  getscoreCompareMobil(String province, String classify, String  schoolname,String scouse){
        schoolEnrollMoudle.getscoreCompareMobil(province, classify, schoolname,scouse, new SchoolEnrollMoudle.GailvBeanBack() {
            @Override
            public void SchoolEnrollsuccess(BaseBean<ForecastBean> listBaseBean) {
                if(listBaseBean.code==0){
                    schoolEnrollView.GetlvBeansuccess(listBaseBean.data );
                }else {
                    schoolEnrollView.GetlvBeanfail(listBaseBean.msg);
                }
            }

            @Override
            public void SchoolEnrollfail(Throwable t) {
                schoolEnrollView.GetlvBeanfail(t.toString());
            }
        });
    }


    public  void  getluquxian(String province, String university, String classify, String time,String line){

        schoolEnrollMoudle.getskx(province, university, classify, time, line, new SchoolEnrollMoudle.SLuquXianBeanBack() {
            @Override
            public void LuquXianBeansuccess(BaseBean<List<LuquXianBean>> BaseBean) {
                 if(BaseBean.code==0){
                     schoolEnrollView.LuquXianBeansuccess(BaseBean.data);
                 }else {
                     schoolEnrollView.LuquXianBeanfail(BaseBean.msg);
                 }

            }

            @Override
            public void LuquXianBeanfail(Throwable t) {
                schoolEnrollView.LuquXianBeanfail(t.toString());
            }
        });

    }

    public  void  TZmajor(String name, int type ){

        schoolEnrollMoudle.getTZmajor(name, type, new SchoolEnrollMoudle.TZmajorBack() {
            @Override
            public void TZmajorsuccess(BaseBean<List<ZYTJBean>> listBaseBean) {
                schoolEnrollView.TZmajorsuccess(listBaseBean);
            }

            @Override
            public void TZmajorfail(Throwable t) {
                schoolEnrollView.TZmajorfail(t);
            }
        });

    }

    public   void  onDestory(){
        this.onDeach();
        schoolEnrollMoudle.onDestory();
    }
}
