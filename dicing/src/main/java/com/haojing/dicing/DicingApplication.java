package com.haojing.dicing;

import com.haojing.dicing.web.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by yeweiping on 2020/9/15.
 */
@SpringBootApplication
@MapperScan("com.haojing.dicing.dao")
public class DicingApplication {
    private static Logger logger = LoggerFactory.getLogger(DicingApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DicingApplication.class, args);
        WebSocketServer webSocketServer = applicationContext.getBean(WebSocketServer.class);

        webSocketServer.reset(-1);
    }

}
