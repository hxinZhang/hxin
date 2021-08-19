package com.hxin.common.utils.main;

import com.hxin.common.utils.freemark.CreateFileUtils;

/**
 * 生成工具
 * @author hxin
 */
public class Go {
    //库名
    private static final String DATABASENAME = "bookkeeping";
    //表名
    private static final String TABLENAME = "system_config";
    //生成文件存放路径
    private static final String PATH = "C:\\hxin\\template";
    //entity 路径
    private static final String ENTITYPATH = "com.thewe.bookkeepingapi.entity.other";
    //dao 路径
    private static final String DAOPATH = "com.thewe.bookkeepingapi.dao.other";
    //mapper 路径
    private static final String MAPPERPATH = "com.thewe.bookkeepingapi.mapper.other";

    public static void main(String[] args) {
        CreateFileUtils.run(PATH, ENTITYPATH, MAPPERPATH, TABLENAME, DATABASENAME, DAOPATH);
    }
}
