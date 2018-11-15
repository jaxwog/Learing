package com.love.jax.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * com.love.jax.database
 * Created by jax on 2018/11/13 11:14
 * TODO: 数据持久化到本地文件
 */
public class HistoryListData {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public static final String FILENAME_SEARCH = "HistroySearch";


    public HistoryListData(Context mContext) {
        preferences = mContext.getSharedPreferences(FILENAME_SEARCH, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 保存List
     *
     * @param tag      key
     * @param datalist 数据内容
     */
    public <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.clear();
        editor.putString(tag, strJson);
        //        editor.commit();
        editor.apply();

    }

    /**
     * 获取List
     *
     * @param tag key
     * @return 数据内容
     */
    public <T> List<T> getDataList(String tag) {
        List<T> datalist = new ArrayList<T>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;

    }

    /**
     * 删除数据内容
     */
    public void delDataList(String tag) {
        editor.remove(tag);
        editor.apply();
    }
}
