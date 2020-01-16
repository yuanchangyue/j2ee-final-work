package com.changyue.j2eefinal.dao;

import com.changyue.j2eefinal.model.ShopDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 袁阊越
 * @title: ShopMapper
 * @package com.changyue.j2eefinal.dao
 * @description: 商铺数据访问接口
 * @date 2019/12/19/019
 */
public interface ShopMapper {
    /**
     * 根据商铺id 删除商铺
     *
     * @param shopId 商铺id
     * @return 影响
     */
    int deleteByPrimaryKey(Integer shopId);

    /**
     * 选择性插入
     *
     * @param shopDO 商铺信息
     * @return 影响
     */
    int insertSelective(ShopDO shopDO);

    /**
     * 根据商铺id 查询商铺信息
     *
     * @param shopId 商铺id
     * @return 商铺信息
     */
    ShopDO selectByPrimaryKey(Integer shopId);

    /**
     * 选择性更新商铺
     *
     * @param shopDO 商铺信息
     * @return 影响
     */
    int updateByPrimaryKeySelective(ShopDO shopDO);

    /**
     * 获得商品列表
     *
     * @param shopDO 商铺信息
     * @return 商品列表
     */
    List<ShopDO> selectShopList(@Param("shopDO") ShopDO shopDO);

    /**
     * 查询商铺数量
     *
     * @param userId 用户id
     * @return 数量
     */
    int countShop(@Param("userId") Integer userId);

}