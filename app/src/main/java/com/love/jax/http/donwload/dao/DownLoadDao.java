package com.love.jax.http.donwload.dao;

import android.database.Cursor;

import com.love.jax.database.db.BaseDao;
import com.love.jax.http.donwload.DownloadItemInfo;
import com.love.jax.http.donwload.enums.DownloadStatus;
import com.love.jax.http.donwload.enums.DownloadStopMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * com.love.jax.http.donwload.dao
 * Created by jax on 2019-12-05 22:23
 * TODO:下载数据操作数据库（DownloadItemInfo数据库表字段实体类）
 * 具备功能：
 * 1、内部应该含有DownItemInfo  list此集合包含着自动暂停，没有完成的下载
 * 2、比较器Comparator 根据Id排序
 * 3、查找自动取消的下载集合，方便外层做恢复下载
 * 4、更新文件长度
 * 5、根据id查找下载记录对象
 * 6、根据下载地址和下载文件路径查找下载记录（防止出现文件路径相同）
 * 7、根据 下载文件路径查找下载记录
 * 8、根据id更新文件当前下载大小
 * 9、根据下载id删除下载记录
 * 10、根据id从内存中移除下载记录
 */
public class DownLoadDao extends BaseDao<DownloadItemInfo> {

    /**
     * 保存应该下载的集合，不包括已经下载成功的
     * 保存在内存中的缓存策略
     */
    private List<DownloadItemInfo> downloadItemInfoList =
            Collections.synchronizedList(new ArrayList<DownloadItemInfo>());

    private DownloadInfoComparator downloadInfoComparator = new DownloadInfoComparator();

    @Override
    protected String createTable() {
        return "create table if not exists  t_downloadInfo(" + "id Integer primary key, " + "url "
                + "TEXT not null," + "filePath TEXT not null, " + "displayName TEXT, " + "status "
                + "Integer, " + "totalLen Long, " + "currentLen Long," + "startTime TEXT," +
                "finishTime TEXT," + "userId TEXT, " + "httpTaskType TEXT," + "priority  Integer,"
                + "stopMode Integer," + "downloadMaxSizeKey TEXT," + "unique(filePath))";
    }

    @Override
    public List<DownloadItemInfo> query(String sql) {

        return null;
    }


    /**
     * 生成下载id,查找数据库中最大的id，然后+1
     *
     * @return 返回下载id
     */
    private Integer generateRecordId() {
        int maxId = 0;
        String sql = "select max(id)  from " + getTableName();
        synchronized (DownLoadDao.class) {
            Cursor cursor = this.database.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                String[] colmName = cursor.getColumnNames();
                int index = cursor.getColumnIndex("max(id)");
                if (index != -1) {
                    Object value = cursor.getInt(index);
                    if (value != null) {
                        maxId = Integer.parseInt(String.valueOf(value));
                    }
                }
            }

        }
        return maxId + 1;
    }

    /**
     * 根据下载地址和下载文件路径查找下载记录
     *
     * @param url      下载地址
     * @param filePath 下载文件路径
     * @return
     */
    public DownloadItemInfo findRecord(String url, String filePath) {
        synchronized (DownLoadDao.class) {
            for (DownloadItemInfo record : downloadItemInfoList) {
                if (record.getUrl().equals(url) && record.getFilePath().equals(filePath)) {
                    return record;
                }
            }
            /**
             * 内存集合找不到，就从数据库中查找
             */
            DownloadItemInfo where = new DownloadItemInfo();
            where.setUrl(url);
            where.setFilePath(filePath);
            List<DownloadItemInfo> resultList = super.query(where);
            if (resultList.size() > 0) {
                return resultList.get(0);
            }
            return null;
        }

    }

    /**
     * 根据 下载文件路径查找下载记录
     *
     * @param filePath 下载文件路径
     * @return
     */
    public List<DownloadItemInfo> findRecord(String filePath) {
        synchronized (DownLoadDao.class) {
            DownloadItemInfo where = new DownloadItemInfo();
            where.setFilePath(filePath);
            List<DownloadItemInfo> resultList = super.query(where);
            return resultList;
        }

    }

    /**
     * 添加下载记录
     *
     * @param url         下载地址
     * @param filePath    下载文件路径
     * @param displayName 文件显示名
     * @param priority    小组优先级
     * @return 下载id
     */
    public int addRecrod(String url, String filePath, String displayName,
                                      int priority) {
        synchronized (DownLoadDao.class) {
            DownloadItemInfo existDownloadInfo = findRecord(url, filePath);
            if (existDownloadInfo == null) {
                DownloadItemInfo record = new DownloadItemInfo();
                record.setId(generateRecordId());
                record.setUrl(url);
                record.setFilePath(filePath);
                record.setDisplayName(displayName);
                record.setStatus(DownloadStatus.waitting.getValue());
                record.setTotalLen(0L);
                record.setCurrentLen(0L);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                record.setStartTime(dateFormat.format(new Date()));
                record.setFinishTime("0");
                record.setPriority(priority);
                super.insert(record);
                downloadItemInfoList.add(record);
                return record.getId();
            }
            return -1;
        }
    }

    /**
     * 更新下载记录（更新数据库并更新内存中的信息），以id方式进行更新
     *
     * @param record 下载记录
     * @return
     */
    public int updateRecord(DownloadItemInfo record) {
        DownloadItemInfo where = new DownloadItemInfo();
        where.setId(record.getId());
        int result = 0;
        synchronized (DownLoadDao.class) {
            try {
                result = super.update(record, where);
            } catch (Throwable e) {
            }
            if (result > 0) {
                for (int i = 0; i < downloadItemInfoList.size(); i++) {
                    if (downloadItemInfoList.get(i).getId().intValue() == record.getId()) {
                        downloadItemInfoList.set(i, record);
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 根据id从内存中移除下载记录
     *
     * @param id 下载id
     * @return true标示删除成功，否则false
     */
    public boolean removeRecordFromMemery(int id) {
        synchronized (DownloadItemInfo.class) {
            for (int i = 0; i < downloadItemInfoList.size(); i++) {
                if (downloadItemInfoList.get(i).getId() == id) {
                    downloadItemInfoList.remove(i);
                    break;
                }
            }
            return true;
        }
    }

    /**
     * 根据下载地址和下载文件路径查找下载记录
     * <p>
     * 下载地址
     *
     * @param filePath 下载文件路径
     * @return
     */
    public DownloadItemInfo findSigleRecord(String filePath) {
        List<DownloadItemInfo> downloadInfoList = findRecord(filePath);
        if (downloadInfoList.isEmpty()) {
            return null;
        }
        return downloadInfoList.get(0);
    }


    /**
     * 根据id查找下载记录对象
     *
     * @param recordId
     * @return
     */
    public DownloadItemInfo findRecordById(int recordId) {
        synchronized (DownLoadDao.class) {
            for (DownloadItemInfo record : downloadItemInfoList) {
                if (record.getId() == recordId) {
                    return record;
                }
            }

            DownloadItemInfo where = new DownloadItemInfo();
            where.setId(recordId);
            List<DownloadItemInfo> resultList = super.query(where);
            if (resultList.size() > 0) {
                return resultList.get(0);
            }
            return null;
        }

    }

    //查找根据下载优先级调度自动退出的下载记录
    public List<DownloadItemInfo> findAllAutoCancelRecords(){
        List<DownloadItemInfo> resultList = new ArrayList<>();
        synchronized (DownLoadDao.class){
            DownloadItemInfo downloadItemInfo = null;
            for (int i = 0; i < downloadItemInfoList.size(); i++) {
                downloadItemInfo = downloadItemInfoList.get(i);
                if (downloadItemInfo.getStatus()!=DownloadStatus.failed.getValue()
                        &&downloadItemInfo.getStatus()==DownloadStatus.pause.getValue()
                        && downloadItemInfo.getStopMode() == DownloadStopMode.auto.getValue()){
                    resultList.add(downloadItemInfo);
                }

            }

            if (!resultList.isEmpty()){
                Collections.sort(resultList,downloadInfoComparator);
            }
        }
        return resultList;
    }


    /**
     * 比较器，比较两个id大小
     */
    class DownloadInfoComparator implements Comparator<DownloadItemInfo> {
        @Override
        public int compare(DownloadItemInfo lhs, DownloadItemInfo rhs) {
            return rhs.getId() - lhs.getId();
        }
    }
}
