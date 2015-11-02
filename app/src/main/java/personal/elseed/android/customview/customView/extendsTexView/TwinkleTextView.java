package personal.elseed.android.customview.customView.extendsTexView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/10/15.
 */
public class TwinkleTextView extends TextView {

    /**
     * 自定义View方式1：对现有的控件进行拓展
     */

    private Paint mPaint1;
    private Paint mPaint2;

    public TwinkleTextView(Context context) {
        super(context);
        init();
    }

    public TwinkleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TwinkleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    /**
     * 程序调用onDraw方法来完成View的绘制
     * 但是在onDraw方法的调用之前之后我们可以做点什么
     */
    @Override
    protected void onDraw(Canvas canvas) {

        //在调用父方法之前实现自己的逻辑，对TextView来说是显示文本内容之前
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, mPaint2);
        canvas.save();
        canvas.translate(10, 0);

        //调用父方法实现原生控件的绘制
        super.onDraw(canvas);

        //在调用父方法之后实现自己的逻辑，对TextView来说是显示文本内容后
        canvas.restore();
    }
}
