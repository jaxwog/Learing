package com.love.jax.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.love.jax.R;
import com.love.jax.utils.Logger;

public class TranslateFragment extends Fragment {

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        int layoutId = bundle.getInt("layoutId");
        int pageIndex = bundle.getInt("pageIndex");

        View view = inflater.inflate(layoutId, null);
        view.setTag(pageIndex);//设置标记，用来记录当前Fragment的是第几个
        return view;
    }


}