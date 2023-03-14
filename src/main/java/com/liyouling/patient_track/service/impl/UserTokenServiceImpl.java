package com.liyouling.patient_track.service.impl;

import com.liyouling.patient_track.dao.po.UserToken;
import com.liyouling.patient_track.dao.UserTokenMapper;
import com.liyouling.patient_track.service.UserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * User的Token表 服务实现类
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements UserTokenService {

}
