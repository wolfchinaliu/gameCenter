package com.shiliu.game.domain;

import java.util.Date;

public class GameAwardRecode extends GameAwardRecodeKey {
    private String prizeName;

    private String prizeValue;

    private Integer level;

    private Date addTime;

    private String moblie;

    private String nickName;

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }

    public String getPrizeValue() {
        return prizeValue;
    }

    public void setPrizeValue(String prizeValue) {
        this.prizeValue = prizeValue == null ? null : prizeValue.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie == null ? null : moblie.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }
}