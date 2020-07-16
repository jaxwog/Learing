package com.love.jax.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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
public class ClaimProgress2NofileAdapter extends RecyclerView.Adapter<ClaimProgress2NofileAdapter.ShowHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<List<String>> mList;

    public ClaimProgress2NofileAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        mList = new ArrayList<>();
    }

    public void setDatas(List<List<String>> list){
        mList.clear();
        mList = list;

        notifyDataSetChanged();
    }

    private boolean isOpen;//是否打开列表

    public void setOpen(boolean open) {
        isOpen = open;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.insur_item_claim_progress, null);
        return new ShowHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ShowHolder viewHolder, int position) {

        viewHolder.mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( viewHolder.mRecyclerView.getVisibility()==View.GONE){
                    viewHolder.mRecyclerView.setVisibility(View.VISIBLE);
                }else {
                    viewHolder.mRecyclerView.setVisibility(View.GONE);
                }


            }
        });



        ClaimProgress2Adapter adapter = new ClaimProgress2Adapter(mContext);
        viewHolder.mRecyclerView.setLayoutManager(new LinearLayoutManager(
                mContext, LinearLayoutManager.VERTICAL, false));
        viewHolder.mRecyclerView.setAdapter(adapter);
        adapter.setDatas(mList.get(position));


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
        @BindView(R.id.tv_title)
        TextView mTvTitle;

        @BindView(R.id.rcv_progress_item)
        RecyclerView mRecyclerView;

        public ShowHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
