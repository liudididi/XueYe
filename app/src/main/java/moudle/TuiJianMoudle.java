package moudle;

import base.BaseBean;
import bean.TuiJianBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 地地 on 2018/4/2.
 * 邮箱：461211527@qq.com.
 */

public class TuiJianMoudle {

    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    public void  getTuiJian(String college, String province, String t_classify, String t_score, String token, final TuiJianBack tuiJianBack){

        DisposableSubscriber<BaseBean<TuiJianBean>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface().getRecommendCollege(college, province, t_classify, t_score, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<TuiJianBean>>() {
                    @Override
                    public void onNext(BaseBean<TuiJianBean> tuiJianBeanBaseBean) {
                        tuiJianBack.TuiJiansuccess(tuiJianBeanBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {

                        tuiJianBack.TuiJianfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public interface TuiJianBack
    {
        void  TuiJiansuccess(BaseBean<TuiJianBean> listBaseBean);
        void  TuiJianfail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }

}
