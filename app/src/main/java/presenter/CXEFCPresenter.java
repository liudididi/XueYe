package presenter;

import base.BaseBean;
import base.Basepresent;
import bean.CXEFCBean;
import moudle.CXEFCMoudle;
import view.CXEFCView;

/**
 * Created by 祝文 on 2018/3/19.
 */

public class CXEFCPresenter extends Basepresent {
    private CXEFCMoudle cxefcMoudle;
    private CXEFCView cxefcView;
    public CXEFCPresenter(Object view) {
        super(view);
        if(cxefcMoudle==null)
        {
            cxefcMoudle=new CXEFCMoudle();
        }
        cxefcView= (CXEFCView) view;
    }
    public void CXEFCPresenter(String token)
    {
        cxefcMoudle.GetEFCResult(token, new CXEFCMoudle.GetEFCResultBack() {
            @Override
            public void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
                cxefcView.GetEFCResultsuccess(cxefcBeanBaseBean);
            }

            @Override
            public void GetEFCResultfail(Throwable t) {

                cxefcView.GetEFCResultfail(t);
            }
        });
    }

    public   void   onDestory(){
        this.onDeach();
        cxefcMoudle.onDestory();
    }

}
