package moudle;

import java.util.List;

import base.BaseBean;
import bean.ForecastBean;
import bean.GailvBean;
import bean.LuquXianBean;
import bean.SchoolEnrollBean;
import bean.ZYTJBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import untils.MyQusetUtils;

/**
 * Created by 祝文 on 2018/2/5.
 */

public class SchoolEnrollMoudle {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    //大学录取的专业招生计划
    public void SchoolEnroll(String name, String province, String type, final SchoolEnrollBack schoolEnrollBack )
    {
        DisposableSubscriber<BaseBean<List<SchoolEnrollBean>>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface().schoolenroll(name, province, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<List<SchoolEnrollBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<SchoolEnrollBean>> listBaseBean) {
                        schoolEnrollBack.SchoolEnrollsuccess(listBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        schoolEnrollBack.SchoolEnrollfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableSubscriber);
    }

//概率
public  void  getscoreCompareMobil(String province, String classify, String  schoolname,String scouse, final GailvBeanBack gailvBeanBack){

    DisposableSubscriber<BaseBean<ForecastBean>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface()
            .getscoreCompareMobil(province, classify, schoolname,scouse)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSubscriber<BaseBean<ForecastBean>>() {
                @Override
                public void onNext(BaseBean<ForecastBean> listBaseBean) {
                       gailvBeanBack.SchoolEnrollsuccess(listBaseBean);
                }

                @Override
                public void onError(Throwable t) {
                 gailvBeanBack.SchoolEnrollfail(t);
                }

                @Override
                public void onComplete() {

                }
            });
    compositeDisposable.add(disposableSubscriber);

}



//查询录取分数线

public  void  getskx(String province, String university, String classify, String time,String line, final SLuquXianBeanBack sLuquXianBeanBack){


    DisposableSubscriber<BaseBean<List<LuquXianBean>>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface()
            .getlqx(province, university, classify, time, line)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSubscriber<BaseBean<List<LuquXianBean>>>() {
                @Override
                public void onNext(BaseBean<List<LuquXianBean>> listBaseBean) {
                    sLuquXianBeanBack.LuquXianBeansuccess(listBaseBean);
                }

                @Override
                public void onError(Throwable t) {
                    sLuquXianBeanBack.LuquXianBeanfail(t);
                }

                @Override
                public void onComplete() {

                }
            });

    compositeDisposable.add(disposableSubscriber);

}

    //获取重点专业或特色专业
    public  void  getTZmajor(String name, int type, final TZmajorBack tZmajorBack ){

        DisposableSubscriber<BaseBean<List<ZYTJBean>>> disposableSubscriber = MyQusetUtils.getInstance().getQuestInterface()
                .getTZmajor(name, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseBean<List<ZYTJBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<ZYTJBean>> listBaseBean) {
                        tZmajorBack.TZmajorsuccess(listBaseBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        tZmajorBack.TZmajorfail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

         compositeDisposable.add(disposableSubscriber);

    }


    //获取重点专业或特色专业
    public interface TZmajorBack
    {
        void TZmajorsuccess(BaseBean<List<ZYTJBean>> listBaseBean);
        void TZmajorfail(Throwable t);
    }

    //大学录取的专业招生计划的接口
    public interface SchoolEnrollBack
    {
        void SchoolEnrollsuccess(BaseBean<List<SchoolEnrollBean>> listBaseBean);
        void SchoolEnrollfail(Throwable t);
    }


    public interface SLuquXianBeanBack
    {
        void LuquXianBeansuccess(BaseBean<List<LuquXianBean>> BaseBean);
        void LuquXianBeanfail(Throwable t);
    }

    public interface GailvBeanBack
    {
        void SchoolEnrollsuccess(BaseBean<ForecastBean> listBaseBean);
        void SchoolEnrollfail(Throwable t);
    }

    public void  onDestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }


}
