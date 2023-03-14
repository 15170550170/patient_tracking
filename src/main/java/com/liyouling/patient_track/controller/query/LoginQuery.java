package com.liyouling.patient_track.controller.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @title: LoginQuery
 * @Author LiYouling
 * @Date: 2023/3/11 16:35
 * @Version 1.0
 */
@Data
public class LoginQuery implements Serializable {
    @ApiModelProperty("用户名/手机/邮箱")
    @NotEmpty(message = "用户名/手机/邮箱 不能为空")
    private String username;

    @ApiModelProperty("用户密码")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
