package com.love.jax.activity.recycleview;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.activity.MainActivity;
import com.love.jax.adapter.CityAdapter;
import com.love.jax.adapter.DividerItemDecoration;
import com.love.jax.adapter.HeaderRecyclerAndFooterWrapperAdapter;
import com.love.jax.adapter.ViewHolder;
import com.love.jax.bean.CityBean;
import com.love.jax.callback.SuspensionDecoration;
import com.love.jax.view.IndexBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 城市列表，仿照通讯录
 */
public class CityListActivity extends BaseActivity {


    @BindView(R.id.rec_view)
    RecyclerView recView;

    @BindView(R.id.indexBar)
    IndexBar mIndexBar;

    private CityAdapter mAdapter;
    private List<CityBean> mDatas;
    private LinearLayoutManager mManager;
    private SuspensionDecoration mDecoration;

    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;


    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


        recView.setLayoutManager(mManager = new LinearLayoutManager(this));

        mAdapter = new CityAdapter(this, mDatas);
        recView.setAdapter(mAdapter);

//        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
//            @Override
//            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
//                holder.setText(R.id.tv_fab_info, (String) o);
//            }
//        };
//        mHeaderAdapter.setHeaderView(R.layout.item_fab_rec_anima, "我是测试头部1111");
//        mHeaderAdapter.addHeaderView(R.layout.item_fab_rec_anima, "我是测试头部1223");
//
//
//        recView.setAdapter(mHeaderAdapter);


        //如果add两个，那么按照先后顺序，依次渲染。
//        recView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        recView.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas));

        mIndexBar
//                .setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(false)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager
        initDatas(getResources().getStringArray(R.array.provinces));

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_city_list;
    }


    private void initDatas(final String[] data) {
        //延迟200ms 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas = new ArrayList<>();
                for (int i = 0; i < data.length; i++) {
                    CityBean cityBean = new CityBean();
                    cityBean.setCity(data[i]);//设置城市名称
                    mDatas.add(cityBean);
                }
                mIndexBar.setmSourceDatas(mDatas)//设置数据
//                        .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                        .invalidate();

//                mHeaderAdapter.notifyDataSetChanged();
                mDecoration.setmDatas(mDatas);
                mAdapter.setDatas(mDatas);
                mAdapter.notifyDataSetChanged();

            }
        }, 200);

    }


}
