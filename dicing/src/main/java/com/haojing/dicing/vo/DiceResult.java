package com.haojing.dicing.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haojing.dicing.entity.DicingLog;
import com.haojing.dicing.entity.User;

/**
 * Created by yeweiping on 2020/9/15.
 */
public class DiceResult extends CommonResult {
    private DicingLog dicingLog;
    private boolean hasAward;
    @JsonIgnore
    private User lastZY;
    @JsonIgnore
    private User currZY;

    public User getCurrZY() {
        return currZY;
    }

    public void setCurrZY(User currZY) {
        this.currZY = currZY;
    }

    public User getLastZY() {
        return lastZY;
    }

    public void setLastZY(User lastZY) {
        this.lastZY = lastZY;
    }

    public DicingLog getDicingLog() {
        return dicingLog;
    }

    public void setDicingLog(DicingLog dicingLog) {
        this.dicingLog = dicingLog;
    }

    public boolean isHasAward() {
        return hasAward;
    }

    public void setHasAward(boolean hasAward) {
        this.hasAward = hasAward;
    }
}
