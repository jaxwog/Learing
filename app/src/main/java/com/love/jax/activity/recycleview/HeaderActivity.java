package com.love.jax.activity.recycleview;



import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.adapter.MyHeardAdapter;
import com.love.jax.view.WrapRecycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义添加recycleView头部与尾部
 */
public class HeaderActivity extends BaseActivity {
    private WrapRecycleView recyclerView;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {
        recyclerView = (WrapRecycleView) findViewById(R.id.recyclerView);


//		View headerView = View.inflate(this, resource, root);
        TextView headerView = new TextView(this);
        //		TextView tv = headerView.findViewById(id);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headerView.setLayoutParams(params);
        headerView.setText("我是HeaderView");
        recyclerView.addHeaderView(headerView);

        TextView footerView = new TextView(this);
        params =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        footerView.setLayoutParams(params);
        footerView.setText("我是FooterView");
        recyclerView.addFooterView(footerView);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("item "+i);
        }

        MyHeardAdapter adapter = new MyHeardAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_header;
    }
}
