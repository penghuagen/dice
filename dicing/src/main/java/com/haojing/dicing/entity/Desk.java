package com.haojing.dicing.entity;

import java.util.Date;

/**
 * Created by yeweiping on 2020/9/15.
 */
public class Desk {
    public static final int STATE_WAITING = 0;
    public static final int STATE_RUNNING = 1;
    public static final int STATE_FINISHED = 2;
    private int deskId;
    private int deskNo;
    private int state;
    private int roundNo;
    private int curSeqNo;
    private int noRemainingRoundNo;
    private Date startTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getNoRemainingRoundNo() {
        return noRemainingRoundNo;
    }

    public void setNoRemainingRoundNo(int noRemainingRoundNo) {
        this.noRemainingRoundNo = noRemainingRoundNo;
    }

    public int getDeskId() {
        return deskId;
    }

    public void setDeskId(int deskId) {
        this.deskId = deskId;
    }

    public int getDeskNo() {
        return deskNo;
    }

    public void setDeskNo(int deskNo) {
        this.deskNo = deskNo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(int roundNo) {
        this.roundNo = roundNo;
    }

    public int getCurSeqNo() {
        return curSeqNo;
    }

    public void setCurSeqNo(int curSeqNo) {
        this.curSeqNo = curSeqNo;
    }
}
