package presenter;

import java.util.List;

import base.BaseBean;
import base.Basepresent;
import bean.NumBean;
import bean.ZYNumBean;
import moudle.NumMoudle;
import view.NumView;

/**
 * Created by 祝文 on 2018/3/31.
 */

public class NumPresenter extends Basepresent {
    private NumMoudle numMoudle;
    private NumView numView;

    public NumPresenter(Object view) {
        super(view);
        if(numMoudle==null)
        {
            numMoudle=new NumMoudle();
        }
        numView= (NumView) view;
    }

    public void NumPresenter(String name, String classify, String stuProvince)
    {
        numMoudle.Num(name, classify, stuProvince, new NumMoudle.NumBack() {
            @Override
            public void Numsuccess(BaseBean<List<NumBean>> listBaseBean) {
                numView.Numsuccess(listBaseBean);
            }

            @Override
            public void Numfail(Throwable t) {
                numView.Numfail(t);
            }
        });
    }
    public void ZYNumPresenter(String name, String classify, String stuProvince,String major)
    {
        numMoudle.ZYNum(name, classify, stuProvince, major, new NumMoudle.ZYNumBack() {
            @Override
            public void ZYNumsuccess(BaseBean<List<ZYNumBean>> listBaseBean) {
                numView.ZYNumsuccess(listBaseBean);
            }

            @Override
            public void ZYNumfail(Throwable t) {
                numView.ZYNumfail(t);
            }
        });
    }
    public   void   onDestory(){
        this.onDeach();
        numMoudle.onDestory();
    }
}
