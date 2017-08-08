package com.wei.service.user.impl;

import com.wei.dao.user.UserInfoDao;
import com.wei.model.user.UserInfo;
import com.wei.service.user.UserInfoService;
import com.wei.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;import java.util.Date;

/**
 * Description
 * Author ed
 * Created 2017-08-08 15:06
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    public Integer insert(UserInfo userInfo) throws Exception {
        String password = MD5Util.md5Encode(userInfo.getPassword());
        userInfo.setPassword(password);
        userInfo.setCreateTime(new Date());
        userInfoDao.insert(userInfo);
        return userInfo.getId();
    }

    public UserInfo selectOne(UserInfo userInfo) throws Exception{
       UserInfo info = userInfoDao.selectOne(userInfo);
       return info;
    }

}
