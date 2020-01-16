package com.changyue.j2eefinal.error;

/**
 * @program: j2ee-final
 * @description: 用户状态
 * @author: 袁阊越
 * @create: 2019-12-17 18:57
 */
public enum BusinessStateEnum implements CommonError {
    /**
     * 用户状态的enum(成功)
     */
    USER_CREATE_SUCCESS(1001, "注册成功"),
    USER_LOGIN_SUCCESS(1002, "登陆成功"),

    /*
     * 用户状态的enum(失败)
     */
    UNKNOWN_ERROR(-1000, "未知错误"),
    USER_LOGIN_FAIL(-1000, "密码或者账号错误"),
    USER_REGISTER_FAIL(-1000, "用户创建失败"),
    USER_IMG_UPLOAD_FAIL(-1000, "用户头像上传失败"),
    USER_USER_PHONE_REPEAT(-1002, "当前手机已经存在"),
    UNKNOWN_USER_ERROR(-1002, "未知用户"),

    /*
     * 区域状态的enum（失败）
     *
     */
    AREA_IS_NULL(-2000, "区域信息为空"),
    SHOP_CATEGORY_IS_NULL(-2000, "区域信息为空"),


    SEARCH_NULL(-3002, "查询结果为空"),
    PARAMETER_VALIDATION_ERROR(-3001, "参数不合法"),
    PARAMETER_VALIDATION_NULL(-3003, "参数为空"),
    FOREIGN_KEY_ERROR(-3004, "该数据正在被使用不能删除，如需删除请先删除关联的数据!");


    private int errorCode;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    BusinessStateEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrCode() {
        return this.errorCode;
    }

    @Override
    public String getErrMsg() {
        return this.errorMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errorMsg = errMsg;
        return this;
    }


}
