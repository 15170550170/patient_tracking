package com.liyouling.patient_track.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liyouling.patient_track.common.NoLoginException;
import com.liyouling.patient_track.config.annotation.RequestPermission;
import com.liyouling.patient_track.dao.UserMapper;
import com.liyouling.patient_track.dao.UserTokenMapper;
import com.liyouling.patient_track.dao.po.UserToken;
import com.liyouling.patient_track.redis.RedisUtils;
import com.liyouling.patient_track.utils.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: TokenPermissionInterceptor
 * @Author LiYouling
 * @Date: 2023/3/12 10:18
 * @Version 1.0
 */
@Slf4j
@Component
public class TokenPermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj) throws Exception {

        // 判断是否需要校验请求token许可，只需要看目标请求处理方法上是否有自定义请求token许可注解-TokenPermission
        if (this.checkTargetMethodHasTokenPermission(obj)){
            // 需要进行请求token许可校验，从请求头中获取token参数，做token鉴权业务逻辑处理
            String userToken = httpServletRequest.getHeader("token");

            // 判断token是否合法，如果没有，直接鉴权失败，跳转到登录
            if(StringUtils.isEmpty(userToken)){
                // token参数为空，返回鉴权失败
                this.returnTokenCheckJson(httpServletResponse, "416", "token参数为空，鉴权失败，请重新登录！");

                // 权限校验失败，需要拦截请求
                return false;
            }

            // 判断token是否有效，如果redis中可以根据此token值获取到信息，说明用户登录鉴权成功，且有效，否则鉴权失败，跳转到登录
            if(ObjectUtils.isEmpty(userTokenMapper.selectOne(new QueryWrapper<UserToken>().eq("token", userToken)))){
                // redis中没有该token的鉴权信息，返回鉴权失败
                this.returnTokenCheckJson(httpServletResponse, "416", "token参数失效，鉴权失败，请重新登录！");
                //throw NoLoginException.fail("token参数失效，鉴权失败，请重新登录！");
                return false;
            }
        }

        // 不需要拦截，直接放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) throws Exception {

    }

    /**
     * @author : zhukang
     * @date   : 2022/11/4
     * @param  : [handler]
     * @return : boolean
     * @description : 判断目标请求方法是否需要鉴权，是返回true,否发false
     */
    public boolean checkTargetMethodHasTokenPermission(Object handler){

        // 判断当前处理的handler是否已经映射到目标请求处理方法，看是不是HandlerMethod的实例对象
        if(handler instanceof HandlerMethod){
            // 强转为目标请求处理方法的实例对象，因为：HandlerMethod对象封装了目标请求处理方法的所有内容，包括方法所有的声明
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 尝试获取目标请求处理方法上，是否添加了自定义请求token许可注解-TokenPermission，取到了就是加了，取不到就没加
            RequestPermission requestPermission = handlerMethod.getMethod().getAnnotation(RequestPermission.class);

            // 判断是否成功获取到请求token许可注解，如果没有获取到，不一定代表不需要进行权限校验，因为此注解还可能加载处理类，要再次尝试从请求处理方法所在处理类上获取该注解
            if(ObjectUtils.isEmpty(requestPermission)){
                requestPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequestPermission.class);
            }

            // 最终判断是否需要进行请求token许可校验，如果获取到了，说明需要校验，否则直接放行
            return null != requestPermission;
        }

        // 请求不是需要进行鉴权操作，直接返回false
        return false;
    }



    /**
     * @author : zhukang
     * @date   : 2022/11/4
     * @param  : [response, returnCode, returnMsg]
     * @return : void
     * @description : 拦截器中，token鉴权失败的统一返回json处理
     */
    public void returnTokenCheckJson(HttpServletResponse response, String returnCode, String returnMsg){
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            response.getWriter().print(JSON.toJSONString(ResultGenerator.genErrorResult(Integer.parseInt(returnCode), returnMsg)));
        } catch (IOException e) {
            log.warn("****** 请求token许可拦截器返回结果异常：{} ******", e.getMessage());
        }
    }
}

