package com.haojing.dicing.vo;

import com.haojing.dicing.entity.*;

import java.util.List;

/**
 * Created by yeweiping on 2020/9/15.
 */
public class InitResult extends CommonResult {
    private List<Award> awards;
    private List<User> deskUsers;
    private User user;
    private int countDownSec;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getDeskUsers() {
        return deskUsers;
    }

    public void setDeskUsers(List<User> deskUsers) {
        this.deskUsers = deskUsers;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }

    public int getCountDownSec() {
        return countDownSec;
    }

    public void setCountDownSec(int countDownSec) {
        this.countDownSec = countDownSec;
    }
}
