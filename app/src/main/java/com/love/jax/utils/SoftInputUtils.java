package com.love.jax.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * com.love.jax.utils
 * Created by jax_wog on 2018/11/13.
 * Todo:显示隐藏软键盘
 */

public class SoftInputUtils {


    /**
     * 显示软键盘
     *
     * @param activity
     * @param editText
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public static void closeSoftInputFromWindow(Context context, View view) {

        /**隐藏软键盘**/
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) context.getSystemService
                    (Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
