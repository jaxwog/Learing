package com.love.jax.activity.performance;

import android.Manifest;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.widget.ListView;
import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.adapter.MyCursorAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;


public class LoadMoreActivity extends BaseActivity {

    private static final String TAG = "LoadMoreActivity";
    // 查询指定的条目
    private static final String[] CALLLOG_PROJECTION = new String[] { CallLog.Calls._ID, CallLog.Calls.NUMBER,
            CallLog.Calls.CACHED_NAME, CallLog.Calls.TYPE, CallLog.Calls.DATE };
    private ListView mListView;
    private MyLoaderCallback mLoaderCallback = new MyLoaderCallback();
    private MyCursorAdapter mAdapter;
    private RxPermissions rxPermissions;


    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {

    }

    //检查读写权限
    private void checkLocal() {

        rxPermissions.request(Manifest.permission.READ_CALL_LOG,Manifest.permission.WRITE_CALL_LOG,Manifest.permission.CALL_PHONE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {


            }
        },new Consumer<Throwable>(){

            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });


    }

    @Override
    protected void initData() {
        rxPermissions = new RxPermissions(this);
        checkLocal();
        mListView = (ListView) findViewById(R.id.lv_list);

        mAdapter = new MyCursorAdapter(LoadMoreActivity.this, null);
        mListView.setAdapter(mAdapter);

        //执行Loader的回调
        getLoaderManager().initLoader(0, null, mLoaderCallback);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_load_more;
    }

    private class MyLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

        //创建Loader
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            //加载的过程在子线程中进行
            CursorLoader loader = new CursorLoader(LoadMoreActivity.this, CallLog.Calls.CONTENT_URI, CALLLOG_PROJECTION,
                    null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
            Log.d(TAG, "onCreateLoader");
            return loader;
        }

        //Loader检测底层数据，当检测到改变时，自动执行新的载入获取最新数据
        //Activity/Fragment所需要做的就是初始化Loader，并且对任何反馈回来的数据进行响应。
        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data == null)
                return;
            mAdapter.swapCursor(data);
            Log.d(TAG, "onLoadFinished data count = " + data.getCount());
        }

        //OnDestroy，自动停止load
        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            Log.d(TAG, "onLoaderReset");
            mAdapter.swapCursor(null);
        }
    }


}
