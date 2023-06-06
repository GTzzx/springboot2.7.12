package com.example.spring.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName user
 */
@TableName(value ="user")
@Data
public class TestTwo implements Serializable {
    private Integer id;

    private String email;

    private String password;

    private String username;

    private static final long serialVersionUID = 1L;
}