package com.changyue.j2eefinal.service.impl;

import com.changyue.j2eefinal.dao.ShopCategoryMapper;
import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.ShopCategoryDO;
import com.changyue.j2eefinal.service.ShopCategoryService;
import com.changyue.j2eefinal.validator.ValidatorImpl;
import com.changyue.j2eefinal.validator.ValidatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program: j2ee-final
 * @description: 商铺类别管理业务实现类
 * @author: 袁阊越
 * @create: 2019-12-19 21:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopCategoryImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryMapper shopCategoryMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public int deleteByShopCategoryId(Integer shopCategoryId) throws BusinessException {
        if (shopCategoryId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        return shopCategoryMapper.deleteByPrimaryKey(shopCategoryId);
    }

    @Override
    public void registerShopCategory(ShopCategoryDO shopCategoryDO) throws BusinessException {

        if (shopCategoryDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        shopCategoryDO.setCreateTime(new Date());
        shopCategoryDO.setLastEditTime(new Date());

        //校验入参
        ValidatorResult validator = this.validator.validator(shopCategoryDO);
        if (validator.isHasError()) {
            throw new BusinessException(validator.getErrMsg(), BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        shopCategoryMapper.insertSelective(shopCategoryDO);
    }

    @Override
    public ShopCategoryDO getByShopCategoryId(Integer shopCategoryId) throws BusinessException {
        if (shopCategoryId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        return shopCategoryMapper.selectByPrimaryKey(shopCategoryId);
    }

    @Override
    public int updateShopCategory(ShopCategoryDO shopCategoryDO) throws BusinessException {
        if (shopCategoryDO == null || shopCategoryDO.getShopCategoryId() == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        shopCategoryDO.setLastEditTime(new Date());
        return shopCategoryMapper.updateByPrimaryKeySelective(shopCategoryDO);
    }

    @Override
    public List<ShopCategoryDO> getShopList(ShopCategoryDO shopCategoryDO) throws BusinessException {
        if (shopCategoryDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        return shopCategoryMapper.selectShopList(shopCategoryDO);
    }

}
