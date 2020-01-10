package com.love.jax.ioc.xutils.annotion;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.love.jax.ioc.xutils.annotion
 * Created by jax on 2020-01-07 16:16
 * TODO:长按事件注解
 *
 *  mTextView.setOnLongClickListener(new View.OnLongClickListener() {
 *             @Override
 *             public boolean onLongClick(View v) {
 *                 return false;
 *             }
 *         });
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventBase(listenerSetter = "setOnLongClickListener",
        listenerType = View.OnLongClickListener.class,callBackMethod = "onLongClick")
public @interface OnLongClick {
    int[] value() default -1;
}
