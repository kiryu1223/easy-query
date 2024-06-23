package com.easy.query.core.lambda.visitor;

import com.easy.query.api.lambda.sqlext.SqlFunctions;
import com.easy.query.core.basic.api.select.*;
import com.easy.query.core.expression.builder.Filter;
import com.easy.query.core.expression.builder.impl.FilterImpl;
import com.easy.query.core.expression.parser.core.EntitySQLTableOwner;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.func.SQLFunction;
import com.easy.query.core.lambda.visitor.context.SqlContext;
import com.easy.query.core.lambda.visitor.context.SqlFuncContext;
import com.easy.query.core.lambda.visitor.context.SqlPropertyContext;
import com.easy.query.core.lambda.visitor.context.SqlValueContext;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static com.easy.query.core.lambda.util.ExpressionUtil.hasParameter;
import static com.easy.query.core.lambda.util.ExpressionUtil.isVoid;
import static com.easy.query.core.lambda.util.SqlUtil.fieldName;

public class QueryWhereVisitor extends Visitor
{
    private final ClientQueryable<?> client;
    private final SQLFunc fx;
    private final List<EntitySQLTableOwner<?>> owners;

    public QueryWhereVisitor(ClientQueryable<?> client, List<EntitySQLTableOwner<?>> owners)
    {
        this.client = client;
        this.fx = client.getSQLEntityExpressionBuilder().getRuntimeContext().fx();
        this.owners = owners;
//        EntityTableExpressionBuilder table = client.getSQLEntityExpressionBuilder().getTable(0);
//        SimpleEntitySQLTableOwner<?> owner = new SimpleEntitySQLTableOwner<Object>(table.getEntityTable());
    }

    @Override
    public void visit(LambdaExpression<?> lambdaExpression)
    {
        List<ParameterExpression> parameters = lambdaExpression.getParameters();
        Expression body = lambdaExpression.getBody();
        round(body, parameters);
    }

    private SqlContext round(Expression expression, List<ParameterExpression> parameters)
    {
        if (expression instanceof MethodCallExpression)
        {
            MethodCallExpression methodCallExpression = (MethodCallExpression) expression;
            Method callMethod = methodCallExpression.getMethod();
            if (methodCallExpression.getExpr().getKind() == Kind.Parameter)
            {
                ParameterExpression parameter = (ParameterExpression) methodCallExpression.getExpr();
                if (parameters.contains(parameter) && !isVoid(callMethod.getReturnType()))
                {
                    int index = parameters.indexOf(parameter);
                    return new SqlPropertyContext(fieldName(callMethod), owners.get(index));
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
                    sqlFuncContext.getArgs().add(round(arg, parameters));
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
                ParameterExpression parameter = (ParameterExpression) fieldSelectExpression.getExpr();
                int index = parameters.indexOf(parameter);
                return new SqlPropertyContext(fieldName(field), owners.get(index));
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
        else if (expression instanceof BinaryExpression)
        {
            BinaryExpression binaryExpression = (BinaryExpression) expression;
            OperatorType operatorType = binaryExpression.getOperatorType();
            if (operatorType == OperatorType.AND || operatorType == OperatorType.OR)
            {
                round(binaryExpression.getLeft(), parameters);
                client.where(w ->
                {
                    if (operatorType == OperatorType.AND)
                    {
                        w.and();
                    }
                    else
                    {
                        w.or();
                    }
                });
                round(binaryExpression.getRight(), parameters);
                return null;
            }
            else if (operatorType == OperatorType.EQ || operatorType == OperatorType.NE
                    || operatorType == OperatorType.GE || operatorType == OperatorType.GT
                    || operatorType == OperatorType.LT || operatorType == OperatorType.LE)
            {
                SqlContext left = round(binaryExpression.getLeft(), parameters);
                SqlContext right = round(binaryExpression.getRight(), parameters);
                if (left instanceof SqlPropertyContext)
                {
                    SqlPropertyContext sqlPropertyContext = (SqlPropertyContext) left;
                    int index = owners.indexOf(sqlPropertyContext.getTableOwner());
                    if (right instanceof SqlValueContext)
                    {
                        SqlValueContext sqlValueContext = (SqlValueContext) right;
                        client1_10Value(index, operatorType, sqlPropertyContext.getProperty(), sqlValueContext.getValue());
                    }
                    else if (right instanceof SqlPropertyContext)
                    {
                        SqlPropertyContext propertyContext = (SqlPropertyContext) right;
                        client1_10Property(index, operatorType, sqlPropertyContext.getProperty(), propertyContext.getTableOwner(), propertyContext.getProperty());
                    }
                    else if (right instanceof SqlFuncContext)
                    {
                        SqlFuncContext sqlFuncContext = (SqlFuncContext) right;
                        SQLFunction function = sqlFuncContext.getFunction(fx);
                        client.where(w -> w.sqlFunc(function));
                    }
                    return null;
                }
                else if (left instanceof SqlValueContext)
                {
                    SqlValueContext sqlValueContext = (SqlValueContext) left;
                    if (right instanceof SqlValueContext)
                    {
                        SqlValueContext sqlValueContext = (SqlValueContext) right;
                        client1_10Value(index, operatorType, sqlPropertyContext.getProperty(), sqlValueContext.getValue());
                    }
                    else if (right instanceof SqlPropertyContext)
                    {
                        SqlPropertyContext propertyContext = (SqlPropertyContext) right;
                        client1_10Property(index, operatorType, sqlPropertyContext.getProperty(), propertyContext.getTableOwner(), propertyContext.getProperty());
                    }
                    else if (right instanceof SqlFuncContext)
                    {
                        SqlFuncContext sqlFuncContext = (SqlFuncContext) right;
                        SQLFunction function = sqlFuncContext.getFunction(fx);
                        client.where(w -> w.sqlFunc(function));
                    }
                    return null;
                }
                else
                {
                    throw new RuntimeException();
                }
            }
            return null;
        }
        else
        {
            throw new RuntimeException("不支持的表达式 " + expression.getClass().getSimpleName() + " " + expression);
        }
    }

    private void client1_10Property(int index, OperatorType type, String Property1, EntitySQLTableOwner<?> owner, String Property2)
    {
        if (client instanceof ClientQueryable10)
        {
            ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) ->
            {
                switch (index)
                {
                    case 0:
                        whereProperty(w0, type, Property1, Property2, owner);
                        break;
                    case 1:
                        whereProperty(w1, type, Property1, Property2, owner);
                        break;
                    case 2:
                        whereProperty(w2, type, Property1, Property2, owner);
                        break;
                    case 3:
                        whereProperty(w3, type, Property1, Property2, owner);
                        break;
                    case 4:
                        whereProperty(w4, type, Property1, Property2, owner);
                        break;
                    case 5:
                        whereProperty(w5, type, Property1, Property2, owner);
                        break;
                    case 6:
                        whereProperty(w6, type, Property1, Property2, owner);
                        break;
                    case 7:
                        whereProperty(w7, type, Property1, Property2, owner);
                        break;
                    case 8:
                        whereProperty(w8, type, Property1, Property2, owner);
                        break;
                    case 9:
                        whereProperty(w9, type, Property1, Property2, owner);
                        break;
                }
            });
        }
        else if (client instanceof ClientQueryable9)
        {
            ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6, w7, w8) ->
            {
                switch (index)
                {
                    case 0:
                        whereProperty(w0, type, Property1, Property2, owner);
                        break;
                    case 1:
                        whereProperty(w1, type, Property1, Property2, owner);
                        break;
                    case 2:
                        whereProperty(w2, type, Property1, Property2, owner);
                        break;
                    case 3:
                        whereProperty(w3, type, Property1, Property2, owner);
                        break;
                    case 4:
                        whereProperty(w4, type, Property1, Property2, owner);
                        break;
                    case 5:
                        whereProperty(w5, type, Property1, Property2, owner);
                        break;
                    case 6:
                        whereProperty(w6, type, Property1, Property2, owner);
                        break;
                    case 7:
                        whereProperty(w7, type, Property1, Property2, owner);
                        break;
                    case 8:
                        whereProperty(w8, type, Property1, Property2, owner);
                        break;
                }
            });
        }
        else if (client instanceof ClientQueryable8)
        {
            ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6, w7) ->
            {
                switch (index)
                {
                    case 0:
                        whereProperty(w0, type, Property1, Property2, owner);
                        break;
                    case 1:
                        whereProperty(w1, type, Property1, Property2, owner);
                        break;
                    case 2:
                        whereProperty(w2, type, Property1, Property2, owner);
                        break;
                    case 3:
                        whereProperty(w3, type, Property1, Property2, owner);
                        break;
                    case 4:
                        whereProperty(w4, type, Property1, Property2, owner);
                        break;
                    case 5:
                        whereProperty(w5, type, Property1, Property2, owner);
                        break;
                    case 6:
                        whereProperty(w6, type, Property1, Property2, owner);
                        break;
                    case 7:
                        whereProperty(w7, type, Property1, Property2, owner);
                        break;

                }
            });
        }
        else if (client instanceof ClientQueryable7)
        {
            ClientQueryable7<?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable7<?, ?, ?, ?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6) ->
            {
                switch (index)
                {
                    case 0:
                        whereProperty(w0, type, Property1, Property2, owner);
                        break;
                    case 1:
                        whereProperty(w1, type, Property1, Property2, owner);
                        break;
                    case 2:
                        whereProperty(w2, type, Property1, Property2, owner);
                        break;
                    case 3:
                        whereProperty(w3, type, Property1, Property2, owner);
                        break;
                    case 4:
                        whereProperty(w4, type, Property1, Property2, owner);
                        break;
                    case 5:
                        whereProperty(w5, type, Property1, Property2, owner);
                        break;
                    case 6:
                        whereProperty(w6, type, Property1, Property2, owner);
                        break;
                }
            });
        }
        else if (client instanceof ClientQueryable6)
        {
            ClientQueryable6<?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable6<?, ?, ?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3, w4, w5) ->
            {
                switch (index)
                {
                    case 0:
                        whereProperty(w0, type, Property1, Property2, owner);
                        break;
                    case 1:
                        whereProperty(w1, type, Property1, Property2, owner);
                        break;
                    case 2:
                        whereProperty(w2, type, Property1, Property2, owner);
                        break;
                    case 3:
                        whereProperty(w3, type, Property1, Property2, owner);
                        break;
                    case 4:
                        whereProperty(w4, type, Property1, Property2, owner);
                        break;
                    case 5:
                        whereProperty(w5, type, Property1, Property2, owner);
                        break;
                }
            });
        }
        else if (client instanceof ClientQueryable5)
        {
            ClientQueryable5<?, ?, ?, ?, ?> clientQueryable = (ClientQueryable5<?, ?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3, w4) ->
            {
                switch (index)
                {
                    case 0:
                        whereProperty(w0, type, Property1, Property2, owner);
                        break;
                    case 1:
                        whereProperty(w1, type, Property1, Property2, owner);
                        break;
                    case 2:
                        whereProperty(w2, type, Property1, Property2, owner);
                        break;
                    case 3:
                        whereProperty(w3, type, Property1, Property2, owner);
                        break;
                    case 4:
                        whereProperty(w4, type, Property1, Property2, owner);
                        break;
                }
            });
        }
        else if (client instanceof ClientQueryable4)
        {
            ClientQueryable4<?, ?, ?, ?> clientQueryable = (ClientQueryable4<?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3) ->
            {
                switch (index)
                {
                    case 0:
                        whereProperty(w0, type, Property1, Property2, owner);
                        break;
                    case 1:
                        whereProperty(w1, type, Property1, Property2, owner);
                        break;
                    case 2:
                        whereProperty(w2, type, Property1, Property2, owner);
                        break;
                    case 3:
                        whereProperty(w3, type, Property1, Property2, owner);
                        break;

                }
            });
        }
        else if (client instanceof ClientQueryable3)
        {
            ClientQueryable3<?, ?, ?> clientQueryable = (ClientQueryable3<?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2) ->
            {
                switch (index)
                {
                    case 0:
                        whereProperty(w0, type, Property1, Property2, owner);
                        break;
                    case 1:
                        whereProperty(w1, type, Property1, Property2, owner);
                        break;
                    case 2:
                        whereProperty(w2, type, Property1, Property2, owner);
                        break;
                }
            });
        }
        else if (client instanceof ClientQueryable2)
        {
            ClientQueryable2<?, ?> clientQueryable = (ClientQueryable2<?, ?>) client;
            clientQueryable.where((w0, w1) ->
            {
                switch (index)
                {
                    case 0:
                        whereProperty(w0, type, Property1, Property2, owner);
                        break;
                    case 1:
                        whereProperty(w1, type, Property1, Property2, owner);
                        break;
                }
            });
        }
        else
        {
            client.where((w0) ->
            {
                whereProperty(w0, type, Property1, Property2, owner);
            });
        }
    }

    private void whereProperty(WherePredicate<?> wherePredicate, OperatorType operatorType, String property1, String property2, EntitySQLTableOwner<?> owner)
    {
        switch (operatorType)
        {
            case EQ:
                wherePredicate.eq(owner, property1, property2);
                break;
            case NE:
                wherePredicate.ne(owner, property1, property2);
                break;
            case LT:
                wherePredicate.lt(owner, property1, property2);
                break;
            case GT:
                wherePredicate.gt(owner, property1, property2);
                break;
            case LE:
                wherePredicate.le(owner, property1, property2);
                break;
            case GE:
                wherePredicate.ge(owner, property1, property2);
                break;
        }
    }

    private void client1_10Value(int index, OperatorType type, String Property1, Object value)
    {
        if (client instanceof ClientQueryable10)
        {
            ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) ->
            {
                switch (index)
                {
                    case 0:
                        whereValue(w0, type, Property1, value);
                        break;
                    case 1:
                        whereValue(w1, type, Property1, value);
                        break;
                    case 2:
                        whereValue(w2, type, Property1, value);
                        break;
                    case 3:
                        whereValue(w3, type, Property1, value);
                        break;
                    case 4:
                        whereValue(w4, type, Property1, value);
                        break;
                    case 5:
                        whereValue(w5, type, Property1, value);
                        break;
                    case 6:
                        whereValue(w6, type, Property1, value);
                        break;
                    case 7:
                        whereValue(w7, type, Property1, value);
                        break;
                    case 8:
                        whereValue(w8, type, Property1, value);
                        break;
                    case 9:
                        whereValue(w9, type, Property1, value);
                        break;
                }
            });
        }
        else if (client instanceof ClientQueryable9)
        {
            ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6, w7, w8) ->
            {
                switch (index)
                {
                    case 0:
                        whereValue(w0, type, Property1, value);
                        break;
                    case 1:
                        whereValue(w1, type, Property1, value);
                        break;
                    case 2:
                        whereValue(w2, type, Property1, value);
                        break;
                    case 3:
                        whereValue(w3, type, Property1, value);
                        break;
                    case 4:
                        whereValue(w4, type, Property1, value);
                        break;
                    case 5:
                        whereValue(w5, type, Property1, value);
                        break;
                    case 6:
                        whereValue(w6, type, Property1, value);
                        break;
                    case 7:
                        whereValue(w7, type, Property1, value);
                        break;
                    case 8:
                        whereValue(w8, type, Property1, value);
                        break;
                }
            });
        }
        else if (client instanceof ClientQueryable8)
        {
            ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6, w7) ->
            {
                switch (index)
                {
                    case 0:
                        whereValue(w0, type, Property1, value);
                        break;
                    case 1:
                        whereValue(w1, type, Property1, value);
                        break;
                    case 2:
                        whereValue(w2, type, Property1, value);
                        break;
                    case 3:
                        whereValue(w3, type, Property1, value);
                        break;
                    case 4:
                        whereValue(w4, type, Property1, value);
                        break;
                    case 5:
                        whereValue(w5, type, Property1, value);
                        break;
                    case 6:
                        whereValue(w6, type, Property1, value);
                        break;
                    case 7:
                        whereValue(w7, type, Property1, value);
                        break;

                }
            });
        }
        else if (client instanceof ClientQueryable7)
        {
            ClientQueryable7<?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable7<?, ?, ?, ?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6) ->
            {
                switch (index)
                {
                    case 0:
                        whereValue(w0, type, Property1, value);
                        break;
                    case 1:
                        whereValue(w1, type, Property1, value);
                        break;
                    case 2:
                        whereValue(w2, type, Property1, value);
                        break;
                    case 3:
                        whereValue(w3, type, Property1, value);
                        break;
                    case 4:
                        whereValue(w4, type, Property1, value);
                        break;
                    case 5:
                        whereValue(w5, type, Property1, value);
                        break;
                    case 6:
                        whereValue(w6, type, Property1, value);
                        break;
                }
            });
        }
        else if (client instanceof ClientQueryable6)
        {
            ClientQueryable6<?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable6<?, ?, ?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3, w4, w5) ->
            {
                switch (index)
                {
                    case 0:
                        whereValue(w0, type, Property1, value);
                        break;
                    case 1:
                        whereValue(w1, type, Property1, value);
                        break;
                    case 2:
                        whereValue(w2, type, Property1, value);
                        break;
                    case 3:
                        whereValue(w3, type, Property1, value);
                        break;
                    case 4:
                        whereValue(w4, type, Property1, value);
                        break;
                    case 5:
                        whereValue(w5, type, Property1, value);
                        break;
                }
            });
        }
        else if (client instanceof ClientQueryable5)
        {
            ClientQueryable5<?, ?, ?, ?, ?> clientQueryable = (ClientQueryable5<?, ?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3, w4) ->
            {
                switch (index)
                {
                    case 0:
                        whereValue(w0, type, Property1, value);
                        break;
                    case 1:
                        whereValue(w1, type, Property1, value);
                        break;
                    case 2:
                        whereValue(w2, type, Property1, value);
                        break;
                    case 3:
                        whereValue(w3, type, Property1, value);
                        break;
                    case 4:
                        whereValue(w4, type, Property1, value);
                        break;
                }
            });
        }
        else if (client instanceof ClientQueryable4)
        {
            ClientQueryable4<?, ?, ?, ?> clientQueryable = (ClientQueryable4<?, ?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2, w3) ->
            {
                switch (index)
                {
                    case 0:
                        whereValue(w0, type, Property1, value);
                        break;
                    case 1:
                        whereValue(w1, type, Property1, value);
                        break;
                    case 2:
                        whereValue(w2, type, Property1, value);
                        break;
                    case 3:
                        whereValue(w3, type, Property1, value);
                        break;

                }
            });
        }
        else if (client instanceof ClientQueryable3)
        {
            ClientQueryable3<?, ?, ?> clientQueryable = (ClientQueryable3<?, ?, ?>) client;
            clientQueryable.where((w0, w1, w2) ->
            {
                switch (index)
                {
                    case 0:
                        whereValue(w0, type, Property1, value);
                        break;
                    case 1:
                        whereValue(w1, type, Property1, value);
                        break;
                    case 2:
                        whereValue(w2, type, Property1, value);
                        break;
                }
            });
        }
        else if (client instanceof ClientQueryable2)
        {
            ClientQueryable2<?, ?> clientQueryable = (ClientQueryable2<?, ?>) client;
            clientQueryable.where((w0, w1) ->
            {
                switch (index)
                {
                    case 0:
                        whereValue(w0, type, Property1, value);
                        break;
                    case 1:
                        whereValue(w1, type, Property1, value);
                        break;
                }
            });
        }
        else
        {
            client.where((w0) ->
            {
                whereValue(w0, type, Property1, value);
            });
        }
    }

    private void whereValue(WherePredicate<?> wherePredicate, OperatorType operatorType, String property1, Object value)
    {
        switch (operatorType)
        {
            case EQ:
                wherePredicate.eq(property1, value);
                break;
            case NE:
                wherePredicate.ne(property1, value);
                break;
            case LT:
                wherePredicate.lt(property1, value);
                break;
            case GT:
                wherePredicate.gt(property1, value);
                break;
            case LE:
                wherePredicate.le(property1, value);
                break;
            case GE:
                wherePredicate.ge(property1, value);
                break;
        }
    }
}
