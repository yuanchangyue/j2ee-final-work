package com.changyue.j2eefinal.service;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.model.ProductDO;

import java.util.List;

/**
 * @program: j2ee-final
 * @description: 商品业务层
 * @author: 袁阊越
 * @create: 2019-12-29 00:13
 */
public interface ProductService {
    /**
     * 根据商品id 删除商品
     *
     * @param productId 商品id
     * @return 影响
     */
    int deleteByProductId(Integer productId) throws BusinessException;

    /**
     * 选择性插入
     *
     * @param productDO 商品信息
     */
    void addProduct(ProductDO productDO) throws BusinessException;


    /**
     * 根据商品id 查询商品信息
     *
     * @param productId 商品id
     * @return 商品信息
     */
    ProductDO selectProductByProductId(Integer productId) throws BusinessException;

    /**
     * 选择性更新商品
     *
     * @param productDO 商品信息
     */
    void modifyProduct(ProductDO productDO) throws BusinessException;


    /**
     * 查询商品
     *
     * @param productDO 商品信息
     * @return 商品列表
     */
    List<ProductDO> queryProductList(ProductDO productDO) throws BusinessException;
}
