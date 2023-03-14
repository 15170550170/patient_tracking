package com.liyouling.patient_track.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liyouling.patient_track.common.ServiceResultEnum;
import com.liyouling.patient_track.dao.UserTokenMapper;
import com.liyouling.patient_track.dao.po.User;
import com.liyouling.patient_track.dao.UserMapper;
import com.liyouling.patient_track.dao.po.UserPo;
import com.liyouling.patient_track.dao.po.UserToken;
import com.liyouling.patient_track.redis.RedisUtils;
import com.liyouling.patient_track.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyouling.patient_track.utils.NumberUtil;
import com.liyouling.patient_track.utils.SystemUtil;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public String login(String username, String password) {
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<User>();
        queryWrapper1
                .eq("username",username)
                .or()
                .eq("phone",username)
                .or()
                .eq("email",username);
        User user = userMapper.selectOne(queryWrapper1);
        if (user != null) {
            if(user.getIsValid() < 1){
                return ServiceResultEnum.LOGIN_ERROR.getResult();
            }
            if(!user.getPassword().equals(password)){
                return ServiceResultEnum.LOGIN_ERROR.getResult();
            }
            //登录后即执行修改token的操作
            String token = getNewToken(System.currentTimeMillis() + "",user.getPhone());

            /*String tokenRedis = redisUtils.get(user.getId());
            if(tokenRedis == null){
                if(redisUtils.add(String.valueOf(user.getId()),token,2, TimeUnit.DAYS) && redisUtils.add(token,user,2,TimeUnit.DAYS)){
                    return token;
                }
            }else{
                if(redisUtils.expire(String.valueOf(user.getId()),2,TimeUnit.DAYS) && redisUtils.expire(token,2,TimeUnit.DAYS)){
                    return token;
                }
            }*/

            QueryWrapper<UserToken> queryWrapper = new QueryWrapper<UserToken>();
            queryWrapper
                    .eq("user_id", user.getId());
            UserToken userToken = userTokenMapper.selectOne(queryWrapper);
            Date now = new Date();

            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);   //过期48小时
            if(userToken == null) {
                userToken = new UserToken();
                userToken.setUserId(user.getId());
                userToken.setToken(token);
                userToken.setUpdateDate(now);
                userToken.setExpireDate(expireTime);
                if(userTokenMapper.insert(userToken) > 0) {
                    //新增成功返回
                    return token;
                }
            }else {
                userToken.setToken(userToken.getToken());
                userToken.setUpdateDate(now);
                userToken.setExpireDate(expireTime);
                if(userTokenMapper.updateById(userToken) > 0){
                    //新增成功返回
                    return token;
                }
            }
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    /*
    * 获取token值
    *
    * */
    private String getNewToken(String timeStr, String phone) {
        return SystemUtil.genToken(timeStr + phone + NumberUtil.genRandomNum(4));
    }
}
