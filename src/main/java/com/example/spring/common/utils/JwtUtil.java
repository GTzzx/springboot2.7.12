package com.example.spring.common.utils;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.exp-time}")
    private Duration expTime;

    /**
     * 签发Token
     * @param subject 用来存储用户id
     * @param claims  用来存储在jwt的内容 一般不放置敏感信息（可解码）
     * @return
     */
    public String createToken(String subject, Map<String,Object> claims){
        JwtBuilder jwtBuilder = Jwts.builder();
        //有数据 则重置
        if (claims!=null){
            jwtBuilder.setClaims(claims);
        }
        if (StringUtils.hasLength(subject)){
            jwtBuilder.setSubject(subject);
        }
        //获取当前时间戳
        long timeMillis = System.currentTimeMillis();
        /**
         * 签发时间
         */
        jwtBuilder.setIssuedAt(new Date(timeMillis));
        //获取时间间隔 转换为时间戳
        long toMillis = expTime.toMillis();
        //若获取时间间隔大于0 将当前时间加上时间间隔载入过期时间
        if (toMillis>0){
            long expMillis=timeMillis+toMillis;
        /**
         * 过期时间
         */
            jwtBuilder.setExpiration(new Date(expMillis));
        }
        /**
         * 签名密钥获取
         */
        if (StringUtils.hasLength(secretKey)){
           //加密算法对象
            SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
            //密钥加密
            jwtBuilder.signWith(hs256, DatatypeConverter.parseBase64Binary(secretKey));
        }
        return jwtBuilder.compact();
    }

    /**
     * 解析Token
     */
    public Claims pareToken(String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))//解析方法
                    .parseClaimsJws(token)//输入密钥进行解析
                    .getBody();//获取体部
            return claims;
        } catch (ExpiredJwtException e) {
           log.error("ExpiredJwtException:",e);
        } catch (UnsupportedJwtException e) {
            log.error("UnsupportedJwtException:",e);
        } catch (MalformedJwtException e) {
            log.error("MalformedJwtException:",e);
        } catch (SignatureException e) {
            log.error("SignatureException:",e);
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException:",e);
        }
        return null;
    }

    /**
     * 从token中获取UserId
     * @param token
     * @return
     */
    public  String getUserId(String token){
        Claims claims = pareToken(token);
        if (claims!=null){
            return claims.getSubject();
        }
        return null;
    }

    /**
     * 校验token是否符合要求
     * true：通过
     * false：失败
     */
    public boolean validateToken(String token){
        try {
            Claims claims = pareToken(token);
            //拿到过期时间
            Date expiration = claims.getExpiration();
            //判断是否在当前时间之后
            return expiration.after(new Date());
        } catch (Exception e) {
           log.error("validateToken",e);
           return false;
        }


    }



}
