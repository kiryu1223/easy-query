package com.easy.query.core.expression.segment.impl;

import com.easy.query.core.basic.jdbc.parameter.ToSQLContext;
import com.easy.query.core.context.QueryRuntimeContext;
import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.expression.segment.scec.context.SQLNativeExpressionContext;
import com.easy.query.core.expression.segment.scec.expression.ParamExpression;
import com.easy.query.core.util.EasyCollectionUtil;
import com.easy.query.core.util.EasySQLExpressionUtil;

import java.text.MessageFormat;

/**
 * create time 2023/6/16 20:55
 * 文件说明
 *
 * @author xuejiaming
 */
public abstract class AbstractSQLNativeSegmentImpl {
    protected final QueryRuntimeContext runtimeContext;
    protected final String sqlSegment;
    protected final SQLNativeExpressionContext sqlConstExpressionContext;

    public AbstractSQLNativeSegmentImpl(QueryRuntimeContext runtimeContext, String sqlSegment, SQLNativeExpressionContext sqlConstExpressionContext) {
        this.runtimeContext = runtimeContext;
        this.sqlSegment = sqlSegment;
        this.sqlConstExpressionContext = sqlConstExpressionContext;
    }

    public TableAvailable getTable() {
        return null;
    }

    public String getPropertyName() {
        return null;
    }

    public String getAlias() {
        return sqlConstExpressionContext.getAlias();
    }


    public String toSQL(ToSQLContext toSQLContext) {

        String resultColumnConst = getResultSQL(toSQLContext);

        String alias = getAlias();
        if (alias != null) {
            return resultColumnConst + " AS " + EasySQLExpressionUtil.getQuoteName(runtimeContext, alias);
        }
        return resultColumnConst;
    }
    private String getResultSQL(ToSQLContext toSQLContext){

        if(EasyCollectionUtil.isNotEmpty(sqlConstExpressionContext.getExpressions())){
            Object[] args = new Object[sqlConstExpressionContext.getExpressions().size()];
            for (int i = 0; i < sqlConstExpressionContext.getExpressions().size(); i++) {
                ParamExpression paramExpression = sqlConstExpressionContext.getExpressions().get(i);
                String arg =EasySQLExpressionUtil.parseParamExpression(runtimeContext,paramExpression,toSQLContext);
                args[i]=arg;
            }
            MessageFormat messageFormat = new MessageFormat(sqlSegment);
            return messageFormat.format(args);
        }
        return sqlSegment;
    }
}
