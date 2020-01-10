package com.love.jax.ioc.xutils.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.love.jax.ioc.xutils.annotion
 * Created by jax on 2020-01-03 15:53
 * TODO:注解View对象
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {
    int value();
}
