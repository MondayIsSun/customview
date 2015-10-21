package personal.elseed.android.customview;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1、View的测量与绘制
        setContentView(R.layout.measure_view_size);

        //2、Canvas相关API

        //3、自定义View

        //3.1、对现有的控件进行拓展——>直接集成现有的控件进行改造
        setContentView(R.layout.twinkle_text_view);

        //3.2、创建复合控件——>继承合适的ViewGroup(或就继承ViewGroup)——>组合现有的控件
        setContentView(R.layout.topbar_test);

        //3.3、自定义View实现全新的控件——>继承View，重写View的相关方法

        //4、View的事件传递机制
        setContentView(R.layout.dispatchevent);

    }
}
