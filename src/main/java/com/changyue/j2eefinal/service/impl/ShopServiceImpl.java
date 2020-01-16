package com.changyue.j2eefinal.service.impl;

import com.changyue.j2eefinal.dao.ShopMapper;
import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.ShopDO;
import com.changyue.j2eefinal.service.ShopService;
import com.changyue.j2eefinal.validator.ValidatorImpl;
import com.changyue.j2eefinal.validator.ValidatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program: j2ee-final
 * @description: 商铺业务接口实现类
 * @author: 袁阊越
 * @create: 2019-12-19 21:10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public void deleteShopByShopId(Integer shopId) throws BusinessException {
        if (shopId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        shopMapper.deleteByPrimaryKey(shopId);
    }

    @Override
    public void registerShop(ShopDO shopDO) throws BusinessException {
        if (shopDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        shopDO.setEnableStatus(1);
        ValidatorResult validator = this.validator.validator(shopDO);
        if (validator.isHasError()) {
            throw new BusinessException(validator.getErrMsg(), BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        shopDO.setCreateTime(new Date());
        shopDO.setLastEditTime(new Date());
        shopMapper.insertSelective(shopDO);
    }

    @Override
    public ShopDO getShopByShopId(Integer shopId) throws BusinessException {
        if (shopId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        return shopMapper.selectByPrimaryKey(shopId);
    }

    @Override
    public void updateShop(ShopDO shopDO) throws BusinessException {
        if (shopDO == null || shopDO.getShopId() == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        shopMapper.updateByPrimaryKeySelective(shopDO);
    }

    @Override
    public List<ShopDO> getShopList(ShopDO shopDO) throws BusinessException {
        return shopMapper.selectShopList(shopDO);
    }

    @Override
    public int getShopCount(Integer userId) throws BusinessException {
        if (userId == null ) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        return shopMapper.countShop(userId);
    }

}
