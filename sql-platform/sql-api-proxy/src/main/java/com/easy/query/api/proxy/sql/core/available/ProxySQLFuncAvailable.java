package com.easy.query.api.proxy.sql.core.available;

import com.easy.query.api.proxy.func.ProxySQLFuncImpl;
import com.easy.query.api.proxy.func.ProxySQLFunc;
import com.easy.query.core.expression.parser.core.available.RuntimeContextAvailable;

/**
 * create time 2023/10/13 10:48
 * 文件说明
 *
 * @author xuejiaming
 */
public interface ProxySQLFuncAvailable extends RuntimeContextAvailable {
    default ProxySQLFunc sqlFunc(){
        return new ProxySQLFuncImpl(getRuntimeContext().sqlFunc());
    }
}