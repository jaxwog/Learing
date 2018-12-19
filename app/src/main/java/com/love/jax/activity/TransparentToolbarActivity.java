package com.love.jax.activity;

import android.support.v7.widget.Toolbar;

import com.love.jax.R;
import com.love.jax.callback.TranslucentListener;
import com.love.jax.view.TransparentScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ScrollView 根据滑动距离设置Toolbar透明度
 */
public class TransparentToolbarActivity extends BaseActivity implements TranslucentListener {


    @BindView(R.id.scrollView)
    TransparentScrollView scrollView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void initJestListener() {
        scrollView.setTranslucentListener(this);

    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_transparent_toolbar;
    }

    @Override
    public void onTranlucent(float alpha) {
          toolbar.setAlpha(alpha);
    }

}
