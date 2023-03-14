package com.liyouling.patient_track.controller.query;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @title: UserQuery
 * @Author LiYouling
 * @Date: 2023/3/11 11:16
 * @Version 1.0
 */
@Data
public class UserQuery {
    private String username;
    private String phone;
    private String email;
    private String password;
    private String confirmCode;
    private String roles;
}
