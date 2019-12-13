package com.hxin.common.utils.freemark;

import java.util.Map;

/**
 * 实体类与表属性的映射关系
 * @author hxin
 */
public class MapperModel {

    //表名
    private String databaseName;
    //表名
    private String tableName;
    //实体类名称
    private String modelName;
    //实体类包名
    private String modelPath;
    //dao 类名
    private String daoName;
    //dao 包名
    private String daoPath;
    //mapper类名
    private String mapperName;
    //mapper 包名
    private String mapperPath;
    //mapper 文件名称
    private String mapperFileName;
    //存储文件名称
    private String path;
    //实体类  属性信息    key 属性名称   value  属性类型
    private Map<String, String> fieldMap;
    //属性与字段名 映射关系   key 属性名称   value 字段名称
    private Map<String, String> filedColumnMap;
    //注释与字段名 映射关系   key 注释   value 字段名称
    private Map<String, String> remarkColumnMap;

    /**
     * 构造器
     * @param path      文件存储位置
     * @param modelPath 实体类 包名
     * @param tableName 表名
     */
    public MapperModel(String path, String modelPath, String mapperPath, String tableName, String databaseName, String daoPath) {
        this.path = path;
        this.mapperPath = mapperPath;
        this.modelPath = modelPath;
        this.tableName = tableName;
        this.databaseName = databaseName;
        this.daoPath = daoPath;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getMapperFileName() {
        return mapperFileName;
    }

    public void setMapperFileName(String mapperFileName) {
        this.mapperFileName = mapperFileName;
    }

    public String getMapperPath() {
        return mapperPath;
    }

    public void setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<String, String> getFiledColumnMap() {
        return filedColumnMap;
    }

    public void setFiledColumnMap(Map<String, String> filedColumnMap) {
        this.filedColumnMap = filedColumnMap;
    }

    public Map<String, String> getRemarkColumnMap() {
        return remarkColumnMap;
    }

    public void setRemarkColumnMap(Map<String, String> remarkColumnMap) {
        this.remarkColumnMap = remarkColumnMap;
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String daoName) {
        this.daoName = daoName;
    }

    public String getDaoPath() {
        return daoPath;
    }

    public void setDaoPath(String daoPath) {
        this.daoPath = daoPath;
    }
}
