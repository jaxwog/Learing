package com.love.jax.activity.materialdesign;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.love.jax.R;
import com.love.jax.utils.ScreenUtil;

/**
 * 沉浸式设计
 */

/**
 * 5.x 底部虚拟导航沉浸效果
 1）属性解决
 navigationBarColor
 2）代码
 getWindow().setNavigationBarColor()

 * 4.4
 用到一些特殊手段！----4.4(KitKat)新出的API，可以设置虚拟导航栏为透明的。
 步骤：
 1）在布局底部添加一个高度为0.1dp的view
 2）动态设置底部View的高度为虚拟导航栏的高度
 */
public class TranslucentActivity extends BaseTranslucentActivity {

    private Toolbar toolbar;
    private View nav;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setNavigationBarColor(getResources().getColor(R.color.zteBlue));
        setContentView(R.layout.activity_translucent);


        //解决低版本4.4+的虚拟导航栏的
//        View nav = findViewById(R.id.nav);
//        ViewGroup.LayoutParams p = nav.getLayoutParams();
//        p.height += ScreenUtil.getNavigationBarHeight(this);
//        nav.setLayoutParams(p);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);
        setOrChangeTranslucentColor(toolbar, nav, getResources().getColor(R.color.zteBlue));

    }


}
