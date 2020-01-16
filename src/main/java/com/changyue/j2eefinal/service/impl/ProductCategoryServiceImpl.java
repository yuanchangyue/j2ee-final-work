package com.changyue.j2eefinal.service.impl;

import com.changyue.j2eefinal.dao.ProductCategoryMapper;
import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.ProductCategoryDO;
import com.changyue.j2eefinal.service.ProductCategoryService;
import com.changyue.j2eefinal.validator.ValidatorImpl;
import com.changyue.j2eefinal.validator.ValidatorResult;
import org.mybatis.generator.codegen.ibatis2.dao.templates.IbatisDAOTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program: j2ee-final
 * @description: 商品类别业务实现类
 * @author: 袁阊越
 * @create: 2019-12-28 22:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public int deleteByProductCategoryId(Integer productCategoryId) throws BusinessException {
        if (productCategoryId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }
        return productCategoryMapper.deleteByPrimaryKey(productCategoryId);
    }

    @Override
    public void addProductCategory(ProductCategoryDO productCategoryDO) throws BusinessException {
        if (productCategoryDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }

        ValidatorResult validator = this.validator.validator(ProductCategoryDO.class);
        if (validator.isHasError()) {
            throw new BusinessException(validator.getErrMsg(), BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        productCategoryDO.setCreateTime(new Date());
        productCategoryDO.setLastEditTime(new Date());

        productCategoryMapper.insert(productCategoryDO);
    }

    @Override
    public ProductCategoryDO queryProductCategoryById(Integer productCategoryId) throws BusinessException {
        if (productCategoryId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }
        return productCategoryMapper.selectByPrimaryKey(productCategoryId);
    }

    @Override
    public int modifyProductCategory(ProductCategoryDO productCategoryDO) throws BusinessException {
        if (productCategoryDO == null || productCategoryDO.getProductCategoryId() == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }
        return productCategoryMapper.updateByPrimaryKeySelective(productCategoryDO);
    }

    @Override
    public List<ProductCategoryDO> queryProductCategoryList(ProductCategoryDO productCategoryDO) throws BusinessException {
        if (productCategoryDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }
        return productCategoryMapper.selectList(productCategoryDO);
    }
}
