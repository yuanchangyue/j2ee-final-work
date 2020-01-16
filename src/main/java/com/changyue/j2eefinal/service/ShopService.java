package com.changyue.j2eefinal.service;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.model.ShopDO;

import java.util.List;

/**
 * @program: j2ee-final
 * @description: 商铺业务层接口
 * @author: 袁阊越
 * @create: 2019-12-19 20:43
 */
public interface ShopService {
    /**
     * 根据商铺id 删除商铺
     *
     * @param shopId 商铺id
     */
    void deleteShopByShopId(Integer shopId) throws BusinessException;

    /**
     * 选择性插入
     *
     * @param shopDO 商铺信息
     */
    void registerShop(ShopDO shopDO) throws BusinessException;

    /**
     * 根据商铺id 查询商铺信息
     *
     * @param shopId 商铺id
     * @return 商铺信息
     */
    ShopDO getShopByShopId(Integer shopId) throws BusinessException;

    /**
     * 选择性更新商铺
     *
     * @param shopDO 商铺信息
     */
    void updateShop(ShopDO shopDO) throws BusinessException;


    /**
     * 获得商铺的list
     *
     * @param shopDO 商铺信息
     * @return 商铺列表
     */
    List<ShopDO> getShopList(ShopDO shopDO) throws BusinessException;

    /**
     * 查询商铺数量
     *
     * @param userId 用户id
     * @return 数量
     */
    int getShopCount(Integer userId) throws BusinessException;

}
