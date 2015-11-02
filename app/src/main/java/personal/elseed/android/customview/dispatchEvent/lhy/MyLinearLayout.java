package personal.elseed.android.customview.dispatchEvent.lhy;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2015/11/2.
 */
public class MyLinearLayout extends LinearLayout {

    private static final String TAG = MyLinearLayout.class.getSimpleName();

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 大体的事件流程为：
     * MyLinearLayout的dispatchTouchEvent ->
     * MyLinearLayout的onInterceptTouchEvent ->
     * MyButton的dispatchTouchEvent ->
     * MyButton的onTouchEvent
     * <p/>
     * 可以看出，在View上触发事件，最先捕获到事件的为View所在的ViewGroup，然后才会到View自身~
     */

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e("MyLinearLayout", "onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("MyLinearLayout", "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("MyLinearLayout", "onTouchEvent ACTION_UP");
                break;

            default:
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onInterceptTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onInterceptTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onInterceptTouchEvent ACTION_UP");
                break;

            default:
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        Log.e(TAG, "requestDisallowInterceptTouchEvent ");
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
}
