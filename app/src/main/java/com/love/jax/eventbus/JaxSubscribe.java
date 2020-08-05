package com.love.jax.eventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.love.jax.eventbus
 * Created by jax on 2020/7/19 00:07
 * TODO:注解，用来接收事件
 * 使用在方法上，运行时
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JaxSubscribe {
   JaxThreadMode threadMode() default JaxThreadMode.POSTING;
}
