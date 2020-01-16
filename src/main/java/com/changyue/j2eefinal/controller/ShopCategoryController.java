package com.changyue.j2eefinal.controller;

import com.changyue.j2eefinal.dao.ShopCategoryMapper;
import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.AreaDO;
import com.changyue.j2eefinal.model.ShopCategoryDO;
import com.changyue.j2eefinal.model.UserDO;
import com.changyue.j2eefinal.response.CommonReturnType;
import com.changyue.j2eefinal.service.ShopCategoryService;
import com.changyue.j2eefinal.service.model.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: j2ee-final
 * @description: 商铺类别控制层
 * @author: 袁阊越
 * @create: 2019-12-18 22:11
 */
@Controller()
@RequestMapping("/shopadmin")
public class ShopCategoryController extends BaseController {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @GetMapping("/toshopcategory")
    public String toShopCategory() {
        return "shopcategory";
    }

    @PostMapping("/getshopcategorylist")
    @ResponseBody
    public CommonReturnType getShopCategoryAll(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                               @RequestParam("shopCategoryName") String shopCategoryName,
                                               HttpServletRequest request) throws BusinessException {

        PageHelper.startPage(pn, 5);

        ShopCategoryDO shopCategoryDO = new ShopCategoryDO();
        shopCategoryDO.setShopCategoryName(shopCategoryName);

        UserVO loginUser = (UserVO) request.getSession().getAttribute("LOGIN_USER");
        shopCategoryDO.setUserId(loginUser.getUserId());

        List<ShopCategoryDO> shopCategoryList = shopCategoryService.getShopList(shopCategoryDO);

        if (shopCategoryList.isEmpty()) {
            throw new BusinessException(BusinessStateEnum.SHOP_CATEGORY_IS_NULL);
        }

        PageInfo<ShopCategoryDO> shopCategoryPageInfo = new PageInfo<>(shopCategoryList, 3);

        return CommonReturnType.create(shopCategoryPageInfo);
    }

    @DeleteMapping("/shopcategory/{shopCategoryId}")
    @ResponseBody
    public CommonReturnType deleteShopCategory(@PathVariable("shopCategoryId") Integer shopCategoryId) throws BusinessException {

        if (shopCategoryId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        shopCategoryService.deleteByShopCategoryId(shopCategoryId);

        return CommonReturnType.create(null);
    }

    @PostMapping("/shopcategory")
    @ResponseBody
    public CommonReturnType registerShopCategory(@RequestBody ShopCategoryDO shopCategoryDO, HttpServletRequest request) throws BusinessException {

        if (shopCategoryDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        UserVO loginUser = (UserVO) request.getSession().getAttribute("LOGIN_USER");
        shopCategoryDO.setUserId(loginUser.getUserId());

        shopCategoryService.registerShopCategory(shopCategoryDO);

        return CommonReturnType.create(null);
    }

    @GetMapping("/shopcategory/{shopCategoryId}")
    @ResponseBody
    public CommonReturnType registerShopCategory(@PathVariable Integer shopCategoryId) throws BusinessException {

        if (shopCategoryId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        ShopCategoryDO category = shopCategoryService.getByShopCategoryId(shopCategoryId);

        return CommonReturnType.create(category);
    }

    @PutMapping("/shopcategory")
    @ResponseBody
    public CommonReturnType modifyCategory(@RequestBody ShopCategoryDO shopCategoryDO) throws BusinessException {

        if (shopCategoryDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        shopCategoryService.updateShopCategory(shopCategoryDO);

        return CommonReturnType.create(null);
    }

}
