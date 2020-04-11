package com.love.jax.activity.performance;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.bean.MapInfo;
import com.love.jax.bean.Node;
import com.love.jax.utils.Astar;
import com.love.jax.utils.MapUtils;
import com.love.jax.view.ShowMapView;

import static com.love.jax.utils.MapUtils.map;
import static com.love.jax.utils.MapUtils.result;
import static com.love.jax.utils.MapUtils.touchFlag;
import static com.love.jax.utils.MapUtils.startRow;
import static com.love.jax.utils.MapUtils.startCol;
import static com.love.jax.utils.MapUtils.endRow;
import static com.love.jax.utils.MapUtils.endCol;

public class ManhattanDistanceActivity extends BaseActivity {

    ShowMapView showMapView;

    Handler handler = null;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {
        handler = new Handler(Looper.getMainLooper());
        showMapView = (ShowMapView) findViewById(R.id.show);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_manhattan_distance;
    }

    public void reset(View view) {
        MapUtils.path = null;
        MapUtils.result.clear();
        touchFlag = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 2) {
                    map[i][j] = 0;
                }
            }
        }
        showMapView.invalidate();
    }

    public void cal(View view) {
        MapInfo info = new MapInfo(map, map[0].length, map.length, new Node(startCol, startRow),
                new Node(endCol, endRow));
        Log.i("jax", "start=" + startRow + " " + startCol);
        Log.i("jax", "end=" + endRow + " " + endCol);
        new Astar().start(info);
        new MoveThread(showMapView).start();
    }

    class MoveThread extends Thread {
        ShowMapView showMapView;

        public MoveThread(ShowMapView showMapView) {
            this.showMapView = showMapView;
        }

        @Override
        public void run() {
            while (result.size() > 0) {
                Log.i("jax", result.size() + "");
                Node node = result.pop();
                map[node.coord.y][node.coord.x] = 2;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        showMapView.invalidate();
                    }
                });

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map[node.coord.y][node.coord.x] = 0;

            }
            MapUtils.touchFlag = 0;
        }
    }
}
