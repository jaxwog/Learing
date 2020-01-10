package com.love.jax.ioc.xutils;

import android.content.Context;
import android.view.View;

import com.love.jax.ioc.xutils.annotion.ContentView;
import com.love.jax.ioc.xutils.annotion.EventBase;
import com.love.jax.ioc.xutils.annotion.OnClick;
import com.love.jax.ioc.xutils.annotion.ViewInject;
import com.love.jax.ioc.xutils.proxy.ListenerInvocationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * com.love.jax.ioc.xutils
 * Created by jax on 2020-01-03 15:46
 * TODO:通过反射，进行赋值
 * 第三方容器
 */
public class InjectUtils {

    public static void inject(Context context) {
        //调用顺序要求
        injectLayout(context);
        injectView(context);
        injectEvents(context);
    }

    //注入监听事件
    private static void injectEvents(Context context) {

        Class<?> clazz = context.getClass();
        //获取Activity里面 所有方法
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            //获取方法上所有的注解
            Annotation[] annotations = method.getAnnotations();

            for (Annotation annotation : annotations) {
                //获取注解 anntionType   相当于方法上的 OnClick  OnLongClck
                Class<?> anntionType = annotation.annotationType();
                  //防止写死,无法进行扩展，采用annotationType 获取
//                OnClick onClick = (OnClick) annotation;

                //获取注解的注解   onClick 注解上面的EventBase
                EventBase eventBase = anntionType.getAnnotation(EventBase.class);
                if (eventBase == null) {
                    continue;
                }

                /*
                 开始获取事件三要素 ---------》 通过反射注入进去
                 1 listenerSetter  返回     setOnClickListener字符串
                 */
                String listenerSetter = eventBase.listenerSetter();
                //得到 listenerType--》 View.OnClickListener.class,
                Class<?> listenerType = eventBase.listenerType();
                //callMethod--->onClick
                String callMethod = eventBase.callBackMethod();
                //方法名 与方法Method的对应关系
                Map<String, Method> methodMap = new HashMap<>();
                // callMethod--->onClick     method--->activity中的 public void onClick(View v)
                methodMap.put(callMethod, method);

                try {

                    //anntionType 没有定义成固定类型，方便进行扩展，通过反射去拿到id数组
                    Method valueMethod = anntionType.getDeclaredMethod("value");
                    int[] viewIds = (int[]) valueMethod.invoke(annotation);

                    for (int viewId : viewIds) {
                        //通过反射拿到TextView
                        Method findViewById = clazz.getMethod("findViewById", int.class);
                        View view = (View) findViewById.invoke(context, viewId);
                        if (view == null) {
                            continue;
                        }
                          /*
                        listenerSetter  setOnClickLitener 方法名
                        listenerType   View.OnClickListener.class  参数类型
                         */
                        Method setOnClickListener = view.getClass().getMethod(listenerSetter,
                                listenerType);

                        ListenerInvocationHandler handler = new ListenerInvocationHandler(context
                                , methodMap);

                        //proxyy已经实现了listenerType接口
                        Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                new Class[]{listenerType}, handler);

                        /**
                         * 类比 于
                         * textView.setOnClickListener(new View.OnClickListener() {
                         *    @Override
                         *    public void onClick(View v) { }
                         *      });
                         */
                        setOnClickListener.invoke(view, proxy);


                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }


            }
        }


    }

    /**
     * context 中不包含findViewById方法，通过反射拿取
     *
     * @param context
     */
    private static void injectView(Context context) {
        Class<?> clazz = context.getClass();
        //获取到Activity里面所有的成员变量 包含 textView
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //得到成员变量的注解
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if (viewInject != null) {
                //拿到id  R.id.tv_ioc
                int valueId = viewInject.value();
                try {
                    // View  view = activity.findViewById()
                    Method method = clazz.getMethod("findViewById", int.class);
                    //反射调用方法
                    View view = (View) method.invoke(context, valueId);
                    field.setAccessible(true);
                    field.set(context, view);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }


        }

    }


    /**
     * 通过反射设置布局加载
     */
    private static void injectLayout(Context context) {
        int layoutId = 0;
        Class<?> clazz = context.getClass();
        //拿到MainActivity类上面的注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            layoutId = contentView.value();
            try {
                Method method = clazz.getMethod("setContentView", int.class);
                method.invoke(context, layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
