package com.changyue.j2eefinal.service;

import com.changyue.j2eefinal.BaseTest;
import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.model.*;
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
public class TestProductService extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testQueryProductList() throws BusinessException {

        ProductDO productDO = new ProductDO();
        /*productDO.setProductName("雪");*/
        List<ProductDO> productDOS = productService.queryProductList(productDO);
        productDOS.forEach(System.out::println);

    }

}
