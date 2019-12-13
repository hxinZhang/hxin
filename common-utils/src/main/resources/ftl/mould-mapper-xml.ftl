<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace='${mapperPath}.${mapperName}'>

    <#assign resultMap='${modelName}Map' />
    <!-- 关系映射 -->
    <resultMap id="${resultMap}" type="${modelPath}.${modelName}" >
        <#if filedColumnMap??>
            <#list filedColumnMap?keys as key>
                <result property="${key}" column="${filedColumnMap[key]}" />
            </#list>
        </#if>
    </resultMap>

    <!-- 分页 -->
    <select id='queryByPage' resultMap='${resultMap}' parameterType='${modelPath}.${modelName}'>
        select
            <include refid='sql_columns' />
        from ${tableName}
        <include refid='sql_where' />
        order by `id` desc
        limit <#noparse>#{</#noparse>begin<#noparse>}</#noparse>, <#noparse>#{</#noparse>pageSize<#noparse>}</#noparse>
    </select>

    <!-- 分页统计 -->
    <select id='countByPage' resultType='java.lang.Integer'>
        select
            count(id)
        from ${tableName}
        <include refid='sql_where' />
    </select>

    <!-- 条件查询 -->
    <select id='get' resultMap='${resultMap}' parameterType='${modelPath}.${modelName}' >
        select <include refid='sql_columns' /> from ${tableName}  <include refid='sql_where' />
    </select>

    <!-- 新增数据 -->
    <insert id='insert' parameterType='${modelPath}.${modelName}' keyProperty="id" useGeneratedKeys="true">
    INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
        <#if filedColumnMap??>
        <#if fieldMap??>
            <#list filedColumnMap?keys as key>
                <#if fieldMap[key]?? && fieldMap[key] == "String">
                <if test='${key}!=null and !"".equals(${key})'> `${filedColumnMap[key]}` , </if>
                <#else>
                <#if key == "createDate">
                `CREATE_DATE` ,
                <#else>
                <if test='${key}!=null'> `${filedColumnMap[key]}` , </if>
                </#if>
                </#if>
            </#list>
        </#if>
        </#if>
        </trim>
        VALUES
        <trim prefix="(" suffix=")" suffixOverrides="," >
        <#if filedColumnMap??>
        <#if fieldMap??>
            <#list filedColumnMap?keys as key>
                <#if fieldMap[key]?? && fieldMap[key] == "String">
                <if test='${key}!=null and !"".equals(${key})'> ${"#"}{${key}} , </if>
                <#else>
                <#if key == "createDate">
                now() ,
                <#else>
                <if test='${key}!=null'> `${filedColumnMap[key]}` , </if>
                </#if>
                </#if>
            </#list>
        </#if>
        </#if>
        </trim>
    </insert>

    <!-- 修改数据 -->
    <update id='update' parameterType='${modelPath}.${modelName}' keyProperty="id" useGeneratedKeys="true">
    UPDATE ${tableName} SET
        <trim suffixOverrides=",">
        <#if filedColumnMap??>
        <#if fieldMap??>
            <#list filedColumnMap?keys as key>
                <#if fieldMap[key]?? && fieldMap[key] == "String">
                <if test='${key}!=null and !"".equals(${key})'> `${filedColumnMap[key]}` = ${"#"}{${key}} , </if>
                <#else>
                <#if key == "modifyDate">
                `${filedColumnMap[key]}` = now() ,
                <#else>
                <if test='${key}!=null'> `${filedColumnMap[key]}` = ${"#"}{${key}} , </if>
                </#if>
                </#if>
            </#list>
        </#if>
        </#if>
        </trim>
        <where>
            `id`=${"#"}{id}
        </where>
    </update>

    <!-- 删除数据 -->
    <delete id='del' parameterType='java.lang.Long'>
        delete from ${tableName} where `id` = ${"#"}{id}
    </delete>

    <!-- 批量删除数据 -->
    <delete id='batchDel' parameterType='java.util.ArrayList'>
        delete from ${tableName} where `id` in
        <foreach collection='list' item='id' separator=',' open='(' close=')'>
            ${"#"}{id}
        </foreach>
    </delete>

    <!-- 表字段 -->
    <sql id='sql_columns'>
        <#if filedColumnMap??>
        <#list filedColumnMap?keys as key>
        `${filedColumnMap[key]}`<#if key_has_next>,</#if>
        </#list>
        </#if>
    </sql>

    <!-- 查询条件 -->
    <sql id='sql_where'>
        <where>
            <trim suffixOverrides='and'>
            <#if filedColumnMap??>
            <#if fieldMap??>
                <#list filedColumnMap?keys as key>
                    <#if fieldMap[key]?? && fieldMap[key] == "String">
                <if test='${key}!=null and !"".equals(${key})'> `${filedColumnMap[key]}` = ${"#"}{${key}} and </if>
                    <#else>
                <if test='${key}!=null'> `${filedColumnMap[key]}` = ${"#"}{${key}} and </if>
                   </#if>
                </#list>
            </#if>
            </#if>
            </trim>
        </where>
    </sql>

    <!--自定义SQL-->

</mapper>