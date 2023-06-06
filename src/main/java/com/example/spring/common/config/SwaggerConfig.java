package com.example.spring.common.config;

import com.example.spring.common.finalEntity.SwaggerInfo;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI //开启访问验证 要在配置中配置
public class SwaggerConfig {
    @Autowired
    SwaggerInfo swaggerInfo;
    @Bean
    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//展示信息
                .select()//扫包
                .apis(RequestHandlerSelectors.basePackage(swaggerInfo.getBasePackage()))//扫描路径
                .build();
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(swaggerInfo.getTitle())
                .version(swaggerInfo.getVersion())
                .description(swaggerInfo.getDescription())
                .contact(new Contact(swaggerInfo.getContactName(),
                        swaggerInfo.getContactUrl(),
                        swaggerInfo.getEmail()))
                .build();
    }
}
