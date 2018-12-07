package com.love.jax.callback;

import android.support.v7.widget.RecyclerView;

/**
 * com.love.jax.callback
 * Created by jax on 2018/12/7 14:23
 * TODO: 处理item触摸头像进行滑动效果
 *@see android.support.v7.widget.helper.ItemTouchHelper#startDrag
 */
public interface StartDragListener {

     void onStartDrag(RecyclerView.ViewHolder holder);
}
