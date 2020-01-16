package com.changyue.j2eefinal.dao;

import com.changyue.j2eefinal.model.AreaDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 袁阊越
 * @title: AreaMapper
 * @package com.changyue.j2eefinal.dao
 * @description: 区域数据库访问层
 * @date 2019/12/18/018
 */
public interface AreaMapper {

    /**
     * 根据主键id删除区域
     *
     * @param areaId 面积id
     * @return 影响
     */
    int deleteByPrimaryKey(Integer areaId);

    /**
     * 插入区域
     *
     * @param areaDO 区域
     * @return 影响
     */
    int insert(AreaDO areaDO);

    /**
     * 选择性插入区域
     *
     * @param areaDO 区域
     * @return 影响
     */
    int insertSelective(AreaDO areaDO);

    /**
     * 根据areaId查询区域
     *
     * @param areaId 区域id
     * @return 区域信息
     */
    AreaDO selectByPrimaryKey(Integer areaId);

    /**
     * 根据区域名称查询区域
     *
     * @param areaName 区域名称
     * @return 区域信息
     */
    List<AreaDO> selectAreaByAreaName(@Param("areaName") String areaName);

    /**
     * 更新区域信息 选择性的
     *
     * @param areaDO 区域信息
     * @return 影响
     */
    int updateByPrimaryKeySelective(AreaDO areaDO);

    /**
     * 更新区域信息
     *
     * @param areaDO 区域信息
     * @return 影响
     */
    int updateByPrimaryKey(AreaDO areaDO);

    /**
     * 获得全部的区域信息
     *
     * @param areaDO 区域信息
     * @return 区域信息
     */
    List<AreaDO> selectAreaListSelective(AreaDO areaDO);
}