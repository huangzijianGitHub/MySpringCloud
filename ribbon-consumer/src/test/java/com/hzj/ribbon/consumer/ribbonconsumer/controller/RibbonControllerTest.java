package com.hzj.ribbon.consumer.ribbonconsumer.controller;

import com.hzj.ribbon.consumer.ribbonconsumer.BaseUnitTest;
import com.hzj.ribbon.consumer.ribbonconsumer.RibbonConsumerApplication;
import com.hzj.ribbon.consumer.ribbonconsumer.entity.UserTable;
import com.hzj.ribbon.consumer.ribbonconsumer.mapper.UserMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;


/*
 * @author <a>huangzijian</a>
 * @version 1.0, 2019-04-23
 * @description 
 */
@SpringBootTest(classes = RibbonConsumerApplication.class)
@WebAppConfiguration
public class RibbonControllerTest  extends BaseUnitTest{

    @Autowired
    private UserMapper userMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(RibbonControllerTest.class);
    @Test
    public void getUser() throws Exception {
        UserTable userTable=userMapper.getUserById("1");
        LOGGER.info("文件名称:"+userTable.toString());
    }

}