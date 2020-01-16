package com.changyue.j2eefinal.mapper;

import com.changyue.j2eefinal.BaseTest;
import com.changyue.j2eefinal.dao.AreaMapper;
import com.changyue.j2eefinal.model.AreaDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: j2ee-final
 * @description: areaMapper 测试类
 * @author: 袁阊越
 * @create: 2019-12-18 15:33
 */
public class TestAreaMapper extends BaseTest {

    @Autowired
    private AreaMapper areaMapper;

    @Test
    public void testSelectAllArea() {

        AreaDO areaDO = new AreaDO();
        areaDO.setAreaName("号");
        List<AreaDO> areaDOS = areaMapper.selectAreaListSelective(areaDO);
        System.out.println(areaDOS);
    }

    @Test
    public void testSelectAreaByAreaName() {
        List<AreaDO> areaDOS = areaMapper.selectAreaByAreaName("和");
        System.out.println(areaDOS);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        int i = areaMapper.deleteByPrimaryKey(11);
        System.out.println(i);
    }

}
