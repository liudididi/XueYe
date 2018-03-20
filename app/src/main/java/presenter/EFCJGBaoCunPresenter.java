package presenter;

import base.BaseBean;
import base.Basepresent;
import moudle.EFCJGBacCunMoudle;
import view.EFCJGBaoCunView;

/**
 * Created by 祝文 on 2018/3/19.
 */

public class EFCJGBaoCunPresenter extends Basepresent {
    private EFCJGBacCunMoudle efcjgBacCunMoudle;
    private EFCJGBaoCunView efcjgBaoCunView;

    public EFCJGBaoCunPresenter(Object view) {
        super(view);
        if(efcjgBacCunMoudle==null)
        {
            efcjgBacCunMoudle=new EFCJGBacCunMoudle();
        }
        efcjgBaoCunView= (EFCJGBaoCunView) view;
    }

    public void EFCJGBaoCunPresenter(String wishType, String testCode, String token)
    {
        efcjgBacCunMoudle.Save(wishType, testCode, token, new EFCJGBacCunMoudle.SaveBack() {
            @Override
            public void Savesuccess(BaseBean baseBean) {
                efcjgBaoCunView.Savesuccess(baseBean);
            }

            @Override
            public void Savefail(Throwable t) {
                efcjgBaoCunView.Savefail(t);
            }
        });
    }

    public   void  onDestory(){
        this.onDeach();
        efcjgBacCunMoudle.onDestory();
    }
}
