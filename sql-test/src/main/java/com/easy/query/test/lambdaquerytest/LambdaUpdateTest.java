package com.easy.query.test.lambdaquerytest;

import com.easy.query.api.lambda.crud.read.LQuery2;
import com.easy.query.api.lambda.crud.update.LUpdate;
import com.easy.query.api.lambda.sqlext.SqlFunctions;
import com.easy.query.core.api.client.EasyQueryClient;
import com.easy.query.core.extension.client.SQLClientFunc;
import com.easy.query.test.h2.domain.DefTable;
import com.easy.query.test.h2.domain.DefTableLeft1;
import org.junit.Assert;
import org.junit.Test;

//@SuppressWarnings("all")
public class LambdaUpdateTest extends LambdaBaseTest
{
    @Test
    public void u1()
    {
        String id = "id";
        LUpdate<DefTable> momo = elq.updatable(DefTable.class).set(s ->
        {
            s.setAvatar(s.getOptions());
            s.setId(id);
            s.setMobile("momo");
            s.setStatus(SqlFunctions.cast("12", int.class));
            s.setNumber(SqlFunctions.cast(s.getScore(), int.class));
        }).where(w -> w.getId() == "1");
        String sql = momo.toSql();
        Assert.assertEquals("UPDATE t_def_table SET avatar = options,id = ?,mobile = ?,status = CAST(? AS SIGNED),number = CAST(score AS SIGNED) WHERE id = ?", sql);
        long l = momo.executeRows();
        Assert.assertEquals(1, l);
    }

    @Test
    public void u2()
    {
        LQuery2<DefTable, DefTableLeft1> where = elq.queryable(DefTable.class, DefTableLeft1.class)
                .where((w, b) -> w.getEnable() == !b.getEnable() || (b.getNumber() == 1 || (100 + SqlFunctions.cast(w.getOptions(), int.class)) < b.getNumber()));
        System.out.println(where.toSQL());
    }

    @Test
    public void u3()
    {
        EasyQueryClient easyQueryClient = elq.getEasyQueryClient();
        String sql = easyQueryClient.queryable(DefTable.class)
                .from(DefTableLeft1.class)
                .where((w0, w1) ->
                {

                }).select(DefTable.class, (a, b) ->
                {
                    a.sqlSegmentAs(SQLClientFunc.caseWhenBuilder(a).elseEnd(0), "");
                }).toSQL();
        System.out.println(sql);
    }
}
