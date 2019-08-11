package com.hzj.eureka.feign.feignwayconsumer.imp;

import com.hzj.eureka.feign.feignwayconsumer.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * @author <a href="mailto:huangzijian@gtmap.cn">huangzijian</a>
 * @version 1.0, 2019/1/10
 * @description 
 */
@Service
public class SchedualServiceImpl implements  SchedualService{

    @Value("${server.port}")
    String port;

    @Override
    public User sayHiFromClientOne(@PathVariable("id") Long id) {
        User user=new User();
        if (null !=id){
            user.setName("服务发生断路");
            user.setAge("断开");
            user.setPort("disconnection");
        }
        return user;
    }
}
