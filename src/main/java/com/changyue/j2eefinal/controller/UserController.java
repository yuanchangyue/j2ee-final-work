package com.changyue.j2eefinal.controller;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.UserDO;
import com.changyue.j2eefinal.response.CommonReturnType;
import com.changyue.j2eefinal.service.UserService;
import com.changyue.j2eefinal.service.model.UserVO;
import com.changyue.j2eefinal.utils.ImageUpload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * @author 袁阊越
 * @title: UserController
 * @package com.changyue.j2eefinal.controller
 * @description: 用户控制层
 * @date 2019/12/17/017
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String tologin() {
        return "login";
    }

    @GetMapping("/toregister")
    public String register() {
        return "register";
    }

    @GetMapping("/tologin")
    public String login() {
        return "login";
    }

    @GetMapping("/user/topersoncenter")
    public String toPersonCenter() {
        return "personcenter";
    }

    @GetMapping("/loginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("LOGIN_USER");
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping(value = "/user/register")
    @ResponseBody
    public CommonReturnType registerUser(HttpServletRequest request) throws BusinessException {

        //获得用户信息
        String userInfo = request.getParameter("userJson");

        //转换成UserDO
        ObjectMapper mapper = new ObjectMapper();
        UserDO userDO;
        try {
            userDO = mapper.readValue(userInfo, UserDO.class);
        } catch (IOException e) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        //上传头像
        String uploadImgPath = ImageUpload.uploadImg(request, userDO.getName());
        userDO.setProfileImg(uploadImgPath);

        //注册用户
        userService.registerUser(userDO);

        return CommonReturnType.create(null);
    }

    @PostMapping("/login")
    @ResponseBody
    public CommonReturnType login(String phone, String password, HttpServletRequest request) throws BusinessException {

        //入参检验
        if (StringUtils.isEmpty(phone) && StringUtils.isEmpty(password)) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        //检查用户
        UserVO userVO = userService.checkPassword(password, phone);

        //存入session中
        request.getSession().setAttribute("IS_LOGIN", userVO);
        request.getSession().setAttribute("LOGIN_USER", userVO);

        System.out.println("login successful");

        return CommonReturnType.create(null);
    }

    @GetMapping("/getUser")
    @ResponseBody
    public CommonReturnType getUser(HttpServletRequest request) throws BusinessException {
        UserVO userVO = (UserVO) request.getSession().getAttribute("LOGIN_USER");
        if (userVO != null && userVO.getUserId() != null) {
            return CommonReturnType.create(userVO);
        } else {
            throw new BusinessException(BusinessStateEnum.UNKNOWN_USER_ERROR);
        }
    }

    @PostMapping(value = "/user/modifyuser")
    @ResponseBody
    public CommonReturnType modifyUser(HttpServletRequest request) throws BusinessException {

        //获得用户信息
        String userInfo = request.getParameter("userInfo");

        //转换成UserDO
        ObjectMapper mapper = new ObjectMapper();
        UserDO userDO;
        try {
            userDO = mapper.readValue(userInfo, UserDO.class);
        } catch (IOException e) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        //修改用户
        userService.modifyUser(userDO);
        UserVO userVO = userService.queryUserByUserId(userDO.getUserId());
        request.getSession().setAttribute("LOGIN_USER", userVO);

        return CommonReturnType.create(null);
    }

    @PostMapping(value = "/user/modifypassword")
    @ResponseBody
    public CommonReturnType modifyPw(HttpServletRequest request) throws BusinessException {

        String userId = request.getParameter("userId");
        String newPassword = request.getParameter("newPassword");
        String oldPassword = request.getParameter("oldPassword");

        if (StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(oldPassword) || userId == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        int id = 0;
        try {
            id = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        //修改用户
        userService.modifyPassword(id, oldPassword, newPassword);

        return CommonReturnType.create(null);
    }

}
