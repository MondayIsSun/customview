package personal.elseed.android.customview.measureView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2015/10/15.
 */
public class MeasureView extends View {

    public MeasureView(Context context) {
        super(context);
    }

    public MeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(200, widthMeasureSpec), measureSize(200, heightMeasureSpec));
    }

    private int measureSize(int size, int measureSpec) {
        int result = size;

        //获取测量模式和具体值
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        Log.d("ELSeed", "" + specSize);

        //根据测量模式来设置返回到测量值
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(specSize, size);
                break;
        }
        return result;
    }
}
