package com.haojing.dicing.web;

import com.haojing.common.auth.UserInfoLoader;
import com.haojing.dicing.ApplicationContextProvider;
import com.haojing.dicing.entity.User;
import com.haojing.dicing.exception.ServiceException;
import com.haojing.dicing.service.UserService;
import com.xiaomishu.web.auth.XmsUserCookie;

/**
 * Created by yeweiping on 2020/9/15.
 */
public class UserLoader implements UserInfoLoader<XmsUserCookie, User> {
    private static UserService userService = ApplicationContextProvider.getBean(
            "userService", UserService.class);

    @Override
    public User load(XmsUserCookie userCookie) {
        User user = userService.find(userCookie.userId);
        if (user == null) {
            throw new ServiceException(1000, "该账号无博饼权限！");
        }
        return user;
    }
}
