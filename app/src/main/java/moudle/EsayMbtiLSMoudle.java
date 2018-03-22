package moudle;

import java.util.List;

import base.BaseBean;
import bean.CXEFCBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 祝文 on 2018/3/21.
 */

public class EsayMbtiLSMoudle {

    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    //简单的MBTI历史报告
    public void EsayMbtiLS(String wishType, String token, final EsayMbtiLSBack esayMbtiLSBack)
    {
        DisposableSubscriber<BaseBean<List<CXEFCBean>>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface().getHistoryInfonew(wishType, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<List<CXEFCBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<CXEFCBean>> listBaseBean) {
                        esayMbtiLSBack.EsayMbtiLSsuccess(listBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        esayMbtiLSBack.EsayMbtiLSfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableSubscriber);
    }

    //简单的MBTI历史报告的接口
    public interface EsayMbtiLSBack
    {
        void EsayMbtiLSsuccess(BaseBean<List<CXEFCBean>> listBaseBean);
        void EsayMbtiLSfail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
