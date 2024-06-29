package com.easy.query.core.lambda.condition.groupBy;

import com.easy.query.api.lambda.crud.read.QueryData;
import com.easy.query.api.lambda.crud.read.group.GroupExtData;
import com.easy.query.core.basic.api.select.*;
import com.easy.query.core.expression.parser.core.base.ColumnGroupSelector;
import com.easy.query.core.lambda.condition.criteria.Criteria;
import com.easy.query.core.lambda.visitor.GroupByReader;
import com.easy.query.core.lambda.visitor.GroupByVisitorV2;
import io.github.kiryu1223.expressionTree.expressions.LambdaExpression;

import java.util.*;


public class GroupBy extends Criteria
{
    private final LambdaExpression<?> expression;

    public GroupBy(LambdaExpression<?> expression)
    {
        checkExprBody(expression);
        this.expression = expression;
    }

//    private void readGroup(QueryData queryData)
//    {
//        GroupByReader groupByReader = new GroupByReader(expression.getParameters(), queryData.getDbType());
//        expression.getBody().accept(groupByReader);
//        Map<String, GroupExtData> groupExtDataMap = groupByReader.getGroupExtDataMap();
//        // 空的GroupExtData说明碰到了非new class的情况，手动包装一下
//        if (groupExtDataMap.isEmpty())
//        {
//            groupExtDataMap.put("key", groupByReader.getCur());
//        }
//        queryData.setGroupExtDataMap(groupExtDataMap);
//    }

    public void analysis(ClientQueryable<?> queryable, QueryData queryData)
    {
//        readGroup(queryData);
//        GroupByVisitor groupBy = new GroupByVisitor(expression.getParameters(), queryData.getDbType());
//        expression.getBody().accept(groupBy);
//        analysisGroupBy(queryable,queryData,groupBy.getData(),groupBy.getSqlValue());
        List<ColumnGroupSelector<?>> columnGroupSelectors = getColumnGroupSelectors(queryable);
        GroupByVisitorV2 groupByVisitorV2 = new GroupByVisitorV2(columnGroupSelectors, expression.getParameters());
        groupByVisitorV2.visit(expression.getBody());
        queryData.setIndexMap(groupByVisitorV2.getIndexMap());
    }

    private List<ColumnGroupSelector<?>> getColumnGroupSelectors(ClientQueryable<?> queryable)
    {
        if (queryable instanceof ClientQueryable10)
        {
            ClientQueryable10 clientQueryable = (ClientQueryable10) queryable;
            ColumnGroupSelector<?> g1 = clientQueryable.getSQLExpressionProvider1().getGroupColumnSelector();
            ColumnGroupSelector<?> g2 = clientQueryable.getSQLExpressionProvider2().getGroupColumnSelector();
            ColumnGroupSelector<?> g3 = clientQueryable.getSQLExpressionProvider3().getGroupColumnSelector();
            ColumnGroupSelector<?> g4 = clientQueryable.getSQLExpressionProvider4().getGroupColumnSelector();
            ColumnGroupSelector<?> g5 = clientQueryable.getSQLExpressionProvider5().getGroupColumnSelector();
            ColumnGroupSelector<?> g6 = clientQueryable.getSQLExpressionProvider6().getGroupColumnSelector();
            ColumnGroupSelector<?> g7 = clientQueryable.getSQLExpressionProvider7().getGroupColumnSelector();
            ColumnGroupSelector<?> g8 = clientQueryable.getSQLExpressionProvider8().getGroupColumnSelector();
            ColumnGroupSelector<?> g9 = clientQueryable.getSQLExpressionProvider9().getGroupColumnSelector();
            ColumnGroupSelector<?> g10 = clientQueryable.getSQLExpressionProvider10().getGroupColumnSelector();
            return Arrays.asList(g1, g2, g3, g4, g5, g6, g7, g8, g9, g10);
        }
        else if (queryable instanceof ClientQueryable9)
        {
            ClientQueryable9 clientQueryable = (ClientQueryable9) queryable;
            ColumnGroupSelector<?> g1 = clientQueryable.getSQLExpressionProvider1().getGroupColumnSelector();
            ColumnGroupSelector<?> g2 = clientQueryable.getSQLExpressionProvider2().getGroupColumnSelector();
            ColumnGroupSelector<?> g3 = clientQueryable.getSQLExpressionProvider3().getGroupColumnSelector();
            ColumnGroupSelector<?> g4 = clientQueryable.getSQLExpressionProvider4().getGroupColumnSelector();
            ColumnGroupSelector<?> g5 = clientQueryable.getSQLExpressionProvider5().getGroupColumnSelector();
            ColumnGroupSelector<?> g6 = clientQueryable.getSQLExpressionProvider6().getGroupColumnSelector();
            ColumnGroupSelector<?> g7 = clientQueryable.getSQLExpressionProvider7().getGroupColumnSelector();
            ColumnGroupSelector<?> g8 = clientQueryable.getSQLExpressionProvider8().getGroupColumnSelector();
            ColumnGroupSelector<?> g9 = clientQueryable.getSQLExpressionProvider9().getGroupColumnSelector();
            return Arrays.asList(g1, g2, g3, g4, g5, g6, g7, g8, g9);
        }
        else if (queryable instanceof ClientQueryable8)
        {
            ClientQueryable8 clientQueryable = (ClientQueryable8) queryable;
            ColumnGroupSelector<?> g1 = clientQueryable.getSQLExpressionProvider1().getGroupColumnSelector();
            ColumnGroupSelector<?> g2 = clientQueryable.getSQLExpressionProvider2().getGroupColumnSelector();
            ColumnGroupSelector<?> g3 = clientQueryable.getSQLExpressionProvider3().getGroupColumnSelector();
            ColumnGroupSelector<?> g4 = clientQueryable.getSQLExpressionProvider4().getGroupColumnSelector();
            ColumnGroupSelector<?> g5 = clientQueryable.getSQLExpressionProvider5().getGroupColumnSelector();
            ColumnGroupSelector<?> g6 = clientQueryable.getSQLExpressionProvider6().getGroupColumnSelector();
            ColumnGroupSelector<?> g7 = clientQueryable.getSQLExpressionProvider7().getGroupColumnSelector();
            ColumnGroupSelector<?> g8 = clientQueryable.getSQLExpressionProvider8().getGroupColumnSelector();
            return Arrays.asList(g1, g2, g3, g4, g5, g6, g7, g8);
        }
        else if (queryable instanceof ClientQueryable7)
        {
            ClientQueryable7 clientQueryable = (ClientQueryable7) queryable;
            ColumnGroupSelector<?> g1 = clientQueryable.getSQLExpressionProvider1().getGroupColumnSelector();
            ColumnGroupSelector<?> g2 = clientQueryable.getSQLExpressionProvider2().getGroupColumnSelector();
            ColumnGroupSelector<?> g3 = clientQueryable.getSQLExpressionProvider3().getGroupColumnSelector();
            ColumnGroupSelector<?> g4 = clientQueryable.getSQLExpressionProvider4().getGroupColumnSelector();
            ColumnGroupSelector<?> g5 = clientQueryable.getSQLExpressionProvider5().getGroupColumnSelector();
            ColumnGroupSelector<?> g6 = clientQueryable.getSQLExpressionProvider6().getGroupColumnSelector();
            ColumnGroupSelector<?> g7 = clientQueryable.getSQLExpressionProvider7().getGroupColumnSelector();
            return Arrays.asList(g1, g2, g3, g4, g5, g6, g7);
        }
        else if (queryable instanceof ClientQueryable6)
        {
            ClientQueryable6 clientQueryable = (ClientQueryable6) queryable;
            ColumnGroupSelector<?> g1 = clientQueryable.getSQLExpressionProvider1().getGroupColumnSelector();
            ColumnGroupSelector<?> g2 = clientQueryable.getSQLExpressionProvider2().getGroupColumnSelector();
            ColumnGroupSelector<?> g3 = clientQueryable.getSQLExpressionProvider3().getGroupColumnSelector();
            ColumnGroupSelector<?> g4 = clientQueryable.getSQLExpressionProvider4().getGroupColumnSelector();
            ColumnGroupSelector<?> g5 = clientQueryable.getSQLExpressionProvider5().getGroupColumnSelector();
            ColumnGroupSelector<?> g6 = clientQueryable.getSQLExpressionProvider6().getGroupColumnSelector();
            return Arrays.asList(g1, g2, g3, g4, g5, g6);
        }
        else if (queryable instanceof ClientQueryable5)
        {
            ClientQueryable5 clientQueryable = (ClientQueryable5) queryable;
            ColumnGroupSelector<?> g1 = clientQueryable.getSQLExpressionProvider1().getGroupColumnSelector();
            ColumnGroupSelector<?> g2 = clientQueryable.getSQLExpressionProvider2().getGroupColumnSelector();
            ColumnGroupSelector<?> g3 = clientQueryable.getSQLExpressionProvider3().getGroupColumnSelector();
            ColumnGroupSelector<?> g4 = clientQueryable.getSQLExpressionProvider4().getGroupColumnSelector();
            ColumnGroupSelector<?> g5 = clientQueryable.getSQLExpressionProvider5().getGroupColumnSelector();
            return Arrays.asList(g1, g2, g3, g4, g5);
        }
        else if (queryable instanceof ClientQueryable4)
        {
            ClientQueryable4 clientQueryable = (ClientQueryable4) queryable;
            ColumnGroupSelector<?> g1 = clientQueryable.getSQLExpressionProvider1().getGroupColumnSelector();
            ColumnGroupSelector<?> g2 = clientQueryable.getSQLExpressionProvider2().getGroupColumnSelector();
            ColumnGroupSelector<?> g3 = clientQueryable.getSQLExpressionProvider3().getGroupColumnSelector();
            ColumnGroupSelector<?> g4 = clientQueryable.getSQLExpressionProvider4().getGroupColumnSelector();
            return Arrays.asList(g1, g2, g3, g4);
        }
        else if (queryable instanceof ClientQueryable3)
        {
            ClientQueryable3 clientQueryable = (ClientQueryable3) queryable;
            ColumnGroupSelector<?> g1 = clientQueryable.getSQLExpressionProvider1().getGroupColumnSelector();
            ColumnGroupSelector<?> g2 = clientQueryable.getSQLExpressionProvider2().getGroupColumnSelector();
            ColumnGroupSelector<?> g3 = clientQueryable.getSQLExpressionProvider3().getGroupColumnSelector();
            return Arrays.asList(g1, g2, g3);
        }
        else if (queryable instanceof ClientQueryable2)
        {
            ClientQueryable2 clientQueryable = (ClientQueryable2) queryable;
            ColumnGroupSelector<?> g1 = clientQueryable.getSQLExpressionProvider1().getGroupColumnSelector();
            ColumnGroupSelector<?> g2 = clientQueryable.getSQLExpressionProvider2().getGroupColumnSelector();
            return Arrays.asList(g1, g2);
        }
        else
        {
            ColumnGroupSelector<?> g1 = queryable.getSQLExpressionProvider1().getGroupColumnSelector();
            return Collections.singletonList(g1);
        }
    }
}
