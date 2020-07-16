package com.love.jax.activity.events;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.love.jax.R;
import com.love.jax.view.MyGradientView;
import com.love.jax.view.MyGradientView2;
import com.love.jax.view.RadarGradientView;
import com.love.jax.view.ZoomImageView;

public class SeniorRendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MyGradientView view  = new MyGradientView(this);
//        MyGradientView2 view  = new MyGradientView2(this);
        RadarGradientView view = new RadarGradientView(this);
//        ZoomImageView view = new ZoomImageView(this);

        setContentView(view);
//        setContentView(R.layout.activity_senior_rend);

    }


}
