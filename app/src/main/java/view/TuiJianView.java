package view;

import bean.TuiJianBean;

/**
 * Created by 地地 on 2018/4/2.
 * 邮箱：461211527@qq.com.
 */

public interface TuiJianView {

    void  TuijianSuccess(TuiJianBean tuiJianBean);
    void  TuijianFail(String msg);

}
