package com.love.jax.database.db;

import java.util.List;

/**
 * com.love.jax.database.db
 * Created by jax on 2019-11-26 15:20
 * TODO:
 */
public interface IBaseDao<T> {
    /**
     * 插入数据
     * @param entity
     * @return
     */
    Long insert(T entity);

    /**
     *更新数据
     * @param entity
     * @param where
     * @return
     */
    int  update(T entity,T where);



    /**
     * 删除数据
     * @param where
     * @return
     */
    int  delete(T where);

    /**
     * 查询数据
     */
    List<T> query(T where);


    /**
     * 查询数据
     * @param where 查询条件（具体的对象）
     * @param orderBy 排序行
     * @param startIndex 查询的开始位置
     * @param limit 限制查询返回的行数
     * @return 查询到的数据列表
     */
    List<T> query(T where,String orderBy,Integer startIndex,Integer limit);


    List<T> query(String sql);

}
