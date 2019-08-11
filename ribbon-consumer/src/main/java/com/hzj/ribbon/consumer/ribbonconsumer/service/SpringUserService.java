package com.hzj.ribbon.consumer.ribbonconsumer.service;

import com.hzj.ribbon.consumer.ribbonconsumer.entity.User;
import com.hzj.ribbon.consumer.ribbonconsumer.entity.UserTable;


/*
 * @author <a>huangzijian</a>
 * @version 1.0, 2019-04-23
 * @description 
 */
public interface SpringUserService {
    /**
    *@Author:<a>huangzijian</a>
    *@param:id
    *@Description:获取user信息
    *@Date 11:43 2019-04-23
    */
    User getUserById(String id);

    /**
    *@Author:<a>huangzijian</a>
    *@param:userid
    *@Description:获取本地数据库
    *@Date 12:02 2019-04-23
    */
    UserTable  getUserLocalById(String userid);
}
