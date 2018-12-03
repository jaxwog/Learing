package com.love.jax.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;

/**
 * com.love.jax.view
 * Created by jax on 2018/11/29 16:35
 * TODO:
 */
public class InputFixedLayout  extends FrameLayout {
    EditText viewEdit;
    private int windowVisibleHeight = -1;
    private int layoutHeight = -1;
    InputFixedLayout.OnInputToggleListener listener;

    public InputFixedLayout(@NonNull Context context) {
        super(context);
        this.init();
    }

    public InputFixedLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public InputFixedLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {
        Activity activity = (Activity)this.getContext();
        activity.getWindow().setSoftInputMode(18);
    }

    public void setForceResize(boolean forceResize) {
        Activity activity = (Activity)this.getContext();
        FrameLayout decorView = (FrameLayout)activity.getWindow().getDecorView();
        if (forceResize) {
            if (this.viewEdit != null && this.viewEdit.getParent() != null && this.viewEdit.getParent().equals(this)) {
                return;
            }

            this.viewEdit = new EditText(this.getContext());
            this.viewEdit.setBackgroundDrawable((Drawable)null);
            this.viewEdit.setCursorVisible(false);
            this.viewEdit.setFocusableInTouchMode(false);
            this.viewEdit.setEnabled(false);
            decorView.addView(this.viewEdit, new FrameLayout.LayoutParams(3, 3, 80));
        } else if (this.viewEdit != null && this.viewEdit.getParent() != null && this.viewEdit.getParent().equals(this)) {
            decorView.removeView(this.viewEdit);
        }

    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Rect rect = new Rect();
        this.getWindowVisibleDisplayFrame(rect);
        if (this.windowVisibleHeight > 0 && this.layoutHeight > 0) {
            if (rect.height() > 0 && this.layoutHeight > 0) {
                if (rect.height() != this.windowVisibleHeight && this.layoutHeight != this.getHeight()) {
                    if (rect.height() < this.windowVisibleHeight) {
                        if (this.layoutHeight > this.getHeight() && this.listener != null) {
                            this.listener.onInputVisible(Math.abs(this.windowVisibleHeight - rect.height()));
                        }
                    } else if (this.layoutHeight < this.getHeight() && this.listener != null) {
                        this.listener.onInputHide(Math.abs(this.windowVisibleHeight - rect.height()));
                    }

                    this.windowVisibleHeight = rect.height();
                    this.layoutHeight = this.getHeight();
                }
            }
        } else {
            this.windowVisibleHeight = rect.height();
            this.layoutHeight = this.getHeight();
        }
    }

    public void setOnInputToggleListener(InputFixedLayout.OnInputToggleListener listener) {
        this.listener = listener;
    }

    public interface OnInputToggleListener {
        void onInputVisible(int var1);

        void onInputHide(int var1);
    }
}
