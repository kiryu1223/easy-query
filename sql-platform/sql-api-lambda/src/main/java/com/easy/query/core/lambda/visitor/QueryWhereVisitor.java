package com.easy.query.core.lambda.visitor;

import com.easy.query.api.lambda.sqlext.SqlFunctions;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.lambda.visitor.context.*;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static com.easy.query.core.lambda.util.ExpressionUtil.hasParameter;
import static com.easy.query.core.lambda.util.ExpressionUtil.isVoid;
import static com.easy.query.core.lambda.util.SqlUtil.fieldName;

public class QueryWhereVisitor extends Visitor
{
    //    private final ClientQueryable<?> client;
//    private final SQLFunc fx;
    private final List<WherePredicate<?>> wherePredicates;

    public QueryWhereVisitor(List<WherePredicate<?>> wherePredicates)
    {
//        this.client = client;
//        this.fx = client.getSQLEntityExpressionBuilder().getRuntimeContext().fx();
        this.wherePredicates = wherePredicates;
//        EntityTableExpressionBuilder table = client.getSQLEntityExpressionBuilder().getTable(0);
//        SimpleEntitySQLTableOwner<?> owner = new SimpleEntitySQLTableOwner<Object>(table.getEntityTable());
    }

    @Override
    public void visit(LambdaExpression<?> lambdaExpression)
    {
        List<ParameterExpression> parameters = lambdaExpression.getParameters();
        Expression body = lambdaExpression.getBody();
        SqlContext round = round(body, parameters);
        round.revWhere(wherePredicates.get(0));
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
                    return new SqlPropertyContext(fieldName(callMethod), wherePredicates.get(index));
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
                return new SqlPropertyContext(fieldName(field), wherePredicates.get(index));
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
            SqlContext left = round(binaryExpression.getLeft(), parameters);
            SqlContext right = round(binaryExpression.getRight(), parameters);
            return new SqlBinaryContext(operatorType, left, right);
        }
        else if (expression instanceof ParensExpression)
        {
            ParensExpression parensExpression = (ParensExpression) expression;
            SqlContext round = round(parensExpression.getExpr(), parameters);
            round.setHasParens(true);
            return round;
        }
        throw new RuntimeException("不支持的表达式 " + expression.getClass().getSimpleName() + " " + expression);
    }

//    private void client1_10Property(int index, OperatorType type, String Property1, EntitySQLTableOwner<?> owner, String Property2)
//    {
//        if (client instanceof ClientQueryable10)
//        {
//            ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereProperty(w0, type, Property1, Property2, owner);
//                        break;
//                    case 1:
//                        whereProperty(w1, type, Property1, Property2, owner);
//                        break;
//                    case 2:
//                        whereProperty(w2, type, Property1, Property2, owner);
//                        break;
//                    case 3:
//                        whereProperty(w3, type, Property1, Property2, owner);
//                        break;
//                    case 4:
//                        whereProperty(w4, type, Property1, Property2, owner);
//                        break;
//                    case 5:
//                        whereProperty(w5, type, Property1, Property2, owner);
//                        break;
//                    case 6:
//                        whereProperty(w6, type, Property1, Property2, owner);
//                        break;
//                    case 7:
//                        whereProperty(w7, type, Property1, Property2, owner);
//                        break;
//                    case 8:
//                        whereProperty(w8, type, Property1, Property2, owner);
//                        break;
//                    case 9:
//                        whereProperty(w9, type, Property1, Property2, owner);
//                        break;
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable9)
//        {
//            ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6, w7, w8) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereProperty(w0, type, Property1, Property2, owner);
//                        break;
//                    case 1:
//                        whereProperty(w1, type, Property1, Property2, owner);
//                        break;
//                    case 2:
//                        whereProperty(w2, type, Property1, Property2, owner);
//                        break;
//                    case 3:
//                        whereProperty(w3, type, Property1, Property2, owner);
//                        break;
//                    case 4:
//                        whereProperty(w4, type, Property1, Property2, owner);
//                        break;
//                    case 5:
//                        whereProperty(w5, type, Property1, Property2, owner);
//                        break;
//                    case 6:
//                        whereProperty(w6, type, Property1, Property2, owner);
//                        break;
//                    case 7:
//                        whereProperty(w7, type, Property1, Property2, owner);
//                        break;
//                    case 8:
//                        whereProperty(w8, type, Property1, Property2, owner);
//                        break;
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable8)
//        {
//            ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6, w7) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereProperty(w0, type, Property1, Property2, owner);
//                        break;
//                    case 1:
//                        whereProperty(w1, type, Property1, Property2, owner);
//                        break;
//                    case 2:
//                        whereProperty(w2, type, Property1, Property2, owner);
//                        break;
//                    case 3:
//                        whereProperty(w3, type, Property1, Property2, owner);
//                        break;
//                    case 4:
//                        whereProperty(w4, type, Property1, Property2, owner);
//                        break;
//                    case 5:
//                        whereProperty(w5, type, Property1, Property2, owner);
//                        break;
//                    case 6:
//                        whereProperty(w6, type, Property1, Property2, owner);
//                        break;
//                    case 7:
//                        whereProperty(w7, type, Property1, Property2, owner);
//                        break;
//
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable7)
//        {
//            ClientQueryable7<?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable7<?, ?, ?, ?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereProperty(w0, type, Property1, Property2, owner);
//                        break;
//                    case 1:
//                        whereProperty(w1, type, Property1, Property2, owner);
//                        break;
//                    case 2:
//                        whereProperty(w2, type, Property1, Property2, owner);
//                        break;
//                    case 3:
//                        whereProperty(w3, type, Property1, Property2, owner);
//                        break;
//                    case 4:
//                        whereProperty(w4, type, Property1, Property2, owner);
//                        break;
//                    case 5:
//                        whereProperty(w5, type, Property1, Property2, owner);
//                        break;
//                    case 6:
//                        whereProperty(w6, type, Property1, Property2, owner);
//                        break;
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable6)
//        {
//            ClientQueryable6<?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable6<?, ?, ?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3, w4, w5) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereProperty(w0, type, Property1, Property2, owner);
//                        break;
//                    case 1:
//                        whereProperty(w1, type, Property1, Property2, owner);
//                        break;
//                    case 2:
//                        whereProperty(w2, type, Property1, Property2, owner);
//                        break;
//                    case 3:
//                        whereProperty(w3, type, Property1, Property2, owner);
//                        break;
//                    case 4:
//                        whereProperty(w4, type, Property1, Property2, owner);
//                        break;
//                    case 5:
//                        whereProperty(w5, type, Property1, Property2, owner);
//                        break;
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable5)
//        {
//            ClientQueryable5<?, ?, ?, ?, ?> clientQueryable = (ClientQueryable5<?, ?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3, w4) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereProperty(w0, type, Property1, Property2, owner);
//                        break;
//                    case 1:
//                        whereProperty(w1, type, Property1, Property2, owner);
//                        break;
//                    case 2:
//                        whereProperty(w2, type, Property1, Property2, owner);
//                        break;
//                    case 3:
//                        whereProperty(w3, type, Property1, Property2, owner);
//                        break;
//                    case 4:
//                        whereProperty(w4, type, Property1, Property2, owner);
//                        break;
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable4)
//        {
//            ClientQueryable4<?, ?, ?, ?> clientQueryable = (ClientQueryable4<?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereProperty(w0, type, Property1, Property2, owner);
//                        break;
//                    case 1:
//                        whereProperty(w1, type, Property1, Property2, owner);
//                        break;
//                    case 2:
//                        whereProperty(w2, type, Property1, Property2, owner);
//                        break;
//                    case 3:
//                        whereProperty(w3, type, Property1, Property2, owner);
//                        break;
//
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable3)
//        {
//            ClientQueryable3<?, ?, ?> clientQueryable = (ClientQueryable3<?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereProperty(w0, type, Property1, Property2, owner);
//                        break;
//                    case 1:
//                        whereProperty(w1, type, Property1, Property2, owner);
//                        break;
//                    case 2:
//                        whereProperty(w2, type, Property1, Property2, owner);
//                        break;
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable2)
//        {
//            ClientQueryable2<?, ?> clientQueryable = (ClientQueryable2<?, ?>) client;
//            clientQueryable.where((w0, w1) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereProperty(w0, type, Property1, Property2, owner);
//                        break;
//                    case 1:
//                        whereProperty(w1, type, Property1, Property2, owner);
//                        break;
//                }
//            });
//        }
//        else
//        {
//            client.where((w0) ->
//            {
//                whereProperty(w0, type, Property1, Property2, owner);
//            });
//        }
//    }
//
//    private void whereProperty(WherePredicate<?> wherePredicate, OperatorType operatorType, String property1, String property2, EntitySQLTableOwner<?> owner)
//    {
//        switch (operatorType)
//        {
//            case EQ:
//                wherePredicate.eq(owner, property1, property2);
//                break;
//            case NE:
//                wherePredicate.ne(owner, property1, property2);
//                break;
//            case LT:
//                wherePredicate.lt(owner, property1, property2);
//                break;
//            case GT:
//                wherePredicate.gt(owner, property1, property2);
//                break;
//            case LE:
//                wherePredicate.le(owner, property1, property2);
//                break;
//            case GE:
//                wherePredicate.ge(owner, property1, property2);
//                break;
//        }
//    }
//
//    private void client1_10Value(int index, OperatorType type, String Property1, Object value)
//    {
//        if (client instanceof ClientQueryable10)
//        {
//            ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereValue(w0, type, Property1, value);
//                        break;
//                    case 1:
//                        whereValue(w1, type, Property1, value);
//                        break;
//                    case 2:
//                        whereValue(w2, type, Property1, value);
//                        break;
//                    case 3:
//                        whereValue(w3, type, Property1, value);
//                        break;
//                    case 4:
//                        whereValue(w4, type, Property1, value);
//                        break;
//                    case 5:
//                        whereValue(w5, type, Property1, value);
//                        break;
//                    case 6:
//                        whereValue(w6, type, Property1, value);
//                        break;
//                    case 7:
//                        whereValue(w7, type, Property1, value);
//                        break;
//                    case 8:
//                        whereValue(w8, type, Property1, value);
//                        break;
//                    case 9:
//                        whereValue(w9, type, Property1, value);
//                        break;
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable9)
//        {
//            ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6, w7, w8) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereValue(w0, type, Property1, value);
//                        break;
//                    case 1:
//                        whereValue(w1, type, Property1, value);
//                        break;
//                    case 2:
//                        whereValue(w2, type, Property1, value);
//                        break;
//                    case 3:
//                        whereValue(w3, type, Property1, value);
//                        break;
//                    case 4:
//                        whereValue(w4, type, Property1, value);
//                        break;
//                    case 5:
//                        whereValue(w5, type, Property1, value);
//                        break;
//                    case 6:
//                        whereValue(w6, type, Property1, value);
//                        break;
//                    case 7:
//                        whereValue(w7, type, Property1, value);
//                        break;
//                    case 8:
//                        whereValue(w8, type, Property1, value);
//                        break;
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable8)
//        {
//            ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6, w7) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereValue(w0, type, Property1, value);
//                        break;
//                    case 1:
//                        whereValue(w1, type, Property1, value);
//                        break;
//                    case 2:
//                        whereValue(w2, type, Property1, value);
//                        break;
//                    case 3:
//                        whereValue(w3, type, Property1, value);
//                        break;
//                    case 4:
//                        whereValue(w4, type, Property1, value);
//                        break;
//                    case 5:
//                        whereValue(w5, type, Property1, value);
//                        break;
//                    case 6:
//                        whereValue(w6, type, Property1, value);
//                        break;
//                    case 7:
//                        whereValue(w7, type, Property1, value);
//                        break;
//
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable7)
//        {
//            ClientQueryable7<?, ?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable7<?, ?, ?, ?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3, w4, w5, w6) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereValue(w0, type, Property1, value);
//                        break;
//                    case 1:
//                        whereValue(w1, type, Property1, value);
//                        break;
//                    case 2:
//                        whereValue(w2, type, Property1, value);
//                        break;
//                    case 3:
//                        whereValue(w3, type, Property1, value);
//                        break;
//                    case 4:
//                        whereValue(w4, type, Property1, value);
//                        break;
//                    case 5:
//                        whereValue(w5, type, Property1, value);
//                        break;
//                    case 6:
//                        whereValue(w6, type, Property1, value);
//                        break;
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable6)
//        {
//            ClientQueryable6<?, ?, ?, ?, ?, ?> clientQueryable = (ClientQueryable6<?, ?, ?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3, w4, w5) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereValue(w0, type, Property1, value);
//                        break;
//                    case 1:
//                        whereValue(w1, type, Property1, value);
//                        break;
//                    case 2:
//                        whereValue(w2, type, Property1, value);
//                        break;
//                    case 3:
//                        whereValue(w3, type, Property1, value);
//                        break;
//                    case 4:
//                        whereValue(w4, type, Property1, value);
//                        break;
//                    case 5:
//                        whereValue(w5, type, Property1, value);
//                        break;
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable5)
//        {
//            ClientQueryable5<?, ?, ?, ?, ?> clientQueryable = (ClientQueryable5<?, ?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3, w4) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereValue(w0, type, Property1, value);
//                        break;
//                    case 1:
//                        whereValue(w1, type, Property1, value);
//                        break;
//                    case 2:
//                        whereValue(w2, type, Property1, value);
//                        break;
//                    case 3:
//                        whereValue(w3, type, Property1, value);
//                        break;
//                    case 4:
//                        whereValue(w4, type, Property1, value);
//                        break;
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable4)
//        {
//            ClientQueryable4<?, ?, ?, ?> clientQueryable = (ClientQueryable4<?, ?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2, w3) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereValue(w0, type, Property1, value);
//                        break;
//                    case 1:
//                        whereValue(w1, type, Property1, value);
//                        break;
//                    case 2:
//                        whereValue(w2, type, Property1, value);
//                        break;
//                    case 3:
//                        whereValue(w3, type, Property1, value);
//                        break;
//
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable3)
//        {
//            ClientQueryable3<?, ?, ?> clientQueryable = (ClientQueryable3<?, ?, ?>) client;
//            clientQueryable.where((w0, w1, w2) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereValue(w0, type, Property1, value);
//                        break;
//                    case 1:
//                        whereValue(w1, type, Property1, value);
//                        break;
//                    case 2:
//                        whereValue(w2, type, Property1, value);
//                        break;
//                }
//            });
//        }
//        else if (client instanceof ClientQueryable2)
//        {
//            ClientQueryable2<?, ?> clientQueryable = (ClientQueryable2<?, ?>) client;
//            clientQueryable.where((w0, w1) ->
//            {
//                switch (index)
//                {
//                    case 0:
//                        whereValue(w0, type, Property1, value);
//                        break;
//                    case 1:
//                        whereValue(w1, type, Property1, value);
//                        break;
//                }
//            });
//        }
//        else
//        {
//            client.where((w0) ->
//            {
//                whereValue(w0, type, Property1, value);
//            });
//        }
//    }
//
//    private void whereValue(WherePredicate<?> wherePredicate, OperatorType operatorType, String property1, Object value)
//    {
//        switch (operatorType)
//        {
//            case EQ:
//                wherePredicate.eq(property1, value);
//                break;
//            case NE:
//                wherePredicate.ne(property1, value);
//                break;
//            case LT:
//                wherePredicate.lt(property1, value);
//                break;
//            case GT:
//                wherePredicate.gt(property1, value);
//                break;
//            case LE:
//                wherePredicate.le(property1, value);
//                break;
//            case GE:
//                wherePredicate.ge(property1, value);
//                break;
//        }
//    }
//

}
