package com.love.jax.activity.performance;



import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.callback.BehaviorTrace;
import com.love.jax.database.db.BaseDaoFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 面向切面编程AOP，面向对象编程OOP
 * 每个对象切开，进行标记,运行在编译时
 * aspectj进行处理
 * aspect切面，pointcut切点，around处理
 * @see com.love.jax.utils.BehaviorAspect
 * 参考https://www.cnblogs.com/weizhxa/p/8567942.html
 */
public class AopActivity extends BaseActivity {
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String TAG = "AopActivity";


    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_aop;
    }

    public void mAudio(View view) {

        long beagin=System.currentTimeMillis();

        //摇一摇的代码逻辑
        {
            SystemClock.sleep(3000);

            Log.i(TAG,"  美女  睡不着   热不热");

        }

        Log.i(TAG,"消耗时间：  "+(System.currentTimeMillis()-beagin)+"ms");
    }

    public void mText(View view) {
        //统计用户行为 的逻辑
        Log.i(TAG,"文字：  使用时间：   "+simpleDateFormat.format(new Date()));
        long beagin=System.currentTimeMillis();

        //摇一摇的代码逻辑
        {
            SystemClock.sleep(3000);
            Log.i(TAG,"  热   我们去18");

        }

        Log.i(TAG,"消耗时间：  "+(System.currentTimeMillis()-beagin)+"ms");
    }

    @BehaviorTrace(value = "摇一摇",type = 1)
    public void mShake(View view) {

        SystemClock.sleep(3000);
        Log.i(TAG,"  摇到一个嫩模：  约不约");
    }
}
