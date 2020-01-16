package com.changyue.j2eefinal.service;

import com.changyue.j2eefinal.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: j2ee-final
 * @description:
 * @author: 袁阊越
 * @create: 2019-12-19 14:35
 */
public class TestAreaService extends BaseTest {
    @Autowired
    private AreaService areaService;

    @Test
    public void test() {
        int i = areaService.deleteByAreaId(10);
        System.out.println(i);
    }


}
