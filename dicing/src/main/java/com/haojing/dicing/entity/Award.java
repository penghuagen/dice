package com.haojing.dicing.entity;

/**
 * Created by yeweiping on 2020/9/15.
 */
public class Award {
    private int awardId;
    private String awardName;
    private int level;
    private int score;
    private String subName;

    public Award() {
    }

    public Award(int awardId, String awardName, int level, int score, String subName) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.level = level;
        this.score = score;
        this.subName = subName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
