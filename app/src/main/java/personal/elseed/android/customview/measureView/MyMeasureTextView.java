package personal.elseed.android.customview.measureView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import personal.elseed.android.customview.R;

public class MyMeasureTextView extends View {

    //画笔
    private Paint mPaint;

    //文字绘制的区域
    private Rect mBound;
    private int mBoundColor;

    //绘制的文字信息
    private String mText;
    private int mTextSize;
    private int mTextColor;

    public MyMeasureTextView(Context context) {
        this(context, null);
    }

    public MyMeasureTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyMeasureTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         * 获取自定义的属性
         */
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyMeasureTextView, defStyleAttr, 0);
        int attrsCnt = typedArray.getIndexCount();
        for (int i = 0; i < attrsCnt; i++) {
            int idx = typedArray.getIndex(i);
            switch (idx) {
                case R.styleable.MyMeasureTextView_text_content:
                    mText = typedArray.getString(idx);
                    break;
                case R.styleable.MyMeasureTextView_text_color:
                    mTextColor = typedArray.getColor(idx, Color.BLACK);
                    break;
                case R.styleable.MyMeasureTextView_text_size:
                    //默认设置16sp,TypeValue也可以把sp转化为px
                    mTextSize = typedArray.getDimensionPixelSize(idx, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MyMeasureTextView_bound_bg_color:
                    mBoundColor = typedArray.getColor(idx, Color.BLUE);
                    break;
            }
        }
        typedArray.recycle();

        /**
         * 初始化
         */
        mBound = new Rect();
        mPaint = new Paint();
    }

    /**
     * 为了同时支持layout_width,match_parent,wrap_content
     * 需要Override onMeasure()
     * <p/>
     * 注意：onMeasure()默认情况下是EXACTLY，即：只支持 通过xml的值 和 match_parent 设置自定义控件的大小，
     * 要实现wrap_content方式来这是控件的大小，需要对AT_MOST的情况自己测量控件的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取测量模式，xml当中如果设置的是具体到值或者match_parent的情况，那么测量模式就是EXACTLY
        //xml当中设置的是wrap_content，那么测量模式就是AT_MOST
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //真正测量的宽高
        int width;
        int height;

        //测量宽
        if (widthMode == MeasureSpec.EXACTLY) {//match_parent + 指定宽高的情况，那么该绘制的宽高就是xml当中设置的宽高，就是通过MeasureSpec获取到的width和height
            width = widthSize;
        } else {//AT_MOST(wrap_content)的情况
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float textWidth = mBound.width();
            width = (int) (getPaddingLeft() + textWidth + getPaddingRight());
        }

        //测量高
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float textHeight = mBound.height();
            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
        }

        //设置测量值
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制文字区域
        mPaint.setColor(mBoundColor);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        //绘制文字信息
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mText, 0, mText.length(), mBound);
        canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}
