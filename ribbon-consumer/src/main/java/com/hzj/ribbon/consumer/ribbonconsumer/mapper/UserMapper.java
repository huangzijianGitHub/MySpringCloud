package com.hzj.ribbon.consumer.ribbonconsumer.mapper;

import com.hzj.ribbon.consumer.ribbonconsumer.entity.UserTable;
import org.springframework.stereotype.Repository;

/*
 * @author <a>huangzijian</a>
 * @version 1.0, 2019-04-23
 * @description 
 */
@Repository
public interface UserMapper {
    /**
    *@Author:<a>huangzijian</a>
    *@param:userid
    *@Description:获取user信息
    *@Date 11:14 2019-04-23
    */
    UserTable getUserById(String userid);
}
