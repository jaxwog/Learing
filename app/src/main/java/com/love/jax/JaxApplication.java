package com.love.jax;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.love.jax.bean.dao.DbHelper;
import com.love.jax.callback.IDbHelper;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.io.File;

/**
 * com.love.jax
 * Created by jax on 2018/11/16 17:34
 * TODO:
 */
public class JaxApplication extends Application {

    private  static RefWatcher refWatcher;
    private  static JaxApplication instance;
//    public static final String FILE_PATH = Environment.getExternalStorageDirectory() + File.separator
//            + "AAA" + File.separator+ "Android" + File.separator
//            + "files" + File.separator;

    public static final String FILE_PATH = "/data/data/com.love.jax/databases/";

    private IDbHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = DbHelper.getInstance(this, FILE_PATH, "carrepairlocation.db");
        refWatcher= setupLeakCanary();
        instance = this;
    }


    public static JaxApplication getInstance(){
        return instance;
    }

    public static Context getAppContext() {
        return instance;
    }


    //获取数据库
    public IDbHelper getDataBase() {
        return dbHelper;
    }

    private RefWatcher setupLeakCanary() {
        //如果当前的进程是用来给LeakCanary进行堆分析的则return
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        JaxApplication baseApplication = (JaxApplication) context.getApplicationContext();
        return baseApplication.refWatcher;
    }

}
