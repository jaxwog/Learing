package com.love.jax.bean;

import android.util.Log;

/**
 * com.love.jax.bean
 * Created by jax on 2020/3/17 17:07
 * TODO:包含地图所需的所有输入数据
 */
public class MapInfo {
    public int[][] map; // 二维数组的地图
    public int width; // 地图的宽
    public int hight; // 地图的高
    public Node start; // 起始结点
    public Node end; // 最终结点

    public MapInfo(int[][] map, int width, int hight, Node start, Node end)
    {
        this.map = map;
        this.width = width;
        this.hight = hight;
        this.start = start;
        this.end = end;
        Log.i("jett","初始化地图成功");
    }
}
