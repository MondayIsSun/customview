package personal.elseed.android.customview.dispatchEvent.xys;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class DispatchViewGroupA extends LinearLayout {

    public DispatchViewGroupA(Context context) {
        super(context);
    }

    public DispatchViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("seed", "ViewGroupA dispatchTouchEvent" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("seed", "ViewGroupA onInterceptTouchEvent" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("seed", "ViewGroupA onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }
}
