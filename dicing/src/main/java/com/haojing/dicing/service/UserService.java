package com.haojing.dicing.service;

import com.haojing.dicing.dao.DicingLogMapper;
import com.haojing.dicing.dao.UserMapper;
import com.haojing.dicing.entity.Award;
import com.haojing.dicing.entity.DicingLog;
import com.haojing.dicing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yeweiping on 2020/9/15.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DicingLogMapper dicingLogMapper;
    @Autowired
    private AwardService awardService;

    public User find(long userId) {
        return userMapper.find(userId);
    }

    public List<User> findByDeskNo(int deskNo) {
        return userMapper.findByDeskNo(deskNo);
    }

    public User findByDeskNoAndSeqNo(int deskNo, int seqNo) {
        return userMapper.findByDeskNoAndSeqNo(deskNo, seqNo);
    }

    public int getDeskUserNum(int deskNo) {
        return userMapper.countByDeskNo(deskNo);
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public int updateAvator(long userId, String avator) {
        return userMapper.updateAvator(userId, avator);
    }

//    public void updateReady(User user) {
//        if (!dicingService.isStarted()) {
//            throw new ServiceException(ExceptionCode.NOT_START);
//        }
//        Desk desk = deskMapper.findByDeskNo(user.getDeskNo());
//        if (desk.getState() == Desk.STATE_FINISHED) {
//            throw new ServiceException(ExceptionCode.FINISHED);
//        }
//
//        userMapper.updateReady(user.getUserId(), true);
//    }

    public Map<Integer, List<Award>> getAwards(User user) {
        List<DicingLog> dicingLogs = dicingLogMapper.findAwardByUserId(user.getUserId());
        Map<Integer, List<Award>> myAwards = new HashMap<>();
        for (DicingLog dicingLog : dicingLogs) {
            List<Award> subs = myAwards.get(dicingLog.getAwardId());
            if (subs == null) {
                subs = new ArrayList<>();
                myAwards.put(dicingLog.getAwardId(), subs);
            }
            subs.add(awardService.findById(dicingLog.getAwardId()));
        }
        return myAwards;
    }
}
