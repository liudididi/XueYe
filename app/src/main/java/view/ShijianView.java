package view;

import base.BaseBean;
import bean.ShijianBean;

/**
 * Created by 祝文 on 2018/5/17.
 */

public interface ShijianView  {
    void Shijiansuccess(ShijianBean shijianBean);
    void Shijianfail(Throwable t);
}
