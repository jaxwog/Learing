package com.love.jax.http.donwload;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.love.jax.database.db.BaseDaoFactory;
import com.love.jax.http.HttpTask;
import com.love.jax.http.RequestHodler;
import com.love.jax.http.ThreadPoolManager;
import com.love.jax.http.donwload.dao.DownLoadDao;
import com.love.jax.http.donwload.enums.DownloadStatus;
import com.love.jax.http.donwload.enums.DownloadStopMode;
import com.love.jax.http.donwload.enums.Priority;
import com.love.jax.http.donwload.interfaces.IDownloadCallable;
import com.love.jax.http.donwload.interfaces.IDownloadServiceCallable;
import com.love.jax.http.interfaces.IHttpListener;
import com.love.jax.http.interfaces.IHttpService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.FutureTask;

/**
 * com.love.jax.http.donwload
 * Created by jax on 2019-12-05 11:21
 * TODO:数据库层（应用层进行调用）去调用下载任务
 */
public class DownFileManager implements IDownloadServiceCallable {
    private static final String TAG = "jax";

    private byte[] lock = new byte[0];

    //创建数据库操作对象
    DownLoadDao downLoadDao = BaseDaoFactory.getInstance().
            getDataHelper(DownLoadDao.class, DownloadItemInfo.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 观察者模式
     */
    private final List<IDownloadCallable> applisteners =
            new CopyOnWriteArrayList<IDownloadCallable>();

    /**
     * 正在下载的所有任务
     */
    private static List<DownloadItemInfo> downloadFileTaskList = new CopyOnWriteArrayList();

    Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 根据 URL路径 下载文件
     */
    public int download(String url) {
        //路径根据SD卡路径+URL地址生成
        String[] preFix = url.split("/");
        return this.download(url, Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/" + preFix[preFix.length - 1]);
    }

    /**
     * 根据 URL路径+文件路径 下载文件
     */
    public int download(String url, String filePath) {
        String[] preFix = url.split("/");
        //文件名字根据URL后缀进行生成
        String displayName = preFix[preFix.length - 1];
        return this.download(url, filePath, displayName);
    }

    /**
     * 根据 URL路径+文件路径+保存的文件名字 下载文件
     */
    public int download(String url, String filePath, String displayName) {
        //传入任务优先级为中等
        return this.download(url, filePath, displayName, Priority.middle);
    }

    /**
     * 根据 URL路径+文件路径+保存的文件名字+任务优先级 下载文件
     */
    public int download(String url, String filePath, String displayName, Priority priority) {
        //如果任务优先级为空，设置优先级
        if (priority == null) {
            priority = Priority.low;
        }

        File file = new File(filePath);
        DownloadItemInfo downloadItemInfo = null;
        //从数据库中查找对象（先从内存中加载）
        downloadItemInfo = downLoadDao.findRecord(url, filePath);

        //没有查询到下载记录
        if (downloadItemInfo == null) {
            /**
             * 根据文件路径查找，扩大搜索范围
             */
            List<DownloadItemInfo> samesFile = downLoadDao.findRecord(filePath);

            //大于0  表示下载
            if (samesFile.size() > 0) {
                DownloadItemInfo sameDown = samesFile.get(0);
                //下载长度与文件长度相同，表示已经下载了文件
                if (sameDown.getCurrentLen() == sameDown.getTotalLen()) {
                    synchronized (applisteners) {
                        for (IDownloadCallable downloadCallable : applisteners) {
                            downloadCallable.onDownloadError(sameDown.getId(), 2, "文件已经下载了");
                        }
                    }

                }
            }
/**---------------------------------------------
 * 插入数据库
 * 可能插入失败
 * 因为filePath  和id是独一无二的  在数据库建表时已经确定了
 */
            int recrodId = downLoadDao.addRecrod(url, filePath, displayName, priority.getValue());
            if (recrodId != -1) {
                synchronized (applisteners) {
                    for (IDownloadCallable downloadCallable : applisteners) {
                        //通知应用层  数据库被添加了
                        downloadCallable.onDownloadInfoAdd(downloadItemInfo.getId());
                    }
                }
            }else {
                //插入失败时，再次进行查找，确保能查得到
                downloadItemInfo = downLoadDao.findRecord(url, filePath);
            }

        }



        downloadItemInfo = downLoadDao.findRecord(url, filePath);
        //正在下载的处理
        if (isDowning(file.getAbsolutePath())) {
            synchronized (applisteners) {
                for (IDownloadCallable downloadCallable : applisteners) {
                    downloadCallable.onDownloadError(downloadItemInfo.getId(), 4, "正在下载，请不要重复添加");
                }
            }
            return downloadItemInfo.getId();
        }


        //判断数据库是否下载完成
        if (downloadItemInfo != null) {
            downloadItemInfo.setPriority(priority.getValue());
            downloadItemInfo.setStopMode(DownloadStopMode.auto.getValue());
            //判断数据库存的 状态是否是完成
            if (downloadItemInfo.getStatus() != DownloadStatus.finish.getValue()) {
                if (downloadItemInfo.getTotalLen() == 0L || file.length() == 0L) {
                    Log.i(TAG, "还未开始下载");
                    downloadItemInfo.setStatus(DownloadStatus.failed.getValue());
                }
                //判断数据库中 总长度是否等于文件长度
                if (downloadItemInfo.getTotalLen() == file.length() && downloadItemInfo.getTotalLen() != 0) {
                    downloadItemInfo.setStatus(DownloadStatus.finish.getValue());
                    synchronized (applisteners) {
                        for (IDownloadCallable downloadCallable : applisteners) {
//防止外部出现异常情况，造成框架抛异常
                            try {
                                downloadCallable.onDownloadError(downloadItemInfo.getId(), 4,
                                        "已经下载了");
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            } else {
                if (!file.exists() || (downloadItemInfo.getTotalLen() != downloadItemInfo.getCurrentLen())) {
                    downloadItemInfo.setStatus(DownloadStatus.failed.getValue());
                }
            }

            //更新数据库状态
            downLoadDao.updateRecord(downloadItemInfo);



        /**
         * 判断是否已经下载完成
         */
        if (downloadItemInfo.getStatus() == DownloadStatus.finish.getValue()) {
            Log.i(TAG, "已经下载完成  回调应用层");
            final int downId = downloadItemInfo.getId();
            synchronized (applisteners) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (IDownloadCallable downloadCallable : applisteners) {
                            downloadCallable.onDownloadStatusChanged(downId, DownloadStatus.finish);
                        }
                    }
                });
            }
            downLoadDao.removeRecordFromMemery(downId);
            return downloadItemInfo.getId();
        }

        //之前的下载 状态为暂停状态
        List<DownloadItemInfo> allDowning = downloadFileTaskList;
        //当前下载不是最高级  则先退出下载
        if (priority != Priority.high) {
            for (DownloadItemInfo downling : allDowning) {
                //从下载表中  获取到全部正在下载的任务
                //从下载表中  获取到全部正在下载的任务
                downling = downLoadDao.findSigleRecord(downling.getFilePath());

                if (downling != null && downling.getPriority() == Priority.high.getValue()) {

                    /**
                     *     更改---------
                     *     当前下载级别不是最高级 传进来的是middle    但是在数据库中查到路径一模一样 的记录   所以他也是最高级------------------------------
                     *     比如 第一次下载是用最高级下载，app闪退后，没有下载完成，第二次传的是默认级别，这样就应该是最高级别下载

                     */
                    if (downling.getFilePath().equals(downloadItemInfo.getFilePath())) {
                        break;
                    } else {
                        return downloadItemInfo.getId();
                    }
                }
            }
        }


        reallyDown(downloadItemInfo);

        if (priority == Priority.high || priority == Priority.middle) {
            synchronized (allDowning) {
                for (DownloadItemInfo downloadItemInfo1 : allDowning) {
                    if (!downloadItemInfo.getFilePath().equals(downloadItemInfo1.getFilePath())) {
                        DownloadItemInfo downingInfo =
                                downLoadDao.findSigleRecord(downloadItemInfo1.getFilePath());
                        if (downingInfo != null) {
                            pause(downloadItemInfo.getId(), DownloadStopMode.auto);
                        }
                    }
                }
            }
            return downloadItemInfo.getId();
        }
        }


        return -1;
    }


    /**
     * 停止
     *
     * @param downloadId
     * @param mode
     */
    public void pause(int downloadId, DownloadStopMode mode) {
        if (mode == null) {
            mode = DownloadStopMode.auto;
        }
        final DownloadItemInfo downloadInfo = downLoadDao.findRecordById(downloadId);
        if (downloadInfo != null) {
            // 更新停止状态
            if (downloadInfo != null) {
                downloadInfo.setStopMode(mode.getValue());
                downloadInfo.setStatus(DownloadStatus.pause.getValue());
                downLoadDao.updateRecord(downloadInfo);
            }
            for (DownloadItemInfo downing : downloadFileTaskList) {
                if (downloadId == downing.getId()) {
                    downing.getHttpTask().pause();
                }
            }
        }
    }


    /**
     * 判断当前是否正在下载
     *
     * @param absolutePath
     * @return
     */
    private boolean isDowning(String absolutePath) {
        for (DownloadItemInfo downloadItemInfo : downloadFileTaskList) {
            if (downloadItemInfo.getFilePath().equals(absolutePath)) {
                return true;
            }
        }
        return false;
    }


    //调用层直接使用，仅仅是相当于开启线程去下载任务
    public DownloadItemInfo reallyDown(DownloadItemInfo downloadItemInfo) {

        synchronized (lock) {

            RequestHodler requestHodler = new RequestHodler();
            //设置请求下载的策略
            IHttpService httpService = new FileDownHttpService();
            //得到请求头的参数 map
            Map<String, String> map = httpService.getHttpHeadMap();
            /**
             * 处理结果的策略
             */
            IHttpListener httpListener = new DownLoadLitener(downloadItemInfo, this, httpService);

            requestHodler.setHttpListener(httpListener);
            requestHodler.setHttpService(httpService);
            requestHodler.setUrl(downloadItemInfo.getUrl());

            HttpTask httpTask = new HttpTask(requestHodler);
            /**
             * 添加
             */
            downloadFileTaskList.add(downloadItemInfo);
//            try {
//                ThreadPoolManager.getInstance().execte(new FutureTask<Object>(httpTask, null));
//            } catch (InterruptedException e) {
//
//            }
            httpTask.start();

        }

        return downloadItemInfo;

    }

    /**
     * 添加观察者
     *
     * @param downloadCallable
     */
    public void setDownCallable(IDownloadCallable downloadCallable) {
        synchronized (applisteners) {
            applisteners.add(downloadCallable);
        }

    }


    @Override
    public void onDownloadStatusChanged(DownloadItemInfo downloadItemInfo) {

    }

    @Override
    public void onTotalLengthReceived(DownloadItemInfo downloadItemInfo) {

    }

    @Override
    public void onCurrentSizeChanged(DownloadItemInfo downloadItemInfo, double downLenth,
                                     long speed) {
        Log.i("jax", "下载速度：" + speed / 1000 + "k/s");
        Log.i("jax", "-----路径  " + downloadItemInfo.getFilePath() + "  下载长度  " + downLenth + "   "
                + "速度  " + speed);

        synchronized (applisteners) {
            for (IDownloadCallable downloadCallable : applisteners) {
                downloadCallable.onCurrentSizeChanged(downloadItemInfo.getId(),downLenth,speed);
            }
        }

    }

    @Override
    public void onDownloadSuccess(DownloadItemInfo downloadItemInfo) {
        Log.i("jax",
                "下载成功    路劲  " + downloadItemInfo.getFilePath() + "  url " + downloadItemInfo.getUrl());
        DownloadItemInfo itemInfo = downLoadDao.findSigleRecord(downloadItemInfo.getFilePath());

        if (itemInfo != null) {
            itemInfo.setCurrentLen(new File(downloadItemInfo.getFilePath()).length());
            itemInfo.setFinishTime(dateFormat.format(new Date()));
            itemInfo.setStopMode(DownloadStopMode.hand.getValue());
            itemInfo.setStatus(DownloadStatus.finish.getValue());
            downLoadDao.updateRecord(itemInfo);
            synchronized (applisteners) {
                for (IDownloadCallable downloadCallable : applisteners) {
                    downloadCallable.onDownloadSuccess(itemInfo.getId());
                }
            }

        }

        resumeAutoCancelItem();
    }

    private void resumeAutoCancelItem() {
        List<DownloadItemInfo> allAutoCancelList = downLoadDao.findAllAutoCancelRecords();
        List<DownloadItemInfo> notDownloadingList = new ArrayList<>();
        for (DownloadItemInfo downloadInfo : allAutoCancelList) {
            if (!isDowning(downloadInfo.filePath)) {
                notDownloadingList.add(downloadInfo);
            }
        }

        for (DownloadItemInfo downloadInfo : notDownloadingList) {
            if (downloadInfo.getPriority() == Priority.high.getValue()) {
                resumeItem(downloadInfo.getId(), Priority.high);
                return;
            } else if (downloadInfo.getPriority() == Priority.middle.getValue()) {
                resumeItem(downloadInfo.getId(), Priority.middle);
                return;
            }

        }

    }

    private void resumeItem(int downloadId, Priority priority) {

        DownloadItemInfo downloadInfo = downLoadDao.findRecordById(downloadId);
        if (downloadInfo == null) {
            return;
        }

        if (priority == null) {
            priority = Priority.getInstance(downloadInfo.getPriority() == null ?
                    Priority.low.getValue() : Priority.middle.getValue());
        }

        File file = new File(downloadInfo.getFilePath());
        downloadInfo.setStopMode(DownloadStopMode.auto.getValue());
        downLoadDao.updateRecord(downloadInfo);
        download(downloadInfo.getUrl(), file.getPath(), null, priority);

    }

    @Override
    public void onDownloadPause(DownloadItemInfo downloadItemInfo) {

    }

    @Override
    public void onDownloadError(DownloadItemInfo downloadItemInfo, int var2, String var3) {

    }
}