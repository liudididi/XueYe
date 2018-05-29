package presenter;

import base.BaseBean;
import base.Basepresent;
import bean.ShijianBean;
import bean.StudyBean;
import moudle.ShijianMoudle;
import moudle.StudyMoudle;
import view.ShijianView;
import view.StudyView;

/**
 * Created by 祝文 on 2018/5/17.
 */

public class ShijianPresent extends Basepresent{
    private ShijianMoudle shijianMoudle;
    private ShijianView shijianView;

    public ShijianPresent(Object view) {
        super(view);
        if(shijianMoudle==null)
        {
            shijianMoudle=new ShijianMoudle();
        }
        shijianView= (ShijianView) view;
    }
    public void ShijianPresent()
    {
        shijianMoudle.shijian(new ShijianMoudle.ShijianBack() {
            @Override
            public void Shijiansuccess(ShijianBean shijianBean) {
                shijianView.Shijiansuccess(shijianBean);
            }

            @Override
            public void Shijianfail(Throwable t) {
                shijianView.Shijianfail(t);
            }
        });
    }
    public   void  onDestory(){
        this.onDeach();
        shijianMoudle.onDestory();
    }

}
