package com.haojing.dicing.vo;

import com.haojing.dicing.entity.DeskAward;
import com.haojing.dicing.entity.DicingLog;
import com.haojing.dicing.entity.User;

import java.util.List;

/**
 * Created by yeweiping on 2020/9/18.
 */
public class DicingGrade {
    private List<DicingLog> dicingLogs;
    private List<User> deskUsers;
    private List<DeskAward> deskAwards;

    public List<DeskAward> getDeskAwards() {
        return deskAwards;
    }

    public void setDeskAwards(List<DeskAward> deskAwards) {
        this.deskAwards = deskAwards;
    }

    public List<DicingLog> getDicingLogs() {
        return dicingLogs;
    }

    public void setDicingLogs(List<DicingLog> dicingLogs) {
        this.dicingLogs = dicingLogs;
    }

    public List<User> getDeskUsers() {
        return deskUsers;
    }

    public void setDeskUsers(List<User> deskUsers) {
        this.deskUsers = deskUsers;
    }
}
