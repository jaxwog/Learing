package com.love.jax.utils;

import android.util.Log;

/**
 * com.love.jax.utils
 * Created by jax on 2018/11/13 11:28
 * TODO: 控制日志打印
 */
public class Logger {
    public static final boolean LOG_ENABLE;

    public Logger() {
    }

    public static void i(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.i(tag, msg);
        }

    }

    public static void v(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.v(tag, msg);
        }

    }

    public static void d(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.d(tag, msg);
        }

    }

    public static void w(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.w(tag, msg);
        }

    }

    public static void e(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.e(tag, msg);
        }

    }

    static {
        LOG_ENABLE = ConfigSet.LOGGER_ENABLE;
    }
}
