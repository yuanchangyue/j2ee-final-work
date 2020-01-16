package com.changyue.j2eefinal.controller;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.ProductCategoryDO;
import com.changyue.j2eefinal.model.ProductDO;
import com.changyue.j2eefinal.model.ShopDO;
import com.changyue.j2eefinal.model.UserDO;
import com.changyue.j2eefinal.response.CommonReturnType;
import com.changyue.j2eefinal.service.ProductCategoryService;
import com.changyue.j2eefinal.service.ProductService;
import com.changyue.j2eefinal.service.ShopService;
import com.changyue.j2eefinal.service.model.UserVO;
import com.changyue.j2eefinal.utils.ImageUpload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * @description: 商品控制层
 * @author: 袁阊越
 * @create: 2019-12-29 00:09
 */
@Controller
@RequestMapping("/shopadmin")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/toproduct")
    public String toProduct() {
        return "product";
    }

    @PostMapping("/getproductlist")
    @ResponseBody
    public CommonReturnType getProductAll(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                          HttpServletRequest request) throws BusinessException {
        ProductDO productDO = new ProductDO();

        //整合查询条件
        integrateShop(request, productDO);

        PageHelper.startPage(pn, 5);

        List<ProductDO> productList = productService.queryProductList(productDO);

        if (productList.isEmpty()) {
            throw new BusinessException(BusinessStateEnum.SEARCH_NULL);
        }
        PageInfo<ProductDO> productPageInfo = new PageInfo<>(productList, 3);

        Map<String, Object> modelMap = new HashMap<>();
        List<ShopDO> shopList = shopService.getShopList(productDO.getShopDO());

        modelMap.put("shopList", shopList);
        modelMap.put("productPageInfo", productPageInfo);

        return CommonReturnType.create(modelMap);
    }

    @PostMapping("/product")
    @ResponseBody
    public CommonReturnType registerProduct(HttpServletRequest request) throws BusinessException {

        String productInfo = request.getParameter("productInfo");

        ProductDO productDO;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productDO = objectMapper.readValue(productInfo, ProductDO.class);
        } catch (Exception e) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        String uploadImgPath = ImageUpload.uploadImg(request, productDO.getProductName());
        productDO.setImgAddr(uploadImgPath);

        //整合商铺信息
        integrateShop(request, productDO);

        productService.addProduct(productDO);

        return CommonReturnType.create(null);
    }

    @GetMapping("/product/{id}")
    @ResponseBody
    public CommonReturnType getShopById(@PathVariable Integer id) throws BusinessException {

        if (id == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_NULL);
        }

        ProductDO productDO = productService.selectProductByProductId(id);
        if (productDO == null) {
            throw new BusinessException(BusinessStateEnum.SEARCH_NULL);
        }

        return CommonReturnType.create(productDO);
    }

    @PostMapping("/modifyproduct")
    @ResponseBody
    public CommonReturnType modifyProduct(HttpServletRequest request) throws BusinessException {

        String productInfo = request.getParameter("productInfo");

        ProductDO productDO;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productDO = objectMapper.readValue(productInfo, ProductDO.class);
        } catch (Exception e) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        //整合商铺信息
        integrateShop(request, productDO);

        productService.modifyProduct(productDO);

        return CommonReturnType.create(null);
    }

    @DeleteMapping("/product/{id}")
    @ResponseBody
    public CommonReturnType deleteShopById(@PathVariable Integer id) throws BusinessException {

        if (id == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        productService.deleteByProductId(id);

        return CommonReturnType.create(null);
    }


    /**
     * 整合商品的信息
     *
     * @param request   请求域
     * @param productDO 商铺信息
     */
    private void integrateShop(HttpServletRequest request, ProductDO productDO) {

        UserVO loginUser = (UserVO) request.getSession().getAttribute("LOGIN_USER");
        String categoryFromReq = request.getParameter("categoryId");
        String shopIdFromReq = request.getParameter("shopId");
        String productName = request.getParameter("productName");


        ShopDO shopDO = new ShopDO();
        ProductCategoryDO productCategoryDO = new ProductCategoryDO();
        UserDO userDO = new UserDO();

        if (!StringUtils.isEmpty(productName)) {
            productDO.setProductName(productName);
        }

        if (!StringUtils.isEmpty(categoryFromReq)) {
            int id = Integer.parseInt(categoryFromReq);
            productCategoryDO.setProductCategoryId(id);
        }

        if (!StringUtils.isEmpty(shopIdFromReq)) {
            int id = Integer.parseInt(shopIdFromReq);
            shopDO.setShopId(id);
        }
        if (loginUser != null && loginUser.getUserId() != null) {
            userDO.setUserId(loginUser.getUserId());
        }
        productDO.setShopDO(shopDO);
        productDO.setProductCategoryDO(productCategoryDO);
        productDO.setUserDO(userDO);
    }


}
