package com.database.shard.datasource.shard;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * 分库分表
 */
public interface DatabaseShardTableDivisionAlgorithm  {

    int executorTable();

    int executoroutside();

}
