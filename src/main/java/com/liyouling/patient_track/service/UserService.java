package com.liyouling.patient_track.service;

import com.liyouling.patient_track.dao.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liyouling.patient_track.dao.po.UserPo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
public interface UserService extends IService<User> {
    String login(String username,String password);
}
