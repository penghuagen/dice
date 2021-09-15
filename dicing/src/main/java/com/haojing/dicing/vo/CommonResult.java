package com.haojing.dicing.vo;

import com.haojing.dicing.entity.Desk;
import com.haojing.dicing.entity.DeskAward;
import com.haojing.dicing.entity.DicingLog;
import com.haojing.dicing.entity.User;

import java.util.List;

/**
 * Created by yeweiping on 2020/9/17.
 */
public abstract class CommonResult {
    protected Desk desk;
    protected List<DeskAward> deskAwards;
    protected User dicingUser;
    protected User nextDicingUser;
    protected User preparedDicingUser;
    protected List<DicingLog> awardDicingLogs;

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    public List<DeskAward> getDeskAwards() {
        return deskAwards;
    }

    public void setDeskAwards(List<DeskAward> deskAwards) {
        this.deskAwards = deskAwards;
    }

    public User getDicingUser() {
        return dicingUser;
    }

    public void setDicingUser(User dicingUser) {
        this.dicingUser = dicingUser;
    }

    public User getNextDicingUser() {
        return nextDicingUser;
    }

    public void setNextDicingUser(User nextDicingUser) {
        this.nextDicingUser = nextDicingUser;
    }

    public User getPreparedDicingUser() {
        return preparedDicingUser;
    }

    public void setPreparedDicingUser(User preparedDicingUser) {
        this.preparedDicingUser = preparedDicingUser;
    }

    public List<DicingLog> getAwardDicingLogs() {
        return awardDicingLogs;
    }

    public void setAwardDicingLogs(List<DicingLog> awardDicingLogs) {
        this.awardDicingLogs = awardDicingLogs;
    }
}
