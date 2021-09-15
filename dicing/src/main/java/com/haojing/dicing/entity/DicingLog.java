package com.haojing.dicing.entity;

import java.util.Date;

/**
 * Created by yeweiping on 2020/9/15.
 */
public class DicingLog {
    private int dicingLogId;
    private int[] dices;
    private int awardId;
    private long userId;
    private int roundNo;
    private Date dicingTime;
    private boolean valid;
    private int deskNo;
    private int score;
    private int voiceIndex;
    private int[] dicesOut;
    private int systemDicing;

    public int getSystemDicing() {
        return systemDicing;
    }

    public void setSystemDicing(int systemDicing) {
        this.systemDicing = systemDicing;
    }

    public int[] getDicesOut() {
        return dicesOut;
    }

    public void setDicesOut(int[] dicesOut) {
        this.dicesOut = dicesOut;
    }

    public int getVoiceIndex() {
        return voiceIndex;
    }

    public void setVoiceIndex(int voiceIndex) {
        this.voiceIndex = voiceIndex;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDeskNo() {
        return deskNo;
    }

    public void setDeskNo(int deskNo) {
        this.deskNo = deskNo;
    }

    public int getDicingLogId() {
        return dicingLogId;
    }

    public void setDicingLogId(int dicingLogId) {
        this.dicingLogId = dicingLogId;
    }

    public int[] getDices() {
        return dices;
    }

    public void setDices(int[] dices) {
        this.dices = dices;
    }

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(int roundNo) {
        this.roundNo = roundNo;
    }

    public Date getDicingTime() {
        return dicingTime;
    }

    public void setDicingTime(Date dicingTime) {
        this.dicingTime = dicingTime;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
