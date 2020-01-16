package com.changyue.j2eefinal.controller;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.AreaDO;
import com.changyue.j2eefinal.response.CommonReturnType;
import com.changyue.j2eefinal.service.AreaService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @program: j2ee-final
 * @description: 区域控制层
 * @author: 袁阊越
 * @create: 2019-12-18 22:51
 */
@Controller
@RequestMapping("/shopadmin")
public class AreaController extends BaseController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/area")
    public String login() {
        return "area";
    }

    @PostMapping("/getarealist")
    @ResponseBody
    public CommonReturnType getAreaAll(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @RequestParam("areaName") String areaName) throws BusinessException {

        PageHelper.startPage(pn, 5);

        AreaDO areaDO = new AreaDO();
        areaDO.setAreaName(areaName);

        List<AreaDO> allArea = areaService.getAreaListSelective(areaDO);

        if (allArea.isEmpty()) {
            throw new BusinessException(BusinessStateEnum.AREA_IS_NULL);
        }

        PageInfo<AreaDO> areaPageInfo = new PageInfo<>(allArea, 3);

        return CommonReturnType.create(areaPageInfo);
    }

    @GetMapping("/getarea")
    @ResponseBody
    public CommonReturnType getArea(Integer areaId) throws BusinessException {
        if (areaId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        AreaDO areaDO = areaService.selectByAreaId(areaId);
        if (areaDO == null && areaDO.getAreaId() == null) {
            throw new BusinessException(BusinessStateEnum.SEARCH_NULL);
        }
        return CommonReturnType.create(areaDO);
    }

    @PostMapping("/deletearea")
    @ResponseBody
    public CommonReturnType deleteArea(Integer areaId) throws BusinessException {

        if (areaId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        areaService.deleteByAreaId(areaId);

        return CommonReturnType.create(null);
    }

    @RequestMapping("/registerarea")
    @ResponseBody
    public CommonReturnType registerArea(@RequestBody AreaDO areaDO) throws BusinessException {

        if (areaDO == null && areaDO.getAreaId() == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }

        areaService.registerArea(areaDO);

        return CommonReturnType.create(null);
    }

    @PostMapping("/modifyarea")
    @ResponseBody
    public CommonReturnType modifyArea(@RequestBody AreaDO areaDO) throws BusinessException {
        if (areaDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        areaService.updateByAreaIdSelective(areaDO);
        return CommonReturnType.create(null);
    }

}
