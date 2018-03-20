package view;

import base.BaseBean;
import bean.CXEFCBean;

/**
 * Created by 祝文 on 2018/3/19.
 */

public interface CXEFCView {
    void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean);
    void GetEFCResultfail(Throwable t);
}
