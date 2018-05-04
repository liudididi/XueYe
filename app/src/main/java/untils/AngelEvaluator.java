package untils;

import android.animation.TypeEvaluator;

/**
 * Created by 地地 on 2018/4/26.
 * 邮箱：461211527@qq.com.
 */

public class AngelEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object Value) {
        int startAngel = (int)startValue;
        int Angel = (int)Value;
        int value = (int) (startAngel + (Angel) *fraction);
        return value;
    }
}
