package moudle;

import java.util.List;

import base.BaseBean;
import bean.SerchZYBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 祝文 on 2018/3/16.
 */

public class SerchZYMoudle {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    //专业搜索
    public void SerchZY(String name, final SerchZYBack serchZYBack)
    {
        DisposableSubscriber<BaseBean<List<SerchZYBean>>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface().SerchZY(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<List<SerchZYBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<SerchZYBean>> listBaseBean) {
                        serchZYBack.SerchZYsuccess(listBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        serchZYBack.SerchZYfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableSubscriber);
    }

    //专业搜索的接口
    public interface SerchZYBack
    {
        void SerchZYsuccess(BaseBean<List<SerchZYBean>> listBaseBean);
        void SerchZYfail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
