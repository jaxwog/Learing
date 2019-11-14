package com.love.jax.utils;

/**
 * com.love.jax.utils
 * Created by jax on 2019-11-13 11:02
 * TODO:
 */

import android.util.Log;

import com.love.jax.callback.BehaviorTrace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 切面
 * 你想要切下来的蛋糕
 */
@Aspect
public class BehaviorAspect {

    private static final String TAG = "BehaviorAspect";
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 如何切蛋糕，切成什么样的形状
     * 切点
     */
    @Pointcut("execution(@com.love.jax.callback.BehaviorTrace  * *(..))")
    public void annoBehavior() {

    }

    /**
     * 切面
     * 蛋糕按照切点切下来之后   怎么吃
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("annoBehavior()")
    public Object dealPoint(ProceedingJoinPoint point) throws  Throwable
    {

        long beagin=System.currentTimeMillis();

        Object object=null;
        try {
            //方法执行前

            MethodSignature methodSignature= (MethodSignature) point.getSignature();
            BehaviorTrace behaviorTrace=methodSignature.getMethod().getAnnotation(BehaviorTrace.class);
            String contentType=behaviorTrace.value();
            int type=behaviorTrace.type();
            Log.i(TAG,contentType+"使用时间：   "+simpleDateFormat.format(new Date())+ ",type类型为："+type);
            //方法执行时
            object=point.proceed();
        }catch (Exception e)
        {

        }


        //方法执行完成
        Log.i(TAG,"aop消耗时间：  "+(System.currentTimeMillis()-beagin)+"ms");

        return  object;
    }


}
