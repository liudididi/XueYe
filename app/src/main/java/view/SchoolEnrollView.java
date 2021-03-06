package view;

import java.util.List;

import base.BaseBean;
import bean.ForecastBean;
import bean.GailvBean;
import bean.LuquXianBean;
import bean.SchoolEnrollBean;
import bean.ZYTJBean;

/**
 * Created by 祝文 on 2018/2/5.
 */

public interface SchoolEnrollView {
    void SchoolEnrollsuccess(BaseBean<List<SchoolEnrollBean>> listBaseBean);
    void SchoolEnrollfail(Throwable t);

    void GetlvBeansuccess(ForecastBean listBaseBean);
    void GetlvBeanfail(String msg);

    void TZmajorsuccess(BaseBean<List<ZYTJBean>> listBaseBean);
    void TZmajorfail(Throwable t);

    void LuquXianBeansuccess(List<LuquXianBean> listBaseBean);
    void LuquXianBeanfail(String msg);
}
