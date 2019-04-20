j

[TOC]



# 登录和注册功能app



## 一.UI设计

这里我想要仿照现在的qq登录界面，实现不止隐藏ActionBar，还要隐藏顶端状态栏，实现全屏幕的沉浸式体验

### 1.实现隐藏状态栏和全屏幕

在res-values-styles.xml中添加一个我们自制的theme主题

```xml
  <style name="FirstTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="android:windowNoTitle">true</item>
        <item name="android:windowFullscreen">true</item>
    </style>
```

然后在AndroidManifest.xml中在我们需要的activity下面添加我们自制的这个theme

```xml
<activity
            android:name=".MainActivity"
            android:label="FirstActivity"
            android:theme="@style/FirstTheme">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
```

以上的这种方法是我看见的容易实现的一种，还有一种方式需要一些条件，在我的手机上无法运行，可以参考一下

[Android中隐藏顶部状态栏的那些坑]: https://www.cnblogs.com/shen-hua/p/6082957.html

### 2.图片设置为背景被拉伸的解决方案

我刚开始直接将这个图片设置为src，但是发现并不能填满屏幕；我又将图片设置为background，但是很遗憾图片被拉伸变形了，虽然在隐藏状态栏加全屏显示后图片能完整的填满屏幕，但是针对另外一些情况，可以将图片适当放大，即使用ImageView控件的scaleType属性。详情见

[Android--UI之ImageView]: https://www.cnblogs.com/plokmju/p/android__ImageView.html

### 3.设置圆角Button按钮

在res右键创建新的Android Resource File，设置name为btn_selector，element选择为selector，设置按下和不按下的shape样式

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:drawable="@drawable/btn_shape_normal" android:state_pressed="false" />
    <item android:drawable="@drawable/btn_shape_pressed" android:state_pressed="true" />

</selector>
```

然后新建btn_shape_normal和btn_shape_pressed

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <corners
        android:radius="10dp" />
    <size
        android:height="45dp"
        android:width="45dp" />
    <stroke
        android:color="#fff"
        android:width="1dp" />

</shape>
```

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <corners
        android:radius="10dp" />
    <size
        android:height="45dp"
        android:width="45dp" />
    <stroke
        android:color="#fff"
        android:width="1dp" />
    <solid
        android:color="#b1ccda" />

</shape>
```

详细属性设置如下：

![img](https://img-blog.csdn.net/20170108225617254?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGVpbGlmZW5neGluZ213/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 4.设置圆形图片

在网上看到的一种自制CircleImageView的代码实现

## 二.运用数据库功能

### 1.实现退出登录活动后再进入可以记住账号

按照第一行代码上的写法，无法实现，我暂时没有办法，在探索中

### 2.实现记住密码

按照第一行代码上的写法，无法实现，我暂时没有办法，在探索中

在

```java
String account = accountEdit.getText().toString();
String password = passwordEdit.getText().toString();
```

这两行虽然不报错，但是Logcat显示在这里程序异常退出

在探索中
