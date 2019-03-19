package com.love.jax.activity.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.love.jax.R;
import com.love.jax.view.search.SearchView;

public class SearchView2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchView view = new SearchView(this);
        setContentView(view);
//        setContentView(R.layout.activity_search_view2);
    }
}
