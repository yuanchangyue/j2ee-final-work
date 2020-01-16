package com.changyue.j2eefinal.dao;

import com.changyue.j2eefinal.model.ProductDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 袁阊越
 * @title: ProductMapper
 * @package com.changyue.j2eefinal.dao
 * @description: 商品数据访问层
 * @date 2019/12/29/029
 */
public interface ProductMapper {
    /**
     * 根据商品id 删除商品
     *
     * @param productId 商品id
     * @return 影响
     */
    int deleteByPrimaryKey(Integer productId);

    /**
     * 选择性插入
     *
     * @param productDO 商品信息
     * @return 影响
     */
    int insert(ProductDO productDO);

    /**
     * 根据商品id 查询商品信息
     *
     * @param productId 商品id
     * @return 商品信息
     */
    ProductDO selectByPrimaryKey(Integer productId);

    /**
     * 选择性更新商品
     *
     * @param productDO 商品信息
     * @return 影响
     */
    int updateByPrimaryKeySelective(ProductDO productDO);


    /**
     * 查询商品
     *
     * @param productDO 商品信息
     * @return 商品列表
     */
    List<ProductDO> selectProductList(@Param("productDO") ProductDO productDO);
}