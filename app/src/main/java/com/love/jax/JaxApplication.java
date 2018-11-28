package com.love.jax;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * com.love.jax
 * Created by jax on 2018/11/16 17:34
 * TODO:
 */
public class JaxApplication extends Application {

    private  static RefWatcher refWatcher;
    private  static JaxApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher= setupLeakCanary();
        instance = this;
    }

    public static JaxApplication getInstance(){
        return instance;
    }

    public static Context getAppContext() {
        return instance;
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
