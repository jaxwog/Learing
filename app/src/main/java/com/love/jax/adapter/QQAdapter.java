package com.love.jax.adapter;

import java.util.Collections;
import java.util.List;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.bean.QQMessage;
import com.love.jax.callback.MyItemMoveListener;
import com.love.jax.callback.StartDragListener;

public class QQAdapter extends Adapter<QQAdapter.MyViewHolder> implements MyItemMoveListener{
    private List<QQMessage> list;
    private StartDragListener mDragListener;


    public QQAdapter(List<QQMessage> list, StartDragListener listener) {
        this.list = list;
        mDragListener = listener;

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //数据交换、刷新
        Collections.swap(list,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        return true;
    }

    class MyViewHolder extends ViewHolder {

        private ImageView iv_logo;
        private TextView tv_name;
        private TextView tv_Msg;
        private TextView tv_time;
        private ImageView iv_pop;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_logo = (ImageView) itemView.findViewById(R.id.iv_logo);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_Msg = (TextView) itemView.findViewById(R.id.tv_lastMsg);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            iv_pop = (ImageView) itemView.findViewById(R.id.iv_pop);
        }

    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int location) {
        QQMessage qqMessage = list.get(location);
        holder.iv_logo.setImageResource(qqMessage.getLogo());
        holder.tv_name.setText(qqMessage.getName());
        holder.tv_Msg.setText(qqMessage.getLastMsg());
        holder.tv_time.setText(qqMessage.getTime());
        holder.iv_pop.setImageResource(qqMessage.getPop());


        holder.iv_logo.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
					mDragListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qq_touch, parent, false);
        return new MyViewHolder(view);
    }


}
