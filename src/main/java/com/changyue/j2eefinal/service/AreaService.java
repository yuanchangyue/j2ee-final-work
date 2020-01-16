package com.changyue.j2eefinal.service;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.model.AreaDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: j2ee-final
 * @description: 区域业务层接口
 * @author: 袁阊越
 * @create: 2019-12-18 22:52
 */
public interface AreaService {
    /**
     * 根据主键id删除区域
     *
     * @param areaId 面积id
     * @return 影响
     */
    int deleteByAreaId(Integer areaId);

    /**
     * 插入区域
     *
     * @param areaDO 区域
     * @throws BusinessException 异常
     */
    void registerArea(AreaDO areaDO) throws BusinessException;


    /**
     * 根据区域名称查询区域
     *
     * @param areaName 区域名称
     * @return 区域信息
     */
    List<AreaDO> selectAreaByAreaName(String areaName);

    /**
     * 根据areaId查询区域
     *
     * @param areaId 区域id
     * @return 区域信息
     */
    AreaDO selectByAreaId(Integer areaId);

    /**
     * 更新区域信息 选择性的
     *
     * @param areaDO 区域信息
     * @throws BusinessException 异常
     */
    void updateByAreaIdSelective(AreaDO areaDO) throws BusinessException;


    /**
     * 获得全部的区域信息
     *
     * @param areaDO 区域
     * @return 区域信息
     */
    List<AreaDO> getAreaListSelective(AreaDO areaDO);
}
