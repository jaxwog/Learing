package com.love.jax.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.love.jax.R;
import com.love.jax.bean.OrderEntity;
import com.love.jax.utils.ConfigSet;
import com.love.jax.utils.Logger;
import com.love.jax.view.CounterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity {
    OrderEntity entity;
    private static final String TAG = "OrderActivity";

    @BindView(R.id.view_count)
    CounterView viewCount;
    @BindView(R.id.view_money_pay)
    TextView viewMoneyPay;
    @BindView(R.id.btn_tips)
    FrameLayout btnTips;


    @Override
    protected void initBundle() {
        super.initBundle();
        if (mBundle != null) {
//            Logger.i("wog","获取到的json数据="+mBundle.getString(ConfigSet.INTENT_STRING));
            Gson gson = new Gson();
            entity = gson.fromJson(mBundle.getString(ConfigSet.INTENT_STRING), OrderEntity.class);

            Logger.i(TAG, "获取到的json数据=" + entity.toString());
        }
    }

    @Override
    protected void initJestListener() {


    }

    @Override
    protected void initView() {
        viewCount.setOnCountChangeListener(new CounterView.OnCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                viewMoneyPay.setText("¥ " + count * 100);
            }
        });

        viewCount.getOnCountChangeListener().onCountChange(1);
        viewCount.setMaxCount(10);
        viewCount.setMinCount(1);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_order;
    }



    @OnClick(R.id.btn_tips)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tips:
                jumpToActivity(InvoiceInfoActivity.class, mBundle);

        }

    }
}
