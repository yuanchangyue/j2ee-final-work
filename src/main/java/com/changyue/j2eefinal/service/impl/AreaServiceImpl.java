package com.changyue.j2eefinal.service.impl;

import com.changyue.j2eefinal.dao.AreaMapper;
import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.AreaDO;
import com.changyue.j2eefinal.service.AreaService;
import com.changyue.j2eefinal.validator.ValidatorImpl;
import com.changyue.j2eefinal.validator.ValidatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @program: j2ee-final
 * @description: 区域业务层接口实现类
 * @author: 袁阊越
 * @create: 2019-12-18 23:01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public int deleteByAreaId(Integer areaId) {
        return areaMapper.deleteByPrimaryKey(areaId);
    }

    @Override
    public void registerArea(AreaDO areaDO) throws BusinessException {

        if (areaDO == null && areaDO.getAreaId() == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }

        //校验入参
        ValidatorResult validator = this.validator.validator(areaDO);
        if (validator.isHasError()) {
            throw new BusinessException(validator.getErrMsg(), BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        areaDO.setLastEditTime(new Date());
        areaDO.setCreateTime(new Date());

        areaMapper.insertSelective(areaDO);

    }

    @Override
    public List<AreaDO> selectAreaByAreaName(String areaName) {
        return areaMapper.selectAreaByAreaName(areaName);
    }

    @Override
    public AreaDO selectByAreaId(Integer areaId) {
        return areaMapper.selectByPrimaryKey(areaId);
    }

    @Override
    public void updateByAreaIdSelective(AreaDO areaDO) throws BusinessException {
        if (areaDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        //校验入参
        ValidatorResult validator = this.validator.validator(areaDO);
        if (validator.isHasError()) {
            throw new BusinessException(validator.getErrMsg(), BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        areaDO.setLastEditTime(new Date());

        areaMapper.updateByPrimaryKeySelective(areaDO);
    }

    @Override
    public List<AreaDO> getAreaListSelective(AreaDO areaDO) {
        return areaMapper.selectAreaListSelective(areaDO);
    }
}
