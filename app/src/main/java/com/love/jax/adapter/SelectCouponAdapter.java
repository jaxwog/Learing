package com.love.jax.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.bean.CouponEntity;
import com.love.jax.bean.LettersEntity;
import com.love.jax.utils.ListUtils;
import com.love.jax.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.love.jax.adapter
 * Created by jax on 2018/11/13 16:44
 * TODO: 优惠券展示
 */
public class SelectCouponAdapter extends RecyclerView.Adapter<SelectCouponAdapter.CouponViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<CouponEntity> mList;
    private OnItemSelectListener onItemSelectListener;

    public int getSelectItem() {
        return mSelectItem;
    }

    private int mSelectItem = -1;

    public SelectCouponAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        mList = new ArrayList<>();
    }

    public void setDatas(List<CouponEntity> list) {
        mList.clear();
        if (!ListUtils.isEmpty(list)) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_coupon_select, null);
        CouponViewHolder holder = new CouponViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CouponViewHolder viewHolder, final int position) {
//        viewHolder.mTextView.setText(mList.get(position).toString());
//        viewHolder.mTextView.setText(Html.fromHtml(mList.get(position).getTitle()));

        viewHolder.tvItemCoupon.setText(mList.get(position).getTitle());

        viewHolder.tvItemDates.setText(String.format("%s至%s",mList.get(position).getStartData(),mList.get(position).getEndData()));
//        if (TextUtils.isEmpty(mList.get(position).getManager())){
//            viewHolder.tvItemManagerNames.setVisibility(View.GONE);
//            int top = ScreenUtil.dip2px(mContext, 15);
//            viewHolder.tvItemCoupon.setPadding(0,top,0,0);
//        }else {
//            int top = ScreenUtil.dip2px(mContext, 3);
//            viewHolder.tvItemCoupon.setPadding(0,top,0,0);
//            viewHolder.tvItemManagerNames.setVisibility(View.VISIBLE);
//            viewHolder.tvItemManagerNames.setText(mList.get(position).getManager());
//        }
        viewHolder.tvItemManagerNames.setTextColor(Color.rgb(138,145,164));
        viewHolder.tvItemManagerNames.setText(mList.get(position).getContent());

        if (!TextUtils.isEmpty(mList.get(position).getPice())){
            viewHolder.tvMoneyFlag.setVisibility(View.VISIBLE);
            viewHolder.tvDiscounts.setVisibility(View.GONE);
            viewHolder.tvItemMoney.setText(mList.get(position).getPice());
        }else {
            viewHolder.tvMoneyFlag.setVisibility(View.GONE);
            viewHolder.tvDiscounts.setVisibility(View.VISIBLE);
            viewHolder.tvItemMoney.setText(mList.get(position).getDiscount());
        }



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemSelectListener != null) {
                    mSelectItem = position;
                    notifyDataSetChanged();
                    onItemSelectListener.onItemSelect(position);
                }
            }
        });

        if (position==mSelectItem){
            viewHolder.ivItemSelect.setImageResource(R.mipmap.coupon_slecect);
        }else {
            viewHolder.ivItemSelect.setImageResource(R.mipmap.coupon_unslecect);
        }


    }

    public void setmSelectItem(int pos){
        this.mSelectItem = pos;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<CouponEntity> getList() {
        return mList;
    }

    public static class CouponViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_coupon)
        TextView tvItemCoupon;
        @BindView(R.id.tv_item_dates)
        TextView tvItemDates;
        @BindView(R.id.tv_money_flag)
        TextView tvMoneyFlag;
        @BindView(R.id.tv_item_money)
        TextView tvItemMoney;
        @BindView(R.id.tv_discounts)
        TextView tvDiscounts;
        @BindView(R.id.ll_item_to_use)
        LinearLayout llItemToUse;
        @BindView(R.id.tv_item_manager_names)
        TextView tvItemManagerNames;
        @BindView(R.id.rl_item_root)
        RelativeLayout rlItemRoot;
        @BindView(R.id.layout_titdata)
        LinearLayout layout_titdata;

        @BindView(R.id.iv_select_iv)
        ImageView ivItemSelect;

        public CouponViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }



    public interface OnItemSelectListener {
        void onItemSelect(int position);
    }

    public void setOnItemSelectListener(OnItemSelectListener listener) {
        onItemSelectListener = listener;
    }

}
