package com.haojing.dicing.entity;

/**
 * Created by yeweiping on 2020/9/15.
 */
public class DeskAward {
    private int deskAwardId;
    private int deskNo;
    private int awardId;
    private int counter;
    private int remaining;

    public int getDeskAwardId() {
        return deskAwardId;
    }

    public void setDeskAwardId(int deskAwardId) {
        this.deskAwardId = deskAwardId;
    }

    public int getDeskNo() {
        return deskNo;
    }

    public void setDeskNo(int deskNo) {
        this.deskNo = deskNo;
    }

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

}
