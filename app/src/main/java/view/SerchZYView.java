package view;

import java.util.List;

import base.BaseBean;
import bean.SerchZYBean;

/**
 * Created by 祝文 on 2018/3/16.
 */

public interface SerchZYView {
    void SerchZYsuccess(BaseBean<List<SerchZYBean>> listBaseBean);
    void SerchZYfail(Throwable t);
}
