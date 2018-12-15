package com.peng.itrat.core.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import com.peng.itrat.core.model.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

@Intercepts({    @Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
    )})
public class PageInterceptor implements Interceptor {
    public PageInterceptor() {
    }

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();

        MetaObject metaObject;
        Object boundSql;
        for(metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory()); metaObject.hasGetter("h"); metaObject = SystemMetaObject.forObject(boundSql)) {
            boundSql = metaObject.getValue("h");
        }

        while(metaObject.hasGetter("target")) {
            boundSql = metaObject.getValue("target");
            metaObject = SystemMetaObject.forObject(boundSql);
        }

        BoundSql boundSql1 = (BoundSql)metaObject.getValue("delegate.boundSql");
        Page page = this.getPage(boundSql1);
        if(page == null) {
            return invocation.proceed();
        } else {
            String sql = boundSql1.getSql();
            this.setTotalCount(page, sql, invocation, metaObject);
            String pageSql = this.getPageSql(page, sql);
            metaObject.setValue("delegate.boundSql.sql", pageSql);
            return invocation.proceed();
        }
    }

    private Page getPage(BoundSql boundSql) {
        Object params = boundSql.getParameterObject();
        if(params == null) {
            return null;
        } else {
            Page page = null;
            if(params instanceof Page) {
                page = (Page)params;
            } else if(params instanceof Map) {
                Map paramsMap = (Map)params;
                if(paramsMap.containsKey("page") && paramsMap.get("page") instanceof Page) {
                    page = (Page)paramsMap.get("page");
                }
            }

            return page;
        }
    }

    private void setTotalCount(Page page, String sql, Invocation invocation, MetaObject metaObject) throws SQLException {
        String countSql = "select count(*) from (" + sql + ") a";
        Connection connection = (Connection)invocation.getArgs()[0];
        PreparedStatement countPrepareStatement = connection.prepareStatement(countSql);
        ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
        parameterHandler.setParameters(countPrepareStatement);
        ResultSet resultSet = countPrepareStatement.executeQuery();
        if(resultSet.next()) {
            page.setTotalCount(resultSet.getInt(1));
        }

    }

    private String getPageSql(Page page, String sql) {
        return sql + " limit " + page.getStartRow() + "," + page.getPageSize();
    }

    public Object plugin(Object target) {
        return target instanceof StatementHandler?Plugin.wrap(target, this):target;
    }

    public void setProperties(Properties properties) {
    }
}
