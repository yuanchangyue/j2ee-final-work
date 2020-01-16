package com.changyue.j2eefinal.service;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.model.UserDO;
import com.changyue.j2eefinal.service.model.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @program: j2ee-final
 * @description: 测试userService
 * @author: 袁阊越
 * @create: 2019-12-17 20:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml", "classpath:dispatcher-server.xml", "classpath:mybatis-config.xml"})
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testQueryUserByUserId() {
        try {
            UserVO userVO = userService.queryUserByUserId(1);
            System.out.println(userVO);
        } catch (BusinessException e) {
            System.out.println("e.getErrCode() = " + e.getErrCode());
            System.out.println("e.getErrMsg() = " + e.getErrMsg());
        }
    }

    @Test
    public void testRegisterUser() {
        UserDO userDO = new UserDO();
        userDO.setName("1233");
        userDO.setEmail("604028466@qq.com");
        userDO.setEnableStatus(1);
        userDO.setPassword("123123");
        userDO.setPhone("13024171189");
        userDO.setProfileImg("123123");
        userDO.setGender("男");
        userDO.setBirthday(new Date());
        userDO.setCreateTime(new Date());
        userDO.setLastEditTime(new Date());
        try {
            userService.registerUser(userDO);
        } catch (BusinessException e) {
            System.out.println("e.getErrCode() = " + e.getErrCode());
            System.out.println("e.getErrMsg() = " + e.getErrMsg());
        }
    }

    @Test
    public void testCheckPassword() {
        try {
            UserVO userVO = userService.checkPassword("123123", "");
            System.out.println(userVO);
        } catch (BusinessException e) {
            System.out.println("e.getErrCode() = " + e.getErrCode());
            System.out.println("e.getErrMsg() = " + e.getErrMsg());
        }
    }

}
