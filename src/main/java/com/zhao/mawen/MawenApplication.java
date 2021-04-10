package com.zhao.mawen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.zhao.mawen.dao.mapper")
@EnableCaching
public class MawenApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MawenApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MawenApplication.class, args);
    }

}
