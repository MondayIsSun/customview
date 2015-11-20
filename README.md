# Android自定义控件详解

	this module will tell you how to create custom view that non-system.

## 一、Android控件架构

	know the sub-view and container.

![image](https://github.com/MondayIsSun/customview/raw/master/screenshots/viewgroup.png)

![image](https://github.com/MondayIsSun/customview/raw/master/screenshots/uijiagoutu.png)

![image](https://github.com/MondayIsSun/customview/raw/master/screenshots/bzsts.png)

![image](https://github.com/MondayIsSun/customview/raw/master/screenshots/viewframwork.png)

## 二、View的绘制流程

### 1、引子

> * 系统是个**“盲人”**，系统绘制View（ViewGroup + ViewGroup包含的子View）
> * 子View由包含它的ViewGroup进行绘制，所以子View必须告诉要绘制它的ViewGroup自己的大小以及要绘制的位置
> * 同样ViewGroup由包含它的ViewGroup绘制，所以ViewGroup同样也要告诉要绘制它的ViewGroup自己的大小以及要绘制的位置
> * 这样一直递归，系统就完成了一组View的绘制
> * View的大小的测量由 **onMeasure()** 实现，View绘制的位置由 **onLayout()** 实现，最后View的绘制由 **onDraw()** 实现



### 2、View的测量与绘制

#### 三种测量模式(MeasureSpec的三种specMode)

- MeasureSpec.**EXACTLY** ==> layout\_width / layout\_height = 具体值 或 " match\_parent "时采用的测量模式
- MeasureSpec.**AT\_MOST** ==> layout\_width / layout\_height = " wrap\_content "时采用的测量模式
- MeasureSpec.**UNSPECIFIED** ==> 不指定测量模式，View想多大就多大，自定义View时候会用到

#### MeasureSpec

> Android提供了MeasureSpec类来帮助开发者测量View

#### 测量onMeasure()

子View自己测量自己的高宽，但是目的是为了让父View调用，告诉父View自己的大小

> 注意：onMeasure()默认采用的测量模式是**EXACTLY**（注意：还是老老实实自己去计算测量View的尺寸吧）

	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(widthMeasureSpec), measureSize(heightMeasureSpec));
    }
	
	//可以做模板代码
	private int measureSize(int measureSpec) {
        int result;

        //获取测量模式和具体值
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        //根据测量模式设置返回的测量值
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
			//设置默认值
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(specSize, result);
            }
        }
        return result;
    }

[Android 自定义View (一)：onMeasure知识点链接](http://blog.csdn.net/lmj623565791/article/details/24252901)

### 3、ViewGroup的测量与绘制

> ViewGroup通常情况下不需要绘制，因为其本身一般没有需要绘制的东西 ——ViewGroup是一种容器View，
> 如果没有指定ViewGroup的背景颜色，ViewGroup的onDraw()方法都不会被调用，
> 但是，ViewGroup会使用 **dispatchDraw()** 方法来绘制其包含的子View，其过程同样是通过遍历所有的子View，并调用子View的绘制方法来完成绘制

### 4、ViewGroup和LayoutParams之间的关系？

ViewGroup.LayoutParams——>LayoutParams are used by views to tell their parents how they want to be laid out.——>laid out：安排，陈列

ViewGroup子类可以实现自定义LayoutParams，自定义LayoutParams提供了更好地扩展性，例如LinearLayout就有LinearLayout. LayoutParams自定义类(见下文)。

1、	直接添加子View时，常见于如下几种方法：ViewGroup.java
	//Adds a child view.      
	void addView(View child, int index)  
	
	//Adds a child view with this ViewGroup's default layout parameters   
	//and the specified width and height.  
	void addView(View child, int width, int height) 
	
	//Adds a child view with the specified layout parameters.         
	void addView(View child, ViewGroup.LayoutParams params) 

2、	通过xml布局文件指定某个View的属性为：android:layout_heigth=””以及android:layout_weight=”” 时。总的来说，这两种方式都会设定View的LayoutParams属性值----指定的或者Default值。

大家可以回忆一下，当在LinearLayout中写childView的时候，可以写layout_gravity，layout_weight属性；在RelativeLayout中的childView有layout_centerInParent属性，却没有layout_gravity，layout_weight，这是为什么呢？这是因为每个ViewGroup需要指定一个LayoutParams，用于确定支持childView支持哪些属性，比如LinearLayout指定LinearLayout.LayoutParams等。如果大家去看LinearLayout的源码，会发现其内部定义了LinearLayout.LayoutParams，在此类中，你可以发现weight和gravity的身影。

## 三、自定义View的三种方式

> 自定义View的一般步骤：
> 
> 自定义View的属性——>首先在res/values/下建立一个attrs.xml，在里面定义属性和声明我们的整个样式

	<?xml version="1.0" encoding="utf-8"?>  
	<resources>  
	    <attr name="titleText" format="string" />  
	    <attr name="titleTextColor" format="color" />  
	    <attr name="titleTextSize" format="dimension" />  
	  
	    <declare-styleable name="CustomTitleView">  
	        <attr name="titleText" />  
	        <attr name="titleTextColor" />  
	        <attr name="titleTextSize" />  
	    </declare-styleable>  
	</resources>

我们定义了字体，字体颜色，字体大小3个属性，format是值该属性的取值类型:
一共有：string,color,demension,integer,enum,reference,float,boolean,fraction,flag等等。

[Android 深入理解Android中的自定义属性](http://blog.csdn.net/lmj623565791/article/details/45022631)

> 使用TypedArray在View的构造函数中获得我们自定义的属性

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyle, 0);
		//a.getDimension(R.styleable.MyTopBarView_titleTextSize, 10);
        int n = a.getIndexCount();  
        for (int i = 0; i < n; i++) {  
            int attr = a.getIndex(i);  
            switch (attr) {  
            case R.styleable.CustomTitleView_titleText:  
                mTitleText = a.getString(attr);  
                break;  
            case R.styleable.CustomTitleView_titleTextColor:  
                // 默认颜色设置为黑色  
                mTitleTextColor = a.getColor(attr, Color.BLACK);  
                break;  
            case R.styleable.CustomTitleView_titleTextSize:  
                // 默认设置为16sp，TypeValue也可以把sp转化为px  
                mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(  
                        TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));  
                break;  
  
            }  
  
        }  
        a.recycle(); 

> 重写onMeasure()——>当设置了WRAP_CONTENT时，我们需要自己进行测量，即重写onMesure方法
> 
> 重写onDraw()

### 1、对现有的控件进行扩展

### 2、创建复合控件——>这种方式通常需要继承一个合适的ViewGroup
>MyTopBar extends RelativeLayout（ViewGroup）,
>
>Android自定义属性，灵活控制控件的呈现,
>
>TypeArray,自定义属性与代码的绑定，
>
>接口回调机制，灵活控制控件的用户响应，
>
>在Layout布局文件里面使用自定义组件

### 3、重写View实现全新的控件

### 4、自定义ViewGroup

[Android 手把手教您自定义ViewGroup（一）](http://blog.csdn.net/lmj623565791/article/details/38339817)

### 5、自定View需要掌握的知识点
- Canvas API
- Paint API
- Android自定义属性，TypeArray的用法

## 四、Android事件拦截/传递机制

>[Android MotionEvent](http://blog.csdn.net/bigconvience/article/details/26611003)

>[Android View事件分发机制](http://blog.csdn.net/lmj623565791/article/details/38960443)

>[Android ViewGroup事件分发机制](http://blog.csdn.net/lmj623565791/article/details/39102591)

## 五、Android UI性能优化

>[Android UI性能优化实战 识别绘制中的性能问题](http://blog.csdn.net/lmj623565791/article/details/45556391)