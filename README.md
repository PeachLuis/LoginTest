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

可以采用SharedPreferences储存方法，编写一个save方法和一个read方法，再在代码中进行调用。

但是考虑到活动的生命期，应该是将save方法放在登录按钮的点击事件当中，而read方法放在重写的onResume（）当中。

save方法：

```java
private void save() {
        SharedPreferences.Editor editor = getSharedPreferences("account_password",MODE_PRIVATE).edit();
        editor.putString("account", account.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.apply();
    }
```

read方法：

```java
private void read() {
        SharedPreferences pref = getSharedPreferences("account_password", MODE_PRIVATE);
        account.setText(pref.getString("account", ""));
        password.setText(pref.getString("password",""));
    }
```

关于SharePreferences的更多知识请看这里：

[传送门]: https://www.cnblogs.com/smyhvae/p/4019379.html



### 2.实现记住密码

这里就在xml文件中添加复选框CheckBox，

```xml
 <LinearLayout
        android:id="@+id/linear_1"
        android:layout_below="@id/password"
        android:layout_alignLeft="@+id/password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <CheckBox
            android:id="@+id/remember_pass"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="18dp"
            android:text="记住密码"/>
    </LinearLayout>
```

然后在save和read方法中添加逻辑

```java
public class LoginActivity extends AppCompatActivity {
    private EditText account,password;
    private CheckBox rememberPassword;
    ...
        
    private void save() {
        SharedPreferences.Editor editor = getSharedPreferences("account_password",MODE_PRIVATE).edit();
        editor.putString("account", account.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.putBoolean("remember_password", rememberPassword.isChecked());
        editor.apply();
    }
    
    private void read() {
        SharedPreferences pref = getSharedPreferences("account_password", MODE_PRIVATE);
        account.setText(pref.getString("account", ""));
        if (pref.getBoolean("remember_password",false)) {
           password.setText(pref.getString("password",""));
        }
    }

```

# 目前学习情况

## java部分

在看哈希表等一些数据结构，和一些集合框架

## Android部分

在看适配器Adapter部分，跟着还学着一些自带组件，还看了点自制的组件，比如说自制的圆形ImageView图像
