package moudle;

import java.util.List;

import base.BaseBean;
import bean.SerchZYBean;
import bean.ShijianBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 祝文 on 2018/5/17.
 */

public class ShijianMoudle {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    //系统时间
    public void shijian(final ShijianBack shijianBack)
    {
        DisposableSubscriber<ShijianBean> disposableSubscriber = MyQusetUtils.getInstance()
                .getQuestInterface().getsystemtime()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ShijianBean>() {
                    @Override
                    public void onNext(ShijianBean shijianBeanBaseBean) {
                        shijianBack.Shijiansuccess(shijianBeanBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        shijianBack.Shijianfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
         compositeDisposable.add(disposableSubscriber);
    }

    //时间接口
    public interface ShijianBack
    {
        void Shijiansuccess(ShijianBean shijianBean);
        void Shijianfail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
