package com.haojing.dicing;

import com.haojing.dicing.entity.User;
import com.xiaomishu.web.auth.XmsAuthHttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Created by yeweiping on 2020/9/15.
 */
@ConditionalOnWebApplication
@Configuration
@Profile({ "prod", "default", "dev", "test" })
public class WebSocketConfig extends ServerEndpointConfig.Configurator implements ApplicationContextAware {
    public static final String USER_KEY = "userInfo";
    private static volatile BeanFactory context;

    private static Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return context.getBean(clazz);
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        if (sec.getUserProperties().get(USER_KEY) == null) {
            HttpSession session = (HttpSession) request.getHttpSession();
            User user = (User) session.getAttribute(XmsAuthHttpSession.SESSION_KEY);
            if (user != null) {
                sec.getUserProperties().put(USER_KEY, user);
            } else {
                logger.warn("ws连接没有到用户信息！");
            }
        }
        super.modifyHandshake(sec, request, response);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebSocketConfig.context = applicationContext;
    }
}
