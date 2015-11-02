package personal.elseed.android.customview.dispatchEvent.lhy;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Administrator on 2015/11/2.
 */
public class MyButton extends Button {

    private static final String TAG = MyButton.class.getSimpleName();

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 引子：
     * <p/>
     * 1、按键事件传递：
     * 系统先捕获到按键事件——>
     * 系统把按键事件传递给顶层父控件——>
     * 父控件把事件传递给焦点子View
     * <p/>
     * 2、触摸事件传递：
     * 先发生=送给焦点子view,view自身接收到这个触摸事件 ——>
     * view自己处理这个触摸事件（处理逻辑，return true），——>
     * view return false,事件往上一层控件传递
     * <p/>
     * View事件相关一般就这三个地方了，
     * 一个onTouchEvent，
     * 一个dispatchTouchEvent，
     * 一个setOnTouchListener
     */
    /**
     * 不管是DOWN，MOVE，UP都会按照下面的顺序执行：
     * 1、dispatchTouchEvent
     * 2、 setOnTouchListener的onTouch
     * 3、onTouchEvent
     */

    /**
     * 1、View源码dispatchTouchEvent:
     * 也就是说：如果我们设置了setOnTouchListener，并且return true，
     * 那么View自己的onTouchEvent就不会被执行了，
     * 当然了，本例我们return false，我们还得往下探索 ;
     */

    /**
     * True if the event was handled by the view,
     * false otherwise.
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e("MyButton", "onTouchEvent ACTION_DOWN");
                Log.e("ButtonMyLinearLayout", "onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("MyButton", "onTouchEvent ACTION_MOVE");
                Log.e("ButtonMyLinearLayout", "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("MyButton", "onTouchEvent ACTION_UP");
                Log.e("ButtonMyLinearLayout", "onTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e("MyButton", "dispatchTouchEvent ACTION_DOWN");
                Log.e("ButtonMyLinearLayout", "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("MyButton", "dispatchTouchEvent ACTION_MOVE");
                Log.e("ButtonMyLinearLayout", "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("MyButton", "dispatchTouchEvent ACTION_UP");
                Log.e("ButtonMyLinearLayout", "dispatchTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

}
