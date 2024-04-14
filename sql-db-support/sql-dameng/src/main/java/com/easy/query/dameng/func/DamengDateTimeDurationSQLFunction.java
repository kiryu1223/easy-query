package com.easy.query.dameng.func;

import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.func.column.ColumnExpression;
import com.easy.query.core.func.def.AbstractExpressionSQLFunction;
import com.easy.query.core.func.def.enums.DateTimeDurationEnum;

import java.util.List;

/**
 * create time 2023/12/21 11:58
 * 文件说明
 *
 * @author xuejiaming
 */
public class DamengDateTimeDurationSQLFunction extends AbstractExpressionSQLFunction {
    private final List<ColumnExpression> columnExpressions;
    private final DateTimeDurationEnum durationEnum;

    public DamengDateTimeDurationSQLFunction(List<ColumnExpression> columnExpressions, DateTimeDurationEnum durationEnum) {

        this.columnExpressions = columnExpressions;
        this.durationEnum = durationEnum;
    }

    @Override
    public String sqlSegment(TableAvailable defaultTable) {
        if(columnExpressions.size()!=2){
            throw new IllegalArgumentException("date time duration sql arguments != 2");
        }
        switch (durationEnum){
            case Days:return "EXTRACT(DAY FROM NUMTODSINTERVAL(({0}+0)-({1}+0),'DAY'))";
            case Hours:return "EXTRACT(HOUR FROM NUMTODSINTERVAL(({0}+0)-({1}+0),'DAY'))";
            case Minutes:return "EXTRACT(MINUTE FROM NUMTODSINTERVAL(({0}+0)-({1}+0),'DAY'))";
            case Seconds:return "FLOOR(EXTRACT(SECOND FROM NUMTODSINTERVAL(({0}+0)-({1}+0),'DAY')))";
        }
        throw new UnsupportedOperationException("不支持当前函数DamengDateTimeDurationSQLFunction:"+ durationEnum);
    }

    @Override
    public int paramMarks() {
        return columnExpressions.size();
    }

    @Override
    protected List<ColumnExpression> getColumnExpressions() {
        return columnExpressions;
    }

}
