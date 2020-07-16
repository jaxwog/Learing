package com.love.jax.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 理赔进度  补充资料adapter
 */
public class ClaimProgress2Adapter extends RecyclerView.Adapter<ClaimProgress2Adapter.ShowHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mList;

    public ClaimProgress2Adapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        mList = new ArrayList<>();
    }

    public void setDatas(List<String> list){
        mList.clear();
        mList = list;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.item_city_name, null);
        return new ShowHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ShowHolder viewHolder, int position) {

        if (!TextUtils.isEmpty(mList.get(position)) && position !=1) {
            viewHolder.mTvTitle.setVisibility(View.VISIBLE);
            viewHolder.mTvTitle.setText((position + 1) + "、" + mList.get(position));
        }else {
            viewHolder.mTvTitle.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if (ListUtils.isEmpty(mList)){
            return 0;
        }
        return mList.size();
    }

    public static class ShowHolder extends RecyclerView.ViewHolder {

        //补充资料步骤标题
        @BindView(R.id.tv_city)
        TextView mTvTitle;


        public ShowHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
