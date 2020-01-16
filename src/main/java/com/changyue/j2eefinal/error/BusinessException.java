package com.changyue.j2eefinal.error;

/**
 * @program: j2ee-final
 * @description:
 * @author: 袁阊越
 * @create: 2019-12-17 19:21
 */
public class BusinessException extends Exception implements CommonError {


    private CommonError commonError;

    /**
     * 业务异常的构造器
     *
     * @param commonError 错误通用类
     */
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    /**
     * 自定义的业务异常构造器
     */
    public BusinessException(String message, CommonError commonError) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(message);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        return this.commonError.setErrMsg(errMsg);
    }

}
