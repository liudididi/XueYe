package presenter;

import java.util.List;

import base.BaseBean;
import base.Basepresent;
import bean.FavourMajorBean;
import moudle.favourMajorMoudle;
import view.favourMajorView;

/**
 * Created by 地地 on 2018/4/16.
 * 邮箱：461211527@qq.com.
 */

public class favourMajorpresent extends Basepresent {

    private favourMajorMoudle  FavourMajorMoudle;
    private favourMajorView      favourMajorView;

    public favourMajorpresent(Object view) {
        super(view);
        if(FavourMajorMoudle==null){
            FavourMajorMoudle=new favourMajorMoudle();
        }
        favourMajorView= (view.favourMajorView) view;
    }


    public  void  favourMajor(String token){
        FavourMajorMoudle.favourMajor(token, new favourMajorMoudle.favourMajorBack() {
            @Override
            public void favourMajorsuccess(BaseBean<FavourMajorBean> listBaseBean) {
                if(listBaseBean.code==0){
                    favourMajorView.favourMajorSuccess(listBaseBean.data.getFavourMajor());
                }else {
                    favourMajorView.favourMajorfail(listBaseBean.msg);
                }
            }

            @Override
            public void favourMajorfail(Throwable t) {
                favourMajorView.favourMajorfail("当前无网络");
            }
        });

    }
    public   void   onDestory(){
        this.onDeach();
        FavourMajorMoudle.onDestory();
    }
}
