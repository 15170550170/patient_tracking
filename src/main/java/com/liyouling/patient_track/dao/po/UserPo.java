package com.liyouling.patient_track.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: User
 * @Author LiYouling
 * @Date: 2023/3/11 11:20
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class UserPo {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "username")
    private String username;
    @TableField(value = "nickname")
    private String nickname;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "email")
    private String email;
    @TableField(value = "password")
    private String password;
    @TableField(value = "confirm_code")
    private String confirmCode;
    @TableField(value = "activation_time")
    private String activationTime;
    @TableField(value = "avatar_url")
    private String avatarUrl;
    @TableField(value = "roles")
    private String roles;
    @TableField(value = "is_valid")
    private Integer isValid;
    @TableField(value = "create_date")
    private String createDate;
    @TableField(value = "update_date")
    private String updateDate;
}
