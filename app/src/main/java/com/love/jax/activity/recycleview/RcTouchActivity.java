package com.love.jax.activity.recycleview;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.adapter.QQAdapter;
import com.love.jax.bean.DataUtils;
import com.love.jax.bean.QQMessage;
import com.love.jax.callback.MyItemTouchCallback;
import com.love.jax.callback.StartDragListener;

import java.util.List;

import butterknife.BindView;

/**
 * 处理item滑动事件
 */
public class RcTouchActivity extends BaseActivity implements StartDragListener{


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    ItemTouchHelper helper;
    QQAdapter adapter;

    @Override
    protected void initJestListener() {
        MyItemTouchCallback callback = new MyItemTouchCallback(adapter);
         helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerview);

    }

    @Override
    protected void initView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        List<QQMessage> list = DataUtils.initQQ();
        adapter = new QQAdapter(list,this);
        recyclerview.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_rc_touch;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder holder) {
        helper.startDrag(holder);
    }
}
