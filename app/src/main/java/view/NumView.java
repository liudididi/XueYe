package view;

import java.util.List;

import base.BaseBean;
import bean.NumBean;
import bean.ZYNumBean;

/**
 * Created by 祝文 on 2018/3/31.
 */

public interface NumView {
    void Numsuccess(BaseBean<List<NumBean>> listBaseBean);
    void Numfail(Throwable t);
    void ZYNumsuccess(BaseBean<List<ZYNumBean>> listBaseBean);
    void ZYNumfail(Throwable t);
}
