package com.love.jax.eventbus;

import java.lang.reflect.Method;

/**
 * com.love.jax.eventbus
 * Created by jax on 2020/7/19 00:33
 * TODO:注册类中接收方法信息
 */
public class SubscribeMethod {

    //注册方法
    private Method method;
    //线程类型
    private JaxThreadMode threadMode;
    //参数类型
    private Class<?> eventType;

    public SubscribeMethod(Method method, JaxThreadMode threadMode, Class<?> eventType) {
        this.method = method;
        this.threadMode = threadMode;
        this.eventType = eventType;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public JaxThreadMode getThreadMode() {
        return threadMode;
    }

    public void setThreadMode(JaxThreadMode threadMode) {
        this.threadMode = threadMode;
    }

    public Class<?> getEventType() {
        return eventType;
    }

    public void setEventType(Class<?> eventType) {
        this.eventType = eventType;
    }
}
