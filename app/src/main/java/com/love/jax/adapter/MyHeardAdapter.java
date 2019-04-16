package com.love.jax.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.bean.table.province;
import com.love.jax.utils.ListUtils;

import java.util.List;

/**
 * com.love.jax.adapter
 * Created by jax on 2018/12/6 16:53
 * TODO: 适配器类
 */
public class MyHeardAdapter extends RecyclerView.Adapter<MyHeardAdapter.ViewHolder> {
    private List<String> list;

    public MyHeardAdapter(List<String> list) {
        // TODO Auto-generated constructor stub
        this.list = list;
    }

    public void addDatas(List<String> list){
        if (!ListUtils.isEmpty(list)) {
            list.addAll(list);
            notifyDataSetChanged();
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv;
        public ViewHolder(View view) {
            super(view);
            // TODO Auto-generated constructor stub
            tv = (TextView) view.findViewById(R.id.tv);
        }

    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_wraphead_tv, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
}

