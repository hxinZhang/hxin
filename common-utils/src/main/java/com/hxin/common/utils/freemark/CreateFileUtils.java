package com.hxin.common.utils.freemark;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建文件
 * @author hxin
 */
public class CreateFileUtils {

    private static MapperModel model;

    private static ApplicationContext context = new ClassPathXmlApplicationContext(
            new String[]{"classpath:spring-freemarker.xml", "classpath:spring-db.xml"});

    /**
     * 构造器
     * @param path       文件存储位置
     * @param modelPath  实体类 包名
     * @param mapperPath dao类  包名
     * @param tableName  表名
     */
    public static void run(String path, String modelPath, String mapperPath, String tableName, String databaseName, String daoPath) {
        model = new MapperModel(path, modelPath, mapperPath, tableName, databaseName, daoPath);
        ParseFreemarker parseFreemarker = (ParseFreemarker) context.getBean("parseFreemarker");
        loadModel();
        System.out.println(model.getFieldMap());
        createJavaFile(parseFreemarker, model.getModelName(), FreemarkerFileEnum.MODAL, "entity");
        createJavaFile(parseFreemarker,model.getDaoName(),FreemarkerFileEnum.DAO, "dao");
        createJavaFile(parseFreemarker,model.getDaoName()+"Impl",FreemarkerFileEnum.DAOIMPL, "daoImpl");
        createJavaFile(parseFreemarker, model.getMapperName(), FreemarkerFileEnum.MAPPER, "mapper");
        createMapperFile(parseFreemarker);
    }

    private static void loadModel() {
        String keyword = hump(model.getTableName()).substring(0, 1).toUpperCase() + hump(model.getTableName()).substring(1);
        model.setModelName(keyword);
        model.setMapperName(keyword + "Mapper");
        model.setMapperFileName(keyword + "Mapper.xml");
        model.setDaoName(keyword+"Dao");
        //连接数据库  加载列表信息
        setColumnInfo();
    }

    //===================================创建java文件====================================
    //创建实体类
    /**
     * 创建java文件
     *
     * @param parseFreemarker
     * @param modelName       类名称
     * @param fileEnum        模板文件
     */
    public static void createJavaFile(ParseFreemarker parseFreemarker, String modelName, FreemarkerFileEnum fileEnum, String notice) {
        PrintWriter writer = getWriter(modelName + ".java");
        if (writer == null) {
            System.out.println("加载IO流失败");
        }
        String text = parseFreemarker.parseFtlFile(fileEnum.getUrl(), model);
        //结束标志
        writer.println(text);
        writer.flush();
        System.out.println(notice+"文件已经生成,路径:" + File.separator + model.getPath() + modelName + ".java");
    }

    //===================================创建mapper配置文件  开始====================================
    //创建实体类
    public static void createMapperFile(ParseFreemarker parseFreemarker) {
        PrintWriter writer = getWriter(model.getMapperFileName());
        if (writer == null) {
            System.out.println("加载IO流失败");
        }
        String text = parseFreemarker.parseFtlFile(FreemarkerFileEnum.MAPPERXML.getUrl(), model);
        //结束标志
        writer.println(text);
        writer.flush();
        System.out.println("Model文件已经生成,路径:" + model.getPath() + File.separator + model.getMapperFileName());
    }

    //===================================获取列信息   ====================================
    /**
     * 获取列信息
     * @return
     */
    private static void setColumnInfo() {
        Connection connection = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
        String sql1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        String sql2 = null;
        try {
            //实体类  属性信息    key 属性名称   value  属性类型
            Map<String, String> fieldMap = new HashMap<String, String>();
            //属性与字段名 映射关系   key 属性名称   value 字段名称
            Map<String, String> filedColumnMap = new HashMap<String, String>();
            //注释与字段名 映射关系   key 注释   value 字段名称
            Map<String, String> remarkColumnMap = new HashMap<String, String>();
            //过滤map
            Map<String, String> tempColumnMap = new HashMap<String, String>();
            DruidDataSource druidDataSource = (DruidDataSource) context.getBean("dataSource");
            connection = druidDataSource.getConnection();

            sql1 = "select * from :tableName limit 1".replace(":tableName", model.getTableName());
            ps1 = connection.prepareStatement(sql1);
            rs1 = ps1.executeQuery();

            ResultSetMetaData rsmd = rs1.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String columnName = rsmd.getColumnName(i);
                filedColumnMap.put(hump(columnName), columnName);
                tempColumnMap.put(columnName, hump(columnName));
                int columnType = rsmd.getColumnType(i);
                //整数
                if (columnType == Types.NUMERIC || columnType == Types.INTEGER) {
                    fieldMap.put(hump(columnName), "Integer");
                } else if (columnType == Types.BIGINT) {
                    fieldMap.put(hump(columnName), "Long");
                } else if (columnType == Types.DECIMAL) {
                    fieldMap.put(hump(columnName), "BigDecimal");
                } else if (columnType == Types.VARCHAR ||
                        columnType == Types.CHAR) {//字符串
                    fieldMap.put(hump(columnName), "String");
                } else if (columnType == Types.DATE ||//日期
                        columnType == Types.TIME) {
                    fieldMap.put(hump(columnName), "Date");
                } else if (columnType == Types.TIMESTAMP) {
                    fieldMap.put(hump(columnName), "Date");
                } else if (columnType == Types.DOUBLE) {
                    fieldMap.put(hump(columnName), "Double");
                } else {//其它
                    fieldMap.put(hump(columnName), "String");
                }
            }
            model.setFieldMap(fieldMap);
            model.setFiledColumnMap(filedColumnMap);

            //用于查询注释
            sql2 = "select `COLUMN_NAME`,`column_comment`,`column_type`,`column_key` from information_schema.columns where table_schema = ':databaseName' and table_name = ':tableName'".
                    replace(":databaseName", model.getDatabaseName()).replace(":tableName", model.getTableName());
            ps2 = connection.prepareStatement(sql2);
            rs2 = ps2.executeQuery();
            while(rs2.next()){
                String columnName = rs2.getString("COLUMN_NAME");
                String columnComment = rs2.getString("column_comment");
                remarkColumnMap.put(tempColumnMap.get(columnName), columnComment);
            }
            model.setRemarkColumnMap(remarkColumnMap);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs1.close();
                ps1.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //===================================转驼峰方法   ====================================
    //将  数据字段 转换成  驼峰式
    private static String hump(String column) {
        StringBuffer javaName = new StringBuffer();
        column = column.toLowerCase();
        String[] arys = column.split("_");
        javaName.append(arys[0]);
        for (int i = 1; i < arys.length; i++) {
            javaName.append(arys[i].substring(0, 1).toUpperCase() + arys[i].substring(1));
        }
        return javaName.toString();
    }

    //===================================创建字符流   ====================================
    //获取IO流
    private static PrintWriter getWriter(String fileName) {
        OutputStream out = null;
        BufferedOutputStream bos = null;
        OutputStreamWriter w = null;
        PrintWriter pw = null;
        try {
            File file = new File(model.getPath() + File.separator + fileName);
            if (file.exists()) {
                file.delete();
            }
            out = new FileOutputStream(file, true);
            bos = new BufferedOutputStream(out);
            w = new OutputStreamWriter(bos, "UTF-8");
            pw = new PrintWriter(w);
            return pw;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
