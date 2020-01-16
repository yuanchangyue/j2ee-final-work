package com.changyue.j2eefinal.error;

/**
 * @author 袁阊越
 * @title: ClientStateEnum
 * @package com.changyue.springwork9.enums
 * @description: 用户操作状态
 * @date 2019/12/3/003
 */
public enum ClientStateEnum {

    /**
     * 用户状态的enum(成功)
     */
    CLIENT_CREATE_SUCCESS(1001, "开户成功"),
    CLIENT_DEPOSIT_MONEY(1002, "存钱成功"),
    CLIENT_WITHDRAW_MONEY(1003, "取钱成功"),
    CLIENT_TRANS_MONEY(1004, "转账成功"),
    CLIENT_LOGIN_SUCCESS(1005, "登陆成功"),
    /*
     * 用户状态的enum(失败)
     */
    CLIENT_INFO_NULL(-1001, "开户名称或者密码为空"),
    CLIENT_MONEY_ILLEGAL(-1002, "金额不合法"),
    CLIENT_IS_NULL(-1003, "用户不存在"),
    CLIENT_DEPOSIT_MONEY_FAIL(-1005, "存钱失败"),
    CLIENT_WITHDRAW_MONEY_FAIL(-1006, "取钱失败"),
    CLIENT_TRANS_MONEY_FAIL(-1007, "转账失败"),
    CLIENT_INSUFFICIENT_BALANCE(-1008, "余额不足"),
    CLIENT_LOGIN_FAIL(-1010, "登陆失败"),
    CLIENT_PW_IS_WRONG(-1009, "密码错误");

    private int stateCode;
    private String stateInfo;

    public int getStateCode() {
        return stateCode;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    ClientStateEnum(int stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
