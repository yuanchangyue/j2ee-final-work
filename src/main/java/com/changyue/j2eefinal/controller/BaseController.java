package com.changyue.j2eefinal.controller;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.response.CommonReturnType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @program: j2ee-final
 * @description: 通用的controller
 * @author: 袁阊越
 * @create: 2019-12-17 19:06
 */
public class BaseController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonReturnType handlerException(HttpServletRequest request, Exception e) {
        HashMap<String, Object> exceptionModel = new HashMap<>();
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            exceptionModel.put("errCode", businessException.getErrCode());
            exceptionModel.put("errMsg", businessException.getErrMsg());
        } else if (e instanceof DataIntegrityViolationException) {
            exceptionModel.put("errCode", BusinessStateEnum.FOREIGN_KEY_ERROR.getErrCode());
            exceptionModel.put("errMsg", BusinessStateEnum.FOREIGN_KEY_ERROR.getErrMsg());
        } else {
            exceptionModel.put("errCode", BusinessStateEnum.UNKNOWN_ERROR.getErrCode());
            exceptionModel.put("errMsg", BusinessStateEnum.UNKNOWN_ERROR.getErrMsg());
        }
        return CommonReturnType.create(exceptionModel, "fail");
    }

}
