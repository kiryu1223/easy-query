package com.easy.query.core.lambda.visitor;

import com.easy.query.api.lambda.sqlext.SqlFunctions;
import com.easy.query.core.basic.api.update.ClientExpressionUpdatable;
import com.easy.query.core.expression.parser.core.EntitySQLTableOwner;
import com.easy.query.core.expression.parser.core.SQLTableOwner;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.lambda.visitor.context.*;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.easy.query.core.lambda.util.ExpressionUtil.*;
import static com.easy.query.core.lambda.util.SqlUtil.fieldName;

public class SetVisitorV2 extends Visitor
{
    private final ClientExpressionUpdatable<?> client;
    private final EntitySQLTableOwner<?> owner;

    public SetVisitorV2(ClientExpressionUpdatable<?> client)
    {
        this.client = client;
        this.owner = client.getColumnSetter();
    }

    @Override
    public void visit(LambdaExpression<?> lambdaExpression)
    {
        Expression body = lambdaExpression.getBody();
        SQLFunc fx = client.getExpressionContext().getRuntimeContext().fx();
        if (body.getKind() == Kind.Block)
        {
            BlockExpression block = (BlockExpression) body;
            for (Expression expression : block.getExpressions())
            {
                SqlContext context = round(expression);
                if (!(context instanceof SqlSetPair)) throw new RuntimeException();
                SqlSetPair sqlSetPair = (SqlSetPair) context;
                sqlSetPair.updatable(client, fx);
            }
        }
        else if (body.getKind() == Kind.MethodCall)
        {
            MethodCallExpression methodCallExpression = (MethodCallExpression) body;
            SqlContext context = round(methodCallExpression);
            if (!(context instanceof SqlSetPair)) throw new RuntimeException();
            SqlSetPair sqlSetPair = (SqlSetPair) context;
            sqlSetPair.updatable(client, fx);
        }
        else
        {
            throw new RuntimeException();
        }
    }

    private SqlContext round(Expression expression)
    {
        if (expression instanceof MethodCallExpression)
        {
            MethodCallExpression methodCallExpression = (MethodCallExpression) expression;
            Method callMethod = methodCallExpression.getMethod();
            if (methodCallExpression.getExpr().getKind() == Kind.Parameter)
            {
                if (isSetter(callMethod))
                {
                    SqlSetPair sqlSetPair = new SqlSetPair();
                    sqlSetPair.setProperty(fieldName(callMethod));
                    sqlSetPair.setRight(round(methodCallExpression.getArgs().get(0)));
                    return sqlSetPair;
                }
                else if (isGetter(callMethod))
                {
                    return new SqlPropertyContext(fieldName(callMethod), owner);
                }
                else
                {
                    throw new RuntimeException();
                }
            }
            else if (SqlFunctions.class.isAssignableFrom(callMethod.getDeclaringClass()))
            {
                SqlFuncContext sqlFuncContext = new SqlFuncContext(callMethod.getName());
                for (Expression arg : methodCallExpression.getArgs())
                {
                    sqlFuncContext.getArgs().add(round(arg));
                }
                return sqlFuncContext;
            }
            else
            {
                if (hasParameter(methodCallExpression)) throw new RuntimeException();
                Object value = methodCallExpression.getValue();
                return new SqlValueContext(value);
            }
        }
        else if (expression instanceof FieldSelectExpression)
        {
            FieldSelectExpression fieldSelectExpression = (FieldSelectExpression) expression;
            Field field = fieldSelectExpression.getField();
            if (fieldSelectExpression.getExpr().getKind() == Kind.Parameter)
            {
                return new SqlPropertyContext(fieldName(field), owner);
            }
            else
            {
                return new SqlValueContext(fieldSelectExpression.getValue());
            }
        }
        else if (expression instanceof ReferenceExpression)
        {
            ReferenceExpression referenceExpression = (ReferenceExpression) expression;
            return new SqlValueContext(referenceExpression.getValue());
        }
        else if (expression instanceof ConstantExpression)
        {
            ConstantExpression constantExpression = (ConstantExpression) expression;
            return new SqlValueContext(constantExpression.getValue());
        }
        else if (expression instanceof StaticClassExpression)
        {
            StaticClassExpression staticClassExpression = (StaticClassExpression) expression;
            return new SqlValueContext(staticClassExpression.getType());
        }
        else
        {
            throw new RuntimeException("不支持的表达式 " + expression.getClass().getSimpleName() + " " + expression.toString());
        }
    }
}
