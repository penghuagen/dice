package com.haojing.dicing.web;

import com.haojing.dicing.entity.User;
import com.haojing.dicing.service.UserService;
import com.haojing.dicing.vo.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by yeweiping on 2020/9/15.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public List<User> find(@RequestParam("deskNo") int deskNo) {
        return userService.findByDeskNo(deskNo);
    }

    @RequestMapping("/user/ready")
    public Error ready(HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute(XmsAuthHttpSession.SESSION_KEY);
//        userService.updateReady(user);
        return new Error(0, "ok");
    }

}
