package com.love.jax.activity;


import com.love.jax.R;
import com.love.jax.utils.ConfigSet;
import com.love.jax.utils.Logger;

/**
 * 根据设计图的px进行屏幕适配
 * 保证进行等比缩放（比dp更加精确）
 */
public class ScreenAdaptationActivity extends BaseActivity {


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
    protected void initBundle() {
        super.initBundle();
        if (mBundle!=null){
            Logger.i("wog",mBundle.getString(ConfigSet.INTENT_STRING));
        }


    }

    @Override
    protected int getContentView() {
        return R.layout.activity_screen_adaptation;
    }
}
