package com.love.jax.database.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.love.jax.database.db.annotion.DbFiled;
import com.love.jax.database.db.annotion.DbTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * com.love.jax.database.db
 * Created by jax on 2019-11-26 15:22
 * TODO:
 */
public abstract class BaseDao<T> implements IBaseDao<T> {
    /**
     * 需要跟底层打交道，持有数据库操作类的引用
     */
    protected SQLiteDatabase database;

    /**
     * 保证实例化一次
     */
    private boolean isInit=false;

    /**
     * 持有操作数据库表所对应的java类型
     * User
     */
    private Class<T> entityClass;

    public String getTableName() {
        return tableName;
    }

    /**
     * 数据库表名
     */
    private String tableName;

    /**
     * 维护这表名与成员变量名的映射关系
     * key---》数据库列名
     * value --》Field实体类对应的方法
     */
    private HashMap<String, Field> cacheMap;



    /**
     * @param entity 被操作数据实体类
     * @param sqLiteDatabase
     * @return
     * 实例化一次
     */
    protected synchronized boolean init(Class<T> entity, SQLiteDatabase sqLiteDatabase) {
        if (!isInit) {
            this.database = sqLiteDatabase;
            entityClass = entity;


            //得到数据库表名，通过注解或者类名
            if (entity.getAnnotation(DbTable.class)==null){
                tableName = entity.getSimpleName();
            }else {
                tableName = entity.getAnnotation(DbTable.class).value();
            }

            //如果数据库没有打开，直接返回
            if(!database.isOpen()) {
                return  false;
            }

            //创建数据库T类型表
            if(!TextUtils.isEmpty(createTable())) {
                database.execSQL(createTable());
            }
            cacheMap = new HashMap<>();

            initCacheMap();

            isInit=true;
        }



        return isInit;
    }

    //维护映射关系
    private void initCacheMap() {
        //第一次去查询一遍，找到表的列名，从第一条开始去查询0条数据
        String sql="select * from "+this.tableName+" limit 1 , 0";
        Cursor cursor=null;
        try {
            cursor=database.rawQuery(sql,null);

            //拿到数据库表的列名数组
            String [] columnNames = cursor.getColumnNames();

            //拿到具体实体类T的成员变量或者方法 Filed 数组
            Field[] colmunFields = this.entityClass.getFields();

            for (Field field:colmunFields){
                //功能是启用或禁用安全检查
                field.setAccessible(true);
            }

            /**
             * 双重循环遍历，找到表的列名与实体类变量对应关系
             */
            for (int i = 0; i < columnNames.length; i++) {

                /**
                 * 如果找到对应的Filed就赋值给他
                 * User
                 */
                Field colmunFiled=null;
                for (int j = 0; j < colmunFields.length; j++) {
                    String fieldName=null;
                    if(colmunFields[j].getAnnotation(DbFiled.class)!=null) {
                        fieldName=colmunFields[j].getAnnotation(DbFiled.class).value();
                    }else {
                        fieldName =colmunFields[j].getName();
                    }

                    /**
                     * 如果表的列名 等于了  成员变量的注解名字
                     */
                    if(columnNames[i].equals(fieldName)) {
                        colmunFiled = colmunFields[j];
                        break;
                    }

                }

                //找到了对应关系
                if(colmunFiled!=null) {
                    cacheMap.put(columnNames[i],colmunFiled);
                }

            }

        }catch (Exception e){

        }finally {
            cursor.close();
        }




    }




    private ContentValues getValuesCV(T entity) {
        ContentValues contentValues=new ContentValues();
        //得到迭代对象iterator
        Iterator<Field> filedsIterator=cacheMap.values().iterator();

        while (filedsIterator.hasNext()){
            //获取到方法，变量
            Field colmunToFiled=filedsIterator.next();
            String cacheKey=null;//方法名字获取到的注解，也就是数据库表列名
            String cacheValue=null;//实体类T方法名获取到的值
            //获取到对象T的方法名字（之前存入到了CacheMap中）
            if(colmunToFiled.getAnnotation(DbFiled.class)!=null) {
                cacheKey=colmunToFiled.getAnnotation(DbFiled.class).value();
            }else {
                cacheKey=colmunToFiled.getName();
            }

            try {
                if(null==colmunToFiled.get(entity)) {
                    continue;
                }
                cacheValue=colmunToFiled.get(entity).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //把获取到的数据库名字跟
            contentValues.put(cacheKey,cacheValue);

        }


        return contentValues;
    }

    /**
     * 将对象T装换为HashMap类型
     * @param entity
     * @return
     */
    private Map<String, String> getValues(T entity) {
        HashMap<String,String> result=new HashMap<>();
        //得到迭代对象iterator
        Iterator<Field> filedsIterator=cacheMap.values().iterator();

        while (filedsIterator.hasNext()){
            //获取到方法，变量
            Field colmunToFiled=filedsIterator.next();
            String cacheKey=null;//方法名字获取到的注解，也就是数据库表列名
            String cacheValue=null;//实体类T方法名获取到的值
            //获取到对象T的方法名字（之前存入到了CacheMap中）
            if(colmunToFiled.getAnnotation(DbFiled.class)!=null) {
                cacheKey=colmunToFiled.getAnnotation(DbFiled.class).value();
            }else {
                cacheKey=colmunToFiled.getName();
            }

            try {
                if(null==colmunToFiled.get(entity)) {
                    continue;
                }
                cacheValue=colmunToFiled.get(entity).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //把获取到的数据库名字跟
            result.put(cacheKey,cacheValue);

        }


        return result;
    }


    /**
     * 讲map 转换成ContentValues
     * @param map
     * @return
     */
    private ContentValues getContentValues(Map<String, String> map) {
        ContentValues contentValues=new ContentValues();
        Set keys=map.keySet();
        Iterator<String> iterator=keys.iterator();
        while (iterator.hasNext())
        {
            String key=iterator.next();
            String value=map.get(key);
            if(value!=null) {
                contentValues.put(key,value);
            }
        }

        return contentValues;
    }

    @Override
    public Long insert(T entity) {
        Map<String,String> map =getValues(entity);
        ContentValues values=getContentValues(map);
//        ContentValues values = getValuesCV(entity);
        Long result =database.insert(tableName,null,values);
        return result;
    }

    @Override
    public int update(T entity, T where) {
        int reslut=-1;

        Map values=getValues(entity);

        ContentValues contentValues=getContentValues(values);

        /**
         * 将条件对象 转换map
         */
        Map whereClause=getValues(where);

        Condition condition = new Condition(whereClause);

        reslut=database.update(tableName,contentValues,condition.getWhereClause(),condition.getWhereArgs());

        return reslut;
    }

    public int  delete(T where){
        Map map=getValues(where);

        Condition condition=new Condition(map);
        /**
         * id=1 数据
         * id=?      new String[]{String.value(1)}
         */
        int reslut=database.delete(tableName,condition.getWhereClause(),condition.getWhereArgs());
        return reslut;
    }


    public List<T> query(T where){
        return query(where,null,null,null);
    }



  public   List<T> query(T where,String orderBy,Integer startIndex,Integer limit){
      Map map = getValues(where);//对象转换为Map类型

      String limitString=null;//拼接的限制条件
      if(startIndex!=null && limit!=null) {
          limitString=startIndex+" , "+limit;
      }

          Condition condition=new Condition(map);
         Cursor cursor =  database.query(tableName,null,condition.getWhereClause(),
                 condition.getWhereArgs(),null,null,orderBy,limitString);
         List<T> result = getResult(cursor,where);
         cursor.close();//关闭游标
        return result;
  }


    private List<T> getResult(Cursor cursor, T where) {
        ArrayList list=new ArrayList();

        Object item;
        while (cursor.moveToNext()){
            try {
                //通过反射获取类
                item = where.getClass().newInstance();

                //拿到已经初始化反射的HashMap
                /**
                 * key---》数据库列名
                 * value --》Field实体类对应的方法
                 */
                Iterator iterator = cacheMap.entrySet().iterator();
                while (iterator.hasNext()){
                    //拿到HashMap中每个元素
                    Map.Entry entry = (Map.Entry) iterator.next();

                    //拿到列名
                    String colomunName = (String) entry.getKey();

                    //根据列名去查询在游标中对应的位置
                     Integer colmunIndex = cursor.getColumnIndex(colomunName);

                    //拿到列名对应的实体类中反射方法
                    Field field= (Field) entry.getValue();

                    //拿到反射方法对应的参数类型
                    Class type = field.getType();

                    //找到了方法名对应的位置
                    if (colmunIndex!=-1){
                        //根据类型给游标位置查询到对应位置的值，赋值给反射的方法方法，值存入到item中
                        if (type==String.class){
                            //反射方式赋值
                            field.set(item,cursor.getString(colmunIndex));
                        }else if (type==Integer.class){
                            field.set(item,cursor.getInt(colmunIndex));
                        }else if (type==Double.class){
                            field.set(item,cursor.getDouble(colmunIndex));
                        }else if (type==Long.class){
                            field.set(item,cursor.getLong(colmunIndex));
                        }else if (type==Float.class){
                            field.set(item,cursor.getFloat(colmunIndex));
                        }else if (type==byte[].class){
                            field.set(item,cursor.getBlob(colmunIndex));
                        }else if (type==short.class){
                            field.set(item,cursor.getShort(colmunIndex));
                        }else {
                            continue;
                        }
                    }

                }
                list.add(item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }

        return list;
    }


    /**
     * 封装修改语句
     */
    class Condition {
        private String whereClause;
        private  String[] whereArgs;

        public Condition(Map<String ,String> whereClause) {
            ArrayList list=new ArrayList();
            StringBuilder stringBuilder=new StringBuilder();

            stringBuilder.append(" 1=1 ");
            Set keys=whereClause.keySet();
            Iterator iterator=keys.iterator();
            while (iterator.hasNext()) {
                String key= (String) iterator.next();
                String value=whereClause.get(key);

                if (value!=null) {
                    /*
                    拼接条件查询语句
                    1=1 and name =? and password=?
                     */
                    stringBuilder.append(" and "+key+" =?");
                    /**
                     * ？----》value
                     */
                    list.add(value);
                }
            }
            this.whereClause=stringBuilder.toString();
            this.whereArgs= (String[]) list.toArray(new String[list.size()]);

        }

        public String[] getWhereArgs() {
            return whereArgs;
        }

        public String getWhereClause() {
            return whereClause;
        }
    }




    /**
     * 创建表
     * 模板模式，具体的dao去实现创建表
     * @return 创建表的语句
     */
    protected  abstract  String createTable();

}
