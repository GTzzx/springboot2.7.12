package com.example.spring.common.utils;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 判断key是否存在
     */
    public boolean hasKey(String key){
        if (StringUtils.hasLength(key)){
            return redisTemplate.hasKey(key);
        }
        return false;
    }

    /**
     * 删除key
     */
    public boolean deletedKey(String key){
        if (StringUtils.hasLength(key)){
            return redisTemplate.delete(key);
        }
        return false;
    }
    /**
     * 设置key过期时间
     */
    public boolean expTime(String key, long timeout, TimeUnit timeUnit){
        if (StringUtils.hasLength(key)){
            return redisTemplate.expire(key,timeout,timeUnit);
        }
        return false;
    }
    /**
     * String set
     */
    public<T> void set(String key,T value) {
        if (StringUtils.hasLength(key)){
            redisTemplate.opsForValue().set(key,value);
        }
    }
    /**
     * String get
     */
    public<T> T get(String key) {
        ValueOperations<String,T>valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
}
