package com.zjz.code.service;

import com.zjz.code.entity.dto.LoginDTO;
import com.zjz.code.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjz.code.utils.Result;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param loginDTO 存储账号和密码
     * @return result类对象
     */
    Result login(LoginDTO loginDTO);

    /**
     * 获得用户信息
     * @param token 令牌
     * @return result类对象
     */
    Result get(String token);

    /**
     * 登出
     * @param token 令牌
     * @return result类对象
     */
    Result logout(String token);
}
