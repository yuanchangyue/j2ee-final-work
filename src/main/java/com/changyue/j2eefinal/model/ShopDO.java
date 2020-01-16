package com.changyue.j2eefinal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 袁阊越
 * @title: Shop
 * @package com.changyue.j2eefinal.model
 * @description: 商铺实体类
 * @date 2019/12/17/017
 */
public class ShopDO implements Serializable {

    @JsonProperty("shopId")
    private Integer shopId;

    @NotNull(message = "该商铺没有拥有者Id")
    private UserDO userDO;

    @NotNull(message = "该商铺没有区域信息")
    private AreaDO areaDO;

    @NotNull(message = "该商铺没有商铺类别")
    private ShopCategoryDO shopCategoryDO;

    @NotBlank(message = "商铺名称不能为空")
    @Length(max = 20, min = 2, message = "商铺名称不能超过20个少于2个字符")
    private String shopName;

    @NotBlank(message = "商铺名称不能为空")
    private String shopDesc;

    @NotBlank(message = "商铺地址不能为空")
    private String shopAddr;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;

    @NotBlank(message = "商铺省略图不能为空")
    private String shopImg;

    @NotNull(message = "商铺没有优先级")
    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private Integer enableStatus;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public AreaDO getAreaDO() {
        return areaDO;
    }

    public void setAreaDO(AreaDO areaDO) {
        this.areaDO = areaDO;
    }

    public ShopCategoryDO getShopCategoryDO() {
        return shopCategoryDO;
    }

    public void setShopCategoryDO(ShopCategoryDO shopCategoryDO) {
        this.shopCategoryDO = shopCategoryDO;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
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

    @Override
    public String toString() {
        return "ShopDO{" +
                "shopId=" + shopId +
                ", userDO=" + userDO +
                ", areaDO=" + areaDO +
                ", shopCategoryDO=" + shopCategoryDO +
                ", shopName='" + shopName + '\'' +
                ", shopDesc='" + shopDesc + '\'' +
                ", shopAddr='" + shopAddr + '\'' +
                ", phone='" + phone + '\'' +
                ", shopImg='" + shopImg + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", enableStatus=" + enableStatus +
                '}';
    }
}