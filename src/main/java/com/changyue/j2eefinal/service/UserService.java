package com.changyue.j2eefinal.service;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.model.UserDO;
import com.changyue.j2eefinal.service.model.UserVO;

/**
 * @program: j2ee-final
 * @description: 用户业务层接口
 * @author: 袁阊越
 * @create: 2019-12-17 18:47
 */
public interface UserService {
    /**
     * 根据主键id删除用户
     *
     * @param userId 用户id
     */
    void deleteUser(Integer userId);

    /**
     * 注册用户
     *
     * @param userDO 用户信息
     * @throws BusinessException 异常
     */
    void registerUser(UserDO userDO) throws BusinessException;

    /**
     * 根据主键id查询用户
     *
     * @param userId 用户id
     * @return 用户信息
     * @throws BusinessException 异常
     */
    UserVO queryUserByUserId(Integer userId) throws BusinessException;

    /**
     * 修改用户信息
     *
     * @param userDO 用户信息
     * @throws BusinessException 异常
     */
    void modifyUser(UserDO userDO) throws BusinessException;


    /**
     * 验证密码
     *
     * @param password 前端传递的密码
     * @param phone    用户手机号
     * @return 用户信息
     * @throws BusinessException 异常
     */
    UserVO checkPassword(String password, String phone) throws BusinessException;

    /**
     * 更新密码
     *
     * @param userId      用户id
     * @param oldPassword 旧的密码
     * @param newPassword 新的密码
     */
    void modifyPassword(Integer userId, String oldPassword, String newPassword) throws BusinessException;

}
