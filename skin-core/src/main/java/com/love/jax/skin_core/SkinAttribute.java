package com.love.jax.skin_core;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.love.jax.skin_core.utils.SkinResources;
import com.love.jax.skin_core.utils.SkinThemeUtils;

import java.util.ArrayList;
import java.util.List;

//属性获取
public class SkinAttribute {

    private static final List<String> mAttributes = new ArrayList<>();


    static {
        mAttributes.add("background");
        mAttributes.add("src");


        mAttributes.add("textColor");
        mAttributes.add("drawableLeft");
        mAttributes.add("drawableTop");
        mAttributes.add("drawableRight");
        mAttributes.add("drawableBottom");
//定义的字体
        mAttributes.add("skinTypeface");

    }

    private Typeface typeface;

    //记录换肤需要操作的View与属性信息
    private List<SkinView> mSkinViews = new ArrayList<>();

    public SkinAttribute(Typeface typeface) {
        this.typeface = typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public void load(View view, AttributeSet attrs) {
        List<SkinPair> mSkinPars = new ArrayList<>();//当前View所有需要替换的值
        //遍历查找
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            //android:textColor="@color/selector_color_test"
            //获得属性名  mAttributes   textColor
            String attributeName = attrs.getAttributeName(i);
            Log.i("skin",attributeName);
            //如果包含需要更改的属性
            if (mAttributes.contains(attributeName)) {
                //@color/selector_color_test
                String attributeValue = attrs.getAttributeValue(i);
                // 如果是color的 以#开头表示写死的颜色 不可用于换肤
                if (attributeValue.startsWith("#")) {
                    continue;
                }
                int resId;//R文件对应的id，字符串"?123243523"
                if (attributeValue.startsWith("?")) {
                    //系统属性
                    int attrId = Integer.parseInt(attributeValue.substring(1));
                    //只拿第一个
                    resId = SkinThemeUtils.getResId(view.getContext(), new int[]{attrId})[0];
                } else {
                    // 正常以 @ 开头    @color/selector_color_test
                    //可能出现@null的情况，拿不到id值
                    resId = Integer.parseInt(attributeValue.substring(1));
                }
//                Log.e("   " + attributeName + " = " + attributeValue);
                Log.i("skin", attributeName + " = " + attributeValue);
                SkinPair skinPair = new SkinPair(attributeName, resId);
                mSkinPars.add(skinPair);//保存全部的属性值
            }
        }

        if (!mSkinPars.isEmpty()) {
            SkinView skinView = new SkinView(view, mSkinPars);
            skinView.applySkin(typeface);
            mSkinViews.add(skinView);
        } else if (view instanceof TextView || view instanceof SkinViewSupport) {
            //没有属性满足 但是需要修改字体
            SkinView skinView = new SkinView(view, mSkinPars);
            skinView.applySkin(typeface);
            mSkinViews.add(skinView);
        }
    }

    public void applySkin() {
        for (SkinView mSkinView : mSkinViews) {
            mSkinView.applySkin(typeface);
        }
    }

    //View对应的属性值，id保存起来
    static class SkinView {
        View view;
        List<SkinPair> skinPairs = new ArrayList<>();

        public SkinView(View view, List<SkinPair> skinPairs) {
            this.view = view;
            this.skinPairs = skinPairs;
        }

        public void applySkin(Typeface typeface) {
            applyTypeFace(typeface);
            applySkinSupport();
            for (SkinPair skinPair : skinPairs) {
                Drawable left = null, top = null, right = null, bottom = null;
                switch (skinPair.attributeName) {
                    case "background":
                        Object background = SkinResources.getInstance().getBackground(skinPair
                                .resId);
                        if (background instanceof Integer) {
                            view.setBackgroundColor((int) background);
                        } else {
                            ViewCompat.setBackground(view, (Drawable) background);
                        }
                        break;
                    case "src":
                        background = SkinResources.getInstance().getBackground(skinPair
                                .resId);
                        if (background instanceof Integer) {
                            ((ImageView) view).setImageDrawable(new ColorDrawable((Integer)
                                    background));
                        } else {
                            ((ImageView) view).setImageDrawable((Drawable) background);
                        }
                        break;
                    case "textColor":
                        ((TextView) view).setTextColor(SkinResources.getInstance().getColorStateList
                                (skinPair.resId));
                        break;
                    case "drawableLeft":
                        left = SkinResources.getInstance().getDrawable(skinPair.resId);
                        break;
                    case "drawableTop":
                        top = SkinResources.getInstance().getDrawable(skinPair.resId);
                        break;
                    case "drawableRight":
                        right = SkinResources.getInstance().getDrawable(skinPair.resId);
                        break;
                    case "drawableBottom":
                        bottom = SkinResources.getInstance().getDrawable(skinPair.resId);
                        break;
                    case "skinTypeface":
                        applyTypeFace(SkinResources.getInstance().getTypeface
                                (skinPair.resId));
                        break;
                    default:
                        break;
                }
                if (null != left || null != right || null != top || null != bottom) {
                    ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(left, top, right,
                            bottom);
                }
            }
        }

        private void applySkinSupport() {
            if (view instanceof SkinViewSupport) {
                ((SkinViewSupport) view).applySkin();
            }
        }

        private void applyTypeFace(Typeface typeface) {
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(typeface);
            }
        }
    }

    //属性对应的id，保存起来
    static class SkinPair {

        String attributeName;
        int resId;

        public SkinPair(String attributeName, int resId) {
            this.attributeName = attributeName;
            this.resId = resId;
        }
    }
}
