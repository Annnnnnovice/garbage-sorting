package com.zjz.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.zjz.code.entity.dto.LoginDTO;
import com.zjz.code.entity.po.User;
import com.zjz.code.entity.vo.UserVO;
import com.zjz.code.mapper.UserMapper;
import com.zjz.code.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjz.code.utils.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public Result login(LoginDTO loginDTO) {
        User user = userMapper.selectOne(new QueryWrapper<>(new User(loginDTO.getAccount(), DigestUtils.md5DigestAsHex(loginDTO.getCipher().getBytes()))));
        if (user == null) {
            return new Result().result500("账号或密码错误", "/user/login");
        }
        String uuid = UUID.randomUUID().toString();
        try {
            redisTemplate.opsForValue().set(uuid, user, 7 * 24 * 60 * 60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().result500("账号或密码错误", "/user/login");
        }
        return new Result().result200(uuid, "/user/login");
    }

    @Override
    public Result get(String token) {
        User user = null;
        try {
            user = (User) redisTemplate.opsForValue().get(token);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().result500("未知错误", "/user/get");
        }
        if (user == null) {
            return new Result().result404("用户可能尚未登录", "/user/get");
        }
        return new Result().result200(new UserVO(user.getName(), user.getHeadUrl()), "/user/get");
    }

    @Override
    public Result logout(String token) {
        Boolean delete = null;
        try {
            delete = redisTemplate.delete(token);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().result500("未知错误", "/user/logout");
        }
        if (delete == null) {
            return new Result().result500("未知错误", "/user/logout");
        }
        return delete ? new Result().result200("退出成功", "/user/logout") : new Result().result404("退出失败", "/user/logout");
    }


}
