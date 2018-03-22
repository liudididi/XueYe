package presenter;

import java.util.List;

import base.BaseBean;
import base.Basepresent;
import bean.EsaySdsBean;
import moudle.EsayHuanLanDeMoudle;
import view.EsayHuanLanDeView;

/**
 * Created by 祝文 on 2018/3/22.
 */

public class EsayHuanLanDePresenter extends Basepresent {
    private EsayHuanLanDeMoudle esayHuanLanDeMoudle;
    private EsayHuanLanDeView esayHuanLanDeView;
    public EsayHuanLanDePresenter(Object view) {
        super(view);
        if(esayHuanLanDeMoudle==null)
        {
            esayHuanLanDeMoudle=new EsayHuanLanDeMoudle();
        }
        esayHuanLanDeView= (EsayHuanLanDeView) view;
    }

    public void EsayHuanLanDePresenter(String testCode)
    {
        esayHuanLanDeMoudle.EsayHuanLanDe(testCode, new EsayHuanLanDeMoudle.EsayHuanLanDeBack() {
            @Override
            public void EsayHuanLanDesuccess(BaseBean<List<EsaySdsBean>> listBaseBean) {
                esayHuanLanDeView.EsayHuanLanDesuccess(listBaseBean);
            }

            @Override
            public void EsayHuanLanDefail(Throwable t) {
                esayHuanLanDeView.EsayHuanLanDefail(t);
            }
        });
    }
    public   void   onDestory(){
        this.onDeach();
        esayHuanLanDeMoudle.onDestory();
    }

}
