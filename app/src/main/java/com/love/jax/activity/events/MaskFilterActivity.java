package com.love.jax.activity.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.love.jax.R;
import com.love.jax.view.MaskFilterView;

/**
 *  滤镜效果
 *  可用于按钮颜色变换，图片颜色
 */
public class MaskFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaskFilterView view  = new MaskFilterView(this);
        setContentView(view);
//        setContentView(R.layout.activity_mask_filter);
    }
}
