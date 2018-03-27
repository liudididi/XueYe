package presenter;

import base.BaseBean;
import base.Basepresent;
import moudle.YfenQMoudle;
import view.YfenQView;

/**
 * Created by 地地 on 2018/3/27.
 * 邮箱：461211527@qq.com.
 */

public class YfenQPresent extends Basepresent {
   private YfenQMoudle yfenQMoudle;
   private YfenQView yfenQView;
    public YfenQPresent(Object view) {
        super(view);
        if(yfenQMoudle==null){
            yfenQMoudle=new YfenQMoudle();
        }
        yfenQView= (YfenQView) view;
    }

    public void yfJudge(String wishType, String token ){

        yfenQMoudle.yfJudge(wishType, token, new YfenQMoudle.YfenQBack() {
            @Override
            public void YfenQsuccess(BaseBean listBaseBean) {
                yfenQView.YfenQsuccess(listBaseBean);
            }

            @Override
            public void YfenQfail(Throwable t) {

            }
        });

    }

    public   void  onDestory(){
        this.onDeach();
        yfenQMoudle.onDestory();
    }


}
