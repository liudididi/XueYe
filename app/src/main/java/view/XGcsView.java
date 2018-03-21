package view;

import java.util.List;

import base.BaseBean;
import bean.XGcsBean;

/**
 * Created by 祝文 on 2018/3/20.
 */

public interface XGcsView {

    void XGcssuccess(BaseBean<List<XGcsBean>> listBaseBean);
    void XGcsfail(Throwable t);
}
