package view;

import java.util.List;

import base.BaseBean;
import bean.EsaySdsBean;

/**
 * Created by 祝文 on 2018/3/22.
 */

public interface EsayHuanLanDeView {
    void EsayHuanLanDesuccess(BaseBean<List<EsaySdsBean>> listBaseBean);
    void EsayHuanLanDefail(Throwable t);
}
