package presenter;

import java.util.List;

import base.BaseBean;
import base.Basepresent;
import bean.XGcsBean;
import moudle.XGcsMoudle;
import view.XGcsView;

/**
 * Created by 祝文 on 2018/3/20.
 */

public class XGcsPresenter extends Basepresent {
    private XGcsMoudle xGcsMoudle;
    private XGcsView xGcsView;

    public XGcsPresenter(Object view) {
        super(view);
        if(xGcsMoudle==null)
        {
            xGcsMoudle=new XGcsMoudle();
        }
        xGcsView= (XGcsView) view;
    }
    public void XGcsPresenter(String testCode)
    {
        xGcsMoudle.XGcs(testCode, new XGcsMoudle.XGcsBack() {
            @Override
            public void XGcssuccess(BaseBean<List<XGcsBean>> listBaseBean) {
                xGcsView.XGcssuccess(listBaseBean);
            }
            @Override
            public void XGcsfail(Throwable t) {
                xGcsView.XGcsfail(t);
            }
        });
    }
    public   void  onDestory(){
        this.onDeach();
        xGcsMoudle.onDestory();
    }
}
