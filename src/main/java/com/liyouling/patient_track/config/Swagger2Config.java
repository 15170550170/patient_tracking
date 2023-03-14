package com.liyouling.patient_track.config;

import com.liyouling.patient_track.dao.po.User;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @title: Swagger2Config
 * @Author LiYouling
 * @Date: 2023/3/11 14:52
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api (){
        ParameterBuilder parameter = new ParameterBuilder();
        List<Parameter> swaggerParameters = new ArrayList<>();
        parameter.name("token").description("用户认证信息")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        swaggerParameters.add(parameter.build());
        return new Docket (DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(User.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liyouling.patient_track.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(swaggerParameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-api文档")
                .description("swagger文档 by 李佑岭")
                .version("1.0")
                .build();
    }
}
