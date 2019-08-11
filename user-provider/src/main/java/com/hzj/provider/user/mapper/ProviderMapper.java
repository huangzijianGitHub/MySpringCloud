package com.hzj.provider.user.mapper;

import com.hzj.provider.user.entity.UserTable;
import org.springframework.stereotype.Repository;

/*
 * @author <a>huangzijian</a>
 * @version 1.0, 2019-04-23
 * @description 
 */
@Repository
public interface ProviderMapper {
    /**
     *@Author:<a>huangzijian</a>
     *@param:userid
     *@Description:获取user信息
     *@Date 11:14 2019-04-23
     */
    UserTable getUser(String userid);
}
