package org.easy;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.easy.query.core.abstraction.*;
import org.easy.query.core.abstraction.client.JQDCClient;
import org.easy.query.core.abstraction.metadata.EntityMetadataManager;
import org.easy.query.core.abstraction.sql.Select1;
import org.easy.query.core.abstraction.sql.enums.EasyPredicate;
import org.easy.query.core.basic.DefaultConnectionManager;
import org.easy.query.core.basic.EasyConnectionManager;
import org.easy.query.core.basic.jdbc.Transaction;
import org.easy.query.core.config.*;
import org.easy.query.core.exception.JDQCException;
import org.easy.query.core.metadata.DefaultEntityMetadataManager;
import org.easy.query.mysql.MySQLJQDCClient;
import org.easy.query.mysql.config.MySQLDialect;
import org.easy.test.*;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

public class Main {
    private static final String driver="com.mysql.cj.jdbc.Driver";
    private static final String username="root";
    private static final String password="root";
    private static final String url="jdbc:mysql://127.0.0.1:3306/dbdbd0?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true";
    private static JQDCClient client;
    public static void main(String[] args) {

        DefaultConfig easyConfig = new DefaultConfig("easy-query",driver, username, password, url);

        // 设置properties
        Properties properties = new Properties();
        properties.setProperty("name", easyConfig.getName());
        properties.setProperty("driverClassName", easyConfig.getDriver());
        properties.setProperty("url",easyConfig.getUrl());
        properties.setProperty("username", easyConfig.getUsername());
        properties.setProperty("password", easyConfig.getPassword());
        int i = Runtime.getRuntime().availableProcessors();
        properties.setProperty("initialSize", String.valueOf(i));
        properties.setProperty("maxActive", String.valueOf(2 * i + 1));
        properties.setProperty("minIdle", String.valueOf(i));
        DataSource dataSource=null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new JDQCException(e);
        }
        EasyConnectionManager connectionManager= new DefaultConnectionManager(dataSource);
        DefaultExecutor defaultExecutor = new DefaultExecutor();
        EasyJdbcTypeHandler jdbcTypeHandler = new DefaultJdbcTypeHandler();
        NameConversion nameConversion = new UnderlinedNameConversion();
        EasyQueryConfiguration configuration = new EasyQueryConfiguration();
        configuration.setNameConversion(nameConversion);
        configuration.setDialect(new MySQLDialect());
        EntityMetadataManager entityMetadataManager = new DefaultEntityMetadataManager(configuration);
        EasyQueryLambdaFactory easyQueryLambdaFactory = new DefaultEasyQueryLambdaFactory();
        DefaultEasyQueryRuntimeContext jqdcRuntimeContext = new DefaultEasyQueryRuntimeContext(configuration, entityMetadataManager,easyQueryLambdaFactory,connectionManager,defaultExecutor,jdbcTypeHandler);
//        String[] packages = scanPackages;
//        for (String packageName : packages) {
//            List<EntityMetadata> entityMetadataList = JDQCUtil.loadPackage(packageName, configuration);
//            if(!entityMetadataList.isEmpty()){
//                for (EntityMetadata entityMetadata : entityMetadataList) {
//                    entityMetadataManager.addEntityMetadata(entityMetadata);
//                }
//            }
//        }


//        TableInfo tableInfo = new TableInfo(TestUser.class,"TestUser");
//        tableInfo.getColumns().putIfAbsent("id",new ColumnInfo(tableInfo,"id"));
//        tableInfo.getColumns().putIfAbsent("name",new ColumnInfo(tableInfo,"name"));
//        tableInfo.getColumns().putIfAbsent("studentName",new ColumnInfo(tableInfo,"age"));
//        configuration.addTableInfo(tableInfo);
//        TableInfo tableInfo1 = new TableInfo(TestUser1.class,"TestUser1");
//        tableInfo1.getColumns().putIfAbsent("id",new ColumnInfo(tableInfo1,"id"));
//        tableInfo1.getColumns().putIfAbsent("name",new ColumnInfo(tableInfo1,"name"));
//        tableInfo1.getColumns().putIfAbsent("uid",new ColumnInfo(tableInfo1,"uid"));
//        configuration.addTableInfo(tableInfo1);
        client=new MySQLJQDCClient(jqdcRuntimeContext);
        try(Transaction transaction = client.beginTransaction()){

            transaction.commit();
        }
        SysUserLogbyMonth sysUserLogbyMonth1 = client.select(SysUserLogbyMonth.class)
                .where(o -> o.eq(SysUserLogbyMonth::getId, "119")).firstOrNull();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 1000; j++) {

            SysUserLogbyMonth sysUserLogbyMonth2 = client.select(SysUserLogbyMonth.class)
                    .where(o -> o.eq(SysUserLogbyMonth::getId, "119")).firstOrNull();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start)+"ms");

        Select1<SysUserLogbyMonth> queryable = client.select(SysUserLogbyMonth.class)
                .where(o -> o.eq(SysUserLogbyMonth::getId, "119"));
        long count2 = queryable.count();
        List<SysUserLogbyMonth> sysUserLogbyMonths = queryable.limit(1, 10).toList();

        long count = client.select(SysUserLogbyMonth.class)
                .where(o -> o.eq(SysUserLogbyMonth::getId, "119")).count();
        long count1 = client.select(SysUserLogbyMonth.class)
                .where(o -> o.eq(SysUserLogbyMonth::getId, "119")).countDistinct(o->o.column(SysUserLogbyMonth::getId).column(SysUserLogbyMonth::getTime));

        boolean any = client.select(SysUserLogbyMonth.class)
                .where(o -> o.eq(SysUserLogbyMonth::getId, "119")).any();
        LocalDateTime localDateTime = client.select(SysUserLogbyMonth.class).maxOrNull(SysUserLogbyMonth::getTime);
        Integer totalAge = client.select(TestUserMysql.class).sumOrDefault(TestUserMysql::getAge, 0);
        Integer max = client.select(TestUserMysql.class).maxOrNull(TestUserMysql::getAge);

//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            SysUserLogbyMonth sysUserLogbyMonth = client.select(SysUserLogbyMonth.class)
//                    .where(o -> o.eq(SysUserLogbyMonth::getId, "119")).firstOrNull();
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("耗时："+(end-start)+"ms");
        List<TestUserMysqlGroup> testUserMysqls = client.select(TestUserMysql.class)
                .where(o -> o.eq(TestUserMysql::getName, "ds0"))
                .groupBy(o -> o.column(TestUserMysql::getAge))
                .having(o -> o.count(TestUserMysql::getId, EasyPredicate.GE, 0))
                .toList(TestUserMysqlGroup.class, o->o.column(TestUserMysql::getAge).columnCount(TestUserMysql::getId,TestUserMysqlGroup::getAcount));
        List<TestUserMysql> testUserMysqls1 = client.select(TestUserMysql.class)
                .where(o -> o.eq(TestUserMysql::getName, "123"))
                .groupBy(o -> o.column(TestUserMysql::getAge))
                .having(o -> o.countDistinct(TestUserMysql::getId, EasyPredicate.GT, 5).and(x->x.countDistinct(TestUserMysql::getId, EasyPredicate.GT, 5).or().countDistinct(TestUserMysql::getId, EasyPredicate.GT, 5)))
                .toList();
        TestUserMysql testUserMysql = client.select(TestUserMysql.class,"y")
                .where(o -> o.eq(TestUserMysql::getId, "102").like(TestUserMysql::getName,"1%").and(x->
                    x.like(TestUserMysql::getName,"123").or().eq(TestUserMysql::getAge,1)
             )).orderByAsc(o->o.column(TestUserMysql::getName).column(TestUserMysql::getAge))
                .orderByDesc(o->o.column(TestUserMysql::getName)).firstOrNull();


        TestUserMysql testUserMysql1 = client.select(TestUserMysql.class,"y")
                .where(o -> o.eq(TestUserMysql::getId, "102").like(TestUserMysql::getName,"1%").and(x->
                    x.like(TestUserMysql::getName,"123").or().eq(TestUserMysql::getAge,1)
             ).or(x->x.eq(TestUserMysql::getName,456).eq(TestUserMysql::getId,8989))).firstOrNull();

        SysUserLogbyMonth sysUserLogbyMonth = client.select(SysUserLogbyMonth.class)
                .where(o -> o.eq(SysUserLogbyMonth::getId, "119")).firstOrNull();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(sysUserLogbyMonth.getTime());
        System.out.println(sysUserLogbyMonth);
        TestUserMysqlx testUserMysql2 = client.select(TestUserMysql.class)
                .leftJoin(SysUserLogbyMonth.class,(a,b)->a.eq(b,TestUserMysql::getName,SysUserLogbyMonth::getId).then(b).eq(SysUserLogbyMonth::getTime, LocalDateTime.now()))
                .where(o -> o.eq(TestUserMysql::getId, "102")
                        .like(TestUserMysql::getName,"1%")
                        .and(x->x.like(TestUserMysql::getName,"123").or().eq(TestUserMysql::getAge,1)
                )).firstOrNull(TestUserMysqlx.class,x->x.columnAll().columnAs(TestUserMysql::getName,TestUserMysqlx::getName1));

        System.out.println("Hello world!");
    }
}