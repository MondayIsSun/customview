package personal.elseed.android.customview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OutlookActivity extends Activity {

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent(this, DetailActivity.class);
    }

    /**
     * 0、View and ViewGroup的测量
     */
    public void btnTeaching(View view) {
        mIntent.putExtra("flag", 0);
        startActivity(mIntent);
    }

    public void btnMeasure(View view) {
        mIntent.putExtra("flag", 10);
        startActivity(mIntent);
    }

    public void btnMyMeasureTextView(View view) {
        mIntent.putExtra("flag", 16);
        startActivity(mIntent);
    }

    /**
     * 1、自定义控件——对现有控件进行拓展
     */
    public void btnMyTextView(View view) {
        mIntent.putExtra("flag", 1);
        startActivity(mIntent);
    }

    public void btnShineTextView(View view) {
        mIntent.putExtra("flag", 2);
        startActivity(mIntent);
    }

    /**
     * 2、自定义控件——重写View实现全新的控件
     */
    public void btnCircleProgress(View view) {
        mIntent.putExtra("flag", 3);
        startActivity(mIntent);
    }

    public void btnCircleImage(View view) {
        mIntent.putExtra("flag", 17);
        startActivity(mIntent);
    }

    public void btnVolumeView(View view) {
        mIntent.putExtra("flag", 4);
        startActivity(mIntent);
    }

    public void btnMyScrollView(View view) {
        mIntent.putExtra("flag", 5);
        startActivity(mIntent);
    }

    /**
     * 3、自定义控件——创建复合组件
     */
    public void btnTopBar(View view) {
        mIntent.putExtra("flag", 6);
        startActivity(mIntent);
    }

    /**
     * View and ViewGroup的事件传递机制
     */
    public void btnDispatchEvent(View view) {
        mIntent.putExtra("flag", 7);
        startActivity(mIntent);
    }

    public void btnDispatchMyButton(View view) {
        mIntent.putExtra("flag", 8);
        startActivity(mIntent);
    }

    public void btnDispatchMyLinearLayout(View view) {
        mIntent.putExtra("flag", 9);
        startActivity(mIntent);
    }
}
