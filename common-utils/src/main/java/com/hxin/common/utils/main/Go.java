package com.hxin.common.utils.main;

import com.hxin.common.utils.freemark.CreateFileUtils;

/**
 * 生成工具
 * @author hxin
 */
public class Go {

    //库名
    private static final String DATABASENAME = "steps_interest";
    //表名
    private static final String TABLENAME = "admin_role_rel";
    //生成文件存放路径
    private static final String PATH = "E:\\template\\";
    //entity 路径
    private static final String ENTITYPATH = "com.steps.interest.background.entity.admin";
    //dao 路径
    private static final String DAOPATH = "com.steps.interest.background.dao.admin";
    //mapper 路径
    private static final String MAPPERPATH = "com.steps.interest.background.mapper.admin";

    public static void main(String[] args) {
        CreateFileUtils.run(PATH, ENTITYPATH, MAPPERPATH, TABLENAME, DATABASENAME, DAOPATH);
    }

}
