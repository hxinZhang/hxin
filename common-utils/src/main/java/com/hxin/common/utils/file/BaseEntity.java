package com.hxin.common.utils.file;

import lombok.Data;
import lombok.ToString;

/**
 * 公共实体
 * @author hxin
 */
@Data
@ToString
public class BaseEntity<T> {

    //开始位置
    private Integer begin;
    //每页显示行数
    private Integer pageSize;

}
