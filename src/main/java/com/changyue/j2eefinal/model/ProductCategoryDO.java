package com.changyue.j2eefinal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 袁阊越
 * @title: ProductCategoryDO
 * @package com.changyue.j2eefinal.model
 * @description: 商品类别实体类
 * @date 2019/12/28/028
 */
public class ProductCategoryDO {

    private Integer productCategoryId;

    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, min = 2, message = "用户名不能超过20个少于2个字符")
    private String productCategoryName;

    @NotBlank(message = "商品描述不能为空")
    private String productCategoryDesc;

    @NotNull(message = "商品类别没有优先级")
    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    @NotBlank(message = "没有所属商铺")
    private ShopDO shop;

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getProductCategoryDesc() {
        return productCategoryDesc;
    }

    public void setProductCategoryDesc(String productCategoryDesc) {
        this.productCategoryDesc = productCategoryDesc;
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

    public ShopDO getShop() {
        return shop;
    }

    public void setShop(ShopDO shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "ProductCategoryDO{" +
                "productCategoryId=" + productCategoryId +
                ", productCategoryName='" + productCategoryName + '\'' +
                ", productCategoryDesc='" + productCategoryDesc + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", shopDO=" + shop +
                '}';
    }
}