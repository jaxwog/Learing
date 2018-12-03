package com.love.jax.activity.recycleview;



import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

/**
 * 主题适配
 */
public class ThemeActivity extends BaseActivity {
    private ProgressBar progressBar1;
    private SwipeRefreshLayout srl;
    private ArrayAdapter<String> adapter;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {
        progressBar1 = (ProgressBar)findViewById(R.id.progressBar1);
        progressBar1.setMax(100);
        progressBar1.setProgress(50);

        srl = (SwipeRefreshLayout)findViewById(R.id.srl);
        srl.setSize(SwipeRefreshLayout.LARGE);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // 下拉完毕 加载更多数据

//				srl.setRefreshing(false);
            }
        });
        srl.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        //设置进度条的背景颜色
        srl.setProgressBackgroundColorSchemeColor(Color.YELLOW);
        //设置下拉多少距离开始刷新
//		srl.setDistanceToTriggerSync(70);

        String[] items = {"条目0","条目1","条目2","条目3","条目4","条目5","条目6",};
        //数据
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items );
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_theme;
    }

    public void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
//		popupMenu.setOnMenuItemClickListener(listener);
        popupMenu.show();

    }
    public void showPopup(View v) {
        final ListPopupWindow listPopupWindow = new ListPopupWindow(this);
        listPopupWindow.setAdapter(adapter);
        //设置锚点，弹出的位置是相对于v的位置
        listPopupWindow.setAnchorView(v);
        listPopupWindow.setWidth(200);
        listPopupWindow.setHeight(500);
        listPopupWindow.show();
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "点了第"+position, 0).show();
                listPopupWindow.dismiss();
            }
        });
    }

    public void showDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("女朋友");
        builder.setMessage("给我一个女朋友");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        builder.show();

    }
}
