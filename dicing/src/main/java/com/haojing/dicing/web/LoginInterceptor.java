package com.haojing.dicing.web;

import com.xiaomishu.web.auth.XmsAuthHttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by yeweiping on 2020/9/15.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        String servletPath = request.getServletPath();
        if (
//                !servletPath.startsWith("/ws")
//                &&
                !servletPath.equals("/favicon.ico")
                && !servletPath.equals("/error")
                && httpSession.getAttribute(XmsAuthHttpSession.SESSION_KEY) == null) {
            logger.warn("没有用户信息！servmntPath: {}, remoteIp: {}", request.getServletPath(), request.getRemoteAddr());
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write("{\"code\":401,\"msg\":\"请先登录!\"}");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
