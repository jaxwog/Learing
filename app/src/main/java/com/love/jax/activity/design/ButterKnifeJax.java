package com.love.jax.activity.design;

import android.app.Activity;

/**
 * com.love.jax.activity.design
 * Created by jax on 2020-01-19 12:46
 * TODO:进行绑定
 */
public class ButterKnifeJax {
    public static void bind(Activity activity){

        String className = activity.getClass().getName()+"$ViewBinderJax";
        try {
            Class<?> viewBinderClass = Class.forName(className);
            ViewBinderJax  viewBinderJax = (ViewBinderJax) viewBinderClass.newInstance();
            viewBinderJax.bind(activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
