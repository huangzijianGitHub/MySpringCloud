package com.hzj.provider.user.controller;

import com.hzj.provider.user.entity.User;
import com.hzj.provider.user.entity.UserTable;
import com.hzj.provider.user.mapper.ProviderMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by huangzijian on 2018-01-17
 */
@RestController
public class UserController {

    @Value("${server.port}")
    String port;
    @Autowired
    private ProviderMapper providerMapper;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
        System.out.println("获取的id值为："+id);
        User user = new User();
        user.setName("张三");
        user.setAge("19");
        user.setPort(port);
       UserTable userTable= providerMapper.getUser(id.toString());
        System.out.println("获取的usertable值为："+userTable.toString());
        return user;
    }
}
