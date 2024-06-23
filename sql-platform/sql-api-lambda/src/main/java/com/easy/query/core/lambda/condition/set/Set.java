package com.easy.query.core.lambda.condition.set;

import com.easy.query.api.lambda.crud.read.QueryData;
import com.easy.query.core.basic.api.update.ClientExpressionUpdatable;
import com.easy.query.core.lambda.condition.criteria.Criteria;
import com.easy.query.core.lambda.visitor.SetVisitorV2;
import io.github.kiryu1223.expressionTree.expressions.LambdaExpression;

public class Set extends Criteria
{
    private final LambdaExpression<?> expression;

    public Set(LambdaExpression<?> expression)
    {
        this.expression = expression;
    }

    public void analysis(ClientExpressionUpdatable<?> updatable, QueryData queryData)
    {
        SetVisitorV2 setVisitorV2 = new SetVisitorV2(updatable);
        setVisitorV2.visit(expression);
    }
}
