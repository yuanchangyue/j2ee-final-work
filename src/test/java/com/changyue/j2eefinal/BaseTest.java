package com.changyue.j2eefinal;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @program: j2ee-final
 * @description:
 * @author: 袁阊越
 * @create: 2019-12-18 15:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml", "classpath:dispatcher-server.xml", "classpath:mybatis-config.xml"})
@WebAppConfiguration("src/main/resources")
public class BaseTest {
}
