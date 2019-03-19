package com.love.jax.activity.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.love.jax.R;
import com.love.jax.view.PathMeasureView;

public class PathMeasureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PathMeasureView view = new PathMeasureView(this);
        setContentView(view);
//        setContentView(R.layout.activity_path_measure);
    }
}
