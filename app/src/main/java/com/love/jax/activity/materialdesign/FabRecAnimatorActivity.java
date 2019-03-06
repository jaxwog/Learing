package com.love.jax.activity.materialdesign;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.adapter.FabRecyclerAdapter;
import com.love.jax.callback.FabScrollListener;
import com.love.jax.callback.HideScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 实现滑动RecyclerView,Fab显示和隐藏。
 * 思路：
 * 1.监听RecyclerView的滑动
 * 2.fab执行显示和隐藏的动画
 */
public class FabRecAnimatorActivity extends BaseActivity implements HideScrollListener {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    ImageButton fab;
    List<String> mStringList = new ArrayList<>();


    @Override
    protected void initJestListener() {

        recyclerview.addOnScrollListener(new FabScrollListener(this));

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

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_fab_rec_animator;
    }


    @Override
    public void onHide() {
       // 隐藏动画--属性动画
        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();
        fab.animate().translationY(fab.getHeight()+layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
    }

    @Override
    public void onShow() {
        // 显示动画--属性动画
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();
        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
    }
}
