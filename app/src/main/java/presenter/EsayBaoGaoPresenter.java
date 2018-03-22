package presenter;

import java.util.List;

import base.BaseBean;
import base.Basepresent;
import bean.EsayBaoGaoBean;
import moudle.EsayBaoGaoMoudle;
import view.EsayBaoGaoView;

/**
 * Created by 祝文 on 2018/3/21.
 */

public class EsayBaoGaoPresenter extends Basepresent{
    private EsayBaoGaoMoudle esayBaoGaoMoudle;
    private EsayBaoGaoView esayBaoGaoView;

    public EsayBaoGaoPresenter(Object view) {
        super(view);
        if(esayBaoGaoMoudle==null)
        {
            esayBaoGaoMoudle=new EsayBaoGaoMoudle();
        }
        esayBaoGaoView= (EsayBaoGaoView) view;
    }

    public void EsayBaoGaoPresenter(String testCode)
    {
        esayBaoGaoMoudle.EsayBaoGao(testCode, new EsayBaoGaoMoudle.EsayBaoGaoBack() {
            @Override
            public void EsayBaoGaosuccess(BaseBean<List<EsayBaoGaoBean>> listBaseBean) {
                esayBaoGaoView.EsayBaoGaosuccess(listBaseBean);
            }

            @Override
            public void EsayBaoGaofail(Throwable t) {
                esayBaoGaoView.EsayBaoGaofail(t);
            }
        });
    }
    public   void  onDestory(){
        this.onDeach();
        esayBaoGaoMoudle.onDestory();
    }
}
