package com.love.jax.activity.materialdesign;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.love.jax.R;
import com.love.jax.utils.ScreenUtil;

/**
 * 沉浸式设计(google全屏，讨论状态栏颜色一致)
 * <p>
 * <p>
 * 一、5.0+ API
 * 5.0+自动实现了沉浸式效果，状态栏的颜色跟随你的主题里面的colorPrimaryDark属性。
 * 1）通过设置主题达到
 * 2）通过设置样式属性解决
 * <item name="android:statusBarColor">@color/system_bottom_nav_color</item>
 * 3）通过代码设置
 * 5.0+可以直接用API来修改状态栏的颜色。
 * getWindow().setStatusBarColor(getResources().getColor(R.color.zteBlue));
 * (注意：要在setContentView方法之前设置)
 */

/**
 * 一、5.0+ API
 * 5.0+自动实现了沉浸式效果，状态栏的颜色跟随你的主题里面的colorPrimaryDark属性。
 * 1）通过设置主题达到
 * 2）通过设置样式属性解决
 * <item name="android:statusBarColor">@color/system_bottom_nav_color</item>
 * 3）通过代码设置
 * 5.0+可以直接用API来修改状态栏的颜色。
 * getWindow().setStatusBarColor(getResources().getColor(R.color.zteBlue));
 * (注意：要在setContentView方法之前设置)
 */

/**
 * 二、4.4版本（4.4以下版本无法使用）
 * 4.4(KitKat)新出的API，可以设置状态栏为透明的。
 * 1.在属性样式里面解决（不推荐使用，因为兼容不好）
 * <item name="android:windowTranslucentStatus">true</item>
 * 2.再代码里面解决
 * getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
 * setContentView(R.layout.activity_main);
 * 出现副作用：
 * APP的内容顶到最上面去了，即状态栏会遮挡一部分界面。很坑，解决办法（有几种）：

 1）给Toolbar设置android:fitsSystemWindows="true"
 该属性的作用：设置布局时，是否考虑当前系统窗口的布局，如果为true就会调整整个系统窗口
 布局(包括状态栏的view)以适应你的布局。
 但是：又出现了一个bug,当里面有ScrollView并且ScrollView里面有Edittext的时候，就会出现软键盘一弹起就会把toolbar拉下来，很难看
 这种办法有什么价值呢？如果里面没有ScrollView就可以用。

 2）推荐
 灵感：发现给布局最外层容器设置android:fitsSystemWindows="true" 可以达到状态栏透明，并且露出底色---android:windowBackground颜色。
 不会出现toolbar被状态栏遮挡的情况。
 巧妙地解决：步骤：
 1.在最外层容器设置android:fitsSystemWindows="true"
 不要给Toolbar设置android:fitsSystemWindows="true"
 2.直接将最外层容器(也可以修改-android:windowBackground颜色)设置成状态栏想要的颜色
 3.下面剩下的布局再包裹一层正常的背景颜色。

 3）修改Toolbar的高度
 1.不要给Toolbar设置android:fitsSystemWindows="true"
 2.
 需要知道状态栏的高度是多少？去源码里面找找
 <!-- Height of the status bar -->
 <dimen name="status_bar_height">24dp</dimen>
 <!-- Height of the bottom navigation / system bar. -->
 <dimen name="navigation_bar_height">48dp</dimen>
 反射手机运行的类：android.R.dimen.status_bar_height.

 3.修改Toolbar的PaddingTop（因为纯粹增加toolbar的高度会遮挡toobar里面的一些内容）
 toolbar.setPadding(
 toolbar.getPaddingLeft(),
 toolbar.getPaddingTop()+getStatusBarHeight(this),
 toolbar.getPaddingRight(),
 toolbar.getPaddingBottom());
 */
public class TranslucentTopActivity extends AppCompatActivity {


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//设置状态栏的透明属性
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_translucent_top);
//        getWindow().setStatusBarColor(getResources().getColor(R.color.zteBlue));
        //1.先设置toolbar的高度
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        ViewGroup.LayoutParams params = toolbar.getLayoutParams();
//        int statusBarHeight = ScreenUtil.getStatusBarHeight(this);
//        params.height += statusBarHeight;
//        toolbar.setLayoutParams(params);
        //2.设置paddingTop，以达到状态栏不遮挡toolbar的内容。
        toolbar.setPadding(toolbar.getPaddingLeft(), toolbar.getPaddingTop() + ScreenUtil
                .getStatusBarHeight(this), toolbar.getPaddingRight(), toolbar.getPaddingBottom());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
