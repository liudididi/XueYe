package moudle;

import java.util.List;

import base.BaseBean;
import bean.NumBean;
import bean.ZYNumBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 祝文 on 2018/3/31.
 */

public class NumMoudle {

    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    //往年专业录取分数
    public void Num(String name, String classify, String stuProvince, final NumBack numBack)
    {
        DisposableSubscriber<BaseBean<List<NumBean>>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface().Universityinfo(name, classify, stuProvince)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<List<NumBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<NumBean>> listBaseBean) {
                        numBack.Numsuccess(listBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        numBack.Numfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
         compositeDisposable.add(disposableSubscriber);
    }
     //往年专业录取分数
    public void ZYNum(String name, String classify, String stuProvince,String major, final ZYNumBack zyNumBack)
    {
        DisposableSubscriber<BaseBean<List<ZYNumBean>>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface().UniversityinfoScores(name, classify, stuProvince, major)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<List<ZYNumBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<ZYNumBean>> listBaseBean) {
                        zyNumBack.ZYNumsuccess(listBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        zyNumBack.ZYNumfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        compositeDisposable.add(disposableSubscriber);
    }


    //获取院校专业录取分数
    public interface ZYNumBack
    {
        void ZYNumsuccess(BaseBean<List<ZYNumBean>> listBaseBean);
        void ZYNumfail(Throwable t);
    }
    //往年专业录取分数
    public interface NumBack
    {
        void Numsuccess(BaseBean<List<NumBean>> listBaseBean);
        void Numfail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
