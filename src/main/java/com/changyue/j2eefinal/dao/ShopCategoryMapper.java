package com.changyue.j2eefinal.dao;

import com.changyue.j2eefinal.model.ShopCategoryDO;

import java.util.List;

/**
 * @author 袁阊越
 * @title: ShopCategoryMapper
 * @package com.changyue.j2eefinal.dao
 * @description: 商铺类别数据访问接口
 * @date 2019/12/19/019
 */
public interface ShopCategoryMapper {

    /**
     * 根据商铺类别id 删除商铺类别
     *
     * @param shopCategoryId 商铺类别id
     * @return 影响
     */
    int deleteByPrimaryKey(Integer shopCategoryId);

    /**
     * 直接插入商铺类别
     *
     * @param shopCategoryDO 商铺类别信息
     * @return 影响
     */
    int insert(ShopCategoryDO shopCategoryDO);

    /**
     * 选择性插入
     *
     * @param shopCategoryDO 商铺类别信息
     * @return 影响
     */
    int insertSelective(ShopCategoryDO shopCategoryDO);

    /**
     * 根据商铺类别id 查询商铺类别信息
     *
     * @param shopCategoryId 商铺类别id
     * @return 商铺类别信息
     */
    ShopCategoryDO selectByPrimaryKey(Integer shopCategoryId);


    /**
     * 选择性更新商铺类别
     *
     * @param shopCategoryDO 商铺类别信息
     * @return 影响
     */
    int updateByPrimaryKeySelective(ShopCategoryDO shopCategoryDO);

    /**
     * 直接更新商铺类别
     *
     * @param shopCategoryDO 商铺类别信息
     * @return 影响
     */
    int updateByPrimaryKey(ShopCategoryDO shopCategoryDO);


    /**
     * 获得商品列表
     *
     * @param shopCategoryDO 商铺类别信息
     * @return 商品列表
     */
    List<ShopCategoryDO> selectShopList(ShopCategoryDO shopCategoryDO);


}