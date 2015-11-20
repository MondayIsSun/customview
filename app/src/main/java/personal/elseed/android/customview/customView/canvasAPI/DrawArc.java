package personal.elseed.android.customview.customView.canvasAPI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/11/19.
 */
public class DrawArc extends View {

    public DrawArc(Context context) {
        super(context);
    }

    public DrawArc(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawArc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawColor(Color.WHITE);
//        drawArcs(canvas, mBigOval, mUseCenters[mBigIndex],
//                mPaints[mBigIndex]);
//
//        for (int i = 0; i < 4; i++) {
//            drawArcs(canvas, mOvals[i], mUseCenters[i], mPaints[i]);
//        }
//
//        mSweep += SWEEP_INC;
//        if (mSweep > 360) {
//            mSweep -= 360;
//            mStart += START_INC;
//            if (mStart >= 360) {
//                mStart -= 360;
//            }
//            mBigIndex = (mBigIndex + 1) % mOvals.length;
//        }
//        invalidate();
//    }
}
