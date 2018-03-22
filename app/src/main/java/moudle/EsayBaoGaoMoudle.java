package moudle;

import java.util.List;

import base.BaseBean;
import bean.EsayBaoGaoBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 祝文 on 2018/3/21.
 */

public class EsayBaoGaoMoudle {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    // MBTI简单的包好
    public void EsayBaoGao(String testCode, final EsayBaoGaoBack esayBaoGaoBack)
    {
        DisposableSubscriber<BaseBean<List<EsayBaoGaoBean>>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface().getmbtiE(testCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<List<EsayBaoGaoBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<EsayBaoGaoBean>> listBaseBean) {
                        esayBaoGaoBack.EsayBaoGaosuccess(listBaseBean);

                    }

                    @Override
                    public void onError(Throwable t) {
                        esayBaoGaoBack.EsayBaoGaofail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableSubscriber);
    }

    //提交建议的接口
    public interface EsayBaoGaoBack
    {
        void EsayBaoGaosuccess(BaseBean<List<EsayBaoGaoBean>> listBaseBean);
        void EsayBaoGaofail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
