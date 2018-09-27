package com.zhuxl.job.component.interceptor;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * 拦截mybatis格式化输出sql 项目名称:咪咕合管 包名称: com.migu.tsg.pms.platform.component.core.pagination.mybatis.interceptor 类名称:
 * MybatisSqlInterceptor 类描述: 创建人: zhuxiaolong@aspirecn.com 创建时间:2017年9月7日 下午5:32:21 版本： V1.0.0
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})})
@Component
@ConditionalOnExpression("${mybatis-sql-plugin.enable}")
public class MybatisSqlInterceptor implements Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisSqlInterceptor.class);

    @Value("${mybatis-sql-plugin.outer-log4j-control}")
    private boolean outerLog4jControl;

    @Value("${mybatis-sql-plugin.show-connection-info}")
    private boolean showConnectionInfo;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        int argsLength = 1;
        if (invocation.getArgs().length > argsLength) {
            parameter = invocation.getArgs()[1];
        }
        String sqlId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        Object returnValue = null;
        long start = System.currentTimeMillis();
        long end = 0L;
        boolean normalExecute = true;
        try {
            returnValue = invocation.proceed();
            end = System.currentTimeMillis();
        } catch (Exception e) {
            end = System.currentTimeMillis();
            normalExecute = false;
            throw e;
        } finally {
            String sql = getSql(configuration, boundSql, sqlId, (end - start), returnValue);
            if (!normalExecute) {
                LOGGER.error(sql);
            } else {
                if (outerLog4jControl) {
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace(sql);
                    } else if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(sql);
                    }
                } else {
                    LOGGER.info(sql);
                }
            }
        }

        return returnValue;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time,
                          Object returnValue) {
        String sql = showSql(configuration, boundSql);


        DataSource dataSource = configuration.getEnvironment().getDataSource();
        String username = "其他", jdbcUrl = "其他";
        if (dataSource instanceof DruidDataSource) {
            DruidDataSource druidDataSource = (DruidDataSource) dataSource;
            username = druidDataSource.getUsername();
            jdbcUrl = druidDataSource.getRawJdbcUrl();

        }


        String _top = showConnectionInfo ? ("========================user:" + username + "   pwd:***   db:" + jdbcUrl) : "=";
        String _sqlId = "[    SQL   ID]:" + sqlId;
        String _sql = "[         SQL]:" + sql;
        String _size = "[RETURN TOTAL]:";
        String _data = "[RETURN  DATA]:";
        String _time = "[WASTE   TIME]:" + time + "(ms)";
        String _bottom = "=";

        if (returnValue instanceof List) {
            List value = (List) returnValue;
            final int size = value.size();
            _size += size;
            if (size > 50) {
                value = value.subList(0, 50);
            }
            _data += JSONArray.toJSONString(value);
        } else if (returnValue instanceof String || returnValue instanceof Long) {
            _size += 1;
            _data += String.valueOf(returnValue);
        } else {
            _data += returnValue;
        }

        StringBuffer str = new StringBuffer("\n")
                .append(rightPad(_top, "=", 120)).append("\n")
                .append(rightPad(_sqlId, " ", 120)).append("\n")
                .append(rightPad(_sql, " ", 120)).append("\n")
                .append(rightPad(_size, " ", 120)).append("\n")
                .append(rightPad(_data, " ", 120)).append("\n")
                .append(rightPad(_time, " ", 120)).append("\n")
                .append(rightPad(_bottom, "=", 120)).append("\n");

        return str.toString();
    }

    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }

    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = replaceFirst("\\?", sql, getParameterValue(parameterObject));

            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = replaceFirst("\\?", sql, getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = replaceFirst("\\?", sql, getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    private static String leftPad(String result, String padChar, int size) {
        return StringUtils.leftPad(result, size, padChar);
    }

    private static String rightPad(String result, String padChar, int size) {
        result = StringUtils.rightPad(result, size, padChar);
        return result;
    }

    private static String replaceFirst(String regex, String sql, String param) {
        param = param.replace("$", "REGEX_DOLLAR");
        sql = sql.replaceFirst(regex, param);
        return sql.replace("REGEX_DOLLAR", "$");
    }
}
