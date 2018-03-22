package view;

import java.util.List;

import base.BaseBean;
import bean.EsayBaoGaoBean;

/**
 * Created by 祝文 on 2018/3/21.
 */

public interface EsayBaoGaoView {

    void EsayBaoGaosuccess(BaseBean<List<EsayBaoGaoBean>> listBaseBean);
    void EsayBaoGaofail(Throwable t);
}
