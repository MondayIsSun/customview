package personal.elseed.android.customview.customView.b;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import personal.elseed.android.customview.R;

public class TopBarTest extends Activity {

    private MyTopBar mTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topbar_test);

        //获得我们创建的topBar
        mTopBar = (MyTopBar) findViewById(R.id.topBar);

        //为topBar注册监听事件，传入定义的接口
        //并以匿名类的方式实现接口内的方法
        mTopBar.setOnTopBarClickListener(
                new MyTopBar.TopBarClickListener() {

                    @Override
                    public void rightClick() {
                        Toast.makeText(TopBarTest.this, "right", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void leftClick() {
                        Toast.makeText(TopBarTest.this, "left", Toast.LENGTH_SHORT).show();
                    }
                });

        //控制topBar上组件的状态
        mTopBar.setButtonVisible(0, true);
        mTopBar.setButtonVisible(1, true
        );
    }
}
