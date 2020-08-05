package com.love.jax.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.love.jax.utils.ListUtils;
import com.love.jax.utils.Logger;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.love.jax.eventbus
 * Created by jax on 2020/7/19 00:03
 * TODO:
 */
public class JaxEventBus {
    // volatile修饰的变量不允许线程内部缓存和重排序,即直接修改内存
    private volatile static JaxEventBus instance;
    private Map<Object, List<SubscribeMethod>> cacheMap;
    private Handler handler;

    //线程池
    private ExecutorService executorService;

    private JaxEventBus() {
        cacheMap = new HashMap<>();
        handler = new Handler(Looper.getMainLooper());
        executorService = Executors.newCachedThreadPool();
    }

    public static JaxEventBus getDefault() {
        if (instance == null) {
            synchronized (JaxEventBus.class) {
                if (instance == null) {
                    instance = new JaxEventBus();
                }
            }
        }
        return instance;
    }

    //注册
    public void register(Object subscriber){
        List<SubscribeMethod> subscribeMethods = cacheMap.get(subscriber);
        //如果没有注册，进行注册，并放到缓存中
        if (ListUtils.isEmpty(subscribeMethods)){
            subscribeMethods = getSubscribleMethods(subscriber);
            cacheMap.put(subscriber,subscribeMethods);
            Logger.i("jax","jax 注册成功");
        }

    }

    private List<SubscribeMethod> getSubscribleMethods(Object subscriber) {
        List<SubscribeMethod> list = new ArrayList<>();
        //1、拿到类
        Class<?> clazz = subscriber.getClass();

        while (clazz!=null){
            //拿到方法名字，方法名字为全类名
            String name = clazz.getName();
             //判断分类是在那个报下，（如果是系统的就不需要）
            if (name.startsWith("java.")||name.startsWith("javax.")
                    ||name.startsWith("android.")||name.startsWith("androidx.")){
                break;
            }

            //包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。当然也包括它所实现接口的方法
            Method[] declaredMethods = clazz.getDeclaredMethods();
            //2、拿到全部方法，遍历是否有注解，注解就添加进去
            for (Method method: declaredMethods){
                JaxSubscribe annotation = method.getAnnotation(JaxSubscribe.class);
                if (annotation==null){
                    continue;
                }

                int modifiers = method.getModifiers();
                if ((modifiers& Modifier.PUBLIC) == 0){
                    throw new RuntimeException("Jax Eventbus receive method must public");
                }

                // 方法必须是返回void（一次匹配）
                Type returnType = method.getGenericReturnType();
                if (!"void".equals(returnType.toString())) {
                    throw new RuntimeException(method.getName() + "方法返回必须是void");
                }


                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length!=1){
                    throw new RuntimeException("Jax Eventbus receive method must one parameter");
                }

                //检测完权限，添加到列表中
                JaxThreadMode threadMode = annotation.threadMode();
                SubscribeMethod subscribeMethod = new SubscribeMethod(method,threadMode,parameterTypes[0]);

                list.add(subscribeMethod);
            }

            clazz = clazz.getSuperclass();
        }

        return list;
    }

    public void unregister(Object subscriber){
        List<SubscribeMethod> subscribeMethods = cacheMap.get(subscriber);
        //如果不为空，移除监听
        if (!ListUtils.isEmpty(subscribeMethods)){
            cacheMap.remove(subscriber);
        }

    }

    public void post(Object obj){
        //根据obj拿到对象，然后通过反射去调用方法
        Set<Object> set = cacheMap.keySet();//获取key的iterator
        Iterator<Object> iterator = set.iterator();

        while (iterator.hasNext()){

          final   Object next = iterator.next();//获取key值，key为注册类名
            List<SubscribeMethod> list = cacheMap.get(next);//获取方法信息
            // 有可能多个方法的参数一样，从而都同时收到发送的消息
            for (final SubscribeMethod subscribeMethod:list){
                // 通过方法的类型匹配，从SecondActivity发送的EventBean对象（参数）
                // 匹配MainActivity中所有注解的方法符合要求的，都发送消息

                // class1.isAssignableFrom(class2) 判定此 Class 对象所表示的类或接口
                // 与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口

                //如果post参数与注册接收类型相同，则回调方法
                if (subscribeMethod.getEventType().isAssignableFrom(obj.getClass())){
                    switch (subscribeMethod.getThreadMode()) {
                        case MAIN:
                            //如果接收方法在主线程执行的情况
                            if(Looper.myLooper() == Looper.getMainLooper()){
                                invoke(subscribeMethod, next, obj);
                            } else {
                                //post方法执行在子线程中，接收消息在主线程中
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribeMethod, next, obj);
                                    }
                                });
                            }
                            break;
                        //接收方法在子线程种情况
                        case ASYNC:
                            //post方法执行在主线程中
                            if(Looper.myLooper() == Looper.getMainLooper()){
                                executorService.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribeMethod, next, obj);
                                    }
                                });
                            } else {
                                //post方法执行在子线程中
                                invoke(subscribeMethod, next, obj);
                            }
                            break;

                        case POSTING:
                            break;
                    }
                }

            }
        }
    }

    private void invoke(SubscribeMethod subscribeMethod, Object next, Object obj) {
        Method method = subscribeMethod.getMethod();

        try {
            method.invoke(next,obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
