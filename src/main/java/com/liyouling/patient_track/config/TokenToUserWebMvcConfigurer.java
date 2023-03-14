package com.liyouling.patient_track.config;

import com.liyouling.patient_track.config.handle.TokenToUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @title: TokenToUserMethodArgumentResolver
 * @Author LiYouling
 * @Date: 2023/3/11 20:02
 * @Version 1.0
 */
@Configuration
public class TokenToUserWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private TokenToUserMethodArgumentResolver tokenToUserMethodArgumentResolver;
    @Autowired
    private TokenPermissionInterceptor tokenPermissionInterceptor;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(tokenToUserMethodArgumentResolver);
    }


    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

        //手动添加自定义拦截器到系统的拦截器组中，才可以生效，否者不生效
        interceptorRegistry.addInterceptor(tokenPermissionInterceptor).addPathPatterns("/**");

    }
}
