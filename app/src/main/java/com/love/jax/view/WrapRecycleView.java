package com.love.jax.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;

import com.love.jax.adapter.HeaderViewRecycleAdapter;

import java.util.ArrayList;

/**
 * com.love.jax.view
 * @see android.widget.ListView#setAdapter(ListAdapter)
 * Created by jax on 2018/12/6 15:48
 * TODO: 包装类，继承recycleView添加自己的添加头部与尾部方法
 */
public class WrapRecycleView extends RecyclerView {
    ArrayList<View> mHeaderViewInfos = new ArrayList();
    ArrayList<View> mFooterViewInfos = new ArrayList();
    private Adapter mAdapter;


    public WrapRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void addHeaderView(View v){
        mHeaderViewInfos.add(v);

        // Wrap the adapter if it wasn't already wrapped.
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecycleAdapter)) {
                mAdapter = new HeaderViewRecycleAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }

    public void addFooterView(View v){
        mFooterViewInfos.add(v);

        // Wrap the adapter if it wasn't already wrapped.
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecycleAdapter)) {
                mAdapter = new HeaderViewRecycleAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }


    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        if (mHeaderViewInfos.size() > 0|| mFooterViewInfos.size() > 0) {
            mAdapter = new HeaderViewRecycleAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        } else {
            mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
    }
}
