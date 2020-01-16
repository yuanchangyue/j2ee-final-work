package com.changyue.j2eefinal.controller;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.ProductCategoryDO;
import com.changyue.j2eefinal.model.ShopCategoryDO;
import com.changyue.j2eefinal.model.ShopDO;
import com.changyue.j2eefinal.model.UserDO;
import com.changyue.j2eefinal.response.CommonReturnType;
import com.changyue.j2eefinal.service.ProductCategoryService;
import com.changyue.j2eefinal.service.ShopService;
import com.changyue.j2eefinal.service.model.UserVO;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: j2ee-final
 * @description:
 * @author: 袁阊越
 * @create: 2019-12-29 16:00
 */
@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryController extends BaseController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ShopService shopService;

    @RequestMapping("/toproductcategory")
    public String toProductCategory() {
        return "productcategory";
    }

    @GetMapping("/getproductcategorylist")
    @ResponseBody
    public CommonReturnType getProductCategoryAll(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                                  @RequestParam(value = "productCategoryName", required = false) String productCategoryName,
                                                  @RequestParam(value = "shopId", required = false) Integer shopId,
                                                  HttpServletRequest request) throws BusinessException {

        UserVO loginUser = (UserVO) request.getSession().getAttribute("LOGIN_USER");

        PageHelper.startPage(pn, 5);

        ShopDO shopDO = new ShopDO();
        shopDO.setShopId(shopId);

        ProductCategoryDO productCategoryDO = new ProductCategoryDO();
        productCategoryDO.setShop(shopDO);

        if (productCategoryName != null) {
            productCategoryDO.setProductCategoryName(productCategoryName);
        }
        List<ProductCategoryDO> queryProductCategoryList = productCategoryService.queryProductCategoryList(productCategoryDO);

        if (queryProductCategoryList.isEmpty()) {
            throw new BusinessException(BusinessStateEnum.SEARCH_NULL);
        }

        PageInfo<ProductCategoryDO> shopCategoryPageInfo = new PageInfo<>(queryProductCategoryList, 3);

        UserDO userDO = new UserDO();
        userDO.setUserId(loginUser.getUserId());
        shopDO.setUserDO(userDO);
        List<ShopDO> shopList = shopService.getShopList(shopDO);

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("shopList", shopList);
        modelMap.put("shopCategoryPageInfo", shopCategoryPageInfo);

        return CommonReturnType.create(modelMap);
    }

    @DeleteMapping("/productcategory/{productCategoryId}")
    @ResponseBody
    public CommonReturnType deleteShopCategory(@PathVariable("productCategoryId") Integer productCategoryId) throws BusinessException {

        if (productCategoryId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }

        productCategoryService.deleteByProductCategoryId(productCategoryId);

        return CommonReturnType.create(null);
    }

    @PostMapping("/productcategory")
    @ResponseBody
    public CommonReturnType registerProductCategory(@RequestParam("productCategoryInfo") String productCategoryInfo, @RequestParam("shopId") Integer shopId) throws BusinessException {

        ProductCategoryDO productCategoryDO = new ProductCategoryDO();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productCategoryDO = objectMapper.readValue(productCategoryInfo, ProductCategoryDO.class);
        } catch (IOException e) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        ShopDO shopDO = new ShopDO();
        if (shopId != null) {
            shopDO.setShopId(shopId);
        }
        productCategoryDO.setShop(shopDO);

        productCategoryService.addProductCategory(productCategoryDO);

        return CommonReturnType.create(null);
    }

    @GetMapping("/productcategory/{productCategoryId}")
    @ResponseBody
    public CommonReturnType getProductCategory(@PathVariable("productCategoryId") Integer productCategoryId) throws BusinessException {

        if (productCategoryId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }

        ProductCategoryDO productCategoryDO = productCategoryService.queryProductCategoryById(productCategoryId);

        return CommonReturnType.create(productCategoryDO);
    }


    @PostMapping("/modifyproductcategory/{productCategoryId}")
    @ResponseBody
    public CommonReturnType modifyCategory(@PathVariable("productCategoryId") Integer productCategoryId, @RequestParam("productCategoryInfo") String productCategoryInfo, @RequestParam("shopId") Integer shopId) throws BusinessException {

        if (productCategoryId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }

        ProductCategoryDO productCategoryDO = new ProductCategoryDO();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productCategoryDO = objectMapper.readValue(productCategoryInfo, ProductCategoryDO.class);
        } catch (IOException e) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        ShopDO shopDO = new ShopDO();
        if (shopId != null) {
            shopDO.setShopId(shopId);
        }

        productCategoryDO.setShop(shopDO);
        productCategoryDO.setProductCategoryId(productCategoryId);

        productCategoryService.modifyProductCategory(productCategoryDO);

        return CommonReturnType.create(null);
    }

}
