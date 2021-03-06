package moudle;

import java.util.List;

import base.BaseBean;
import bean.ForecastBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 祝文 on 2018/2/23.
 */

public class ForecastMoudle {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    //获取预估对比分数
    public void Forecast(String province, String classify , String university ,String score, final ForecastBack forecastBack)
    {
        DisposableSubscriber<BaseBean<ForecastBean>> disposableSubscriber =
                MyQusetUtils.getInstance().getQuestInterface()
                .getscoreCompareMobil(province, classify, university,score)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<ForecastBean>>() {
                    @Override
                    public void onNext(BaseBean<ForecastBean> listBaseBean) {
                        forecastBack.Forecastsuccess(listBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        forecastBack.Forecastfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableSubscriber);
    }

    //获取预估对比分数的接口
    public interface ForecastBack
    {
        void Forecastsuccess(BaseBean<ForecastBean> listBaseBean);
        void Forecastfail(Throwable t);
    }
    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
