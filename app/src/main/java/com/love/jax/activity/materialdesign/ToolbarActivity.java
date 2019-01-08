package com.love.jax.activity.materialdesign;


import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

import butterknife.BindView;

/**
 * TODO 导航控件---显示标题、导航back、快捷操作、菜单等。而且Toolbar不一定要放在顶部，也可以放底部
 * 1.引入support-v7包
 * 2.修改主题：    <style name="AppBaseTheme" parent="Theme.AppCompat.Light.NoActionBar">
 * 注意主题一定要使用：NoActionBar
 * 3.写布局，把Toolbar当做一个普通的容器使用。
 * 4.Activity--->AppcompatActivity
 * 5.使用Toolbar替换ActionBar           setSupportActionBar(toolbar);
 * 将弹出的菜单泡泡窗体修改成黑底白字Dark；Light白底黑字 app:popupTheme="@style/ThemeOverlay.AppCompat.Dark"
 * Toolbar属性：android:background="?attr/colorPrimary" 设置背景颜色，使用系统属性colorPrimary主色调。--在主题里面设置了。
 */
public class ToolbarActivity extends BaseActivity {


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
        return R.layout.activity_toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchview_main, menu);

        //SearchView在Menu里面
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        //设置一出来就直接呈现搜索框---SearchView
//		searchView.setIconified(false);
        //进来就呈现搜索框并且不能被隐藏
//		searchView.setIconifiedByDefault(false);
        //有时候我们需要实现自定义扩展效果
        //通过猜想，searchView用到了一个布局，去appcompat里面找到abc_search_view.xml,该里面的控件的属性
        ImageView icon = (ImageView) searchView.findViewById(R.id.search_go_btn);
        icon.setImageResource(R.mipmap.search_icon_fire);
        icon.setVisibility(View.VISIBLE);
//		searchView.setMaxWidth(200);

        SearchView.SearchAutoComplete et = (SearchView.SearchAutoComplete) searchView
                .findViewById(R.id.search_src_text);
        et.setHint("输入商品名或首字母");
        et.setHintTextColor(Color.WHITE);


        //设置提交按钮是否可用(可见)
        searchView.setSubmitButtonEnabled(true);

//		icon.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(MainActivity.this, "提交", 1).show();
//			}
//		});


//		searchView.setSuggestionsAdapter(adapter)
        //监听焦点改变
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });
        //searchView的关闭监听
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {

            @Override
            public boolean onClose() {
                // TODO Auto-generated method stub
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "提交", Toast.LENGTH_LONG).show();
            }
        });
        //监听文本变化，调用查询
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                //提交文本
                Toast.makeText(mContext, "提交文本:" + text, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                // 文本改变的时候回调
                System.out.println("文本变化~~~~~" + text);

                return false;
            }
        });
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
