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

> * 系统是个盲人，系统绘制View（ViewGroup + ViewGroup包含的子View）
> * 子View由包含它的ViewGroup进行绘制，所以子View必须告诉要绘制它的ViewGroup自己的大小以及要绘制的位置
> * 同样ViewGroup由包含它的ViewGroup绘制，所以ViewGroup同样也要告诉要绘制它的ViewGroup自己的大小以及要绘制的位置
> * 这样一直递归，系统就完成了一组View的绘制
> * View的大小的测量由 **onMeasure()** 实现，View绘制的位置由 **onLayout()** 实现，最后View的绘制由 **onDraw()** 实现

### 2、View的测量与绘制

#### 三种测量模式

- MeasureSpec.**EXACTLY** ==> layout\_width / layout\_height = 具体值 或 " match\_parent "
- MeasureSpec.**AT\_MOST** ==> layout\_width / layout\_height = " wrap\_content "
- MeasureSpec.**UNSPECIFIED** ==> 不指定测量模式，View想多大就多大，自定义View时候会用到

#### MeasureSpec

> Android提供了MeasureSpec类来帮助开发者测量View

#### 测量onMeasure()

> onMeasure()默认测量模式是**EXACTLY**

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

### 3、ViewGroup的测量与绘制

> ViewGroup通常情况下不需要绘制，因为其本身一般没有需要绘制的东西 ——ViewGroup是一种容器View，
> 如果没有指定ViewGroup的背景颜色，ViewGroup的onDraw()方法都不会被调用，
> 但是，ViewGroup会使用 **dispatchDraw()** 方法来绘制其包含的子View，其过程同样是通过遍历所有的子View，并调用子View的绘制方法来完成绘制

## 三、自定义View的三种方式

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

### 4、自定View需要掌握的知识点
- Canvas API
- Paint API
- Android自定义属性，TypeArray的用法

## 四、Android事件拦截/传递机制

>[Android View事件分发机制](http://blog.csdn.net/lmj623565791/article/details/38960443)

>[Android ViewGroup事件分发机制](http://blog.csdn.net/lmj623565791/article/details/39102591)

## 五、Android UI性能优化

>[Android UI性能优化实战 识别绘制中的性能问题](http://blog.csdn.net/lmj623565791/article/details/45556391)