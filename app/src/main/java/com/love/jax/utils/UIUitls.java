package com.love.jax.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * com.love.jax.utils
 * Created by jax on 2018/11/15 22:36
 * TODO:
 */
public class UIUitls {

    //反射系统组件信息
private static final String DIMEN_CLASS = "com.android.internal.R$dimen";

public static final float STANDARD_WIDTH = 480F;
    public static final float STANDARD_HEIGHT = 800F;

    public float displayMericsWidth;
    public float displayMericsHeight;



    private Context mContext;

    private static UIUitls sUIUitls;

    public static UIUitls getInstance(Context context){
        if (sUIUitls==null){
            sUIUitls = new UIUitls(context);
        }
        return sUIUitls;
    }

    private UIUitls(Context context){
       this.mContext = context;
       //加载当前界面信息
        WindowManager window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        window.getDefaultDisplay().getMetrics(metrics);

        if (displayMericsWidth==0.0f || displayMericsHeight ==0.0f){

            //得到系统通知栏高度
            int systemBarHeight = getStatusBarValue(context,"status_bar_height",48);
            int navigationBarHeight = getNavigationBarHeight(context);


            Logger.i("wog","系统状态栏高度="+systemBarHeight);
            Logger.i("wog","虚拟三大按键高度="+navigationBarHeight);

            if (metrics.widthPixels > metrics.heightPixels){
                this.displayMericsHeight = metrics.widthPixels - systemBarHeight;
                this.displayMericsWidth = metrics.heightPixels;
            }else {
                this.displayMericsHeight = metrics.heightPixels - systemBarHeight;
                this.displayMericsWidth = metrics.widthPixels;
            }
        }


    }

    //对外提供系数
    public float getVertucakScalValue(){
        return this.displayMericsHeight / STANDARD_HEIGHT;
    }

    public float getHorizontalScalValue(){
        return this.displayMericsWidth / STANDARD_WIDTH;
    }

    public int getStatusBarValue(Context context, String systemId, int defValue){

        try {
            Class<?> clazz =  Class.forName(DIMEN_CLASS);
            Object r = clazz.newInstance();
            Field field = clazz.getField(systemId);
            int x = (int) field.get(r);
            Logger.i("wog","实际获取="+context.getResources().getDimensionPixelOffset(x));
            return context.getResources().getDimensionPixelOffset(x);
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * 获取虚拟按键高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int navigationBarHeight = 0;
        try {
            Resources rs = context.getResources();
            int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
            if (id > 0 && checkDeviceHasNavigationBar(context)) {
                navigationBarHeight = rs.getDimensionPixelSize(id);
            }
        } catch (Exception e) {
        }
        return navigationBarHeight;
    }

    /**
     * 判断有没有虚拟按键
     *
     * @param context
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }

        return hasNavigationBar;

    }


}
