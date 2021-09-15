package com.haojing.dicing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by yeweiping on 2020/9/15.
 */
public class User {
    private long userId;
    private String userName;
    private String phone;
    private boolean ready;
    private int deskNo;
    private int seqNo;
    private String avator;
    private String orgName;
    @JsonIgnore
    private long startDicingTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return (int) (userId ^ (userId >>> 32));
    }

    public long getStartDicingTime() {
        return startDicingTime;
    }

    public void setStartDicingTime(long startDicingTime) {
        this.startDicingTime = startDicingTime;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getDeskNo() {
        return deskNo;
    }

    public void setDeskNo(int deskNo) {
        this.deskNo = deskNo;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }
}
