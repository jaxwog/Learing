package com.love.jax.activity.materialdesign;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.love.jax.R;

/**
 * 沉浸式设计(google全屏，讨论状态栏颜色一致)
 *
 */
public class TranslucentTopActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent);
    }
}
