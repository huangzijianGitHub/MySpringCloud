package com.hzj.eureka.feign.feignwayconsumer.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.hzj.eureka.feign.feignwayconsumer.entity.User;
import com.hzj.eureka.feign.feignwayconsumer.imp.SchedualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by huangzijian on 2018-01-25
 */
@RestController
public class BaseController {

    @Autowired
    SchedualService schedualService;


    @RequestMapping(value = "/hi/{id}",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMe")
    public User sayHi(@PathVariable("id") Long id){
        return schedualService.sayHiFromClientOne(id);
    }


    /**
     * 熔断器
     */
    public User fallbackMe(Long id){
        User user = new User();
        user.setName("服务发生断路");
        user.setAge("断开");
        user.setPort("disconnection");
        System.out.print("sorry");
        return user;
    }


}
