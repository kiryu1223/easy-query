package com.easy.query.core.lambda.visitor.context;

import com.easy.query.core.expression.lambda.SQLExpression1;
import com.easy.query.core.expression.parser.core.base.WhereAggregatePredicate;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.func.SQLFunction;
import com.easy.query.core.func.column.ColumnFuncSelector;
import com.easy.query.core.func.def.enums.DateTimeDurationEnum;
import com.easy.query.core.func.def.enums.DateTimeUnitEnum;
import com.easy.query.core.func.def.enums.MathMethodEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SqlFuncContext extends SqlContext
{
    private final String methodName;
    private final List<SqlContext> args = new ArrayList<>(2);

    public SqlFuncContext(String methodName)
    {
        this.methodName = methodName;
    }

    public String getMethodName()
    {
        return methodName;
    }

    public List<SqlContext> getArgs()
    {
        return args;
    }

    public SQLFunction getFunction(SQLFunc fx)
    {
        if (args.isEmpty())
        {
            return transNoArgSqlFunctions(fx);
        }
        else
        {
            switch (methodName)
            {
                case "abs":
                    return fx.abs(transHasArgSqlFunction(fx));
                case "avg":
                    return fx.avg(transHasArgSqlFunction(fx));
                case "concat":
                    return fx.concat(transHasArgSqlFunction(fx));
                case "cast":
                {
                    SqlContext context = args.get(1);
                    if (context instanceof SqlValueContext)
                    {
                        SqlValueContext valueContext = (SqlValueContext) context;
                        return fx.cast(transHasArgSqlFunction(fx, 1), (Class<?>) valueContext.getValue());
                    }
                    else
                    {
                        throw new RuntimeException(methodName + "函数的第二个参数必须是可求值的");
                    }
                }
                case "count":
                    return fx.count(transHasArgSqlFunction(fx));
                case "dateFormat":
                {
                    SqlContext context = args.get(1);
                    if (context instanceof SqlValueContext)
                    {
                        SqlValueContext valueContext = (SqlValueContext) context;
                        return fx.dateTimeFormat(transHasArgSqlFunction(fx, 1), (String) valueContext.getValue());
                    }
                    else
                    {
                        throw new RuntimeException();
                    }
                }
                case "getYear":
                    return fx.dateTimeProperty(transHasArgSqlFunction(fx), DateTimeUnitEnum.Year);
                case "getMonth":
                    return fx.dateTimeProperty(transHasArgSqlFunction(fx), DateTimeUnitEnum.Month);
                case "getDay":
                    return fx.dateTimeProperty(transHasArgSqlFunction(fx), DateTimeUnitEnum.Day);
                case "getHour":
                    return fx.dateTimeProperty(transHasArgSqlFunction(fx), DateTimeUnitEnum.Hour);
                case "getMinute":
                    return fx.dateTimeProperty(transHasArgSqlFunction(fx), DateTimeUnitEnum.Minute);
                case "getSecond":
                    return fx.dateTimeProperty(transHasArgSqlFunction(fx), DateTimeUnitEnum.Second);
                case "getDayOfWeek":
                    return fx.dateTimeProperty(transHasArgSqlFunction(fx), DateTimeUnitEnum.DayOfWeek);
                case "getDayOfYear":
                    return fx.dateTimeProperty(transHasArgSqlFunction(fx), DateTimeUnitEnum.DayOfYear);
                case "daysDiff":
                    return fx.duration(transHasArgSqlFunction(fx), DateTimeDurationEnum.Days);
                case "hoursDiff":
                    return fx.duration(transHasArgSqlFunction(fx), DateTimeDurationEnum.Hours);
                case "minutesDiff":
                    return fx.duration(transHasArgSqlFunction(fx), DateTimeDurationEnum.Minutes);
                case "secondsDiff":
                    return fx.duration(transHasArgSqlFunction(fx), DateTimeDurationEnum.Seconds);
                case "groupJoin":
                    return fx.join(transHasArgSqlFunction(fx));
                case "leftPad":
                    return fx.leftPad(transHasArgSqlFunction(fx));
                case "length":
                    return fx.length(transHasArgSqlFunction(fx));
                case "asin":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Asin);
                case "acos":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Acos);
                case "atan":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Atan);
                case "atan2":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Atan2);
                case "sin":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Sin);
                case "cos":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Cos);
                case "tan":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Tan);
                case "ceil":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Ceiling);
                case "exp":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Exp);
                case "floor":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Floor);
                case "log":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Log);
                case "log10":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Log10);
                case "pow":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Pow);
                case "round":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Round);
                case "sign":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Sign);
                case "sqrt":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Sqrt);
                case "truncate":
                    return fx.math(transHasArgSqlFunction(fx), MathMethodEnum.Truncate);
                case "max":
                    return fx.max(transHasArgSqlFunction(fx));
                case "min":
                    return fx.min(transHasArgSqlFunction(fx));
                case "addDays":
                {
                    SqlContext context = args.get(1);
                    if (context instanceof SqlValueContext)
                    {
                        SqlValueContext duration = (SqlValueContext) context;
                        return fx.plusDateTime(transHasArgSqlFunction(fx, 1), (long) duration.getValue(), TimeUnit.DAYS);
                    }
                    else
                    {
                        throw new RuntimeException(methodName + "函数的第二个参数必须是可求值的");
                    }
                }
                case "addHours":
                {
                    SqlContext context = args.get(1);
                    if (context instanceof SqlValueContext)
                    {
                        SqlValueContext duration = (SqlValueContext) context;
                        return fx.plusDateTime(transHasArgSqlFunction(fx, 1), (long) duration.getValue(), TimeUnit.HOURS);
                    }
                    else
                    {
                        throw new RuntimeException(methodName + "函数的第二个参数必须是可求值的");
                    }
                }
                case "addMinutes":
                {
                    SqlContext context = args.get(1);
                    if (context instanceof SqlValueContext)
                    {
                        SqlValueContext duration = (SqlValueContext) context;
                        return fx.plusDateTime(transHasArgSqlFunction(fx, 1), (long) duration.getValue(), TimeUnit.MINUTES);
                    }
                    else
                    {
                        throw new RuntimeException(methodName + "函数的第二个参数必须是可求值的");
                    }
                }
                case "addSeconds":
                {
                    SqlContext context = args.get(1);
                    if (context instanceof SqlValueContext)
                    {
                        SqlValueContext duration = (SqlValueContext) context;
                        return fx.plusDateTime(transHasArgSqlFunction(fx, 1), (long) duration.getValue(), TimeUnit.SECONDS);
                    }
                    else
                    {
                        throw new RuntimeException(methodName + "函数的第二个参数必须是可求值的");
                    }
                }
                case "addMicroSeconds":
                {
                    SqlContext context = args.get(1);
                    if (context instanceof SqlValueContext)
                    {
                        SqlValueContext duration = (SqlValueContext) context;
                        return fx.plusDateTime(transHasArgSqlFunction(fx, 1), (long) duration.getValue(), TimeUnit.MICROSECONDS);
                    }
                    else
                    {
                        throw new RuntimeException(methodName + "函数的第二个参数必须是可求值的");
                    }
                }
                case "addMilliSeconds":
                {
                    SqlContext context = args.get(1);
                    if (context instanceof SqlValueContext)
                    {
                        SqlValueContext duration = (SqlValueContext) context;
                        return fx.plusDateTime(transHasArgSqlFunction(fx, 1), (long) duration.getValue(), TimeUnit.MILLISECONDS);
                    }
                    else
                    {
                        throw new RuntimeException(methodName + "函数的第二个参数必须是可求值的");
                    }
                }
                case "addNanoSeconds":
                {
                    SqlContext context = args.get(1);
                    if (context instanceof SqlValueContext)
                    {
                        SqlValueContext duration = (SqlValueContext) context;
                        return fx.plusDateTime(transHasArgSqlFunction(fx, 1), (long) duration.getValue(), TimeUnit.NANOSECONDS);
                    }
                    else
                    {
                        throw new RuntimeException(methodName + "函数的第二个参数必须是可求值的");
                    }
                }
                case "addMonths":
                    return fx.plusDateTimeMonths(transHasArgSqlFunction(fx));
                case "addYears":
                    return fx.plusDateTimeYears(transHasArgSqlFunction(fx));
                case "rightPad":
                    return fx.rightPad(transHasArgSqlFunction(fx));
                case "replace":
                    return fx.replace(transHasArgSqlFunction(fx));
                case "sum":
                    return fx.sum(transHasArgSqlFunction(fx));
                case "compare":
                    return fx.stringCompareTo(transHasArgSqlFunction(fx));
                case "subString":
                    return fx.subString(transHasArgSqlFunction(fx));
                case "trim":
                    return fx.trim(transHasArgSqlFunction(fx));
                case "toLower":
                    return fx.toLower(transHasArgSqlFunction(fx));
                case "toUpper":
                    return fx.toUpper(transHasArgSqlFunction(fx));
                case "trimStart":
                    return fx.trimStart(transHasArgSqlFunction(fx));
                case "trimEnd":
                    return fx.trimEnd(transHasArgSqlFunction(fx));
                case "ifNull":
                    return fx.nullOrDefault(transHasArgSqlFunction(fx));
            }
            throw new RuntimeException("非法的sql函数");
        }
    }

    private SQLFunction transNoArgSqlFunctions(SQLFunc fx)
    {
        switch (methodName)
        {
            case "now":
                return fx.now();
            case "utcNow":
                return fx.utcNow();
        }
        throw new RuntimeException("非法的sql函数");
    }

    private SQLExpression1<ColumnFuncSelector> transHasArgSqlFunction(SQLFunc fx)
    {
        return transHasArgSqlFunction(fx, -1);
    }

    private SQLExpression1<ColumnFuncSelector> transHasArgSqlFunction(SQLFunc fx, int limit)
    {
        return (s) ->
        {
            int index = 0;
            for (SqlContext arg : args)
            {
                if (limit > 0 && index >= limit)
                {
                    break;
                }
                roundSqlContext(arg, s, fx);
                index++;
            }
        };
    }


    @Override
    public void revWhere(WherePredicate<?> wherePredicate)
    {
        wherePredicate.sqlFunc(getFunction(wherePredicate.fx()));
    }
}
