# Android自定义控件详解

	this module will tell you how to create custom view that non-system.

## 1、Android控件架构

	know the sub-view and container.

![image](https://github.com/MondayIsSun/customview/raw/master/screenshots/viewgroup.png)

![image](https://github.com/MondayIsSun/customview/raw/master/screenshots/uijiagoutu.png)

![image](https://github.com/MondayIsSun/customview/raw/master/screenshots/bzsts.png)

![image](https://github.com/MondayIsSun/customview/raw/master/screenshots/viewframwork.png)

## 2、View的绘制流程

> 1. 系统是个盲人，系统绘制View（ViewGroup + ViewGroup包含的子View）
> 2. 子View由包含它的ViewGroup进行绘制，所以子View必须告诉要绘制它的ViewGroup自己的大小以及要绘制的位置
> 3. 同样ViewGroup由包含它的ViewGroup绘制，所以ViewGroup同样也要告诉要绘制它的ViewGroup自己的大小以及要绘制的位置
> 4. 这样一直递归，系统就完成了一组View的绘制
> 5. View的大小的测量由 **onMeasure()** 实现，View绘制的位置由 **onLayout()** 实现，最后View的绘制由 **onDraw()** 实现

### View的测量与绘制

### ViewGroup的测量与绘制
> ViewGroup通常情况下不需要绘制，因为其本身一般没有需要绘制的东西 ——ViewGroup是一种容器View，
> 如果没有指定ViewGroup的背景颜色，ViewGroup的onDraw()方法都不会被调用，
> 但是，ViewGroup会使用 **dispatchDraw()** 方法来绘制其包含的子View，其过程同样是通过遍历所有的子View，并调用子View的绘制方法来完成绘制

## 4、自定义View的三种方式

* 对现有的控件进行扩展

* 创建复合控件

* 重写View实现全新的控件

## 5、Android事件拦截/传递机制

>[Android ViewGroup事件分发机制](http://blog.csdn.net/lmj623565791/article/details/39102591)

>[Android View 事件分发机制源码解析](http://blog.csdn.net/lmj623565791/article/details/38960443)

## 6、Android UI性能优化

>[Android UI性能优化实战 识别绘制中的性能问题](http://blog.csdn.net/lmj623565791/article/details/45556391)