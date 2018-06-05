package untils;

import android.content.Context;
import android.util.Log;

/**
 * Created by 地地 on 2018/6/2.
 * 邮箱：461211527@qq.com.
 */

public class Myexception implements  Thread.UncaughtExceptionHandler {
    private  static  final String Tag="Myexception";
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private static Myexception myinstans;
    private Context context;

    public static Myexception getinstants( ) {
       if(myinstans==null){
           synchronized (Myexception.class){
               if(myinstans==null){
                   myinstans=new Myexception();
               }
           }
       }
       return myinstans;
    };

    public  void  init(Context context){
        this.context=context;
        Thread.currentThread().setUncaughtExceptionHandler(this);
        uncaughtExceptionHandler = Thread.currentThread().getUncaughtExceptionHandler();

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
         Log.e(Tag,"报异常了");
        uncaughtExceptionHandler.uncaughtException(t,e);
    }
}
