package com.changyue.j2eefinal.mapper;

import com.changyue.j2eefinal.BaseTest;
import com.changyue.j2eefinal.dao.UserMapper;
import com.changyue.j2eefinal.model.UserDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @program: j2ee-final
 * @description:
 * @author: 袁阊越
 * @create: 2019-12-17 15:35
 */
public class TestUserDOMapper extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectByPrimaryKey() {
        UserDO userDO = userMapper.selectByPrimaryKey(1);
        System.out.println(userDO);
    }

    @Test
    public void testInsert() {
        UserDO userDO = new UserDO();
        userDO.setBirthday(new Date());
        userDO.setCreateTime(new Date());
        userDO.setName("张三");
        userDO.setEmail("604028466@qq.com");
        userDO.setPassword("123123");
        userDO.setLastEditTime(new Date());
        userDO.setProfileImg("img");
        userDO.setSalt("salt");
        userDO.setEnableStatus(1);
        userDO.setPhone("12123123123");
        userDO.setGender("男");
        int i = userMapper.insertSelective(userDO);
        Assert.assertEquals(i, 1);
    }

    @Test
    public void test() {
        UserDO userDO = userMapper.selectByPhone("13024171189");
        System.out.println("userDO = " + userDO);
    }

    @Test
    public void testUpdatePassword() {
        int i = userMapper.updatePassword(37, "123", "123");
        System.out.println("i = " + i);
    }


}
