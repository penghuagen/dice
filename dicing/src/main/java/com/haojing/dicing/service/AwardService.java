package com.haojing.dicing.service;

import com.haojing.dicing.dao.AwardMapper;
import com.haojing.dicing.entity.Award;
import org.springframework.beans.factory.InitializingBean;
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
public class AwardService implements InitializingBean {
    private static Map<Integer, Award> allAwardMap = new HashMap<>();
    private static List<Award> allAwards = new ArrayList<>();

    @Autowired
    private AwardMapper awardMapper;

    public void init() {
        allAwards = awardMapper.findAll();
        for (Award award : allAwards) {
            allAwardMap.put(award.getAwardId(), award);
        }
    }

    public Award findById(int awardId) {
        return allAwardMap.get(awardId);
    }

    public List<Award> getAll() {
        return allAwards;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
