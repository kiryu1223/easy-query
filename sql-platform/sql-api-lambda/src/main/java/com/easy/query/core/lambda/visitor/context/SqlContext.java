package com.easy.query.core.lambda.visitor.context;


import com.easy.query.core.expression.builder.Filter;
import com.easy.query.core.expression.parser.core.EntitySQLTableOwner;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.func.SQLFunction;
import com.easy.query.core.func.column.ColumnFuncSelector;
import com.easy.query.core.func.def.enums.NumberCalcEnum;
import io.github.kiryu1223.expressionTree.expressions.OperatorType;

import java.util.Collection;

import static com.easy.query.core.lambda.util.ExpressionUtil.isArithmeticOperator;
import static com.easy.query.core.lambda.util.ExpressionUtil.isCompareOperator;

public abstract class SqlContext
{
    public void revWhere(WherePredicate<?> wherePredicate)
    {
    }

    protected void comparePropertyAndValue(Filter filter, EntitySQLTableOwner<?> tableOwner, String Property, Object value, OperatorType operatorType)
    {
        switch (operatorType)
        {
            case EQ:
                filter.eq(tableOwner.getTable(), Property, value);
                break;
            case NE:
                filter.ne(tableOwner.getTable(), Property, value);
                break;
            case LT:
                filter.lt(tableOwner.getTable(), Property, value);
                break;
            case GT:
                filter.gt(tableOwner.getTable(), Property, value);
                break;
            case LE:
                filter.le(tableOwner.getTable(), Property, value);
                break;
            case GE:
                filter.ge(tableOwner.getTable(), Property, value);
                break;
        }
    }

    protected void compareValueAndProperty(Filter filter, Object value, EntitySQLTableOwner<?> tableOwner, String Property, OperatorType operatorType)
    {
        SQLFunc fx = filter.getRuntimeContext().fx();
        switch (operatorType)
        {
            case EQ:
                filter.eq(tableOwner.getTable(), fx.constValue(o -> o.value(value)), tableOwner.getTable(), Property);
                break;
            case NE:
                filter.ne(tableOwner.getTable(), fx.constValue(o -> o.value(value)), tableOwner.getTable(), Property);
                break;
            case LT:
                filter.lt(tableOwner.getTable(), fx.constValue(o -> o.value(value)), tableOwner.getTable(), Property);
                break;
            case GT:
                filter.gt(tableOwner.getTable(), fx.constValue(o -> o.value(value)), tableOwner.getTable(), Property);
                break;
            case LE:
                filter.le(tableOwner.getTable(), fx.constValue(o -> o.value(value)), tableOwner.getTable(), Property);
                break;
            case GE:
                filter.ge(tableOwner.getTable(), fx.constValue(o -> o.value(value)), tableOwner.getTable(), Property);
                break;
        }
    }

    protected void comparePropertyAndProperty(Filter filter, EntitySQLTableOwner<?> table1, String Property1, EntitySQLTableOwner<?> table2, String Property2, OperatorType operatorType)
    {
        switch (operatorType)
        {
            case EQ:
                filter.eq(table1.getTable(), Property1, table2.getTable(), Property2);
                break;
            case NE:
                filter.ne(table1.getTable(), Property1, table2.getTable(), Property2);
                break;
            case LT:
                filter.lt(table1.getTable(), Property1, table2.getTable(), Property2);
                break;
            case GT:
                filter.gt(table1.getTable(), Property1, table2.getTable(), Property2);
                break;
            case LE:
                filter.le(table1.getTable(), Property1, table2.getTable(), Property2);
                break;
            case GE:
                filter.ge(table1.getTable(), Property1, table2.getTable(), Property2);
                break;
        }
    }

    protected void comparePropertyAndFunc(Filter filter, EntitySQLTableOwner<?> table1, String Property1, SQLFunction sqlFunction, OperatorType operatorType)
    {
        switch (operatorType)
        {
            case EQ:
                filter.eq(table1.getTable(), Property1, sqlFunction);
                break;
            case NE:
                filter.ne(table1.getTable(), Property1, sqlFunction);
                break;
            case LT:
                filter.lt(table1.getTable(), Property1, sqlFunction);
                break;
            case GT:
                filter.gt(table1.getTable(), Property1, sqlFunction);
                break;
            case LE:
                filter.le(table1.getTable(), Property1, sqlFunction);
                break;
            case GE:
                filter.ge(table1.getTable(), Property1, sqlFunction);
                break;
        }
    }

    protected void compareValueAndValue(WherePredicate<?> wherePredicate, Object value1, Object value2, OperatorType operatorType)
    {
        SQLFunc fx = wherePredicate.fx();
        SQLFunction sqlFunction = fx.constValue(value1);
        switch (operatorType)
        {
            case EQ:
                wherePredicate.eq(sqlFunction, value2);
                break;
            case NE:
                wherePredicate.ne(sqlFunction, value2);
                break;
            case LT:
                wherePredicate.lt(sqlFunction, value2);
                break;
            case GT:
                wherePredicate.gt(sqlFunction, value2);
                break;
            case LE:
                wherePredicate.le(sqlFunction, value2);
                break;
            case GE:
                wherePredicate.ge(sqlFunction, value2);
                break;
        }
    }

    protected void compareFuncAndValue(Filter filter, EntitySQLTableOwner<?> table1, SQLFunction function, Object value, OperatorType operatorType)
    {
        switch (operatorType)
        {
            case EQ:
                filter.eq(table1.getTable(), function, value);
                break;
            case NE:
                filter.ne(table1.getTable(), function, value);
                break;
            case LT:
                filter.lt(table1.getTable(), function, value);
                break;
            case GT:
                filter.gt(table1.getTable(), function, value);
                break;
            case LE:
                filter.le(table1.getTable(), function, value);
                break;
            case GE:
                filter.ge(table1.getTable(), function, value);
                break;
        }
    }

    protected void compareValueAndFunc(Filter filter, Object value, EntitySQLTableOwner<?> table1, SQLFunction function, OperatorType operatorType)
    {
        SQLFunc fx = filter.getRuntimeContext().fx();
        switch (operatorType)
        {
            case EQ:
                filter.eq(table1.getTable(), fx.constValue(a -> a.value(value)), table1.getTable(), function);
                break;
            case NE:
                filter.ne(table1.getTable(), fx.constValue(a -> a.value(value)), table1.getTable(), function);
                break;
            case LT:
                filter.lt(table1.getTable(), fx.constValue(a -> a.value(value)), table1.getTable(), function);
                break;
            case GT:
                filter.gt(table1.getTable(), fx.constValue(a -> a.value(value)), table1.getTable(), function);
                break;
            case LE:
                filter.le(table1.getTable(), fx.constValue(a -> a.value(value)), table1.getTable(), function);
                break;
            case GE:
                filter.ge(table1.getTable(), fx.constValue(a -> a.value(value)), table1.getTable(), function);
                break;
        }
    }

    protected void compareFuncAndProperty(Filter filter, SQLFunction function, EntitySQLTableOwner<?> table, String Property, OperatorType operatorType)
    {
        switch (operatorType)
        {
            case EQ:
                filter.eq(table.getTable(), function, table.getTable(), Property);
                break;
            case NE:
                filter.ne(table.getTable(), function, table.getTable(), Property);
                break;
            case LT:
                filter.lt(table.getTable(), function, table.getTable(), Property);
                break;
            case GT:
                filter.gt(table.getTable(), function, table.getTable(), Property);
                break;
            case LE:
                filter.le(table.getTable(), function, table.getTable(), Property);
                break;
            case GE:
                filter.ge(table.getTable(), function, table.getTable(), Property);
                break;
        }
    }

    protected void compareFuncAndFunc(Filter filter, EntitySQLTableOwner<?> table, SQLFunction function1, SQLFunction function2, OperatorType operatorType)
    {
        switch (operatorType)
        {
            case EQ:
                filter.eq(table.getTable(), function1, table.getTable(), function2);
                break;
            case NE:
                filter.ne(table.getTable(), function1, table.getTable(), function2);
                break;
            case LT:
                filter.lt(table.getTable(), function1, table.getTable(), function2);
                break;
            case GT:
                filter.gt(table.getTable(), function1, table.getTable(), function2);
                break;
            case LE:
                filter.le(table.getTable(), function1, table.getTable(), function2);
                break;
            case GE:
                filter.ge(table.getTable(), function1, table.getTable(), function2);
                break;
        }
    }

    protected NumberCalcEnum opTrans(OperatorType type)
    {
        switch (type)
        {
            case PLUS:
                return NumberCalcEnum.NUMBER_ADD;
            case MINUS:
                return NumberCalcEnum.NUMBER_SUBTRACT;
            case MUL:
                return NumberCalcEnum.NUMBER_MULTIPLY;
            case DIV:
                return NumberCalcEnum.NUMBER_DEVIDE;
            default:
                throw new RuntimeException("不支持的运算符: " + type.getOperator());
        }
    }

    protected void roundSqlContext(SqlContext context, ColumnFuncSelector s, SQLFunc fx)
    {
        if (context instanceof SqlPropertyContext)
        {
            SqlPropertyContext propertyContext = (SqlPropertyContext) context;
            s.column(propertyContext.getTableOwner(), propertyContext.getProperty());
        }
        else if (context instanceof SqlValueContext)
        {
            SqlValueContext valueContext = (SqlValueContext) context;
            Object value = valueContext.getValue();
            if (value instanceof Collection<?>)
            {
                Collection<?> vs = (Collection<?>) value;
                s.collection(vs);
            }
            else
            {
                s.value(valueContext.getValue());
            }
        }
        else if (context instanceof SqlFuncContext)
        {
            SqlFuncContext funcContext = (SqlFuncContext) context;
            SQLFunction function = funcContext.getFunction(fx);
            s.sqlFunc(function);
        }
        else if (context instanceof SqlBinaryContext)
        {
            SqlBinaryContext sqlBinaryContext = (SqlBinaryContext) context;
            OperatorType operatorType = sqlBinaryContext.getOperatorType();
            SqlContext left = sqlBinaryContext.getLeft();
            SqlContext right = sqlBinaryContext.getRight();
            if (isArithmeticOperator(operatorType))
            {
                SQLFunction sqlFunction = fx.numberCalc(a ->
                        {
                            roundSqlContext(left, a, fx);
                            roundSqlContext(right, a, fx);
                        },
                        opTrans(operatorType)
                );
                s.sqlFunc(sqlFunction);
            }
//            else if (isCompareOperator(operatorType))
//            {
//
//            }
            else
            {
                throw new RuntimeException(sqlBinaryContext + " " + operatorType.getOperator());
            }
        }
        else if (context instanceof SqlParensContext)
        {
            SqlParensContext sqlParensContext = (SqlParensContext) context;
            s.sqlFunc(fx.constValue(c -> roundSqlContext(sqlParensContext.getContext(), c, fx)));
        }
        else
        {
            throw new RuntimeException(context.toString());
        }
    }
}
