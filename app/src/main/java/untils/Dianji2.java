package untils;

/**
 * Created by 祝文 on 2018/4/14.
 */

public class Dianji2 {
    public static final int DELAY = 2000;
    private static long lastClickTime = 0;
    public static boolean isNotFastClick(){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > DELAY) {
            lastClickTime = currentTime;
            return true;
        }else{return false;}
    }
}
