package moudle;

import base.BaseBean;
import bean.CXEFCBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 祝文 on 2018/3/19.
 */

public class CXEFCMoudle {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    //查询当前EFC精准结果
    public void GetEFCResult(String token, final GetEFCResultBack getEFCResultBack)
    {
        DisposableSubscriber<BaseBean<CXEFCBean>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface()
                .getEFCResult(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<CXEFCBean>>() {
                    @Override
                    public void onNext(BaseBean<CXEFCBean> cxefcBeanBaseBean) {
                        getEFCResultBack.GetEFCResultsuccess(cxefcBeanBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        getEFCResultBack.GetEFCResultfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        compositeDisposable.add(disposableSubscriber);
    }


    //查询当前EFC精准结果的接口
    public interface GetEFCResultBack
    {
        void GetEFCResultsuccess(BaseBean<CXEFCBean> cxefcBeanBaseBean);
        void GetEFCResultfail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
