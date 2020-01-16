package com.changyue.j2eefinal.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 袁阊越
 * @title: ShopCategory
 * @package com.changyue.j2eefinal.model
 * @description: 商铺实体类
 * @date 2019/12/17/017
 */
public class ShopCategoryDO {


    private Integer shopCategoryId;

    @NotBlank(message = "商铺类别名称不能为空")
    @Length(max = 10, min = 2, message = "商铺类别名称不能超过10少于2字符")
    private String shopCategoryName;

    @NotBlank(message = "商铺类别名称描述不能为空")
    private String shopCategoryDesc;

    private String shopCategoryImg;

    @NotNull(message = "商铺类别没有优先级")
    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    @NotNull(message = "没有所属的商铺id")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Integer shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName == null ? null : shopCategoryName.trim();
    }

    public String getShopCategoryDesc() {
        return shopCategoryDesc;
    }

    public void setShopCategoryDesc(String shopCategoryDesc) {
        this.shopCategoryDesc = shopCategoryDesc == null ? null : shopCategoryDesc.trim();
    }

    public String getShopCategoryImg() {
        return shopCategoryImg;
    }

    public void setShopCategoryImg(String shopCategoryImg) {
        this.shopCategoryImg = shopCategoryImg == null ? null : shopCategoryImg.trim();
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

    @Override
    public String toString() {
        return "ShopCategoryDO{" +
                "shopCategoryId=" + shopCategoryId +
                ", shopCategoryName='" + shopCategoryName + '\'' +
                ", shopCategoryDesc='" + shopCategoryDesc + '\'' +
                ", shopCategoryImg='" + shopCategoryImg + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", shopId=" + userId +
                '}';
    }
}