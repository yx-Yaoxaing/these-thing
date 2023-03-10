package org.multi.source.multi.config;

import org.multi.source.multi.DynamicsDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class MultiDataSourceConfig {



    @Bean("masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")  // yaml文件中配置的数据源前缀
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("slave1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")  // yaml文件中配置的数据源前缀
    public DataSource slave1DataSource(){
        return DataSourceBuilder.create().build();
    }


    @Primary
    @Bean("dynamicDataSource")
    public DynamicsDataSource dynamicDataSource(){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("slave1",slave1DataSource());
        return new DynamicsDataSource(masterDataSource(),map);
    }



}
