package moudle;

import base.BaseBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 祝文 on 2018/3/19.
 */

public class EFCJGBacCunMoudle {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    //EFC结果保存
    public void Save(String wishType, String testCode, String token, final SaveBack saveBack)
    {
        DisposableSubscriber<BaseBean> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface().save(wishType, testCode,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        saveBack.Savesuccess(baseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        saveBack.Savefail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableSubscriber);
    }

    //EFC结果保存的接口
    public interface SaveBack
    {
        void Savesuccess(BaseBean baseBean);
        void Savefail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
