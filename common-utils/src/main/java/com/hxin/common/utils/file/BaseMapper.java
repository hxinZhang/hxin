package com.hxin.common.utils.file;

import java.sql.SQLException;
import java.util.List;

/**
 * 常用的mapper
 * @author hxin
 */
public interface BaseMapper<T> {

    /**
     * 分页 查询
     * @param t
     * @return
     * @throws SQLException
     */
    List<T> queryByPage(T t) throws SQLException;

    /**
     * 分页 统计
     * @param t
     * @return
     * @throws SQLException
     */
    Integer countByPage(T t) throws SQLException;

    /**
     * 条件过滤查询
     * @param t
     * @return
     * @throws SQLException
     */
    T get(T t) throws SQLException;

    /**
     * 新增数据
     * @param t
     * @return
     * @throws SQLException
     */
    Integer insert(T t) throws SQLException;

    /**
     * 修改数据
     * @param t
     * @return
     * @throws SQLException
     */
    Integer update(T t) throws SQLException;

    /**
     * 删除数据
     * @param id
     * @return
     * @throws SQLException
     */
    Integer del(Long id) throws SQLException;

    /**
     * 批量删除
     * @param ids
     * @return
     * @throws SQLException
     */
    Integer batchDel(List ids) throws SQLException;

}
