package com.easy.query.core.basic.api.abstraction;

/**
 * @FileName: SqlExecuteRows.java
 * @Description: 文件说明
 * @Date: 2023/3/17 17:34
 * @Created by xuejiaming
 */
public interface SqlExecuteRows {
    /**
     * 返回受影响行数
     * @return
     */
    long executeRows();
}