package com.changyue.j2eefinal.mapper;

import com.changyue.j2eefinal.BaseTest;
import com.changyue.j2eefinal.dao.ShopMapper;
import com.changyue.j2eefinal.model.AreaDO;
import com.changyue.j2eefinal.model.ShopDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @program: j2ee-final
 * @description: shopmapper 测试类
 * @author: 袁阊越
 * @create: 2019-12-18 15:33
 */
public class TestShopMapper extends BaseTest {
    @Autowired
    private ShopMapper shopMapper;

    @Test
    public void testProduct() {
        ShopDO shopDO1 = new ShopDO();
        AreaDO areaDO = new AreaDO();
        areaDO.setAreaId(5);
        shopDO1.setAreaDO(areaDO);
        List<ShopDO> shopDO = shopMapper.selectShopList(shopDO1);
        shopDO.forEach(System.out::println);
    }


    @Test
    public void test() throws IOException {
        ShopDO shopDO = new ShopDO();
        shopDO.setShopName("test");
        shopDO.setCreateTime(new Date());
        shopDO.setPhone("13101098722");
        AreaDO areaDO = new AreaDO();
        areaDO.setAreaName("test");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(System.out, shopDO);
    }

    @Test
    public void testCountShop() {

        System.out.println("shopMapper.countShop(1) = " + shopMapper.countShop(35));

    }
}
