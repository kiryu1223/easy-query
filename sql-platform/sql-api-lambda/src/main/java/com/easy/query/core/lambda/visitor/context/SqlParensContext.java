package com.easy.query.core.lambda.visitor.context;

import com.easy.query.core.expression.parser.core.base.WherePredicate;
import io.github.kiryu1223.expressionTree.expressions.OperatorType;

public class SqlParensContext extends SqlContext
{
    private final SqlContext context;

    public SqlParensContext(SqlContext context)
    {
        this.context = context;
    }

    public SqlContext getContext()
    {
        return context;
    }

    @Override
    public void revWhere(WherePredicate<?> wherePredicate)
    {
        if (context instanceof SqlBinaryContext)
        {
            SqlBinaryContext binaryContext = (SqlBinaryContext) context;
            SqlOperator operatorType = binaryContext.getOperatorType();
            switch (operatorType)
            {
                case AND:
                {
                    wherePredicate.and(() -> binaryContext.revWhere(wherePredicate));
                    break;
                }
                case OR:
                {
                    wherePredicate.or(() -> binaryContext.revWhere(wherePredicate));
                    break;
                }
                default:
                    context.revWhere(wherePredicate);
            }
        }
        else
        {
            context.revWhere(wherePredicate);
        }
    }
}
