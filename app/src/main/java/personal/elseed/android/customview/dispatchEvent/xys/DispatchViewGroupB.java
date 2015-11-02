package personal.elseed.android.customview.dispatchEvent.xys;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class DispatchViewGroupB extends LinearLayout {

    public DispatchViewGroupB(Context context) {
        super(context);
    }

    public DispatchViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("seed", "ViewGroupB dispatchTouchEvent" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("seed", "ViewGroupB onInterceptTouchEvent" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("seed", "ViewGroupB onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }
}
