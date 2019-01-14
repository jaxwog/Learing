package com.love.jax.activity.materialdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.adapter.FabRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 隐藏交互动画behavior
 * ？？？？？？？？？？？？？？？？？？？？？？？？没有实现？？？？？？？？？？？？
 */
public class FabRecAnimatorActivity2 extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    ImageButton fab;
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

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_fab_rec_animator2;
    }
}
