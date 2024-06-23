package com.easy.query.core.lambda.visitor.context;

import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.func.SQLFunc;

public interface IRevQueryableWhere
{
    void RevQueryableWhere(ClientQueryable<?> client, SQLFunc fx);
}
