package com.love.jax.database.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * com.love.jax.database.db
 * Created by jax on 2019-11-26 15:23
 * TODO:单例模式，打开数据库，返回basedao操作类
 */
public class BaseDaoFactory {
    //数据库存储路径
    private String sqliteDatabasePath;

    private SQLiteDatabase sqLiteDatabase;

    private static BaseDaoFactory instance = new BaseDaoFactory();

    private SQLiteDatabase userDatabase;
    //线程安全的数据
    private Map<String, BaseDao> map = Collections.synchronizedMap(new HashMap<String, BaseDao>());

    private BaseDaoFactory() {
//        sqliteDatabasePath= Environment.getExternalStorageDirectory().getAbsolutePath()
//        +"/teacher.db";
        File file = new File(Environment.getExternalStorageDirectory(), "update");
        if (!file.exists()) {
            file.mkdirs();
        }
        sqliteDatabasePath = file.getAbsolutePath() + "/teacher.db";
        openDatabase();
    }

    /**
     * @param clazz       传入的为BaseDao
     * @param entityClass 传入的为数据实体类
     * @param <T>         BaseDao持有M数据实体类， 范围为BaseDao的范围
     * @param <M>         无范围
     * @return BaseDao
     */
    public synchronized <T extends BaseDao<M>, M> T getDataHelper(Class<T> clazz,
                                                                  Class<M> entityClass) {
        BaseDao baseDao = null;
        try {
            //根据key去获取当前对象是否已经创建，如果创建直接拿取内存中保存的对象
            if(map.get(clazz.getSimpleName())!=null) {
                return (T) map.get(clazz.getSimpleName());
            }
            //通过反射创建具体的BaseDao子类
            baseDao = clazz.newInstance();

            baseDao.init(entityClass, this.sqLiteDatabase);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return (T) baseDao;
    }

    /**
     * 创建数据库对象，数据库操作类根据枚举模式获取到的路径
     * 并把该对象放入到数据库中
     * @return
     */
    public synchronized <T extends BaseDao<M>, M> T getLoginHelper(Class<T> clazz,
                                                                   Class<M> entityClass) {
        userDatabase =
                SQLiteDatabase.openOrCreateDatabase(PrivateDataBaseEnums.database.getValue(), null);
        BaseDao baseDao = null;
        try {
            baseDao = clazz.newInstance();
            baseDao.init(entityClass, userDatabase);
            map.put(clazz.getSimpleName(), baseDao);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) baseDao;
    }

    private void openDatabase() {
        //打开数据库，没有数据库就创建一个数据库
        this.sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqliteDatabasePath, null);
    }


    public static BaseDaoFactory getInstance() {
        return instance;
    }

}
