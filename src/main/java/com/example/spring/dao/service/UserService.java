package com.example.spring.dao.service;

import com.example.spring.dao.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service
* @createDate 2023-05-25 15:53:02
*/
public interface UserService extends IService<User> {
    User getOneByName(String name);

}
