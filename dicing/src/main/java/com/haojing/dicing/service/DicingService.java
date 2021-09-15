package com.haojing.dicing.service;

import com.google.common.annotations.VisibleForTesting;
import com.haojing.dicing.dao.DeskAwardMapper;
import com.haojing.dicing.dao.DeskMapper;
import com.haojing.dicing.dao.DicingLogMapper;
import com.haojing.dicing.entity.*;
import com.haojing.dicing.exception.ExceptionCode;
import com.haojing.dicing.exception.ServiceException;
import com.haojing.dicing.vo.CommonResult;
import com.haojing.dicing.vo.DiceResult;
import com.haojing.dicing.vo.DicingGrade;
import com.haojing.dicing.vo.InitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yeweiping on 2020/9/15.
 */
@Service
public class DicingService {
    private static Logger logger = LoggerFactory.getLogger(DicingService.class);
    @Autowired
    private DeskMapper deskMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private AwardService awardService;
    @Autowired
    private DeskAwardMapper deskAwardMapper;
    @Autowired
    private DicingLogMapper dicingLogMapper;

//    @Value("#{new java.text.SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\").parse(\"${dicing.startTime}\")}")
//    private Date startTime;
//    @Value("${dicing")

    private Map<Integer, int[]> deskDiceMock = new ConcurrentHashMap<>();
    private Map<Integer, int[]> deskDiceOutMock = new ConcurrentHashMap<>();
    private static ConcurrentMap<Integer, Lock> lockMap = new ConcurrentHashMap<>(256);

    public List<Desk> getAllDesks() {
        return deskMapper.findAll();
    }

    public List<DicingLog> getDicingLogs(int deskNo, Integer startDicingLogId, int pageSize) {
        return dicingLogMapper.getDicingLogs(deskNo, startDicingLogId, pageSize);
    }

    public boolean isStarted(int deskNo) {
        Desk desk = deskMapper.findByDeskNo(deskNo);
        return new Date().getTime() > desk.getStartTime().getTime();
    }

    public void mockDices(int deskNo, String dicesStr) {
        if (dicesStr != null && dicesStr.length() == 6) {
            int []dicesMock = new int[6];
            for (int i = 0; i < dicesStr.length(); i++) {
                dicesMock[i] = Integer.parseInt(dicesStr.substring(i, i + 1));
            }
            deskDiceMock.put(deskNo, dicesMock);
        } else {
            throw new ServiceException(ExceptionCode.SET_DICES_ERROR);
        }
    }

    public void mockDicesOut(int deskNo, String dicesOutStr) {
        if (dicesOutStr != null) {
            String[] dicesOutArr = dicesOutStr.split(",");
            if (dicesOutArr.length == 6) {
                int[] dicesOutMock = new int[6];
                for (int i = 0; i < dicesOutArr.length; i++) {
                    dicesOutMock[i] = Integer.parseInt(dicesOutArr[i]);
                }
                deskDiceOutMock.put(deskNo, dicesOutMock);
            }
        } else {
            throw new ServiceException(ExceptionCode.SET_DICES_ERROR);
        }
    }

    public InitResult init(User user) {
        Lock lock = getLock(user.getDeskNo());
        lock.lock();
        try {
            InitResult initResult = new InitResult();
            initResult.setUser(user);
            Desk desk = deskMapper.findByDeskNo(user.getDeskNo());
            initResult.setDesk(desk);
            List<DeskAward> deskAwards = deskAwardMapper.findByDeskNo(desk.getDeskNo());
            initResult.setDeskAwards(deskAwards);
            initResult.setAwards(awardService.getAll());
            List<User> deskUsers = userService.findByDeskNo(desk.getDeskNo());
            initResult.setDeskUsers(deskUsers);

            Date now = new Date();
            if (now.getTime() < desk.getStartTime().getTime()) {
                // 还没开始
                initResult.setCountDownSec(Long.valueOf(((desk.getStartTime().getTime() - now.getTime()) / 1000)).intValue());
            } else {
                initResult.setAwardDicingLogs(dicingLogMapper.findValidByDeskNo(desk.getDeskNo()));
            }

            settingUsers(initResult, desk, deskUsers);
            return initResult;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取指定桌子的成绩。
     * @param deskNo
     * @return
     */
    public DicingGrade findDeskGrades(int deskNo) {
        List<DicingLog> dicingLogs = dicingLogMapper.findValidByDeskNo(deskNo);
        List<User> deskUsers = userService.findByDeskNo(deskNo);
        DicingGrade dicingGrade = new DicingGrade();
        dicingGrade.setDeskUsers(deskUsers);
        dicingGrade.setDicingLogs(dicingLogs);
        dicingGrade.setDeskAwards(deskAwardMapper.findByDeskNo(deskNo));
        return dicingGrade;
    }

    @Transactional
    public DiceResult dicing(User user, int systemDicing) {
        StopWatch stopWatch = new StopWatch("dicing-SW");
        stopWatch.start("get lock");
        Lock lock = getLock(user.getDeskNo());
        lock.lock();
        stopWatch.stop();
        stopWatch.start("dicing0");
        try {
            return dicing0(user, systemDicing);
        } finally {
            lock.unlock();
            lockMap.remove(user.getUserId());
            stopWatch.stop();
            if (stopWatch.getTotalTimeMillis() > 1000) {
                logger.warn(stopWatch.toString());
            }
        }
    }

    private Lock getLock(int deskNo) {
        Lock lock = lockMap.get(deskNo);
        if (lock == null) {
            lock = new ReentrantLock();
            Lock tempLock = lockMap.putIfAbsent(deskNo, lock);
            if (tempLock != null) {
                lock = tempLock;
            }
        }
        return lock;
    }

    private DiceResult dicing0(User user, int systemDicing) {
        DiceResult diceResult = new DiceResult();
        Desk desk = deskMapper.findByDeskNo(user.getDeskNo());
        if (desk.getState() == Desk.STATE_FINISHED) {
            throw new ServiceException(ExceptionCode.FINISHED);
        }
        diceResult.setDesk(desk);

        boolean isTry = false;

        Date now = new Date();
        if (now.getTime() < desk.getStartTime().getTime()) {
            // 还没开始，试博
            isTry = true;
        } else {
            desk.setState(Desk.STATE_RUNNING);
            if (desk.getCurSeqNo() == 0) {
                // seqNo为0是初始化状态，开始后应置为1
                desk.setCurSeqNo(1);
            }
            if (user.getSeqNo() != desk.getCurSeqNo()) {
                throw new ServiceException(ExceptionCode.SHOULD_WAITING);
            }
        }

        Award award;
        int[] dicesOut = deskDiceOutMock.remove(desk.getDeskNo());
        int[] dices = deskDiceMock.remove(desk.getDeskNo());
        int retry = 1;
        do {
            if (retry < 1) {
                logger.info("重试，提高中奖概率，桌号：{}，用户：{}", user.getDeskNo(), user.getUserName());
            }
            if (dicesOut == null) {
                int max = 200;
                if (systemDicing > 0) {
                    max = max / 2;
                }
                dicesOut = new int[6];
                for (int i = 0; i < dicesOut.length; i++) {
                    dicesOut[i] = ThreadLocalRandom.current().nextInt(max);
                }
            }

            boolean isOut = false;
            for (int i = 0; i < dicesOut.length; i++) {
                if (dicesOut[i] == 0) {
                    isOut = true;
                    break;
                }
            }

            if (dices == null) {
                dices = new int[6];
                for (int i = 0; i < dices.length; i++) {
                    dices[i] = ThreadLocalRandom.current().nextInt(6) + 1;
                }
            }
            Arrays.sort(dices);
            if (isOut) {
                award = awardService.findById(-1);
            } else {
                award = checkAward(dices);
            }
            // 开桌超过30分钟，没有得奖的，重试一次，增加得奖概率
        } while (retry-- > 0
                && award.getAwardId() < 2
                && now.getTime() - desk.getStartTime().getTime() > 30 * 60 * 1000);

        List<DeskAward> deskAwards = deskAwardMapper.findByDeskNo(desk.getDeskNo());
        diceResult.setDeskAwards(deskAwards);

        award = promoteProbability(award, dices, deskAwards, user);


        DicingLog dicingLog  = new DicingLog();
        dicingLog.setAwardId(award.getAwardId());
        dicingLog.setSystemDicing(systemDicing);
        dicingLog.setScore(award.getScore());
        dicingLog.setDices(dices);
        dicingLog.setDicesOut(dicesOut);
        dicingLog.setVoiceIndex(ThreadLocalRandom.current().nextInt(20));
        dicingLog.setDicingTime(new Date());
        dicingLog.setRoundNo(desk.getRoundNo());
        dicingLog.setUserId(user.getUserId());
        dicingLog.setValid(true);
        dicingLog.setDeskNo(desk.getDeskNo());
        diceResult.setDicingLog(dicingLog);

        if (isTry) {
            desk.setState(Desk.STATE_WAITING);
            return diceResult;
        }

        List<User> deskUsers = userService.findByDeskNo(desk.getDeskNo());
        settingUsers(diceResult, desk, deskUsers);

        boolean haszy = false; // 是否有状元
        boolean hasAward = false;
        List<DicingLog> zyDicingLogs = dicingLogMapper.findZYByDeskNo(desk.getDeskNo());
        if (zyDicingLogs != null && zyDicingLogs.size() > 0) {
            diceResult.setLastZY(userService.find(zyDicingLogs.get(0).getUserId()));
            haszy = true;
        }

        if (award.getAwardId() >= 7) {
            // 覆盖之前博的状元
            dicingLogMapper.invalidZYByUserIdAndDeskNo(desk.getDeskNo(), user.getUserId());
            zyDicingLogs = dicingLogMapper.findZYByDeskNo(desk.getDeskNo());
            haszy = true;
            boolean iszy = true;
            if (zyDicingLogs.size() > 0) {
                iszy = iszy(dicingLog, zyDicingLogs);
                diceResult.setCurrZY(user);
            }
            if (iszy) {
                hasAward = true;
            } else {
                diceResult.setCurrZY(userService.find(zyDicingLogs.get(0).getUserId()));
            }
        }

        boolean noRamainingAward = true;
        for (DeskAward deskAward : deskAwards) {
            if ( (deskAward.getAwardId() == award.getAwardId())
                    || (award.getAwardId() >= 7 && deskAward.getAwardId() == 7) ) {
                if (deskAward.getRemaining() > 0) {
                    // 奖品还有剩
                    deskAward.setRemaining(deskAward.getRemaining()  - 1);
                    deskAwardMapper.updateRemaining(deskAward.getDeskAwardId(), deskAward.getRemaining());
                    hasAward = true;
                } else {
                    // 无奖品
                    if (award.getAwardId() < 7) {
                        dicingLog.setValid(false);
                    }
                }
            }
            if (deskAward.getAwardId() < 7 && deskAward.getRemaining() > 0) {
                noRamainingAward = false;
            }
        }

        int deskUserNum = userService.getDeskUserNum(desk.getDeskNo());
        if (desk.getCurSeqNo() == deskUserNum) {
            if (desk.getNoRemainingRoundNo() > 0 && desk.getRoundNo() == desk.getNoRemainingRoundNo() + 1) {
                logger.debug("经过 {} 轮激战，第 {} 桌奖品已经结束", desk.getRoundNo(), desk.getDeskNo());
                desk.setState(Desk.STATE_FINISHED);
            } else {
                if (haszy && noRamainingAward) {
                    logger.debug("经过 {} 轮激战，第 {} 桌奖品已经博完", desk.getRoundNo(), desk.getDeskNo());
                    desk.setNoRemainingRoundNo(desk.getRoundNo());
                }
                desk.setRoundNo(desk.getRoundNo() + 1);
            }
            desk.setCurSeqNo(1);
        } else {
            desk.setCurSeqNo(desk.getCurSeqNo() + 1);
        }

        diceResult.setHasAward(hasAward);

        deskMapper.update(desk);
        dicingLogMapper.add(dicingLog);
        diceResult.setDicingUser(user);

        List<DicingLog> dicingLogs = dicingLogMapper.findValidByDeskNo(desk.getDeskNo());
        for (DicingLog tempDicingLog : dicingLogs) {
            if (tempDicingLog.getAwardId() >= 7) {
                tempDicingLog.setScore(
                        checkAward(tempDicingLog.getDices()).getScore());
            }
        }
        diceResult.setAwardDicingLogs(dicingLogs);
        return diceResult;
    }

    /**
     * 如果是三红，三红无奖品，四进奖品还有剩，则设置二分之一概率变成四进（为加快四进出现概率），如果四进没奖品了，则设置二分之一概率变成对堂
     * @param award
     * @param dices
     * @param deskAwards
     * @return
     */
    private Award promoteProbability(Award award, int[] dices, List<DeskAward> deskAwards, User user) {
        if (award.getAwardId() == 5) {
            Map<Integer, DeskAward> deskAwardMap = new HashMap<>();
            for (DeskAward deskAward : deskAwards) {
                deskAwardMap.put(deskAward.getAwardId(), deskAward);
            }
            DeskAward deskAwardSanHong = deskAwardMap.get(5);
            DeskAward deskAwardDuitang = deskAwardMap.get(6);
            if (deskAwardSanHong != null
                    && deskAwardSanHong.getRemaining() == 0
                    && ThreadLocalRandom.current().nextInt(2) > 0) {
                DeskAward deskAwardSiJin = deskAwardMap.get(4);
                if (deskAwardSiJin != null && deskAwardSiJin.getRemaining() > 0) {
                    logger.info("三红转四进，桌号：{}，用户：{}", user.getDeskNo(), user.getUserName());
                    // 生成四进
                    int x = ThreadLocalRandom.current().nextInt(6) + 1;
                    while (x == 4) {
                        x = ThreadLocalRandom.current().nextInt(6) + 1;
                    }
                    int y = ThreadLocalRandom.current().nextInt(6) + 1;
                    while (y == x) {
                        y = ThreadLocalRandom.current().nextInt(6) + 1;
                    }
                    int z = ThreadLocalRandom.current().nextInt(6) + 1;
                    while (z == x) {
                        z = ThreadLocalRandom.current().nextInt(6) + 1;
                    }
                    dices[0] = x;
                    dices[1] = x;
                    dices[2] = x;
                    dices[3] = x;
                    dices[4] = y;
                    dices[5] = z;

                    Arrays.sort(dices);
                    award = checkAward(dices);
                } else if (deskAwardDuitang != null && deskAwardDuitang.getRemaining() > 0) {
                    logger.info("三红转对堂，桌号：{}，用户：{}", user.getDeskNo(), user.getUserName());
                    dices[0] = 1;
                    dices[1] = 2;
                    dices[2] = 3;
                    dices[3] = 4;
                    dices[4] = 5;
                    dices[5] = 6;

                    Arrays.sort(dices);
                    award = checkAward(dices);
                }
            }
        }
        return award;
    }

    private void settingUsers(CommonResult commonResult, Desk desk, List<User> deskUsers) {
        int nextSeqNo = desk.getCurSeqNo() + 1;
        if (nextSeqNo > deskUsers.size()) {
            nextSeqNo = 1;
        }
        int preparedSeqNo = nextSeqNo + 1;
        if (preparedSeqNo > deskUsers.size()) {
            preparedSeqNo = 1;
        }
        for (User deskUser : deskUsers) {
            if (deskUser.getSeqNo() == desk.getCurSeqNo()) {
                commonResult.setDicingUser(deskUser);
            }
            if (deskUser.getSeqNo() == nextSeqNo) {
                commonResult.setNextDicingUser(deskUser);
            }
            if (deskUser.getSeqNo() == preparedSeqNo) {
                commonResult.setPreparedDicingUser(deskUser);
            }
        }
    }

    @VisibleForTesting
    public boolean iszy(DicingLog curDicingLog, List<DicingLog> zyDicingLogs) {
        boolean iszy = true;
        for (DicingLog zyDicingLog : zyDicingLogs) {
            if (zyDicingLog.getAwardId() > curDicingLog.getAwardId()) {
                iszy = false;
                break;
            } else if (zyDicingLog.getAwardId() == curDicingLog.getAwardId()) {
                if (zyDicingLog.getScore() >= curDicingLog.getScore()) {
                    iszy = false;
                    break;
                }
            }
        }
        return iszy;
    }

    public int getWuziSum(int[] dices) {
        if (dices[0] == dices[2]) {
            return dices[5];
        }
        return dices[0];
    }

    public int getSihongSum(int[] dices) {
        if (dices[0] == dices[3]) {
            return dices[4] + dices[5];
        } else if (dices[1] == dices[4]) {
            return dices[0] + dices[5];
        } else {
            return dices[0] + dices[1];
        }
    }

    @VisibleForTesting
    public Award checkAward(int[] dices) {
        int counter1 = 0, counter2 = 0, counter3 = 0, counter4 = 0, counter5 = 0, counter6 = 0;
        for (int dice : dices) {
            if (dice == 1) {
                counter1++;
            } else if (dice == 2) {
                counter2++;
            } else if (dice == 3) {
                counter3++;
            } else if (dice == 4) {
                counter4++;
            } else if (dice == 5) {
                counter5++;
            } else if (dice == 6) {
                counter6++;
            }
        }
        Award award;
        if (counter4 == 4 && counter1 == 2) {
            // 状元插金花
            award = awardService.findById(13);
        } else if (counter4 == 6) {
            // 六勃红
            award = awardService.findById(12);
        } else if (counter1 == 6) {
            // 遍地锦
            award = awardService.findById(11);
        } else if (counter2 == 6 || counter3 == 6 || counter5 == 6 || counter6 == 6) {
            // 六勃黑
            award = awardService.findById(10);
            award.setScore(dices[0]);
        } else if (counter4 == 5) {
            // 五红
            award = awardService.findById(9);
            award.setScore(getWuziSum(dices));
        } else if (counter1 == 5 || counter2 == 5 || counter3 == 5 || counter5 == 5 || counter6 == 5) {
            // 五子
            award = awardService.findById(8);
            award.setScore(getWuziSum(dices));
        } else if (counter4 == 4) {
            // 四红
            award = awardService.findById(7);
            award.setScore(getSihongSum(dices));
        } else if (counter1 == 1 && counter2 == 1 && counter3 == 1 && counter4 == 1 && counter5 == 1 && counter6 == 1) {
            // 顺子，对堂
            award = awardService.findById(6);
        } else if (counter4 == 3) {
            // 三红
            award = awardService.findById(5);
        } else if (counter1 == 4 || counter2 == 4 || counter3 == 4 || counter5 == 4 || counter6 == 4) {
            // 四进
            award = awardService.findById(4);
        } else if (counter4 == 2) {
            // 二举
            award = awardService.findById(3);
        } else if (counter4 == 1) {
            // 一秀
            award = awardService.findById(2);
        } else {
            // 无
            award = awardService.findById(1);
        }

        return award;
    }

    @Transactional
    public void resetDesk(int deskNo, Date startTime) {
        Lock lock = getLock(deskNo);
        lock.lock();
        try {
            deskMapper.resetDesk(deskNo, startTime);
            deskAwardMapper.removeByDeskNo(deskNo);
            deskAwardMapper.init(deskNo);
            dicingLogMapper.removeByDeskNo(deskNo);
        } finally {
            lock.unlock();
        }
    }

    public Date getStartTime(int deskNo) {
        return deskMapper.findByDeskNo(deskNo).getStartTime();
    }
}
