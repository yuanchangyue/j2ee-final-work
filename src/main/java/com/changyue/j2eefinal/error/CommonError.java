package com.changyue.j2eefinal.error;

/**
 * @program: j2ee-final
 * @description: 通用的错误接口
 * @author: 袁阊越
 * @create: 2019-12-17 19:13
 */
public interface CommonError {

    /**
     * 获得错误代码
     *
     * @return ErrorCode
     */
    int getErrCode();

    /**
     * 获得错误信息
     *
     * @return ErrorMsg
     */
    String getErrMsg();


    /**
     * 设置错误信息
     *
     * @param errMsg 错误信息
     * @return 通用错误
     */
    CommonError setErrMsg(String errMsg);

}
