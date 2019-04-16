package com.love.jax.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.ArrayList;

/**
 * com.love.jax.adapter
 * Created by jax on 2018/12/6 16:04
 * @see android.widget.HeaderViewListAdapter
 * TODO: adapter 包装类
 */
public class HeaderViewRecycleAdapter extends RecyclerView.Adapter {
    private RecyclerView.Adapter mAdapter;
    ArrayList<View> mHeaderViewInfos;
    ArrayList<View> mFooterViewInfos;


    public HeaderViewRecycleAdapter(ArrayList<View> headerViewInfos,
                                 ArrayList<View> footerViewInfos,
                                 RecyclerView.Adapter adapter) {
        mAdapter = adapter;


        if (headerViewInfos == null) {
            mHeaderViewInfos = new ArrayList<>();
        } else {
            mHeaderViewInfos = headerViewInfos;
        }

        if (footerViewInfos == null) {
            mFooterViewInfos = new ArrayList<>();
        } else {
            mFooterViewInfos = footerViewInfos;
        }

    }

    public int getHeadersCount() {
        return mHeaderViewInfos.size();
    }

    public int getFootersCount() {
        return mFooterViewInfos.size();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //header
        if(viewType==RecyclerView.INVALID_TYPE){
            return new HeaderViewHolder(mHeaderViewInfos.get(0));
        }else if(viewType==RecyclerView.INVALID_TYPE-1){//footer
            return new HeaderViewHolder(mFooterViewInfos.get(0));
        }
        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
        return mAdapter.onCreateViewHolder(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
//也要划分三个区域
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {//是头部
            return ;
        }
        //adapter body
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {

                mAdapter.onBindViewHolder(viewHolder, adjPosition);
                return ;
            }
        }
        //footer
    }

    @Override
    public int getItemViewType(int position) {
        //判断当前条目是什么类型的---决定渲染什么视图给什么数据
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {//是头部
            return RecyclerView.INVALID_TYPE;
        }
        //正常条目部分
        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }
        //footer部分
        return RecyclerView.INVALID_TYPE-1;
    }

    @Override
    public int getItemCount() {
        if (mAdapter!=null){
            return getFootersCount()+getHeadersCount()+mAdapter.getItemCount();
        }else {
            return getFootersCount()+getHeadersCount();
        }

    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View view) {
            super(view);
        }
    }
}
