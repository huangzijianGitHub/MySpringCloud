package com.hzj.ribbon.consumer.ribbonconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.hzj.ribbon.consumer.ribbonconsumer.entity.User;
import com.hzj.ribbon.consumer.ribbonconsumer.entity.UserTable;
import com.hzj.ribbon.consumer.ribbonconsumer.service.SpringUserService;
import com.hzj.ribbon.consumer.ribbonconsumer.utils.UUIDGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by huangzijian on 2018-01-17
 */
@Api(tags = "ribbon 接口")
@RestController
public class RibbonController {

    @Autowired
    private SpringUserService userService;


    @ApiOperation(value = "ribbon say hello接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "String",paramType = "query")
    })
    @GetMapping("/hi/{id}")
//    @HystrixCommand(fallbackMethod = "fallbackMe")
    public User getUser(@PathVariable String id){
        System.out.println("收到的id是："+id);
        User user=new User();
        try {
            user=userService.getUserById(id);
            System.out.println("查询结果数据为："+user.toString());
        }catch (Exception err){
            System.out.println("err："+err.getMessage());
        }
        return user;
    }

    @ApiOperation(value = "ribbon say localhello接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid",value = "用户id",required = true,dataType = "String",paramType = "query")
    })
    @GetMapping("/sayhi/{userid}")
    @HystrixCommand(fallbackMethod = "fallbackMe")
    public UserTable getLocalUser(@PathVariable String userid){
        System.out.println("收到的userid是："+userid);
        UserTable userTable=new UserTable();
        try {
             userTable=userService.getUserLocalById(userid);
            System.out.println("查询结果数据为："+userTable.toString());
        }catch (Exception err){
            System.out.println("err："+err.getMessage());
        }
        return userTable;
    }

    /**
     * 熔断器
     */
    public UserTable fallbackMe(String id){
        UserTable user = new UserTable();
        user.setUserid(UUIDGenerator.generate18());
        user.setName("服务发生断路");
        user.setAge("断开");
        user.setPort("disconnection");
        return user;
    }


}
