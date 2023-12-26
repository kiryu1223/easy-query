package com.easy.query.core.func;

import com.easy.query.core.expression.lambda.SQLExpression1;
import com.easy.query.core.expression.parser.core.SQLTableOwner;
import com.easy.query.core.func.column.ColumnFuncSelector;
import com.easy.query.core.func.def.enums.DateTimeDurationEnum;
import com.easy.query.core.func.def.enums.DateTimeUnitEnum;

import java.util.concurrent.TimeUnit;

/**
 * create time 2023/12/21 12:00
 * 文件说明
 *
 * @author xuejiaming
 */
public interface SQLDateTimeFunc {
    /**
     * 对时间格式的列进行格式化 默认 yyyy-MM-dd HH:mm:ss.fff
     *
     * @param property 时间格式列
     * @return 时间格式函数
     */

    default SQLFunction dateTimeFormat(String property) {
        return dateTimeFormat(property, null);
    }

    /**
     * 对时间格式的列进行格式化 默认 yyyy-MM-dd HH:mm:ss.fff
     * {@code javaFormat} yyyy-MM-dd HH:mm:ss | yyyy/MM/dd HH:mm:ss | yyyy-MM-dd ...
     *
     * @param property 时间格式列
     * @param javaFormat java格式化
     * @return 时间格式函数
     */
    default SQLFunction dateTimeFormat(String property, String javaFormat) {
        return dateTimeFormat(null, property, javaFormat);
    }

    /**
     * 对时间格式的列进行格式化 默认 yyyy-MM-dd HH:mm:ss.fff
     * {@code javaFormat} yyyy-MM-dd HH:mm:ss | yyyy/MM/dd HH:mm:ss | yyyy-MM-dd ...
     *
     * @param tableOwner 属性对应的表
     * @param property 时间格式列
     * @param javaFormat java格式化
     * @return 时间格式函数
     */
    SQLFunction dateTimeFormat(SQLTableOwner tableOwner, String property, String javaFormat);

    /**
     * 对时间格式的列进行格式化
     *
     * @param property 时间格式列
     * @param format 数据库格式化
     * @return 时间格式函数
     */
    default SQLFunction dateTimeSQLFormat(String property, String format) {
        return dateTimeSQLFormat(null, property, format);
    }

    /**
     * 对时间格式的列进行格式化
     *
     * @param tableOwner 属性对应的表
     * @param property 时间格式列
     * @param format 数据库格式化
     * @return 时间格式函数
     */
    SQLFunction dateTimeSQLFormat(SQLTableOwner tableOwner, String property, String format);

    /**
     * 时间格式添加函数
     * @param property
     * @param duration
     * @param timeUnit
     * @return 时间格式添加函数
     */
    default SQLFunction plusDateTime(String property, long duration, TimeUnit timeUnit){
        return plusDateTime(s->{
            s.column(property);
        },duration,timeUnit);
    }

    /**
     * 时间格式添加函数
     * @param sqlFunction
     * @param duration
     * @param timeUnit
     * @return 时间格式添加函数
     */
    default SQLFunction plusDateTime(SQLFunction sqlFunction, long duration, TimeUnit timeUnit){
        return plusDateTime(s->{
            s.sqlFunc(sqlFunction);
        },duration,timeUnit);
    }

    /**
     * 时间格式添加函数
     * @param sqlExpression
     * @param duration
     * @param timeUnit
     * @return 时间格式添加函数
     */
    SQLFunction plusDateTime(SQLExpression1<ColumnFuncSelector> sqlExpression, long duration, TimeUnit timeUnit);
    /**
     * 时间格式添加函数
     * @param property
     * @param month
     * @return 时间格式添加函数
     */
    default SQLFunction plusDateTimeMonths(String property, int month){
        return plusDateTimeMonths(s->{
            s.column(property).value(month);
        });
    }

    /**
     * 时间格式添加函数
     * @param sqlFunction
     * @param month
     * @return 时间格式添加函数
     */
    default SQLFunction plusDateTimeMonths(SQLFunction sqlFunction, int month){
        return plusDateTimeMonths(s->{
            s.sqlFunc(sqlFunction).value(month);
        });
    }

    /**
     * 时间格式添加函数
     * @param sqlExpression
     * @return 时间格式添加函数
     */
    SQLFunction plusDateTimeMonths(SQLExpression1<ColumnFuncSelector> sqlExpression);

    /**
     * 时间格式添加函数
     * @param property
     * @param year
     * @return 时间格式添加函数
     */
    default SQLFunction plusDateTimeYears(String property, int year){
        return plusDateTimeYears(s->{
            s.column(property).value(year);
        });
    }

    /**
     * 时间格式添加函数
     * @param sqlFunction
     * @param year
     * @return 时间格式添加函数
     */
    default SQLFunction plusDateTimeYears(SQLFunction sqlFunction, int year){
        return plusDateTimeMonths(s->{
            s.sqlFunc(sqlFunction).value(year);
        });
    }

    /**
     * 时间格式添加函数
     * @param sqlExpression
     * @return 时间格式添加函数
     */
    SQLFunction plusDateTimeYears(SQLExpression1<ColumnFuncSelector> sqlExpression);


    /**
     * 时间格式添加函数
     * @param property
     * @param dateTimeUnitEnum
     * @return 时间格式添加函数
     */
    default SQLFunction dateTimeProperty(String property, DateTimeUnitEnum dateTimeUnitEnum){
        return dateTimeProperty(s->{
            s.column(property);
        },dateTimeUnitEnum);
    }

    /**
     * 时间格式添加函数
     * @param sqlFunction
     * @param dateTimeUnitEnum
     * @return 时间格式添加函数
     */
    default SQLFunction dateTimeProperty(SQLFunction sqlFunction, DateTimeUnitEnum dateTimeUnitEnum){
        return dateTimeProperty(s->{
            s.sqlFunc(sqlFunction);
        },dateTimeUnitEnum);
    }

    /**
     * 时间格式添加函数
     * @param sqlExpression
     * @return 时间格式添加函数
     */
    SQLFunction dateTimeProperty(SQLExpression1<ColumnFuncSelector> sqlExpression, DateTimeUnitEnum dateTimeUnitEnum);

    /**
     * 相差时间函数
     * @param property
     * @param otherTable
     * @param otherProperty
     * @param durationEnum
     * @return 相差时间函数
     */
    default SQLFunction duration(String property, SQLTableOwner otherTable, String otherProperty, DateTimeDurationEnum durationEnum){
        return duration(s->{
            s.column(property)
                    .column(otherTable,otherProperty);
        },durationEnum);
    }

    /**
     * 相差时间函数
     * @param property
     * @param sqlFunction
     * @param durationEnum
     * @return 相差时间函数
     */
    default SQLFunction duration(String property,SQLFunction sqlFunction, DateTimeDurationEnum durationEnum){
        return duration(s->{
            s.column(property).sqlFunc(sqlFunction);
        },durationEnum);
    }

    /**
     * 相差时间函数
     * @param sqlFunction
     * @param otherTable
     * @param otherProperty
     * @param durationEnum
     * @return 相差时间函数
     */
    default SQLFunction duration(SQLFunction sqlFunction,SQLTableOwner otherTable, String otherProperty, DateTimeDurationEnum durationEnum){
        return duration(s->{
            s.sqlFunc(sqlFunction)
                    .column(otherTable,otherProperty);
        },durationEnum);
    }

    /**
     * 相差时间函数
     * @param sqlFunction1
     * @param sqlFunction2
     * @param durationEnum
     * @return 相差时间函数
     */
    default SQLFunction duration(SQLFunction sqlFunction1,SQLFunction sqlFunction2, DateTimeDurationEnum durationEnum){
        return duration(s->{
            s.sqlFunc(sqlFunction1).sqlFunc(sqlFunction2);
        },durationEnum);
    }

    /**
     * 相差时间函数
     * @param sqlExpression
     * @param durationEnum
     * @return 相差时间函数
     */
    SQLFunction duration(SQLExpression1<ColumnFuncSelector> sqlExpression, DateTimeDurationEnum durationEnum);

}
