package com.love.jax.butterknife_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.love.jax.butterknife_annotations
 * Created by jax on 2020-01-19 10:33
 * TODO:
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface BindViewJax {
    int value();
}
