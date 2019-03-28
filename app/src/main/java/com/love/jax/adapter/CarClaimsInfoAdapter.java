package com.love.jax.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.utils.ListUtils;
import com.love.jax.view.CarClaimsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.love.jax.adapter
 * Created by jax on 2019/3/26 14:48
 * TODO:适用于违章查询详细信息
 */
public class CarClaimsInfoAdapter extends RecyclerView.Adapter<CarClaimsInfoAdapter.CarClaimsInfoHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mList;

    public CarClaimsInfoAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        mList = new ArrayList<>();
    }

    public void setDatas(List<String> list){
        mList.clear();
        if (!ListUtils.isEmpty(list)) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarClaimsInfoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.insur2_item_car_claims_steps, null);
        CarClaimsInfoHolder holder = new CarClaimsInfoHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull CarClaimsInfoHolder viewHolder, int position) {
//        viewHolder.mTitle.setText(Html.fromHtml(StringShowUtils.highLight(mList.get(position))));
        if (position==0){
            viewHolder.mClaimsView.setInt(1);
            viewHolder.mTitle.setTextColor(Color.parseColor("#517DF7"));
        }else if (position == getItemCount()-1){
            viewHolder.mClaimsView.setInt(2);
            viewHolder.mTitle.setTextColor(Color.parseColor("#4C5979"));
        }else {
            viewHolder.mClaimsView.setInt(3);
            viewHolder.mTitle.setTextColor(Color.parseColor("#4C5979"));
        }

        viewHolder.mTitle.setText(mList.get(position).toString());
        viewHolder.mContent.setText(mList.get(position).toString());
        viewHolder.mDate.setText(mList.get(position).toString());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class CarClaimsInfoHolder extends RecyclerView.ViewHolder {

        //执行步骤
        @BindView(R.id.tv_car_claims_steps_title)
        TextView mTitle;
        //步骤描述
        @BindView(R.id.tv_car_claims_steps_content)
        TextView mContent;
        //执行时间
        @BindView(R.id.tv_car_claims_date)
        TextView mDate;

        @BindView(R.id.ccv_claims)
        CarClaimsView mClaimsView;

        public CarClaimsInfoHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
