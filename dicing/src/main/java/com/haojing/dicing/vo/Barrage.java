package com.haojing.dicing.vo;

import com.haojing.dicing.entity.User;

/**
 * Created by yeweiping on 2020/9/28.
 */
public class Barrage {
    private User user;
    private String text;
    private String color;
    private int screenTime;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getScreenTime() {
        return screenTime;
    }

    public void setScreenTime(int screenTime) {
        this.screenTime = screenTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
