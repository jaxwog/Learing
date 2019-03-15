package com.love.jax.activity.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.love.jax.R;
import com.love.jax.view.search.ControllerSearch;
import com.love.jax.view.search.SearchIconView;

/**
 * 搜索图标直接转换为一条横线
 */
public class SearchIconActivity extends AppCompatActivity {

    private SearchIconView mIconView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_icon);
        mIconView = super.findViewById(R.id.sv);
        mIconView.setController(new ControllerSearch());
    }

    public void start(View v){
        mIconView.startAnimation();
    }
    public void reset(View v){
        mIconView.resetAnimation();
    }
}
