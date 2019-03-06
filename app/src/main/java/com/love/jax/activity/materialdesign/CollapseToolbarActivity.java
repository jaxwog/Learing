package com.love.jax.activity.materialdesign;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

import butterknife.BindView;

/**
 * 实现Toolbar折叠效果.
 *
 * 注意:1.AppBarLayout设置固定高度，并且要实现折叠效果必须比toolbar的高度要高。
 2.CollapsingToolbarLayout最好设置成match_parent
 app:layout_collapseMode="parallax"
 parallax:视差模式，在折叠的时候会有折叠视差效果。
 一般搭配layout_collapseParallaxMultiplier=“0.5”视差的明显程度
 be between 0.0 and 1.0.
 none:没有任何效果，往上滑动的时候toolbar会首先被固定并推出去。
 pin:固定模式，在折叠的时候最后固定在顶端。

 app1:contentScrim="@color/colorPrimary_pink"//内容部分的沉浸式效果：toolbar和imageview有一个渐变过渡的效果
 app1:statusBarScrim="@color/colorPrimary_pink"//和状态栏的沉浸式效果：指定颜色。
 */
public class CollapseToolbarActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void initJestListener() {
//设置NavigationIcon的点击事件监听，比如返回按钮。
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_collapse_toolbar;
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
    public void rotate(View view){

    }
}
