package com.changyue.j2eefinal.mapper;

import com.changyue.j2eefinal.BaseTest;
import com.changyue.j2eefinal.dao.ProductCategoryMapper;
import com.changyue.j2eefinal.dao.ProductMapper;
import com.changyue.j2eefinal.model.ProductCategoryDO;
import com.changyue.j2eefinal.model.ProductDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: j2ee-final
 * @description: areaMapper 测试类
 * @author: 袁阊越
 * @create: 2019-12-18 15:33
 */
public class TestProductCategoryMapper extends BaseTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void testSelectList() {
        List<ProductCategoryDO> productCategoryDOS = productCategoryMapper.selectList(new ProductCategoryDO());
        productCategoryDOS.forEach(System.out::println);
    }

    @Test
    public void test() {
        int i = productCategoryMapper.deleteByPrimaryKey(16);
        System.out.println(i);
    }
}
