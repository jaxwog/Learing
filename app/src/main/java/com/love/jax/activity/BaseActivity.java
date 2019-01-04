package com.love.jax.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.love.jax.utils.ConfigSet;
import com.love.jax.utils.Logger;

import butterknife.ButterKnife;

/**
 * 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity {
    // 声明变量
    public Context mContext;
    public Activity mActivity;
    private static final String TAG = "BaseActivity";
    /**
     * 需要判断是否为空
     * if(null!=mBundle){
     * 进行数据处理
     * }
     */
    public Bundle mBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
        mActivity = this;
        mBundle = new Bundle();
        Logger.e(TAG, "Add Activity To Stack:" + getClass().getSimpleName());
        setContentView(getContentView());
        ButterKnife.bind(this);
        initData();
        initBundle();
        initView();
        initJestListener();
    }


    /**
     * 注册监听事件
     */
    protected abstract void initJestListener();

    /**
     * 进行View初始化操作
     */
    protected abstract void initView();


    /**
     * 从Intent中或者本地文件读取数据
     */
    protected void initBundle() {
        mBundle = getIntent().getBundleExtra(ConfigSet.INTENT_BUNDLE);
    }


    /**
     * 数据初始化
     */
    protected abstract void initData();


    /**
     * 获取布局
     *
     * @return 资源id
     */
    protected abstract int getContentView();


    /**
     * 跳转到Activity
     *
     * @param cls    跳转到的Activity的class文件
     * @param bundle 传递的数据内容
     */
    public void jumpToActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(ConfigSet.INTENT_BUNDLE, bundle);
        startActivity(intent);
    }

    public void jumpToActivityResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent,requestCode);
    }

}
