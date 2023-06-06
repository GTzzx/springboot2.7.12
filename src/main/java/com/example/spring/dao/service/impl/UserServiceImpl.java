package com.example.spring.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring.common.utils.CloneUtils;
import com.example.spring.dao.entity.User;
import com.example.spring.dao.service.UserService;
import com.example.spring.dao.mapper.UserMapper;

import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-05-25 15:53:02
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    @Override
    public User getOneByName(String name) {
        User one = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, name)
                .orderBy(true, false, User::getId)
                .last("limit 1"));
        User clone = CloneUtils.clone(one, User.class);
        return clone;

    }
}




