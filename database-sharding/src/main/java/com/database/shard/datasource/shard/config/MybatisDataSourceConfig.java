package com.database.shard.datasource.shard.config;


import com.database.shard.datasource.DynamicDataSourceContentHodler;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 利用mybatis 插件实现 分库分表
 * demo 实现 分两库 四表
 * db_
 * table_
 */
@Configuration
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class }) })
public class MybatisDataSourceConfig implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement[] args = (MappedStatement[]) invocation.getArgs();
        MappedStatement mappedStatements = args[0];
        Method method = invocation.getMethod();
        Parameter[] parameters = method.getParameters();
        BoundSql boundSql = mappedStatements.getBoundSql(parameters);
        String sql = boundSql.getSql();

        DynamicDataSourceContentHodler.set("");

        return null;
    }
}
