package com.huang.web.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.04.06
 * @Version 1.0
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * swagger2的配置文件
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.huang.web.controller"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * 构建api文档的信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //页面标题
                .title("短视频后端api接口文档")
                //设置联系人
                .contact(new Contact("Huang-笑之", "https://www.baidu.com", "946022591@qq.com"))
                //描述
                .description("欢迎访问短视频接口文档，这里是描述信息")
                //版本号
                .version("1.0")
                .build();
    }

}
