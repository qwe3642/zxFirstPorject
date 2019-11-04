package com.developproject.refexample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.developproject.test.mapper")
public class RefexampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RefexampleApplication.class, args);
    }

}
