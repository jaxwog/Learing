package com.love.jax.activity.events;

import android.os.Bundle;
import android.view.View;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.view.CustomProgressBar;

import butterknife.BindView;

public class PaintProgressActivity extends BaseActivity {
    private int progress = 0;

    @BindView(R.id.cs_pro_bar)
    CustomProgressBar csProBar;

    @Override
    protected void initJestListener() {
        csProBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {


                    @Override
                    public void run() {
                        while (progress <= 100) {
                            progress += 2;
                            csProBar.setProgress(progress);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
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
        return R.layout.activity_paint_progress;
    }




}
