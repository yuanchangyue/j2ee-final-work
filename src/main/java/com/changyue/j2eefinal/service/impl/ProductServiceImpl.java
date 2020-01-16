package com.changyue.j2eefinal.service.impl;

import com.changyue.j2eefinal.dao.ProductMapper;
import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.ProductDO;
import com.changyue.j2eefinal.service.ProductService;
import com.changyue.j2eefinal.validator.ValidatorImpl;
import com.changyue.j2eefinal.validator.ValidatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program: j2ee-final
 * @description: 商品业务接口实现类
 * @author: 袁阊越
 * @create: 2019-12-29 00:19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ValidatorImpl validator;


    @Override
    public int deleteByProductId(Integer productId) throws BusinessException {
        if (productId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }
        return productMapper.deleteByPrimaryKey(productId);
    }

    @Override
    public void addProduct(ProductDO productDO) throws BusinessException {
        if (productDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }

        ValidatorResult validator = this.validator.validator(ProductDO.class);
        if (validator.isHasError()) {
            throw new BusinessException(validator.getErrMsg(), BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        productDO.setCreateTime(new Date());
        productDO.setLastEditTime(new Date());
        productDO.setEnableStatus(1);

        productMapper.insert(productDO);
    }

    @Override
    public ProductDO selectProductByProductId(Integer productId) throws BusinessException {
        if (productId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public void modifyProduct(ProductDO productDO) throws BusinessException {
        if (productDO == null || productDO.getProductId() == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }
        productMapper.updateByPrimaryKeySelective(productDO);
    }

    @Override
    public List<ProductDO> queryProductList(ProductDO productDO) throws BusinessException {
        if (productDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }
        return productMapper.selectProductList(productDO);
    }

}
