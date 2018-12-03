package com.love.jax.activity;


import com.google.gson.Gson;
import com.love.jax.R;
import com.love.jax.bean.OrderEntity;
import com.love.jax.utils.ConfigSet;
import com.love.jax.utils.Logger;

public class OrderActivity extends BaseActivity {
    OrderEntity entity;


    @Override
    protected void initBundle() {
        super.initBundle();
        if (mBundle!=null){
//            Logger.i("wog","获取到的json数据="+mBundle.getString(ConfigSet.INTENT_STRING));
            Gson gson  = new Gson();
          entity =  gson.fromJson(mBundle.getString(ConfigSet.INTENT_STRING), OrderEntity.class);

            Logger.i("wog","获取到的json数据="+entity.toString());
        }
    }

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_order;
    }
}
