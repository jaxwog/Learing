package com.love.jax.ioc.xutils.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.love.jax.ioc.xutils.annotion
 * Created by jax on 2020-01-07 14:45
 * TODO:  设计目的是 对所有的事件点击 进行扩展
 *  用于注解的注解，事件三要素
 *
 *    mTextView.setOnClickListener(new View.OnClickListener() {
 *             @Override
 *             public void onClick(View v) {
 *
 *             }
 *         });
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface EventBase {
    /**
     * 设置监听的方法
     * setOnClickListener
     */
    String listenerSetter();

    /**
     * 事件类型
     * View.OnClickListener.class
     */
    Class<?> listenerType();

    /**
     * 回调方法 onClick
     * 事件被触发后，执行回调方法名称
     */
    String callBackMethod();
}
