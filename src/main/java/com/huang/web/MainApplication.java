package com.huang.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.26
 * @Version 1.0
 */

@SpringBootApplication
@MapperScan(value = "com.huang.web")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
