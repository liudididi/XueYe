package moudle;

import java.util.List;

import base.BaseBean;
import bean.AnswerBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 地地 on 2018/3/27.
 * 邮箱：461211527@qq.com.
 */

public class YfenQMoudle {


    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    public void yfJudge(String wishType, String token, final YfenQBack yfenQBack ){


        DisposableSubscriber<BaseBean> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface().yfJudge(wishType, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        yfenQBack.YfenQsuccess(baseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        yfenQBack.YfenQfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        compositeDisposable.add(disposableSubscriber);
    }

    public interface YfenQBack
    {
        void YfenQsuccess(BaseBean  listBaseBean);
        void YfenQfail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
