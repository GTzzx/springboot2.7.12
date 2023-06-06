package com.example.spring;


import com.example.spring.common.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {

    @Resource
    private RedisUtil redisUtil;

    /**
     * redis 设置key实现
     */
    @Test
    public void  redisTest1(){
        String key ="NAME";
        redisUtil.set(key,"zzxGt");
        String res = redisUtil.get(key);
        System.out.println(res);

    }


}
