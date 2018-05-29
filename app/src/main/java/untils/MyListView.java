package untils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by 地地 on 2018/5/17.
 * 邮箱：461211527@qq.com.
 */

public class MyListView extends ListView {

    private static final String TAG = "MyListView";

    public MyListView(Context context) {
        this(context, null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int defaultsize=measureHight(Integer.MAX_VALUE >> 2, heightMeasureSpec);
        int expandSpec = MeasureSpec.makeMeasureSpec(defaultsize, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }


    private int measureHight(int size, int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            Log.i(TAG, "exactly" );

            result = specSize;
        } else {

            result = size;//最小值是200px ，自己设定
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
            Log.i(TAG, "specMode:"+specMode+"--result:"+result );
        }
        return result;
    }


    /**
     * 改listview滑到底端了
     *
     * @return
     */
    public boolean isBottom() {
        int firstVisibleItem = getFirstVisiblePosition();//屏幕上显示的第一条是list中的第几条
        int childcount = getChildCount();//屏幕上显示多少条item
        int totalItemCount = getCount();//一共有多少条
        if ((firstVisibleItem + childcount) >=totalItemCount) {
            return true;
        }
        return false;
    }

    /**
     * 改listview在顶端
     *
     * @return
     */
    public boolean isTop() {
        int firstVisibleItem = getFirstVisiblePosition();
        if (firstVisibleItem ==0) {
            return true;
        }
        return false;
    }



}
