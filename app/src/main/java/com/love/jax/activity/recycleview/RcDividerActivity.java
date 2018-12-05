package com.love.jax.activity.recycleview;



import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.adapter.MyDivItemDecoration;
import com.love.jax.adapter.MyDivItemGridDecoration;
import com.love.jax.adapter.MySimpleAdapter;
import com.love.jax.adapter.MyStagSimpleAdapter;

import java.util.ArrayList;

/**
 * recycleView间隔线绘制
 */
public class RcDividerActivity extends BaseActivity {
    private RecyclerView recylerview;
    private ArrayList<String> list;
    private MySimpleAdapter adapter;
    private MyStagSimpleAdapter sAdapter;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {
        list = new ArrayList<String>();
        for (int i = 0; i < 60; i++) {
            list.add("item"+i);
        }

        recylerview = findViewById(R.id.recylerview);
        adapter = new MySimpleAdapter(list);
        sAdapter = new MyStagSimpleAdapter(list);
        //LayoutManager布局摆放管理器(线性摆放、瀑布流)
//        recylerview.setLayoutManager(new LinearLayoutManager(this));//默认垂直
        //reverseLayout:数据倒置，从右边开始滑动
//		recylerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
		recylerview.setLayoutManager(new GridLayoutManager(this, 3));
        //瀑布流效果
//		recylerview.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
//		recylerview.setAdapter(sAdapter);
        recylerview.setAdapter(adapter);
//        v7包中系统自带间隔线
//        DividerItemDecoration decoration = new DividerItemDecoration(mContext,LinearLayoutManager.VERTICAL);
//        recylerview.addItemDecoration(decoration);
//        MyDivItemDecoration decoration1 = new MyDivItemDecoration(mContext,LinearLayoutManager.VERTICAL);
//        recylerview.addItemDecoration(decoration1);
        MyDivItemGridDecoration decoration2  = new MyDivItemGridDecoration(mContext);
        recylerview.addItemDecoration(decoration2);

        recylerview.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(new MySimpleAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, "点我干嘛"+position, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_rc_divider;
    }

    boolean isGrid = false;
    public void change(View v){
        if(!isGrid){
            recylerview.setLayoutManager(new GridLayoutManager(this, 3));
        }else{
            recylerview.setLayoutManager(new LinearLayoutManager(this));//默认垂直
        }
        isGrid = !isGrid;
    }

    public void addItem(View v){
        adapter.addData(3);
    }
}
