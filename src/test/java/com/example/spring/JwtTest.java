package com.example.spring;

import com.example.spring.common.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**\
 * jwt token创建工具
 *          用于秘钥创建与解析
 */

@SpringBootTest
public class JwtTest {
    @Resource
    private JwtUtil jwtUtil;

    @Test
    public  void testJwt(){
        System.out.println(jwtUtil.createToken("1",null));
    }
    @Test
    public  void  getTokenUserId(){

        System.out.println(jwtUtil.getUserId("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg0OTkyMjY3LCJleHAiOjE2ODQ5OTU4Njd9.x91AKT5mrTUT0c-pRTqHsKfmGNTCJXZa97ebUFAfrVM"));
    }
    @Test
    public void getValidateToken(){
        System.out.println(jwtUtil.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg0OTkyMjY3LCJleHAiOjE2ODQ5OTU4Njd9.x91AKT5mrTUT0c-pRTqHsKfmGNTCJXZa97ebUFAfrVM"));
    }

}
