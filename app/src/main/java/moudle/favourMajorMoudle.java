package moudle;

import java.util.List;

import base.BaseBean;
import bean.FavourMajorBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 地地 on 2018/4/16.
 * 邮箱：461211527@qq.com.
 */

public class favourMajorMoudle {

    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    public  void  favourMajor(String token, final favourMajorBack favourMajorBack){
        DisposableSubscriber<BaseBean<FavourMajorBean>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface().favourMajor(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<FavourMajorBean>>() {

                    @Override
                    public void onNext(BaseBean<FavourMajorBean> favourMajorBeanBaseBean) {
                        favourMajorBack.favourMajorsuccess(favourMajorBeanBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        favourMajorBack.favourMajorfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableSubscriber);
    }

    public interface favourMajorBack
    {
        void favourMajorsuccess(BaseBean<FavourMajorBean> listBaseBean);
        void favourMajorfail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
