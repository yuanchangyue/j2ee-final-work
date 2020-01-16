package com.changyue.j2eefinal.dao;

import com.changyue.j2eefinal.model.ProductCategoryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 袁阊越
 * @title: ProductCategoryMapper
 * @package com.changyue.j2eefinal.dao
 * @description: 商品类别管理数据访问层
 * @date 2019/12/28/028
 */
public interface ProductCategoryMapper {

    /**
     * 根据商品类别id删除
     *
     * @param productCategoryId 商品类别id
     * @return 影响
     */
    int deleteByPrimaryKey(Integer productCategoryId);

    /**
     * 插入商品类别
     *
     * @param productCategoryDO 商品类别信息
     * @return 影响
     */
    int insert(ProductCategoryDO productCategoryDO);

    /**
     * 插入商品类别
     *
     * @param productCategoryDO 商品类别信息
     * @return 影响
     */
    int insertSelective(ProductCategoryDO productCategoryDO);

    /**
     * 选择性查询
     *
     * @param productCategoryId 商品类别id
     * @return 商品类别信息
     */
    ProductCategoryDO selectByPrimaryKey(@Param("productCategoryId") Integer productCategoryId);

    /**
     * 更新商品
     *
     * @param productCategoryDO 商品类别信息
     * @return 影响
     */
    int updateByPrimaryKeySelective(ProductCategoryDO productCategoryDO);

    /**
     * 查询商铺类别list
     *
     * @param productCategoryDO 商品类别信息
     * @return 商铺类别list
     */
    List<ProductCategoryDO> selectList(ProductCategoryDO productCategoryDO);
}