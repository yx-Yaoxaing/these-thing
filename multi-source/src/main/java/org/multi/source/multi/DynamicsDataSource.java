package org.multi.source.multi;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * 动态数据源
 */
public class DynamicsDataSource extends AbstractRoutingDataSource {

    /**
     *
     * @param defaultTargetDataSource 默认数据源
     * @param targetDataSources 数据源选择
     */
    public DynamicsDataSource(Object defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 供 getConnection()时进行选择 切换数据源的标识 根据你返回的key 去你之前定义的connection集合中根据key进行查询
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getName();
    }
}
