package com.love.jax.callback;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.love.jax.R;

/**
 * com.love.jax.callback
 * Created by jax on 2018/12/6 19:38
 * TODO:
 */
public class MyItemTouchCallback extends ItemTouchHelper.Callback {


    private MyItemMoveListener mListener;

    public MyItemTouchCallback(MyItemMoveListener listener){
        mListener = listener;
    }

    /**
     * callback 回调监听时先调用的，用来判断当前是什么动作，比如方向（我要监听哪个方向的拖动）
     * @param recyclerView
     * @param viewHolder
     * @return
     *
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView
            .ViewHolder viewHolder) {
        //我要监听拖拽的方向，上或者下 位运算
        int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        int swipFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;

        int flags = makeMovementFlags(dragFlags,swipFlags);




        return flags;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        //是否允许长按拖拽效果,ItemTouchHelper类中默认是允许的
        return true;
    }

    /**
     * 当移动的时候回调该方法---拖动
     * @param recyclerView
     * @param srcHolder  从。。。
     * @param targetHolder 到。。。
     * @return
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder
            srcHolder, @NonNull RecyclerView.ViewHolder targetHolder) {
        if (srcHolder.getItemViewType()!= targetHolder.getItemViewType()){
            return false;
        }
        //当拖拽的过程中不断的调用adapter.notifyItemMoved(from,to) 方法
        boolean result = mListener.onItemMove(srcHolder.getAdapterPosition(),targetHolder.getAdapterPosition());

        return result;
    }

    /**
     * 当移动的时候回调该方法--侧滑
     * @param viewHolder
     * @param i
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        //监听侧滑，删除数据，调用方法刷新adapter
        mListener.onItemRemove(viewHolder.getAdapterPosition());


    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        //判断选中状态
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE ){
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.color_698DD9));
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder
            viewHolder) {
        //恢复选中状态颜色
        viewHolder.itemView.setBackgroundColor(Color.WHITE);

        viewHolder.itemView.setAlpha(1);
        viewHolder.itemView.setScaleX(1);
        viewHolder.itemView.setScaleY(1);

        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull
            RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean
            isCurrentlyActive) {
        //dX:水平方向移动的增量（负：往左；正：往右）范围：0~View.getWidth  0~1
        /**
         * 滑动删除的时候，然后再滑动页面，结果有的条目不出现了 有空白的地方。
         原因：ListView和RecyclerView都会有复用条目itemView。这样就会导致上面的问题。
         itemView 条目被复用，但是状态还在需要恢复这些条目
         解决：很多。比如在clearView回调方法里面去恢复这些条目的状态
         @see MyItemTouchCallback#clearView(RecyclerView, RecyclerView.ViewHolder)
         */
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            float alph = 1 - Math.abs(dX)/viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alph);
            viewHolder.itemView.setScaleX(alph);
            viewHolder.itemView.setScaleY(alph);

        }

        //判断是否超出或者达到width/2，就让其setTranslationX位一般的位置
//        if(Math.abs(dX)<=viewHolder.itemView.getWidth()/2){
//            viewHolder.itemView.setTranslationX(-0.5f*viewHolder.itemView.getWidth());
//        }else{
//            viewHolder.itemView.setTranslationX(dX);
//        }

//        方法二：ItemView就是一个ViewPager，上面的view可以朝反方向设置TranslationX




         //处理了setTranslationX 方法
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
