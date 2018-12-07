package com.love.jax.callback;

/**
 * com.love.jax.callback
 * Created by jax on 2018/12/7 14:51
 * TODO: 移动或者删除时候回调接口
 */
public interface MyItemMoveListener {

    /**
     * 拖拽的时候回调，可以实现拖拽刷新条目
     * @param fromPosition 从什么位置拖拽
     * @param toPosition 拖到什么位置
     * @return 是否执行了move
     */
    boolean onItemMove(int fromPosition,int toPosition);

    /**
     * 当条目被移除时回调
     * @param position 移除条目的位置
     * @return
     */
    boolean onItemRemove(int position);

}
