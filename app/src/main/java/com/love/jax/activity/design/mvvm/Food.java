package com.love.jax.activity.design.mvvm;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * com.love.jax.activity.design.mvvm
 * Created by jax on 2020-01-15 20:08
 * TODO:list列表实体类
 */
public class Food {
    private String img;

    private String description;

    private String keywords;

    public Food(String img, String description, String keywords) {
        this.img = img;
        this.description = description;
        this.keywords = keywords;
    }

    @BindingAdapter("bind:img")
    public static void getImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void click(View view) {
        Toast.makeText(view.getContext(), getDescription(), Toast.LENGTH_SHORT).show();
    }

}
