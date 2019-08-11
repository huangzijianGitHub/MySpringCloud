package com.hzj.ribbon.consumer.ribbonconsumer.service.serviceImpl;

import com.hzj.ribbon.consumer.ribbonconsumer.entity.User;
import com.hzj.ribbon.consumer.ribbonconsumer.entity.UserTable;
import com.hzj.ribbon.consumer.ribbonconsumer.mapper.UserMapper;
import com.hzj.ribbon.consumer.ribbonconsumer.service.SpringUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*
 * @author <a>huangzijian</a>
 * @version 1.0, 2019-04-23
 * @description 
 */
@Service
//@CacheConfig(cacheNames = "user")
public class SpringUserServiceImpl implements SpringUserService {
    @Autowired
    private  RestTemplate restTemplate;
    @Autowired
   private UserMapper userMapper;

    @Override
//    @Cacheable(key = "'findUser'+#p0")
    public User getUserById(String id) {
        return restTemplate.getForObject("http://USER-SERVICE/user/"+id,User.class);
    }

    @Override
//    @Cacheable(key = "'findUserTable'+#p0")
    public UserTable getUserLocalById(String userid) {
        return userMapper.getUserById(userid);
    }
}
