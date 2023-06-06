package com.example.spring.controller;

import com.example.spring.common.exception.BusinessException;
import com.example.spring.dao.entity.TestOne;
import com.example.spring.dao.entity.TestTwo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "测试模块")
//swagger接口文档访问路径/doc.html
public class testController {

    @Value("${env}")
    private String env;

    @ApiOperation(value = "测试环境配置接口")
    @GetMapping("/env")
    //进行响应状态封装 对象模式
//    public DataResult<String> getEnv(){
//        return  DataResult.success("env="+env);
//    }
    //进行响应状态封装 装饰者模式
    public String getEnv(){
        return  "env="+env;
    }

    @ApiOperation(value = "全局异常统一测试接口")
    @GetMapping("/error")
    public String getError(){
        int i =1/0;
        return "error测试";
    }


    @ApiOperation(value = "全局异常统一测试接口2")
    @GetMapping("error1")
    public void empty(){
        throw  new RuntimeException("自定义异常");
    }

    @ApiOperation(value = "数据校验测试")
    @PostMapping("/testOne")
    public TestOne getTestOne(@RequestBody TestOne one){
        if (!StringUtils.hasLength(one.getUsername())){
            throw  new BusinessException(5000,"账号为空");
        }
        if (!StringUtils.hasLength(one.getPassword())){
            throw  new BusinessException(5000,"密码为空");
        }
        return one;
    }


    //使用工具 实现快速校验 减少if判断
    @ApiOperation(value = "数据校验测试2")
    @PostMapping("/testTwo")
    public TestTwo getTestTwo(@RequestBody  @Valid TestTwo two){
        return two;
    }

}
