package com.huang.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description
 * @Author huangzthttp://localhost:8080/login/to_login
 * @Date 2019.03.26
 * @Version 1.0
 */

@SpringBootApplication
@MapperScan(value = "com.huang.web")
public class MainApplication
        /**
         * 要打war包时打开下面注释
         */
        //extends SpringBootServletInitializer
{
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    //@Override
    //protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    //    return builder.sources(MainApplication.class);
    //}
}
