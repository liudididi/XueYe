package presenter;

import java.util.List;

import base.BaseBean;
import base.Basepresent;
import bean.CXEFCBean;
import moudle.EsayMbtiLSMoudle;
import view.EsayMbtiLSView;

/**
 * Created by 祝文 on 2018/3/21.
 */

public class EsayMbtiLSPresenter extends Basepresent {
    private EsayMbtiLSMoudle esayMbtiLSMoudle;
    private EsayMbtiLSView esayMbtiLSView;

    public EsayMbtiLSPresenter(Object view) {
        super(view);
        if(esayMbtiLSMoudle==null)
        {
            esayMbtiLSMoudle=new EsayMbtiLSMoudle();
        }
        esayMbtiLSView= (EsayMbtiLSView) view;

    }
    public void EsayMbtiLSPresenter(String wishType,String token)
    {
        esayMbtiLSMoudle.EsayMbtiLS(wishType, token, new EsayMbtiLSMoudle.EsayMbtiLSBack() {
            @Override
            public void EsayMbtiLSsuccess(BaseBean<List<CXEFCBean>> listBaseBean) {
                esayMbtiLSView.EsayMbtiLSsuccess(listBaseBean);
            }

            @Override
            public void EsayMbtiLSfail(Throwable t) {
                esayMbtiLSView.EsayMbtiLSfail(t);
            }
        });
    }
    public   void  onDestory(){
        this.onDeach();
        esayMbtiLSMoudle.onDestory();
    }
}
