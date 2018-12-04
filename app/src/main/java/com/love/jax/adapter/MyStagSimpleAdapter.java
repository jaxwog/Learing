package com.love.jax.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * com.love.jax.adapter
 * Created by jax on 2018/12/3 18:44
 * TODO: 瀑布流效果
 */
public class MyStagSimpleAdapter extends RecyclerView.Adapter<MyStagSimpleAdapter.MyViewHolder> {

private List<String> list;
private List<Integer> heights;

public MyStagSimpleAdapter(List<String> list) {
        // TODO Auto-generated constructor stub
        this.list = list;

        heights = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
        heights.add((int) Math.max(200,Math.random()*550));
        }

        }

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView tv;

    public MyViewHolder(View view) {
        super(view);
        tv = (TextView)view.findViewById(android.R.id.text1);
//			tv = (TextView)view.findViewById(R.id.tv);

    }

}

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //绑定数据
        ViewGroup.LayoutParams params = holder.tv.getLayoutParams();
        params.height = heights.get(position);
        holder.tv.setBackgroundColor(Color.rgb(100, (int)(Math.random()*255), (int)(Math.random()*255)));
        holder.tv.setLayoutParams(params);
        holder.tv.setText(list.get(position));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
        // 创建ViewHolder
//		MyViewHolder holder = new MyViewHolder(View.inflate(viewGroup.getContext(), R.layout.listitem, null));
//		MyViewHolder holder = new MyViewHolder(View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null));
//		MyViewHolder holder = new MyViewHolder(View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, viewGroup));
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate( android.R.layout.simple_list_item_1, viewGroup,false));
        return holder;
    }

/**
 *
  报错：NullPointException----LayoutParams为空
 View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null)
 最终都会调用该方法：
 inflate(resource, root, root != null);
 inflate(resource, null, false);
 经过修改：
 MyViewHolder holder = new MyViewHolder(View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, viewGroup));
 还是报错：
 java.lang.IllegalStateException:
 The specified child already has a parent. You must call removeView() on the child's parent first.
 默认还是调用的：
 inflate(resource, root, root != null);
 inflate(resource, root, true);
 看源码就知道：多做了一个事情就是
 if (root != null && attachToRoot) {
 root.addView(temp, params);
 }
 由于RecyclerView/ListView会自动将child添加到它里面去

 为何Fragment的onCreateView又可以呢？

 最终结局办法：
 首先，root不为null, boolean(attachToRoot)必须为false。
 inflate(resource, root, false);
 */
}
