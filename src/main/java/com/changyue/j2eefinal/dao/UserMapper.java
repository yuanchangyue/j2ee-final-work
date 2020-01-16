package com.changyue.j2eefinal.dao;

import com.changyue.j2eefinal.model.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author 袁阊越
 * @title: UserMapper
 * @package com.changyue.j2eefinal.dao
 * @description: 用户数据访问接口
 * @date 2019/12/17/017
 */
public interface UserMapper {
    /**
     * 根据主键id删除用户
     *
     * @param userId 用户id
     * @return 影响
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * 插入用户
     *
     * @param userDO 用户信息
     * @return 影响
     */
    int insert(UserDO userDO);

    /**
     * 选择性的插入用户
     *
     * @param userDO 用户信息
     * @return 影响
     */
    int insertSelective(UserDO userDO);

    /**
     * 根据主键id查询用户
     *
     * @param userId 用户id
     * @return 用户信息
     */
    UserDO selectByPrimaryKey(Integer userId);

    /**
     * 根据手机号查询用户
     *
     * @param phone 用户手机号
     * @return 用户信息
     */
    UserDO selectByPhone(String phone);

    /**
     * 根据主键id选择性的更新用户信息
     *
     * @param userDO 用户信息
     * @return 影响
     */
    int updateByPrimaryKeySelective(UserDO userDO);

    /**
     * 根据主键id更新用户
     *
     * @param userDO 用户信息
     * @return 影响
     */
    int updateByPrimaryKey(UserDO userDO);


    /**
     * 更新密码
     *
     * @param userId      用户id
     * @param newPassword 新的密码
     * @param newSalt     新的盐
     * @return 影响
     */
    int updatePassword(@Param("userId") Integer userId,@Param("newPassword") String newPassword,@Param("newSalt") String newSalt);

}