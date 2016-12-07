package com.shiliu.game.domain;

import com.shiliu.game.utils.LongIdWorker;

public class Ballot {
    private String ballotId;

    private String gameId;

    private String startTime;

    private String endTime;

    private String title;

    private String openId;

    private String lab1;

    private String lab2;

    public String getBallotId() {
        return Long.toString(LongIdWorker.getDataId());
    }

    public void setBallotId(String ballotId) {
        this.ballotId = ballotId == null ? null : ballotId.trim();
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId == null ? null : gameId.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getLab1() {
        return lab1;
    }

    public void setLab1(String lab1) {
        this.lab1 = lab1 == null ? null : lab1.trim();
    }

    public String getLab2() {
        return lab2;
    }

    public void setLab2(String lab2) {
        this.lab2 = lab2 == null ? null : lab2.trim();
    }
}