package com.easy.query.core.lambda.visitor;

import com.easy.query.core.expression.parser.core.EntitySQLTableOwner;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.lambda.visitor.context.SqlContext;
import io.github.kiryu1223.expressionTree.expressions.Expression;
import io.github.kiryu1223.expressionTree.expressions.LambdaExpression;
import io.github.kiryu1223.expressionTree.expressions.ParameterExpression;

import java.util.List;

public class WhereVisitorV2 extends BaseVisitorV2
{
    private final WherePredicate<?> wherePredicate;

    public WhereVisitorV2(List<EntitySQLTableOwner<?>> owners, WherePredicate<?> wherePredicate)
    {
        super(owners);
        this.wherePredicate = wherePredicate;
    }

    @Override
    public void visit(LambdaExpression<?> lambdaExpression)
    {
        List<ParameterExpression> parameters = lambdaExpression.getParameters();
        Expression body = lambdaExpression.getBody();
        SqlContext round = round(body, parameters);
        round.revWhere(wherePredicate);
    }
}
