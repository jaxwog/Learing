package com.love.jax.activity.design.mvvm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * com.love.jax.activity.design.mvvm
 * Created by jax on 2020-01-15 20:16
 * TODO: 泛型T基本上通用的方法，特殊需要定制的再从新编写
 */
public class ComonAdapter<T> extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private int layoutId;//item视图id
    private int variableId;//BR.food
    //List <Food> User
    private List<T> list;

    public ComonAdapter(Context context, LayoutInflater inflater, int layoutId, int variableId,
                        List<T> list) {
        this.context = context;
        this.inflater = inflater;
        this.layoutId = layoutId;
        this.variableId = variableId;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding dataBinding;
        //复用视图为空，通过DataBindingUtil.inflate创建ViewDataBinding
        if (convertView == null) {
            dataBinding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        } else {
            //重用DataBinding，
            dataBinding = DataBindingUtil.getBinding(convertView);
        }
        //设置ViewDataBinding，variableId用来判断是否需要更新视图
        dataBinding.setVariable(variableId, list.get(position));
        //返回item视图
        return dataBinding.getRoot().getRootView();
    }
}
