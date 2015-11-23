package personal.elseed.android.customview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import personal.elseed.android.customview.customView.extendsView.CircleProgressView;
import personal.elseed.android.customview.customView.extendsView.RollCircleImageView;
import personal.elseed.android.customview.customView.extendsViewGroup.MyTopBarView;
import personal.elseed.android.customview.dispatchEvent.lhy.MyButton;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = getIntent().getIntExtra("flag", -1);
        switch (flag) {
            case 0:
                setContentView(R.layout.teaching);
                break;
            case 10:
                setContentView(R.layout.measure_view_size);
            case 1:
                setContentView(R.layout.my_textview);
                break;
            case 2:
                setContentView(R.layout.shine_textview);
                break;
            case 3:
                setContentView(R.layout.circle_progress);
                CircleProgressView circle = (CircleProgressView) findViewById(R.id.circle);
                circle.setSweepValue(0);
                break;
            case 4:
                setContentView(R.layout.volume);
                break;
            case 5:
                setContentView(R.layout.my_scrollview);
                break;
            case 6:
                setContentView(R.layout.topbar_test);

                MyTopBarView mTopBar = (MyTopBarView) findViewById(R.id.topBar);

                //为topBar注册监听事件，传入定义的接口
                //并以匿名类的方式实现接口内的方法
                mTopBar.setOnTopBarClickListener(
                        new MyTopBarView.TopBarClickListener() {

                            @Override
                            public void rightClick() {
                                Toast.makeText(DetailActivity.this, "right", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void leftClick() {
                                Toast.makeText(DetailActivity.this, "left", Toast.LENGTH_SHORT).show();
                            }
                        });

                //控制topBar上组件的状态
                mTopBar.setButtonVisible(0, true);
                mTopBar.setButtonVisible(1, true
                );
                break;
            case 7:
                setContentView(R.layout.dispatchevent);
                break;
            case 8:
                setContentView(R.layout.dispatch_mybutton);
                MyButton mButton = (MyButton) findViewById(R.id.id_btn);
                mButton.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int action = event.getAction();

                        switch (action) {
                            case MotionEvent.ACTION_DOWN:
                                Log.e("MyButton", "onTouch ACTION_DOWN");
                                break;
                            case MotionEvent.ACTION_MOVE:
                                Log.e("MyButton", "onTouch ACTION_MOVE");
                                break;
                            case MotionEvent.ACTION_UP:
                                Log.e("MyButton", "onTouch ACTION_UP");
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                break;
            case 9:
                setContentView(R.layout.dispatch_mylinearlayout);
                break;
            case 16:
                setContentView(R.layout.mymeasuretextview);
                break;
            case 17:
                setContentView(R.layout.roll_circle_image);
                RollCircleImageView circleImageView = (RollCircleImageView) findViewById(R.id.circle_image);
                circleImageView.setSweepAngle((int)(Math.random()*360.0));
                circleImageView.setBorderWidth(2);
                circleImageView.setBorderColor(Color.BLACK);
                break;
        }
    }
}
