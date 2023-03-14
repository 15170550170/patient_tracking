package com.liyouling.patient_track.controller;


import com.liyouling.patient_track.common.Constants;
import com.liyouling.patient_track.config.annotation.RequestPermission;
import com.liyouling.patient_track.config.annotation.TokenToUser;
import com.liyouling.patient_track.controller.query.LoginQuery;
import com.liyouling.patient_track.dao.po.User;
import com.liyouling.patient_track.service.UserService;
import com.liyouling.patient_track.utils.Result;
import com.liyouling.patient_track.utils.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid LoginQuery user){
        String username = user.getUsername();
        String password = user.getPassword();
        String loginResult = userService.login(username, password);
        if(!StringUtils.isEmpty(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH){
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        //登录失败
        return ResultGenerator.genFailResult(loginResult);
    }


    @GetMapping("/test")
    //@RequestPermission
    public Result<String> test(){
        Result result = null;
        /*if(user == null){
            result = ResultGenerator.genErrorResult(416,"未登录");
            return result;
        }else {
            result = ResultGenerator.genSuccessResult("登录成功！");
        }*/
        result = ResultGenerator.genSuccessResult("登录成功！");
        result.setData("sdadada");
        return result;
    }
}

