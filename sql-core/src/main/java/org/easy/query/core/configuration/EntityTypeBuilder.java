package org.easy.query.core.configuration;

import org.easy.query.core.abstraction.metadata.EntityMetadata;
import org.easy.query.core.abstraction.metadata.LogicDeleteMetadata;
import org.easy.query.core.expression.lambda.SqlExpression;
import org.easy.query.core.expression.parser.abstraction.SqlPredicate;

/**
 * @FileName: EntityTypeBuilder.java
 * @Description: 文件说明
 * @Date: 2023/2/26 23:02
 * @Created by xuejiaming
 */
public class EntityTypeBuilder<T> {
    private final EntityMetadata entityMetadata;

    public EntityTypeBuilder(EntityMetadata entityMetadata){

        this.entityMetadata = entityMetadata;
    }
    public void configLogicDelete(SqlExpression<SqlPredicate<T>> defSqlExpression, SqlExpression<SqlPredicate<T>> deletedSqlExpression){
        LogicDeleteMetadata logicDeleteMetadata = new LogicDeleteMetadata(defSqlExpression, deletedSqlExpression);
        entityMetadata.setLogicDeleteMetadata(logicDeleteMetadata);
    }
}
