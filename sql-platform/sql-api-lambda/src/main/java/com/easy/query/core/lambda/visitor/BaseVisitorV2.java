package com.easy.query.core.lambda.visitor;

import com.easy.query.api.lambda.sqlext.SqlFunctions;
import com.easy.query.core.expression.builder.Filter;
import com.easy.query.core.expression.parser.core.EntitySQLTableOwner;
import com.easy.query.core.func.SQLFunction;
import com.easy.query.core.lambda.visitor.context.*;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static com.easy.query.core.lambda.util.ExpressionUtil.hasParameter;
import static com.easy.query.core.lambda.util.ExpressionUtil.isVoid;
import static com.easy.query.core.lambda.util.SqlUtil.fieldName;

public abstract class BaseVisitorV2 extends Visitor
{
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

    protected void compareValueAndValue(Filter filter, Object value1, Object value2, OperatorType operatorType)
    {
        String op;
        if (operatorType == OperatorType.EQ)
        {
            op = "=";
        }
        else if (operatorType == OperatorType.NE)
        {
            op = "<>";
        }
        else
        {
            op = operatorType.getOperator();
        }

        filter.sqlNativeSegment("{0} " + op + " {1}", s ->
        {
            s.value(value1);
            s.value(value2);
        });
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

    protected void compareFuncAndProperty(Filter filter, EntitySQLTableOwner<?> table1, SQLFunction function, EntitySQLTableOwner<?> table2, String Property, OperatorType operatorType)
    {
        switch (operatorType)
        {
            case EQ:
                filter.eq(table1.getTable(), function, table2.getTable(), Property);
                break;
            case NE:
                filter.ne(table1.getTable(), function, table2.getTable(), Property);
                break;
            case LT:
                filter.lt(table1.getTable(), function, table2.getTable(), Property);
                break;
            case GT:
                filter.gt(table1.getTable(), function, table2.getTable(), Property);
                break;
            case LE:
                filter.le(table1.getTable(), function, table2.getTable(), Property);
                break;
            case GE:
                filter.ge(table1.getTable(), function, table2.getTable(), Property);
                break;
        }
    }

    protected void compareFuncAndFunc(Filter filter, EntitySQLTableOwner<?> table1, SQLFunction function1, EntitySQLTableOwner<?> table2, SQLFunction function2, OperatorType operatorType)
    {
        switch (operatorType)
        {
            case EQ:
                filter.eq(table1.getTable(), function1, table2.getTable(), function2);
                break;
            case NE:
                filter.ne(table1.getTable(), function1, table2.getTable(), function2);
                break;
            case LT:
                filter.lt(table1.getTable(), function1, table2.getTable(), function2);
                break;
            case GT:
                filter.gt(table1.getTable(), function1, table2.getTable(), function2);
                break;
            case LE:
                filter.le(table1.getTable(), function1, table2.getTable(), function2);
                break;
            case GE:
                filter.ge(table1.getTable(), function1, table2.getTable(), function2);
                break;
        }
    }

    protected OperatorType revOp(OperatorType operatorType)
    {
        switch (operatorType)
        {
            case EQ:
            case NE:
                return operatorType;
            case GE:
                return OperatorType.LE;
            case LE:
                return OperatorType.GE;
            case GT:
                return OperatorType.LT;
            case LT:
                return OperatorType.GT;
            default:
                throw new RuntimeException();
        }
    }
}
