package presenter;

import base.BaseBean;
import base.Basepresent;
import bean.TuiJianBean;
import moudle.TuiJianMoudle;
import view.TuiJianView;

/**
 * Created by 地地 on 2018/4/2.
 * 邮箱：461211527@qq.com.
 */

public class TuiJianPresent  extends Basepresent {
   private TuiJianMoudle tuiJianMoudle;
   private TuiJianView tuiJianView;

    public TuiJianPresent(Object view) {
        super(view);
        if(tuiJianMoudle==null){
            tuiJianMoudle=new TuiJianMoudle();
        }
        tuiJianView= (TuiJianView) view;
    }

    public  void  GetTuijian(String college,String time, String province, String t_classify, String t_score, String token){
        tuiJianMoudle.getTuiJian(college,time, province, t_classify, t_score, token, new TuiJianMoudle.TuiJianBack() {
            @Override
            public void TuiJiansuccess(BaseBean<TuiJianBean> listBaseBean) {
                if(listBaseBean.code==0){
                    tuiJianView.TuijianSuccess(listBaseBean.data);
                }else {
                    tuiJianView.TuijianFail(listBaseBean.msg);
                }
            }

            @Override
            public void TuiJianfail(Throwable t) {
                tuiJianView.TuijianFail("网络不佳，请重试");
            }
        });


    }

    public   void  onDestory(){
        this.onDeach();
        tuiJianMoudle.onDestory();
    }
}
