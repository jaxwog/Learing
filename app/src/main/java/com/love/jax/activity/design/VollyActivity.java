package com.love.jax.activity.design;


import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.http.donwload.DownFileManager;
import com.love.jax.http.donwload.DownloadItemInfo;
import com.love.jax.http.donwload.enums.DownloadStatus;
import com.love.jax.http.donwload.interfaces.IDownloadCallable;
import com.love.jax.http.interfaces.IDataListener;
import com.love.jax.http.jsondeal.ContentBean;
import com.love.jax.http.jsondeal.FakeVolley;
import com.love.jax.view.CustomProgressBar;

/**
 * 自定义网络访问框架开发 需求：
 * 支持请求 JSON文本类型，音频，图片类型,批量下载。上传
 * 请求各种 数据时，调用层不关心上传参数分装，如（request.addParamas(key,value)）
 * 直接将参数分装成对象，传给框架
 * 获取数据后 调用层不关心JSON数据解析
 * 回调时  调用层只需要知道传入的JSON的对应的响应类。
 * 回调响应结果发生在主线程（线程切换）
 * 对下载，上传扩展
 * 支持高并发请求，请求队列依次获取，可以设置最大并发数，设置先请求先执
 *
 *
 * 会用到的知识点
 * 泛型
 * 请求队列
 * 阻塞队列
 * 线程池拒绝策略
 *
 * 设计模式
 *
 * 模板方法模式
 * 单例模式
 * 策略模式
 * 生产者消费者模式
 */
public class VollyActivity extends BaseActivity {
    private static final  String JSON_URL="https://tklife.mobile.taikang.com/search/hotspots";
    private static final  String DOWN_URL="http://gdown.baidu.com/data/wisegame/8be18d2c0dc8a9c9/WPSOffice_177.apk";

    private TextView mTextView;
    private CustomProgressBar mProgressBar;
    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {
       mTextView = findViewById(R.id.tv_show_info);
       mProgressBar = findViewById(R.id.bar_download);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_volly;
    }

    public void jsonDeal(View view) {

        for (int i = 0; i < 20; i++) {


        FakeVolley.sendRequest(null, JSON_URL, ContentBean.class,"get", new IDataListener<ContentBean>() {
            @Override
            public void onSuccess(ContentBean loginRespense) {
                Log.i("jax",loginRespense.toString());
                mTextView.setText(loginRespense.toString());
            }

            @Override
            public void onFail() {
                Log.i("jax","获取失败");
            }
        });
    }
    }

    public void fileDown(View view) {
        DownFileManager downFileManager=new DownFileManager();
        downFileManager.download(DOWN_URL);
        downFileManager.setDownCallable(new IDownloadCallable() {
            @Override
            public void onDownloadInfoAdd(int downloadId) {

            }

            @Override
            public void onDownloadInfoRemove(int downloadId) {

            }

            @Override
            public void onDownloadStatusChanged(int downloadId, DownloadStatus status) {

            }

            @Override
            public void onTotalLengthReceived(int downloadId, long totalLength) {

            }

            @Override
            public void onCurrentSizeChanged(int downloadId, double downloadpercent, long speed) {
                mProgressBar.setProgress((int) (downloadpercent*100));
                mTextView.setText("当前网速："+ speed / 1000 + "k/s");

            }

            @Override
            public void onDownloadSuccess(int downloadId) {
                mTextView.setText("文件下载成功");
            }

            @Override
            public void onDownloadError(int downloadId, int errorCode, String errorMsg) {

            }
        });


    }
}
