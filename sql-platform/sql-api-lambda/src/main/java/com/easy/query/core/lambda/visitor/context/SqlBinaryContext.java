package com.easy.query.core.lambda.visitor.context;

import com.easy.query.core.expression.builder.Filter;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.func.SQLFunction;
import io.github.kiryu1223.expressionTree.expressions.OperatorType;

public class SqlBinaryContext extends SqlContext
{
    private final OperatorType operatorType;
    private final SqlContext left;
    private final SqlContext right;

    public SqlBinaryContext(OperatorType operatorType, SqlContext left, SqlContext right)
    {
        this.operatorType = operatorType;
        this.left = left;
        this.right = right;
    }

    public OperatorType getOperatorType()
    {
        return operatorType;
    }

    public SqlContext getLeft()
    {
        return left;
    }

    public SqlContext getRight()
    {
        return right;
    }

    @Override
    public void revWhere(WherePredicate<?> wherePredicate)
    {
        Filter filter = wherePredicate.getFilter();
        SQLFunc fx = filter.getRuntimeContext().fx();
        if (operatorType == OperatorType.AND || operatorType == OperatorType.OR)
        {
            left.revWhere(wherePredicate);
            if (operatorType == OperatorType.AND) wherePredicate.and();
            else wherePredicate.or();
            right.revWhere(wherePredicate);
        }
        else if (operatorType == OperatorType.EQ || operatorType == OperatorType.NE
                || operatorType == OperatorType.GE || operatorType == OperatorType.GT
                || operatorType == OperatorType.LT || operatorType == OperatorType.LE)
        {
            if (left instanceof SqlPropertyContext) // 左侧是字段
            {
                SqlPropertyContext leftProperty = (SqlPropertyContext) left;
                if (right instanceof SqlValueContext)// 右侧是值
                {
                    SqlValueContext sqlValueContext = (SqlValueContext) right;
                    comparePropertyAndValue(filter, leftProperty.getTableOwner(), leftProperty.getProperty(), sqlValueContext.getValue(), operatorType);
                }
                else if (right instanceof SqlPropertyContext)// 右侧是字段
                {
                    SqlPropertyContext rightProperty = (SqlPropertyContext) right;
                    comparePropertyAndProperty(filter, leftProperty.getTableOwner(), leftProperty.getProperty(), rightProperty.getTableOwner(), rightProperty.getProperty(), operatorType);
                }
                else if (right instanceof SqlFuncContext)// 右侧是函数
                {
                    SqlFuncContext sqlFuncContext = (SqlFuncContext) right;
                    SQLFunction function = sqlFuncContext.getFunction(fx);
                    comparePropertyAndFunc(filter, leftProperty.getTableOwner(), leftProperty.getProperty(), function, operatorType);
                }
            }
            else if (left instanceof SqlValueContext)
            {
                SqlValueContext leftValue = (SqlValueContext) left;
                if (right instanceof SqlValueContext)
                {
                    SqlValueContext rightValue = (SqlValueContext) right;
                    compareValueAndValue(wherePredicate, leftValue.getValue(), rightValue.getValue(), operatorType);
                }
                else if (right instanceof SqlPropertyContext)
                {
                    SqlPropertyContext rightProperty = (SqlPropertyContext) right;
                    comparePropertyAndValue(filter, rightProperty.getTableOwner(), rightProperty.getProperty(), leftValue.getValue(), revOp(operatorType));
                }
                else if (right instanceof SqlFuncContext)
                {
                    SqlFuncContext sqlFuncContext = (SqlFuncContext) right;
                    SQLFunction function = sqlFuncContext.getFunction(fx);
                    compareFuncAndValue(filter, wherePredicate, function, leftValue.getValue(), revOp(operatorType));
                }
            }
            else if (left instanceof SqlFuncContext)
            {
                SqlFuncContext leftFunc = (SqlFuncContext) left;
                if (right instanceof SqlValueContext)
                {
                    SqlValueContext rightValue = (SqlValueContext) right;
                    compareFuncAndValue(filter, wherePredicate, leftFunc.getFunction(fx), rightValue.getValue(), operatorType);
                }
                else if (right instanceof SqlPropertyContext)
                {
                    SqlPropertyContext rightProperty = (SqlPropertyContext) right;
                    compareFuncAndProperty(filter, wherePredicate, leftFunc.getFunction(fx), rightProperty.getTableOwner(), rightProperty.getProperty(), operatorType);
                }
                else if (right instanceof SqlFuncContext)
                {
                    SqlFuncContext sqlFuncContext = (SqlFuncContext) right;
                    SQLFunction function = sqlFuncContext.getFunction(fx);
                    compareFuncAndFunc(filter, wherePredicate, function, wherePredicate, function, operatorType);
                }
            }
            else
            {
                throw new RuntimeException();
            }
        }
    }

//    private void addParen(WherePredicate<?> wherePredicate, SqlContext left)
//    {
//        switch (operatorType)
//        {
//            case AND:
//                if (left.isHasParens())
//                {
//                    wherePredicate.and(() ->
//                    {
//                        left.revWhere(wherePredicate);
//                    });
//                }
//                else
//                {
//                    wherePredicate.and();
//                    left.revWhere(wherePredicate);
//                }
//                break;
//            case OR:
//                if (left.isHasParens())
//                {
//                    wherePredicate.or(() ->
//                    {
//                        left.revWhere(wherePredicate);
//                    });
//                }
//                else
//                {
//                    wherePredicate.or();
//                    left.revWhere(wherePredicate);
//                }
//                break;
//        }
//    }

    private OperatorType revOp(OperatorType operatorType)
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
