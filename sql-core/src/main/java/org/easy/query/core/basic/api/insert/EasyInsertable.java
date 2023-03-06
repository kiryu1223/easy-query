package org.easy.query.core.basic.api.insert;

import org.easy.query.core.query.SqlEntityInsertExpression;

/**
 * @FileName: EasyInsertable.java
 * @Description: 文件说明
 * @Date: 2023/3/6 08:45
 * @Created by xuejiaming
 */
public class EasyInsertable<T> extends AbstractInsertable<T> {
    public EasyInsertable(Class<T> clazz, SqlEntityInsertExpression sqlEntityInsertExpression) {
        super(clazz, sqlEntityInsertExpression);
    }

    @Override
    public String toSql() {
        return sqlEntityInsertExpression.toSql();
    }
}
