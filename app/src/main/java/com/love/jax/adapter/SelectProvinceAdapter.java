package com.love.jax.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.bean.table.city;
import com.love.jax.bean.table.province;
import com.love.jax.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.love.jax.adapter
 * Created by jax on 2019/4/9 10:48
 * TODO:适用于省份选择列表
 */
public class SelectProvinceAdapter extends RecyclerView.Adapter<SelectProvinceAdapter.SelectCityHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<province> mList;
    private OnItemClickListener mOnItemClickListener;
    private int mSelectPos = -1;

    public SelectProvinceAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        mList = new ArrayList<>();
    }

    public void setDatas(List<province> list){
        mList.clear();
        if (!ListUtils.isEmpty(list)) {
            mList.addAll(list);
            mSelectPos = -1;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SelectCityHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.item_city_select, null);
        SelectCityHolder holder = new SelectCityHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull SelectCityHolder viewHolder, int position) {
//        viewHolder.mTitle.setText(Html.fromHtml(StringShowUtils.highLight(mList.get(position))));

        viewHolder.mTextView.setText(mList.get(position).getProvinceName());
        if(mOnItemClickListener!=null) {
            viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectPos= position;
                    notifyDataSetChanged();
                    mOnItemClickListener.onItemClick(v, position);
                }
            });
        }
        if(position == mSelectPos){
            viewHolder.mTextView.setTextColor(mContext.getResources().getColor(R.color.color_517DF7));
        }else{
            viewHolder.mTextView.setTextColor(mContext.getResources().getColor(R.color.color_1F2845));
        }


    }

    @Override
    public int getItemCount() {
        if (ListUtils.isEmpty(mList)){
            return 0;
        }
        return mList.size();
    }

    public void setmSelectPos(int pos){
        this.mSelectPos = pos;
        notifyItemChanged(pos);
    }

    public static class SelectCityHolder extends RecyclerView.ViewHolder {

      //
        @BindView(R.id.tv_city)
        TextView mTextView;

        @BindView(R.id.content)
        LinearLayout mLayout;

        public SelectCityHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }
}
