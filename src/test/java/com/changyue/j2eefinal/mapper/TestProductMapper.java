package com.changyue.j2eefinal.mapper;

import com.changyue.j2eefinal.BaseTest;
import com.changyue.j2eefinal.dao.AreaMapper;
import com.changyue.j2eefinal.dao.ProductMapper;
import com.changyue.j2eefinal.model.*;
import com.changyue.j2eefinal.service.model.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: j2ee-final
 * @description: areaMapper 测试类
 * @author: 袁阊越
 * @create: 2019-12-18 15:33
 */
public class TestProductMapper extends BaseTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testSelectAllArea() {
        ProductDO productDO = new ProductDO();
        ShopDO shopDO = new ShopDO();
        ProductCategoryDO productCategoryDO = new ProductCategoryDO();
        UserDO userDO = new UserDO();
        shopDO.setShopId(19);

        productDO.setShopDO(shopDO);
        productDO.setProductCategoryDO(productCategoryDO);
        productDO.setUserDO(userDO);

        List<ProductDO> productDOS = productMapper.selectProductList(productDO);
        productDOS.forEach(System.out::println);
    }

    @Test
    public void testSelectByPrimaryKey() {
        ProductDO productDO = productMapper.selectByPrimaryKey(30);
        System.out.println(productDO);
    }

}
