package com.love.jax.activity.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.view.WaveView;

/**
 * path绘制波形动态效果
 */
public class WaveViewActivity extends BaseActivity {



    @Override
    protected void initJestListener() {
        WaveView waveView = super.findViewById(R.id.waveView);
      Button button = findViewById(R.id.startWave);
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              waveView.startAnimation();
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
        return R.layout.activity_wave_view;
    }
}
