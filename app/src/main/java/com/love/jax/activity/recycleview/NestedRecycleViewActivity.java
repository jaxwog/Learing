package com.love.jax.activity.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;

import com.love.jax.R;
import com.love.jax.adapter.ClaimProgress2Adapter;
import com.love.jax.adapter.ClaimProgress2NofileAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NestedRecycleViewActivity extends AppCompatActivity {

    @BindView(R.id.rec_claim_progress)
    RecyclerView recClaimProgress;

    @BindView(R.id.titleLay)
    ScrollView mScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_recycle_view);
        ButterKnife.bind(this);

        List<List<String>> lists = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
               list.add("i="+i+",j"+j);
            }
            lists.add(list);
        }


        ClaimProgress2NofileAdapter adapter = new ClaimProgress2NofileAdapter(this);
        recClaimProgress.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));
        recClaimProgress.setAdapter(adapter);
        adapter.setDatas(lists);





       mScrollView.setSmoothScrollingEnabled(true);

    }
}
