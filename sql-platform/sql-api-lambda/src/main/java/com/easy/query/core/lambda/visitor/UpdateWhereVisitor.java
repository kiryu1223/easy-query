package com.easy.query.core.lambda.visitor;

import com.easy.query.core.basic.api.update.ClientExpressionUpdatable;
import com.easy.query.core.expression.parser.core.SQLTableOwner;
import com.easy.query.core.func.SQLFunc;
import io.github.kiryu1223.expressionTree.expressions.Visitor;

import java.util.List;

public class UpdateWhereVisitor extends Visitor
{
    private final ClientExpressionUpdatable<?> client;
    private final SQLFunc fx;
    private final SQLTableOwner owner;

    public UpdateWhereVisitor(ClientExpressionUpdatable<?> client, SQLFunc fx, List<SQLTableOwner> owners)
    {
        this.client = client;
        this.fx = client.getExpressionContext().getRuntimeContext().fx();
        this.owner = client.getColumnSetter();
    }
}
