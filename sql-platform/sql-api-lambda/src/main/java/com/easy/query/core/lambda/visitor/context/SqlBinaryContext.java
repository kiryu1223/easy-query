package com.easy.query.core.lambda.visitor.context;

import com.easy.query.core.expression.builder.Filter;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.func.SQLFunction;
import io.github.kiryu1223.expressionTree.expressions.OperatorType;

import static com.easy.query.core.lambda.util.ExpressionUtil.isAndorOr;
import static com.easy.query.core.lambda.util.ExpressionUtil.isCompareOperator;

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
        if (isAndorOr(operatorType))
        {
            left.revWhere(wherePredicate);
            if (operatorType == OperatorType.AND)
            {
                wherePredicate.and();
            }
            else
            {
                wherePredicate.or();
            }
            right.revWhere(wherePredicate);
        }
        else if (isCompareOperator(operatorType))
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
                else if (right instanceof SqlBinaryContext)
                {
                    SqlBinaryContext binaryContext = (SqlBinaryContext) right;
                    SQLFunction binaryRight = fx.numberCalc(a ->
                    {
                        roundSqlContext(binaryContext.getLeft(), a, fx);
                        roundSqlContext(binaryContext.getRight(), a, fx);
                    }, opTrans(binaryContext.getOperatorType()));
                    comparePropertyAndFunc(filter, leftProperty.getTableOwner(), leftProperty.getProperty(), binaryRight, operatorType);
                }
                else if (right instanceof SqlParensContext)
                {
                    SQLFunction parens = fx.constValue(c -> roundSqlContext(((SqlParensContext) right).getContext(), c, fx));
                    comparePropertyAndValue(filter, leftProperty.getTableOwner(), leftProperty.getProperty(), parens, operatorType);
                }
                else
                {
                    throw new RuntimeException();
                }
            }
            else if (left instanceof SqlValueContext)// 左侧是值
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
                    compareValueAndProperty(filter, leftValue.getValue(), rightProperty.getTableOwner(), rightProperty.getProperty(), operatorType);
                }
                else if (right instanceof SqlFuncContext)
                {
                    SqlFuncContext sqlFuncContext = (SqlFuncContext) right;
                    SQLFunction function = sqlFuncContext.getFunction(fx);
                    compareValueAndFunc(filter, leftValue.getValue(), wherePredicate, function, operatorType);
                }
                else if (right instanceof SqlBinaryContext)
                {
                    SqlBinaryContext binaryContext = (SqlBinaryContext) right;
                    SQLFunction binaryRight = fx.numberCalc(a ->
                    {
                        roundSqlContext(binaryContext.getLeft(), a, fx);
                        roundSqlContext(binaryContext.getRight(), a, fx);
                    }, opTrans(binaryContext.getOperatorType()));
                    compareValueAndFunc(filter, leftValue.getValue(), wherePredicate, binaryRight, operatorType);
                }
                else if (right instanceof SqlParensContext)
                {
                    SQLFunction parens = fx.constValue(c -> roundSqlContext(((SqlParensContext) right).getContext(), c, fx));
                    compareValueAndFunc(filter, leftValue.getValue(), wherePredicate, parens, operatorType);
                }
                else
                {
                    throw new RuntimeException();
                }
            }
            else if (left instanceof SqlFuncContext)// 左侧是函数
            {
                SqlFuncContext leftFunc = (SqlFuncContext) left;
                SQLFunction function = leftFunc.getFunction(fx);
                if (right instanceof SqlValueContext)
                {
                    SqlValueContext rightValue = (SqlValueContext) right;
                    compareFuncAndValue(filter, wherePredicate, leftFunc.getFunction(fx), rightValue.getValue(), operatorType);
                }
                else if (right instanceof SqlPropertyContext)
                {
                    SqlPropertyContext rightProperty = (SqlPropertyContext) right;
                    compareFuncAndProperty(filter, leftFunc.getFunction(fx), rightProperty.getTableOwner(), rightProperty.getProperty(), operatorType);
                }
                else if (right instanceof SqlFuncContext)
                {
                    SqlFuncContext sqlFuncContext = (SqlFuncContext) right;
                    SQLFunction functionRight = sqlFuncContext.getFunction(fx);
                    compareFuncAndFunc(filter, wherePredicate, function, functionRight, operatorType);
                }
                else if (right instanceof SqlBinaryContext)
                {
                    SqlBinaryContext binaryContext = (SqlBinaryContext) right;
                    SQLFunction binaryRight = fx.numberCalc(a ->
                    {
                        roundSqlContext(binaryContext.getLeft(), a, fx);
                        roundSqlContext(binaryContext.getRight(), a, fx);
                    }, opTrans(binaryContext.getOperatorType()));
                    compareFuncAndFunc(filter, wherePredicate, function, binaryRight, operatorType);
                }
                else if (right instanceof SqlParensContext)
                {
                    SQLFunction parens = fx.constValue(c -> roundSqlContext(((SqlParensContext) right).getContext(), c, fx));
                    compareFuncAndFunc(filter,wherePredicate, function, parens, operatorType);
                }
                else
                {
                    throw new RuntimeException();
                }
            }
            else if (left instanceof SqlBinaryContext)// 左侧是二元运算
            {
                SqlBinaryContext binaryContext = (SqlBinaryContext) left;
                SQLFunction binary = fx.numberCalc(a ->
                {
                    roundSqlContext(binaryContext.getLeft(), a, fx);
                    roundSqlContext(binaryContext.getRight(), a, fx);
                }, opTrans(binaryContext.getOperatorType()));
                if (right instanceof SqlPropertyContext)
                {
                    SqlPropertyContext propertyContext = (SqlPropertyContext) right;
                    compareFuncAndProperty(filter, binary, propertyContext.getTableOwner(), propertyContext.getProperty(), operatorType);
                }
                else if (right instanceof SqlValueContext)
                {
                    SqlValueContext valueContext = (SqlValueContext) right;
                    compareFuncAndValue(filter, wherePredicate, binary, valueContext.getValue(), operatorType);
                }
                else if (right instanceof SqlFuncContext)
                {
                    SqlFuncContext funcContext = (SqlFuncContext) right;
                    compareFuncAndFunc(filter, wherePredicate, binary, funcContext.getFunction(fx), operatorType);
                }
                else if (right instanceof SqlBinaryContext)
                {
                    SqlBinaryContext rightBinaryContext = (SqlBinaryContext) right;
                    SQLFunction binaryRight = fx.numberCalc(a ->
                    {
                        roundSqlContext(rightBinaryContext.getLeft(), a, fx);
                        roundSqlContext(rightBinaryContext.getRight(), a, fx);
                    }, opTrans(rightBinaryContext.getOperatorType()));
                    compareFuncAndFunc(filter, wherePredicate, binary, binaryRight, operatorType);
                }
                else if (right instanceof SqlParensContext)
                {
                    SqlParensContext rightParens = (SqlParensContext) right;
                    SQLFunction parens = fx.constValue(c -> roundSqlContext(rightParens.getContext(), c, fx));
                    compareFuncAndFunc(filter, wherePredicate, binary, parens, operatorType);
                }
                else
                {
                    throw new RuntimeException();
                }
            }
            else if (left instanceof SqlParensContext)// 左侧是括号
            {
                SqlParensContext leftParensContext = (SqlParensContext) left;
                SQLFunction leftParens = fx.constValue(c -> roundSqlContext(leftParensContext.getContext(), c, fx));
                if (right instanceof SqlPropertyContext)
                {
                    SqlPropertyContext propertyContext = (SqlPropertyContext) right;
                    compareFuncAndProperty(filter, leftParens, propertyContext.getTableOwner(), propertyContext.getProperty(), operatorType);
                }
                else if (right instanceof SqlValueContext)
                {
                    SqlValueContext valueContext = (SqlValueContext) right;
                    compareFuncAndValue(filter, wherePredicate, leftParens, valueContext.getValue(), operatorType);
                }
                else if (right instanceof SqlFuncContext)
                {
                    SqlFuncContext funcContext = (SqlFuncContext) right;
                    compareFuncAndFunc(filter, wherePredicate, leftParens, funcContext.getFunction(fx), operatorType);
                }
                else if (right instanceof SqlBinaryContext)
                {
                    SqlBinaryContext binaryContext = (SqlBinaryContext) right;
                    SQLFunction binaryRight = fx.numberCalc(a ->
                    {
                        roundSqlContext(binaryContext.getLeft(), a, fx);
                        roundSqlContext(binaryContext.getRight(), a, fx);
                    }, opTrans(binaryContext.getOperatorType()));
                    compareFuncAndFunc(filter, wherePredicate, leftParens, binaryRight, operatorType);
                }
                else if (right instanceof SqlParensContext)
                {
                    SQLFunction rightParens = fx.constValue(c -> roundSqlContext(((SqlParensContext) right).getContext(), c, fx));
                    compareFuncAndFunc(filter, wherePredicate, leftParens, rightParens, operatorType);
                }
                else
                {
                    throw new RuntimeException();
                }
            }
            else
            {
                throw new RuntimeException();
            }
        }
        else
        {
            throw new RuntimeException();
        }
    }
}
