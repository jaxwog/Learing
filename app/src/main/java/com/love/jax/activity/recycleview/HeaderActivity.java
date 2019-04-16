package com.love.jax.activity.recycleview;



import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.adapter.MyHeardAdapter;
import com.love.jax.view.WrapRecycleView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 自定义添加recycleView头部与尾部
 */
public class HeaderActivity extends BaseActivity {
    private WrapRecycleView recyclerView;
    @BindView(R.id.outles_refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    int mInt = 1;
    LinearLayout mLayout;
    MyHeardAdapter adapter;
    List<String> list = new ArrayList<>();
    int adapterNowPos;
    int x,y;
    @SuppressLint("NewApi")
    @Override
    protected void initJestListener() {
        //上拉加载数据
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mInt++;
                if (mInt>2){
                    recyclerView.addFooterView(mLayout);
                    mRefreshLayout.setEnableLoadMore(false);
//                    adapter.notifyDataSetChanged();

                    recyclerView.setAdapter(adapter);
                    recyclerView.scrollToPosition(adapterNowPos);
//                    recyclerView.scrollTo(x,y);

                }else {
                    recyclerView.delFooterView(mLayout);
                    adapter.addDatas(list);
//                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    mRefreshLayout.setEnableLoadMore(true);
                    recyclerView.scrollToPosition(adapterNowPos);
//                    recyclerView.scrollTo(x,y);
                }
                mRefreshLayout.finishLoadMore();
            }
        });

        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int
                    oldScrollY) {
                LinearLayoutManager l = (LinearLayoutManager)recyclerView.getLayoutManager();
                x = scrollX;
                y = scrollY;
                adapterNowPos = l.findFirstVisibleItemPosition();
                if (adapterNowPos>1){
                    adapterNowPos--;
                }

//                adapterNowPos = l.findFirstVisibleItemPosition();
//                allItems = l.getItemCount();


            }

        });
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
        mLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.include_outles_footer,null);
//        mLayout = (LinearLayout) View.inflate(this, R.layout.include_outles_footer, null);
        params =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mLayout.setLayoutParams(params);

//        footerView.setLayoutParams(params);
//        footerView.setText("我是FooterView");
//        recyclerView.addFooterView(mLayout);
//        recyclerView.delFooterView(footerView);


        for (int i = 0; i < 20; i++) {
            list.add("item "+i);
        }

        adapter = new MyHeardAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //添加刷新组件
        ClassicsFooter footer = new ClassicsFooter(mContext).setSpinnerStyle(SpinnerStyle.FixedBehind);
        mRefreshLayout.setRefreshFooter(footer);

        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(true);
        mRefreshLayout.setEnableOverScrollBounce(false);
        mRefreshLayout.setEnableAutoLoadMore(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_header;
    }
}
