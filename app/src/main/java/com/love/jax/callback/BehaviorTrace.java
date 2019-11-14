package com.love.jax.callback;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.love.jax.callback
 * Created by jax on 2019-11-13 10:56
 * TODO:注解
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BehaviorTrace {
    String value();
    int type();
}
