package com.love.jax.database.db.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.love.jax.database.db.annotion
 * Created by jax on 2019-11-26 15:24
 * TODO:数据库表的注解
 * 注解是做一个标记，方便查找
 * Target
 * ElementType.TYPE 应用在类、接口(包括注释类型)或enum声明
 * ElementType.METHOD 应用在方法上,方法声明
 * ElementType.FIELD 应用在成员变量上, 字段声明(包括enum常量)
 *
 * Retention
 * RetentionPolicy.RUNTIME 运行时，注解还有,注释将由编译器记录在类文件中VM在运行时保留它们，因此可以反射地读取它们。
 * RetentionPolicy.SOURCE 注释将由编译器丢弃(Override)
 * RetentionPolicy.CLASS 注释由编译器记录在类文件中但是在运行时不需要被VM保留。这是默认设置的行为。
 */


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DbTable {
    String value();
}
