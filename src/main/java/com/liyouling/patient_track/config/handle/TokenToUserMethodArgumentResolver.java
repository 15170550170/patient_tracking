package com.liyouling.patient_track.config.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liyouling.patient_track.common.Constants;
import com.liyouling.patient_track.common.PatientTrackException;
import com.liyouling.patient_track.common.ServiceResultEnum;
import com.liyouling.patient_track.config.annotation.TokenToUser;
import com.liyouling.patient_track.dao.UserMapper;
import com.liyouling.patient_track.dao.UserTokenMapper;
import com.liyouling.patient_track.dao.po.User;
import com.liyouling.patient_track.dao.po.UserToken;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @title: TokenToUserMethodArgumentResolver
 * @Author LiYouling
 * @Date: 2023/3/11 19:31
 * @Version 1.0
 */
@Component
@NoArgsConstructor
public class TokenToUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if(parameter.getParameterAnnotation(TokenToUser.class) instanceof TokenToUser) {
            User user = null;
            // 获取请求头中的header
            String token = webRequest.getHeader("token");
            //验证token值是否存在
            if(null != token && !"".equals(token) && token.length() == Constants.TOKEN_LENGTH) {
                QueryWrapper<UserToken> queryWrapper = new QueryWrapper<UserToken>();
                queryWrapper
                        .eq("token", token);
                UserToken userToken = userTokenMapper.selectOne(queryWrapper);
                if(userToken == null || userToken.getExpireDate().getTime() <= System.currentTimeMillis()) {
                    PatientTrackException.fail(ServiceResultEnum.TOKEN_EXPIRED_ERROR.getResult());
                }
                QueryWrapper<User> queryWrapper1 = new QueryWrapper<User>();
                queryWrapper1
                        .eq("id", userToken.getUserId());
                user = userMapper.selectOne(queryWrapper1);
                if(user == null) {
                    PatientTrackException.fail(ServiceResultEnum.USER_NULL_ERROR.getResult());
                }
                if(user.getIsValid() == 0){
                    PatientTrackException.fail(ServiceResultEnum.LOGIN_USER_LOCKED.getResult());
                }
                return user;
            }else {
                PatientTrackException.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }
}
