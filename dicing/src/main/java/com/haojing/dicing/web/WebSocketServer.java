package com.haojing.dicing.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.haojing.dicing.WebSocketConfig;
import com.haojing.dicing.entity.Award;
import com.haojing.dicing.entity.Desk;
import com.haojing.dicing.entity.User;
import com.haojing.dicing.exception.ExceptionCode;
import com.haojing.dicing.exception.ServiceException;
import com.haojing.dicing.service.AwardService;
import com.haojing.dicing.service.DicingService;
import com.haojing.dicing.service.UserService;
import com.haojing.dicing.vo.*;
import com.haojing.dicing.vo.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by yeweiping on 2020/9/15.
 */
@ServerEndpoint(value = "/ws", configurator = WebSocketConfig.class)
@Component
public class WebSocketServer {
    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private static ConcurrentMap<Integer, Set<User>> deskUserMap = new ConcurrentHashMap<>(32);
    private static ConcurrentMap<String, User> sidUserMap = new ConcurrentHashMap<>(256);
    private static ConcurrentMap<Long, Set<Session>> userIdSessionMap = new ConcurrentHashMap<>(256);
//    private static Map<Long, User> nextDicingUserMap = new ConcurrentHashMap<>(32);
    private static Set<User> nextDicingUserSet = new HashSet<>(32);
    private ExecutorService barrageExector = Executors.newFixedThreadPool(1, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("Barrage-T");
            return t;
        }
    });

    private static Timer timer = null;
    private static int timerIdx = 1;

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmssSSS"));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Autowired
    private DicingService dicingService;
    @Autowired
    private UserService userService;
    @Autowired
    private AwardService awardService;

//    @PostConstruct
    public void initNextDicingUserMap(Desk desk) {
//        List<Desk> desks = dicingService.getAllDesks();
//        synchronized (nextDicingUserSet) {
//            for (Desk desk : desks) {
//                if (desk.getState() == Desk.STATE_RUNNING) {
//                    User user = userService.findByDeskNoAndSeqNo(desk.getDeskNo(), desk.getCurSeqNo());
//                    logger.debug("初始化加 {} 进nextDicingUserSet", user.getUserName());
//                    user.setStartDicingTime(System.currentTimeMillis());
//                    nextDicingUserSet.add(user);
//                }
//            }
//        }
        synchronized (nextDicingUserSet) {
            int curSeqNo = desk.getCurSeqNo();
            if (curSeqNo <= 0) {
                curSeqNo = 1;
            }
            User user = userService.findByDeskNoAndSeqNo(desk.getDeskNo(), curSeqNo);
            logger.debug("初始化加 {} 进nextDicingUserSet", user.getUserName());
            user.setStartDicingTime(System.currentTimeMillis());
            nextDicingUserSet.add(user);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        User user = (User) session.getUserProperties().get(WebSocketConfig.USER_KEY);
        if (user == null) {
            write(session, "请先登录！");
            try {
                session.getAsyncRemote().flushBatch();
                session.close();
            } catch (IOException e) {
            }
            return;
        }

        logger.debug("用户【{}】已连接上来", user.getUserName());
        sidUserMap.put(session.getId(), user);
        Set<Session> sessionSet = new HashSet<>();
        sessionSet.add(session);
        Set<Session> preSessionSet = userIdSessionMap.putIfAbsent(user.getUserId(), sessionSet);
        if (preSessionSet != null) {
            preSessionSet.add(session);
        }
        Set<User> deskUsers = deskUserMap.get(user.getDeskNo());
        if (deskUsers == null) {
            deskUsers = Collections.synchronizedSet(new HashSet<User>());
            Set<User> temp = deskUserMap.putIfAbsent(user.getDeskNo(), deskUsers);
            if (temp != null) {
                deskUsers = temp;
            }
        }
        deskUsers.add(user);

        InitResult initResult = dicingService.init(user);
        Message initMsg = new Message(Message.MSG_TYPE_INIT, toJson(initResult));
        write(session, toJson(initMsg));
    }

    private static void write(Session session, String msg) {
        session.getAsyncRemote().sendText(msg);
    }

    private static void write(Set<Session> sessionSet, String msg) {
        for (Session session : sessionSet) {
            try {
                session.getAsyncRemote().sendText(msg);
            } catch (Exception e) {
                logger.warn("消息发送失败！用户：{}, 错误信息：{}", sidUserMap.get(session.getId()).getUserName(), e.getMessage());
                try {
                    session.close();
                } catch (Exception e1) {
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        User user = sidUserMap.remove(session.getId());
        if (user != null) {
            logger.debug("用户【{}】已断开连接", user.getUserName());
            Set<Session> sessionSet = userIdSessionMap.get(user.getUserId());
            if (sessionSet != null) {
                sessionSet.remove(session);
            }
            Set<User> deskUsers = deskUserMap.get(user.getDeskNo());
            if (deskUsers != null) {
                deskUsers.remove(user);
            }
        }
        try {
            session.close();
        } catch (IOException e) {
        }
    }

    @OnError
    public void onError(Throwable e, Session session) {
        onClose(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        User user = sidUserMap.get(session.getId());
        logger.info("收到客户端发来的消息:{}，用户：{}", message, user.getUserName());
        Message msg;
        try {
            msg = objectMapper.readValue(message, Message.class);
        } catch (JsonProcessingException e) {
            Error error = new Error(ExceptionCode.MSG_MSG_FORMAT_ERROR
                    , ExceptionCode.MESSAGE.get(ExceptionCode.MSG_MSG_FORMAT_ERROR));
            Message resp = new Message(Message.MSG_TYPE_ERROR, toJson(error));
            write(session, toJson(resp));
            return;
        }

        switch (msg.getMsgType()) {
            case Message.MSG_TYPE_DICING:
                int systemDicing = 0;
                try {
                    String content = msg.getContent();
                    if (content != null && content.length() > 0) {
                        Map<String, Object> params = toJavaMap(content, String.class, Object.class);
                        if (params != null) {
                            if (params.containsKey("systemDicing")
                                    && Integer.parseInt(params.get("systemDicing").toString()) == 1) {
                                systemDicing = 1;
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
                dicing(sidUserMap.get(session.getId()), systemDicing);
                break;
            case Message.MSG_TYPE_HEARTBEAT:
                pong(session);
                break;
            case Message.MSG_TYPE_BARRAGE_R:
                barrageReq(session, msg.getContent());
                break;
            default:
                Error error = new Error(ExceptionCode.MSG_MSG_UNKNOWN
                        , ExceptionCode.MESSAGE.get(ExceptionCode.MSG_MSG_UNKNOWN));
                Message resp = new Message(Message.MSG_TYPE_ERROR, toJson(error));
                write(session, toJson(resp));
        }
    }

    private void barrageReq(Session session, String content) {
        barrageExector.execute(() -> {
            User user = sidUserMap.get(session.getId());
            Barrage barrage = new Barrage();
            barrage.setUser(user);
            barrage.setText(content);
            barrage.setScreenTime(ThreadLocalRandom.current().nextInt(4) + 3);
            Message message = new Message(Message.MSG_TYPE_BARRAGE_B, toJson(barrage));
            String mseesgeStr = toJson(message);
            barrageBroadcast(mseesgeStr);
        });
    }

    private void barrageBroadcast(String mseesgeStr) {
        Map<Long, Set<Session>> tempMap = new HashMap<>(userIdSessionMap);
        for (Map.Entry<Long, Set<Session>> entry : tempMap.entrySet()) {
            write(entry.getValue(), mseesgeStr);
        }
    }

    private void pong(Session session) {
        write(session, toJson(new Message(Message.MSG_TYPE_HEARTBEAT, "pong")));
    }

    private void dicing(User user, int systemDicing) {
        Set<Session> sessionSet = userIdSessionMap.get(user.getUserId());

        synchronized (nextDicingUserSet) {
            boolean isExist = nextDicingUserSet.remove(user);
            if (!isExist && dicingService.isStarted(user.getDeskNo())) {
                // 如果不存在，说明已经博好了
                return;
            }
        }
        if (systemDicing > 0) {
            logger.info("系统自动博, userId: {}, userName: {}", user.getUserId()
                    , user.getUserName());
        }

        int retry = 0;
        DiceResult diceResult = null;
        while (retry++ < 3) {
            try {
                diceResult = dicingService.dicing(user, systemDicing);
                break;
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
                Error error;
                if (e instanceof ServiceException) {
                    ServiceException se = (ServiceException) e;
                    if (se.getCode() == ExceptionCode.FINISHED) {
                        // 博饼已经结束
                        return;
                    }
                    error = new Error(se.getCode(), ExceptionCode.MESSAGE.get(se.getCode()));
                } else {
                    error = new Error(ExceptionCode.DICES_ERROR, ExceptionCode.MESSAGE.get(ExceptionCode.DICES_ERROR));
                }

                if (sessionSet != null) {
                    logger.warn("博饼失败，重新加入nextDicingUserSet，继续博，用户：{}", user.getUserName());
                    synchronized (nextDicingUserSet) {
                        user.setStartDicingTime(user.getStartDicingTime() + 10 * 1000);
                        nextDicingUserSet.add(user);
                    }
                    Message errMsg = new Message(Message.MSG_TYPE_ERROR, toJson(error));
                    write(sessionSet, toJson(errMsg));
                    return;
                } else if (systemDicing == 2) {
                    // 用户不在线，且为系统自动博饼时，出错要重试
                    logger.error("系统后台自动博失败，将重试！用户：{}", user.getUserName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        break;
                    }
                    continue;
                }
            }
        }

        if (diceResult == null) {
            logger.warn("系统后台博饼失败，重新加入nextDicingUserSet，继续自动博，用户：{}", user.getUserName());
            synchronized (nextDicingUserSet) {
                user.setStartDicingTime(user.getStartDicingTime() + 10 * 1000);
                nextDicingUserSet.add(user);
            }
            return;
        }
        Message dicingMsg = new Message(Message.MSG_TYPE_DICING_RESULT, toJson(diceResult));
        String msgStr = toJson(dicingMsg);

        if (!dicingService.isStarted(user.getDeskNo())) {
            // 试博仅推结果给自己
            if (sessionSet != null) {
                write(sessionSet, msgStr);
            }
        } else {
            if (diceResult.getDesk().getState() == Desk.STATE_RUNNING) {
                synchronized (nextDicingUserSet) {
                    logger.debug("进行中加 {} 进nextDicingUserSet", diceResult.getNextDicingUser().getUserName());
                    diceResult.getNextDicingUser().setStartDicingTime(System.currentTimeMillis());
                    nextDicingUserSet.add(diceResult.getNextDicingUser());
                }
            }

            Set<User> deskUsers = deskUserMap.get(user.getDeskNo());
            if (deskUsers != null) {
                for (User deskUser : deskUsers) {
                    Set<Session> deskSessionSet = userIdSessionMap.get(deskUser.getUserId());
                    if (deskSessionSet != null) {
                        write(deskSessionSet, msgStr);
                    }
                }
            }
        }

        final DiceResult diceResultTemp = diceResult;
        barrageExector.execute(() -> {
            if (diceResultTemp.getDicingLog().getAwardId() >= 6 && diceResultTemp.isHasAward()) {
                Barrage barrage = new Barrage();
                Award award = awardService.findById(diceResultTemp.getDicingLog().getAwardId());
                barrage.setText("恭喜第 " + user.getDeskNo() + " 桌 " + user.getUserName() + " 获得 " + award.getAwardName() + " !");
                barrage.setScreenTime(6);
                barrage.setColor("#FFD700");
                Message message = new Message(Message.MSG_TYPE_BARRAGE_B, toJson(barrage));
                barrageBroadcast(toJson(message));
            }

            if (diceResultTemp.getLastZY() != null && diceResultTemp.getCurrZY() != null
                    && diceResultTemp.getLastZY().getUserId() != diceResultTemp.getCurrZY().getUserId()) {
                // 状元被抢
                Barrage barrage = new Barrage();
                barrage.setText("第 " + user.getDeskNo() + " 桌 " + diceResultTemp.getLastZY().getUserName() + " 的状元被 "
                        + diceResultTemp.getCurrZY().getUserName() + " 抢啦！！！");
                barrage.setScreenTime(6);
                barrage.setColor("#FFD700");
                Message message = new Message(Message.MSG_TYPE_BARRAGE_B, toJson(barrage));
                barrageBroadcast(toJson(message));
            }
        });
    }

    public void sendStartMessage(Desk desk) {
//        dicingService.startAllDesks();
        initNextDicingUserMap(desk);

        Message startMsg = new Message(Message.MSG_TYPE_START, "");
        String startMsgStr = toJson(startMsg);
        Set<User> deskUesrs = deskUserMap.get(desk.getDeskNo());
        int deskUserNumOnline = 0;
        if (deskUesrs != null) {
            deskUserNumOnline = deskUesrs.size();
        }
        logger.debug("发送开始消息给第 {} 桌用户，用户数：{}", desk.getDeskNo(), deskUserNumOnline);
        if (deskUserNumOnline > 0) {
            for (User user : deskUserMap.get(desk.getDeskNo())) {
                write(userIdSessionMap.get(user.getUserId()), startMsgStr);
            }
        }
    }

    public void systemDicing() {
        Set<User> userSet;
        synchronized (nextDicingUserSet) {
            if (nextDicingUserSet.size() == 0) {
                return;
            }
            userSet = new HashSet<>(nextDicingUserSet);
        }
        long now = System.currentTimeMillis();
        Iterator<User> userIt = userSet.iterator();
        while (userIt.hasNext()) {
            User user = userIt.next();
            if (now - user.getStartDicingTime() >= 20000) {
                // 系统自动博
                dicing(user, 2);
            }
        }
    }

    public synchronized void reset(int deskNo) {
        if (deskNo > 0) {
            synchronized (nextDicingUserSet) {
                nextDicingUserSet.clear();
                Iterator<User> userIterator = nextDicingUserSet.iterator();
                while (userIterator.hasNext()) {
                    User user = userIterator.next();
                    if (user.getDeskNo() == deskNo) {

                    }
                }
            }
        }

        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer("Timer-" + timerIdx++);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    systemDicing();
                } catch (Throwable e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }, 10000, 2000);

        List<Desk> desks = dicingService.getAllDesks();
        long now = System.currentTimeMillis();
        for (Desk desk : desks) {
            if (now > desk.getStartTime().getTime()) {
                sendStartMessage(desk);
            } else {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            sendStartMessage(desk);
                        } catch (Throwable e) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                }, desk.getStartTime().getTime() - now);
            }
        }
    }

    private String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e1) {
            logger.error(e1.getMessage(), e1);
        }
        return null;
    }

    private <T, V> Map<T, V> toJavaMap(String jsonString, Class<T> keyClass, Class<V> valueClass) {
        try {
            return (Map)this.objectMapper.readValue(jsonString, this.objectMapper.getTypeFactory().constructMapLikeType(Map.class, keyClass, valueClass));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
