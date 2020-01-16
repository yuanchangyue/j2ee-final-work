package com.changyue.j2eefinal.service;

import com.changyue.j2eefinal.BaseTest;
import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.model.AreaDO;
import com.changyue.j2eefinal.model.ShopCategoryDO;
import com.changyue.j2eefinal.model.ShopDO;
import com.changyue.j2eefinal.model.UserDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @program: j2ee-final
 * @description:
 * @author: 袁阊越
 * @create: 2019-12-19 14:35
 */
public class TestShopService extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testGetShopList() throws BusinessException {
        ShopDO shopDO = new ShopDO();

        AreaDO areaDO = new AreaDO();
        areaDO.setAreaId(null);

        ShopCategoryDO shopCategoryDO = new ShopCategoryDO();
        shopCategoryDO.setShopCategoryId(null);

        shopDO.setAreaDO(new AreaDO());
        shopDO.setShopCategoryDO(new ShopCategoryDO());
        shopDO.setUserDO(new UserDO());
        List<ShopDO> shopList = shopService.getShopList(shopDO);
        System.out.println(shopList);
    }

    @Test
    public void testAddShop() throws BusinessException {
        ShopDO shopDO = new ShopDO();
        shopDO.setShopImg("ces");
        shopDO.setShopName("ce");
        shopDO.setPhone("13101098722");
        shopDO.setShopDesc("test");
        shopDO.setCreateTime(new Date());
        shopDO.setShopAddr("chongqing");
        shopDO.setPriority(1);
        shopDO.setEnableStatus(1);

        UserDO userDO = new UserDO();
        userDO.setUserId(35);

        AreaDO areaDO = new AreaDO();
        areaDO.setAreaId(3);

        ShopCategoryDO shopCategoryDO = new ShopCategoryDO();
        shopCategoryDO.setShopCategoryId(15);

        shopDO.setAreaDO(areaDO);
        shopDO.setShopCategoryDO(shopCategoryDO);
        shopDO.setUserDO(userDO);

        shopService.registerShop(shopDO);
    }

    @Test
    public void update() throws BusinessException {
        ShopDO shopDO = new ShopDO();
        shopDO.setShopId(34);
        shopDO.setShopName("ce123123");
        shopDO.setPhone("13101098722");
        shopDO.setShopDesc("test");
        shopDO.setCreateTime(new Date());
        shopDO.setShopAddr("chongqing");
        shopDO.setPriority(1);
        shopDO.setEnableStatus(1);

        UserDO userDO = new UserDO();
        userDO.setUserId(35);

        AreaDO areaDO = new AreaDO();
        areaDO.setAreaId(3);

        ShopCategoryDO shopCategoryDO = new ShopCategoryDO();
        shopCategoryDO.setShopCategoryId(15);

        shopDO.setAreaDO(areaDO);
        shopDO.setShopCategoryDO(shopCategoryDO);
        shopDO.setUserDO(userDO);

        shopService.updateShop(shopDO);
    }

}
