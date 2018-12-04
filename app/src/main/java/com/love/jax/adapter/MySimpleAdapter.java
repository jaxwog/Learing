package com.love.jax.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * com.love.jax.adapter
 * Created by jax on 2018/12/3 18:41
 * TODO: 简单示例
 */
public class MySimpleAdapter  extends RecyclerView.Adapter<MySimpleAdapter.MyViewHolder> {

    private List<String> list;
    private OnItemClickListener mOnItemClickListener;

    public MySimpleAdapter(List<String> list) {
        // TODO Auto-generated constructor stub
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView)view.findViewById(android.R.id.text1);

        }

    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //绑定数据
        holder.tv.setText(list.get(position));
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
        // 创建ViewHolder
        MyViewHolder holder = new MyViewHolder(View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null));
        return holder;
    }

    public void addData(int position){
        list.add(position,"additem"+position);
        //提示刷新--会影响效率
//		notifyDataSetChanged();
        notifyItemInserted(position);
    }
    public void removeData(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }


}