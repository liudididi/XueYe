package view;

import java.util.List;

import base.BaseBean;
import bean.CXEFCBean;

/**
 * Created by 祝文 on 2018/3/21.
 */

public interface EsayMbtiLSView {
    void EsayMbtiLSsuccess(BaseBean<List<CXEFCBean>> listBaseBean);
    void EsayMbtiLSfail(Throwable t);
}
