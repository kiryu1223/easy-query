package com.easy.query.core.basic.api.delete;

import com.easy.query.core.basic.api.internal.LogicDeletable;
import com.easy.query.core.basic.api.internal.SQLExecuteExpectRows;
import com.easy.query.core.basic.api.internal.TableReNameable;
import com.easy.query.core.basic.jdbc.parameter.ToSQLContext;

/**
 * @author xuejiaming
 * @FileName: Deletable.java
 * @Description: 文件说明
 * @Date: 2023/2/28 12:19
 */
public interface Deletable<T, TChain> extends SQLExecuteExpectRows, LogicDeletable<TChain>, TableReNameable<TChain> {
    /**
     * 语句转成sql
     *
     * @return
     */
    default String toSQL() {
        return toSQL(null);
    }

    String toSQL(ToSQLContext toSQLContext);

    /**
     * 是否允许删除命令
     *
     * @param allow
     * @return
     */
    TChain allowDeleteStatement(boolean allow);
}
