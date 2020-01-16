package com.changyue.j2eefinal.controller;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.AreaDO;
import com.changyue.j2eefinal.model.ShopCategoryDO;
import com.changyue.j2eefinal.model.ShopDO;
import com.changyue.j2eefinal.model.UserDO;
import com.changyue.j2eefinal.response.CommonReturnType;
import com.changyue.j2eefinal.service.AreaService;
import com.changyue.j2eefinal.service.ShopCategoryService;
import com.changyue.j2eefinal.service.ShopService;
import com.changyue.j2eefinal.service.model.UserVO;
import com.changyue.j2eefinal.utils.ImageUpload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: j2ee-final
 * @description: 商铺控制层
 * @author: 袁阊越
 * @create: 2019-12-18 22:06
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopController extends BaseController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;

    @GetMapping("/shop")
    public String login() {
        return "shop";
    }

    @PostMapping("/getshoplist")
    @ResponseBody
    public CommonReturnType getShopAll(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                       @RequestParam(value = "shopCondition", required = false) String shopCondition,
                                       HttpServletRequest request) throws BusinessException {

        ShopDO shopDO;

        //获取前端传递来的值
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            shopDO = objectMapper.readValue(shopCondition, ShopDO.class);
        } catch (Exception e) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        //整合查询条件
        integrateShop(request, shopDO);

        PageHelper.startPage(pn, 5);

        List<ShopDO> shopList = shopService.getShopList(shopDO);

        if (shopList.isEmpty()) {
            throw new BusinessException(BusinessStateEnum.AREA_IS_NULL);
        }
        PageInfo<ShopDO> shopPageInfo = new PageInfo<>(shopList, 3);

        Map<String, Object> modelMap = new HashMap<>();

        ShopCategoryDO shopCategoryWithUserId = new ShopCategoryDO();
        shopCategoryWithUserId.setUserId(shopDO.getUserDO().getUserId());
        List<ShopCategoryDO> shopCategory = shopCategoryService.getShopList(shopCategoryWithUserId);

        List<AreaDO> areaList = areaService.getAreaListSelective(new AreaDO());

        modelMap.put("shopCategory", shopCategory);
        modelMap.put("ares", areaList);
        modelMap.put("shopPageInfo", shopPageInfo);

        return CommonReturnType.create(modelMap);
    }

    @GetMapping("/shop/{id}")
    @ResponseBody
    public CommonReturnType getShopById(@PathVariable Integer id) throws BusinessException {

        if (id == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        ShopDO shopByShopId = shopService.getShopByShopId(id);

        return CommonReturnType.create(shopByShopId);
    }

    @DeleteMapping("/shop/{id}")
    @ResponseBody
    public CommonReturnType deleteShopById(@PathVariable Integer id) throws BusinessException {

        if (id == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        shopService.deleteShopByShopId(id);
        return CommonReturnType.create(null);
    }

    @PostMapping("/shop")
    @ResponseBody
    public CommonReturnType registerShop(HttpServletRequest request) throws BusinessException {

        String shopInfo = request.getParameter("shopInfo");

        ShopDO shopDO;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            shopDO = objectMapper.readValue(shopInfo, ShopDO.class);
        } catch (Exception e) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        String uploadImgPath = ImageUpload.uploadImg(request, shopDO.getShopName());
        shopDO.setShopImg(uploadImgPath);

        //整合商铺信息
        integrateShop(request, shopDO);

        shopService.registerShop(shopDO);

        return CommonReturnType.create(null);
    }

    @PostMapping("/modifyshop")
    @ResponseBody
    public CommonReturnType modifyShop(HttpServletRequest request) throws BusinessException {

        String shopInfo = request.getParameter("shopInfo");

        ShopDO shopDO;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            shopDO = objectMapper.readValue(shopInfo, ShopDO.class);
        } catch (Exception e) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        //整合商铺信息
        integrateShop(request, shopDO);

        shopService.updateShop(shopDO);

        return CommonReturnType.create(null);
    }


    @GetMapping("/shopbyname")
    @ResponseBody
    private CommonReturnType getShopByName(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @RequestParam("shopName") String shopName) throws BusinessException {

        ShopDO shopDO = new ShopDO();
        shopDO.setShopName(shopName);

        PageHelper.startPage(pn, 5);

        List<ShopDO> shopList = shopService.getShopList(shopDO);

        if (shopList.isEmpty()) {
            throw new BusinessException(BusinessStateEnum.AREA_IS_NULL);
        }
        PageInfo<ShopDO> shopPageInfo = new PageInfo<>(shopList, 3);

        return CommonReturnType.create(shopPageInfo);
    }

    @GetMapping("/getshopcount")
    @ResponseBody
    private CommonReturnType getShopCount(HttpServletRequest request) throws BusinessException {
        UserVO loginUser = (UserVO) request.getSession().getAttribute("LOGIN_USER");
        int count = shopService.getShopCount(loginUser.getUserId());
        return CommonReturnType.create(count);
    }

    /**
     * 整合Shop的信息
     *
     * @param request 请求域
     * @param shopDO  商铺信息
     */
    private void integrateShop(HttpServletRequest request, ShopDO shopDO) {

        UserVO loginUser = (UserVO) request.getSession().getAttribute("LOGIN_USER");
        String categoryFromReq = request.getParameter("categoryId");
        String areaFromReq = request.getParameter("areaId");
        String shopIdFromReq = request.getParameter("shopId");

        AreaDO areaDO = new AreaDO();
        UserDO userDO = new UserDO();
        ShopCategoryDO shopCategoryDO = new ShopCategoryDO();

        if (!StringUtils.isEmpty(areaFromReq)) {
            int areaCondition = Integer.parseInt(areaFromReq);
            areaDO.setAreaId(areaCondition);
        }

        if (!StringUtils.isEmpty(shopIdFromReq)) {
            int shopId = Integer.parseInt(shopIdFromReq);
            shopDO.setShopId(shopId);
        }

        if (!StringUtils.isEmpty(categoryFromReq)) {
            int categoryCondition = Integer.parseInt(categoryFromReq);
            shopCategoryDO.setShopCategoryId(categoryCondition);
        }

        if (loginUser != null && loginUser.getUserId() != null) {
            userDO.setUserId(loginUser.getUserId());
        }

        shopDO.setAreaDO(areaDO);
        shopDO.setUserDO(userDO);
        shopDO.setShopCategoryDO(shopCategoryDO);

    }

}
