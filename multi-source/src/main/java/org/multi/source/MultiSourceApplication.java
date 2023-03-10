package org.multi.source;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.multi.source.mapper")
public class MultiSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiSourceApplication.class, args);
    }

}
