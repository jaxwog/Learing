package com.love.jax.activity.events;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.love.jax.R;
import com.love.jax.view.MyGradientView;
import com.love.jax.view.ZoomImageView;

public class SeniorRendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MyGradientView view  = new MyGradientView(this);
        ZoomImageView view = new ZoomImageView(this);
        setContentView(view);
//        setContentView(R.layout.activity_senior_rend);

    }


}