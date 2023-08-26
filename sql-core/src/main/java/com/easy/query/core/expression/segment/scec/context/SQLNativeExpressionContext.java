package com.easy.query.core.expression.segment.scec.context;

import com.easy.query.core.basic.api.select.Query;
import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.expression.segment.scec.expression.ParamExpression;

import java.util.List;

/**
 * create time 2023/7/29 21:44
 * 文件说明
 *
 * @author xuejiaming
 */
public interface SQLNativeExpressionContext {
    SQLNativeExpressionContext expression(TableAvailable table, String property);
   <TEntity> SQLNativeExpressionContext expression(Query<TEntity> subQuery);
    SQLNativeExpressionContext value(Object val);

    SQLNativeExpressionContext format(Object formatVal);

     List<ParamExpression> getExpressions();


     String getAlias();

    /**
     * 别名 column_name
     * @param alias
     * @return
     */
    SQLNativeExpressionContext setAlias(String alias);
}
