package com.changyue.j2eefinal.service;

import com.changyue.j2eefinal.BaseTest;
import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.model.ProductCategoryDO;
import com.changyue.j2eefinal.model.ProductDO;
import com.changyue.j2eefinal.model.ShopDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: j2ee-final
 * @description:
 * @author: 袁阊越
 * @create: 2019-12-19 14:35
 */
public class TestProductCategoryService extends BaseTest {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void testQueryProductList() throws BusinessException {

        ProductCategoryDO productCategoryDO = new ProductCategoryDO();
        ShopDO shopDO = new ShopDO();
        shopDO.setShopId(15);
        productCategoryDO.setShop(shopDO);

        List<ProductCategoryDO> productCategoryDOS = productCategoryService.queryProductCategoryList(productCategoryDO);
        productCategoryDOS.forEach(System.out::println);

    }

}
