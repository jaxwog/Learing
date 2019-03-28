package com.love.jax.activity.tkjobs;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.adapter.CarClaimsInfoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarClaimsInfoActivity extends BaseActivity {

    CarClaimsInfoAdapter mInfoAdapter;
    List<String> mList;
    @BindView(R.id.ac_rec_product)
    RecyclerView acRecProduct;

    @Override
    protected void initJestListener() {

        mInfoAdapter.setDatas(mList);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        mList = new ArrayList<>();
        mInfoAdapter = new CarClaimsInfoAdapter(mContext);
        for (int i = 1; i < 16; i++) {
            mList.add("测试步骤啊" + i);
        }
        acRecProduct.setLayoutManager(new LinearLayoutManager(
                mContext, LinearLayoutManager.VERTICAL, false));
        acRecProduct.setAdapter(mInfoAdapter);





    }

    @Override
    protected int getContentView() {
        return R.layout.activity_car_claims_info;
    }


}
