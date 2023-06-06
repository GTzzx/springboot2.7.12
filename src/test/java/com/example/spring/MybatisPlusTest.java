package com.example.spring;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import com.example.spring.dao.entity.User;
import com.example.spring.dao.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class MybatisPlusTest {
    /**
     * mybatisplus 代码生成器使用
     */
    @Test
    public void testMP(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/minzu", "root", "root")
                .globalConfig(builder -> {
                    builder.author("zzxGt") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("generator"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.TINYINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .moduleName("spring") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "generator")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .addTablePrefix("sys_") // 设置过滤表前缀
                            .entityBuilder()//实体类配置
                            .enableFileOverride()//文件自动覆盖
                            .enableLombok()//开启lombok
                            .mapperBuilder()//mapper配置
                            .enableBaseResultMap()//生成基础查询语句
                            .controllerBuilder()
                            .enableRestStyle()//开启rest风格
                            .enableFileOverride()//覆盖

                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

    /**
     * mybatisplus 查询实现
     */
    @Resource
    UserServiceImpl userService;
    @Test
    public void get(){
        List<User> list = userService.list();
        for (User user : list) {
            System.out.println(user.toString());
        }
    }


}
