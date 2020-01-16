package com.changyue.j2eefinal.service;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.model.ProductCategoryDO;

import java.util.List;

/**
 * @program: j2ee-final
 * @description: 商品类别业务接口
 * @author: 袁阊越
 * @create: 2019-12-28 22:30
 */
public interface ProductCategoryService {
    /**
     * 根据商品类别id删除
     *
     * @param productCategoryId 商品类别id
     * @return 影响
     * @throws BusinessException 异常
     */
    int deleteByProductCategoryId(Integer productCategoryId) throws BusinessException;

    /**
     * 插入商品类别
     *
     * @param productCategoryDO 商品类别信息
     */
    void addProductCategory(ProductCategoryDO productCategoryDO) throws BusinessException;

    /**
     * 选择性查询
     *
     * @param productCategoryId 商品类别id
     * @return 商品类别信息
     */
    ProductCategoryDO queryProductCategoryById(Integer productCategoryId) throws BusinessException;

    /**
     * 更新商品
     *
     * @param productCategoryDO 商品类别信息
     * @return 影响
     */
    int modifyProductCategory(ProductCategoryDO productCategoryDO) throws BusinessException;


    /**
     * 查询商铺类别list
     *
     * @param productCategoryDO 商品类别信息
     * @return 商铺类别list
     */
    List<ProductCategoryDO> queryProductCategoryList(ProductCategoryDO productCategoryDO) throws BusinessException;

}
