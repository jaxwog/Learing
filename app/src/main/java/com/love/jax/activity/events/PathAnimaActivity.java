package com.love.jax.activity.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.view.PathAnimaView;
import com.love.jax.view.WaveView;

public class PathAnimaActivity extends BaseActivity {

    PathAnimaView animaView;

    @Override
    protected void initJestListener() {
         animaView = super.findViewById(R.id.waveView);
        Button button = findViewById(R.id.startWave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animaView.startAnimation();
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_path_anima;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        animaView.stopAnimation();
    }
}
