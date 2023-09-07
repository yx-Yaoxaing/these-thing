package org.multi.source.multi;

import org.apache.ibatis.plugin.Interceptor;
import org.multi.source.multi.plugins.ReadWriteSeparatePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

//    @Bean
//    public Interceptor readWriteSeparatePlugin(){
//        return new ReadWriteSeparatePlugin();
//    }

}
