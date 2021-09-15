package com.haojing.dicing.web;

import com.haojing.dicing.entity.Desk;
import com.haojing.dicing.entity.DicingLog;
import com.haojing.dicing.entity.User;
import com.haojing.dicing.service.DicingService;
import com.haojing.dicing.vo.DiceResult;
import com.haojing.dicing.vo.DicingGrade;
import com.haojing.dicing.vo.InitResult;
import com.xiaomishu.web.auth.XmsAuthHttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yeweiping on 2020/9/15.
 */
@RestController
public class DicingController {
    private static Logger logger = LoggerFactory.getLogger(DicingController.class);
    @Autowired
    private DicingService dicingService;
    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping("/init")
    public InitResult init(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(XmsAuthHttpSession.SESSION_KEY);
        return dicingService.init(user);
    }

    @Profile({ "default", "dev", "test" })
    @RequestMapping("/dicing")
    public DiceResult dicing(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(XmsAuthHttpSession.SESSION_KEY);
        return dicingService.dicing(user, 0);
    }

    @RequestMapping("/dicingLogs")
    public List<DicingLog> dicingLogs(@RequestParam("deskNo") int deskNo
            , @RequestParam(value = "startDicingLogId", required = false) Integer startDicingLogId
            , @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageSize == null) {
            pageSize = new Integer(10);
        } else {
            if (pageSize.intValue() > 100) {
                pageSize = new Integer(100);
            }
        }
        return dicingService.getDicingLogs(deskNo, startDicingLogId, pageSize);
    }

    @RequestMapping("/deskGrades")
    public DicingGrade dicingLogs(@RequestParam("deskNo") int deskNo) {
        return dicingService.findDeskGrades(deskNo);
    }

    @RequestMapping("/desks")
    public List<Desk> desks() {
        return dicingService.getAllDesks();
    }

    @Profile({ "default", "dev", "test" })
    @RequestMapping("/mockDicing")
    public String mockDicing(@RequestParam("dicesStr") String dicesStr, @RequestParam("deskNo") int deskNo) {
        dicingService.mockDices(deskNo, dicesStr);
        return "ok";
    }

    @Profile({ "default", "dev", "test" })
    @RequestMapping("/mockDicingOut")
    public String mockDicingOut(@RequestParam("dicesOutStr") String dicesOutStr, @RequestParam("deskNo") int deskNo) {
        dicingService.mockDicesOut(deskNo, dicesOutStr);
        return "ok";
    }

    @Profile({ "default", "dev", "test" })
    @RequestMapping("/resetStartTime")
    public String resetStartTime(@RequestParam("startTimeStr") String startTimeStr, @RequestParam("deskNo") int deskNo) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = df.parse(startTimeStr);
        logger.info("重置 {} 桌开始时间，新时间为：{}", deskNo, startTimeStr);
        dicingService.resetDesk(deskNo, startTime);
        webSocketServer.reset(deskNo);

        return "成功重置开始时间，新时间为：" + startTimeStr;
    }
}
