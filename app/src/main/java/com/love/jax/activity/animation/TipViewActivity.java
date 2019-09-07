package com.love.jax.activity.animation;


import android.os.Bundle;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.view.TipView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipViewActivity extends BaseActivity {
    private ArrayList<String> dataList;

    @BindView(R.id.tip_view)
    TipView tipView;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }

        for (int i = 0; i < 4; i++) {
            dataList.add("我是测试数据"+i);
        }

        tipView.setTipList(dataList);
        tipView.setOnLooperPositionListener(new TipView.OnLooperPositionListener() {
            @Override
            public void onLooperPosition(int position) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_tip_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
