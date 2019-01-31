package com.love.jax.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.love.jax.R;
import com.love.jax.bean.LettersEntity;
import com.love.jax.utils.ListUtils;
import com.love.jax.utils.NumUtils;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.love.jax.adapter
 * Created by jax on 2018/11/13 16:44
 * TODO: 首页功能显示
 */
public class InFuncAdapter extends RecyclerView.Adapter<InFuncAdapter.FunctionViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<LettersEntity> mList;
    private OnItemSelectListener onItemSelectListener;
    RequestOptions options = new RequestOptions().placeholder(R.mipmap.placeholder_icon).error(R
            .mipmap.ic_launcher);
    //从以下图片中随机加载一个图片加载到icon中
    private Integer[] mIntegers = new Integer[]{R.drawable.search_icon_casesearch, R.drawable
            .search_icon_deliver, R.drawable.search_icon_instest, R.drawable.search_icon_manager,
            R.drawable.search_icon_message, R.drawable.search_icon_pcase, R.drawable
            .search_icon_policy, R.drawable.search_icon_posignfor, R.drawable
            .search_icon_povisit, R.drawable.search_icon_ppapplication, R.drawable
            .search_icon_pprogress, R.drawable.search_icon_stephealth, R.drawable
            .search_icon_invest};


    public InFuncAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        mList = new ArrayList<>();
//        Resources res =context.getResources();
//        mIntegers= res.getIntArray(R.array.resourceId);
//        for (int i = 0; i < mIntegers.length - 1; i++) {
//            Logger.i("wog",String.valueOf(mIntegers[i]));
//        }
    }

    public void setDatas(List<LettersEntity> list) {
        mList.clear();
        if (!ListUtils.isEmpty(list)) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InFuncAdapter.FunctionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int
            viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_function_main, null);
        FunctionViewHolder holder = new FunctionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FunctionViewHolder viewHolder, final int position) {
//        viewHolder.mTextView.setText(mList.get(position).toString());
        viewHolder.mTextView.setText(Html.fromHtml(mList.get(position).getTitle()));
        Glide.with(mContext).load(mIntegers[NumUtils.getRandom(mIntegers.length)]).apply
                (options).
                into(viewHolder.mImageView);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemSelectListener != null) {
                    onItemSelectListener.onItemSelect(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<LettersEntity> getList() {
        return mList;
    }

    public static class FunctionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_function_item)
        TextView mTextView;
        @BindView(R.id.iv_function_item)
        ImageView mImageView;


        public FunctionViewHolder(View itemView) {
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
