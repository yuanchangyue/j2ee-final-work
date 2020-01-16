package com.changyue.j2eefinal.service;

import com.changyue.j2eefinal.BaseTest;
import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.model.ShopCategoryDO;
import com.changyue.j2eefinal.model.UserDO;
import com.changyue.j2eefinal.service.model.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @program: j2ee-final
 * @description: 测试userService
 * @author: 袁阊越
 * @create: 2019-12-17 20:18
 */

public class TestShopCategoryService extends BaseTest {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Test
    public void testGetShopList() throws BusinessException {
        ShopCategoryDO shopCategoryDO = new ShopCategoryDO();
        shopCategoryDO.setUserId(34);
        List<ShopCategoryDO> shopList = shopCategoryService.getShopList(shopCategoryDO);
        System.out.println(shopList);
    }
}
