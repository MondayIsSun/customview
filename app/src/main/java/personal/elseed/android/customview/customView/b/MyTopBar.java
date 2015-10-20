package personal.elseed.android.customview.customView.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import personal.elseed.android.customview.R;

/**
 * Created by Administrator on 2015/10/15.
 */
public class MyTopBar extends RelativeLayout {

    /**
     * 自定义View方式2：创建复合控件
     */

    // 包含topBar上的元素：左按钮、右按钮、标题
    private TextView mTitleTextView;
    private Button mLeftButton;
    private Button mRightButton;

    // 布局属性，用来控制组件元素在ViewGroup中的位置
    private LayoutParams mLeftParams, mTitleParams, mRightParams;

    // 标题的属性值，即我们在attrs.xml文件中定义的属性
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitleText;

    // 左按钮的属性值，即我们在attrs.xml文件中定义的属性
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;

    // 右按钮的属性值，即我们在attrs.xml文件中定义的属性
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;

    public MyTopBar(Context context) {
        super(context);
    }

    public MyTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        //设置MyTopBar的默认背景
        setBackgroundColor(0xFFF59563);

        //通过这个方法，将你在attrs.xml中定义的declare-styleable的所有属性的值存储到TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTopBar);

        //从TypedArray中取出对应的值来为要设置的属性赋值
        mTitleTextSize = ta.getDimension(R.styleable.MyTopBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.MyTopBar_titleTextColor, 0);
        mTitleText = ta.getString(R.styleable.MyTopBar_titleText);

        mLeftTextColor = ta.getColor(R.styleable.MyTopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.MyTopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.MyTopBar_leftText);

        mRightTextColor = ta.getColor(R.styleable.MyTopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.MyTopBar_rightBackground);
        mRightText = ta.getString(R.styleable.MyTopBar_rightText);

        //获取完TypedArray的值后，一般要调用recycle方法来避免重新创建的时候的错误
        ta.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleTextView = new TextView(context);

        // 为创建的组件元素赋值,值就来源于我们在引用的xml文件中给对应属性的赋值
        mTitleTextView.setText(mTitleText);
        mTitleTextView.setTextColor(mTitleTextColor);
        mTitleTextView.setTextSize(mTitleTextSize);
        mTitleTextView.setGravity(Gravity.CENTER);

        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        // 为组件元素设置相应的布局元素
        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        // 添加到ViewGroup
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleTextView, mTitleParams);

        // 按钮的点击事件，不需要具体的实现，只需调用接口的方法，回调的时候，会有具体的实现
        mRightButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });

        mLeftButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
    }

    //接口对象，实现回调机制，在回调方法中
    //通过映射的接口对象调用接口中的方法
    //而不用去考虑如何实现，具体的实现由调用者去创建
    public interface TopBarClickListener {

        //左按钮点击事件
        void leftClick();

        //右按钮点击事件
        void rightClick();
    }

    //映射传入的接口对象
    private TopBarClickListener mListener;

    //暴露一个方法给调用者来注册接口回调
    //通过接口来获得回调者对接口方法的实现
    public void setOnTopBarClickListener(TopBarClickListener mListener) {//由调用者注入自己的实现逻辑
        this.mListener = mListener;
    }

    /**
     * 设置按钮的显示与否 通过id区分按钮，flag区分是否显示
     *
     * @param id   id
     * @param flag 是否显示
     */
    public void setButtonVisible(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftButton.setVisibility(View.VISIBLE);
            } else {
                mRightButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftButton.setVisibility(View.GONE);
            } else {
                mRightButton.setVisibility(View.GONE);
            }
        }
    }

}