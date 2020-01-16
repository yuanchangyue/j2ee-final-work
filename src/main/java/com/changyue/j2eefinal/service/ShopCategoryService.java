package com.changyue.j2eefinal.service;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.model.ShopCategoryDO;

import java.util.List;

/**
 * @program: j2ee-final
 * @description: 商铺类别业务接口
 * @author: 袁阊越
 * @create: 2019-12-19 21:26
 */
public interface ShopCategoryService {
    /**
     * 根据商铺类别id 删除商铺类别
     *
     * @param shopCategoryId 商铺类别id
     * @return 影响
     */
    int deleteByShopCategoryId(Integer shopCategoryId) throws BusinessException;

    /**
     * 注册商铺类别
     *
     * @param shopCategoryDO 商铺类别信息
     */
    void registerShopCategory(ShopCategoryDO shopCategoryDO) throws BusinessException;

    /**
     * 根据商铺类别id 查询商铺类别信息
     *
     * @param shopCategoryId 商铺类别id
     * @return 商铺类别信息
     */
    ShopCategoryDO getByShopCategoryId(Integer shopCategoryId) throws BusinessException;

    /**
     * 选择性更新商铺类别
     *
     * @param shopCategoryDO 商铺类别信息
     * @return 影响
     */
    int updateShopCategory(ShopCategoryDO shopCategoryDO) throws BusinessException;

    /**
     * 获得商品列表
     *
     * @param shopCategoryDO 商铺类别信息
     * @return 商品列表
     */
    List<ShopCategoryDO> getShopList(ShopCategoryDO shopCategoryDO) throws BusinessException;

}
