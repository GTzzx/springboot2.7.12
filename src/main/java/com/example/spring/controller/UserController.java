package com.example.spring.controller;


import com.example.spring.dao.entity.User;
import com.example.spring.dao.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@Api(tags = "user操作模块")
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @ApiOperation(value = "获取user列表接口")
    @GetMapping("/list")
    public List<User> getUserList(){
        if (userService.list()!=null){
            return userService.list();
        }
        return null;
    }

/**
 * @Author zzxGT
 * @Date 2023-06-08 09:5521
 * @param name
 * @return com.example.spring.dao.entity.User
 **/
    @ApiOperation(value = "查询单一user接口(name)")
    @GetMapping("one/byName")
    public User getUserOneByName(String name){
       return  userService.getOneByName(name);
    }

    @ApiOperation(value = "查询单一user接口(id)")
    @GetMapping("one/byId")
    public User getUserOneById(Integer id){
        return  userService.getById(id);
    }


}
