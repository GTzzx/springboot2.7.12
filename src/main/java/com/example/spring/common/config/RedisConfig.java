package com.example.spring.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {
    /**
     * 配置序列化方式 使数据可以实例化到redis
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer redisSerializer = new GenericJackson2JsonRedisSerializer();

        // 设置序列化 Key 的实例对象
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // 设置序列化 HashKey 的实例对象
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // 设置序列化 value 的实例对象
        redisTemplate.setValueSerializer(redisSerializer);
        // 设置序列化 HashValue 的实例对象
        redisTemplate.setHashValueSerializer(redisSerializer);
        return redisTemplate;
    }

}
