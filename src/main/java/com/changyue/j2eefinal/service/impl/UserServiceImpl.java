package com.changyue.j2eefinal.service.impl;

import com.changyue.j2eefinal.dao.UserMapper;
import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.UserDO;
import com.changyue.j2eefinal.service.UserService;
import com.changyue.j2eefinal.service.model.UserVO;
import com.changyue.j2eefinal.utils.MD5Utils;
import com.changyue.j2eefinal.validator.ValidatorImpl;
import com.changyue.j2eefinal.validator.ValidatorResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @program: j2ee-final
 * @description: 用户业务层接口实现类
 * @author: 袁阊越
 * @create: 2019-12-17 18:55
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void deleteUser(Integer userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(UserDO userDO) throws BusinessException {

        if (userDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        //校验入参
        ValidatorResult validator = this.validator.validator(userDO);
        if (validator.isHasError()) {
            throw new BusinessException(validator.getErrMsg(), BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }

        //加密密码
        String userPrivateSalt = MD5Utils.md5PrivateSalt(userDO.getPassword(), userDO.getPassword() + userDO.getEmail());
        userDO.setSalt(userPrivateSalt);
        String password = MD5Utils.md5PrivateSalt(userDO.getPassword(), userDO.getSalt());
        userDO.setPassword(password);

        //检查手机号是否重复
        if (userMapper.selectByPhone(userDO.getPhone()) != null) {
            throw new BusinessException(BusinessStateEnum.USER_USER_PHONE_REPEAT);
        }

        //插入到数据库
        userMapper.insertSelective(userDO);
    }

    @Override
    public UserVO queryUserByUserId(Integer userId) throws BusinessException {
        UserDO userDO = userMapper.selectByPrimaryKey(userId);
        if (userDO == null) {
            throw new BusinessException(BusinessStateEnum.UNKNOWN_USER_ERROR);
        }
        return convertFromDO(userDO);
    }

    @Override
    public void modifyUser(UserDO userDO) throws BusinessException {
        if (userDO == null) {
            throw new BusinessException(BusinessStateEnum.PARAMETER_VALIDATION_ERROR);
        }
        userMapper.updateByPrimaryKeySelective(userDO);
    }

    @Override
    public UserVO checkPassword(String password, String phone) throws BusinessException {
        UserDO userDO = userMapper.selectByPhone(phone);
        if (userDO == null) {
            throw new BusinessException(BusinessStateEnum.UNKNOWN_USER_ERROR);
        }
        String passwordByMd5 = MD5Utils.md5PrivateSalt(password, userDO.getSalt());
        if (!StringUtils.equals(passwordByMd5, userDO.getPassword())) {
            throw new BusinessException(BusinessStateEnum.USER_LOGIN_FAIL);
        }
        return convertFromDO(userDO);
    }

    @Override
    public void modifyPassword(Integer userId, String oldPassword, String newPassword) throws BusinessException {

        UserDO userDO = userMapper.selectByPrimaryKey(userId);
        String passwordByMd5 = MD5Utils.md5PrivateSalt(oldPassword, userDO.getSalt());
        if (!StringUtils.equals(passwordByMd5, userDO.getPassword())) {
            throw new BusinessException(BusinessStateEnum.USER_LOGIN_FAIL);
        }

        String userPrivateSalt = MD5Utils.md5PrivateSalt(newPassword, userDO.getPassword() + userDO.getEmail());
        String password = MD5Utils.md5PrivateSalt(newPassword, userPrivateSalt);

        userMapper.updatePassword(userId, password, userPrivateSalt);
    }

    /**
     * 转换DO位VO
     *
     * @param userDO 用户DO
     * @return 用户VO
     */
    private static UserVO convertFromDO(UserDO userDO) {

        if (userDO == null) {
            return null;
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);

        return userVO;
    }

}
