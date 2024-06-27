package com.easy.query.core.lambda.visitor;

import com.easy.query.core.expression.parser.core.EntitySQLTableOwner;
import com.easy.query.core.expression.parser.core.base.ColumnSetter;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.lambda.visitor.context.SqlContext;
import com.easy.query.core.lambda.visitor.context.SqlFuncContext;
import com.easy.query.core.lambda.visitor.context.SqlPropertyContext;
import com.easy.query.core.lambda.visitor.context.SqlValueContext;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.util.List;

import static com.easy.query.core.lambda.util.SqlUtil.fieldName;

public class SetVisitorV2 extends BaseVisitorV2
{
    private final ColumnSetter<?> columnSetter;

    public SetVisitorV2(List<EntitySQLTableOwner<?>> owners, ColumnSetter<?> columnSetter)
    {
        super(owners);
        this.columnSetter = columnSetter;
    }

    @Override
    public void visit(LambdaExpression<?> lambdaExpression)
    {
        List<ParameterExpression> parameters = lambdaExpression.getParameters();
        Expression body = lambdaExpression.getBody();
        SQLFunc fx = columnSetter.getSetter().getRuntimeContext().fx();
        if (body.getKind() == Kind.Block)
        {
            BlockExpression block = (BlockExpression) body;
            for (Expression expression : block.getExpressions())
            {
                if (expression instanceof MethodCallExpression)
                {
                    MethodCallExpression methodCall = (MethodCallExpression) expression;
                    String property = fieldName(methodCall.getMethod());
                    SqlContext context = round(methodCall.getArgs().get(0), parameters);
                    setValue(property, context, fx);
                }
            }
        }
        else if (body.getKind() == Kind.MethodCall)
        {
            MethodCallExpression methodCall = (MethodCallExpression) body;
            String property = fieldName(methodCall.getMethod());
            SqlContext context = round(methodCall, parameters);
            setValue(property, context, fx);
        }
        else
        {
            throw new RuntimeException();
        }
    }

    private void setValue(String property, SqlContext context, SQLFunc fx)
    {
        if (context instanceof SqlPropertyContext)
        {
            SqlPropertyContext sqlPropertyContext = (SqlPropertyContext) context;
            columnSetter.setWithColumn(property, sqlPropertyContext.getProperty());
        }
        else if (context instanceof SqlValueContext)
        {
            SqlValueContext sqlValueContext = (SqlValueContext) context;
            columnSetter.set(property, sqlValueContext.getValue());
        }
        else if (context instanceof SqlFuncContext)
        {
            SqlFuncContext sqlFuncContext = (SqlFuncContext) context;
            columnSetter.getSetter().setFunc(columnSetter.getTable(), property, sqlFuncContext.getFunction(fx));
        }
    }
}
