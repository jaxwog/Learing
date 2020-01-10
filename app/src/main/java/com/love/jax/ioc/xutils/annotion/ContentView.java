package com.love.jax.ioc.xutils.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.love.jax.ioc.xutils
 * Created by jax on 2020-01-03 15:48
 * TODO:用来注解 布局文件
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentView {
    int value();
}
