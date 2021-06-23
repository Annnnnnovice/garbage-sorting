package com.zjz.code.controller;


import com.zjz.code.entity.dto.LoginDTO;
import com.zjz.code.service.UserService;
import com.zjz.code.utils.Result;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
@Api(tags = "用户模块")
@CrossOrigin
@RestController
@RequestMapping("/default/user")
public class UserController {

    @Resource
    UserService userService;

    @ApiResponses({
        @ApiResponse(code = 200, message = "登录成功"),
        @ApiResponse(code = 500, message = "账号或密码错误")
    })
    @ApiOperation("管理员登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 404, message = "用户可能尚未登录"),
        @ApiResponse(code = 500, message = "未知错误")
    })
    @ApiImplicitParam(name = "token", value = "令牌", required = true, paramType = "header", dataTypeClass = String.class)
    @ApiOperation("获取管理员信息")
    @GetMapping("/get")
    public Result get(@RequestHeader String token) {
        return userService.get(token);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 404, message = "退出登录失败"),
        @ApiResponse(code = 500, message = "未知错误")
    })
    @ApiImplicitParam(name = "token", value = "令牌", required = true, paramType = "header", dataTypeClass = String.class)
    @ApiOperation("登出")
    @GetMapping("/logout")
    public Result logout(@RequestHeader String token) {
        return userService.logout(token);
    }
}
