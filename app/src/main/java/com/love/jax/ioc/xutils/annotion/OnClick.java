package com.love.jax.ioc.xutils.annotion;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.love.jax.ioc.xutils.annotion
 * Created by jax on 2020-01-07 15:07
 * TODO:点击事件注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventBase(listenerSetter = "setOnClickListener",listenerType = View.OnClickListener.class,callBackMethod = "onClick")
public @interface OnClick {
    /**
     * 哪些控件id 进行设置点击事件
     */
    int[] value() default -1;
}
