package moudle;

import java.util.List;

import base.BaseBean;
import bean.XGcsBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 祝文 on 2018/3/20.
 */

public class    XGcsMoudle {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    //获取MBTI复杂分析报告
    public void XGcs(String testCode, final XGcsBack xGcsBack)
    {
        DisposableSubscriber<BaseBean<List<XGcsBean>>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface().getmbti(testCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<List<XGcsBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<XGcsBean>> listBaseBean) {
                        xGcsBack.XGcssuccess(listBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        xGcsBack.XGcsfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableSubscriber);
    }

    //获取MBTI复杂分析报告
    public interface XGcsBack
    {
        void XGcssuccess(BaseBean<List<XGcsBean>> listBaseBean);
        void XGcsfail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
