package com.sunyj.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jia
 * @since 2020/7/25 20:15
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.sunyj.template.biz.mapper"})
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                // 当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.sunyj"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("Swagger2 API接口").description("API接口文档")
                .description("Spring Boot 系列学习")
                .termsOfServiceUrl("https://github.com/imyanger")
                // 创建人
                .contact(new Contact("sunyj", "https://github.com/smalldust111/", "2110615719@qq.com")).version("1.0")
                .build();
    }
}
