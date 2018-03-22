package moudle;

import java.util.List;

import base.BaseBean;
import bean.EsaySdsBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 祝文 on 2018/3/22.
 */

public class EsayHuanLanDeMoudle {

    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    //获取SDS简单分析报告
    public void EsayHuanLanDe(String testCode, final EsayHuanLanDeBack esayHuanLanDeBack)
    {
        DisposableSubscriber<BaseBean<List<EsaySdsBean>>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface().getsdsE(testCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<List<EsaySdsBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<EsaySdsBean>> listBaseBean) {
                        esayHuanLanDeBack.EsayHuanLanDesuccess(listBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        esayHuanLanDeBack.EsayHuanLanDefail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableSubscriber);
    }

    //获取SDS简单分析报告
    public interface EsayHuanLanDeBack
    {
        void EsayHuanLanDesuccess(BaseBean<List<EsaySdsBean>> listBaseBean);
        void EsayHuanLanDefail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
