package com.love.jax.activity.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * com.love.jax.activity.fragment
 * Created by jax on 2019/1/7 15:25
 * TODO:
 */
public class NewsDetailFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getContext());
        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        tv.setBackgroundColor(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
        tv.setText(title);
        return tv;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
