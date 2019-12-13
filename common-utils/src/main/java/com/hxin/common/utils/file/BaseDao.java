package com.hxin.common.utils.file;

import java.sql.SQLException;
import java.util.List;

/**
 * 公共dao
 * @param <T>
 * @author hxin
 */
public interface BaseDao<T> {

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
     * 通过id查询实体类信息
     * @param id
     * @return
     * @throws SQLException
     */
    T queryById(Integer id) throws SQLException;

    /**
     * 通过实体查询实体类信息
     * @param t
     * @return
     * @throws SQLException
     */
    T queryByModel(T t) throws SQLException;

    /**
     * 新增
     * @param t
     * @return
     * @throws SQLException
     */
    Integer insert(T t) throws SQLException;

    /**
     * 修改
     * @param t
     * @return
     * @throws SQLException
     */
    Integer modify(T t) throws SQLException;

    /**
     * 删除
     * @param ids
     * @return
     * @throws SQLException
     */
    Integer delete(List ids) throws SQLException;

}
