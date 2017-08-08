package com.wei.service.user;

import com.wei.model.user.UserInfo;

/**
 * Description ${DESC}
 * Author ed
 * Created 2017-08-08 15:05
 */
public interface UserInfoService {

    Integer insert(UserInfo userInfo) throws Exception;

    UserInfo selectOne(UserInfo userInfo) throws Exception;
}
