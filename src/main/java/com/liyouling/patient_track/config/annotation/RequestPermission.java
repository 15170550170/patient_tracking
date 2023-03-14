package com.liyouling.patient_track.config.annotation;

import java.lang.annotation.*;


/*
 * @Date: 2023/3/12 10:01
 * Description: 请求token许可自定义注解，只要请求处理方法上加了此注解，就需要token鉴权
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestPermission {


}