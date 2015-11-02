package personal.elseed.android.customview.dispatchEvent.xys;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DispatchView extends View {

    public DispatchView(Context context) {
        super(context);
    }

    public DispatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("seed", "View onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("seed", "View dispatchTouchEvent" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

}
