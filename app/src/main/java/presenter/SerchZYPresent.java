package presenter;

import java.util.List;

import base.BaseBean;
import base.Basepresent;
import bean.SerchZYBean;
import moudle.SerchZYMoudle;
import view.SerchZYView;

/**
 * Created by 祝文 on 2018/3/16.
 */

public class SerchZYPresent extends Basepresent {
    private SerchZYMoudle serchZYMoudle;
    private SerchZYView serchZYView;
    public SerchZYPresent(Object view) {
        super(view);
        if(serchZYMoudle==null)
        {
            serchZYMoudle=new SerchZYMoudle();
        }
        serchZYView= (SerchZYView) view;
    }
    public void SerchZYPresent(String name)
    {
        serchZYMoudle.SerchZY(name, new SerchZYMoudle.SerchZYBack() {
            @Override
            public void SerchZYsuccess(BaseBean<List<SerchZYBean>> listBaseBean) {
                serchZYView.SerchZYsuccess(listBaseBean);
            }

            @Override
            public void SerchZYfail(Throwable t) {
                serchZYView.SerchZYfail(t);
            }
        });
    }

    public   void  onDestory(){
        this.onDeach();
        serchZYMoudle.onDestory();
    }
}
