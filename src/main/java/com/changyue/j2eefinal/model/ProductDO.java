package com.changyue.j2eefinal.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 袁阊越
 * @title: Product
 * @package com.changyue.j2eefinal.model
 * @description: 商品实体类
 * @date 2019/12/17/017
 */
public class ProductDO {

    private Integer productId;

    @NotBlank(message = "商品类别名称不能为空")
    @Length(max = 10, min = 2, message = "商品类别名称不能超过10少于2字符")
    private String productName;

    @NotBlank(message = "商品类别名称描述不能为空")
    private String productDesc;

    @NotBlank(message = "商品类别名称描述不能为空")
    private String imgAddr;

    @NotBlank(message = "商品价格描述不能为空")
    private String normalPrice;

    @NotBlank(message = "商品促销价格不能为空")
    private String promotionPrice;

    @NotNull(message = "商品类别没有优先级")
    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private Integer enableStatus;

    @NotNull(message = "商品没有积分")
    private Integer point;

    @NotBlank(message = "商品类别名称不能为空")
    private ProductCategoryDO productCategoryDO;

    @NotBlank(message = "所属商铺不能为空")
    private ShopDO shopDO;

    @NotBlank(message = "所属人不能为空")
    private UserDO userDO;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public ProductCategoryDO getProductCategoryDO() {
        return productCategoryDO;
    }

    public void setProductCategoryDO(ProductCategoryDO productCategoryDO) {
        this.productCategoryDO = productCategoryDO;
    }

    public ShopDO getShopDO() {
        return shopDO;
    }

    public void setShopDO(ShopDO shopDO) {
        this.shopDO = shopDO;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    @Override
    public String toString() {
        return "ProductDO{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", imgAddr='" + imgAddr + '\'' +
                ", normalPrice='" + normalPrice + '\'' +
                ", promotionPrice='" + promotionPrice + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", enableStatus=" + enableStatus +
                ", point=" + point +
                ", productCategoryDO=" + productCategoryDO +
                ", shopDO=" + shopDO +
                ", userDO=" + userDO +
                '}';
    }
}


