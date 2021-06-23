package com.zjz.code.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description token的工具类
 * @Author zjz
 * @Date 2021/1/17 19:12
 */
@Component
public class RedisUtil {

    @Autowired
    private static RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 验证token是否存在
     * @param token
     * @return
     */
    public static Boolean verifyToken(String token) {
        try {
            return redisTemplate.hasKey(token);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }



    public static Boolean delete(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * 普通缓存的放入
     *
     * @param key   键
     * @param value 值
     * @return true 成功 false 失败
     */
    public static Boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public static boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public static Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
}
