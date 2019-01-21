package com.love.jax.activity.materialdesign;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.adapter.FabRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 隐藏交互动画behavior
 * CoordinatorLayout: 继承自ViewGroup。
 * 通过协调并调度里面的子控件或者布局来实现触摸（一般是指滑动）产生一些相关的动画效果。
 * 可以通过设置view的Behavior来实现触摸的动画调度。
 * 谷歌有个bug在最新版的Design包里面解决了：snackbar弹出的时候会遮挡住fab.
 * 解决：用CoordinatorLayout将其包裹在里面就可以解决了。
 */
public class FabRecAnimatorActivity2 extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    List<String> mStringList = new ArrayList<>();

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        setTitle("健康档案");
        for (int i = 0; i < 20; i++) {
            mStringList.add("德玛西亚--哇咔咔"+i);
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new FabRecyclerAdapter(mStringList );
        recyclerview.setAdapter(adapter);
    }

    public void rotate(View v){
        Snackbar.make(v, "你该休息了！", Snackbar.LENGTH_LONG)
                .setAction("确定", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "好多啦", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_fab_rec_animator2;
    }
}
