package com.liyouling.patient_track.config;

import com.liyouling.patient_track.common.NoLoginException;
import com.liyouling.patient_track.common.PatientTrackException;
import com.liyouling.patient_track.utils.Result;
import com.liyouling.patient_track.utils.ResultGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常类
 * @title: GlobelExceptionResolver
 * @Author LiYouling
 * @Date: 2023/3/11 21:47
 * @Version 1.0
 */
@ControllerAdvice // 声明这个类为全局异常处理器
@ResponseBody // 将方法的返回值，以特定的格式写入到response的body区域，进而将数据返回给浏览器。
public class GlobalExceptionHandler {

    // 处理唯一值重复的异常  通过具体的异常类型定位 对于他们，我们直接捕获，给前端提示
    @ExceptionHandler(NoLoginException.class) // 声明当前方法要处理的异常类型
    public Result handlerDuplicateKeyException(NoLoginException e) {
        //1. 打印日志 以后要将日志记录在一个地方，比如数据库
        e.printStackTrace();
        //2. 可以给前端一个通用的提示
        return ResultGenerator.genErrorResult(416,"未登录");
    }


    //非预期异常 对于他们，我们直接捕获，捕获完了，记录日志， 给前端一个假提示
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        //1. 打印日志
        e.printStackTrace();

        //2. 给前端提示
        return ResultGenerator.genErrorResult(420,"系统异常");
    }
}